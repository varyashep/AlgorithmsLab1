import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
         //код для текстовых файлов
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("rusTolstoy.txt"), "windows-1251"));
//        String line = br.readLine();
//        //System.out.println(line);
//        //FileOutputStream fw = new FileOutputStream("compressedText.txt");
////        FileWriter fw = new FileWriter("compressedText.txt");
////        BufferedWriter bw = new BufferedWriter(fw);
//        DataOutputStream dos = new DataOutputStream(new FileOutputStream("compressedText.txt"));
//
//        while (line != null) {
////            HashMap<Character, String> codes = Huffman.HuffmanCoding(line);
////            byte[] result = Huffman.getHuffmanString(line, codes);
//            int curLength = 0;
//            String resultAC;
//            while (curLength + 10 <= line.length()) {
//                double[] probabilities = ArithmeticCoding.getProbabilities(line.substring(curLength, curLength+10));
//                System.out.println(Arrays.toString(probabilities));
//                // Получаем результат кодирования и сжимаем его
//                resultAC = String.valueOf(ArithmeticCoding.arithmeticCoding(line.substring(curLength, curLength+10), probabilities));
//                System.out.println(resultAC);
//                curLength += 10;
//                if (resultAC.charAt(2) != '0') {
//                    resultAC = resultAC.substring(2);
//                }
//                else {
//                    int index = 0;
//                    for (int i = 3; i < resultAC.length() && index == 0; i++) {
//                        if (resultAC.charAt(i) != '0') {
//                            index = i;
//                        }
//                    }
//                    resultAC = resultAC.substring(index);
//                }
////                System.out.println(resultAC);
////                System.out.println();
//                if (resultAC.contains("E")) {
//                    resultAC = resultAC.substring(0, resultAC.indexOf("E"));
//                }
//                //resultAC = Long.toHexString(Long.parseLong(resultAC));
//                dos.writeLong(Long.parseLong(resultAC));
//            }
//            if (line.length() - curLength > 0) {
//                double[] probabilities = ArithmeticCoding.getProbabilities(line.substring(curLength, line.length()));
//                resultAC = String.valueOf(ArithmeticCoding.arithmeticCoding(line.substring(curLength, line.length()), probabilities));
//                if (resultAC.charAt(2) != '0') {
//                    resultAC = resultAC.substring(2);
//                }
//                else {
//                    int index = 0;
//                    for (int i = 3; i < resultAC.length() && index == 0; i++) {
//                        if (resultAC.charAt(i) != '0') {
//                            index = i;
//                        }
//                    }
//                    resultAC = resultAC.substring(index);
//                }
//                if (resultAC.contains("E")) {
//                    resultAC = resultAC.substring(0, resultAC.indexOf("E"));
//                }
//                //resultAC = Long.toHexString(Long.parseLong(resultAC));
//                dos.writeLong(Long.parseLong(resultAC));
//            }
//            line = br.readLine();
//        }
//        //fw.write(MTFCompress.MTF(BWTCompress.efficientBWT(result.toString()), MTFCompress.getAlphabet(BWTCompress.efficientBWT(result.toString()))));
//        dos.close();


//        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt"), "windows-1251"));;
//        //RLECompress.compressText(br2);
//        String text = br2.readLine();
//        FileOutputStream fw2 = new FileOutputStream("compressedTextFinal.txt");
//        HashMap<Character, String> codes = Huffman.HuffmanCoding(text);
//        byte[] resultBytes = Huffman.getHuffmanString(text, codes);
//        fw2.write(resultBytes);
//        fw2.close();

        // код для изображений
//        BufferedImage bImageMono = ImageIO.read(new File("coloredCats.jpg"));
//        RLECompress.convertToRGBColor(bImageMono);
        //int[][] data = RLECompress.convertMono(bImageMono);
        //FileOutputStream fw = new FileOutputStream("pictureBWCompressed.txt");
        //int[][] data = RLECompress.convertToRGBColor(bImageMono);
//        for (int i = 0; i < bImageMono.getHeight(); i++) {
//            byte[] result;
//            StringBuilder curLine = new StringBuilder();
//            for (int j = 0; j < bImageMono.getWidth(); j++) {
//                curLine.append((char) (data[i][j]));
//            }
//            //System.out.println(curLine);
//            HashMap<Character, String> codes = Huffman.HuffmanCoding(curLine.toString());
//            result = Huffman.getHuffmanString(curLine.toString(), codes);
//            fw.write(result);
//        }
//        fw.close();
//        System.out.println("Выберите вид изображения:");
//        System.out.println("1. Монохромное черно-белое");
//        System.out.println("2. Черно-белое");
//        System.out.println("3. Цветное");
//        System.out.println("4. Текст");
//        Scanner keyboard = new Scanner(System.in);
//        int choice = keyboard.nextInt();
//        switch (choice) {
//            case 1:
//                BufferedImage bImageMono = ImageIO.read(new File("bw_pic.jpg"));
//                int[][] picData = RLECompress.convertMono(bImageMono);
//                RLECompress.compressMono(picData, bImageMono);
//                FileReader fr = new FileReader("pictureCompressed.txt");
//                BufferedReader compressedPic = new BufferedReader(fr);
//                RLECompress.decompressMono(bImageMono, compressedPic);
//                break;
//            case 2:
//                BufferedImage bImageBW = ImageIO.read(new File("rose.jpg"));
//                int[][] BWpicData = RLECompress.convertBW(bImageBW);
//                FileReader fr2 = new FileReader("pictureBWCompressed.txt");
//                BufferedReader compressedBW = new BufferedReader(fr2);
//                RLECompress.compressBW(BWpicData, bImageBW);
//                RLECompress.decompressBW(bImageBW, compressedBW);
//                break;
//            case 3:
//                BufferedImage bImage = ImageIO.read(new File("coloredCats.jpg"));
//                RLECompress.convertToRGBColor(bImage);
//                FileReader fr3 = new FileReader("picture.txt");
//                BufferedReader br3 = new BufferedReader(fr3);
//                RLECompress.compressColor(br3);
//                FileReader fr4 = new FileReader("coloredCompressed.txt");
//                BufferedReader compressedColor = new BufferedReader(fr4);
//                RLECompress.decompressColor(compressedColor);
//                break;
//            case 4:
//                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("rusTolstoy.txt"), "windows-1251"));
//                RLECompress.compressText(br);
//                BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt"), "windows-1251"));
//                RLECompress.decompressText(br2);
//                break;
//            case 5:
//                BufferedReader br5 = new BufferedReader(new InputStreamReader(new FileInputStream("enwik7.txt"), "UTF-8"));
//                RLECompress.compressText(br5);
//                BufferedReader br6 = new BufferedReader(new InputStreamReader(new FileInputStream("compressedText.txt"), "UTF-8"));
//                RLECompress.decompressText(br6);
//                break;
//        }

        //System.out.println("BWT Compressor");
