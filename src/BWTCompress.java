import java.lang.reflect.Array;
import java.rmi.ConnectIOException;
import java.util.*;

public class BWTCompress {

    public static String BWT(String input) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(input);
        String shift;
        // Циклические сдвиги
        for (int i = 1; i < input.length(); i++) {
            shift = input.substring(i) + input.substring(0,i);
            strings.add(shift);
        }
        // Сортировка строк
        strings.sort(String::compareTo);
        ArrayList<String> result = new ArrayList<>();
        int index = 0;
        String lastRow = "";
        // Вывод последнего столбца и номера исходной строки
        for (int i = 0; i < strings.size(); i++) {
            if (Objects.equals(strings.get(i), input)) {
               index += i + 1;
            }
            lastRow += strings.get(i).substring(input.length()-1);
        }
        result.add(lastRow);
        result.add(Integer.toString(index));
        String finalResult = result.get(0) + result.get(1);
        return finalResult;
    }

    public static String rBWT(ArrayList<String> input) {
        String result;
        ArrayList<String> strings = new ArrayList<>();
        String inputString = input.get(0);
        String current;
        for (int i = 0; i < inputString.length(); i++) {
            int index = 0;
            for (int j = 0; j < inputString.length(); j++) {
                if (i > 0) {
                    current = inputString.substring(j,j+1) + strings.get(j);
                    strings.set(index, current);
                }
                else {
                    current = inputString.substring(j,j+1);
                    strings.add(index, current);
                }
                index++;
            }
            strings.sort(String::compareTo);
        }
        int finalIndex = Integer.parseInt(input.get(1));
        result = strings.get(finalIndex-1);
        return result;
    }

    public static String optimizedRevBWT(ArrayList<String> input) {
        String bwtResult = input.get(0);
        int stringNum = Integer.parseInt(input.get(1))-1;
        ArrayList<Letters> data = new ArrayList<>();
        for (int i = 0; i < bwtResult.length(); i++) {
            data.add(new Letters(bwtResult.charAt(i), i));
        }
        Collections.sort(data);
        for (int i = 0 ; i < data.size(); i++) {
            System.out.println(data.get(i).letter + " " + data.get(i).number);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bwtResult.length(); i++) {
            stringNum = data.get(stringNum).number;
            result.append(bwtResult.charAt(stringNum));
        }

        return result.toString();
    }
    public static double avLength(String input) {
        float average;
        int countSubstrings = 0;
        int lengthSubstrings = 0;
        boolean repeated = false;
        for (int i = 0; i < input.length()-1; i++) {
            if (input.charAt(i) == input.charAt(i+1) && !repeated) {
                lengthSubstrings += 2;
                countSubstrings++;
                repeated = true;
            } else if (input.charAt(i) == input.charAt(i+1) && repeated) {
                lengthSubstrings++;
            }
            else {
                repeated = false;
            }
        }
        average = (float) (lengthSubstrings - 2 * countSubstrings) /input.length();
        return average;
    }

    public static String suffixBWT(String data, ArrayList<Integer> indices) {

        ArrayList<Character> result = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            if (indices.get(i) == 0) {
                result.add(data.charAt(data.length()-1));
            } else {
                result.add(data.charAt(indices.get(i)-1));
            }
        }

        String bwtResult = "";
        for (int i = 0; i < result.size(); i++) {
            bwtResult += result.get(i);
        }
        return bwtResult;
    }

    public static String efficientBWT(String s) {
        if (s.length() >= 1) {
            s+="$";
            StringBuilder bwt = new StringBuilder();
            int[] suffixArray = compress(s);
            for (int i : suffixArray) {
                int j = i - 1;
                if (j < 0) {
                    j += suffixArray.length;
                }
                bwt.append(s.charAt(j));
            }
            return bwt.toString();
        }
        return "";
    }

    public static int[] compress(String s){

        int N = s.length();

        int steps = Integer.bitCount(Integer.highestOneBit(N) - 1);

        int rank[][] = new int[steps + 1][N];

        for (int i = 0; i < N; i++) {
            rank[0][i] = s.charAt(i) - 'a';
        }

        Tuple[] tuples = new Tuple[N];

        for (int step = 1, cnt = 1; step <= steps; step++, cnt <<= 1) {
            for (int i = 0; i < N; i++) {
                Tuple tuple = new Tuple();
                tuple.firstHalf = rank[step - 1][i];
                tuple.secondHalf = i + cnt < N ? rank[step - 1][i + cnt] : -1;
                tuple.originalIndex = i;

                tuples[i] = tuple;
            }

            Arrays.sort(tuples);
            if (tuples[0] != null) {
                rank[step][tuples[0].originalIndex] = 0;
            }

            for (int i = 1, currRank = 0; i < N; i++) {
                if(!tuples[i - 1].firstHalf.equals(tuples[i].firstHalf)
                        || tuples[i - 1].secondHalf.equals(tuples[i].secondHalf)) {
                    ++currRank;
                }
                if (tuples[i] != null) {
                    rank[step][tuples[i].originalIndex] = currRank;
                }
            }


        }

        int[] suffixArray = new int[N];

        for (int i = 0; i < N; i++) {
            suffixArray[i] = tuples[i].originalIndex;
        }

        return suffixArray;
    }

}
