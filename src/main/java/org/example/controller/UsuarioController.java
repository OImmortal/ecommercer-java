package org.example.controller;

import org.example.models.UsuarioModel;
import org.example.repository.ProductRepository;
import org.example.repository.UsuarioRepository;
import org.example.view.AdminMenuView;
import org.example.view.LoginMenuView;

import java.util.Scanner;

public class UsuarioController {
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void iniciarSistema() {
        Scanner scanner = new Scanner(System.in);
        UsuarioModel userConnected = null;
        int opcao = 0;

        do {
            System.out.println("====================================");
            System.out.println("===========Loja Bem Legal===========");
            System.out.println("====================================\n");

            if (userConnected == null) {
                opcao = LoginMenuView.ShowMenu();

                switch (opcao) {
                    case 1 -> {
                        UsuarioModel usuario = LoginMenuView.CreateUsuario();
                        usuarioRepository.save(usuario);
                        userConnected = usuario;
                    }

                    case 2 -> {
                        LoginMenuView.setUsuarioRepository(usuarioRepository);
                        UsuarioModel user = LoginMenuView.AcessUsuario();
                        if (user == null) {
                            System.out.println("Usuário não encontrado");
                        } else {
                            userConnected = user;
                            System.out.println("Usuário conectado com sucesso");
                        }
                    }

                    case 3 -> {
                        LoginMenuView.setUsuarioRepository(usuarioRepository);
                        LoginMenuView.LoginAdmin();
                    }

                    case 4 -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("Opção inválida!");
                }
            }
        } while (userConnected == null && opcao != 4);
    }
}
