package Control;

import Model.Categoria;
import Persistence.Dados;

import javax.swing.*;
import java.util.ArrayList;


public class GerenciarCategoria {
    public static int encontrar(String nome) {
        ArrayList<Categoria> cats = Dados.getCategorias();

        for (int i = 0; i < cats.size(); i++) {
            if (cats.get(i).getNome().equals(nome)) return i;
        }

        return -1;
    }

    private static Categoria criar() {
        String nome = SafeInputControl.sString("Cadastro de Categorias", "Nome da categoria:");
        if (nome == null) return null;

        Categoria tmpCat = new Categoria();
        tmpCat.setNome(nome);

        return tmpCat;
    }

    public static void adicionar() {
        Categoria tmpCat = criar();
        if (tmpCat == null) {
            JOptionPane.showMessageDialog(null, "Nenhuma categoria adicionada");
            return;
        }

        if (encontrar(tmpCat.getNome()) >= 0) {
            JOptionPane.showMessageDialog(null, "Essa categoria já existe!");
        } else {
            ArrayList<Categoria> tmpArr = Dados.getCategorias();
            tmpArr.add(tmpCat);

            Dados.setCategorias(tmpArr);
            JOptionPane.showMessageDialog(null, "Categoria adicionada!");
        }
    }

    public static void modificar() {
        String nome = SafeInputControl.sString("Cadastro de Categorias", "Nome da categoria à modificar");
        if (nome == null) return;

        int found = encontrar(nome);
        if (found >= 0) {
            String nvNome = SafeInputControl.sString("Cadastro de Categorias", "Novo nome dessa categoria:");
            if (nvNome == null) return;

            if (encontrar(nvNome) >= 0) {
                JOptionPane.showMessageDialog(null, "Não pode alerar, pois já existe uma categoria com esse nome");
                return;
            }

            Dados.getCategorias().get(found).setNome(nvNome);
            JOptionPane.showMessageDialog(null, "Categoria alterada!");
        } else {
            JOptionPane.showMessageDialog(null, "Categoria não encotrada!");
        }
    }

    public static void excluir() {
        String cats = "";

        for (Categoria c : Dados.getCategorias()) {
            cats += "nome: " + c.getNome() + " qntsProdutos: " + c.getProdutos().size() + "\n";
        }

        JOptionPane.showMessageDialog(null, cats);

    }
}
