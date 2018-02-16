/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animate;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Panel;

/**
 *
 * @author Dila
 */
public class DilaErbakan implements Animator {

    final Container pan = new Panel();
   
    int kenar1 = 0, kenar2 = 0, r = 50;
   

    public int doTick() {
        kenar1 += 10;
        pan.repaint();
        return 100;
    }
    public Container container() {
        return pan;
    }
    public String description() {
        return " Moving Rectangle";
    }
    public String author() {
        return "DILA ERBAKAN";
    }
    class Panel extends javax.swing.JPanel {

        public void paint(Graphics g) {
          
            g.clearRect(0, 0, this.getSize().width, this.getSize().height);
            g.drawRect(kenar1, kenar2, r, r);
            g.setColor(Color.red);
            g.fillRect(kenar1, kenar2, r, r);
            
              if(kenar1==this.getSize().width){
                kenar2=0;
                kenar1=0;
            }
        }
    }
}
