package org.example;

import org.example.controller.ProductController;
import org.example.controller.UsuarioController;
import org.example.models.ProductModel;
import org.example.models.UsuarioModel;
import org.example.repository.ProductRepository;
import org.example.repository.UsuarioRepository;
import org.example.view.AdminMenuView;
import org.example.view.LoginMenuView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsuarioRepository usuarioRepository = null;
        UsuarioModel userConnected = null;

        ProductRepository productRepository = null;
        ProductModel productConnected = null;



        Connection conn = null;
        String urlConnection = "jdbc:sqlite:databaseecommerce.sqlite";

        try {
            conn = DriverManager.getConnection(urlConnection);
            if (conn != null) {
                usuarioRepository = new UsuarioRepository(conn);
                System.out.println("Conectado com sucesso!");
            } else {
                System.out.println("Falha na conexão");
                System.exit(1);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: \n" + e.getMessage());
            System.exit(1);
        }

        UsuarioController usuarioController = new UsuarioController(usuarioRepository);
        ProductController productController = new ProductController(productRepository);
        usuarioController.iniciarSistema();

    }
}