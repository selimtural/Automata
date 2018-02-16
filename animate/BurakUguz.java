package animate;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;




public class BurakUguz implements Animator {
    final static double DELTA = Math.PI/50; //one turn = 100 ticks 
    final static int A = MX-50, B = MY-50, D = 10;
    final Container pan = new Panel();
    double angle = 0; //in radians
    static int mx=5;
    public int doTick() {
        angle += DELTA; pan.repaint(); 
        return 100;
    }
    public Container container() {
        return pan;
    }
    public String description() {
        return "Spinnig Star";
    }
    public String author() {
        return "Burak Uğuz";
    }

private static Shape createStar(double centerX, double centerY,
        double innerRadius, double outerRadius, int numRays,
        double startAngleRad)
    {
        Path2D path = new Path2D.Double();
        double deltaAngleRad = Math.PI / numRays;
        for (int i = 0; i < numRays * 2; i++)
        {
            double angleRad = startAngleRad + i * deltaAngleRad;
            double ca = Math.cos(angleRad);
            double sa = Math.sin(angleRad);
            double relX = ca;
            double relY = sa;
            if ((i & 1) == 0)
            {
                relX *= outerRadius;
                relY *= outerRadius;
            }
            else
            {
                relX *= innerRadius;
                relY *= innerRadius;
            }
            if (i == 0)
            {
                path.moveTo(centerX + relX, centerY + relY);
            }
            else
            {
                path.lineTo(centerX + relX, centerY + relY);
            }
        }
        path.closePath();
        return path;
    }
   
   class Panel extends javax.swing.JPanel { //drawing
      public void paint(Graphics gr) {

	gr.clearRect(0, 0, 2*MX, 2*MY);
	
	super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
	g.rotate(Math.toRadians(25+mx), pan.getWidth()/2, pan.getHeight()/2);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
	g.setPaint(new RadialGradientPaint(
            new Point(pan.getWidth()/2, pan.getHeight()/2), 60, new float[] { 0, 1 }, 
            new Color[] { Color.RED, Color.YELLOW }));
	g.fill(createStar(pan.getWidth()/2, pan.getHeight()/2, 40, 100, 8, 0));
	mx=mx+5;

      }
   } 
}
