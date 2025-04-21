package org.example.controller;

import org.example.repository.ProductRepository;
import org.example.view.AdminMenuView;


public class AdminController {
    private ProductRepository productRepository;
    private final AdminMenuView adminMenuView;

    public AdminController(ProductRepository productRepository, AdminMenuView adminMenuView) {

        this.productRepository = productRepository;
        this.adminMenuView = adminMenuView;
    }


    int option = 0;

    public void administrar(){
        do {

            option = adminMenuView.showMenu();

            switch (option) {
                case 1 -> adminMenuView.casdastrarProduto(productRepository);
                case 2 -> adminMenuView.listarProdutos(productRepository);
                case 3 -> adminMenuView.BuscaPorId(productRepository);
                case 4 -> adminMenuView.deletarProduto(productRepository);
                default->{
                    throw new IllegalStateException("Unexpected value: " + option);
                }
            }

        } while (option != 5);
    }
}
