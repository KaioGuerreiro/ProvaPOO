package Control;

import Model.Categoria;
import Model.ProdRelatorio;
import Model.Produto;
import Persistence.Dados;
import View.Tela;

import javax.swing.*;
import java.util.ArrayList;

public class RelatorioEstoqueControl {
    private static ArrayList<ProdRelatorio> getAllProducts() {
        ArrayList<ProdRelatorio> listaDeProdutos = new ArrayList<ProdRelatorio>();

        for (Categoria cat : Dados.getCategorias()) {
            String nomeCategoria = cat.getNome();
            for (Produto p : cat.getProdutos()) {
                if (p.isExcluido()) {
                    continue;
                }
                ProdRelatorio tempProdRelatorio = new ProdRelatorio(nomeCategoria, p.getCodigo(), p.getNome(), p.getQuantidadeEstoque(),
                        p.getQuantidadeMin(), p.getPreco(), p.getFornecedor().getNome(), p.getFornecedor().getContato());

                listaDeProdutos.add(tempProdRelatorio);
            }
        }

        return listaDeProdutos;
    }

    public static String todosProdutos() {
        ArrayList<Object> tempListProdutos = new ArrayList<Object>();

        for (ProdRelatorio pr : getAllProducts())
            tempListProdutos.add((Object) pr); // converte para object

        try {
            return ObjToStringControl.relatorioObj(tempListProdutos);
        } catch (Exception e) {
            return null;
        }
    }

    public static String filtroCategoria(String nomeCategoria) {
        ArrayList<Object> tempListProdutos = new ArrayList<Object>();

        for (ProdRelatorio pr : getAllProducts()) {
            if (nomeCategoria.equals(pr.nomeCategoria)) {
                tempListProdutos.add(pr);
            }
        }

        try {
            return ObjToStringControl.relatorioObj(tempListProdutos);
        } catch (Exception e) {
            return null;
        }
    }

    public static String filtroProduto(Integer prodCod) {
        ArrayList<Object> tempListProdutos = new ArrayList<Object>();

        for (ProdRelatorio pr : getAllProducts()) {
            if (prodCod.equals(pr.prodCod)) {
                tempListProdutos.add(pr);
            }
        }

        try {
            return ObjToStringControl.relatorioObj(tempListProdutos);
        } catch (Exception e) {
            return null;
        }
    }

    public static String filtroQuantidade(Integer min, Integer max) {
        ArrayList<Object> tempListProdutos = new ArrayList<Object>();

        for (ProdRelatorio pr : getAllProducts()) {
            if (pr.qntEstoque >= min && pr.qntEstoque <= max) {
                tempListProdutos.add(pr);
            }
        }

        try {
            return ObjToStringControl.relatorioObj(tempListProdutos);
        } catch (Exception e) {
            return null;
        }
    }


    //no futuro, essa será a unica função publica.
    public static void gen() {
        switch (Tela.relatoriosEstoques()) {
            case 0: {   //Todos
                JOptionPane.showMessageDialog(null, todosProdutos(),
                        "todos", JOptionPane.DEFAULT_OPTION);
                break;
            }
            case 1: {   //Categoria
                JOptionPane.showMessageDialog(null, filtroCategoria("nomeCat2"),
                        "Categoria", JOptionPane.DEFAULT_OPTION);
                break;
            }
            case 2: {   //Produto
                JOptionPane.showMessageDialog(null, filtroProduto(1),
                        "produto", JOptionPane.DEFAULT_OPTION);
                break;
            }
            case 3: {   //Quantidade
                JOptionPane.showMessageDialog(null, filtroQuantidade(8, 10),
                        "quantidade", JOptionPane.DEFAULT_OPTION);
                break;
            }
        }
    }
}
