package animate;

import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JLabel;
import java.util.Random;

public class BedirhanYildirim implements Animator {
    final JLabel jl = new JLabel();
    Random rand = new Random();
    int counter = -1;
    int scale = 0;
    int scaleDelay = 50;
    int animationCounter = 0;
    boolean flag = false;
    String text = "Bedirhan";
    BedirhanYildirim() {
        jl.setForeground(Color.BLACK);
        jl.setHorizontalAlignment(0);
        jl.setFont(new Font("Serif", Font.BOLD, 40));
    }
    public int doTick() {
        if(counter < 0){counter++;}
        if(text.length() == counter){
            heartbeatEffect();
            if(animationCounter == 5){
                animationCounter = 0;
                counter = -1;
            }
            jl.setText(text);
            return scaleDelay;
        }else if(counter >= 0){
            jl.setForeground(new Color(rand.nextInt(255 - 0 + 1) + 0,rand.nextInt(255 - 0 + 1) + 0,rand.nextInt(255 - 0 + 1) + 0));
            jl.setText(new StringBuilder().append("").append(text.charAt(counter)).toString());
            counter++;
            animationCounter = 0;
        }
        return 500;
    }
    public Container container() {
        return jl;
    }
    public String description() {
        return "Random Colored Speller Animation";
    }
    public String author() {
        return "Bedirhan Yildirim";
    }
    private void heartbeatEffect(){
        if(scale == 0){
            flag = false;
            animationCounter++;
            jl.setFont(new Font("Serif", Font.BOLD, 40));
            jl.setForeground(new Color(0,0,0));
        }else if(scale == 1){
            jl.setFont(new Font("Serif", Font.BOLD, 45));
            jl.setForeground(new Color(60,44,44));
        }else if(scale == 2){
            jl.setFont(new Font("Serif", Font.BOLD, 50));
            jl.setForeground(new Color(101,55,55));
        }else if(scale == 3){
            jl.setFont(new Font("Serif", Font.BOLD, 55));
            jl.setForeground(new Color(139,63,63));
        }else if(scale == 4){
            jl.setFont(new Font("Serif", Font.BOLD, 60));
            jl.setForeground(new Color(195,44,44));
        }else if(scale == 5){
            jl.setFont(new Font("Serif", Font.BOLD, 65));
            jl.setForeground(new Color(255,0,0));
            flag = true;
        }
        if(flag){
            scale--;
        }else{
            scale++;
        }
    }
}
