package Model;

import java.util.ArrayList;

public class Categoria {
    private ArrayList<Produto> produtos = new ArrayList<>();
    private String nome;

    public Categoria() {
    }

    public Categoria(ArrayList<Produto> produtos, String nome) {
        this.produtos = produtos;
        this.nome = nome;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
