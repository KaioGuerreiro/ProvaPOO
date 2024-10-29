package Control;

import Model.Alerta;
import Persistence.Dados;

import javax.swing.*;
import java.util.ArrayList;

public class GerenciarAlertas {
    public static void adicionar(String dado, String descr) {
        Alerta a = new Alerta(descr, dado);

        Dados.getAlertas().add(a);
    }

    public static ArrayList<Alerta> activeAlert() {
        ArrayList<Alerta> tmp = new ArrayList<>();

        for (Alerta a : Dados.getAlertas()) {
            if (!a.isResolvido()) tmp.add(a);
        }

        return tmp;
    }

    public static void resolver() {
        Integer indxIni = null;
        Integer indxFin = null;

        while (true) {
            try {
                if (indxIni == null) {
                    indxIni = SafeInputControl.sInteger("Remover alertar", "Indice inicial: ");

                    if (indxIni < 0) {
                        JOptionPane.showMessageDialog(null, "Inválido!");
                        indxIni = null;
                        continue;
                    }
                }

            } catch (Exception e) {
                break;
            }


            try {
                if (indxFin == null) {
                    indxFin = SafeInputControl.sInteger("Remover alertar", "Indice Final: ");

                    if (indxFin < indxIni) {
                        JOptionPane.showMessageDialog(null, "Inválido!");
                        indxFin = null;
                        continue;
                    }
                }
            } catch (Exception e) {
                break;
            }

            int actIndx = 0;
            for (Alerta a : activeAlert()) {
                if (actIndx >= indxIni && actIndx <= indxFin) {
                    a.setResolvido(true);
                }
                actIndx++;
            }
            return;
        }
    }

    public static void listagem() {
        String str = "";
        for (Alerta a : Dados.getAlertas()) {
            str += "Dado: " + a.getDados() + "\n|____" + a.getDescricao() + "\n";
        }
        JOptionPane.showMessageDialog(null, str);
    }


    public static void verActives() {
        String str = "";
        int actIndex = 0;
        for (Alerta a : activeAlert()) {
            str += "Index: " + actIndex + "\n|____Dado: " + a.getDados() + "\n    |____" + a.getDescricao() + "\n";
            actIndex++;
        }
        JOptionPane.showMessageDialog(null, str);
    }
}
