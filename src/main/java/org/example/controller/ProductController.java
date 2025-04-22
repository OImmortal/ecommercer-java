package org.example.controller;

import org.example.models.ProductModel;
import org.example.models.UsuarioModel;
import org.example.models.VendaModel;
import org.example.repository.ProductRepository;
import org.example.repository.VendaRepository;
import org.example.view.ProductMenuView;
import org.example.view.VendaMenuView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMenuView productMenuView;
    private final VendaMenuView vendaMenuView;
    private final VendaRepository vendaRepository;

    public ProductController(ProductRepository productRepository, ProductMenuView productMenuView, VendaMenuView vendaMenuView, VendaRepository vendaRepository) {
        this.productRepository = productRepository;
        this.productMenuView = productMenuView;
        this.vendaMenuView = vendaMenuView;
        this.vendaRepository = vendaRepository;
    }

    public void iniciarCompra(UsuarioModel userConnected) {
        boolean continuar = true;
        while (continuar) {
            int opcao = productMenuView.showMenu();

            switch (opcao) {
                case 1 -> {
                    List<ProductModel> products = productRepository.findAll();
                    System.out.println("======================");
                    for (ProductModel product : products) {
                        System.out.println(product.getId() + " - " + product.nome + " - " + product.preco);
                    }
                    System.out.println("======================");
                }
                case 2 -> {
                    int id = productMenuView.lerIdProduto();
                    productRepository.findById(id).ifPresentOrElse(
                            produto -> System.out.println(produto.getNome() + ": R$" + produto.getPreco()),
                            () -> System.out.println("Produto não encontrado.")
                    );
                }
                case 3 -> {
                    VendaController vendaController = new VendaController(vendaMenuView, vendaRepository);
                    vendaController.iniciarVenda(userConnected);
                }
                case 4 -> continuar = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
