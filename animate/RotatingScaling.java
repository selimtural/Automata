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
   
    BufferedImage img = loadImage();
    final Container pan = new Panel();
    int i = 0;
    

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
          
            AffineTransform at = AffineTransform.getTranslateInstance(40, 40);
            at.rotate(Math.toRadians(i++), img.getWidth(), img.getHeight());
            at.scale(1.5, 1.5);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(img, at, null);
            //repaint();
        }
    }

    BufferedImage loadImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("images", "kus.png"));

        } catch (IOException e) {

        }
        return img;
    }

}

