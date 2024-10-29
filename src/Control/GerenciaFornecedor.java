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

    public static void modificar() {

        String nome = null;

        while (true) {
            try {
                if (nome == null)
                    nome = SafeInputControl.sString("Cadastro de Fornecedor", "Digite o nome do fornecedor à alterar:");
            } catch (Exception e) {
                return;
            }

            int found = encontrar(nome);

            if (found >= 0) {

                String novoNome;
                try {
                    novoNome = SafeInputControl.sString("Cadastro de Fornecedor", "Novo nome:");
                } catch (Exception e) {
                    return;
                }

                if (encontrar(novoNome) >= 0) {
                    JOptionPane.showMessageDialog(null, "Não pode alterar, pois ja existe um fornecedor com esse nome.\nDigite outro.");
                    continue;
                }

                Dados.getFornecedores().get(found).setNome(novoNome);

                JOptionPane.showMessageDialog(null, "Fornecedor alterado!");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "fornecedor não encontrado");
                nome = null;
            }
        }
    }

    public static int excluir() {
        String temp = "";

        for (Fornecedor f : Dados.getFornecedores()) {
            temp += "Nome: " + f.getNome() + " contato: " + f.getContato() + "\n";
        }

        JOptionPane.showMessageDialog(null, temp, "MOSTRANDO FORNECEDORs", JOptionPane.DEFAULT_OPTION);

        return -1;
    }


}
