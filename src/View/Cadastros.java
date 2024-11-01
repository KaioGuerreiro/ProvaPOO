package View;

import javax.swing.*;

public class Cadastros {
    public static int showOpcoes(String titulo, String[] options, Integer defSel) {
        JComboBox<String> comboBox = new JComboBox<>(options);

        if (defSel != null && defSel >= 0 && defSel < options.length) {
            comboBox.setSelectedIndex(defSel);
        }

        int res = JOptionPane.showConfirmDialog(
                null,
                comboBox,
                titulo,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        switch (res) {
            case JOptionPane.OK_OPTION: {
                return comboBox.getSelectedIndex();
            }
            default:
                return -100 - res;
        }
    }
}
