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
        String nomeForn = null;
        String novoNome = null;


        while (true) {

            try {
                if (idProd == null)
                    idProd = SafeInputControl.sInteger("Cadastro de Produto", "Id do produto à alterar:");
            } catch (Exception e) {
                break;
            }

            int[] prodIndex = encontrar(idProd);
            if (prodIndex[0] < 0 || prodIndex[1] < 0) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado! digite outro.");
                idProd = null;
                continue;
            }

            try {
                if (novoNome == null) novoNome = SafeInputControl.sString("Cadastro de Produto", "Novo nome:");
            } catch (Exception e) {
                break;
            }

            try {
                if (nomeForn == null)
                    nomeForn = SafeInputControl.sString("Cadastro de Produto", "Digite o nome do fornecedor à alterar:");
            } catch (Exception e) {
                break;
            }

            int fornIndex = GerenciaFornecedor.encontrar(nomeForn);
            if (fornIndex < 0) {
                JOptionPane.showMessageDialog(null, "Fornecedor não encontrado! digite outro.");
                nomeForn = null;
                continue;
            }


            Dados.getCategorias().get(prodIndex[0]).getProdutos().get(prodIndex[1]).setNome(novoNome);
            Dados.getCategorias().get(prodIndex[0]).getProdutos().get(prodIndex[1]).setFornecedor(Dados.getFornecedores().get(fornIndex));

            JOptionPane.showMessageDialog(null, "Produto modificado!);");
            return;
        }

        JOptionPane.showMessageDialog(null, "Modificação do produto cancelada!");
    }

    public static void excluir() {

    }
}
