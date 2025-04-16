package org.example.models;

import java.sql.Date;

public class ProductModel extends EntityModel {

    public String nome;
    public double preco;

    public ProductModel(int id, Date createdAt, Date updatedAt, String nome, double preco) {
        super(id, createdAt, updatedAt);
        this.nome = nome;
        this.preco = preco;
    }

    public ProductModel(int id, String nome, double preco) {
        super(id);
        this.nome = nome;
        this.preco = preco;
    }

    public ProductModel(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
