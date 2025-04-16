package org.example.models;

import java.sql.Date;
import java.time.LocalDateTime;

public class UsuarioModel extends EntityModel{
    private String nome;
    private String email;
    private String senha;

    public UsuarioModel(int id, Date createdAt, Date updatedAt, String nome, String email, String senha) {
        super(id, createdAt, updatedAt);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UsuarioModel(int id, String nome, String email, String senha) {
        super(id);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UsuarioModel(String nome, String email, String senha) {
        super();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
