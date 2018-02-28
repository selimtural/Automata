package animate;

import javax.swing.*;
import java.awt.*;

public class Rajab implements Animator{
    // a try to make something like : 
    // https://www.youtube.com/watch?v=3n9QTdj6Jv4
    public JLabel text = new JLabel("Hello World -_- ", SwingConstants.CENTER); 

    public int Y = MY*2; //
    public int pixel=5;  // movement 
    public int nabz = 1; // 1 for giving, 0 for getting
    public int sayac = 0;
    public int time = 0;
    public int control = 0;
    public int x = 0  ;
    public int y = 0;

    public Rajab(){
      
      text.setVerticalTextPosition(0);
    }
    public int doTick(){
        Movement();
        return 500;
    }
    public void Movement(){
        //currentY+pixel*side
        int currentY = (int)text.getLocation().getY();
       
        //text.setText("try"+sayac );
        sayac+=15;
        time ++;
        if(time == 5 ){
            // text control for every 5 seconds and every Loop.
            time = 0;
            control++;
            if(control == 5 ){
                control = 0;

            }

        }
        // change the colour increasly from black to red 
        text.setForeground(new Color(sayac,0,0));
        //text.setFont(new Font("TimesRoman", Font.PLAIN, sayac));
       
        switch (control) {
            case 0:
            text.setText("hello ");
            break;
            case 1:
            text.setText("world" );
            break;
            case 2:
            text.setText("Merhaba");
            break;
            case 3:
            text.setText("dunya" );
            break;
            case 4:
            text.setText("Naber" );
            break;
            case 5:
            text.setText("iyiyim" );
            break;
            default:
            text.setText("default" );
        }
        /*if(sayac >= 50){
        
        text.setText("try "+sayac/50 +" nabz = "+ nabz);
        }*/
        if(sayac==255){
        sayac=0;
        }
        if(nabz == 0 ){
        text.setFont(new Font("TimesRoman", Font.PLAIN, -sayac+30));
        nabz =1;
        }
        else {
        text.setFont(new Font("TimesRoman", Font.PLAIN, sayac-60));
        nabz=0;

        }
    }
    public Container container(){
        return text;
    } //drawing area -- called once
    public String description(){
        return "playing with words";
    } //what does it do?
    public String author(){
        return "A.Rajab";
    }  //who made it?
}
