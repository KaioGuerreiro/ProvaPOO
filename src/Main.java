import Control.GerenciarVenda;
import Control.LoginControl;
import Control.RelatorioEstoqueControl;
import Control.RelatorioVendaControl;
import Model.*;
import Persistence.Dados;
import View.Relatorios;
import View.Tela;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    static void setTestDatas() {
        Fornecedor forn = new Fornecedor();
        forn.setContato("contForn");
        forn.setNome("nomeForn");

        ArrayList<Fornecedor> tmpForns = new ArrayList<Fornecedor>();
        tmpForns.add(forn);

        Dados.setFornecedores(tmpForns);

        /*

        this.codigo = codigo;
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMin = quantidadeMin;
        this.preco = preco;
        this.excluido = excluido;
        this.fornecedor = fornecedor;
         */

        Produto tmpProd = new Produto(1, "produto1", 9, 10, 15.99F, false, forn);
        Produto tmpProd2 = new Produto(7, "produto7", 11, 10, 15.99F, false, forn);

        ArrayList<Produto> vecProds = new ArrayList<Produto>();
        vecProds.add(tmpProd);
        vecProds.add(tmpProd2);

        ArrayList<Produto> vecProds2 = new ArrayList<Produto>();
        vecProds2.add(tmpProd2);
        vecProds2.add(tmpProd2);
        vecProds2.add(tmpProd2);
        vecProds2.add(tmpProd);


        Categoria cat = new Categoria();
        cat.setNome("nomeCat");
        cat.setProdutos(vecProds);

        Categoria cat2 = new Categoria();
        cat2.setNome("nomeCat2");
        cat2.setProdutos(vecProds2);

        ArrayList<Categoria> tmpCats = new ArrayList<Categoria>();
        tmpCats.add(cat);
        tmpCats.add(cat2);

        Dados.setCategorias(tmpCats);










        /*
        private int codigo;
        private String nome;
        private float preco;
        private Fornecedor fornecedor;
        private int qntVendida
         */
        ProdutoVenda pV = new ProdutoVenda();
        pV.setCodigo(3);
        pV.setNome("pv1");
        pV.setCategoria("cate1");
        pV.setPreco(25.0F);
        pV.setFornecedor(forn);
        pV.setQntVendida(10);

        ProdutoVenda pV2 = new ProdutoVenda();
        pV2.setCodigo(9);
        pV2.setNome("pv18");
        pV2.setCategoria("cate2");
        pV2.setPreco(30.0F);
        pV2.setFornecedor(forn);
        pV2.setQntVendida(5);

        ArrayList<ProdutoVenda> vecPVs = new ArrayList<ProdutoVenda>();
        vecPVs.add(pV);
        vecPVs.add(pV2);
        vecPVs.add(pV);
        vecPVs.add(pV);

        ArrayList<ProdutoVenda> vecPVs2 = new ArrayList<ProdutoVenda>();
        vecPVs2.add(pV2);
        vecPVs2.add(pV2);


        Vendedor vendedor = new Vendedor();
        vendedor.setId(1);
        vendedor.setNome("ivan");
        vendedor.setContato("996921942");

        Cliente cliente = new Cliente();
        cliente.setDestino("casa do caraleo");
        cliente.setContato("emailcliente");
        cliente.setId(1);
        cliente.setNome("jubiscleiudons");

        /*
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.carrinho = carrinho;
        this.data = LocalDate.now();
         */
        Venda v = new Venda(vendedor, cliente, vecPVs);
        Venda v2 = new Venda(vendedor, cliente, vecPVs2);

        ArrayList<Venda> vendas = new ArrayList<>();
        vendas.add(v);
        vendas.add(v2);

        Dados.setVendas(vendas);

    }

    static void getTestDatas() {

        if (JOptionPane.showInputDialog("ver produtos?").equals("sim")) {

            JOptionPane.showMessageDialog(null, RelatorioEstoqueControl.todosProdutos(),
                    "todos", JOptionPane.DEFAULT_OPTION);

            JOptionPane.showMessageDialog(null, RelatorioEstoqueControl.filtroProduto(1),
                    "produto", JOptionPane.DEFAULT_OPTION);

            JOptionPane.showMessageDialog(null, RelatorioEstoqueControl.filtroCategoria("nomeCat2"),
                    "categoria", JOptionPane.DEFAULT_OPTION);

            JOptionPane.showMessageDialog(null, RelatorioEstoqueControl.filtroQuantidade(8, 10),
                    "quantidade", JOptionPane.DEFAULT_OPTION);


        }

        if (JOptionPane.showInputDialog("ver vendas?").equals("sim")) {

            JOptionPane.showMessageDialog(null, RelatorioVendaControl.todasVendas(),
                    "todas", JOptionPane.DEFAULT_OPTION);

            JOptionPane.showMessageDialog(null, RelatorioVendaControl.filtroProduto(3),
                    "produto", JOptionPane.DEFAULT_OPTION);

            JOptionPane.showMessageDialog(null, RelatorioVendaControl.filtroData(LocalDate.of(2024, 10, 27), LocalDate.of(2024, 10, 27)),
                    "data", JOptionPane.DEFAULT_OPTION);

            JOptionPane.showMessageDialog(null, RelatorioVendaControl.filtroVolume(),
                    "volume??", JOptionPane.DEFAULT_OPTION);


        }
    }

    /*static void testInputs() {

        String tmpstr = SafeInputControl.sString("SafeStrig", "uma string:");
        if (tmpstr != null) JOptionPane.showMessageDialog(null, tmpstr);
        else JOptionPane.showMessageDialog(null, "operação cancelada");

        Integer tmpInt = SafeInputControl.sInteger("SafeStrig", "um inteiro:");
        if (tmpInt != null) JOptionPane.showMessageDialog(null, tmpInt);
        else JOptionPane.showMessageDialog(null, "operação cancelada");


        Float tmpFlo = SafeInputControl.sFloat("SafeStrig", "um float:");
        if (tmpFlo != null) JOptionPane.showMessageDialog(null, tmpFlo);
        else JOptionPane.showMessageDialog(null, "operação cancelada");
    }*/

    private static void addAdmUser() {
        Administrador adm = new Administrador();
        adm.setNome("ADMNISTRADOR");
        adm.setContato("ADM@ADM.COM");
        adm.setCanGerUser(true);
        adm.setId(0);

        Vendedor vend = new Vendedor();
        vend.setNome("IVAN LUIZ");
        vend.setContato("ivan@luiz.COM");
        vend.setId(2);

        Vendedor vend2 = new Vendedor();
        vend2.setNome("Kaio guerreiro");
        vend2.setContato("kaio@guerreiro.COM");
        vend2.setId(3);

        Cliente cli = new Cliente();
        cli.setNome("matheus");
        cli.setContato("matheus@iori.COM");
        cli.setId(1);
        cli.setDestino("hauseOfDragon");

        ArrayList<Pessoa> pps = Dados.getPessoas();
        pps.add(adm);
        pps.add(vend);
        pps.add(vend2);
        pps.add(cli);

        Dados.setPessoas(pps);
    }


    public static void main(String[] args) {
        //setTestDatas();
        //getTestDAtas();
        //testInputs();

        addAdmUser();
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
                            //control
                            break;
                        }
                        case 1: {    //adicionar pessoa.
                            Tela.cadastrosPessoas();
                            break;
                        }
                        case 2: {    //voltar menu principal

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