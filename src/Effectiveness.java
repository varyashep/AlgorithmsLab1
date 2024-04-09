import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class Effectiveness {


    public static void lengthDependBWT() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("test.txt"), "windows-1251"));
        RLECompress.compressText(br);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt"), "windows-1251"));
        String result = br2.readLine();
        FileWriter fw = new FileWriter("compressedTextFinal.txt");
        fw.write(BWTCompress.BWT(result));
        fw.close();
    }

    public static void countAC() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt")));
        String line = br.readLine();
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("compressedTextFinal.txt"));
        while (line != null) {
            int curLength = 0;
            double result;
            while (curLength + 10 <= line.length()) {
                double[] probabilities = ArithmeticCoding.getProbabilities(line.substring(curLength, curLength+10));
                result = ArithmeticCoding.arithmeticCoding(line.substring(curLength, curLength+10),probabilities);
                BigDecimal bigDecimal = new BigDecimal(Double.toString(result));
                String decimalPart = bigDecimal.subtract(new BigDecimal(bigDecimal.longValue())).toPlainString().substring(2);
                curLength += 10;
                dos.writeLong(Long.parseLong(decimalPart));
                //dos.writeChar(' ');
            }
            if (line.length() - curLength > 0) {
                double[] probabilities = ArithmeticCoding.getProbabilities(line.substring(curLength, line.length()));
                result = ArithmeticCoding.arithmeticCoding(line.substring(curLength, line.length()),probabilities);
                BigDecimal bigDecimal = new BigDecimal(Double.toString(result));
                String decimalPart = bigDecimal.subtract(new BigDecimal(bigDecimal.longValue())).toPlainString().substring(2);
                dos.writeLong(Long.parseLong(decimalPart));
                //dos.writeChar(' ');
            }

            line = br.readLine();
            dos.writeChar('\n');
        }
        dos.close();
    }

    public static void countBwtMtfAc() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("rusTolstoy.txt"), "windows-1251"));
        String line = br.readLine();
        String result = "";
        while (line != null) {
            result += line;
            line = br.readLine();
        }
        result = MTFCompress.MTF(BWTCompress.efficientBWT(result), MTFCompress.getAlphabet(BWTCompress.efficientBWT(result)));
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("compressedText.txt"));
        int curLength = 0;
        double resultAC;
        while (curLength + 10 <= result.length()) {
            double[] probabilities = ArithmeticCoding.getProbabilities(result.substring(curLength, curLength + 10));
            resultAC = ArithmeticCoding.arithmeticCoding(result.substring(curLength, curLength + 10), probabilities);
            BigDecimal bigDecimal = new BigDecimal(Double.toString(resultAC));
            String decimalPart = bigDecimal.subtract(new BigDecimal(bigDecimal.longValue())).toPlainString().substring(2);
            curLength += 10;
            dos.writeLong(Long.parseLong(decimalPart));
            //dos.writeChar(' ');
        }
        if (result.length() - curLength > 0) {
            double[] probabilities = ArithmeticCoding.getProbabilities(result.substring(curLength, result.length()));
            resultAC = ArithmeticCoding.arithmeticCoding(result.substring(curLength, result.length()), probabilities);
            BigDecimal bigDecimal = new BigDecimal(Double.toString(resultAC));
            String decimalPart = bigDecimal.subtract(new BigDecimal(bigDecimal.longValue())).toPlainString().substring(2);
            dos.writeLong(Long.parseLong(decimalPart));
            //dos.writeChar(' ');
        }
        dos.close();
    }


    public static void countBwtMtfRLEHa() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("picture2.txt"), "windows-1251"));
        String line = br.readLine();
        String result = "";
        while (line != null) {
            result += line;
            line = br.readLine();
        }
        result = MTFCompress.MTF(BWTCompress.efficientBWT(result), MTFCompress.getAlphabet(BWTCompress.efficientBWT(result)));
        FileWriter fw = new FileWriter("compressedText.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        fw.write(result);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt"), "windows-1251"));
        RLECompress.compressText(br2);
        BufferedReader br3 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedTextFinal.txt"), "windows-1251"));
        line = br3.readLine();
        String text = "";
        while (line != null) {
            text += line;
            br3.readLine();
        }
        HashMap<Character, String> codes = Huffman.HuffmanCoding(text);
        byte[] resultBytes = Huffman.getHuffmanString(text, codes);
        FileOutputStream fw2 = new FileOutputStream("compressedTextVeryFinal.txt");
        fw2.write(resultBytes);
        fw2.close();
    }

    public static void countBwtMtfRLEAc() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("rusTolstoy.txt"), "windows-1251"));
        String line = br.readLine();
        String result = "";
        while (line != null) {
            result += line;
            line = br.readLine();
        }
        result = MTFCompress.MTF(BWTCompress.efficientBWT(result), MTFCompress.getAlphabet(BWTCompress.efficientBWT(result)));
        FileWriter fw = new FileWriter("compressedText.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        fw.write(result);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt"), "windows-1251"));
        RLECompress.compressText(br2);
        BufferedReader br3 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedTextFinal.txt"), "windows-1251"));
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("compressedTextVeryFinal.txt"));
        result = br3.readLine();
        int curLength = 0;
        double resultAC;
        while (curLength + 10 <= result.length()) {
            double[] probabilities = ArithmeticCoding.getProbabilities(result.substring(curLength, curLength + 10));
            resultAC = ArithmeticCoding.arithmeticCoding(result.substring(curLength, curLength + 10), probabilities);
            BigDecimal bigDecimal = new BigDecimal(Double.toString(resultAC));
            String decimalPart = bigDecimal.subtract(new BigDecimal(bigDecimal.longValue())).toPlainString().substring(2);
            curLength += 10;
            dos.writeLong(Long.parseLong(decimalPart));
            //dos.writeChar(' ');
        }
        if (result.length() - curLength > 0) {
            double[] probabilities = ArithmeticCoding.getProbabilities(result.substring(curLength, result.length()));
            resultAC = ArithmeticCoding.arithmeticCoding(result.substring(curLength, result.length()), probabilities);
            BigDecimal bigDecimal = new BigDecimal(Double.toString(resultAC));
            String decimalPart = bigDecimal.subtract(new BigDecimal(bigDecimal.longValue())).toPlainString().substring(2);
            dos.writeLong(Long.parseLong(decimalPart));
            //dos.writeChar(' ');
        }
        dos.close();
    }
    public static void countLZ77() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("enwik7.txt"), "windows-1251"));
        String line = br.readLine();
        FileWriter fw = new FileWriter("compressedText.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        while (line != null) {
            List<Object> result = LZ77.LZ77Compress(line);
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i) instanceof LZ77Tuple) {
                    bw.write(String.valueOf(((LZ77Tuple) result.get(i)).length) + String.valueOf(((LZ77Tuple) result.get(i)).offset) + String.valueOf(((LZ77Tuple) result.get(i)).nextChar));
                }
                else {
                    bw.write(result.get(i).toString());
                }
            }
            //bw.write(result.toString());
            bw.newLine();
            line = br.readLine();
        }
        bw.close();
    }


    public static void countLZ77Ha() throws Exception{
        countLZ77();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt")));
        String line = br.readLine();
        String text = "";
        while (line != null) {
            text += line;
            line = br.readLine();
        }
        FileOutputStream fw = new FileOutputStream("compressedTextFinal.txt");
        HashMap<Character, String> codes = Huffman.HuffmanCoding(text);
        byte[] resultBytes = Huffman.getHuffmanString(text,codes);
        fw.write(resultBytes);
        fw.close();
    }

    public static void countLZ77Ac() throws Exception {
        countLZ77();
        countAC();
    }
}
