import java.nio.ByteBuffer;
import java.util.*;

import static java.util.Collections.swap;

public class Huffman {


    // Функция, возвращающая набор канонических кодов Хаффмана
    public static ArrayList<String> getCanonicalCodes(ArrayList<String> codes) {
        ArrayList<String> canonicalCodes = new ArrayList<>();
        StringBuilder adder = new StringBuilder();
        int current;
        int length;
        for (int i = 0; i < codes.size(); i++) {
            adder.setLength(0);
            if (i == 0) {
                length = codes.get(i).length();
                while (length > 0) {
                    adder.append("0");
                    length--;
                }
                canonicalCodes.add(adder.toString());
            }
            else {
                current = Integer.parseInt(canonicalCodes.get(i-1),2);
                current += 1;
                if (Integer.toBinaryString(current).length() < canonicalCodes.get(i-1).length()) {
                    length = canonicalCodes.get(i-1).length() - Integer.toBinaryString(current).length();
                    while (length > 0) {
                        adder.append("0");
                        length--;
                    }
                }
                adder.append(Integer.toBinaryString(current));
                if (adder.length() < codes.get(i).length()) {
                    length = codes.get(i).length() - adder.length();
                    while (length > 0) {
                        adder.append("0");
                        length--;
                    }
                }
                canonicalCodes.add(adder.toString());
            }
        }

        return canonicalCodes;
    }

    // Функция, возвращающая канонический набор кодов Хаффмана по их длинам
    public static ArrayList<String> getCanonicalCodesByLength(ArrayList<String> lengths) {
        ArrayList<String> canonicalCodes = new ArrayList<>();
        StringBuilder adder = new StringBuilder();
        int current;
        int length;
        for (int i = 0; i < lengths.size(); i++) {
            adder.setLength(0);
            if (i == 0) {
                length = Integer.parseInt(lengths.get(i));
                while (length > 0) {
                    adder.append("0");
                    length--;
                }
                canonicalCodes.add(adder.toString());
            }
            else {
                current = Integer.parseInt(canonicalCodes.get(i-1),2);
                current += 1;
                if (Integer.toBinaryString(current).length() < canonicalCodes.get(i-1).length()) {
                    length = canonicalCodes.get(i-1).length() - Integer.toBinaryString(current).length();
                    while (length > 0) {
                        adder.append("0");
                        length--;
                    }
                }
                adder.append(Integer.toBinaryString(current));
                if (adder.length() < Integer.parseInt(lengths.get(i))) {
                    length = Integer.parseInt(lengths.get(i)) - adder.length();
                    while (length > 0) {
                        adder.append("0");
                        length--;
                    }
                }
                canonicalCodes.add(adder.toString());
            }
        }

        return canonicalCodes;
    }


    static String result;
    static HashMap<Character, String> codes = new HashMap<>();
    public static HashMap<Character, String> HuffmanCoding(String data) {
        ArrayList<Character> symbols = new ArrayList<>();
        ArrayList<Integer> counters = new ArrayList<>();
        // Заполнение массивов букв и частот
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (!symbols.contains(c)) {
                symbols.add(c);
                counters.add(1);
            }
            else {
                int index = symbols.indexOf(c);
                counters.set(index, counters.get(index) + 1);
            }
        }

        List<Map.Entry<Character, Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < symbols.size(); i++) {
            pairs.add(new AbstractMap.SimpleEntry<>(symbols.get(i), counters.get(i)));
        }

        pairs.sort(Map.Entry.comparingByValue());
        Collections.reverse(pairs);

        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();

        for (int i = 0; i < pairs.size(); i++) {
            queue.add(new HuffmanNode(pairs.get(i).getValue(), pairs.get(i).getKey()));
        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, '\0');
            parent.left = left;
            parent.right = right;
            queue.add(parent);
        }

        HuffmanNode root = queue.poll();

        getCodes(root, "", codes);
        return codes;
    }

    public static void getCodes(HuffmanNode root, String code, HashMap<Character, String> codes) {
        if (root == null) {
            return;
        }
        if (root.isLeaf()) {
            codes.put(root.character, code);
        }
        getCodes(root.left, code + "0", codes);
        getCodes(root.right, code + "1", codes);
    }

    public static byte[] binaryStringToBytes(String binaryString) {
        // Дополняем строку до длины, кратной 8, если она не кратна
        while (binaryString.length() % 8 != 0) {
            binaryString = "0" + binaryString;
        }

        byte[] bytes = new byte[binaryString.length() / 8];
        for (int i = 0; i < bytes.length; i++) {
            String byteString = binaryString.substring(i * 8, (i + 1) * 8);
            bytes[i] = (byte) Integer.parseInt(byteString, 2);
        }
        return bytes;
    }

    public static byte[] getHuffmanString(String data, HashMap<Character, String> codes) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            result.append(codes.get(data.charAt(i)));
        }

        byte[] bytes = binaryStringToBytes(result.toString());
        StringBuilder finalResult = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            finalResult.append(bytes[i]);
        }
        return bytes;
    }

    public static HashMap<String, Character> createReverseCodes(HashMap<Character, String> codes) {
        HashMap<String, Character> reverseCodes = new HashMap<>();
        for (HashMap.Entry<Character, String> entry : codes.entrySet()) {
            reverseCodes.put(entry.getValue(), entry.getKey());
        }
        return reverseCodes;
    }

    public static String decodeHuffmanString(byte[] encodedData, HashMap<String, Character> reverseCodes) {
        StringBuilder binaryString = new StringBuilder();
        for (byte b : encodedData) {
            binaryString.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < binaryString.length(); i++) {
            codeBuilder.append(binaryString.charAt(i));
            if (reverseCodes.containsKey(codeBuilder.toString())) {
                result.append(reverseCodes.get(codeBuilder.toString()));
                codeBuilder.setLength(0);
            }
        }
        return result.toString();
    }

    public static String HuffmanDecoding(String resultHuffman, HashMap<Character, String> codes) {
        StringBuilder data = new StringBuilder();
        int i = 0;
        while(i < resultHuffman.length()) {
            int adder = 1;
            while (!codes.containsValue(resultHuffman.substring(i, i+adder)) && i+adder < resultHuffman.length()) {
                adder++;
            }
            for (Map.Entry<Character, String> entry : codes.entrySet()) {
                if (entry.getValue().equals(resultHuffman.substring(i,i+adder))) {
                    data.append(entry.getKey());
                    break;
                }
            }
            i += adder;
        }
        System.out.println(data);
        return data.toString();
    }

    
}
