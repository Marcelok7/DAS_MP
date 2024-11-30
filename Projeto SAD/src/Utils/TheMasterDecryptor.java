package Utils;
import java.util.*;

public class TheMasterDecryptor {

    public static String descriptografarCifraCesar(String texto, int deslocamento) {
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

    public static List<String> desencriptarSalt(String mensagemCifrada) {
        List<String> resultados = new ArrayList<>();
        for (int chave = 1; chave <= 25; chave++) {
            String textoDescriptografado = descriptografarCifraCesar(mensagemCifrada, chave);
            // Testa se o Salt está no início
            if (textoDescriptografado.length() > 4) {
                String possivelSaltInicio = textoDescriptografado.substring(0, 4);
                String mensagemSemSaltInicio = textoDescriptografado.substring(4);
                resultados.add("Chave: " + chave + ", Salt (início): " + possivelSaltInicio + ", Mensagem: " + mensagemSemSaltInicio);
            }
            // Testa se o Salt está no final
            if (textoDescriptografado.length() > 4) {
                String possivelSaltFim = textoDescriptografado.substring(textoDescriptografado.length() - 4);
                String mensagemSemSaltFim = textoDescriptografado.substring(0, textoDescriptografado.length() - 4);
                resultados.add("Chave: " + chave + ", Salt (final): " + possivelSaltFim + ", Mensagem: " + mensagemSemSaltFim);
            }
        }
        return resultados;
    }

    private static String removePepper(String mensagem) {
        if (mensagem.length() > 1 && Character.isDigit(mensagem.charAt(0))) {
            if (Character.isDigit(mensagem.charAt(1))) {
                return mensagem.substring(2); // PEPPER tem 2 dígitos
            } else {
                return mensagem.substring(1); // PEPPER tem 1 dígito
            }
        }
        return mensagem; // Sem PEPPER
    }

    /*
    private static List<String> removeSalt(String mensagem) {
        List<String> mensagensPossiveis = new ArrayList<>();
        // SALT no início
        for (int i = 1; i <= 4; i++) {
            if (mensagem.length() > i) {
                String possivelSaltInicio = mensagem.substring(0, i);
                if (possivelSaltInicio.matches("[!#$%&+\\-<=>@]+")) {
                    mensagensPossiveis.add(mensagem.substring(i)); // Remover SALT inicial
                }
            }
        }
        // SALT no final
        for (int i = 1; i <= 4; i++) {
            if (mensagem.length() > i) {
                String possivelSaltFim = mensagem.substring(mensagem.length() - i);
                if (possivelSaltFim.matches("[!#$%&+\\-<=>@]+")) {
                    mensagensPossiveis.add(mensagem.substring(0, mensagem.length() - i)); // Remover SALT final
                }
            }
        }
        // Caso nenhum SALT seja encontrado
        if (mensagensPossiveis.isEmpty()) {
            mensagensPossiveis.add(mensagem); // Mantém a mensagem original
        }
        return mensagensPossiveis;
    }*/

    private static List<String> removeSalt(String mensagem) {
        List<String> mensagensPossiveis = new ArrayList<>();
        String saltPattern = "[!#$%&+\\-<=>@]{1,4}";

        // SALT no início
        if (mensagem.matches("^" + saltPattern + ".*")) {
            for (int i = 1; i <= 4 && i < mensagem.length(); i++) {
                if (mensagem.substring(0, i).matches(saltPattern)) {
                    mensagensPossiveis.add(mensagem.substring(i));
                }
            }
        }

        // SALT no final
        if (mensagem.matches(".*" + saltPattern + "$")) {
            for (int i = 1; i <= 4 && i < mensagem.length(); i++) {
                if (mensagem.substring(mensagem.length() - i).matches(saltPattern)) {
                    mensagensPossiveis.add(mensagem.substring(0, mensagem.length() - i));
                }
            }
        }

        // Caso nenhum SALT seja encontrado
        if (mensagensPossiveis.isEmpty()) {
            mensagensPossiveis.add(mensagem);
        }

        return mensagensPossiveis;
    }

    private static String applyCaesarCipher(String mensagem, int deslocamento) {
        return descriptografarCifraCesar(mensagem, deslocamento);
    }

    private static Map<Character, Character> inferirAlfabetoSubstituicao(String mensagem) {
        final String PORTUGUESE_ALPHABET = "AEOISRNTULCDMPVGHQBFZJXKYW";
        final String MESSAGE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Map<Character, Integer> frequencias = new HashMap<>();

        for (char c : mensagem.toCharArray()) {
            if (MESSAGE_ALPHABET.indexOf(c) != -1) {
                frequencias.put(c, frequencias.getOrDefault(c, 0) + 1);
            }
        }

        List<Map.Entry<Character, Integer>> ordenadas = new ArrayList<>(frequencias.entrySet());
        ordenadas.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        Map<Character, Character> mapaSubstituicao = new HashMap<>();
        for (int i = 0; i < Math.min(ordenadas.size(), PORTUGUESE_ALPHABET.length()); i++) {
            mapaSubstituicao.put(ordenadas.get(i).getKey(), PORTUGUESE_ALPHABET.charAt(i));
        }

        return mapaSubstituicao;
    }

    private static String aplicarSubstituicao(String mensagem, Map<Character, Character> mapa) {
        StringBuilder resultado = new StringBuilder();
        for (char c : mensagem.toCharArray()) {
            if (mapa.containsKey(c)) {
                resultado.append(mapa.get(c));
            } else {
                resultado.append(c); // Mantém caracteres que não estão no mapa
            }
        }
        return resultado.toString();
    }

    public static List<String> desencriptarMensagem(String mensagem) {
        String mensagemSemPepper = removePepper(mensagem);
        List<String> mensagensSemSalt = removeSalt(mensagemSemPepper);
        List<String> resultadosFinais = new ArrayList<>();

        for (String mensagemPossivel : mensagensSemSalt) {
            for (int deslocamento = 1; deslocamento <= 25; deslocamento++) {
                String mensagemCesar = applyCaesarCipher(mensagemPossivel, deslocamento);
                Map<Character, Character> mapaSubstituicao = inferirAlfabetoSubstituicao(mensagemCesar);
                String mensagemFinal = aplicarSubstituicao(mensagemCesar, mapaSubstituicao);
                resultadosFinais.add("César: " + deslocamento + ", Mensagem: " + mensagemFinal);
            }
        }
        return resultadosFinais;
    }
}