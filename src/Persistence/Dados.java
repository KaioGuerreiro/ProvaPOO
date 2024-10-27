package Persistence;

import Model.*;

import java.util.ArrayList;

public class Dados {
    private int userLogged;
    private ArrayList<Alerta> alertas = new ArrayList<>();
    private ArrayList<Categoria> categorias = new ArrayList<>();
    private ArrayList<Pessoa> pessoas = new ArrayList<>();
    private ArrayList<Venda> vendas = new ArrayList<>();
    private ArrayList<Fornecedor> fornecedores = new ArrayList<>();

    public Dados() {
    }

    public int getUserLogged() {
        return userLogged;
    }

    public void setUserLogged(int userLogged) {
        this.userLogged = userLogged;
    }

    public ArrayList<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(ArrayList<Alerta> alertas) {
        this.alertas = alertas;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public ArrayList<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(ArrayList<Venda> vendas) {
        this.vendas = vendas;
    }

    public ArrayList<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
}
