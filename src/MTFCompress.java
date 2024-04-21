import java.util.LinkedList;

public class MTFCompress {

    // Метод для получения алфавита по исходной строке
    public static LinkedList<Character> getAlphabet(String input) {
        LinkedList<Character> alphabet = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            if (!alphabet.contains(input.charAt(i))) {
                alphabet.add(input.charAt(i));
            }
        }
        alphabet.sort(Character::compareTo);
        return alphabet;
    }


    public static StringBuilder MTF(String input, LinkedList<Character> alphabet) {
        // получаем алфавит по исходной строке
        StringBuilder result = new StringBuilder();
        int index;
        for (int i = 0; i < input.length(); i++) {
            index = alphabet.indexOf(input.charAt(i));
            result.append(Integer.toString(index));
            result.append(" ");
            alphabet.remove(index);
            alphabet.addFirst(input.charAt(i));
        }
        if (result.length() > 0 && result.charAt(result.length() - 1) == ' ') {
            result.deleteCharAt(result.length() - 1);
        }
        return result;
    }

    public static String rMTF(String encoded, LinkedList<Character> alphabet) {
        StringBuilder result = new StringBuilder();
        StringBuilder indexBuilder = new StringBuilder();

        for (int i = 0; i < encoded.length(); i++) {
            char currentChar = encoded.charAt(i);
            if (currentChar == ' ' && indexBuilder.length() > 0 && !indexBuilder.toString().contains(" ")) { // Предполагаем, что разделитель - пробел
                int index;
                    index = Integer.parseInt(indexBuilder.toString());
                    char character = alphabet.get(index);
                    result.append(character);
                    alphabet.remove(index);
                    alphabet.addFirst(character);
                    indexBuilder.setLength(0); // Очищаем StringBuilder для следующего индекса
            } else {
                indexBuilder.append(currentChar);
            }
        }

        // Обработка последнего индекса, если строка не заканчивается пробелом
        if (indexBuilder.length() > 0 && !indexBuilder.toString().contains(" ")) {
            int index = Integer.parseInt(indexBuilder.toString());
            char character = alphabet.get(index);
            result.append(character);
            alphabet.remove(index);
            alphabet.addFirst(character);
        }

        return result.toString();
    }
}
