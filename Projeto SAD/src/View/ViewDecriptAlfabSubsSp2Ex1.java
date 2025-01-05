package View;

import Utils.TheMasterDecryptor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewDecriptAlfabSubsSp2Ex1 {

    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JButton backButton;
    private JTextArea textArea1;

    public ViewDecriptAlfabSubsSp2Ex1() {

        JFrame frame = new JFrame("Sprint 2 - EX 1 - Desincriptar Apenas Alfabeto Substituição");
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240)); // Cor de fundo do painel

        jLabel1 = new JLabel("Texto a Descodificar:");
        jLabel1.setBounds(20, 10, 200, 20);
        jLabel1.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(jLabel1);

        textField1 = new JTextField();
        textField1.setBounds(20, 40, 540, 30);
        textField1.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(textField1);

        jLabel2 = new JLabel("Alfabeto Substituição:");
        jLabel2.setBounds(20, 80, 200, 20);
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(jLabel2);

        textField2 = new JTextField();
        textField2.setBounds(20, 110, 540, 30);
        textField2.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(textField2);

        button1 = new JButton("Desincriptar");
        button1.setBounds(380, 160, 180, 30);
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        button1.setBackground(new Color(135, 206, 250));
        button1.setFocusPainted(false);
        panel.add(button1);

        backButton = new JButton("Voltar para o Menu");
        backButton.setBounds(20, 700, 200, 30);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(255, 99, 71));
        backButton.setFocusPainted(false);
        panel.add(backButton);

        textArea1 = new JTextArea();
        textArea1.setBounds(20, 210, 540, 470);
        textArea1.setEditable(false);
        textArea1.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea1.setBackground(Color.WHITE);
        textArea1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(textArea1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String inputText2 = textField2.getText();

                int numberOfLetters = inputText2.length();

                if (!inputText2.isEmpty() && numberOfLetters == 26) {

                    String inputText = textField1.getText().toUpperCase();
                    String resultadoTexto = TheMasterDecryptor.aplicarAlfabetoSubstituicao(inputText, inputText2);

                    textArea1.setText(resultadoTexto);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "O campo de substituição deve conter exatamente 26 letras!",
                            "Erro de Validação",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
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
}