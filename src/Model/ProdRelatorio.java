package Model;

public class ProdRelatorio {
    public String nomeCategoria;
    public Integer prodCod;
    public Integer qntEstoque;
    public Integer qntMinEstoque;
    public float preco;
    public String nomeFornecedor;
    public String contatoFornecedor;

    public ProdRelatorio() {
    }

    public ProdRelatorio(String nomeCategoria, Integer prodCod, Integer qntEstoque, Integer qntMinEstoque, float preco, String nomeFornecedor, String contatoFornecedor) {
        this.nomeCategoria = nomeCategoria;
        this.prodCod = prodCod;
        this.qntEstoque = qntEstoque;
        this.qntMinEstoque = qntMinEstoque;
        this.preco = preco;
        this.nomeFornecedor = nomeFornecedor;
        this.contatoFornecedor = contatoFornecedor;
    }
}
