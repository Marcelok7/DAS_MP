package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    private JButton desincriptarCesar;
    private JButton desincriptarSalt;
    private JButton desincriptarSaltPepper;

    private JPanel panel;

    public Menu() {

        JFrame frame = new JFrame("Menu");
        frame.setSize(520, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // Mudado para 3 linhas
        panel.setBorder(BorderFactory.createTitledBorder("Escolha a Descriptografia"));

        desincriptarCesar = new JButton("Desincriptar Cifra de César");
        desincriptarCesar.setFont(new Font("Arial", Font.BOLD, 16));
        desincriptarCesar.setBackground(new Color(135, 206, 250));
        desincriptarCesar.setFocusPainted(false);
        desincriptarCesar.setIcon(new ImageIcon("path/to/cesar_icon.png"));
        desincriptarCesar.setHorizontalTextPosition(SwingConstants.RIGHT);
        panel.add(desincriptarCesar);

        desincriptarSalt = new JButton("Desincriptar Salt");
        desincriptarSalt.setFont(new Font("Arial", Font.BOLD, 16));
        desincriptarSalt.setBackground(new Color(135, 206, 250));
        desincriptarSalt.setFocusPainted(false);
        desincriptarSalt.setIcon(new ImageIcon("path/to/salt_icon.png"));
        desincriptarSalt.setHorizontalTextPosition(SwingConstants.RIGHT);
        panel.add(desincriptarSalt);

        desincriptarSaltPepper = new JButton("Desincriptar Salt e Pepper");
        desincriptarSaltPepper.setFont(new Font("Arial", Font.BOLD, 16));
        desincriptarSaltPepper.setBackground(new Color(135, 206, 250));
        desincriptarSaltPepper.setFocusPainted(false);
        desincriptarSaltPepper.setIcon(new ImageIcon("path/to/salt_icon.png"));
        desincriptarSaltPepper.setHorizontalTextPosition(SwingConstants.RIGHT);
        panel.add(desincriptarSaltPepper);

        desincriptarCesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewDecript();
                frame.dispose(); // Fecha o menu atual
            }
        });

        desincriptarSalt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewDecriptComSalt();
                frame.dispose();
            }
        });

        desincriptarSaltPepper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewDecriptComSaltPepper();
                frame.dispose();
            }
        });

        frame.add(panel, BorderLayout.CENTER);

        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Cor de fundo

        JLabel titleLabel = new JLabel("Menu de Desincriptar", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(70, 130, 180));
        frame.add(titleLabel, BorderLayout.NORTH);

        frame.setVisible(true);
    }
}