//        double length;
//        String input = "ABACABA";
//        length = BWTCompress.avLength(input);
//        System.out.println("Средняя длина последовательности повторяющихся символов: " + length);
//        ArrayList<String> result = BWTCompress.BWT(input);
//        System.out.println(result);
//        length = BWTCompress.avLength(result.get(0));
//        System.out.println("Средняя длина последовательности повторяющихся символов: " + length);
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("rusTextTest.txt"), "UTF-8"));
//        String line = br.readLine();
//        double length;
//        while (line != null) {
//            System.out.println(line.length());
//            System.out.println(line);
//            length = BWTCompress.avLength(line);
//            System.out.println("Средняя длина последовательности повторяющихся символов: " + length);
//            ArrayList<String> result = BWTCompress.BWT(line);
//            System.out.println(result.get(0));
//            length = BWTCompress.avLength(result.get(0));
//            System.out.println("Средняя длина последовательности повторяющихся символов: " + length);
//            System.out.println(BWTCompress.rBWT(result));
//            line = br.readLine();
//        }

//        String input = "BCABAAA";
//        LinkedList<Character> alphabet = MVTCompress.getAlphabet(input);
//        String result = MVTCompress.MVT(input, alphabet);
//        System.out.println(result);
//        String revResult = MVTCompress.rMVT(result, alphabet);
//        System.out.println(revResult);


//        codes.add("101");
//        codes.add("1");
//        codes.add("01");
//        System.out.println(Huffman.getCanonicalCodes(codes));
//        System.out.println(0b101 + 0b1);
//        int number = 6;
//        String binaryNumber = Integer.toBinaryString(number);
//        System.out.println(Integer.toBinaryString(6));
//        System.out.println(Integer.parseInt(binaryNumber));
//        StringBuilder something = new StringBuilder();
//        something.append("123");
//        System.out.println(something);
//        something.setLength(0);
//        System.out.println(something);

//        ArrayList<String> codes = new ArrayList<>();
//        codes.add("00");
//        codes.add("01");
//        codes.add("11");
//        codes.add("100");
//        codes.add("1010");
//        codes.add("1011");
//        System.out.println(Huffman.getCanonicalCodes(codes));
//        ArrayList<String> lengths = new ArrayList<>();
//        lengths.add("2");
//        lengths.add("2");
//        lengths.add("2");
//        lengths.add("3");
//        lengths.add("4");
//        lengths.add("4");
//        System.out.println(Huffman.getCanonicalCodesByLength(lengths));

//        double[] probs = {0.571429, 0.285714, 0.142857};
//        String data = "abacaba";
//        System.out.println(ArithmeticCoding.arithmeticCoding("abacaba", probs));
//        System.out.println(ArithmeticCoding.arithmeticDecoding(0.41147147247452504, probs, data.length()));


//        ArrayList<String> result = BWTCompress.BWT("окорок");
//        System.out.println(result);
//        System.out.println(BWTCompress.optimizedRevBWT(result));


        //String s = "abracadabra";
//        System.out.println(s);
//        System.out.println(Huffman.HuffmanCoding(s));
//        System.out.println(Huffman.result);
//        Huffman.HuffmanDecoding();
//        System.out.println(Huffman.getHuffmanString(s, Huffman.HuffmanCoding(s)));

        //System.out.println(Huffman.getCanonicalCodes(Huffman.HuffmanCoding(s)));

//        ArrayList<String> codes = new ArrayList<>();
//        codes.add("0");
//        codes.add("10");
//        codes.add("101");
//        codes.add("1001");
//        codes.add("1111");
//        System.out.println(codes);
//        System.out.println(Huffman.getCanonicalCodes(codes));

//        String s = "abacaba";
//        System.out.println(BWTCompress.BWT(s));
//        Suffixes.getSuffixArray(s);
//        System.out.println(BWTCompress.suffixBWT(s, Suffixes.getSuffixArray(s)));
//        System.out.println(Suffixes.getSuffixTypes("aaabcdab$"));

//        String s = "aaaaaaaaabbcccabc";
//        //String s = "abacabacabadaca";
//        System.out.println(LZ77.LZ77Compress(s));
//        System.out.println(LZ77.LZ77Decompress(LZ77.LZ77Compress(s)));

        //Effectiveness.countBwtMtfRLEHa();

    }
}