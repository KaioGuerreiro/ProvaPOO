package Control;

import Model.ProdutoVenda;
import Model.Venda;
import Persistence.Dados;

import java.util.ArrayList;

public class RelatorioVendaControl {
    private static ArrayList<Venda> getAllVendas() {
        return Dados.getVendas();
    }

    public static String filtroProduto(Integer codProd) {
        ArrayList<Object> tempListaVendas = new ArrayList<>();
        ArrayList<Venda> listaFiltrada = new ArrayList<Venda>();

        for (Venda v : getAllVendas()) {
            for (ProdutoVenda pv : v.getCarrinho()) {
                if (pv.getCodigo() == codProd) {
                    listaFiltrada.add(v);
                    break;
                }
            }
        }

        for (Venda v : listaFiltrada) {
            tempListaVendas.add((Object) v);
        }

        return ObjToStringControl.relatorioObj(tempListaVendas);
    }
}
