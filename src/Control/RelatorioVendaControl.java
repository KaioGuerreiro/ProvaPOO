package Control;

import Model.ProdutoVenda;
import Model.Venda;
import Persistence.Dados;

import javax.swing.*;
import java.time.LocalDate;
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


    public static String filtroCategoria(String nomeCategoria) {    //Traz as todas as vendas que conter tal categoria.
        ArrayList<Object> tempListaVendas = new ArrayList<>();


        for (Venda v : getAllVendas()) {
            for (ProdutoVenda pv : v.getCarrinho()) {
                if (nomeCategoria.equals(pv.getCategoria())) {
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


    public static String filtroData(LocalDate in, LocalDate fi) {    //Traz as todas as vendas que conter tal categoria.
        ArrayList<Object> tempListaVendas = new ArrayList<>();


        for (Venda v : getAllVendas()) {
            if ((v.getData().isAfter(in) || v.getData().isEqual(in)) && (v.getData().isBefore(fi) || v.getData().isEqual(fi))) {
                tempListaVendas.add(v);
                break;
            }
        }

        try {
            return ObjToStringControl.relatorioObj(tempListaVendas);
        } catch (Exception e) {
            return null;
        }
    }

    public static String filtroVolume() {    //requisito não compreendido
        ArrayList<Object> tempListaVendas = new ArrayList<>();

        JOptionPane.showMessageDialog(null, "filtro ainda nao implementado!");

        if (tempListaVendas.isEmpty()) {
            return null;
        }


        for (Venda v : getAllVendas()) {
            for (ProdutoVenda pv : v.getCarrinho()) {
                if (false) {
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
