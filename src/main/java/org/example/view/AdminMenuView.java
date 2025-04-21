package org.example.view;

import org.example.models.ProductModel;
import org.example.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AdminMenuView {
    private final Scanner scanner;


    public AdminMenuView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int showMenu() {
        System.out.println("\n--- MENU ADMINISTRATIVO ---");
        System.out.println("1 - Cadastrar novo produto");
        System.out.println("2 - Listar todos os produtos");
        System.out.println("3 - Listar produtos por ID");
        System.out.println("4 - Deletar produto por ID");
        System.out.println("5 - Sair do menu administrativo");
        System.out.print("Escolha: ");
        return scanner.nextInt();
    }

    public void casdastrarProduto(ProductRepository productRepository) {
        scanner.nextLine();
        System.out.print("Nome do produto: ");
        String nomeProduto = scanner.nextLine();

        System.out.print("Preço do produto: ");
        double precoProduto = scanner.nextDouble();

        ProductModel produto = new ProductModel(nomeProduto, precoProduto);
            productRepository.save(produto);
            System.out.println("Produto adicionado com sucesso!");
    }

    public void listarProdutos(ProductRepository productRepository) {
        List<ProductModel> produtos = productRepository.findAll();

        if(produtos.isEmpty()) {
            System.out.println("\n--- Nenhum produto encontrado ---");
        }else {
            System.out.println("\n--- Produtos cadastrados ---");
            for (ProductModel p : produtos) {
                System.out.println(p.getId() + " - " + p.getNome() + " - R$" + p.getPreco());
            }
        }
    }

    public void BuscaPorId(ProductRepository productRepository) {
        scanner.nextLine();
        System.out.println("Digite o id do produto: ");
        int id = Integer.parseInt(scanner.nextLine());

        Optional<ProductModel> IDprodutos = productRepository.findById(id);
        if (IDprodutos.isPresent()) {
            System.out.println("\n--- Produtos cadastrados ---");
            System.out.println(IDprodutos.get().getNome() + " - " + IDprodutos.get().getPreco());
        }else{
            System.out.println("\n--- Nenhum produto encontrado com o id ---");
        }
    }

    public void deletarProduto(ProductRepository productRepository) {
        scanner.nextLine();
        System.out.println("Digite o id do produto a ser Deletado: ");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<ProductModel> IDproduto = productRepository.findById(id);
        if (IDproduto.isPresent()) {
            productRepository.delete(IDproduto.get());
            System.out.println("Produto deletado com sucesso!");
        }else{
            System.out.println("Produto não encontrado!");
        }
    }
}
