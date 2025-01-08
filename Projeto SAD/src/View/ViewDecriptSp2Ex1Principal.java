package View;

import Utils.TheMasterDecryptor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewDecriptSp2Ex1Principal {

    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JButton backButton;
    private JTextArea textArea1;

    public ViewDecriptSp2Ex1Principal() {

        JFrame frame = new JFrame("Sprint 2 - EX 1 - Desincriptação Final");
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));

        jLabel1 = new JLabel("Texto a Descodificar:");
        jLabel1.setFont(new Font("Arial", Font.PLAIN, 14));

        textField1 = new JTextField();
        textField1.setFont(new Font("Arial", Font.PLAIN, 14));

        jLabel2 = new JLabel("Alfabeto Substituição:");
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 14));

        textField2 = new JTextField();
        textField2.setFont(new Font("Arial", Font.PLAIN, 14));

        button1 = new JButton("Desincriptar");
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        button1.setBackground(new Color(135, 206, 250));
        button1.setFocusPainted(false);

        backButton = new JButton("Voltar para o Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(255, 99, 71));
        backButton.setFocusPainted(false);

        textArea1 = new JTextArea();
        textArea1.setEditable(false);
        textArea1.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea1.setBackground(Color.WHITE);
        textArea1.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JScrollPane scrollPane = new JScrollPane(textArea1);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(textField1)
                        .addComponent(jLabel2)
                        .addComponent(textField2)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addGap(200, 200, Integer.MAX_VALUE)
                                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        )
                        .addComponent(scrollPane)
                        .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 400, Integer.MAX_VALUE)
                        .addGap(10)
                        .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        );

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String inputText2 = textField2.getText();

                int numberOfLetters = inputText2.length();

                if (!inputText2.isEmpty() && numberOfLetters == 26) {

                    StringBuilder resultado = new StringBuilder();

                    String inputText = textField1.getText();

                    List<String> mensagensPossiveis = TheMasterDecryptor.desencriptarMensagem(inputText, inputText2);

                    for (int i = 0; i < mensagensPossiveis.size(); i++) {
                        resultado.append(i).append(" - ").append(mensagensPossiveis.get(i)).append("\n");
                    }

                    textArea1.setText(resultado.toString());
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

        frame.add(panel);
        frame.setVisible(true);
    }
}