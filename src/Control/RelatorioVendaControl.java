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

        for (Venda v : getAllVendas()) {
            for (ProdutoVenda pv : v.getCarrinho()) {
                if (pv.getCodigo() == codProd) {
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
