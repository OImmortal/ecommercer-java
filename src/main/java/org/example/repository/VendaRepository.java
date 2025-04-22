package org.example.repository;

import org.example.models.ProductModel;
import org.example.models.UsuarioModel;
import org.example.models.VendaModel;

import java.sql.*;
import java.util.*;

public class VendaRepository implements IEntityRepository<VendaModel> {

    private final Connection connection;
    private final ProductRepository productRepository;
    private final String table = "sales";

    public VendaRepository(Connection connection, ProductRepository productRepository) {
        this.connection = connection;
        this.productRepository = productRepository;
    }

    public Connection getConnection() {
        return connection;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    @Override
    public void save(VendaModel entity) {
        for (ProductModel produto : entity.getProdutos()) {

            ProductModel searchProduct = productRepository.findById(produto.getId()).orElse(null);
            if (searchProduct == null) {
                System.out.println("Produto Inexistente");
                return;
            }

            String sql = "INSERT INTO " + table + " (user_id, product_id, forma_pagamento, valor_total, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, entity.getUsuario().getId());
                statement.setInt(2, produto.getId());
                statement.setString(3, entity.getFormaPagamento());
                statement.setDouble(4, entity.getValorTotal());
                statement.setDate(5, entity.getCreatedAt());
                statement.setDate(6, entity.getUpdatedAt());


                int linhas = statement.executeUpdate();
                if (linhas > 0) {
                    System.out.println("Salvo com sucesso!");
                } else {
                    System.out.println("Erro ao salvar!");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(VendaModel entity) {
        String sql = "DELETE FROM " + table + " WHERE user_id = ? AND createdAt = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getUsuario());
            statement.setDate(2, entity.getCreatedAt());
            int linhas = statement.executeUpdate();
            System.out.println(linhas > 0 ? "Venda deletada com sucesso!" : "Nenhuma venda encontrada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(VendaModel entity) {
        delete(entity); // Remove venda antiga
        save(entity);   // Insere nova versão
    }

    @Override
    public Optional<VendaModel> findById(int userId) {
        UsuarioRepository usuarioRepository = new UsuarioRepository(connection);
        UsuarioModel usuario = usuarioRepository.returnModelById(userId);

        if (usuario == null) {
            System.out.println("Usuário não encontrado");
            return Optional.empty();
        }

        String sql = "SELECT * FROM " + table + " WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, usuario.getId());
            ResultSet rs = statement.executeQuery();

            VendaModel venda = null;
            List<ProductModel> produtos = new ArrayList<>();

            while (rs.next()) {
                if (venda == null) {
                    venda = new VendaModel();
                    venda.setUsuario(usuario); // Aqui usamos o já carregado
                    venda.setFormaPagamento(rs.getString("forma_pagamento"));
                    venda.setValorTotal(rs.getDouble("valor_total"));
                    venda.setCreatedAt(rs.getDate("createdAt"));
                    venda.setUpdatedAt(rs.getDate("updatedAt"));
                }

                int productId = rs.getInt("product_id");
                productRepository.findById(productId).ifPresent(produtos::add);
            }

            if (venda != null) {
                venda.setProdutos(produtos);
                return Optional.of(venda);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    @Override
    public List<VendaModel> findAll() {
        String sql = "SELECT * FROM " + table + " ORDER BY createdAt DESC";
        Map<String, VendaModel> vendaMap = new HashMap<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                String key = rs.getInt("user_id") + "_" + rs.getDate("createdAt");

                VendaModel venda = vendaMap.get(key);
                if (venda == null) {
                    venda = new VendaModel();
                    venda.setUsuario(rs.getObject("user_id", UsuarioModel.class));
                    venda.setFormaPagamento(rs.getString("forma_pagamento"));
                    venda.setValorTotal(rs.getDouble("valor_total"));
                    venda.setCreatedAt(rs.getDate("createdAt"));
                    venda.setUpdatedAt(rs.getDate("updatedAt"));
                    venda.setProdutos(new ArrayList<>());
                    vendaMap.put(key, venda);
                }

                int productId = rs.getInt("product_id");
                venda.getProdutos().add(productRepository.findById(productId).get());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(vendaMap.values());
    }
}
