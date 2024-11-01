package Control;

import Model.Fornecedor;
import Persistence.Dados;

import javax.swing.*;
import java.util.ArrayList;

public class GerenciaFornecedor {
    public static int encontrar(String nome) {
        ArrayList<Fornecedor> forn = Dados.getFornecedores();

        for (int i = 0; i < forn.size(); i++) {
            if (forn.get(i).getNome().equals(nome)) return i;
        }

        return -1;
    }

    public static Fornecedor criar() {

        String nome = null;

        while (true) {
            try {
                if (nome == null) nome = SafeInputControl.sString("Cadstro de Fornecedor", "Nome:");
            } catch (Exception e) {
                return null;
            }

            if (encontrar(nome) != -1) {
                JOptionPane.showMessageDialog(null, "fornecedor ja existe! digite outro");
                nome = null;
                continue;
            }

            String contato;
            try {
                contato = SafeInputControl.sString("Cadstro de Fornecedor", "Contato:");
            } catch (Exception e) {
                return null;
            }

            Fornecedor tmpForn = new Fornecedor();
            tmpForn.setNome(nome);
            tmpForn.setContato(contato);

            return tmpForn;
        }
    }


    public static void adicionar() {    //Essa função deveria estar em Dados
        Fornecedor tmpF = criar();

        if (tmpF == null) {
            JOptionPane.showMessageDialog(null, "Nenhum fornecedor adicionado");
            return;
        }

        Dados.getFornecedores().add(tmpF);
        JOptionPane.showMessageDialog(null, "Fornecedor adicionado!");
    }

    public static Fornecedor selectFornecedor() {
        ArrayList<Fornecedor> todos = Dados.getFornecedores();
        if (todos == null || todos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum fornecedor cadastrado!");
            return null;
        }

        Fornecedor select = (Fornecedor) JOptionPane.showInputDialog(
                null,
                "Selecione o fornecedor:",
                "Fornecedor Encontrados",
                JOptionPane.QUESTION_MESSAGE,
                null,
                todos.toArray(),
                todos.get(0)
        );

        return select;
    }

    public static void modificar() {

        Fornecedor fornMod = selectFornecedor();
        if (fornMod == null) return;

        Integer atribModify = null;
        while (true) {

            String[] cBoxArr = {"Nome: " + fornMod.getNome(), "Contato: " + fornMod.getContato()};
            /* O array deve obrigatoriamente ser:
                nome ; contato
             */

            atribModify = View.Cadastros.showOpcoes("Modificar", cBoxArr, atribModify);
            switch (atribModify) {
                case 0: {
                    try {
                        String novoNome = SafeInputControl.sString("Cadastro de Fornecedor", "Novo nome:");

                        if (encontrar(novoNome) >= 0) {
                            JOptionPane.showMessageDialog(null, "Não pode alterar, pois ja existe um fornecedor com esse nome.\nDigite outro.");
                            break;
                        }

                        fornMod.setNome(novoNome);

                    } catch (Exception e) {
                        return;
                    }
                    break;
                }
                case 1: {
                    try {
                        String novoContato = SafeInputControl.sString("Cadastro de Fornecedor", "Novo contato:");

                        fornMod.setContato(novoContato);

                    } catch (Exception e) {
                        return;
                    }
                    break;
                }
                default:
                    return;
            }
        }
    }


    public static int listagem() {
        String temp = "";

        for (Fornecedor f : Dados.getFornecedores()) {
            temp += "Nome: " + f.getNome() + " contato: " + f.getContato() + "\n";
        }

        JOptionPane.showMessageDialog(null, temp, "MOSTRANDO FORNECEDORs", JOptionPane.DEFAULT_OPTION);

        return -1;
    }


}
