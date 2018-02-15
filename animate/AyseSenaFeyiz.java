package animate;

import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JLabel;
import java.util.Date;
import java.util.Random;

public class AyseSenaFeyiz implements Animator {
    final JLabel label = new JLabel();
    Random rand = new Random();

    AyseSenaFeyiz() {
        label.setForeground(Color.PINK);
        label.setHorizontalAlignment(0);
        label.setFont(new Font("Serif", Font.BOLD, 40));
        doTick(); //initial call fills the label
    }
    public int doTick() {
 Random rand = new Random();
int redValue = rand.nextInt(255);
int greenValue = rand.nextInt(255);
int blueValue = rand.nextInt(255);

Color clr = new Color(redValue, greenValue, blueValue);

        label.setForeground(clr);
        label.setFont(new Font("Serif", Font.BOLD, 10+rand.nextInt(200)));
        label.setText("Ayse Sena Feyiz");
        
        return 1000;
    }
    public Container container() {
        return label;
    }
    public String description() {
        return "Label Random Color and Size Change";
    }
    public String author() {
        return "Ayse Sena Feyiz";
    }
}