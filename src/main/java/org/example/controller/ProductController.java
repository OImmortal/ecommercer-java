package org.example.controller;

import org.example.models.ProductModel;
import org.example.repository.ProductRepository;
import org.example.view.ProductMenuView;

import java.util.List;

public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMenuView productMenuView;

    public ProductController(ProductRepository productRepository, ProductMenuView productMenuView) {
        this.productRepository = productRepository;
        this.productMenuView = productMenuView;
    }

    public void iniciarCompra() {
        boolean continuar = true;
        while (continuar) {
            int opcao = productMenuView.showMenu();

            switch (opcao) {
                case 1 -> {
                    List<ProductModel> products = productRepository.findAll();
                    for (ProductModel product : products) {
                        System.out.println(product.getId() + " - " + product.nome + " - " + product.preco);
                    }
                }
                case 2 -> {
                    int id = productMenuView.lerIdProduto();
                    productRepository.findById(id).ifPresentOrElse(
                            produto -> System.out.println(produto.getNome() + ": R$" + produto.getPreco()),
                            () -> System.out.println("Produto não encontrado.")
                    );
                }
                case 3 -> continuar = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
