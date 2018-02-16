package animate;

import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JLabel;
import java.util.Random;

public class SalimSahin implements Animator {
    final JLabel jl = new JLabel();
	Random rand = new Random();
    Color GOLD = new Color(255,215,0);
	int delay = 1;
    SalimSahin() {
        jl.setForeground(Color.RED);
        jl.setHorizontalAlignment(0);
        jl.setFont(new Font("Serif", Font.BOLD, 40));
        doTick(); //initial call fills the label
    }
    public int doTick() {
        jl.setFont(new Font("Serif", Font.BOLD, 40));
		if(delay < 1041){
            if(delay < 250){
                delay = delay+10;
            }
            else if(delay < 500){
                delay = delay+50;
            }
            else if(delay < 750){
                delay = delay+150;
            }
            else{
                delay = delay+30;
            }
            swapColor();
        }
		else if(delay == 1041){
            jl.setFont(new Font("Serif", Font.BOLD, 100));
            jl.setForeground(GOLD);
            delay = 5000;
        }
        else{
            delay = 1;
            swapColor();
        }
        jl.setText(Integer.toString(rand.nextInt(500)));
        return delay;
    }
    public Container container() {
        return jl;
    }
    public String description() {
        return "Random Number Generator";
    }
    public String author() {
        return "Salim Sahin";
    }
	private void swapColor(){
		if(jl.getForeground() == Color.BLUE)
			jl.setForeground(Color.RED);
        else
            jl.setForeground(Color.BLUE);
	}
}