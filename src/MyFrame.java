import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class MyFrame extends JFrame{
    
    JLabel label;
    JButton compress;
    JButton decompress;
    JLayeredPane layeredPane;
    
    MyFrame(){
        this.setTitle("Compressor Decompressor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setSize(400,400);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,400,400);


        compress = new JButton("Compress");
        compress.setBounds(50,200,120,50);
        compress.setVisible(true);
        compress.setFocusable(false);

        decompress = new JButton("Decompress");
        decompress.setBounds(200,200,130,50);
        decompress.setVisible(true);
        decompress.setFocusable(false);
        
        label = new JLabel();
        label.setBackground(new Color(218, 235, 237));
        label.setBounds(0,0,400,400);
        label.setVisible(true);
        label.setOpaque(true);
        layeredPane.add(label,Integer.valueOf(0));
        layeredPane.add(compress,Integer.valueOf(1));
        layeredPane.add(decompress,Integer.valueOf(1));
        


        this.add(layeredPane);
        
    }
}
