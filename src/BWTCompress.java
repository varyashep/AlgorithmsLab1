import java.lang.reflect.Array;
import java.rmi.ConnectIOException;
import java.util.*;

public class BWTCompress {

    public static String InverseBWT(String resultString){
        String originalString = resultString;
        String creationLine = "";
        String[] sbsCreationArray = new String[originalString.length()];
        for (int i = 0; i < sbsCreationArray.length; i++) {
            sbsCreationArray[i] = "";
        }

        char[] charArray = resultString.toCharArray();

        int lengthOrigin = resultString.length();
        while (lengthOrigin > 0) {
            for (int i = 0; i < resultString.length(); i++) {
                sbsCreationArray[i] = resultString.substring(i, i + 1) + sbsCreationArray[i];
            }
            Arrays.sort(sbsCreationArray);
            lengthOrigin--;
        }
        //System.out.println("4) Восстановленный массив из преобразованной строки: \n" + Arrays.toString(sbsCreationArray));

        for (int i = 0; i < sbsCreationArray.length; i++) {
            String currentString =  sbsCreationArray[i];
            String lastSymbol = currentString.substring(resultString.length() - 1);
            if (lastSymbol.equals("$")){
                creationLine = (sbsCreationArray[i]).substring(0,resultString.length()-1);
                //System.out.println("5) Результат ОБРАТНОГО преобразования: \n" + creationLine);
            }
        }

        return creationLine;
    }


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



}
