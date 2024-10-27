package Model;

import java.util.ArrayList;
import java.util.Date;

public class Venda {
    private Vendedor vendedor;
    private Cliente cliente;
    private ArrayList<ProdutoVenda> carrinho = new ArrayList<>();
    private Date data;

    public Venda(Vendedor vendedor, Cliente cliente, ArrayList<ProdutoVenda> carrinho, Date data) {
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.carrinho = carrinho;
        this.data = data;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<ProdutoVenda> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(ArrayList<ProdutoVenda> carrinho) {
        this.carrinho = carrinho;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
