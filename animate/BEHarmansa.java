package animate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class BEHarmansa implements Animator {
    final static int R = 10;
    public static final int STEP = R * 2;

    final Container container = new Panel();

    List<Ball> ballList = new ArrayList();

    public int doTick() {
        for (Ball ball: ballList) {
          ball.roll(container);
        }

        container.repaint();

        return 200;
    }
    public Container container() {
        container.addMouseListener(mouseListener); // listens pressed and relased events
        container.addMouseMotionListener(mouseListener); // listens mouse dragged event
        return container;
    }
    public String description() {
       return "Rolling Ball Animation - Click and hold on the panel, give a direction to ball and let it roll!";
    }
    public String author() {
        return "Bilal Ekrem Harmansa";
    }
   
    class Panel extends javax.swing.JPanel { //drawing
      public Point inProgressBall = new Point(); 
      public Point inProgressLine = new Point(); 

      boolean onProgressBall, onProgressLine;

      public void paint(Graphics g) {
          g.clearRect(0, 0, 2*MX, 2*MY);
          
          for(Ball b: ballList) {
            drawCircle(g, b.center.x, b.center.y, b.r, b.color);

          }
           // g.fillOval(b.center.x, b.center.y, b.r, b.r);

          if (onProgressBall ) {
              drawCircle(g, inProgressBall.x, inProgressBall.y, R, Color.DARK_GRAY);
              // g.fillOval(inProgressBall.x, inProgressBall.y, R, R);
              if (onProgressLine)
                g.drawLine(inProgressBall.x, inProgressBall.y, inProgressLine.x, inProgressLine.y);
          }
      }

      // src: wiki.scn.sap.com/wiki/display/Snippets/Drawing+circle+using+center+and+radius+instead+of+drawOval+params
      private void drawCircle(Graphics g, int x, int y, int r, Color c) {
        g.setColor(c);
        g.fillOval(x-r, y-r, 2*r, 2*r);
      }
    }

    // this methods checks boundaries, if ball moves out of panel's dimension,
    // the ball must be reflected; if ball is reflected, returns true, otherwise returns false.
    boolean checkAndReflect(Ball ball, Container con) {
        int x = ball.center.x;
        int y = ball.center.y;
        boolean result = false;
        if ( (y - ball.r) <= 0 ) { // horizantal reflection
            y = ball.r;
            x = ball.getAxisX(y); 
            ball.m = -ball.m;
            //recalculaten (x, 0)

            if(Double.isInfinite(ball.m))
              ball.expressionMode = !ball.expressionMode;

            result = true;
        } else if ( ( y + ball.r) >= con.getHeight() ) { // horizantal reflection
            y = con.getHeight() - ball.r;
            x = ball.getAxisX(y); 
            
            ball.m = -ball.m;
            // recalculaten (x, con.getHeight)
            if(Double.isInfinite(ball.m))
              ball.expressionMode = !ball.expressionMode;

            result = true;
        } else if ( ( x - ball.r) <= 0 ) { // vertical reflection
            x = ball.r;
            y = ball.getAxisY(x); 
            // recalculaten (0, y)
            ball.m = -ball.m;

            ball.expressionMode = !ball.expressionMode; // changing ball's direction
            result = true;
        } else if ( ( x + ball.r) >= con.getWidth() ) { // vertical reflection
            x = con.getWidth() - ball.r;
            y = ball.getAxisY(x);
            ball.m = -ball.m;
            //recalculaten (con.getWidth, y)

            ball.expressionMode = !ball.expressionMode; // changing ball's direction
            result = true;
        }

        if ( result ) {
          ball.recalculaten(x, y);
          ball.center.x = x;
          ball.center.y = y;
        }
        // else, clearly, no need to reflect
        return result;
    }

    class Ball {
      int r; //radius
      Point p1, p2, center; //these points defines that direction way of ball(the way is point1 to point 2)
      double m, n;
      boolean expressionMode; //defines that posAxisX will be inceremented or decremented.
                              // true incerements, false decrements
      Color color;

      Ball (int x1, int y1, int x2, int y2) {
          r = R;
          p1 = new Point(x1,y1); 
          p2 = new Point(x2,y2); 
          center = new Point(x1, y1);

          Random rnd = new Random();
          color = new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)) ;

          /*
            What is sign of slope(m) ? negative or positive ?  to calculate it,  
            determine which point on left ? x1 or x2 ? 
            after that look at value y. Which one is higher-upper than the other ? 
            if value x1 is smaller than x2 and y1 is bigger than y2 that means m is negative,
            also x2 is smaller and y2 is bigger than the other, m is also negative.
            otherwise m is positive.
           
            A problem will be occured, if the slope is steep. 
            Consider that x2 = x1. CONSTANT / 0 is infinite. We need to check this situations
            If m is infinite use y = k instead of y = mx + n
          */
          
          m = (double)(Math.abs(y2-y1)) / (double)(Math.abs(x2-x1));
          System.out.println("eğim " + m );
          if ( (x1 < x2  && y1 > y2) || (x2 < x1 && y2 > y1) ) { // slope -
              m = -m;
          }  //else slope +

          recalculaten(x1, y1); // init n, y = mx + 'n' ;

          if (Double.isInfinite(m)) {
            expressionMode = y2 > y1;
          } else {
            expressionMode = x2 > x1;
          }

      }

      Ball (Point p1, Point p2) {
        this(p1.x, p1.y, p2.x, p2.y);
      }

      void roll(Container con) {
        // if m is infinite, axis y need to inc or decr
        // else axis x need to inc or decr
        if ( Double.isInfinite(m) ) {
            if (expressionMode) 
              center.y += STEP;
            else
              center.y -= STEP;
            
        } else {
            if (expressionMode) 
              center.x += STEP;
            else
              center.x -= STEP;
            
        }

        int x = center.x;
        int y = getAxisY(x);
        center.x = x;
        center.y = y;

        checkAndReflect(this, con);
      }

      // return coordinate X for given value Y
      int getAxisX(int y) {
        // if slope is infinite, just return x (y = k)
          if ( Double.isInfinite(m) ) {
            return center.x;
          }

          return (int)((y - n) / m);
      }

      // return coordinate Y for given value X
      int getAxisY(int x) {
         // if slope is infinite, this method is not usefull.
          if ( Double.isInfinite(m) ) {
            return center.y;
          }

          return (int)((m * x) + n);
      }

      void recalculaten(int x, int y){
        // if m is infiye y = k , n = 0
        // otherwise calculate 
          if ( Double.isInfinite(m) )
            n = 0;
          else 
            n = y - (m * x);  
      }

      public String toString() {
         return "x:  " + center.x + "\ny: "  + center.y + "\nr: " + r + "\nm : " + m + " n: " + n  +
                                   "\nmode: " + (expressionMode ? "inc" : "decr");
      } 

   }

   private MouseInputAdapter mouseListener = new MouseInputAdapter() {
      Panel p = (Panel) container;

      @Override
      public void mousePressed(MouseEvent e) {
          p.inProgressBall.x = e.getX();
          p.inProgressBall.y = e.getY();
          p.onProgressBall = true;
      }

      @Override
      public void mouseDragged(MouseEvent e) {
          p.inProgressLine.x = e.getX();
          p.inProgressLine.y = e.getY();
          p.onProgressLine = true;
      }

      @Override
      public void mouseReleased(MouseEvent e) {
          //if balls start point and end point is same. do not draw ball
          if ( !p.inProgressBall.equals(p.inProgressLine) ) { 
              Ball b = new Ball(p.inProgressBall, p.inProgressLine);
              ballList.add(b);
          }

          p.onProgressBall = false;
          p.onProgressLine = false;
      }
   };
}

