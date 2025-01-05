package View;

import Utils.TheMasterDecryptor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewDecriptPepperSp2Ex1 {
    private JTextField textField1;
    private JButton button1;
    private JButton backButton;
    private JTextArea textArea1;

    public ViewDecriptPepperSp2Ex1() {

        JFrame frame = new JFrame("Sprint 2 - EX 1 - Desincriptar Apenas Pepper");
        frame.setSize(550, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

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
        textArea1.setBounds(20, 60, 500, 600);
        textArea1.setEditable(false);
        textArea1.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea1.setBackground(Color.WHITE);
        textArea1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(textArea1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String inputText = textField1.getText().toUpperCase();

                List<String> resultados = TheMasterDecryptor.desencriptarPeperSp2Ex1(inputText);

                StringBuilder resultadoTexto = new StringBuilder();
                for (String resultado : resultados) {
                    resultadoTexto.append(resultado).append("\n");
                }

                textArea1.setText(resultadoTexto.toString());
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