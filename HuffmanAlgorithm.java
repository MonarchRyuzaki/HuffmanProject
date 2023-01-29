import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

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

        if (root.left==null && root.right == null && Character.isLetter(root.ch)){
            hm.put(root.ch,s);
            return;
        }

        createPrefixCodes(root.left,s+"0");
        createPrefixCodes(root.right,s+"1");
    }


    public static void main(String[] args) {
        
        int n = 6;
        char[] ch= { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] freq = { 5, 9, 12, 15, 16, 45 };

        for (int i=0;i<n;i++){
            pq.add(new HuffmanNode(ch[i],freq[i]));
        }

        constructHuffmanTree();
        createPrefixCodes(root, "");
        System.out.println(hm);
    }
}