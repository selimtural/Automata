/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animate;

import static animate.Animator.MX;
import static animate.Motion_MAE.D;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JLabel;

/**
 *
 * @author selin
 */
public class SelinDaldaban implements Animator {
     final static double DELTA = Math.PI/50; //one turn = 100 ticks 
    final static int A = MX-50, B = MY-50, D = 10;
    final Container pan = new Panel();
    double angle = 0; //in radians
   

    public int doTick() {
        angle += DELTA; 

        pan.repaint(); 
        
        return 100;
    }
    public Container container() {
        return pan;
    }
    public String description() {
        return "kayan yazı";
    }
    public String author() {
        return "Selin Daldaban";
    }
   
   class Panel extends javax.swing.JPanel { //drawing
      public void paint(Graphics g) {
            g.clearRect(0, 0, 2*MX, 2*MY);
          
          String s="DALDABAN";
          g.setColor(Color.pink);
     
         g.clearRect(0, 0, 2 * MX, 2 * MY);
          Font font = new Font("Arial", Font.BOLD, 20);
          g.setFont(font);
          g.clearRect(0, 0, 2*MX, 2*MY);
         
          double x = MX + A * Math.cos(angle+50);
          double y = MY + B * Math.sin(angle+20);
          g.drawString(s, (int)x,(int)y);
         
           double x1 = MX + A * Math.cos(angle+20);
          double y1 = MY + B * Math.sin(angle+50);
          String  s1="SELIN";
          g.drawString(s1, (int)x1,(int)y1);
          

      }
   } 
}
 





