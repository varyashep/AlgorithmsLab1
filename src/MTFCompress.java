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


    public static String MTF(String input, LinkedList<Character> alphabet) {
        // получаем алфавит по исходной строке
        String result = "";
        int index;
        for (int i = 0; i < input.length(); i++) {
            index = alphabet.indexOf(input.charAt(i));
            result += Integer.toString(index);
            alphabet.remove(index);
            alphabet.addFirst(input.charAt(i));
        }
        return result;
    }

    public static String rMTF(String input, LinkedList<Character> alphabet) {
        String result = "";
        Character character;
        for (int i = 0; i < input.length(); i++) {
            character = alphabet.get(Integer.parseInt(String.valueOf(input.charAt(i))));
            result += String.valueOf(character);
            alphabet.remove(Integer.parseInt(String.valueOf(input.charAt(i))));
            alphabet.addFirst(character);
        }

        return result;
    }
}
