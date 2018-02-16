/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animate;

/**
 *
 * @author Lenovo
 */
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;

import java.awt.Container;

public class Betul_Ezgu implements Animator {

    final static double DELTA = Math.PI / 50; //one turn = 100 ticks 

    final static int A = MX - 50, B = MY - 50, D = 20, M = 20, N = 20, K = 20;

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

        return "Dikdortgen";

    }

    public String author() {

        return "Betul Ezgu";

    }

    class Panel extends javax.swing.JPanel { //drawing

        public void paint(Graphics g) {
            g.clearRect(0,0,2 * MX,2 * MY);
            g.setColor(new Color(255, 0, 0));
            g.drawRect(10, 10, 100, 50);
            g.fillRect(129, 10, 100, 50);
        }

    }

}
