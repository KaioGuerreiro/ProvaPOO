package View;

import javax.swing.*;

public class GerProduto {
    public static int modificar(String[] options) {
        /* O array deve obrigatoriamente ser:
            nome ; qntmin ; preço ; exluido ; fornecedor
         */
        JComboBox<String> comboBox = new JComboBox<>(options);

        if (JOptionPane.showConfirmDialog(
                null,
                comboBox,
                "Modificar:",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        ) == JOptionPane.OK_OPTION) {
            return comboBox.getSelectedIndex();
        }

        return -1;
    }
}
