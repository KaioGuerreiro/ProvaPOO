package View;

import javax.swing.*;

public class Tela {

    public static int menuPrincipal() {
        Object[] mainBtt = {"Vender", "Cadastros", "Relatorios", "Avisos", "sair"};

        return JOptionPane.showOptionDialog(null, "", "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, mainBtt, mainBtt[0]);
    }

    public static int cadastros() {
        Object[] cadstroBtt = {"Categoria", "Produto", "Fornecedor", "voltar"};

        return JOptionPane.showOptionDialog(null, "", "Cadastros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, cadstroBtt, cadstroBtt[0]);
    }

    public static int cadastrosCategoria() {
        Object[] optCadstroBtt = {"Criar", "Modificar", "Excluir", "voltar"};

        return JOptionPane.showOptionDialog(null, "", "Cadastros > ação",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, optCadstroBtt, optCadstroBtt[0]);
    }

    public static int cadastrosProduto() {
        Object[] optCadstroBtt = {"Criar", "Modificar", "Excluir", "voltar"};

        return JOptionPane.showOptionDialog(null, "", "Cadastros > ação",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, optCadstroBtt, optCadstroBtt[0]);
    }

    public static int cadastrosFornecedor() {
        Object[] optCadstroBtt = {"Criar", "Modificar", "Excluir", "voltar"};

        return JOptionPane.showOptionDialog(null, "", "Cadastros > ação",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, optCadstroBtt, optCadstroBtt[0]);
    }


    public static int relatorios() {
        Object[] relatBtt = {"Vendas", "Estoque", "voltar"};

        return JOptionPane.showOptionDialog(null, "", "Relatorios",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, relatBtt, relatBtt[0]);
    }
}
