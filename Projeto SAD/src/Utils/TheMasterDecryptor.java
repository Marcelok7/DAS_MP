/*
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
    }

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

    /*
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


    public static List<String> desencriptarMensagem(String mensagem) {
        List<String> resultadosFinais = new ArrayList<>();

        for (int deslocamento = 1; deslocamento <= 25; deslocamento++) {
            // 1. Aplicar Cifra de César
            String mensagemCesar = applyCaesarCipher(mensagem, deslocamento);

            // 2. Aplicar Alfabeto de Substituição
            Map<Character, Character> mapaSubstituicao = inferirAlfabetoSubstituicao(mensagemCesar);
            String mensagemSubstituida = aplicarSubstituicao(mensagemCesar, mapaSubstituicao);

            // 3. Remover Pepper
            String mensagemSemPepper = removePepper(mensagemSubstituida);

            // 4. Remover Salt
            List<String> mensagensSemSalt = removeSalt(mensagemSemPepper);

            // Adicionar todas as combinações possíveis ao resultado final
            for (String mensagemPossivel : mensagensSemSalt) {
                resultadosFinais.add("César: " + deslocamento + ", Mensagem: " + mensagemPossivel);
            }
        }

        return resultadosFinais;
    }
}*/
package Utils;

import java.util.*;

public class TheMasterDecryptor {

