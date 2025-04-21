package org.example.view;

import org.example.models.ProductModel;
import org.example.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class AdminMenuView {
    private static final Scanner scanner = new Scanner(System.in);
    static ProductRepository productRepository;

    public static void AdminMenu() {

        int option = 0;

        do{
            System.out.println("\n--- MENU ADMINISTRATIVO ---");
            System.out.println("1 - Cadastrar novo produto");
            System.out.println("2 - Listar todos os produtos");
            System.out.println("3 - Listar produtos por ID");
            System.out.println("4 - Deletar produto por ID");
            System.out.println("5 - Sair do menu administrativo");

            option= scanner.nextInt();

            switch (option) {
                case 1:
                    scanner.nextLine();
                    System.out.print("Nome do produto: ");
                    String nomeProduto = scanner.nextLine();

                    System.out.print("Preço do produto: ");
                    double precoProduto = scanner.nextDouble();

                    ProductModel produto = new ProductModel(nomeProduto, precoProduto);
                    productRepository.save(produto);
                    System.out.println("Produto adicionado com sucesso!");
                    break;

                case 2:
                    List<ProductModel> produtos = productRepository.findAll();
                    System.out.println("\n--- Produtos cadastrados ---");
                    for (ProductModel p : produtos) {
                        System.out.println(p.getId() + " - " + p.getNome() + " - R$" + p.getPreco());
                    }
                    break;

                case 3:
                    System.out.println("Digite o id do produto: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    Optional<ProductModel> IDprodutos = productRepository.findById(id);
                    if (IDprodutos.isPresent()) {
                        System.out.println("\n--- Produtos cadastrados ---");
                        System.out.println(IDprodutos.get().getNome() + " - " + IDprodutos.get().getPreco());
                    }
                    break;

                case 4:
                    System.out.println("Digite o id do produto a ser Deletado: ");
                    id = Integer.parseInt(scanner.nextLine());
                    Optional<ProductModel> IDproduto = productRepository.findById(id);
                    if (IDproduto.isPresent()) {
                        productRepository.delete(IDproduto.get());
                        System.out.println("Produto deletado com sucesso!");
                    }else{
                        System.out.println("Produto não encontrado!");
                    }

                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }

        }while(option != 5);
    }
}
