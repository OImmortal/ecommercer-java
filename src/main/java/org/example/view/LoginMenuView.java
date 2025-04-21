package org.example.view;

import org.example.models.UsuarioModel;

import java.util.Scanner;

public class LoginMenuView {
    private final Scanner scanner;

    public LoginMenuView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int showMenu() {
        System.out.println("1 - Cadastrar usuário");
        System.out.println("2 - Fazer login");
        System.out.println("3 - Sair");
        System.out.print("Escolha: ");
        return scanner.nextInt();
    }

    public int showMenuOption() {
        System.out.println("1 - Usuario");
        System.out.println("2 - Administrador");
        System.out.println("3 - Sair");
        System.out.print("Escolha: ");
        return scanner.nextInt();
    }

    public UsuarioModel getUsuarioCadastro() {
        System.out.print("Nome: ");
        scanner.nextLine(); // limpar buffer
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        return new UsuarioModel(nome, email, senha);
    }

    public UsuarioModel getLoginInfo() {
        System.out.print("Email: ");
        scanner.nextLine(); // limpar buffer
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        return new UsuarioModel("", email, senha); // Nome vazio para comparação
    }
}
