package Control;

import Model.Categoria;
import Model.Fornecedor;
import Model.Produto;
import Persistence.Dados;

import javax.swing.*;
import java.util.ArrayList;

public class GerenciarProduto {
    /*
    encontrar
    criar
    adicionar
    modificar
    excluir
     */

    public static int[] encontrar(Integer codigo) {
        ArrayList<Categoria> catArr = Dados.getCategorias();

        for (int i = 0; i < catArr.size(); i++) {
            ArrayList<Produto> prodArr = catArr.get(i).getProdutos();

            for (int ii = 0; ii < prodArr.size(); ii++) {
                if (codigo.equals(prodArr.get(ii).getCodigo())) {
                    return new int[]{i, ii};
                }
            }
        }

        return new int[]{-1, -1};
    }

    private static Produto criar() {
        //int codigo, String nome, int quantidadeEstoque, int quantidadeMin, float preco, boolean excluido, Fornecedor fornecedor
        Integer codi = null;
        String forn = null;
        String nome = null;
        Float preco = null;

        while (true) {
            try {
                if (codi == null) codi = SafeInputControl.sInteger("Cadastro de Produtos", "Codigo do produto:");
            } catch (Exception e) {
                return null;
            }

            int[] found = encontrar(codi);
            if (found[0] >= 0 || found[1] >= 0) {
                JOptionPane.showMessageDialog(null, "Esse produto já existe! digite outro.");
                codi = null;
                continue;
            }

            try {
                if (nome == null) nome = SafeInputControl.sString("Cadastro de Produtos", "Nome do produto:");
            } catch (Exception e) {
                return null;
            }

            Integer qntEst = 15;
            Integer qntMinEst = 10;
            boolean excl = false;

            try {
                if (preco == null)
                    preco = SafeInputControl.sFloat("Cadastro de Produtos", "Valor unitário do produto:");
                if (preco <= 0) {
                    JOptionPane.showMessageDialog(null, "O valor do produto precisa ser maior que 0.");
                    preco = null;
                    continue;
                }
            } catch (Exception e) {
                return null;
            }

            try {
                if (forn == null) forn = SafeInputControl.sString("Cadastro de Produtos", "Nome do fornecedor");
            } catch (Exception e) {
                return null;
            }

            int indexForn = GerenciaFornecedor.encontrar(forn);
            if (indexForn < 0) {
                JOptionPane.showMessageDialog(null, "Fornecedor não encontrado! digite outro.");
                forn = null;
                continue;
            }

            Fornecedor fornecedor = Dados.getFornecedores().get(indexForn);

            return new Produto(codi, nome, qntEst, qntMinEst, preco, excl, fornecedor);
        }
    }

    public static void adicionar() {
        String categ = null;

        while (true) {
            try {
                if (categ == null) categ = SafeInputControl.sString("Cadastro de Produtos", "Nome da categoria");
            } catch (Exception e) {
                break;
            }

            int indexCateg = GerenciarCategoria.encontrar(categ);
            if (indexCateg < 0) {
                JOptionPane.showMessageDialog(null, "Categoria nao encontrada! digite outra.");
                categ = null;
                continue;
            }

            Produto tmpProd = criar();
            if (tmpProd == null) break;

            //Necessário modificar o vetor de produtos da categoria que o usuario escolheu.

            Dados.getCategorias().get(indexCateg).getProdutos().add(tmpProd);
            JOptionPane.showMessageDialog(null, "Produto adicionado!");
            return;
        }

        JOptionPane.showMessageDialog(null, "Nenhum produto adicionado!");
    }

    public static void modificar() {

        Integer idProd = null;
        int[] prodIndex = {-1, -1};

        boolean prodFound = false;
        while (!prodFound) {
            try {
                idProd = SafeInputControl.sInteger("Cadastro de Produto", "Id do produto à alterar:");
            } catch (Exception e) {
                return; //usuario cancelou
            }

            prodIndex = encontrar(idProd);
            if (prodIndex[0] < 0 || prodIndex[1] < 0) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado! digite outro.");
            } else prodFound = true;
        }

