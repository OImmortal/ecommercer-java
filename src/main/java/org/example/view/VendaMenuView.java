package org.example.view;

import org.example.models.ProductModel;
import org.example.models.UsuarioModel;
import org.example.models.VendaModel;
import org.example.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class VendaMenuView {
    private final Scanner scanner;
    private final ProductRepository productRepository;

    public VendaMenuView(Scanner scanner, ProductRepository productRepository) {
        this.scanner = scanner;
        this.productRepository = productRepository;
    }

    public int showMenu() {
        System.out.println("==== MENU DE VENDA ====");
        System.out.println("1. Criar nova venda");
        System.out.println("2. Todas as vendas");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    public VendaModel createVenda(UsuarioModel userConnected) {
        // Inserir lista de IDs dos produtos
        scanner.nextLine(); // limpar o buffer
        System.out.print("Informe os IDs dos produtos (separados por vírgula): ");
        String idsInput = scanner.nextLine();
        String[] ids = idsInput.split(",");
        List<ProductModel> produtos = new ArrayList<>();

        for (String idStr : ids) {
            try {
                int produtoId = Integer.parseInt(idStr.trim());
                ProductModel product = productRepository.findById(produtoId).orElse(null);
                if (product == null) {
                    System.out.println("Produto Inexistente");
                    break;
                }

                produtos.add(product);
            } catch (NumberFormatException e) {
                System.out.println("ID inválido ignorado: " + idStr);
            }
        }

        // Forma de pagamento
        System.out.print("Informe a forma de pagamento: ");
        String formaPagamento = scanner.nextLine();

        double valorTotal = produtos.stream()
                .mapToDouble(ProductModel::getPreco)
                .sum();

        return new VendaModel(userConnected, produtos, formaPagamento, valorTotal);
    }
}
