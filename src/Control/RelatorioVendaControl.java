package Control;

import Model.ProdutoVenda;
import Model.Venda;
import Persistence.Dados;
import View.Relatorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

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


    public static ArrayList<Venda> orqQntVendida(ArrayList<Venda> vendas) {
        ArrayList<Venda> copiaVenda = new ArrayList<>(vendas);

        copiaVenda.sort(Comparator.comparingInt(Venda::getQntProdutos).reversed());

        return copiaVenda;
    }

    public static String filtroVolume() {    //Ordenação por produtos mais vendidos
        ArrayList<Object> tempListaVendas = new ArrayList<>();

        for (Venda v : orqQntVendida(getAllVendas())) {
            tempListaVendas.add(v);
        }

        try {
            return ObjToStringControl.relatorioObj(tempListaVendas);
        } catch (Exception e) {
            return null;
        }
    }

    public static void gen() {
        String resultado = null;
        switch (Relatorios.tipoVendas()) {
            case 0: {    //todos
                //JOptionPane.showMessageDialog(null, todasVendas(),
                //        "Mostrando todas as vendas", JOptionPane.DEFAULT_OPTION);
                resultado = todasVendas();
                break;
            }
            case 1: {    //produto
                Integer prod;

                try {
                    prod = SafeInputControl.sInteger("Filtrar por Produto", "Codigo do produto:");
                } catch (Exception e) {
                    break;
                }

                //JOptionPane.showMessageDialog(null, filtroProduto(prod),
                //        "Mostando as vendas que tem o produto " + prod, JOptionPane.DEFAULT_OPTION);
                resultado = filtroProduto(prod);
                break;
            }
            case 2: {    //categoria
                String nomeCat;
                try {
                    nomeCat = SafeInputControl.sString("Filtrar por categoria", "Nome da categoria:");
                } catch (Exception e) {
                    break;
                }

                //JOptionPane.showMessageDialog(null, filtroCategoria(cat),
                //        "Mostando as vendas que tem a categoria " + cat, JOptionPane.DEFAULT_OPTION);
                resultado = filtroCategoria(nomeCat);
                break;
            }
            case 3: {    //data

                String dataTexto = "2024-10-29";
                LocalDate lcMin;
                LocalDate lcMax;


                try {
                    dataTexto = SafeInputControl.sString("Filtrar por Data", "Digite no formato (AAAA-MM-DD)\n\nData inicial:");
                    lcMin = LocalDate.parse(dataTexto);
                } catch (Exception e) {
                    break;
                }


                try {
                    dataTexto = SafeInputControl.sString("Filtrar por Data", "Digite no formato (AAAA-MM-DD)\n\nData Final:");
                    lcMax = LocalDate.parse(dataTexto);
                } catch (Exception e) {
                    break;
                }


                //JOptionPane.showMessageDialog(null, filtroData(lcMin, lcMax),
                //        "Mostando as vendas no intervalo de data", JOptionPane.DEFAULT_OPTION);
                resultado = filtroData(lcMin, lcMax);
                break;
            }
            case 4: {    //volume
                //JOptionPane.showMessageDialog(null, filtroVolume(),
                //        "volume??", JOptionPane.DEFAULT_OPTION);
                resultado = filtroVolume();
                break;
            }
        }

        Relatorios.imprimir(null, resultado);

    }
}
