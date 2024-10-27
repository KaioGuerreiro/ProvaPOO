package Control;

import Model.Categoria;
import Model.ProdRelatorio;
import Model.Produto;
import Persistence.Dados;

import java.util.ArrayList;

public class RelatorioEstoqueControl {
    private static ArrayList<ProdRelatorio> getAllProducts(Dados dados){
        ArrayList<ProdRelatorio> listaDeProdutos = new ArrayList<ProdRelatorio>();
        ArrayList<Categoria> listaDeCategoria = new ArrayList<>();

        listaDeCategoria = dados.getCategorias(); //copia todas as categorias armazenadas em dados

        for (Categoria cat : listaDeCategoria){
            String nomeCategoria;
            nomeCategoria = cat.getNome();
            for (Produto p : cat.getProdutos()){
                if (p.isExcluido()){
                    continue;
                }
                ProdRelatorio tempProdRelatorio = new ProdRelatorio(nomeCategoria, p.getCodigo(), p.getNome(), p.getQuantidadeEstoque(),
                        p.getQuantidadeMin(), p.getPreco(), p.getFornecedor().getNome(), p.getFornecedor().getContato());

                listaDeProdutos.add(tempProdRelatorio);
            }
        }

        return listaDeProdutos;
    }
    public static String todosProdutos(Dados dados){
        ArrayList<Object> tempListProdutos = new ArrayList<Object>();

        for (ProdRelatorio pr : RelatorioEstoqueControl.getAllProducts(dados))
            tempListProdutos.add((Object) pr); // converte para object

        return ObjToStringControl.relatorioObj(tempListProdutos);
    }

    public static String filtroCategoria(Dados dados, String nomeCategoria){
        ArrayList<Object> tempListProdutos = new ArrayList<Object>();
        ArrayList<ProdRelatorio> listaAllProd = new ArrayList<ProdRelatorio>();
        ArrayList<ProdRelatorio> listaFiltrada = new ArrayList<ProdRelatorio>();

        listaAllProd = RelatorioEstoqueControl.getAllProducts(dados);
        //Nao esta pronta
        return ObjToStringControl.relatorioObj(tempListProdutos);
    }


}
