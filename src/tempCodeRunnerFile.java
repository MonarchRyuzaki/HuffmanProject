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

    static PriorityQueue<HuffmanNode> pq  = new PriorityQueue<>();
    static HashMap<Character,String> hm = new HashMap<>();
    static HuffmanNode root = null;

    public static void constructHuffmanTree(){

        while (pq.size()>1){
            HuffmanNode x = pq.poll();
            HuffmanNode y = pq.poll();
            HuffmanNode newNode = new HuffmanNode('-', x.f+y.f);
            newNode.left = x;
            newNode.right = y;
            root = newNode;
            pq.add(newNode);
        }

    }
    
    public static void createPrefixCodes(HuffmanNode root,String s){
        if (s.length() == 0){
            return;
        }

        if (root.left==null && root.right == null){
            hm.put(root.ch,s);
            return;
        }

        createPrefixCodes(root.left,s+"0");
        s=s.substring(0,s.length()-1);
        createPrefixCodes(root.right,s+"1");
        s=s.substring(0,s.length()-1);
    }

    // static void buildCodeMap(HuffmanNode node, StringBuilder code) {
    //     if (node == null)
    //         return;

    //     if (node.ch != '\0') {
    //         hm.put(node.ch, code.toString());
    //         return;
    //     }

    //     buildCodeMap(node.left, code.append("0"));
    //     code.deleteCharAt(code.length() - 1);
    //     buildCodeMap(node.right, code.append("1"));
    //     code.deleteCharAt(code.length() - 1);
    // }

    public static String printEncodedText(String str){

        StringBuilder v = new StringBuilder();
        for (Character c : str.toCharArray()){
            v.append(hm.get(c));
        }

        return v.toString();
    }

    public static void main(String[] args) {
        

        StringBuilder str = new StringBuilder();
        File myFile = new File("test.txt");
        try {
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()){
                str.append(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            map.put(ch,map.getOrDefault(ch, 0)+1);
        }

        for (char c : map.keySet()){
            pq.add(new HuffmanNode(c, map.get(c)));
        }

        constructHuffmanTree();
        createPrefixCodes(root, "");
        //System.out.println(hm);
        //buildCodeMap(root, new StringBuilder());
        String v = printEncodedText(str.toString());
        File myFile1 = new File("Compressed test.txt");
        try {
            //myFile1.createNewFile();
            FileOutputStream fileWriter = new FileOutputStream("Compressed test.txt");
            int currentByte = 0;
            int bitCounter = 0;
            for (char bit : v.toCharArray()) {
            currentByte = (currentByte << 1) | (bit - '0');
            bitCounter++;
            if (bitCounter == 8) {
                fileWriter.write(currentByte);
                currentByte = 0;
                bitCounter = 0;
            }
            if (bitCounter > 0) {
                currentByte <<= 8 - bitCounter;
                fileWriter.write(currentByte);
            }
        }
        fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}