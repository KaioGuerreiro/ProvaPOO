package Model;

import java.util.ArrayList;

public class Venda {
    private Vendedor vendedor;
    private Cliente cliente;
    private ArrayList<ProdutoVenda> carrinho = new ArrayList<>();
    private String data;
}
