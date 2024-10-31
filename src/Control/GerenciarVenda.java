package Control;

import Model.*;
import Persistence.Dados;

import javax.swing.*;
import java.util.ArrayList;

public class GerenciarVenda {

    private static ProdutoVenda criarPV() {
        Integer qntVend = null;

        Produto selProd = null;


        while (true) {
            if (selProd == null) selProd = GerenciarProduto.selectProduct();
            if (selProd == null) return null;   //Usuario cancelou
            else if (selProd.isExcluido()) {
                JOptionPane.showMessageDialog(null, "Este produto encontra-se excluido, digite outro.");
                selProd = null;
                continue;
            }

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
            tmpPV.setCodigo(selProd.getCodigo());
            tmpPV.setNome(selProd.getNome());
            tmpPV.setCategoria(GerenciarProduto.getCategoriaFrom(selProd));
            tmpPV.setPreco(selProd.getPreco());
            tmpPV.setFornecedor(selProd.getFornecedor());
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


        Cliente selCli = (Cliente) GerenciarPessoa.selectPessoa(GerenciarPessoa.filterByClients(Dados.getPessoas()));
        if (selCli == null) return null; //usuario cancelou
        Cliente copyCli = new Cliente(); //Pro caso de o estino for alterado.
        copyCli.setNome(selCli.getNome());
        copyCli.setDestino(selCli.getDestino());
        copyCli.setId(selCli.getId());
        copyCli.setContato(selCli.getContato());
        tmpVenda.setCliente(copyCli);

        while (true) {

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
                            GerenciarAlertas.adicionar("Baixo estoque", pv.getCodigo() + "|" + pv.getNome());
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
