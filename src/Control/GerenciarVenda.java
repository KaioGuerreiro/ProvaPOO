package Control;

import Model.*;
import Persistence.Dados;

import javax.swing.*;
import java.util.ArrayList;

public class GerenciarVenda {

    private static ProdutoVenda criarPV() {
        Integer idCod = null;
        Integer qntVend = null;

        while (true) {
            try {
                if (idCod == null) idCod = SafeInputControl.sInteger("Realizando Venda", "Digite o ID do produto:");
            } catch (Exception e) {
                return null;
            }

            int[] indexProd = GerenciarProduto.encontrar(idCod);
            if (indexProd[0] < 0 || indexProd[1] < 0) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado, digite outro.");
                idCod = null;
                continue;
            }
            ;

            String nomeProd = Dados.getCategorias().get(indexProd[0]).getProdutos().get(indexProd[1]).getNome();
            String nomeCat = Dados.getCategorias().get(indexProd[0]).getNome();
            Float preco = Dados.getCategorias().get(indexProd[0]).getProdutos().get(indexProd[1]).getPreco();
            Fornecedor forn = Dados.getCategorias().get(indexProd[0]).getProdutos().get(indexProd[1]).getFornecedor();

            try {
                if (qntVend == null)
                    qntVend = SafeInputControl.sInteger("Realizando Venda", "Quantos produtos será vendido?");
                if (qntVend < 1) {
                    JOptionPane.showMessageDialog(null, "Você precisa vender no minimo 1 produto.");
                    qntVend = null;
                    continue;
                }
            } catch (Exception e) {
                return null;
            }

            ProdutoVenda tmpPV = new ProdutoVenda();
            tmpPV.setCodigo(idCod);
            tmpPV.setNome(nomeProd);
            tmpPV.setCategoria(nomeCat);
            tmpPV.setPreco(preco);
            tmpPV.setFornecedor(forn);
            tmpPV.setQntVendida(qntVend);


            return tmpPV;
        }
    }

    private static Venda criar() {
        Venda tmpVenda = new Venda();

        if (Dados.getPessoas().get(Dados.getUserLogged()) instanceof Vendedor vend) {
            tmpVenda.setVendedor(vend);
        } else {
            JOptionPane.showMessageDialog(null, "Você não é um vendedor para realizar uma venda!");
            return null;
        }

        Integer idClient = null;

        while (true) {
            try {
                if (idClient == null) idClient = SafeInputControl.sInteger("Realizando Venda", "ID do cliente:");
            } catch (Exception e) {
                return null;
            }

            int clientIndex = GerenciarPessoa.encontrar(idClient);
            if (clientIndex < 0) {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado, digite outro.");
                idClient = null;
                continue;
            }

            if (Dados.getPessoas().get(clientIndex) instanceof Cliente cli) {
                tmpVenda.setCliente(cli);
            } else {
                JOptionPane.showMessageDialog(null, "O cliente selecionado não é de fato um cliente! digite outro.");
                idClient = null;
                continue;
            }


            //Criando uma copia de todos os produtos cadastrados no sistema (para controlar o estoque individual)
            ArrayList<Produto> tmpArrProdutos = new ArrayList<>();
            for (Categoria c : Dados.getCategorias()) {
                for (Produto p : c.getProdutos()) {
                    int codig = p.getCodigo();
                    int qntEsto = p.getQuantidadeEstoque();
                    int qntMini = p.getQuantidadeMin();

                    //a copia só precisa dessas três informações, o resto nunca será utilizado.
                    Produto copyP = new Produto(codig, null, qntEsto, qntMini, 0, false, null);

                    tmpArrProdutos.add(copyP);
                }
            }


            //Essa parte é pra montar o carrinho da venda. Ele verifica o estoque sempre que o usuario adiciona um produto.
            ArrayList<ProdutoVenda> tmpCarrinho = new ArrayList<>();
            while (true) {
                ProdutoVenda pv = criarPV();
                if (pv == null) break;

                boolean canAdd = true;


                //Parte que verifica o estoque
                for (Produto p : tmpArrProdutos) {
                    if (p.getCodigo() == pv.getCodigo()) {
                        int emEst = p.getQuantidadeEstoque();
                        int tentVend = pv.getQntVendida();
                        int res = emEst - tentVend;
                        if (res < 0) {
                            JOptionPane.showMessageDialog(null,
                                    "Você está tentando vender mais do que há no estoque!\n" +
                                            "Estoque: " + emEst +
                                            "\nTentando vender: " + tentVend + "\nEsse produto não será adicionado ao carrinho!");
                            canAdd = false;
                        } else p.setQuantidadeEstoque(res);

                        //Adiciona um aviso de baixo estoque pros ADM, a variável booleana "excluido" é usada pra não adicionar varios avisos pro mesmo produto
                        if (!p.isExcluido() && p.getQuantidadeEstoque() < p.getQuantidadeMin()) {
                            JOptionPane.showMessageDialog(null, "Atenção!\nProduto chegou em sua quantidade minima");
                            p.setExcluido(true);
                        }

                        //Partir pra adição do próximo produto do carrinho
                        break;
                    }
                }


                if (canAdd) tmpCarrinho.add(pv);
            }


            if (tmpCarrinho.isEmpty()) {
                if (JOptionPane.showConfirmDialog(null, "Carrinho não pode ser vazio! Digitar os produtos?", "",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    return null;
                }
                continue;
            }

            tmpVenda.setCarrinho(tmpCarrinho);

            return tmpVenda;
        }
    }

    public static void adicionar() {
        Venda tmpVenda = criar();
        if (tmpVenda == null) {
            JOptionPane.showMessageDialog(null, "Nenhuma venda adicionada!");
            return;
        }


        //Atualizando os novos valores de estoque de cada produto utilizado.
        for (ProdutoVenda pv : tmpVenda.getCarrinho()) {
            for (Categoria c : Dados.getCategorias()) {
                boolean found = false;
                for (Produto p : c.getProdutos()) {
                    if (pv.getCodigo() == p.getCodigo()) {
                        p.setQuantidadeEstoque(p.getQuantidadeEstoque() - pv.getQntVendida());
                        found = true;
                        break;
                    }
                }
                //Se o produto foi encontrado, não tem porque continuar olhando dentro das categorias.
                if (found) break;
            }
        }


        if (Dados.getPessoas().get(Dados.getUserLogged()) instanceof Vendedor vend) {
            vend.setVendasRealizadas(vend.getVendasRealizadas() + 1);
        }

        Dados.getVendas().add(tmpVenda);
        JOptionPane.showMessageDialog(null, "Venda adicionada!");
    }
}
