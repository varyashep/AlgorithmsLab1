import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RLECompress {

    public static final int whiteColor = 255;
    // Converting mono BW images to array of 0 and 1
    public static int[][] convertMono(BufferedImage image) throws IOException {
        int[][] data = convertToRGBBW(image);
        FileWriter fw = new FileWriter("picture.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                if (data[i][j] > whiteColor/2) {
                    data[i][j] = 1;
                }
                else {
                    data[i][j] = 0;
                }
                bw.write((char) (data[i][j] + '0'));
            }
            bw.newLine();
        }
        bw.close();
        return data;
    }

    // Compressing mono BW image
    public static void compressMono(int[][] data, BufferedImage image) throws IOException {
        FileWriter fw = new FileWriter("pictureCompressed.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        int counter = 1;
        for (int i = 0; i < image.getHeight(); i++) {
            counter = 1;
            for (int j = 0; j < image.getWidth()-1; j++) {
                if (j == 0 && (char) (data[i][j] + '0') == '1') {
                    bw.write('0');
                }
                if ((char) (data[i][j] + '0') == (char) (data[i][j+1] + '0')) {
                    counter++;
                } else {
                    bw.write((char) (counter + 'a'));
                    counter = 1;
                }
            }
            if (counter > 1) {
                bw.write((char) (counter + 'a'));
            }
            bw.newLine();
        }
        bw.close();
    }

    // Decompressing mono BW image
    public static void decompressMono(BufferedImage image, BufferedReader compressedImg) throws IOException {
        FileWriter fw = new FileWriter("decompressedPic.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < image.getHeight(); i++) {
            String line = compressedImg.readLine();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(0) == '0') {
                    if (j != 0) {
                        int counter = (int) line.charAt(j) - (int) 'a';
                        if (j % 2 != 0) {
                            while (counter != 0) {
                                bw.write('1');
                                counter--;
                            }
                        } else {
                            while (counter != 0) {
                                bw.write('0');
                                counter--;
                            }
                        }
                    }
                }
                else {
                    int counter = (int) line.charAt(j) - (int) 'a';
                    if (j % 2 != 0) {
                        while (counter != 0) {
                            bw.write('0');
                            counter--;
                        }
                    } else {
                        while (counter != 0) {
                            bw.write('1');
                            counter--;
                        }
                    }
                }
            }
            bw.newLine();
        }
        bw.close();
    }

    // Converting BW image to byte array
    public static int[][] convertBW(BufferedImage image) throws IOException {
        int[][] data = convertToRGBBW(image);
        FileWriter fw = new FileWriter("pictureBW.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                bw.write((char) ((char) (data[i][j]) + 'a'));
            }
            bw.newLine();
        }
        bw.close();
        return data;
    }

    // Compressing BW image
    public static void compressBW(int[][] data, BufferedImage image) throws IOException {
        FileWriter fw = new FileWriter("pictureBWCompressed.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < image.getHeight(); i++) {
            int counter = 1;
            for (int j = 0; j < image.getWidth()-1; j++) {
                if (((char) (data[i][j]) + 'a') == ((char) (data[i][j+1]) + 'a')) {
                    counter++;
                }
                else {
                    bw.write((char) (counter + 'a'));
                    bw.write((char) ((char) (data[i][j]) + 'a'));
                    counter = 1;
                }
            }
            if (counter > 1) {
                bw.write((char) (counter + 'a'));
                bw.write((char) ((char) (data[i][image.getWidth()-1]) + 'a'));
            }
            bw.newLine();
        }
        bw.close();
    }

    // Decompressing BW image
    public static void decompressBW(BufferedImage image, BufferedReader compressedImg) throws IOException {
        FileWriter fw = new FileWriter("decompressedBW.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        int counter;
        for (int i = 0; i < image.getHeight(); i++) {
            String line = compressedImg.readLine();
            for (int j = 0; j < line.length()-1; j+=2) {
                counter = (int) line.charAt(j) - (int) 'a';
                while (counter != 0) {
                    bw.write(line.charAt(j+1));
                    counter--;
                }
            }
            bw.newLine();
        }
        bw.close();
    }

    // Compressing colored image
    public static void compressColor(BufferedReader text) throws IOException {
        FileWriter fw = new FileWriter("coloredCompressed.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        String line = text.readLine();
        while (line != null) {
            int i = 0;
            int counter = 1;
            while (i < line.length()-7) {
                boolean repeat = true;
                for (int j = 0; j < 4 && repeat; j++) {
                    if (line.charAt(i+j) != line.charAt(i+j+4)) {
                        repeat = false;
                    }
                }
                if (repeat) {
                    counter++;
                    i += 4;
                }
                else {
                    bw.write((char) (counter + 'a'));
                    bw.write(line.charAt(i));
                    bw.write(line.charAt(i+1));
                    bw.write(line.charAt(i+2));
                    bw.write(line.charAt(i+3));
                    counter = 1;
                    i += 4;
                }
            }
            if (counter > 1) {
                bw.write((char) (counter + 'a'));
                bw.write(line.charAt(line.length()-4));
                bw.write(line.charAt(line.length()-3));
                bw.write(line.charAt(line.length()-2));
                bw.write(line.charAt(line.length()-1));
            }
            bw.newLine();
            line = text.readLine();
        }
        bw.close();
    }

    // Decompressing colored image
    public static void decompressColor(BufferedReader text) throws IOException {
        FileWriter fw = new FileWriter("decompressedPic.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        String line = text.readLine();
        while (line != null) {
            int i = 0;
            while (i < line.length() - 4) {
                int counter = (int) line.charAt(i) - (int) 'a';
                while (counter != 0) {
                    for (int j = 1; j < 5; j++) {
                        bw.write(line.charAt(i+j));
                    }
                    counter--;
                }
                i += 5;
            }
            line = text.readLine();
            bw.newLine();
        }
        bw.close();
    }

    // Compressing text
    public static void compressText(BufferedReader text) throws IOException {
        int counter = 1;
        String line = text.readLine();
        FileWriter fw = new FileWriter("compressedText.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        while (line != null) {
            //System.out.println(line);
            int i = 0;
            while (i < line.length()-1) {
                if (line.charAt(i) == line.charAt(i+1)) {
                    counter++;
                    i++;
                }
                else {
                    if (counter > 1) {
                        bw.write((char) (counter + 'a'));
                        bw.write(line.charAt(i));
                        i++;
                        counter = 1;
                    }
                    bw.write("|");
                    while (i < line.length() - 1 && line.charAt(i) != line.charAt(i+1)) {
                        bw.write(line.charAt(i));
                        i++;
                    }

                    if (i == line.length() - 1) {
                        bw.write(line.charAt(i));
                    }
                    bw.write("|");
                }
            }
            line = text.readLine();
        }
        bw.close();
    }

    // Decompressing text
    public static void decompressText(BufferedReader text) throws IOException {
        FileWriter fw = new FileWriter("decompressedText.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        String line = text.readLine();
        while (line != null) {
            int i = 0;
            boolean noRepeats = false;
            while (i < line.length()-1) {
                if (line.charAt(i) == '|' && !noRepeats) {
                    noRepeats = true;
                    i++;
                }
                else if (line.charAt(i) != '|' && noRepeats) {
                    bw.write(line.charAt(i));
                    i++;
                }
                else if (line.charAt(i) == '|' && noRepeats) {
                    noRepeats = false;
                    i++;
                }
                else if (!noRepeats) {
                    int counter = (int) line.charAt(i) - (int) 'a';
                    while (counter > 0) {
                        bw.write(line.charAt(i+1));
                        counter--;
                    }
                    i += 2;
                }
            }
            line = text.readLine();
        }
        bw.close();
    }

    // Converting BW picture with RGB
    public static int[][] convertToRGBBW(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] result = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color c = new Color(image.getRGB(col, row));
                int alpha = c.getAlpha();
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                result[row][col] = (red + green+ blue)/3;
            }
        }

        return result;
    }

    // Converting colored image with RGB
    public static void convertToRGBColor(BufferedImage image) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();
        FileWriter fw = new FileWriter("picture.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color c = new Color(image.getRGB(col,row));
                int alpha = c.getAlpha();
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                bw.write((char) alpha);
                bw.write((char) red);
                bw.write((char) green);
                bw.write((char) blue);
            }
            bw.newLine();
        }
        bw.close();
    }
}
