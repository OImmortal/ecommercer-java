package org.example.view;

import org.example.models.UsuarioModel;
import org.example.repository.UsuarioRepository;

import java.util.Scanner;

public class LoginMenuView {
    private static final Scanner scanner = new Scanner(System.in);

    static UsuarioRepository usuarioRepository;

    public static int ShowMenu() {
        System.out.println("1 - Criar Usuário");
        System.out.println("2 - Acessar conta");
        System.out.println("3 - Login Administrativo");
        System.out.println("4 - Sair");
        System.out.print("Informe uma opção: ");
        return scanner.nextInt();
    }

    public static UsuarioModel CreateUsuario() {
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

        return new UsuarioModel(email, nome, senha);
    }

    public static UsuarioModel AcessUsuario() {
        String email = "", senha = "";
        scanner.nextLine();

        while (email.trim().isEmpty()) {
            System.out.print("Informe o email: ");
            email = scanner.nextLine();
        }

        while (senha.trim().isEmpty()) {
            System.out.print("Informe a senha: ");
            senha = scanner.nextLine();
        }

        return usuarioRepository.findByEmailAndPassword(email, senha);
    }

    public static void setUsuarioRepository(UsuarioRepository repository) {
        usuarioRepository = repository;
    }

    public static void LoginAdmin() {
        String email = "", senha = "";
        scanner.nextLine();

        while (email.trim().isEmpty()) {
            System.out.println("Digite o email de administrador: ");
            email = scanner.nextLine();
        }

        while (senha.trim().isEmpty()) {
            System.out.println("Digite o senha de administrador: ");
            senha = scanner.nextLine();
        }

        if (email.equals("admin@admin") && senha.equals("admin123")) {
            AdminMenuView.AdminMenu();
        }
        System.out.println("Credenciais Invalidas!");
    }
}
