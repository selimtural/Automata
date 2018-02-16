package animate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class RotatingScaling implements Animator {

    final Container pan = new Panel();
    int i=0;
 

    public int doTick() {

        pan.repaint();
        return 600;

    }

    public Container container() {
 
        return pan;
    }

    public String description() {
        return "Rotating and scaling image";
    }

    public String author() {
        return "Seda Çağlar";
    }


    class Panel extends javax.swing.JPanel { 
       
        public void paint(Graphics g) {
            g.clearRect(0, 0, 3*MX, 3*MY);
            File img = new File("images", "kus.png");
            BufferedImage kus = null;
            try {

                kus = ImageIO.read(img);

            } catch (IOException e) {
                e.printStackTrace();
            }

            AffineTransform at = AffineTransform.getTranslateInstance(40, 40);
            at.rotate(Math.toRadians(i++), kus.getWidth(), kus.getHeight());
            at.scale(1.5, 1.5);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(kus, at, null);
            Graphics2D g2dd = (Graphics2D) g;
            g2dd.setPaint(Color.BLUE);
          
            int w = getWidth();
            int h = getHeight();

            Random r = new Random();

            for (int  j= 0; j < 4000; j++) {

                int x = Math.abs(r.nextInt()) % w;
                int y = Math.abs(r.nextInt()) % h;
                g2dd.drawLine(x, y, x, y);
            }
           
        }
    }
}


