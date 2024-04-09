import java.lang.reflect.Array;
import java.util.ArrayList;


public class Suffixes {

    public static ArrayList<Integer> getSuffixArray(String data) {
        ArrayList<Integer> indices = new ArrayList<>();
        ArrayList<String> suffixes = new ArrayList<>();

        for (int i = 0; i < data.length(); i++) {
            suffixes.add(data.substring(i, data.length()));
            indices.add(i);
        }
        for (int i = 0; i < suffixes.size(); i++) {
            for (int j = i + 1; j < suffixes.size(); j++) {
               if (suffixes.get(i).compareTo(suffixes.get(j)) > -1) {
                  String tmp = suffixes.get(i);
                  suffixes.set(i, suffixes.get(j));
                  suffixes.set(j, tmp);
                  int index = indices.get(i);
                  indices.set(i, indices.get(j));
                  indices.set(j, index);
               }
           }
       }

        return indices;
    }

    public static ArrayList<String> getSuffixTypes(String data) {
        ArrayList<String> suffixes = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            suffixes.add("0");
        }
        suffixes.set(data.length()-1, "S");
        suffixes.set(data.length()-2, "L");
        for (int i = data.length()-3; i > -1; i--) {
            if (data.charAt(i) < data.charAt(i+1)) {
                suffixes.set(i, "S");
            }
            if (data.charAt(i) > data.charAt(i+1)) {
                suffixes.set(i, "L");
            }
            if (data.charAt(i) == data.charAt(i+1)) {
                suffixes.set(i, suffixes.get(i+1));
            }
        }
        return suffixes;
    }
}