    private static final String PORTUGUESE_ALPHABET = "AEOISRNTULCDMPVGHQBFZJXKWY";
    private static final String MESSAGE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    //CIFRA DE CÉSAR - 1º Sprint - Exercício 1
    public static String descriptografarCifraCesar(String texto, int deslocamento) {
        StringBuilder textoDescriptografado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                char novaLetra = (char) (((c - 'A' - deslocamento + 26) % 26) + 'A');
                textoDescriptografado.append(novaLetra);
            } else {
                textoDescriptografado.append(c);
            }
        }
        return textoDescriptografado.toString();
    }

    //SALT - 1º Sprint - Exercício 2
    public static List<String> desencriptarSaltSp1Ex2(String mensagem) {

        final String SALT_PATTERN = "[a-zA-Z]{3}";

        List<String> mensagensPossiveis = new ArrayList<>();

        // SALT no início
        if (mensagem.length() > 3 && mensagem.substring(0, 3).matches(SALT_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(3));
        }

        // SALT no final
        if (mensagem.length() > 3 && mensagem.substring(mensagem.length() - 3).matches(SALT_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(0, mensagem.length() - 3));
        }

        // Caso nenhum SALT seja encontrado
        if (mensagensPossiveis.isEmpty()) {
            mensagensPossiveis.add(mensagem);
        }

        return mensagensPossiveis;
    }

    //SALT - 2º Sprint - Exercício 1
    public static List<String> desencriptarSalt2SPEx1(String mensagem) {

        // SALT com caracteres especiais e comprimento máximo de 4
        final String SALT_PATTERN = "[!#$%&+\\-<=>@]{1,4}";

        List<String> mensagensPossiveis = new ArrayList<>();

        // SALT no início
        if (mensagem.length() > 4 && mensagem.substring(0, 4).matches(SALT_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(4));
        }
        if (mensagem.length() > 3 && mensagem.substring(0, 3).matches(SALT_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(3));
        }
        if (mensagem.length() > 2 && mensagem.substring(0, 2).matches(SALT_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(2));
        }
        if (mensagem.length() > 1 && mensagem.substring(0, 1).matches(SALT_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(1));
        }

        // SALT no final
        if (mensagem.length() > 4 && mensagem.substring(mensagem.length() - 4).matches(SALT_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(0, mensagem.length() - 4));
        }
        if (mensagem.length() > 3 && mensagem.substring(mensagem.length() - 3).matches(SALT_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(0, mensagem.length() - 3));
        }
        if (mensagem.length() > 2 && mensagem.substring(mensagem.length() - 2).matches(SALT_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(0, mensagem.length() - 2));
        }
        if (mensagem.length() > 1 && mensagem.substring(mensagem.length() - 1).matches(SALT_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(0, mensagem.length() - 1));
        }

        // Caso nenhum SALT seja encontrado
        if (mensagensPossiveis.isEmpty()) {
            mensagensPossiveis.add(mensagem);
        }

        return mensagensPossiveis;
    }

    //PEPPER - 2º Sprint - Exercício 1
    public static List<String> desencriptarPeperSp2Ex1(String mensagem) {

        //Padrão para o peper: apenas dígitos (0-9), comprimento de 1 a 2
        final String PEPER_PATTERN = "\\d{1,2}";

        List<String> mensagensPossiveis = new ArrayList<>();

        // Peper no início
        if (mensagem.length() > 2 && mensagem.substring(0, 2).matches(PEPER_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(2));
        }
        if (mensagem.length() > 1 && mensagem.substring(0, 1).matches(PEPER_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(1));
        }

        // Peper no final
        if (mensagem.length() > 2 && mensagem.substring(mensagem.length() - 2).matches(PEPER_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(0, mensagem.length() - 2));
        }
        if (mensagem.length() > 1 && mensagem.substring(mensagem.length() - 1).matches(PEPER_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(0, mensagem.length() - 1));
        }

        // Caso nenhum peper seja encontrado
        if (mensagensPossiveis.isEmpty()) {
            mensagensPossiveis.add(mensagem);
        }

        return mensagensPossiveis;
    }

    //Alfabeto Substituição - 2ª Sprint - Exercício 1
    public static String desencriptarAlfabetoSubstituicao(String mensagemCriptografada) {

        // Frequência relativa das letras mais comuns em português (base no gráfico)
        char[] frequenciaPortugues = {'A', 'E', 'O', 'S', 'R', 'I', 'N', 'D', 'M', 'U', 'T', 'C', 'L', 'P', 'V', 'G', 'H', 'Q', 'B', 'F', 'Z', 'J', 'X', 'K', 'W', 'Y'};

        // Frequência das letras no texto criptografado
        Map<Character, Integer> frequenciaTexto = new HashMap<>();

        // Contar frequência das letras na mensagem criptografada
        for (char c : mensagemCriptografada.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                frequenciaTexto.put(c, frequenciaTexto.getOrDefault(c, 0) + 1);
            }
        }

        // Ordenar as letras criptografadas por frequência em ordem decrescente
        char[] letrasCriptografadasOrdenadas = frequenciaTexto.keySet().stream()
                .sorted((a, b) -> frequenciaTexto.get(b) - frequenciaTexto.get(a))
                .map(Object::toString)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString().toCharArray();

        // Criar o mapeamento entre as letras criptografadas e as letras mais frequentes em português
        Map<Character, Character> mapaSubstituicao = new HashMap<>();
        for (int i = 0; i < letrasCriptografadasOrdenadas.length && i < frequenciaPortugues.length; i++) {
            mapaSubstituicao.put(letrasCriptografadasOrdenadas[i], frequenciaPortugues[i]);
        }

        // Substituir as letras na mensagem criptografada com base no mapeamento
        StringBuilder mensagemDesencriptada = new StringBuilder();
        for (char c : mensagemCriptografada.toUpperCase().toCharArray()) {
            if (mapaSubstituicao.containsKey(c)) {
                mensagemDesencriptada.append(mapaSubstituicao.get(c));
            } else {
                mensagemDesencriptada.append(c); // Manter caracteres que não são letras
            }
        }

        return mensagemDesencriptada.toString();
    }

    // Método principal para desencriptar mensagens
    public static List<String> desencriptarMensagem(String mensagem) {
        //String mensagemSemPepper = desencriptarPeper(mensagem);
        //List<String> mensagensSemSalt = desencriptarSalt2SPEx1(mensagemSemPepper);
        List<String> resultadosFinais = new ArrayList<>();

        /*for (String mensagemPossivel : mensagensSemSalt) {
            for (int deslocamento = 1; deslocamento <= 25; deslocamento++) {
                String mensagemCesar = descriptografarCifraCesar(mensagemPossivel, deslocamento);
                Map<Character, Character> mapaSubstituicao = inferirAlfabetoSubstituicao(mensagemCesar);
                String mensagemFinal = aplicarSubstituicao(mensagemCesar, mapaSubstituicao);
                resultadosFinais.add("César: " + deslocamento + ", Mensagem: " + mensagemFinal);
            }
        }
        return resultadosFinais;
    }*/
        return resultadosFinais;
    }
}