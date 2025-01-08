package Utils;

import java.util.*;

public class TheMasterDecryptor {

    private static Base64.Decoder decoder = Base64.getDecoder();

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

        //3 letras, seja minuscula ou maiscula
        final String SALT_PATTERN = "[a-zA-Z]{3}";

        List<String> mensagensPossiveis = new ArrayList<>();

        //Verifica SALT no início
        if (mensagem.length() > 3 && mensagem.substring(0, 3).matches(SALT_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(3));
        }

        // SALT no final
        if (mensagem.length() > 3 && mensagem.substring(mensagem.length() - 3).matches(SALT_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(0, mensagem.length() - 3));
        }

        // Caso nenhum SALT seja encontrado, retorna mensagem original
        if (mensagensPossiveis.isEmpty()) {
            mensagensPossiveis.add(mensagem);
        }

        return mensagensPossiveis;
    }

    //SALT - 2º Sprint - Exercício 1
    public static List<String> desencriptarSalt2SPEx1(String mensagem) {

        //SALT com estes caracteres especiais e comprimento de 1 a 4
        final String SALT_PATTERN = "[!#$%&+\\-<=>@]{1,4}";

        List<String> mensagensPossiveis = new ArrayList<>();

        //Verificar SALT no início
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

        //Verificar SALT no final
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

        //Caso nenhum SALT seja encontrado, é apresentada a mensagem original
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

        /*
        // Peper no final
        if (mensagem.length() > 2 && mensagem.substring(mensagem.length() - 2).matches(PEPER_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(0, mensagem.length() - 2));
        }
        if (mensagem.length() > 1 && mensagem.substring(mensagem.length() - 1).matches(PEPER_PATTERN)) {
            mensagensPossiveis.add(mensagem.substring(0, mensagem.length() - 1));
        }
        */

        //Caso nenhum peper seja encontrado, é apresentada a mensagem original
        if (mensagensPossiveis.isEmpty()) {
            mensagensPossiveis.add(mensagem);
        }

        return mensagensPossiveis;
    }

    //Decifra uma palavra, de acordo com o alfabeto de substituitção usado (Só funciona com palavras portuguesas)
    public static String aplicarAlfabetoSubstituicao(String palavraADecifrarString, String alfabetoDeSubstituicaoString) {

        String alfabetoOriginalString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        char[] alfabetoOriginal     = alfabetoOriginalString.toCharArray();
        char[] alfabetoSubstituicao = alfabetoDeSubstituicaoString.toUpperCase().toCharArray();

        char[] palavraADecifrar = palavraADecifrarString.toCharArray();

        StringBuilder palavraDecifrada = new StringBuilder();

        for(int i = 0; i < palavraADecifrar.length; i++){

            char currentLetter = palavraADecifrar[i];

            for (int j = 0; j < alfabetoSubstituicao.length; j++){
                if(alfabetoSubstituicao[j] == currentLetter){
                    palavraDecifrada.append(alfabetoOriginal[j]);
                }
            }
        }

        return palavraDecifrada.toString();
    }

    // Método principal para desencriptar mensagens
    public static List<String> desencriptarMensagem(String hashBase64, String alfabetoSubstituicao) {

        //============================ 1º - Remove 64 ============================
        hashBase64 = hashBase64.replace("64", "");

        System.out.println("hashBase64 sem 64: " + hashBase64);

        //============================ 2º - Efetua o decode ============================
        byte[] hashBase64Decode = decoder.decode(hashBase64);
        String hashBase64DecodedString = new String(hashBase64Decode);
        System.out.println("hashBase64 decoded: " + hashBase64DecodedString);

        List<String> mensagensSemPeperSemSaltSemCesar = new ArrayList<>();

        //============================ 3º - Remove o pepper ============================

        List<String> mensagensSemPeper = desencriptarPeperSp2Ex1(hashBase64DecodedString);

        //Para cada mensagem Sem Peper
        for (int i = 0; i < mensagensSemPeper.size(); i++){

            String mensagemSemPeper = mensagensSemPeper.get(i);

            //============================ 4º - Remove o SALT ============================
            List<String> mensagensSemPeperSemSalt  = desencriptarSalt2SPEx1(mensagemSemPeper);

            //Para cada palavra sem Salt
            for(int j = 0; j < mensagensSemPeperSemSalt.size(); j++){

                String mensagemSemPeperSemSalt = mensagensSemPeperSemSalt.get(j);

                //============================ 5º - Aplica Cifra de César ============================
                for (int k = 1; k <= 25; k++) {
                    int deslocamento = k;
                    mensagensSemPeperSemSaltSemCesar.add(descriptografarCifraCesar(mensagemSemPeperSemSalt, deslocamento));
                }
            }
        }

        List<String> mensagemSemPeperSemSaltSemCesarSemAlfabetoSubstituicao = new ArrayList<>();

        //Para cada palavra sem Peper,Salt e Cifra de César
        for(int i = 0; i < mensagensSemPeperSemSaltSemCesar.size(); i++){

            String mensagemSemPeperSemSaltSemCesar = mensagensSemPeperSemSaltSemCesar.get(i);


            String aplicaAlfabetoSubstituicao = aplicarAlfabetoSubstituicao(mensagemSemPeperSemSaltSemCesar, alfabetoSubstituicao);

            //============================ 6º - Aplica Cifra de César ============================
            mensagemSemPeperSemSaltSemCesarSemAlfabetoSubstituicao.add(aplicaAlfabetoSubstituicao);
        }

        return mensagemSemPeperSemSaltSemCesarSemAlfabetoSubstituicao;
    }
}