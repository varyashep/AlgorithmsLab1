import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Effectiveness {


    public static double getEntropy() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("enwik7.txt")));
        StringBuilder text = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            text.append(line);
            line = br.readLine();
        }
        HashMap<Character, Integer> symbols = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            symbols.put(text.charAt(i), symbols.getOrDefault(text.charAt(i), 0) + 1);
        }
        ArrayList<Integer> frequencies = new ArrayList<>(symbols.values());
        double[] probabilities = new double[symbols.size()];
        for (int i = 0; i < symbols.size(); i++) {
            probabilities[i] = (double) frequencies.get(i) / text.length();
        }
        double entropy = Probabilites.getEntropy(probabilities);
        return entropy;
    }
    // RLE
    public static void countRLE() throws Exception {
        System.out.println("Выберите вид изображения:");
        System.out.println("1. Монохромное черно-белое");
        System.out.println("2. Черно-белое");
        System.out.println("3. Цветное");
        System.out.println("4. Текст");
        System.out.println("5. Enwik7");
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        switch (choice) {
            case 1:
                BufferedImage bImageMono = ImageIO.read(new File("bw_pic.jpg"));
                int[][] picData = RLECompress.convertMono(bImageMono);
                RLECompress.compressMono(picData, bImageMono);
                FileReader fr = new FileReader("pictureCompressed.txt");
                BufferedReader compressedPic = new BufferedReader(fr);
                RLECompress.decompressMono(bImageMono, compressedPic);
                break;
            case 2:
                BufferedImage bImageBW = ImageIO.read(new File("rose.jpg"));
                int[][] BWpicData = RLECompress.convertBW(bImageBW);
                FileReader fr2 = new FileReader("pictureBWCompressed.txt");
                BufferedReader compressedBW = new BufferedReader(fr2);
                RLECompress.compressBW(BWpicData, bImageBW);
                RLECompress.decompressBW(bImageBW, compressedBW);
                break;
            case 3:
                BufferedImage bImage = ImageIO.read(new File("coloredCats.jpg"));
                RLECompress.convertToRGBColor(bImage);
                FileReader fr3 = new FileReader("picture.txt");
                BufferedReader br3 = new BufferedReader(fr3);
                RLECompress.compressColor(br3);
                FileReader fr4 = new FileReader("coloredCompressed.txt");
                BufferedReader compressedColor = new BufferedReader(fr4);
                RLECompress.decompressColor(compressedColor);
                break;
            case 4:
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("rusTolstoy.txt"), "windows-1251"));
                RLECompress.compressText(br);
                BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt"), "windows-1251"));
                RLECompress.decompressText(br2);
                break;
            case 5:
                BufferedReader br5 = new BufferedReader(new InputStreamReader(new FileInputStream("enwik7.txt")));
                RLECompress.compressText(br5);
                BufferedReader br6 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt")));
                RLECompress.decompressText(br6);
                break;
        }
    }

    // BWT + RLE
    public static void countBWTRLE() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("1000000.txt")));
        StringBuilder text = new StringBuilder();
        String line;
        line = br.readLine();
        while (line != null) {
            text.append(line);
            line = br.readLine();
        }
        //System.out.println(text);
        StringBuilder result = BWTFast.getBWT(text.toString());
        System.out.println("+");
