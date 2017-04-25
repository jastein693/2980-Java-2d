package window;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Render extends JPanel implements Runnable, ActionListener{
	private static List<List<ColorVector>> frames = new ArrayList<List<ColorVector>>();
	private int currentFrame = 0;
	//private long elapsedTime;
	public float frameRate = 24;

	public Render(List<List<ColorVector>> frames) {
		this.frames = frames;
		Thread thread = new Thread( this );
	    thread.start();
	}

	@Override
	public void run() {
		//long curTime = System.currentTimeMillis();
		//long lastTime = curTime;
		//double delta;
		while(true) {
			try {
				Thread.sleep((long) (1 / frameRate * 1000));
			} catch (InterruptedException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}
			//curTime = System.currentTimeMillis();
			//delta = curTime - lastTime;
			//float delta = (float) (nsPerFrame);
			//elapsedTime += delta;
			//if(elapsedTime % (int)(1 / frameRate * 1000) == 0) {
				
				//System.out.println(currentFrame);
				repaint();
				++currentFrame;
				if(currentFrame >= frames.size()) {
					currentFrame = 0;
				}
			//}
			//System.out.println((int)(1 / frameRate * 1000));
			
			//lastTime = curTime;
		}
	}
	
	public void paint( Graphics g ) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		AffineTransform transform = new AffineTransform();
		transform.translate(this.getWidth()/2, this.getHeight()/2);
		g2d.setTransform(transform);
		g.setColor(Color.white);
		g.fillRect(-this.getWidth()/2, -this.getHeight()/2, this.getWidth(), this.getHeight());
		for( int i = 0; i < frames.get(currentFrame).size() - 1; ++i ) {
	          ColorVector p1 = (ColorVector) frames.get(currentFrame).get( i );
	          ColorVector p2 = (ColorVector) frames.get(currentFrame).get( i + 1 );
	          
	          // Adding a null into the list is used
	          // for breaking up the lines when
	          // there are two or more lines
	          // that are not connected
	          if( !(p1 == null || p2 == null)) {
	        	 //g.setColor( p2.getColor());
                      g.setColor(p1.color);
                      g2d.setStroke(new BasicStroke(p1.width,              
                              BasicStroke.CAP_ROUND,   
                              BasicStroke.JOIN_MITER));
                      g.drawLine( p1.point.x, p1.point.y, p2.point.x, p2.point.y );  
	          }
		 }
	}

	public static void main( String[] args ) {
	      //final Render app = new Render(frames);
	   }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
