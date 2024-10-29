package Control;

import Model.*;
import Persistence.Dados;

import javax.swing.*;
import java.util.ArrayList;

public class GerenciarVenda {

    private static ProdutoVenda criarPV() {
        Integer idCod = SafeInputControl.sInteger("Realizando Venda", "Digite o ID do produto:");
        if (idCod == null) return null;

        int[] indexProd = GerenciarProduto.encontrar(idCod);
        if (indexProd[0] < 0 || indexProd[1] < 0) return null;

        String nomeProd = Dados.getCategorias().get(indexProd[0]).getProdutos().get(indexProd[1]).getNome();
        String nomeCat = Dados.getCategorias().get(indexProd[0]).getNome();
        Float preco = Dados.getCategorias().get(indexProd[0]).getProdutos().get(indexProd[1]).getPreco();
        Fornecedor forn = Dados.getCategorias().get(indexProd[0]).getProdutos().get(indexProd[1]).getFornecedor();

        Integer qntVend = SafeInputControl.sInteger("Realizando Venda", "Quantos produtos será vendido?");
        if (qntVend == null) return null;


        ProdutoVenda tmpPV = new ProdutoVenda();
        tmpPV.setCodigo(idCod);
        tmpPV.setNome(nomeProd);
        tmpPV.setCategoria(nomeCat);
        tmpPV.setPreco(preco);
        tmpPV.setFornecedor(forn);
        tmpPV.setQntVendida(qntVend);

        return tmpPV;
    }

    private static Venda criar() {
        Venda tmpVenda = new Venda();

        if (Dados.getPessoas().get(Dados.getUserLogged()) instanceof Vendedor vend) {
            tmpVenda.setVendedor(vend);
        } else {
            JOptionPane.showMessageDialog(null, "Você não é um vendedor para realizar uma venda!");
            return null;
        }

        Integer idClient = SafeInputControl.sInteger("Realizando Venda", "ID do cliente:");
        if (idClient == null) return null;

        int clientIndex = GerenciarPessoa.encontrar(idClient);
        if (clientIndex < 0) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return null;
        }

        if (Dados.getPessoas().get(clientIndex) instanceof Cliente cli) {
            tmpVenda.setCliente(cli);
        } else {
            JOptionPane.showMessageDialog(null, "O cliente selecionado não é de fato um cliente!");
            return null;
        }

        ArrayList<ProdutoVenda> tmpCarrinho = new ArrayList<>();
        while (true) {
            ProdutoVenda pv = criarPV();
            if (pv == null) break;

            tmpCarrinho.add(pv);
        }

        if (tmpCarrinho.size() < 1) {
            JOptionPane.showMessageDialog(null, "Carrinho não pode ser vazio!");
            return null;
        }

        tmpVenda.setCarrinho(tmpCarrinho);

        return tmpVenda;
    }

    public static void adicionar() {
        Venda tmpVenda = criar();
        if (tmpVenda == null) {
            JOptionPane.showMessageDialog(null, "Nenhuma venda adicionada!");
            return;
        }

        ArrayList<Venda> vendas = Dados.getVendas();
        vendas.add(tmpVenda);

        Dados.setVendas(vendas);

        JOptionPane.showMessageDialog(null, "Venda adicionada!");
    }
}
