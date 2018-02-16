package animate;

import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JLabel;
import java.util.Random;

public class CelilReha implements Animator {
    final JLabel label = new JLabel();
	Random r = new Random();
    Color GOLD = new Color(255,215,0);
    char c;
    CelilReha() {
        label.setForeground(GOLD);
        label.setHorizontalAlignment(0);
        label.setFont(new Font("Serif", Font.BOLD, 40));
        doTick(); //initial call fills the label
    }
    public int doTick() {
         
        label.setForeground(new Color(r.nextInt(200),r.nextInt(200),r.nextInt(200)));
        
        label.setFont(new Font("Serif", Font.BOLD, r.nextInt(100)+20)); 
        
        
        c= (char) (r.nextInt(250));
        label.setText(String.valueOf(c));
        
        return 500; 
    }
    public Container container() {
        return label;
    }
    public String description() {
        return "Random colors and character";
    }
    public String author() {
        return "Celil Reha Esgice";
    }
}