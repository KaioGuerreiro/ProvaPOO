package Control;

import Model.Categoria;
import Model.ProdRelatorio;
import Model.Produto;
import Persistence.Dados;
import View.Relatorios;

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

        String cabecalho = "Nome Categoria;Codigo Produto;Nome Produto;Qnt em Estoque;Qnt Minima;Valor Unitario;Nome Fornecedor;Contato Fornecedor;Baixo Estoque";
        String resultado = null;

        switch (Relatorios.tipoEstoque()) {

            case 0: {   //Todos
                //JOptionPane.showMessageDialog(null, todosProdutos(),
                //        "Mostrando todos os produtos", JOptionPane.DEFAULT_OPTION);
                resultado = todosProdutos();
                break;
            }
            case 1: {   //Categoria
                String nomeCat;
                try {
                    nomeCat = SafeInputControl.sString("Filtrar por categoria", "Nome da categoria:");
                } catch (Exception e) {
                    break;
                }

                //JOptionPane.showMessageDialog(null, filtroCategoria(nomeCat),
                //        "Mostrando os produtos pertencentes à categoria " + nomeCat, JOptionPane.DEFAULT_OPTION);
                resultado = filtroCategoria(nomeCat);
                break;
            }
            case 2: {   //Produto
                Integer prodCod;
                try {
                    prodCod = SafeInputControl.sInteger("Filtrar por Produto", "Codigo do produto:");
                } catch (Exception e) {
                    break;
                }

                //JOptionPane.showMessageDialog(null, filtroProduto(prodCod),
                //        "Mostrando o produto de codigo " + prodCod, JOptionPane.DEFAULT_OPTION);
                resultado = filtroProduto(prodCod);
                break;
            }
            case 3: {   //Quantidade
                Integer min = 8, max = 10;

                try {
                    min = SafeInputControl.sInteger("Filtrar por Quantidade", "Menor quantidade:");
                } catch (Exception e) {
                    break;
                }

                try {
                    max = SafeInputControl.sInteger("Filtrar por Quantidade", "Maior quantidade:");
                } catch (Exception e) {
                    break;
                }

                //JOptionPane.showMessageDialog(null, filtroQuantidade(min, max),
                //        "Mostrando os produtos com estoque entre " + min + " e " + max, JOptionPane.DEFAULT_OPTION);
                resultado = filtroQuantidade(min, max);
                break;
            }
        }

        Relatorios.imprimir(cabecalho, resultado);

    }
}
