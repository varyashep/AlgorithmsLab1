import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ArithmeticCoding {


    public static double[] getProbabilities(String data) {
        ArrayList<Character> letters = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            if (!letters.contains(data.charAt(i))) {
                letters.add(data.charAt(i));
            }
        }
        letters.sort(Character::compareTo);
        double[] probabilities = new double[letters.size()];
        HashMap<Character, Integer> alphabet = new HashMap<>();
        for (int i = 0; i < data.length(); i++) {
            alphabet.put(data.charAt(i), alphabet.getOrDefault(data.charAt(i),0) + 1);
        }
        for (int i = 0; i < letters.size(); i++) {
            probabilities[i] = (double) alphabet.get(letters.get(i)) / data.length();
        }
        return probabilities;

    }
    // Наивный алгоритм арифметического кодирования
    public static double arithmeticCoding(String data, double[] probabilities) {
        ArrayList<Character> letters = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            if (!letters.contains(data.charAt(i))) {
                letters.add(data.charAt(i));
            }
        }
        double[] intervals = new double[probabilities.length + 1];
        double sum = 0;
        for (int i = 0; i < intervals.length-1; i++) {
            intervals[i] += sum;
            sum += probabilities[i];
        }
        intervals[intervals.length-1] = sum;
        double L = 0;
        double R = 1;
        double curLeft;
        for (int i = 0; i < data.length(); i++) {
            double length = R - L;
            curLeft = L;
            L = L + intervals[letters.indexOf(data.charAt(i))]*length;
            R = curLeft + intervals[letters.indexOf(data.charAt(i)) + 1]*length;
            if (L == R) {
                break;
            }
        }
        return (R+L)/2;
    }

    public static String arithmeticDecoding(double code, double[] probabilities, int dataSize) {
        ArrayList<Character> letters = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            letters.add((char) (97 + i));
        }
        double[] intervals = new double[probabilities.length + 1];
        double sum = 0;
        for (int i = 0; i < intervals.length-1; i++) {
            intervals[i] += sum;
            sum += probabilities[i];
        }
        intervals[intervals.length-1] = sum;
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < intervals.length-1; j++) {
                if (code >= intervals[j] && code < intervals[j+1]) {
                    data.append(letters.get(j));
                    code = (code - intervals[j]) / (intervals[j+1] - intervals[j]);
                    break;
                }
            }
        }
        return data.toString();
    }

}
