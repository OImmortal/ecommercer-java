package org.example.repository;

import org.example.models.ProductModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements IEntityRepository<ProductModel> {

    private final Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(ProductModel produto) {
        String query = "INSERT INTO products VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getNome());
            stmt.setDouble(3, produto.getPreco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(ProductModel entity) {
        String query = "DELETE FROM products WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(ProductModel entity) {
        String query = "UPDATE products SET nome = ?, preco = ?, WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, entity.getNome());
            stmt.setDouble(2, entity.getPreco());
            stmt.setInt(4, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<ProductModel> findById(int id) {
        String query = "SELECT * FROM products WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                ProductModel product = new ProductModel(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getDouble("preco")
                );
                return Optional.of(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }


    @Override
    public List findAll() {
        String query = "SELECT * FROM products";
        List<ProductModel> products = new LinkedList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                ProductModel product = new ProductModel(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;

    }
}
