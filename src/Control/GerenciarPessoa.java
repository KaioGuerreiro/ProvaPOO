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

        if (tipo < 0 || tipo > 2) return null;   //Usuario apertou em cancelar

        Integer id = null;

        while (true) {
            try {
                if (id == null) id = SafeInputControl.sInteger("Cadastro de Pessoas", "Digite o ID:");
            } catch (Exception e) {
                return null;
            }

            if (encontrar(id) != -1) {
                JOptionPane.showMessageDialog(null, "Já existe um usuario com esse ID! digite outro.");
                id = null;
                continue;
            }

            String nome;
            try {
                nome = SafeInputControl.sString("Cadastro de Pessoas", "Nome do Usuario:");
            } catch (Exception e) {
                return null;
            }

            String contato;
            try {
                contato = SafeInputControl.sString("Cadastro de Pessoas", "Contato do Usuario:");
            } catch (Exception e) {
                return null;
            }

            switch (tipo) {
                case 0: {   //Cliente
                    String dest;
                    try {
                        dest = SafeInputControl.sString("Cadastro de Pessoas", "Destino do cliente");
                    } catch (Exception e) {
                        return null;
                    }

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
    }

    public static void adicionar() {
        Pessoa p = criar();
        if (p == null) return;

        Dados.getPessoas().add(p);
        JOptionPane.showMessageDialog(null, "Pessoa adicionada!");
    }

    public static void modificar() {
        Integer id = null;

        while (true) {
            try {
                if (id == null) id = SafeInputControl.sInteger("Modificando Pessoa", "Id da pessoa à ser modificada:");
            } catch (Exception e) {
                break;
            }

            int indexP = encontrar(id);
            if (indexP < 0) {
                JOptionPane.showMessageDialog(null, "Pessoa não encontrada! digite outra.");
                id = null;
                continue;
            }

            Pessoa pplMod = Dados.getPessoas().get(indexP); //Pessoa que o usuario selecionou (é uma referência da memoria);

            String nvNome;
            try {
                nvNome = SafeInputControl.sString("Modificando Pessoa", "Novo nome (vazio cancela): ");
            } catch (Exception e) {
                nvNome = pplMod.getNome();
            }

            if (pplMod instanceof Cliente cli) {

                String nvDest;
                try {
                    nvDest = SafeInputControl.sString("Modificando cliente", "Novo destino: ");
                } catch (Exception e) {
                    break;
                }

                cli.setDestino(nvDest);
            } else if (pplMod instanceof Vendedor vend) {

            } else if (pplMod instanceof Administrador adm) {

                String nvCfg;
                try {
                    nvCfg = SafeInputControl.sString("Modificando Administrador", "Ele pode gerenciar usuarios?: ");
                } catch (Exception e) {
                    break;
                }

                adm.setCanGerUser(nvCfg.equals("sim"));
            } else {
                break;
            }

            pplMod.setNome(nvNome);
            JOptionPane.showMessageDialog(null, "Pessoa modificada!");
            return;
        }

        JOptionPane.showMessageDialog(null, "Mofificação cancelada");
    }

    public static void listagem() {
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
