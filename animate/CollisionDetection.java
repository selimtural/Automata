package animate;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class CollisionDetection implements Animator {
	final Container pan = new Panel();
	public ArrayList<Obj> objList = new ArrayList<Obj>();
	
        CollisionDetection(){
	    pan.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent me) { 
            Random r = new Random();
            int x1 = r.nextInt(50-15) + 15;
            int y1 = r.nextInt(300-30) + 10;
            Obj o = new Obj(new Point(x1,y1),x1,-(Math.PI/12));
			objList.add(o);
          } 
        }); 
		Obj o1 = new Obj(new Point(100,50),40,-(Math.PI/12));
	    Obj o2 = new Obj(new Point(300,60),50,Math.PI/12);
		Obj o3 = new Obj(new Point(200,100),50,Math.PI/3);
		Obj o4 = new Obj(new Point(300,200),30,Math.PI/12);
	    objList.add(o1);
	    objList.add(o2);
		objList.add(o3);
		objList.add(o4);
	}
    public int doTick(){
        
		Update();
		
		pan.repaint(); //Render..
        return 50;
	}
    public Container container(){
		return pan;
	}
    public String description(){
		return "Basic collision detection - Click the pane for generate a new circle!";
	}
    public String author(){
		return "Furkan Sarihan";
	}
	public void Update(){	//Objects are getting
		for (Obj o : objList){
		o.Move();		    //their new location.
		
                if(isCollision(o)){		
		        Random r = new Random();
		        int ri = r.nextInt(6-0) + 0;
                        double t = r.nextDouble();
                        t = (double)ri+t;
			o.setAngle(t);
	    }
		if(o.getX()<o.getRadius()/2){o.setAngle(-(o.getAngle()-Math.PI));}
		else if(o.getX()>(MX*2)-(o.getRadius()/2)){o.setAngle(-(o.getAngle()-Math.PI));}
		else if(o.getY()<(o.getRadius()/2)){o.setAngle(-o.getAngle());}
		else if(o.getY()>(MY*2)-(o.getRadius()/2)){o.setAngle(-o.getAngle());}
	}
	}
	boolean isCollision(Obj oCur){
		for (Obj o : objList){
			if(!o.equals(oCur)){
				if((o.getRadius()+oCur.getRadius())>getDist(o.getX()-oCur.getX(),o.getY()-oCur.getY())){
					return true;
				}
			}
		}
		return false;
	}
	double getDist(double x,double y){
		return Math.sqrt((x*x)+(y*y));
	}
	class Panel extends javax.swing.JPanel { //drawing
	  
	  public void paint(Graphics g) {		 
		  g.clearRect(0, 0, MX*2, MY*2);
		  for (Obj o : objList){
			g.setColor(Color.CYAN);
			g.fillOval(o.getX(),o.getY(),o.getRadius(),o.getRadius());
		  }
	  }
	}
	public class Obj {
		Point P;
		int radius;
		double angle;
		int speed=12;
		
        Obj(Point po,int ra,double an){ P = po; radius= ra; angle = an; }
		
        public void setAngle(double a){angle = a;}
		public int getX(){ return (int)P.getX(); }
		public int getY(){ return (int)P.getY(); }
		public int getRadius(){ return radius; }
		public double getAngle(){ return angle; }

		public void Move(){
			P.setLocation((double)(P.getX()+(Math.cos(angle)*speed)),
                          (double)(P.getY()-(Math.sin(angle)*speed)));
        }
	}
}
