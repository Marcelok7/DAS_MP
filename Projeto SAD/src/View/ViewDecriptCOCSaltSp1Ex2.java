package View;

import Utils.TheMasterDecryptor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewDecriptCOCSaltSp1Ex2 {
    private JTextField textField1;
    private JButton button1;
    private JButton backButton;
    private JTextArea textArea1;

    public ViewDecriptCOCSaltSp1Ex2() {

        JFrame frame = new JFrame("Exercicio 2 - Desincriptar CDC + Salt");
        frame.setSize(550, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));

        textField1 = new JTextField();
        textField1.setBounds(20, 20, 340, 30);
        textField1.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(textField1);

        button1 = new JButton("Desincriptar");
        button1.setBounds(370, 20, 150, 30);
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        button1.setBackground(new Color(135, 206, 250));
        button1.setFocusPainted(false);
        panel.add(button1);

        backButton = new JButton("Voltar para o Menu");
        backButton.setBounds(20, 670, 200, 30);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(255, 99, 71));
        backButton.setFocusPainted(false);
        panel.add(backButton);

        textArea1 = new JTextArea();
        textArea1.setEditable(false);
        textArea1.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea1.setBackground(Color.WHITE);
        textArea1.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JScrollPane scrollPane = new JScrollPane(textArea1);
        scrollPane.setBounds(20, 60, 500, 600);
        panel.add(scrollPane);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoCifrado = textField1.getText().toUpperCase();
                StringBuilder resultado = new StringBuilder();

                if (textoCifrado.isEmpty()) {
                    resultado.append("Insira um texto para cifrar.");
                } else {

                    List<String> mensagensSemSalt = TheMasterDecryptor.desencriptarSaltSp1Ex2(textoCifrado);

                    for(int i = 0; i < mensagensSemSalt.size(); i++){

                        String mensagemSemSalt = mensagensSemSalt.get(i);

                        String posicaoSalt = null;

                        if(i == 0) {
                            posicaoSalt = "===== SALT Remoção Inicio =====";
                        }
                        else{
                            posicaoSalt = "===== SALT Remoção Fim  =====";
                        }

                        resultado.append(posicaoSalt).append(mensagemSemSalt).append("\n");

                        // Tenta todas as chaves de 1 a 25
                        for (int j = 1; j <= 25; j++) {
                            String textoDescriptografado = TheMasterDecryptor.descriptografarCifraCesar(mensagemSemSalt, j);
                            resultado.append("Deslocamento ").append(j).append(": ").append(textoDescriptografado).append("\n");
                        }
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