package animate;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Color;
import java.awt.Point;

public class ummu implements Animator {
    final static double DELTA = Math.PI/50; //one turn = 100 ticks 
    final static int A = MX-50, B = MY-50;
    final Container pan = new Panel();
    double angle = 0; //in radians
     int counter=0;
    Point lab = new Point(25,255);

    public int doTick() {
     
     if (counter < 40) {
            lab.setLocation(lab.getX() + 5, lab.getY() + 5);
            counter++;
        } else if (counter < 80) {
            lab.setLocation(lab.getX() + 5, lab.getY() - 5);
            counter++;
        } else if ( counter < 120) {
            lab.setLocation(lab.getX() - 5, lab.getY() - 5);
            counter++;
        } else if (counter < 160) {
            lab.setLocation(lab.getX() - 5, lab.getY() + 5);
            counter++;
        }else{
            counter = 0;
        }	
        pan.repaint();
        return 10;



    }
    public Container container() {
        return pan;
    }
    public String description() {
        return "Kare olusturan geometrýk sekýl";
    }
    public String author() {
        return "ummugulsum can ";
    }
   
   class Panel extends javax.swing.JPanel { //drawing
     int count=1;
      public void paint(Graphics g) {

      if(count%2 == 0){
      g.setColor(Color.red);
      }
      else if(count%3 == 0){
       g.setColor(Color.blue);
      }
      else if(count%4 == 0){
      g.setColor(Color.yellow);
      }
      else if (count%5 == 0){
      g.setColor(Color.green);
      }
       else {
      g.setColor(Color.black);
      }
      
      g.clearRect(0, 0, 2*MX, 2*MY);

      double x = MX + A * Math.cos(angle+20);
      double y = MY + B * Math.sin(angle+30);

       g.drawRect(lab.x, lab.y, 50, 50);
       g.fillOval(lab.x, lab.y, 50, 50);  

      count++;


      }
   } 
}


