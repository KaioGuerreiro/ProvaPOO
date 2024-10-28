package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venda {
    private Vendedor vendedor;
    private Cliente cliente;
    private ArrayList<ProdutoVenda> carrinho = new ArrayList<>();
    private LocalDate data;

    public Venda() {
    }

    public Venda(Vendedor vendedor, Cliente cliente, ArrayList<ProdutoVenda> carrinho) {
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.carrinho = carrinho;
        this.data = LocalDate.now();
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

    public LocalDate getData() {
        return data;
    }

    public void setData() {
        this.data = LocalDate.now();
    }
}
