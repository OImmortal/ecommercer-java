package org.example.view;

import java.util.Scanner;

public class ProductMenuView {
    private final Scanner scanner;

    public ProductMenuView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int showMenu() {
        System.out.println("1 - Ver produtos");
        System.out.println("2 - Buscar produto por ID");
        System.out.println("3 - Sair");
        System.out.print("Escolha: ");
        return scanner.nextInt();
    }

    public int lerIdProduto() {
        System.out.print("Digite o ID do produto: ");
        return scanner.nextInt();
    }
}
