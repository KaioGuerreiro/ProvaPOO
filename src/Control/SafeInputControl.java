package Control;

import javax.swing.*;

public class SafeInputControl {
    private static String getInput(String titulo, String msg, int tipo) {
        boolean repeat = false;
        String errMsg = "Parametro ";
        switch (tipo) {
            case 1:
                errMsg += "String";
                break;
            case 2:
                errMsg += "Inteiro";
                break;
            case 3:
                errMsg += "Float";
                break;
        }

        while (true) {
            if (repeat && JOptionPane.showConfirmDialog(null, errMsg + " inválido, repetir?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return null;
            } else repeat = true;

            try {
                String str = JOptionPane.showInputDialog(null, msg, titulo, JOptionPane.DEFAULT_OPTION);

                //Verifica se da pra converter, se não, pergunta se quer repetir.
                if (str != null && !str.isBlank()) {
                    switch (tipo) {
                        case 1: {
                            return str;
                        }
                        case 2: {
                            return Integer.valueOf(str).toString();
                        }
                        case 3: {
                            return Float.valueOf(str).toString();
                        }
                    }
                }

            } catch (Exception e) {

            }
        }
    }

    public static String sString(String titulo, String msg) {
        return getInput(titulo, msg, 1);
    }

    public static Integer sInteger(String titulo, String msg) {
        String str = getInput(titulo, msg, 2);
        return str == null ? null : Integer.valueOf(str);
    }

    public static Float sFloat(String titulo, String msg) {
        String str = getInput(titulo, msg, 3);
        return str == null ? null : Float.valueOf(str);
    }
}
