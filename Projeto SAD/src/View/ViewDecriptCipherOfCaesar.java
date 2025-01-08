package View;

import Utils.TheMasterDecryptor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewDecriptCipherOfCaesar {
    private JTextField textField1;
    private JButton button1;
    private JButton backButton;
    private JTextArea textArea1;
    private TheMasterDecryptor theMasterDecryptor = new TheMasterDecryptor();

    public ViewDecriptCipherOfCaesar() {

        JFrame frame = new JFrame("EX 1 - Desincriptar Cifra De César");
        frame.setSize(550, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240)); // Cor de fundo do painel

        textField1 = new JTextField();
        textField1.setBounds(20, 20, 340, 30);
        textField1.setFont(new Font("Arial", Font.PLAIN, 14)); // Fonte do JTextField
        panel.add(textField1);

        button1 = new JButton("Desincriptar");
        button1.setBounds(370, 20, 150, 30);
        button1.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte do botão
        button1.setBackground(new Color(135, 206, 250)); // Cor de fundo do botão
        button1.setFocusPainted(false); // Remove o foco do botão
        panel.add(button1);

        backButton = new JButton("Voltar para o Menu");
        backButton.setBounds(20, 670, 200, 30);
        backButton.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte do botão
        backButton.setBackground(new Color(255, 99, 71)); // Cor de fundo do botão
        backButton.setFocusPainted(false); // Remove o foco do botão
        panel.add(backButton);

        textArea1 = new JTextArea();
        textArea1.setBounds(20, 60, 500, 600);
        textArea1.setEditable(false); // Impede a edição direta pelo usuário
        textArea1.setFont(new Font("Arial", Font.PLAIN, 14)); // Fonte do JTextArea
        textArea1.setBackground(Color.WHITE); // Cor de fundo do JTextArea
        textArea1.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Borda do JTextArea
        panel.add(textArea1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoCifrado = textField1.getText().toUpperCase();
                StringBuilder resultado = new StringBuilder();

                if (textoCifrado.isEmpty()) {
                    resultado.append("Insira um texto para cifrar.");
                } else {
                    for (int i = 1; i <= 25; i++) {
                        String textoDescriptografado = theMasterDecryptor.descriptografarCifraCesar(textoCifrado, i);
                        resultado.append("Deslocamento ").append(i).append(": ").append(textoDescriptografado).append("\n");
                    }
                }

                textArea1.setText(resultado.toString());
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new Menu();
            }
        });


        frame.add(panel);
        frame.setVisible(true);
    }
}