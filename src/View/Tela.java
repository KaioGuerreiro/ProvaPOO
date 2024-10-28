package View;

import Control.GerenciaFornecedor;

import javax.swing.*;

public class Tela {

    public static int menuPrincipal() {
        Object[] mainBtt = {"Vender", "Cadastros", "Relatorios", "Administrativo", "sair"};

        return JOptionPane.showOptionDialog(null, "", "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, mainBtt, mainBtt[0]);
    }

    public static int cadastros() {
        Object[] cadstroBtt = {"Categoria", "Produto", "Fornecedor", "voltar"};

        return JOptionPane.showOptionDialog(null, "", "Cadastros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, cadstroBtt, cadstroBtt[0]);
    }

    private static int optCadastrosBTT() {
        Object[] optCadstroBtt = {"Criar", "Modificar", "Excluir", "voltar"};

        return JOptionPane.showOptionDialog(null, "", "Cadastros > ação",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, optCadstroBtt, optCadstroBtt[0]);

    }

    public static int cadastrosCategoria() {
        switch (optCadastrosBTT()) {
            case 0: {
                JOptionPane.showMessageDialog(null, "criou categoria");
                //control
                break;
            }
            case 1: {

                break;
            }
            case 2: {

                break;
            }
            case 3: {

                break;
            }
        }

        return 0;
    }

    public static int cadastrosProduto() {
        switch (optCadastrosBTT()) {
            case 0: {
                JOptionPane.showMessageDialog(null, "criou produto");
                //control
                break;
            }
            case 1: {

                break;
            }
            case 2: {

                break;
            }
            case 3: {

                break;
            }
        }

        return 0;
    }

    public static int cadastrosFornecedor() {
        switch (optCadastrosBTT()) {
            case 0: {
                GerenciaFornecedor.adicionar();
                break;
            }
            case 1: {
                GerenciaFornecedor.modificar();
                break;
            }
            case 2: {
                GerenciaFornecedor.excluir();
                break;
            }
            case 3: {

                break;
            }
        }

        return 0;
    }


    public static int relatorios() {
        Object[] relatBtt = {"Vendas", "Estoque", "voltar"};

        return JOptionPane.showOptionDialog(null, "", "Relatorios",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, relatBtt, relatBtt[0]);
    }


    public static int relatoriosEstoques() {
        Object[] tiposRelat = {"Todos", "Por Categoria", "Por Produto", "Por Quantidade"};

        return JOptionPane.showOptionDialog(null, "", "Relatorios",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, tiposRelat, tiposRelat[0]);
    }

    public static int administrativo() {
        Object[] admBtts = {"Avisos", "Cadastrar Usuario", "voltar"};

        return JOptionPane.showOptionDialog(null, "", "Administração",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, admBtts, admBtts[0]);
    }
}
