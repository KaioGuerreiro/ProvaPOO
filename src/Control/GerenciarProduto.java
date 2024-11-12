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

    public static String getCategoriaFrom(Produto p) {
        for (Categoria c : Dados.getCategorias()) {
            for (Produto pc : c.getProdutos()) {
                if (pc == p) return c.getNome();
            }
        }
        return "categoria não identificada";
    }

    public static ArrayList<Produto> getAll(boolean onlyActive) {
        ArrayList<Produto> allProd = new ArrayList<>();

        for (Categoria c : Dados.getCategorias()) {
            for (Produto p : c.getProdutos()) {
                if (!p.isExcluido() || !onlyActive) { //So add se não excluido, ou, se onlyActive for false.
                    allProd.add(p);
                }
            }
        }

        return allProd;
    }

    private static int getNextCodi() {
        int max = 1;
        for (Produto p : getAll(false)) {
            if (p.getCodigo() == max) max = p.getCodigo() + 1;
        }
        return max;
    }

    private static Produto criar() {
        //int codigo, String nome, int quantidadeEstoque, int quantidadeMin, float preco, boolean excluido, Fornecedor fornecedor
        Integer codi = getNextCodi();
        Fornecedor selForn = null;
        String nome = "Padrao";
        Float preco = 0F;

        Integer qntEst = 1;
        Integer qntMinEst = 0;
        boolean excl = false;

        Integer atribModify = null;
        while (true) {
            String[] cBoxArr = {"Codigo: " + codi, "Nome: " + nome, "Estoque Inicial: " + qntEst,
                    "Estoque Minimo: " + qntMinEst, "Valor unitário: " + preco, "Fornecedor: " + selForn};

            //Isso grava a seleção do usuario
            atribModify = View.Cadastros.showOpcoes("Criando produto", cBoxArr, atribModify);

            switch (atribModify) {
                case 0: {
                    try {
                        codi = SafeInputControl.sInteger("Cadastro de Produtos", "Codigo do produto:");

                        int[] found = encontrar(codi);
                        if (found[0] >= 0 || found[1] >= 0) {
                            JOptionPane.showMessageDialog(null, "Esse produto já existe! digite outro.");
                            codi = null;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    continue;
                }
                case 1: {
                    try {
                        nome = SafeInputControl.sString("Cadastro de Produtos", "Nome do produto:");
                    } catch (Exception e) {
                        continue;
                    }

                    continue;
                }
                case 2: {
                    try {
                        qntEst = SafeInputControl.sInteger("Cadastro de Produtos", "Estoque inicial:");
                        if (qntEst < 0) {
                            JOptionPane.showMessageDialog(null, "O estoque nao pode ser negativo!");
                            qntEst = null;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    continue;
                }
                case 3: {
                    try {
                        qntMinEst = SafeInputControl.sInteger("Cadastro de Produtos", "Quantidade minima do estoque:");
                        if (qntMinEst < 0) {
                            JOptionPane.showMessageDialog(null, "Quantidade minima não pode ser negativa!");
                            qntMinEst = null;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    continue;
                }
                case 4: {
                    try {
                        preco = SafeInputControl.sFloat("Cadastro de Produtos", "Valor unitário do produto:");
                        if (preco <= 0) {
                            JOptionPane.showMessageDialog(null, "O valor do produto precisa ser maior que 0.");
                            preco = null;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    continue;
                }
                case 5: {
                    selForn = GerenciaFornecedor.selectFornecedor();    //Se retornar nulo, sera tratado la embaixo.
                    continue;
                }
                case -102: {
                    //Cancelou
                    return null;
                }
                case -99: { //Apertou X
                    if (JOptionPane.showConfirmDialog(null, "Deseja salvar esse produto?",
                            "Cadastro de Produto", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) return null;
                    break;
                }
                default: {
                    JOptionPane.showMessageDialog(null, "Retorno não identificado!", "Cadastro de produto", JOptionPane.DEFAULT_OPTION);
                    return null;
                }
            }

            //Validar nulos
            if (
                    codi == null ||
                            nome == null ||
                            qntEst == null ||
                            qntMinEst == null ||
                            preco == null ||
                            selForn == null
            ) {
                JOptionPane.showMessageDialog(null, "Há algum parametro não preenchido!");
            } else break;
        }

        return new Produto(codi, nome, qntEst, qntMinEst, preco, excl, selForn);
    }

    public static void adicionar() {

        Categoria selCat = GerenciarCategoria.selectCategoria();
        if (selCat == null) return;

        while (true) {
            Produto tmpProd = criar();
            if (tmpProd == null) break;

            //Necessário modificar o vetor de produtos da categoria que o usuario escolheu.

            selCat.getProdutos().add(tmpProd);
            JOptionPane.showMessageDialog(null, "Produto adicionado!");
            return;
        }

        JOptionPane.showMessageDialog(null, "Nenhum produto adicionado!");
    }


    public static Produto selectProduct(boolean onlyActive) {
        ArrayList<Produto> todos = getAll(onlyActive);
        if (todos == null || todos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto ativo encontrado!");
            return null;
        }

        Produto select = (Produto) JOptionPane.showInputDialog(
                null,
                "Selecione o produto:",
                "Produtos Encontrados",
                JOptionPane.QUESTION_MESSAGE,
                null,
                todos.toArray(),
                todos.get(0)
        );

        return select;
    }


    public static void modificar() {

        Produto modP = selectProduct(false);
        if (modP == null) return;

        boolean modificando = true;

        Integer atribModify = null;
        while (modificando) {
                /* O array deve obrigatoriamente ser:
                    nome ; qntEst ; qntmin ; preço ; exluido ; fornecedor
                 */

            String[] cBoxArr = {"Nome: " + modP.getNome(), "Estoque: " + modP.getQuantidadeEstoque(), "Qnt Minima Estoque: " + modP.getQuantidadeMin().toString(),
                    "Valor Unitário: " + modP.getPreco().toString(), "Excluído: " + (modP.isExcluido() ? "sim" : "não"),
                    "Fornecedor: " + modP.getFornecedor().getNome()};

            atribModify = View.Cadastros.showOpcoes("Modificar", cBoxArr, atribModify);
            switch (atribModify) {
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
                        modP.setQuantidadeEstoque(SafeInputControl.sInteger("Cadastro de Produto", "Quantidade em estoque: "));
                    } catch (Exception e) {
                        break;
                    }

                    break;
                }
                case 2: {

                    try {
                        modP.setQuantidadeMin(SafeInputControl.sInteger("Cadastro de Produto", "Quantidade minima estoque: "));
                    } catch (Exception e) {
                        break;
                    }

                    break;
                }
                case 3: {

                    try {
                        modP.setPreco(SafeInputControl.sFloat("Cadastro de Produto", "Valor unitário:"));
                    } catch (Exception e) {
                        break;
                    }

                    break;
                }
                case 4: {

                    int opt = JOptionPane.showConfirmDialog(null, "Esse produto está excluido?", "Cadastro de Produto",
                            JOptionPane.YES_NO_CANCEL_OPTION);

                    if (opt == JOptionPane.CANCEL_OPTION) break;

                    modP.setExcluido(opt == JOptionPane.YES_OPTION ? true : false);

                    break;
                }
                case 5: {

                    Fornecedor selForn = GerenciaFornecedor.selectFornecedor();
                    if (selForn == null) break;

                    modP.setFornecedor(selForn);

                    break;
                }
                default:
                    modificando = false;
            }
        }
    }

    public static void listagem() {
        String res = "";
        for (Categoria c : Dados.getCategorias()) {
            String categ = c.getNome();
            for (Produto p : c.getProdutos()) {
                res += "Categoria: " + categ +
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
