package Control;

import Model.Categoria;
import Model.ProdRelatorio;
import Model.Produto;
import Persistence.Dados;

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

}
