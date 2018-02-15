package animate;

import java.awt.*;
import java.util.Random;

public class ibrahimenesaydogdu implements Animator {
    final Container pan = new Panel();
    Random random = new Random();
    int counter = 0,counterForScreen=0;
    final static double DELTA = Math.PI/50;
    final static int A = MX-50, B = MY-50, D = 10;
    double angle = 0;
    Color color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    @Override
    public int doTick() {

        angle += DELTA;
        pan.repaint();
        return 100;
    }

    @Override
    public Container container() {

        return pan;
    }

    @Override
    public String description() {
        return "0'dan 100'e sayac";
    }

    @Override
    public String author() {
        return "Ibrahim Enes Aydogdu";
    }
    class Panel extends javax.swing.JPanel { //drawing

        @Override
        public void paint(Graphics g) {
            g.clearRect(0, 0, 2 * MX, 2 * MY);
            Font font = new Font("Arial", Font.BOLD, 60);
            g.setFont(font);
            counter = counter+1;

            String s = String.valueOf(counter);

            g.setColor(color);
            double x = MX + A * Math.cos(angle);
            double y = MY + B * Math.sin(angle);
            g.drawString(s, (int)x, (int)y);
            g.drawString(String.valueOf(counterForScreen),850,600);
            if (counter ==100){
                counter = 0;
                counterForScreen+=1;
                color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
            }

        }
    }
}
