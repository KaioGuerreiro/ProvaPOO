import Control.GerenciarVenda;
import Control.LoginControl;
import Control.RelatorioEstoqueControl;
import Control.RelatorioVendaControl;
import Model.*;
import Persistence.Dados;
import View.Relatorios;
import View.Tela;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    private static void addPeople() {
        Administrador adm = new Administrador();
        adm.setNome("ADMNISTRADOR");
        adm.setContato("ADM@ADM.COM");
        adm.setCanGerUser(true);
        adm.setId(0);

        Vendedor vend = new Vendedor();
        vend.setNome("Ivan Luiz");
        vend.setContato("ivan@luiz.COM");
        vend.setId(1);

        Vendedor vend2 = new Vendedor();
        vend2.setNome("Kaio Guerreiro");
        vend2.setContato("kaio@guerreiro.COM");
        vend2.setId(2);

        Cliente cli = new Cliente();
        cli.setNome("André Martins");
        cli.setContato("andré@martins.COM");
        cli.setId(3);
        cli.setDestino("Casa do André");

        ArrayList<Pessoa> pps = Dados.getPessoas();
        pps.add(adm);
        pps.add(vend);
        pps.add(vend2);
        pps.add(cli);

        Dados.setPessoas(pps);
    }

    private static void addEstoque() {
        Fornecedor forn = new Fornecedor();
        forn.setNome("forn");
        forn.setContato("fornecedor@contato.com");

        Produto prod = new Produto(1, "Pista HotWheels Tubarão", 5, 10, 3584.98F, false, forn);
        ArrayList<Produto> arrProd = new ArrayList<>();
        arrProd.add(prod);

        Categoria cat = new Categoria();
        cat.setNome("Brinquedos");
        cat.setProdutos(arrProd);

        Dados.getFornecedores().add(forn);
        Dados.getCategorias().add(cat);

    }


    public static void main(String[] args) {
        addPeople();
        addEstoque();

        Dados.setUserLogged(-1);

        boolean running = true;
        while (running) {

            if (Dados.getUserLogged() < 0) {
                if (Tela.loginMenu() != 0) {
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    return;
                } else if (LoginControl.loginCod() == null) {
                    JOptionPane.showMessageDialog(null, "Login não permitido.");
                    continue;
                }
            }

            switch (Tela.menuPrincipal()) {
                case 0: {   //Realizar vendas
                    GerenciarVenda.adicionar();
                    break;
                }
                case 1: {   //Consultar cadastros
                    switch (Tela.cadastros()) {
                        case 0: {    //Categoria
                            Tela.cadastrosCategoria();
                            break;
                        }
                        case 1: {    //Produto
                            Tela.cadastrosProduto();
                            break;
                        }
                        case 2: {    //Fornecedor
                            Tela.cadastrosFornecedor();
                            break;
                        }
                        case 3: {    //voltar menu principal

                            break;
                        }
                    }
                    break;
                }
                case 2: {   //Relatorios
                    switch (Relatorios.tipos()) {
                        case 0: {   //Venda
                            RelatorioVendaControl.gen();
                            break;
                        }
                        case 1: {   //Estoque
                            RelatorioEstoqueControl.gen();
                            break;
                        }
                        case 2: {   //voltar menu principal

                            break;
                        }
                    }
                    break;
                }
                case 3: {   //Admnistrativos
                    switch (Tela.administrativo()) {
                        case 0: {    //ver avisos
                            Tela.avisos();
                            break;
                        }
                        case 1: {    //adicionar pessoa.
                            Tela.cadastrosPessoas();
                            break;
                        }
                        case 2: {    //vo1ltar menu principal

                            break;
                        }
                    }
                    break;
                }
                case 4: {   //sair
                    Dados.setUserLogged(-1);
                    break;
                }
            }
        }
    }
}