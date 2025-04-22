package org.example.controller;

import org.example.models.ProductModel;
import org.example.models.UsuarioModel;
import org.example.models.VendaModel;
import org.example.repository.VendaRepository;
import org.example.view.VendaMenuView;

import java.util.Optional;

public class VendaController {

    private final VendaMenuView vendaMenuView;
    private final VendaRepository vendaRepository;

    public VendaController(VendaMenuView vendaMenuView, VendaRepository vendaRepository) {
        this.vendaMenuView = vendaMenuView;
        this.vendaRepository = vendaRepository;
    }

    public void iniciarVenda(UsuarioModel userConnected) {
        boolean continuar = true;
        while (continuar) {
            int opcao = vendaMenuView.showMenu();
            switch (opcao) {
                case 1 -> {
                    VendaModel venda = vendaMenuView.createVenda(userConnected);
                    vendaRepository.save(venda);
                }
                case 2 -> {
                    Optional<VendaModel> vendas = vendaRepository.findById(userConnected.getId());
                    System.out.println("=== Lista de Vendas ===");
                    for (VendaModel venda : vendas.stream().toList()) {
                        System.out.println("Data: " + venda.getCreatedAt());
                        System.out.println("Forma de Pagamento: " + venda.getFormaPagamento());
                        System.out.println("Valor Total: R$ " + venda.getValorTotal());

                        System.out.println("Produtos:");
                        for (ProductModel produto : venda.getProdutos()) {
                            System.out.println("- " + produto.getNome() + " (R$ " + produto.getPreco() + ")");
                        }
                        System.out.println("-------------------------");
                    }
                }
                case 3 -> continuar = false;
            }
        }
    }
}
