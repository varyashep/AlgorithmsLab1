import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {


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


//        String line = "The string class doesnt have any method that directly sorts a string, but we can sort a string by applying other methods one after another. The string is a sequence of characters. In java, objects of String are immutable which means a constant and cannot be changed once created.";
//        System.out.println(line);
//        String result = BWTFast.getBWT(line).toString();
//        System.out.println(result);
//        System.out.println(BWTCompress.InverseBWT(result));


        //Effectiveness.countBWTRLE();
        //Effectiveness.countRLE();
        //Effectiveness.countHa();
        //Effectiveness.countAC();
        //Effectiveness.countBwtMtfHa();
        //Effectiveness.countBwtMtfRLEHa();
        //Effectiveness.countLZ77();
        System.out.println(Effectiveness.getEntropy()*Math.pow(10,7));
        System.out.println(Effectiveness.getEntropy()*Math.pow(10,7)/Math.pow(2,3));
        System.out.println(Effectiveness.getEntropy());
        //Effectiveness.countLZ77Ha();
    }
}