package org.example;

import org.example.models.UsuarioModel;
import org.example.repository.UsuarioRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsuarioRepository usuarioRepository = null;
        UsuarioModel userConnected = null;

        Connection conn = null;
        String urlConnection = "jdbc:sqlite:database.sqlite";

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

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while(opcao != 3) {
            System.out.println("====================================");
            System.out.println("===========Loja Bem Legal===========");
            System.out.println("====================================\n\n");

            if (userConnected == null) {

                System.out.println("1 - Criar Usuário");
                System.out.println("2 - Acessar conta");
                System.out.println("3 - Sair");
                System.out.print("Informe uma opção: ");
                opcao = scanner.nextInt();

                if (opcao == 1) {
                    String email = "", nome = "", senha = "";

                    scanner.nextLine();

                    while (email.trim().isEmpty()) {
                        System.out.print("Informe o email: ");
                        email = scanner.nextLine();
                    }

                    while (nome.trim().isEmpty()) {
                        System.out.print("Informe o nome: ");
                        nome = scanner.nextLine();
                    }

                    while (senha.trim().isEmpty()) {
                        System.out.print("Informe a senha: ");
                        senha = scanner.nextLine();
                    }

                    UsuarioModel usuario = new UsuarioModel(email, nome, senha);
                    usuarioRepository.save(usuario);
                    userConnected = usuario;
                }
                if (opcao == 2) {
                    String email, senha;
                    System.out.print("Informe seu email: ");
                    email = scanner.next();
                    System.out.print("Informe sua senha: ");
                    senha = scanner.next();
                }
            }

            opcao = 3;
        }
    }
}