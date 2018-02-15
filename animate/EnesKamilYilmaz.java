package animate;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Color;

public class EnesKamilYilmaz implements Animator {
    final static double DELTA = Math.PI/60; 
    final static double DELTA2 = Math.PI/55;
    final static double DELTA3 = Math.PI/42; 
    final static double DELTA4 = Math.PI/65;
    final static double DELTA5 = Math.PI/77; 
    final static double DELTA6 = Math.PI/81;
    final static double DELTA7 = Math.PI/83; 
    final static double DELTA8 = Math.PI/84;
    final static double DELTA9 = Math.PI/85; 

    final static int A = MX-140, B = MY-140, D = 24; // Earth 
    final static int A2 = MX-163, B2 = MY-163, D2 = 14; // Venus 
    final static int A3 = MX-180, B3 = MY-180, D3 = 10; //Mercury
    final static int A4 = MX-120, B4 = MY-120, D4 = 20; // Mars
    final static int A5 = MX-85, B5 = MY-90, D5 = 43; // Jupiter
    final static int A6 = MX-60, B6 = MY-60, D6 = 18; // Saturn
    final static int A7 = MX-40, B7 = MY-40, D7 = 20; // Uranus
    final static int A8 = MX-21, B8 = MY-21, D8 = 16; // Neptune
    final static int A9 = MX-5, B9 = MY-5, D9 = 8; // Pluto

    double angle = 0, angle1 = 0, angle2 = 0, angle3 = 0;
    double angle4 = 0, angle5 = 0, angle6 = 0, angle7 = 0;
    double angle8 = 0, angle9 = 0;

    final Container pan = new Panel();

    public int doTick() {
        angle += DELTA; 
        angle2 += DELTA2; 
        angle3 += DELTA3; 
        angle4 += DELTA4; 
        angle5 += DELTA5; 
        angle6 += DELTA6; 
        angle7 += DELTA7; 
        angle8 += DELTA8; 
        angle9 += DELTA9; 
        pan.repaint(); 
        return 50;
    }
    public Container container() {
        return pan;
    }
    public String description() {
        return "Sun system";
    }
    public String author() {
        return "Enes Kamil Yilmaz";
    }
   
   class Panel extends javax.swing.JPanel {
      public void paint(Graphics g) {
          g.clearRect(0, 0, 2*MX, 2*MY);

          double xPluto = MX + A9 * Math.cos(angle9);
          double yPluto = MY + B9 * Math.sin(angle9);
          g.setColor(Color.lightGray);
          g.fillOval((int)xPluto, (int)yPluto, D9, D9);

          double xNeptune = MX + A8 * Math.cos(angle8);
          double yNeptune = MY + B8 * Math.sin(angle8);
          g.setColor(Color.blue);
          g.fillOval((int)xNeptune, (int)yNeptune, D8, D8);

          double xUranus = MX + A7 * Math.cos(angle7);
          double yUranus = MY + B7 * Math.sin(angle7);
          g.setColor(Color.cyan);
          g.fillOval((int)xUranus, (int)yUranus, D7, D7);

          double xSaturn = MX + A6 * Math.cos(angle6);
          double ySaturn = MY + B6 * Math.sin(angle6);
          g.setColor(Color.gray);
          g.fillOval((int)xSaturn, (int)ySaturn, D6, D6);

          double xJupiter = MX + A5 * Math.cos(angle5);
          double yJupiter = MY + B5 * Math.sin(angle5);
          g.setColor(Color.gray);
          g.fillOval((int)xJupiter, (int)yJupiter, D5, D5);

          double xMars = MX + A4 * Math.cos(angle4);
          double yMars = MY + B4 * Math.sin(angle4);
          g.setColor(Color.orange);
          g.fillOval((int)xMars, (int)yMars, D4, D4);

          double xEarth = MX + A * Math.cos(angle);
          double yEarth = MY + B * Math.sin(angle);
          g.setColor(Color.blue);
          g.fillOval((int)xEarth, (int)yEarth, D, D);

          double xVenus = MX + A2 * Math.cos(angle2);
          double yVenus = MY + B2 * Math.sin(angle2);
          g.setColor(Color.lightGray);
          g.fillOval((int)xVenus, (int)yVenus, D2, D2);

          double xMercury = MX + A3 * Math.cos(angle3);
          double yMercury = MY + B3 * Math.sin(angle3);
          g.setColor(Color.darkGray);
          g.fillOval((int)xMercury, (int)yMercury, D3, D3);

          g.setColor(Color.yellow);
          g.fillOval(295,215,70,70);//Sun
      }
   } 
}
