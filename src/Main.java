import Control.RelatorioEstoqueControl;
import Model.Categoria;
import Model.Fornecedor;
import Model.Produto;
import Persistence.Dados;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    static void addProdutos() {
        Fornecedor forn = new Fornecedor();
        forn.setContato("contForn");
        forn.setNome("nomeForn");

        ArrayList<Fornecedor> tmpForns = new ArrayList<Fornecedor>();
        tmpForns.add(forn);

        Dados.setFornecedores(tmpForns);

        /*

        this.codigo = codigo;
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMin = quantidadeMin;
        this.preco = preco;
        this.excluido = excluido;
        this.fornecedor = fornecedor;
         */
        Produto tmpProd = new Produto(1, "produto1", 9, 10, 15.99F, false, forn);
        Produto tmpProd2 = new Produto(1, "produto2", 11, 10, 15.99F, false, forn);

        ArrayList<Produto> vecProds = new ArrayList<Produto>();
        vecProds.add(tmpProd);
        vecProds.add(tmpProd2);

        ArrayList<Produto> vecProds2 = new ArrayList<Produto>();
        vecProds2.add(tmpProd2);
        vecProds2.add(tmpProd2);
        vecProds2.add(tmpProd2);
        vecProds2.add(tmpProd);


        Categoria cat = new Categoria();
        cat.setNome("nomeCat");
        cat.setProdutos(vecProds);

        Categoria cat2 = new Categoria();
        cat2.setNome("nomeCat2");
        cat2.setProdutos(vecProds2);

        ArrayList<Categoria> tmpCats = new ArrayList<Categoria>();
        tmpCats.add(cat);
        tmpCats.add(cat2);

        Dados.setCategorias(tmpCats);


    }

    public static void main(String[] args) {

        addProdutos();

        if (JOptionPane.showInputDialog("ver produtos?").equals("sim")) {

            JOptionPane.showMessageDialog(null, RelatorioEstoqueControl.todosProdutos());


            JOptionPane.showMessageDialog(null, RelatorioEstoqueControl.filtroCategoria("nomeCat2"));

        }


    }
}