package Control;

import Model.Fornecedor;
import Persistence.Dados;

import javax.swing.*;
import java.util.ArrayList;

public class GerenciaFornecedor {
    private static int encontrar(String nome) {
        ArrayList<Fornecedor> forn = Dados.getFornecedores();

        for (int i = 0; i < forn.size(); i++) {
            if (forn.get(i).getNome().equals(nome)) return i;
        }

        return -1;
    }

    public static Fornecedor criar() {
        //mock
        String nome = SafeInputControl.sString("Cadstro de Fornecedor", "Nome:");
        if (nome == null) return null;

        String contato = SafeInputControl.sString("Cadstro de Fornecedor", "Contato:");
        if (contato == null) return null;

        Fornecedor tmpForn = new Fornecedor();
        tmpForn.setNome(nome);
        tmpForn.setContato(contato);

        return tmpForn;
    }

    public static void adicionar() {    //Essa função deveria estar em Dados
        Fornecedor tmpF = criar();

        if (tmpF == null) {
            JOptionPane.showMessageDialog(null, "Nenhum fornecedor adicionado");
            return;
        }

        if (encontrar(tmpF.getNome()) == -1) {
            ArrayList<Fornecedor> tmpArr = Dados.getFornecedores();
            tmpArr.add(tmpF);
            Dados.setFornecedores(tmpArr);
            JOptionPane.showMessageDialog(null, "Fornecedor adicionado!");
        } else {
            JOptionPane.showMessageDialog(null, "fornecedor ja existe");
        }
    }

    public static void modificar() {
        String nome = SafeInputControl.sString("Cadastro de Fornecedor", "Digite o nome do fornecedor à alterar:");
        if (nome == null) return;

        int found = encontrar(nome);

        if (found >= 0) {
            String novoNome = SafeInputControl.sString("Cadastro de Fornecedor", "Novo nome:");
            if (nome == null) return;

            if (encontrar(novoNome) >= 0) {
                JOptionPane.showMessageDialog(null, "não pode alterar, pois ja existe um fornecedor com esse nome");
                return;
            }

            ArrayList<Fornecedor> tmpArr = Dados.getFornecedores();
            tmpArr.get(found).setNome(novoNome);

            Dados.setFornecedores(tmpArr);

            JOptionPane.showMessageDialog(null, "Fornecedor alterado!");
        } else {
            JOptionPane.showMessageDialog(null, "fornecedor não encontrado");
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
