package View;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class Relatorios {

    public static int tipos() {
        Object[] relatBtt = {"Vendas", "Estoque", "voltar"};

        return JOptionPane.showOptionDialog(null, "", "Relatorios",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, relatBtt, relatBtt[0]);
    }


    public static int tipoEstoque() {
        Object[] tiposRelat = {"Todos", "Por Categoria", "Por Produto", "Por Quantidade"};

        return JOptionPane.showOptionDialog(null, "", "Relatorios",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, tiposRelat, tiposRelat[0]);
    }

    public static int tipoVendas() {
        Object[] tiposRelat = {"Todos", "Por Produto", "Por Categoria", "Por Data", "Por Volume"};

        return JOptionPane.showOptionDialog(null, "", "Tipos de relatorios de venda",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, tiposRelat, tiposRelat[0]);
    }


    private static int formatoRelatorio() {
        Object[] formatos = {"sistema", "csv", "voltar"};
        return JOptionPane.showOptionDialog(null, "", "Onde você deseja ver o relatorio?",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, formatos, formatos[0]);
    }


    private static void formatoSistema(String str) {
        JOptionPane.showMessageDialog(null, str);
    }

    private static void formatoCsv(String str) {
        try (FileWriter writer = new FileWriter("RELATORIO_zzz.csv")) {
            writer.write(str);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao gravar CSV", JOptionPane.DEFAULT_OPTION);
        }
    }


    public static void imprimir(String cabecalho, String resultado) {
        if (resultado == null) {
            JOptionPane.showMessageDialog(null, "Sem dados para imprimir");
            return;
        }

        if (cabecalho != null) resultado = cabecalho + "\n" + resultado;

        switch (formatoRelatorio()) {
            case 0: {   //sistema
                formatoSistema(resultado);
                break;
            }
            case 1: {   //csv
                formatoCsv((resultado));
                break;
            }
        }
    }
}
