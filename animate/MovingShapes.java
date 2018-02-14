package animate;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Color;

/**
 *
 * @author Tolga
 */
public class MovingShapes implements Animator {
    final static double DELTA = 20; //one turn = 100 ticks 
    final static int A = MX-400, B = MY-300, D = 20; 
    final Container pan = new Panel();
    double angle1 = 0; 
    double angle2 = 0;//in radians
   
    public int doTick() {
       angle1 += DELTA; 
       angle2 += DELTA;
       pan.repaint(); 
        return 100;
    }
    public Container container() {
      return pan ;
    }
    public String description() {
        return "Moving Shapes";
    }
    public String author() {
        return "Tolga Tezel";
    }
    class Panel extends javax.swing.JPanel { //drawing
      public void paint(Graphics g) {
       g.clearRect(0, 0, 2*MX, 2*MY);
		
          double x1 = A + angle1;
          double y1 = B;
          double x2 = A;
          double y2 = B + angle2;
		
              
          if(y2==580){
               angle2-=460;
               angle1-=460; 
               }
			   
          g.setColor(Color.yellow);
          g.fillOval((int)x1-30, (int)y1+20, D, D);
          g.setColor(Color.BLUE);
          g.fillOval((int)x1-30, (int)y1+50, D, D);
          g.setColor(Color.RED);
          g.fillOval((int)x1-30, (int)y1+80, D, D);
          g.setColor(Color.orange);
          g.fillOval((int)x1-30, (int)y1+110, D, D);
     
          g.setColor(Color.yellow);
          g.fillRect((int)x2, (int)y2, D, D);
          g.setColor(Color.BLUE);
          g.fillRect((int)x2+30, (int)y2, D, D);
          g.setColor(Color.RED);
          g.fillRect((int)x2+60, (int)y2, D, D);
          g.setColor(Color.orange);
          g.fillRect((int)x2+90, (int)y2, D, D);

          
          
      }
   } 
}
