import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanAlgorithm{
    static class HuffmanNode implements Comparable<HuffmanNode>{
        int f;
        char ch;
        HuffmanNode left;
        HuffmanNode right;
        HuffmanNode(char ch,int f){
            this.f = f;
            this.ch = ch;
            this.left = null;
            this.right = null;
        }
        
        @Override
        public int compareTo(HuffmanNode o) {
            
            return this.f-o.f;
        }
    }
    
    static HuffmanNode root = null;
    static PriorityQueue<HuffmanNode> pq  = new PriorityQueue<>();      //Priority Queue to Store the Huffman Nodes. It is used to construct the Huffman Tree
    static HashMap<Character,String> hm = new HashMap<>();              //HashMap to Store the character with their Prefix Codes
    static StringBuilder str = new StringBuilder();                     //Stores the String that is read from the File
    
    public static void readFromFile(String fileName){                   //Reads from the file and Converts all the data to a String
        File myFile = new File("test.txt");
        try {
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()){
                str.append(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void initializingHashMap(){
        HashMap<Character,Integer> map = new HashMap<>();           //Intializing The HashMap with char and their Frequencies (map stores char with their frequencies)
        for (int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            map.put(ch,map.getOrDefault(ch, 0)+1);
        }
        for (char c : map.keySet()){                                //Creating Huffman Nodes from the map HM and adding it to the PriorityQueue pq
            pq.add(new HuffmanNode(c, map.get(c)));
        }
    }

    public static void constructHuffmanTree(){                      //Constructs The Huffman Tree
        
        while (pq.size()>1){
            HuffmanNode x = pq.poll();
            HuffmanNode y = pq.poll();
            HuffmanNode newNode = new HuffmanNode('\0', x.f+y.f);
            newNode.left = x;
            newNode.right = y;
            root = newNode;
            pq.add(newNode);
        }

    }

    public static void createPrefixCodes(HuffmanNode node, StringBuilder code) {    //Creates the Prefix Codes for each Character and Stores it in HashMap hm

        if (node == null){
            return;
        }

        if (node.ch != '\0') {
            hm.put(node.ch, code.toString());
            return;
        }

        createPrefixCodes(node.left, code.append("0"));
        code.deleteCharAt(code.length() - 1);
        createPrefixCodes(node.right, code.append("1"));
        code.deleteCharAt(code.length() - 1);
    }

    public static String printEncodedText(String str){          // Accepts The String read from the File

        StringBuilder v = new StringBuilder();                  // Appends the corresponding String (from HashMap) for the character 
        for (Character c : str.toCharArray()){
            v.append(hm.get(c));
        }

        return v.toString();                                   // Returns the encoded String
    }
 
    public static void writeToFile(String fileName){           // Writes the Encoded String on the File

        String v = printEncodedText(str.toString());
        try {
            FileOutputStream fileWriter = new FileOutputStream(fileName);
            int currentByte = 0;
            int bitCounter = 0;
            for (char bit : v.toCharArray()) {
                currentByte = (currentByte << 1) | (bit - '0'); //Converts 0's and 1's to be written on the File
                bitCounter++;
                if (bitCounter == 8) {
                    fileWriter.write(currentByte);
                    currentByte = 0;
                    bitCounter = 0;
                }
            }
            if (bitCounter > 0) {
                currentByte <<= 8 - bitCounter;
                fileWriter.write(currentByte);
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        
        readFromFile("test.txt");
        initializingHashMap();
        constructHuffmanTree();
        createPrefixCodes(root, new StringBuilder());
        writeToFile("Compressed test.txt");
    }
}