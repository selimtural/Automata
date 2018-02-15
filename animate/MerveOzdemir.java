package animate;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Color;

public class MerveOzdemir implements Animator {
    final static double DELTA =Math.PI/50;  
    final static int A = MX-50, B = MY-50, D = 10;
    final Container pan = new Panel();
    double angle = 0; 
   

    public int doTick() {
          angle += DELTA; 
          pan.repaint(); 
          return 100;
    }
    public Container container() {
        return pan;
    }
    public String description() {
        return "Circle Patterns";
    }
    public String author() {
        return "Merve Özdemir";
    }
   
   class Panel extends javax.swing.JPanel { //drawing
      public void paint(Graphics g) {
         
         double x = MX+ A * Math.cos(angle)/2;
         double y = MY+ B * Math.sin(angle)/2;
          
          if((int)x == 454 && (int)y == 245 ){
             g.clearRect(0, 0, 2 * MX, 2 * MY);
          }
           
          
          g.setColor(Color.yellow);
          g.fillOval((int)x-125, (int)y-125, D, D);
          
          g.setColor(Color.yellow);
          g.fillOval((int)x-125, (int)y+125, D, D);
          
          
          g.setColor(Color.green);
          g.fillOval((int)x-75, (int)y-75, D, D);
          
          g.setColor(Color.green);
          g.fillOval((int)x-75, (int)y+75, D, D);
          
          
          g.setColor(Color.green);
          g.fillOval((int)x+75, (int)y+75, D, D);
          
          g.setColor(Color.green);
          g.fillOval((int)x+75, (int)y-75, D, D);
          
          
          g.setColor(Color.yellow);
          g.fillOval((int)x+125 ,(int)y+125, D, D);
          
          g.setColor(Color.yellow);
          g.fillOval((int)x+125, (int)y-125, D, D);
          
          
      }
   } 
}
