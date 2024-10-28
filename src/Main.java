import Control.RelatorioEstoqueControl;
import Control.RelatorioVendaControl;
import Control.SafeInputControl;
import Model.*;
import Persistence.Dados;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    static void setAll() {
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


    public static void main(String[] args) {
        //setAll();
        int tmp = 0;

        Object[] mainBtt = {"Vender", "Cadastros", "Relatorios", "Avisos", "sair"};

        Object[] cadstroBtt = {"Categoria", "Produto", "Fornecedor", "voltar"};
        Object[] optCadstroBtt = {"Criar", "Modificar", "Excluir", "voltar"};

        Object[] relatBtt = {"Vendas", "Estoque", "voltar"};


        boolean running = true;
        while (running) {
            Integer tmpInt = SafeInputControl.sInteger("SafeStrig", "uma inteiro:");
            if (tmpInt != null) JOptionPane.showMessageDialog(null, tmpInt);
            else JOptionPane.showMessageDialog(null, "operação cancelada");


            Float tmpFlo = SafeInputControl.sFloat("SafeStrig", "uma float:");
            if (tmpFlo != null) JOptionPane.showMessageDialog(null, tmpFlo);
            else JOptionPane.showMessageDialog(null, "operação cancelada");

            if (tmp < 100) continue;

            mainBtt[3] = tmp > 0 ? "Avisos(" + tmp + ")" : "Avisos";
            tmp++;
            /*
            String str = safeString("String", "Digite uma string");
            if ("sair".equals(str)) running = false;

            Float floatt = safeFloat("Float", "Digite um numero");
            if (Float.valueOf(999).equals(floatt)) running = false;

            Integer inteiro = safeInteger("Interio", "Digite um numero");
            if (Integer.valueOf(999).equals(inteiro)) running = false;
             */
            JOptionPane.showOptionDialog(null, "", "Menu Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, mainBtt, mainBtt[0]);

            JOptionPane.showOptionDialog(null, "", "Cadastros",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, cadstroBtt, cadstroBtt[0]);

            JOptionPane.showOptionDialog(null, "", "Cadastros > ação",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, optCadstroBtt, optCadstroBtt[0]);

            JOptionPane.showOptionDialog(null, "", "Relatorios",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, relatBtt, relatBtt[0]);


        }
    }
}