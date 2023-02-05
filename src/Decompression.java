import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Decompression {

    static StringBuilder str = new StringBuilder();
    public static String readFromFile(String filePath){                 
        try (Scanner sc = new Scanner(new FileReader(filePath))) {
            while (( sc.hasNextLine())) {
                str.append(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading from file " + filePath + ": " + e.getMessage());
        }
        return str.toString();
    }
    Decompression(String path){
        readFromFile(path);
        String k = str.toString();
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.home") + File.separator +"Desktop"+File.separator+"Decompressed.txt");
            fileWriter.write(k);

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
