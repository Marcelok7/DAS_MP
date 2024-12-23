import View.Menu;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        new Menu();
/*
            String textoCifrado = "FPG FB NBVPKFB DM SBEMDM GUCDKDG. UGD MUSPMMDB EFLEFEQD CP "
                    + "PGIDEXDCBKPM “DHKPMPFQBU‑GP SUGHKEGPFQBM”, PXPKSESEB SURB HKBHBMEQB "
                    + "FUFSD SBGHKPPFCE. CP HDKQP D HDKQP FDB ADVED FDCD D CEZPK. LDZEDG‑MP "
                    + "PMNDKPM DGDVPEM P DNUPFQDVD‑MP CPZ GEFUQBM CP UG MEOPFSEB HPFBMB. FB "
                    + "DUNP CD PXSEQDSDB CB BUQBFB CP, MUKNEKDG UGDM HPKMBFDNPFM CP BKENPG "
                    + "BIMSUKD, SBG JUPG PFQKPQEVP SBFSEOEDIUOBM PG KPMQDUKDFQPM PXSPFQKESBM. "
                    + "PMMPM QKDZEDG UGD HPKNUFQD QPKKEVPO: “B JUP P JUP VDE MUSPCPK?” "
                    + "KPMHBFCE‑OAPM JUP FDB MDIED P POPM BOADKDG HDKD B HKDQB SBG "
                    + "EFJUEPQDSDB.";

            // Lista de alfabetos de substituição
            List<String> alfabetosSubstituicao = new ArrayList<>();
            alfabetosSubstituicao.add("ZYXWVUTSRQPONMLKJIHGFEDCBA"); // Exemplo 1
            alfabetosSubstituicao.add("QWERTYUIOPLKJHGFDSAZXCVBNM"); // Exemplo 2
            alfabetosSubstituicao.add("ABCDEFGHIJKLMNOPQRSTUVWXYZ"); // Exemplo 3 (nenhuma substituição)
            alfabetosSubstituicao.add("AEOISRNTULCDMPVGHQBFZJXKWY");


            // Processa o texto para cada alfabeto
            for (int i = 0; i < alfabetosSubstituicao.size(); i++) {
                System.out.println("Substituição com Alfabeto #" + (i + 1));
                String textoDecifrado = substituirTexto(textoCifrado, alfabetosSubstituicao.get(i));
                System.out.println(textoDecifrado);
                System.out.println("--------------------------------------------------");
            }
        }

        public static String substituirTexto(String texto, String alfabetoSubstituicao) {
            // Alfabeto padrão
            String alfabetoPadrao = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            // Cria um mapa de substituição
            Map<Character, Character> mapaSubstituicao = new HashMap<>();
            for (int i = 0; i < alfabetoPadrao.length(); i++) {
                mapaSubstituicao.put(alfabetoPadrao.charAt(i), alfabetoSubstituicao.charAt(i));
            }

            // Constrói o texto decifrado
            StringBuilder textoDecifrado = new StringBuilder();
            for (char c : texto.toCharArray()) {
                if (mapaSubstituicao.containsKey(c)) {
                    textoDecifrado.append(mapaSubstituicao.get(c)); // Substitui se está no alfabeto
                } else {
                    textoDecifrado.append(c); // Mantém os outros caracteres
                }
            }

            return textoDecifrado.toString();
        }
        */
    }
}