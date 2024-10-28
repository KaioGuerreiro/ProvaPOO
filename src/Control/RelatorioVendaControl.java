package Control;

import Model.ProdutoVenda;
import Model.Venda;
import Persistence.Dados;

import java.util.ArrayList;

public class RelatorioVendaControl {
    private static ArrayList<Venda> getAllVendas() {
        return Dados.getVendas();
    }


    public static String todasVendas() {
        ArrayList<Object> tempListaVendas = new ArrayList<>();
        tempListaVendas.addAll(getAllVendas());

        try {
            return ObjToStringControl.relatorioObj(tempListaVendas);
        } catch (Exception e) {
            return null;
        }
    }


    public static String filtroProduto(Integer codProd) {   //Traz as todas as vendas que conter tal produto.
        ArrayList<Object> tempListaVendas = new ArrayList<>();

        for (Venda v : getAllVendas()) {
            for (ProdutoVenda pv : v.getCarrinho()) {
                if (codProd.equals(pv.getCodigo())) {
                    tempListaVendas.add(v);
                    break;
                }
            }
        }

        try {
            return ObjToStringControl.relatorioObj(tempListaVendas);
        } catch (Exception e) {
            return null;
        }
    }
}
