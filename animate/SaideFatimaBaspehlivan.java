/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animate;

import java.awt.Graphics;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.util.Random;

public class SaideFatimaBaspehlivan implements Animator{
     final Container pan = new Panel();
    int counter = 0;

    public int doTick() {
        pan.repaint();
        return 200;
    }

    public Container container() {
        return pan;
    }

    public String description() {
        return "rastgele gorunen sayilar";
    }

    public String author() {
        return "Saide Fatima Baspehlivan";
    }

    class Panel extends javax.swing.JPanel { //drawing

        @Override
        public void paint(Graphics g) {
            g.clearRect(0, 0, 2 * MX, 2 * MY);
            Font font = new Font("Arial", Font.BOLD, 60);
            Random random = new Random();
            int i  =random.nextInt(100);
            String sayi = String.valueOf(i);
            Color c = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
            g.setFont(font);
            int a = random.nextInt(MX);
            int b = random.nextInt(MY);
            g.setColor(c);
            g.drawString(sayi, a, b);

        }
    }
}
