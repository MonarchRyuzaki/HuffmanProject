import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class MyFrame extends JFrame implements ActionListener{
    
    JLabel label;
    JButton compress;
    JButton decompress;
    JButton selectFile;
    JLabel path;
    JLayeredPane layeredPane;
    
    MyFrame(){
        this.setTitle("Compressor Decompressor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setSize(800,480);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,800,480);


        compress = new JButton("Compress");
        compress.setBounds(200,250,120,50);
        compress.setVisible(true);
        compress.setFocusable(false);
        compress.addActionListener(this);

        decompress = new JButton("Decompress");
        decompress.setBounds(400,250,130,50);
        decompress.setVisible(true);
        decompress.setFocusable(false);
        decompress.addActionListener(this);
        
        label = new JLabel();
        label.setBackground(new Color(218, 235, 237));
        //label.setIcon(new ImageIcon("bg.jpg"));
        label.setBounds(0,0,800,480);
        label.setVisible(true);
        label.setOpaque(true);

        selectFile = new JButton("Select File");
        selectFile.setBounds(400,150,130,50);
        selectFile.setVisible(true);
        selectFile.setFocusable(false);
        selectFile.addActionListener(this);

        Border blackLine = BorderFactory.createLineBorder(Color.BLACK);

        path = new JLabel("");
        
        path.setVisible(true);
        path.setOpaque(true);
        path.setForeground(Color.BLACK);
        path.setBackground(Color.WHITE);
        path.setBorder(blackLine);
        path.setBounds(150,165,200,25);
        layeredPane.add(label,Integer.valueOf(0));
        layeredPane.add(compress,Integer.valueOf(1));
        layeredPane.add(decompress,Integer.valueOf(1));
        layeredPane.add(selectFile,Integer.valueOf(1));
        layeredPane.add(path,Integer.valueOf(1));


        this.add(layeredPane);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == selectFile){
            JFileChooser fileChooser = new JFileChooser();

            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION){
                String p = fileChooser.getSelectedFile().getAbsolutePath();
                path.setText(p);
            }
        }    
        if (e.getSource() == compress){
            new Compression(path.getText());
            JOptionPane.showMessageDialog(null,"The Compression is Done");
        }
        if (e.getSource() == decompress){
            new Decompression(path.getText());
            JOptionPane.showMessageDialog(null,"The Decompression is Done");
        }
    }
}
