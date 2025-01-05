package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    private JButton desincriptarCesar;
    private JButton desincriptarSaltSp1Ex2;
    private JButton desincriptarCOCSalt;
    private JButton desincriptarSaltSp2Ex1;
    private JButton desincriptarPepperSp2Ex1;
    private JButton desincriptarAlfaSubsSp2Ex1;

    private JPanel panel;

    public Menu() {

        JFrame frame = new JFrame("Menu");
        frame.setSize(520, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // Mudado para 3 linhas
        panel.setBorder(BorderFactory.createTitledBorder("Escolha a Descriptografia"));

        desincriptarCesar = new JButton("Sprint 1 - EX 1 - Desincriptar Apenas Cifra De César");
        desincriptarCesar.setFont(new Font("Arial", Font.BOLD, 16));
        desincriptarCesar.setBackground(new Color(135, 206, 250));
        desincriptarCesar.setFocusPainted(false);
        desincriptarCesar.setHorizontalTextPosition(SwingConstants.RIGHT);
        panel.add(desincriptarCesar);

        desincriptarSaltSp1Ex2 = new JButton("Sprint 1 - EX 2 - Desincriptar Apenas Salt");
        desincriptarSaltSp1Ex2.setFont(new Font("Arial", Font.BOLD, 16));
        desincriptarSaltSp1Ex2.setBackground(new Color(135, 206, 250));
        desincriptarSaltSp1Ex2.setFocusPainted(false);
        desincriptarSaltSp1Ex2.setHorizontalTextPosition(SwingConstants.RIGHT);
        panel.add(desincriptarSaltSp1Ex2);


        desincriptarCOCSalt = new JButton("Sprint 1 - EX 2 - Desincriptar CDC + Salt");
        desincriptarCOCSalt.setFont(new Font("Arial", Font.BOLD, 16));
        desincriptarCOCSalt.setBackground(new Color(135, 206, 250));
        desincriptarCOCSalt.setFocusPainted(false);
        desincriptarCOCSalt.setHorizontalTextPosition(SwingConstants.RIGHT);
        panel.add(desincriptarCOCSalt);

        desincriptarSaltSp2Ex1 = new JButton("Sprint 2 - EX 1 - Desincriptar Apenas SALT");
        desincriptarSaltSp2Ex1.setFont(new Font("Arial", Font.BOLD, 16));
        desincriptarSaltSp2Ex1.setBackground(new Color(135, 206, 250));
        desincriptarSaltSp2Ex1.setFocusPainted(false);
        desincriptarSaltSp2Ex1.setHorizontalTextPosition(SwingConstants.RIGHT);
        panel.add(desincriptarSaltSp2Ex1);

        desincriptarPepperSp2Ex1 = new JButton("Sprint 2 - EX 1 - Desincriptar Apenas Pepper");
        desincriptarPepperSp2Ex1.setFont(new Font("Arial", Font.BOLD, 16));
        desincriptarPepperSp2Ex1.setBackground(new Color(135, 206, 250));
        desincriptarPepperSp2Ex1.setFocusPainted(false);
        desincriptarPepperSp2Ex1.setHorizontalTextPosition(SwingConstants.RIGHT);
        panel.add(desincriptarPepperSp2Ex1);

        desincriptarAlfaSubsSp2Ex1 = new JButton("Sprint 2 - EX 1 - Desincriptar Apenas Alfabeto Substituição");
        desincriptarAlfaSubsSp2Ex1.setFont(new Font("Arial", Font.BOLD, 16));
        desincriptarAlfaSubsSp2Ex1.setBackground(new Color(135, 206, 250));
        desincriptarAlfaSubsSp2Ex1.setFocusPainted(false);
        desincriptarAlfaSubsSp2Ex1.setHorizontalTextPosition(SwingConstants.RIGHT);
        panel.add(desincriptarAlfaSubsSp2Ex1);

        desincriptarCesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewDecriptCipherOfCaesar();
                frame.dispose();
            }
        });

        desincriptarSaltSp1Ex2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewDecriptSaltSp1Ex2();
                frame.dispose();
            }
        });

        desincriptarCOCSalt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewDecriptCOCSaltSp1Ex2();
                frame.dispose();
            }
        });

        desincriptarSaltSp2Ex1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewDecriptSaltSp2Ex1();
                frame.dispose();
            }
        });

        desincriptarPepperSp2Ex1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewDecriptPepperSp2Ex1();
                frame.dispose();
            }
        });

        desincriptarAlfaSubsSp2Ex1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewDecriptAlfabSubsSp2Ex1();
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