        boolean modificando = true;
        while (modificando) {
                /* O array deve obrigatoriamente ser:
                    nome ; qntmin ; preço ; exluido ; fornecedor
                 */
            Produto modP = Dados.getCategorias().get(prodIndex[0]).getProdutos().get(prodIndex[1]);

            String[] cBoxArr = {"Nome: " + modP.getNome(), "Qnt Minima Estoque: " + modP.getQuantidadeMin().toString(),
                    "Valor Unitário: " + modP.getPreco().toString(), "Excluído: " + (modP.isExcluido() ? "sim" : "não"),
                    "Fornecedor: " + modP.getFornecedor().getNome()};

            switch (View.GerProduto.modificar(cBoxArr)) {
                case 0: {

                    try {
                        modP.setNome(SafeInputControl.sString("Cadastro de Produto", "Novo nome:"));
                    } catch (Exception e) {
                        break;
                    }

                    break;
                }
                case 1: {

                    try {
                        modP.setQuantidadeMin(SafeInputControl.sInteger("Cadastro de Produto", "Quantidade minima estoque: "));
                    } catch (Exception e) {
                        break;
                    }

                    break;
                }
                case 2: {

                    try {
                        modP.setPreco(SafeInputControl.sFloat("Cadastro de Produto", "Valor unitário:"));
                    } catch (Exception e) {
                        break;
                    }

                    break;
                }
                case 3: {

                    int opt = JOptionPane.showConfirmDialog(null, "Esse produto está excluido?", "Cadastro de Produto",
                            JOptionPane.YES_NO_CANCEL_OPTION);

                    if (opt == JOptionPane.CANCEL_OPTION) break;

                    modP.setExcluido(opt == JOptionPane.YES_OPTION ? true : false);

                    break;
                }
                case 4: {

                    String nomeForn;
                    try {
                        nomeForn = SafeInputControl.sString("Cadastro de Produto", "Digite o nome do fornecedor à alterar:");
                    } catch (Exception e) {
                        break;
                    }

                    int fornIndex = GerenciaFornecedor.encontrar(nomeForn);
                    if (fornIndex < 0) {
                        JOptionPane.showMessageDialog(null, "Fornecedor não encontrado! digite outro.");
                    } else {
                        modP.getFornecedor().setNome(nomeForn);
                    }

                    break;
                }
                default:
                    modificando = false;
            }
        }
    }

    public static void ajustarEstoque() {
        Integer idCod = null;
        Integer adjEst = null;

        while (true) {

            try {
                if (idCod == null)
                    idCod = SafeInputControl.sInteger("Ajuste de Estoque", "Codigo do produto à ajustar:");
            } catch (Exception e) {
                break;
            }

            int[] found = encontrar(idCod);
            if (found[0] < 0 || found[1] < 0) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado. Digite outro");
                idCod = null;
                continue;
            }

            try {
                if (adjEst == null)
                    adjEst = SafeInputControl.sInteger("Ajuste de Estoque", "Ajustar o estoque em: (pode usar valor negativo)");
            } catch (Exception e) {
                break;
            }


            Produto act = Dados.getCategorias().get(found[0]).getProdutos().get(found[1]);

            if (act.getQuantidadeEstoque() + adjEst < 0) {
                JOptionPane.showMessageDialog(null, "Esse ajuste ficará negativo! Negado!");
                adjEst = null;
                continue;
            }

            act.setQuantidadeEstoque(act.getQuantidadeEstoque() + adjEst);
            JOptionPane.showMessageDialog(null, "Ajuste realizado!");
            return;
        }

        JOptionPane.showMessageDialog(null, "Nenhum ajuste realizado!");
    }


    public static void excluir() {
        Integer idCod = null;

        while (true) {
            try {
                if (idCod == null) idCod = SafeInputControl.sInteger("Realizando Venda", "Digite o ID do produto:");
            } catch (Exception e) {
                break;
            }

            int[] indexProd = GerenciarProduto.encontrar(idCod);
            if (indexProd[0] < 0 || indexProd[1] < 0) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado, digite outro.");
                idCod = null;
                continue;
            }

            Dados.getCategorias().get(indexProd[0]).getProdutos().get(indexProd[1]).setExcluido(true);

            JOptionPane.showMessageDialog(null, "Produto excluido");
            return;
        }

        JOptionPane.showMessageDialog(null, "Nenhum produto excluido");
    }

    public static void listagem() {
        String res = "";
        for (Categoria c : Dados.getCategorias()) {
            String nomeCat = c.getNome();
            for (Produto p : c.getProdutos()) {
                res += "Categoria: " + nomeCat +
                        " cod: " + p.getCodigo() +
                        " Nome: " + p.getNome() +
                        " Min: " + p.getQuantidadeMin() +
                        " Act: " + p.getQuantidadeEstoque() +
                        " Preço: " + p.getPreco() +
                        " Excluido: " + p.isExcluido() + "\n";
            }
        }


        JOptionPane.showMessageDialog(null, res);

    }
}
