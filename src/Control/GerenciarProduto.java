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

            Integer qntEst = 1;
            Integer qntMinEst = 10;
            boolean excl = false;

            try {
                if (preco == null)
                    preco = SafeInputControl.sFloat("Cadastro de Produtos", "Valor unitário do produto:");
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
                return;
            }

            int indexCateg = GerenciarCategoria.encontrar(categ);
            if (indexCateg < 0) {
                JOptionPane.showMessageDialog(null, "Categoria nao encontrada! digite outra.");
                categ = null;
                continue;
            }

            Produto tmpProd = criar();
            if (tmpProd == null) return;

            //Necessário modificar o vetor de produtos da categoria que o usuario escolheu.

            Categoria tmpCate = Dados.getCategorias().get(indexCateg);  //Categoria que o usuario digitou.

            ArrayList<Produto> tmpProds = tmpCate.getProdutos();    //Vetor de produtos da categoria que o usuario escolheu.
            tmpProds.add(tmpProd);  //adicionando o novo produto à esse vetor.

            tmpCate.setProdutos(tmpProds);  //Devolvendo o vetor de produtos com o novo produto à categoria.

            ArrayList<Categoria> allCategorias = Dados.getCategorias(); //Fazendo uma cópia do vetor de categoria princiapal.
            allCategorias.set(indexCateg, tmpCate); //Modificando a categoria (e consequentemente a dicionando o novo produto).

            Dados.setCategorias(allCategorias); //Devolvendo o novo array de categorias ao Dados, já com o novo produto.

            JOptionPane.showMessageDialog(null, "Produto adicionado!");
            return;
        }
    }
}
