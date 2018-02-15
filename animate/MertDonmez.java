package animate;


import java.awt.Graphics;
import java.awt.Container;
import java.awt.*;

public class MertDonmez implements Animator {
    final static double DELTA = Math.PI/10; //one turn = 100 ticks 
  
       Color [] List = {Color.blue,Color.cyan,Color.magenta,Color.orange, Color.pink,
                        Color.yellow};
                
                int count = 0;
    
    final static int widht = MX-100, height = MY-100, boyut = 20 , boyut2=15 ,boyut3=10;
    final static int widhtt = MX-50, heightt = MY-50;
    final static int widhttt = MX-150, heighttt = MY-150;
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
        return "Turning Balls";
    }
    public String author() {
        return "Mert Donmez";
    }
   
   class Panel extends javax.swing.JPanel { //drawing
      public void paint(Graphics g) {
          g.clearRect(0, 0, 2*MX, 2*MY);
          
          double x = MX + widht * Math.cos(angle);
          double y = MY + height * Math.sin(angle);
         g.setColor(Color.blue);
          g.fillOval((int)x, (int)y, boyut2, boyut2);
          

          double a = MX + widhtt * Math.cos(angle);
          double b= MY + heightt * Math.sin(angle);
          g.setColor(Color.yellow);
          g.fillOval((int)a, (int)b, boyut, boyut);
          
          
           double k = MX + widhttt * Math.cos(angle);
          double l= MY + heighttt * Math.sin(angle);
          g.setColor(Color.yellow);
          g.fillOval((int)k, (int)l, boyut3, boyut3);
          
          
          
          double m =  widhttt * Math.sin(angle)+ MX;
          double n=  heighttt * Math.cos(angle)+ MY;
          g.setColor(Color.yellow);
          g.fillOval((int)m, (int)n, boyut3, boyut3);
          
          
          double t = MX + widhtt * Math.sin(angle);
          double r= MY + heightt * Math.cos(angle);
          g.setColor(Color.yellow);
          g.fillOval((int)t, (int)r, boyut, boyut);
         
         
         double o = MX + widht * Math.sin(angle);
          double p = MY + height * Math.cos(angle);
         g.setColor(Color.blue);
          g.fillOval((int)o, (int)p, boyut2, boyut2);
          
           if (count == List.length)
           {
             count = 0;
             
             }
          
          
           double z = MX-40 ;
          double q = MY-40 ;
          
           g.setColor(List[count]);
           
          if(count==0 || count == 2 || count == 4 || count == 6){
          g.fillRect((int)z, (int)q, 80, 80);
          
          } 
           
           count++;
           
          g.fillOval((int)z, (int)q, 80, 80);
          
          
      }
   } 
}
