package org.example.repository;

import org.example.models.UsuarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioRepository implements IEntityRepository<UsuarioModel> {
    private final Connection connection;

    public UsuarioRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(UsuarioModel entity) {
        String query = "INSERT INTO users (nome, email, senha, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getEmail());
            stmt.setString(3, entity.getSenha());
            stmt.setDate(4, entity.getCreatedAt());
            stmt.setDate(5, entity.getUpdatedAt());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuario: " + e.getMessage());
            return;
        }
    }

    @Override
    public void delete(UsuarioModel entity) {

    }

    @Override
    public void update(UsuarioModel entity) {

    }

    @Override
    public UsuarioModel findById(Long id) {
        return null;
    }

    public UsuarioModel findByEmailAndPassowrd(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        UsuarioModel entity = null;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                entity = new UsuarioModel(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
                return entity;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
            return null;
        }

        return entity;
    }

    @Override
    public List<UsuarioModel> findAll() {
        return List.of();
    }
}
