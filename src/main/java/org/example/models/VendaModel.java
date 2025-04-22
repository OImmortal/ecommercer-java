package org.example.models;

import java.sql.Date;
import java.util.List;

public class VendaModel extends EntityModel {

    private UsuarioModel usuario;
    private List<ProductModel> produtos;
    private String formaPagamento;
    private double valorTotal;

    public VendaModel(UsuarioModel usuario, List<ProductModel> produtos, String formaPagamento, double valorTotal) {
        this.usuario = usuario;
        this.produtos = produtos;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
    }

    public VendaModel(int id, Date createdAt, Date updatedAt, UsuarioModel usuario, List<ProductModel> produtos, String formaPagamento, double valorTotal) {
        super(id, createdAt, updatedAt);
        this.usuario = usuario;
        this.produtos = produtos;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
    }

    public VendaModel(int id, UsuarioModel usuario, List<ProductModel> produtos, String formaPagamento, double valorTotal) {
        super(id);
        this.usuario = usuario;
        this.produtos = produtos;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
    }

    public VendaModel() {
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public List<ProductModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProductModel> produtos) {
        this.produtos = produtos;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
