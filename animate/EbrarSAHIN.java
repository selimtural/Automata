/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animate;

import static animate.Animator.MX;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EbrarSAHIN implements Animator, MouseMotionListener, MouseListener {

    boolean isClicked = false;
    Container pan = new Panel();
    //Graphics g = pan.getGraphics();
    int X = 0;
    int Y = 0;

    public EbrarSAHIN() {

        pan.addMouseMotionListener(this);
        pan.addMouseListener(this);
        pan.setBackground(Color.WHITE);
    }

    @Override
    public int doTick() {
        pan.repaint();

        return 100;
    }

    @Override
    public Container container() {
        return pan;
    }

    @Override
    public String description() {
        return "Click and Draw";
    }

    @Override
    public String author() {
        return "Ebrar SAHIN";
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("Mouse Dragged");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("mouse moved");
        X = e.getX();
        Y = e.getY();
    }

    public void drawCenteredCircle(Graphics g, int x, int y, int r) {
        x = x - (r / 2);
        y = y - (r / 2);
        g.drawOval(x, y, r, r);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        X = e.getX();
        Y = e.getY();
        //System.out.println("Mouse Clicked.");
        isClicked = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    class Panel extends javax.swing.JPanel {

        @Override
        public void paint(Graphics g) {
            g.clearRect(0, 0, 2 * MX, 2 * MY);
            g.setColor(Color.red);
            g.fillOval(X, Y, 20, 20);
            if (isClicked) {
                for (int i = 0; i < 200; i++) {
                    drawCenteredCircle(g, X, Y, i);
                    g.clearRect(0, 0, 2 * MX, 2 * MY);
                }
                for (int i = 198; i >= 0; i--) {
                    drawCenteredCircle(g, X, Y, i);
                    g.clearRect(0, 0, 2 * MX, 2 * MY);
                }
                isClicked = false;
            }
        }
    }
}
