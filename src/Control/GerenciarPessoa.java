package Control;

import Model.Administrador;
import Model.Cliente;
import Model.Pessoa;
import Model.Vendedor;
import Persistence.Dados;
import View.Tela;

import javax.swing.*;
import java.util.ArrayList;

public class GerenciarPessoa {
    public static Integer encontrar(Integer id) {

        ArrayList<Pessoa> ppl = Dados.getPessoas();
        for (int i = 0; i < ppl.size(); i++) {
            if (ppl.get(i).getId().equals(id)) return i;
        }

        return -1;
    }

    private static Pessoa criar() {

        int tipo = Tela.cadastrosTipoPessoas();

        if (tipo < 0 || tipo > 2) return null;   //Cancelou

        Integer id = SafeInputControl.sInteger("Cadastro de Pessoas", "Digite o ID:");
        if (id == null) return null;

        String nome = SafeInputControl.sString("Cadastro de Pessoas", "Nome do Usuario:");
        if (nome == null) return null;

        String contato = SafeInputControl.sString("Cadastro de Pessoas", "Contato do Usuario:");
        if (contato == null) return null;

        switch (tipo) {
            case 0: {   //Cliente
                String dest = SafeInputControl.sString("Cadastro de Pessoas", "Destino do cliente");
                if (dest == null) return null;

                Cliente cli = new Cliente();
                cli.setNome(nome);
                cli.setContato(contato);
                cli.setId(id);
                cli.setDestino(dest);

                return cli;
            }
            case 1: {   //Vendedor
                Vendedor vend = new Vendedor();
                vend.setNome(nome);
                vend.setContato(contato);
                vend.setId(id);

                return vend;
            }
            case 2: {   //Adm
                Administrador adm = new Administrador();
                adm.setNome(nome);
                adm.setContato(contato);
                adm.setId(id);

                return adm;
            }
            default: {
                JOptionPane.showMessageDialog(null, "Tipo não definido! Esse erro não deveria aparecer pro usuario!");
                return null;
            }
        }
    }

    public static void adicionar() {
        Pessoa p = criar();
        if (p == null) return;

        if (encontrar(p.getId()) == -1) {
            ArrayList<Pessoa> tmpPpl = Dados.getPessoas();
            tmpPpl.add(p);

            Dados.setPessoas(tmpPpl);

            JOptionPane.showMessageDialog(null, "Pessoa adicionada!");
        } else {
            JOptionPane.showMessageDialog(null, "Já existe um usuario com esse nome");
        }
    }

    public static void modificar() {
        Integer id = SafeInputControl.sInteger("Modificando Pessoa", "Id da pessoa à ser modificada:");
        if (id == null) return;

        int indexP = encontrar(id);
        if (indexP < 0) {
            JOptionPane.showMessageDialog(null, "Pessoa não encontrada");
            return;
        }

        ArrayList<Pessoa> pplArr = Dados.getPessoas();
        Pessoa p = pplArr.get(indexP); //Pessoa que o usuario selecionou;

        String nvNome = SafeInputControl.sString("Modificando Pessoa", "Novo nome (vazio cancela): ");
        if (nvNome == null) nvNome = p.getNome();

        p.setNome(nvNome);

        if (p instanceof Cliente tmp) {
            String nvDest = SafeInputControl.sString("Modificando cliente", "Novo destino: ");
            if (nvDest == null) return;

            tmp.setDestino(nvDest);

            pplArr.set(indexP, tmp);
        } else if (p instanceof Administrador adm) {
            String nvCfg = SafeInputControl.sString("Modificando Administrador", "Ele pode gerenciar usuarios?: ");
            if (nvCfg == null) return;

            adm.setCanGerUser(nvCfg.equals("sim"));

            pplArr.set(indexP, adm);
        }

        Dados.setPessoas(pplArr);
        JOptionPane.showMessageDialog(null, "Pessoa modificada!");
    }

    public static void excluir() {
        String str = "";
        for (Pessoa p : Dados.getPessoas()) {
            str += "Nome: " + p.getNome() + " ID: " + p.getId() + " Cont: " + p.getContato();

            if (p instanceof Cliente cli) str += " Dest: " + cli.getDestino();
            else if (p instanceof Vendedor vend) str += " Vendas: " + vend.getVendasRealizadas();
            else if (p instanceof Administrador adm) str += " CanGer: " + adm.isCanGerUser();

            str += "\n\n";
        }

        JOptionPane.showMessageDialog(null, str);

    }


}
