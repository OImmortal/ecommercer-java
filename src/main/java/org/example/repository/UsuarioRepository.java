package org.example.repository;

import org.example.models.UsuarioModel;

import java.sql.*;
import java.util.List;
import java.util.Optional;

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
            stmt.setString(1, entity.getNome().trim());
            stmt.setString(2, entity.getEmail().trim());
            stmt.setString(3, entity.getSenha().trim());
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
        UsuarioModel user = returnModelById(entity.getId());
        if (user == null) return;
        String query = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement stmtS = this.connection.prepareStatement(query);

        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuario: " + e.getMessage());
        }
    }

    @Override
    public void update(UsuarioModel entity) {
        String sql = "";
    }

    @Override
    public Optional<UsuarioModel> findById(int id) {
        return Optional.empty();
    }

    public UsuarioModel returnModelById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        UsuarioModel entity = null;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UsuarioModel(rs.getInt("id"), rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
            return null;
        }

        return entity;
    }

    public UsuarioModel findByEmailAndPassowrd(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND senha = ?";
        UsuarioModel entity = null;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, email.trim());
            stmt.setString(2, password.trim());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UsuarioModel(rs.getInt("id"), rs.getDate("createdAt"), rs.getDate("updatedAt"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
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