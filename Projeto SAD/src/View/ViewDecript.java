package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Utils.DescriptografarCifraDeCesar.descriptografar;

public class ViewDecript {
    private JTextField textField1;
    private JButton button1;
    private JButton backButton; // Botão para voltar ao menu
    private JTextArea textArea1;

    public ViewDecript() {
        // Configuração do JFrame
        JFrame frame = new JFrame("Desincriptar o amigo César");
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
        backButton.setBounds(20, 670, 150, 30);
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

        // ActionListener para o botão de descriptografar
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém o texto do campo de texto e converte para maiúsculas
                String textoCifrado = textField1.getText().toUpperCase();
                StringBuilder resultado = new StringBuilder();

                // Verifica se o campo de texto está vazio
                if (textoCifrado.isEmpty()) {
                    // Se o campo estiver vazio, adiciona uma mensagem de erro
                    resultado.append("Insira um texto para cifrar.");
                } else {
                    // Tenta todas as chaves de 1 a 25 para descriptografar
                    for (int i = 1; i <= 25; i++) {
                        String textoDescriptografado = descriptografar(textoCifrado, i);
                        resultado.append("Deslocamento ").append(i).append(": ").append(textoDescriptografado).append("\n");
                    }
                }

                // Exibe os resultados na JTextArea
                textArea1.setText(resultado.toString());
            }
        });

        // ActionListener para o botão de voltar ao menu
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para voltar ao menu
                frame.dispose(); // Fecha a janela atual
                new Menu();
            }
        }); // Missing ); here

        // Adiciona o painel ao frame e torna o frame visível
        frame.add(panel);
        frame.setVisible(true);
    } // Missing } here to close the constructor
}
