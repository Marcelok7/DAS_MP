package Utils;

public class DescriptografarCifraDeCesar {

    public static String descriptografar(String texto, int deslocamento) {
        StringBuilder textoDescriptografado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            // Verificar se o caractere está entre 'A' e 'Z'
            if (c >= 'A' && c <= 'Z') {
                // Deslocamento para decifrar
                char novaLetra = (char) (((c - 'A' - deslocamento + 26) % 26) + 'A');
                textoDescriptografado.append(novaLetra);
            } else {
                // Caso o caractere não esteja no alfabeto
                textoDescriptografado.append(c);
            }
        }

        return textoDescriptografado.toString();
    }

}
