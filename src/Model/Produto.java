package Model;

public class Produto {
    private int codigo;
    private String nome;
    private int quantidadeEstoque;
    private int quantidadeMin;
    private float preco;
    private boolean excluido;
    private Fornecedor fornecedor = new Fornecedor();

}
