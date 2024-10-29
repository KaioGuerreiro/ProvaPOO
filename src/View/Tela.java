package View;

import Control.GerenciaFornecedor;
import Control.GerenciarCategoria;
import Control.GerenciarPessoa;
import Control.GerenciarProduto;
import Persistence.Dados;

import javax.swing.*;

public class Tela {
    public static int loginMenu() {
        Object[] loginBtt = {"Login", "Finalizar"};
        return JOptionPane.showOptionDialog(null, "", "Bem Vindo!!!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, loginBtt, loginBtt[0]);
    }


    public static int menuPrincipal() {
        Object[] mainBtt = {"Vender", "Cadastros", "Relatorios", "Administrativo", "sair"};

        return JOptionPane.showOptionDialog(null, "Bem vindo, " + Dados.getPessoas().get(Dados.getUserLogged()).getNome(), "Menu Principal",
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
                GerenciarCategoria.adicionar();
                break;
            }
            case 1: {
                GerenciarCategoria.modificar();
                break;
            }
            case 2: {
                GerenciarCategoria.excluir();
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
                GerenciarProduto.adicionar();
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

    public static int relatoriosVendas() {
        Object[] tiposRelat = {"Todos", "Por Produto", "Por Categoria", "Por Data", "Por Volume"};

        return JOptionPane.showOptionDialog(null, "", "Tipos de relatorios de venda",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, tiposRelat, tiposRelat[0]);
    }

    public static int administrativo() {
        Object[] admBtts = {"Avisos", "Cadastrar Usuario", "voltar"};

        return JOptionPane.showOptionDialog(null, "", "Administração",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, admBtts, admBtts[0]);
    }

    public static int avisos() {
        switch (optCadastrosBTT()) {
            case 0: {
                GerenciarPessoa.adicionar();
                break;
            }
            case 1: {
                GerenciarPessoa.modificar();
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

    public static int cadastrosPessoas() {
        switch (optCadastrosBTT()) {
            case 0: {
                GerenciarPessoa.adicionar();
                break;
            }
            case 1: {
                GerenciarPessoa.modificar();
                break;
            }
            case 2: {
                GerenciarPessoa.excluir();
                break;
            }
            case 3: {

                break;
            }
        }

        return 0;
    }

    public static int cadastrosTipoPessoas() {
        Object[] relatBtt = {"Cliente", "Vendedor", "Administrador"};

        return JOptionPane.showOptionDialog(null, "", "Tipos de Pessoa",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, relatBtt, relatBtt[0]);
    }
}