//        FileWriter fw = new FileWriter("midFile.txt");
//        fw.write(result.toString());
//        fw.close();
//        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("midFile.txt"), "windows-1251"));
//        //BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("midFile.txt")));
//        RLECompress.compressText(br2);
//        System.out.println("+");
//        BufferedReader br3 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt"), "windows-1251"));
//        //BufferedReader br3 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt")));
//        RLECompress.decompressText(br3);
//        System.out.println("+");
//        BufferedReader br4 = new BufferedReader(new InputStreamReader(new FileInputStream("decompressedText.txt"), "windows-1251"));
//        //BufferedReader br4 = new BufferedReader(new InputStreamReader(new FileInputStream("decompressedText.txt")));
//        line = br4.readLine();
//        StringBuilder decompressedText = new StringBuilder();
//        while (line != null) {
//            decompressedText.append(line);
//            line = br4.readLine();
//        }
//        StringBuilder decompressResult = BWTFast.reverseBWT(decompressedText.toString());
//        //System.out.println(decompressedText);
//        System.out.println("+");
//        FileWriter fw2 = new FileWriter("midFile.txt");
//        fw2.write(decompressResult.toString());
//        fw2.close();

        String encoded = RLECompress.RLECoder(result).toString();
        System.out.println("+");
        FileWriter fw = new FileWriter("compressedText.txt");
        fw.write(encoded);
        fw.close();
        String decoded = RLECompress.RLEDecoder(encoded).toString();
        System.out.println("+");
        String finalDecoded = BWTFast.reverseBWT(decoded).toString();
        System.out.println("+");
        FileWriter fw2 = new FileWriter("decompressedText.txt");
        fw2.write(finalDecoded);
        fw2.close();
    }

    public static void lengthDependBWT() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("test.txt"), "windows-1251"));
        RLECompress.compressText(br);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt"), "windows-1251"));
        String result = br2.readLine();
        FileWriter fw = new FileWriter("compressedTextFinal.txt");
        fw.write(BWTCompress.BWT(result));
        fw.close();
    }

    public static byte[] convertDoubleArrayListToByteArray(ArrayList<Double> doubleList) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Double.BYTES * doubleList.size());
        for (Double value : doubleList) {
            byteBuffer.putDouble(value);
        }
        return byteBuffer.array();
    }

    // ARITHMETIC CODING
    public static void countAC() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("midFile.txt")));
        String line = br.readLine();
        ArrayList<Double> results = new ArrayList<>();
        FileOutputStream fw = new FileOutputStream("compressedText.txt");
        FileWriter fw2 = new FileWriter("midFile.txt");
        String decodedResult;
        while (line != null) {
            int curLength = 0;
            double result;
            while (curLength + 10 <= line.length()) {
                double[] probabilities = ArithmeticCoding.getProbabilities(line.substring(curLength, curLength+10));
                result = ArithmeticCoding.arithmeticCoding(line.substring(curLength, curLength+10),probabilities);
                results.add(result);
                decodedResult = ArithmeticCoding.arithmeticDecoding(result, probabilities, line.substring(curLength, curLength+10).length(), ArithmeticCoding.getAlphabet(line.substring(curLength, curLength+10)));
                fw2.write(decodedResult);
                curLength += 10;
            }
            if (line.length() - curLength > 0) {
                double[] probabilities = ArithmeticCoding.getProbabilities(line.substring(curLength, line.length()));
                result = ArithmeticCoding.arithmeticCoding(line.substring(curLength, line.length()),probabilities);
                results.add(result);
                decodedResult = ArithmeticCoding.arithmeticDecoding(result, probabilities, line.substring(curLength, line.length()).length(), ArithmeticCoding.getAlphabet(line.substring(curLength, line.length())));
                fw2.write(decodedResult);
            }
            line = br.readLine();
            fw2.write('\n');
        }
        byte[] compressedData = convertDoubleArrayListToByteArray(results);
        fw.write(compressedData);
        fw2.close();
        fw.close();
    }

    // HUFFMAN
    public static void countHa() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt")));
        StringBuilder text = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            text.append(line);
            line = br.readLine();
        }
        HashMap<Character, String> codes = Huffman.HuffmanCoding(text.toString());
        byte[] result = Huffman.getHuffmanString(text.toString(), codes);
        FileOutputStream fw = new FileOutputStream("compressedTextFinal.txt");
        fw.write(result);
        fw.close();
        byte[] fileContent = Files.readAllBytes(Path.of("compressedTextFinal.txt"));
        HashMap<String, Character> reverseCodes = Huffman.createReverseCodes(codes);
        //System.out.println(reverseCodes);
        String encodedString = Huffman.decodeHuffmanString(fileContent, reverseCodes);
        FileWriter fw2 = new FileWriter("midFile.txt");
        fw2.write(encodedString);
        fw2.close();
    }

    // BWT + MTF + HA

    public static void countBwtMtfHa() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("enwik7.txt")));
        String line = br.readLine();
        StringBuilder text = new StringBuilder();
        while (line != null) {
            text.append(line);
            line = br.readLine();
        }
        StringBuilder result = BWTFast.getBWT(text.toString());
        System.out.println("BWT finished");
        String getMTF = MTFCompress.MTF(result.toString(), MTFCompress.getAlphabet(result.toString())).toString();
        FileWriter fw = new FileWriter("midFile.txt");
        fw.write(getMTF);
        fw.close();
        System.out.println("MTF finished");
        countHa();
        System.out.println("Huffman finished");
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("midFile.txt")));
        String encodedLine = br2.readLine();
        String decodedMTF = MTFCompress.rMTF(encodedLine, MTFCompress.getAlphabet(result.toString()));
        String decodedBWT = BWTFast.reverseBWT(decodedMTF).toString();
        FileWriter fw2 = new FileWriter("decompressedText.txt");
        fw2.write(decodedBWT);
        fw2.close();
    }

    // BWT + MTF + RLE + HA

    public static void countBwtMtfRLEHa() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("enwik7.txt")));
        String line = br.readLine();
        StringBuilder text = new StringBuilder();
        while (line != null) {
            text.append(line);
            line = br.readLine();
        }
        StringBuilder result = BWTFast.getBWT(text.toString());
        System.out.println("BWT done");
        StringBuilder getMTF = MTFCompress.MTF(result.toString(), MTFCompress.getAlphabet(result.toString()));
        System.out.println("MTF done");
        String encodeRLE = RLECompress.RLECoder(getMTF).toString();
        FileWriter fw = new FileWriter("compressedText.txt");
        fw.write(encodeRLE);
        System.out.println("RLE done");
        fw.close();
        countHa();
        System.out.println("Huffman done");
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("midFile.txt")));
        String encoded = br2.readLine();
        //System.out.println(encoded);
        String decodedRLE = RLECompress.RLEDecoder(encoded).toString();
        //System.out.println(decodedRLE);
        String decodedMTF = MTFCompress.rMTF(getMTF.toString(), MTFCompress.getAlphabet(result.toString()));
        String decodedBWT = BWTFast.reverseBWT(decodedMTF).toString();
        FileWriter fw2 = new FileWriter("decompressedText.txt");
        fw2.write(decodedBWT);
        fw2.close();
    }

    public static void countBwtMtfAc() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("rusTolstoy.txt"), "windows-1251"));
        String line = br.readLine();
        StringBuilder text = new StringBuilder();
        while (line != null) {
            text.append(line);
            line = br.readLine();
        }
        StringBuilder result = BWTFast.getBWT(text.toString());
        System.out.println("BWT finished");
        String getMTF = MTFCompress.MTF(result.toString(), MTFCompress.getAlphabet(result.toString())).toString();
        FileWriter fw = new FileWriter("midFile.txt");
        fw.write(getMTF);
        fw.close();
        System.out.println("MTF finished");
        countAC();
        System.out.println("AC finished");
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("midFile.txt")));
        String encodedLine = br2.readLine();
        String decodedMTF = MTFCompress.rMTF(encodedLine, MTFCompress.getAlphabet(result.toString()));
        String decodedBWT = BWTFast.reverseBWT(decodedMTF).toString();
        FileWriter fw2 = new FileWriter("decompressedText.txt");
        fw2.write(decodedBWT);
        fw2.close();
    }


