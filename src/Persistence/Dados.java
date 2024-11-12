package Persistence;

import Model.*;

import java.util.ArrayList;

public class Dados {
    private static int userLogged;
    private static ArrayList<Alerta> alertas = new ArrayList<>();
    private static ArrayList<Categoria> categorias = new ArrayList<>();
    private static ArrayList<Pessoa> pessoas = new ArrayList<>();
    private static ArrayList<Venda> vendas = new ArrayList<>();
    private static ArrayList<Fornecedor> fornecedores = new ArrayList<>();

    public Dados() {
        userLogged = -1;
    }

    public static int getUserLogged() {
        return userLogged;
    }

    public static void setUserLogged(int userLogged) {
        Dados.userLogged = userLogged;
    }

    public static ArrayList<Alerta> getAlertas() {
        return alertas;
    }

    public static void setAlertas(ArrayList<Alerta> alertas) {
        Dados.alertas = alertas;
    }

    public static boolean addAlerta(Alerta newA) {
        Dados.alertas.add(newA);
        return false;
    }

    public static ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public static void setCategorias(ArrayList<Categoria> categorias) {
        Dados.categorias = categorias;
    }

    public static boolean addCategoria(Categoria newC) {
        Dados.categorias.add(newC);
        return false;
    }

    public static ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public static void setPessoas(ArrayList<Pessoa> pessoas) {
        Dados.pessoas = pessoas;
    }

    public static boolean addPessoa(Pessoa newP) {
        Dados.pessoas.add(newP);
        return false;
    }

    public static ArrayList<Venda> getVendas() {
        return vendas;
    }

    public static void setVendas(ArrayList<Venda> vendas) {
        Dados.vendas = vendas;
    }

    public static boolean addVenda(Venda newV) {
        Dados.vendas.add(newV);
        return false;
    }

    public static ArrayList<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public static void setFornecedores(ArrayList<Fornecedor> fornecedores) {
        Dados.fornecedores = fornecedores;
    }

    public static boolean addFornecedor(Fornecedor newF) {
        Dados.fornecedores.add(newF);
        return false;
    }
}
