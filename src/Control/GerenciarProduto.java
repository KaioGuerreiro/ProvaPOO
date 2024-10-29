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
        Integer codi = SafeInputControl.sInteger("Cadastro de Produtos", "Codigo do produto:");
        String nome = SafeInputControl.sString("Cadastro de Produtos", "Nome do produto:");
        Integer qntEst = 1;
        Integer qntMinEst = 10;
        Float preco = SafeInputControl.sFloat("Cadastro de Produtos", "Valor unitário do produto:");
        boolean excl = false;
        String forn = SafeInputControl.sString("Cadastro de Produtos", "Nome do fornecedor");

        int indexForn = GerenciaFornecedor.encontrar(forn);
        if (indexForn < 0) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!");
            return null;
        }

        Fornecedor fornecedor = Dados.getFornecedores().get(indexForn);

        return new Produto(codi, nome, qntEst, qntMinEst, preco, excl, fornecedor);
    }

    public static void adicionar() {
        String categ = SafeInputControl.sString("Cadastro de Produtos", "Nome da categoria");
        if (categ == null) return;

        int indexCateg = GerenciarCategoria.encontrar(categ);
        if (indexCateg < 0) {
            JOptionPane.showMessageDialog(null, "Categoria nao encontrada");
            return;
        }

        Produto tmpProd = criar();
        if (tmpProd == null) return;

        int[] found = encontrar(tmpProd.getCodigo());
        if (found[0] >= 0 || found[1] >= 0) {
            JOptionPane.showMessageDialog(null, "Esse produto já existe!");
        } else {
            //Necessário modificar o vetor de produtos da categoria que o usuario escolheu.

            Categoria tmpCate = Dados.getCategorias().get(indexCateg);  //Categoria que o usuario digitou.

            ArrayList<Produto> tmpProds = tmpCate.getProdutos();    //Vetor de produtos da categoria que o usuario escolheu.
            tmpProds.add(tmpProd);  //adicionando o novo produto à esse vetor.

            tmpCate.setProdutos(tmpProds);  //Devolvendo o vetor de produtos com o novo produto à categoria.

            ArrayList<Categoria> allCategorias = Dados.getCategorias(); //Fazendo uma cópia do vetor de categoria princiapal.
            allCategorias.set(indexCateg, tmpCate); //Modificando a categoria (e consequentemente a dicionando o novo produto).

            Dados.setCategorias(allCategorias); //Devolvendo o novo array de categorias ao Dados, já com o novo produto.

            JOptionPane.showMessageDialog(null, "Produto adicionado!");
        }
    }
}
