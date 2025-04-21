package org.example;

import org.example.controller.AdminController;
import org.example.controller.UsuarioController;
import org.example.controller.ProductController;
import org.example.repository.UsuarioRepository;
import org.example.repository.ProductRepository;
import org.example.view.AdminMenuView;
import org.example.view.LoginMenuView;
import org.example.view.ProductMenuView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:sqlite:databaseecommerce.sqlite";

        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Conectado ao banco!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);

        // Repositórios
        UsuarioRepository usuarioRepository = new UsuarioRepository(conn);
        ProductRepository productRepository = new ProductRepository(conn);



        // Views
        LoginMenuView loginMenuView = new LoginMenuView(scanner);
        ProductMenuView productMenuView = new ProductMenuView(scanner);
        AdminMenuView adminMenuView = new AdminMenuView(scanner);

        // Controllers
        UsuarioController usuarioController = new UsuarioController(usuarioRepository, loginMenuView);
        ProductController productController = new ProductController(productRepository, productMenuView);
        AdminController adminController = new AdminController(productRepository, adminMenuView);

        // Executa sistema
        boolean sistemaAtivo = true;
        while (sistemaAtivo) {
            int opcaoLogin = loginMenuView.showMenuOption();
            switch (opcaoLogin) {
                case 1-> {
                    usuarioController.iniciarSistema();
                    productController.iniciarCompra();
                }
                case 2 -> {
                    System.out.print("Digite o email de admin: ");
                    String email = scanner.next();
                    System.out.print("Digite a senha de admin: ");
                    String senha = scanner.next();

                    // Validar login admin
                    if ("admin@admin".equals(email) && "admin123".equals(senha)) {
                        adminController.administrar();
                    } else {
                        System.out.println("Credenciais inválidas!");
                    }
                }
                case 3 -> sistemaAtivo = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
