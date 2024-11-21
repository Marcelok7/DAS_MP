package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    private JButton desincriptarCesar;
    private JButton desincriptarSalt;

    private JPanel panel;

    public Menu() {
        // Criar o JFrame
        JFrame frame = new JFrame("Menu");
        frame.setSize(340, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Criar um painel para os botões
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10)); // 2 linhas, 1 coluna, espaçamento de 10
        panel.setBorder(BorderFactory.createTitledBorder("Escolha a Descriptografia")); // Borda com título

        // Botão para Descriptografar Cifra de César
        desincriptarCesar = new JButton("Desincriptar Cifra de César");
        desincriptarCesar.setFont(new Font("Arial", Font.BOLD, 16)); // Fonte do botão
        desincriptarCesar.setBackground(new Color(135, 206, 250)); // Cor de fundo do botão
        desincriptarCesar.setFocusPainted(false); // Remove o foco do botão
        desincriptarCesar.setIcon(new ImageIcon("path/to/cesar_icon.png")); // Ícone do botão
        desincriptarCesar.setHorizontalTextPosition(SwingConstants.RIGHT); // Texto à direita do ícone
        panel.add(desincriptarCesar);

        // Botão para Descriptografar Cifra de Salt
        desincriptarSalt = new JButton("Desincriptar Salt");
        desincriptarSalt.setFont(new Font("Arial", Font.BOLD, 16)); // Fonte do botão
        desincriptarSalt.setBackground(new Color(135, 206, 250)); // Cor de fundo do botão
        desincriptarSalt.setFocusPainted(false); // Remove o foco do botão
        desincriptarSalt.setIcon(new ImageIcon("path/to/salt_icon.png")); // Ícone do botão
        desincriptarSalt.setHorizontalTextPosition(SwingConstants.RIGHT); // Texto à direita do ícone
        panel.add(desincriptarSalt);

        // Ação para o botão "Desincriptar Cifra de César"
        desincriptarCesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewDecript();
                frame.dispose(); // Fecha o menu atual
            }
        });

        // Ação para o botão "Desincriptar Salt"
        desincriptarSalt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewDecriptComSalt();
                frame.dispose();
            }
        });

        // Adicionar o painel ao frame
        frame.add(panel, BorderLayout.CENTER);

        // Adicionar um fundo ao frame
        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Cor de fundo

        // Adicionar um título ao frame
        JLabel titleLabel = new JLabel("Menu de Descriptografia", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(70, 130, 180));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Exibe o menu
        frame.setVisible(true);
    }
}