//    public static void countBwtMtfRLEAc() throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("rusTolstoy.txt"), "windows-1251"));
//        String line = br.readLine();
//        String result = "";
//        while (line != null) {
//            result += line;
//            line = br.readLine();
//        }
//        result = MTFCompress.MTF(BWTCompress.efficientBWT(result), MTFCompress.getAlphabet(BWTCompress.efficientBWT(result))).toString();
//        FileWriter fw = new FileWriter("compressedText.txt");
//        BufferedWriter bw = new BufferedWriter(fw);
//        fw.write(result);
//        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt"), "windows-1251"));
//        RLECompress.compressText(br2);
//        BufferedReader br3 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedTextFinal.txt"), "windows-1251"));
//        DataOutputStream dos = new DataOutputStream(new FileOutputStream("compressedTextVeryFinal.txt"));
//        result = br3.readLine();
//        int curLength = 0;
//        double resultAC;
//        while (curLength + 10 <= result.length()) {
//            double[] probabilities = ArithmeticCoding.getProbabilities(result.substring(curLength, curLength + 10));
//            resultAC = ArithmeticCoding.arithmeticCoding(result.substring(curLength, curLength + 10), probabilities);
//            BigDecimal bigDecimal = new BigDecimal(Double.toString(resultAC));
//            String decimalPart = bigDecimal.subtract(new BigDecimal(bigDecimal.longValue())).toPlainString().substring(2);
//            curLength += 10;
//            dos.writeLong(Long.parseLong(decimalPart));
//            //dos.writeChar(' ');
//        }
//        if (result.length() - curLength > 0) {
//            double[] probabilities = ArithmeticCoding.getProbabilities(result.substring(curLength, result.length()));
//            resultAC = ArithmeticCoding.arithmeticCoding(result.substring(curLength, result.length()), probabilities);
//            BigDecimal bigDecimal = new BigDecimal(Double.toString(resultAC));
//            String decimalPart = bigDecimal.subtract(new BigDecimal(bigDecimal.longValue())).toPlainString().substring(2);
//            dos.writeLong(Long.parseLong(decimalPart));
//            //dos.writeChar(' ');
//        }
//        dos.close();
//    }


    // LZ77

    public static void countLZ77() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("rusTolstoy.txt")));
        String line = br.readLine();
        FileWriter fw = new FileWriter("compressedText.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        FileWriter fw2 = new FileWriter("decompressedText.txt");
        while (line != null) {
            List<Object> result = LZ77.LZ77Compress(line);
            fw2.write(LZ77.LZ77Decompress(result));
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i) instanceof LZ77Tuple) {
                    StringBuilder cur = new StringBuilder();
                    cur.append(((LZ77Tuple) result.get(i)).length).append(((LZ77Tuple) result.get(i)).offset).append(((LZ77Tuple) result.get(i)).nextChar);
                    bw.write(cur.toString());
                }
                else {
                    bw.write(result.get(i).toString());
                }
            }
            bw.newLine();
            line = br.readLine();
        }
        bw.close();
        fw2.close();
    }


    // LZ77 + HA

    public static void countLZ77Ha() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("enwik7.txt")));
        String line = br.readLine();
        FileWriter fw = new FileWriter("compressedText.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        FileWriter fw2 = new FileWriter("decompressedText.txt");
        while (line != null) {
            List<Object> result = LZ77.LZ77Compress(line);
            fw2.write(LZ77.LZ77Decompress(result));
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i) instanceof LZ77Tuple) {
                    StringBuilder cur = new StringBuilder();
                    cur.append(((LZ77Tuple) result.get(i)).length).append(((LZ77Tuple) result.get(i)).offset).append(((LZ77Tuple) result.get(i)).nextChar);
                    bw.write(cur.toString());
                }
                else {
                    bw.write(result.get(i).toString());
                }
            }
            bw.newLine();
            line = br.readLine();
        }
        bw.close();
        fw2.close();
        countHa();
    }

    public static void countLZ77Ac() throws Exception {
        countLZ77();
        countAC();
    }
}
