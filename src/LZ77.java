import java.util.ArrayList;
import java.util.List;

public class LZ77 {

    public static List<Object> LZ77Compress(String data) {
        List<Object> result = new ArrayList<>();
        int bufferSize = 500;
        int dataIndex = 0;
        while (dataIndex < data.length()) {
            int matchLength = 0;
            int matchOffset = 0;
            char nextChar = '\0';
            for (int i = Math.max(0, dataIndex - bufferSize); i < dataIndex; i++) {
                int j = 0;
                while (dataIndex + j < data.length() && data.charAt(i + j) == data.charAt(dataIndex + j)) {
                    j++;
                }
                if (j > matchLength) {
                    matchLength = j;
                    matchOffset = dataIndex - i;
                }
                if (j == matchLength && dataIndex - i < matchOffset) {
                    matchOffset = dataIndex - i;
                }
            }
            if (dataIndex + matchLength < data.length()) {
                nextChar = data.charAt(dataIndex + matchLength);
            }
            if (matchOffset == 0 && matchLength == 0) {
                result.add(nextChar);
            } else {
                result.add(new LZ77Tuple(matchOffset, matchLength, nextChar));
            }
            dataIndex += matchLength + 1;
        }
        return result;
    }

    public static String LZ77Decompress(List<Object> compressed) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < compressed.size(); i++) {
            if (compressed.get(i) instanceof LZ77Tuple) {
                LZ77Tuple tuple = (LZ77Tuple) compressed.get(i);
                int start = result.length() - tuple.offset;
                if (start + tuple.length <= result.length()) {
                    result.append(result.substring(start, start+tuple.length));
                }
                else {
                    int j = tuple.length;
                    while (j > 0) {
                        int curLength = result.length() - start;
                        if (j < curLength) {
                            curLength = start + j;
                        }
                        else {
                            curLength = result.length();
                        }
                        result.append(result.substring(start, curLength));
                        j -= curLength - start;
                    }
                }
                if (tuple.nextChar != '\0') {
                    result.append(tuple.nextChar);
                }
            }
            else {
                result.append(compressed.get(i));
            }
        }
        return result.toString();
    }


}
