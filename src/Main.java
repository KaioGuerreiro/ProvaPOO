import Control.RelatorioEstoqueControl;
import Model.Categoria;
import Model.Fornecedor;
import Model.ProdRelatorio;
import Model.Produto;
import Persistence.Dados;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    static void addProdutos(){
        Fornecedor forn = new Fornecedor();
        forn.setContato("contForn");
        forn.setNome("nomeForn");



        /*

        this.codigo = codigo;
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMin = quantidadeMin;
        this.preco = preco;
        this.excluido = excluido;
        this.fornecedor = fornecedor;
         */
        Produto tmpProd = new Produto(1, "produto1", 1, 10, 15,false, forn);
        ArrayList<Produto> tmpProds = new ArrayList<Produto>();
        tmpProds.add(tmpProd);

        Categoria cat = new Categoria();
        cat.setNome("nomeCat");
        cat.setProdutos(tmpProds);
    }
    public static void main(String[] args) {

        addProdutos();

        if (JOptionPane.showInputDialog("ver produtos?").equals("sim")){

            JOptionPane.showMessageDialog(null, RelatorioEstoqueControl.todosProdutos());

        }


    }
}