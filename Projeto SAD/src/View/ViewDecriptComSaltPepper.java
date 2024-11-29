package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewDecriptComSaltPepper {
    private JTextField textField1;
    private JButton button1;
    private JButton backButton;
    private JTextArea textArea1;

    public ViewDecriptComSaltPepper() {
        // Configuração do JFrame
        JFrame frame = new JFrame("Desincriptar Salt e Pepper");
        frame.setSize(550, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Configuração do painel principal
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240)); // Cor de fundo do painel

        // Configuração do JTextField (entrada de texto)
        textField1 = new JTextField();
        textField1.setBounds(20, 20, 340, 30);
        textField1.setFont(new Font("Arial", Font.PLAIN, 14)); // Fonte do JTextField
        panel.add(textField1);

        // Configuração do JButton (botão de descriptografar)
        button1 = new JButton("Desincriptar");
        button1.setBounds(370, 20, 150, 30);
        button1.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte do botão
        button1.setBackground(new Color(135, 206, 250)); // Cor de fundo do botão
        button1.setFocusPainted(false); // Remove o foco do botão
        panel.add(button1);

        // Configuração do JButton (botão para voltar ao menu)
        backButton = new JButton("Voltar para o Menu");
        backButton.setBounds(20, 670, 200, 30);
        backButton.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte do botão
        backButton.setBackground(new Color(255, 99, 71)); // Cor de fundo do botão
        backButton.setFocusPainted(false); // Remove o foco do botão
        panel.add(backButton);

        // Configuração do JTextArea (área para mostrar o resultado)
        textArea1 = new JTextArea();
        textArea1.setBounds(20, 60, 500, 600);
        textArea1.setEditable(false); // Impede a edição direta pelo usuário
        textArea1.setFont(new Font("Arial", Font.PLAIN, 14)); // Fonte do JTextArea
        textArea1.setBackground(Color.WHITE); // Cor de fundo do JTextArea
        textArea1.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Borda do JTextArea
        panel.add(textArea1);

        // Ação para o botão "Desincriptar Salt e Pepper"
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField1.getText();
                String result = descriptografarSaltPepper(inputText);
                textArea1.setText(result);
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Menu();
            }
        });

        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }


    private String descriptografarSaltPepper(String text) {
        // Aqui você implementaria a lógica de descriptografia do "Salt e Pepper"
        // Este é um exemplo fictício de como a lógica poderia ser aplicada
        return "Texto descriptografado com Salt e Pepper: " + text;
    }
}