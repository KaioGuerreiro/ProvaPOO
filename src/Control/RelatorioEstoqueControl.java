package Control;

import Model.Categoria;
import Model.ProdRelatorio;
import Model.Produto;
import Persistence.Dados;

import java.util.ArrayList;

public class RelatorioEstoqueControl {
    static ArrayList<ProdRelatorio> getAllProducts(Dados dados){
        ArrayList<ProdRelatorio> listaDeProdutos = new ArrayList<>();
        ArrayList<Categoria> listaDeCategoria = new ArrayList<>();

        listaDeCategoria = dados.getCategorias(); //copia todas as categorias armazenadas em dados

        for (Categoria cat : listaDeCategoria){
            String nomeCategoria;
            nomeCategoria = cat.getNome();
            for (Produto p : cat.getProdutos()){
                if (p.isExcluido()){
                    continue;
                }
                ProdRelatorio tempProdRelatorio = new ProdRelatorio(nomeCategoria, p.getCodigo(), p.getQuantidadeEstoque(),
                        p.getQuantidadeMin(), p.getPreco(), p.getFornecedor().getNome(), p.getFornecedor().getContato());

                listaDeProdutos.add(tempProdRelatorio);
            }
        }

        return listaDeProdutos;
    }
}
