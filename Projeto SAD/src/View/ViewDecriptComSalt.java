package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static Utils.DescriptografarCifraDeCesar.descriptografar;

public class ViewDecriptComSalt {
    private JTextField textField1;
    private JButton button1;
    private JButton backButton;
    private JTextArea textArea1;

    public ViewDecriptComSalt() {
        // Configuração do JFrame
        JFrame frame = new JFrame("Desincriptar o amigo César com Salt");
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


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensagemCifrada = textField1.getText().toUpperCase();
                List<String> resultados = desencriptarMensagem(mensagemCifrada);

                StringBuilder resultadoText = new StringBuilder();
                for (String resultado : resultados) {
                    resultadoText.append(resultado).append("\n");
                }
                textArea1.setText(resultadoText.toString());
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

    public static List<String> desencriptarMensagem(String mensagemCifrada) {
        List<String> resultados = new ArrayList<>();

        for (int chave = 1; chave <= 25; chave++) {
            String textoDescriptografado = descriptografar(mensagemCifrada, chave);

            // Testa se o Salt está no início
            if (textoDescriptografado.length() > 3) {
                String possivelSaltInicio = textoDescriptografado.substring(0, 3);
                String mensagemSemSaltInicio = textoDescriptografado.substring(3);
                resultados.add("Chave: " + chave + ", Salt (início): " + possivelSaltInicio + ", Mensagem: " + mensagemSemSaltInicio);
            }

            // Testa se o Salt está no final
            if (textoDescriptografado.length() > 3) {
                String possivelSaltFim = textoDescriptografado.substring(textoDescriptografado.length() - 3);
                String mensagemSemSaltFim = textoDescriptografado.substring(0, textoDescriptografado.length() - 3);
                resultados.add("Chave: " + chave + ", Salt (final): " + possivelSaltFim + ", Mensagem: " + mensagemSemSaltFim);
            }
        }
        return resultados;
    }
}
