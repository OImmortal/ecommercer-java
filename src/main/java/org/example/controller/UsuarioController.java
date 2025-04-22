package org.example.controller;

import org.example.models.UsuarioModel;
import org.example.repository.UsuarioRepository;
import org.example.view.LoginMenuView;

public class UsuarioController {
    private final UsuarioRepository usuarioRepository;
    private final LoginMenuView loginMenuView;
    private UsuarioModel userConnected;

    public UsuarioController(UsuarioRepository usuarioRepository, LoginMenuView loginMenuView) {
        this.usuarioRepository = usuarioRepository;
        this.loginMenuView = loginMenuView;
    }

    public void iniciarSistema() {
        boolean continuar = true;

        System.out.println("====================================");
        System.out.println("===========Loja Bem Legal===========");
        System.out.println("====================================\n");

        while (continuar) {
            int opcao = loginMenuView.showMenu();
            switch (opcao) {
                case 1 -> {
                    UsuarioModel novo = loginMenuView.getUsuarioCadastro();
                    usuarioRepository.save(novo);
                }
                case 2 -> {
                    UsuarioModel login = loginMenuView.getLoginInfo();
                    UsuarioModel user = usuarioRepository.findByEmailAndPassword(login.getEmail(), login.getSenha());
                    if (user != null) {
                        userConnected = user;
                        System.out.println("Login bem-sucedido: " + user.getNome());
                        continuar = false;
                    } else {
                        System.out.println("Usuário ou senha inválidos.");
                    }
                }
//                case 3 -> System.out.println("Acesso ao modo admin (função futura)");
                case 3 -> continuar = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public UsuarioModel getUserConnected() {
        return userConnected;
    }
}
