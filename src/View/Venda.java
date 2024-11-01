package View;

import javax.swing.*;

public class Venda {
    public static int carrinho(String carrinho) {

        Object[] btts = {"Adicionar Produtos", "Vender", "Cancelar"};

        return JOptionPane.showOptionDialog(null, carrinho, "Realizando Venda",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, btts, btts[0]);
    }
}
