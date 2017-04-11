package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Render extends JFrame implements Runnable, ActionListener{
	private static List<List<ColorVector>> frames = new ArrayList<List<ColorVector>>();
	private float currentFrame = 0;

	public Render(List<List<ColorVector>> frames) {
		this.frames = frames;
		Thread thread = new Thread( this );
	    thread.start();
	}

	@Override
	public void run() {
		long curTime = System.nanoTime();
		long lastTime = curTime;
		double nsPerFrame;
		while(true) {
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			curTime = System.nanoTime();
			nsPerFrame = curTime - lastTime;
			float delta = (float) (nsPerFrame / 1.0E9);
			currentFrame += delta;
			if((int)currentFrame < frames.size()) {
				repaint();
			}
			if((int)currentFrame >= frames.size()) {
				currentFrame = 0;
			}
			lastTime = curTime;
		}
	}
	
	public void paint( Graphics g ) {
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform transform = new AffineTransform();
		transform.translate(this.getWidth()/2, this.getHeight()/2);
		g2d.setTransform(transform);
		g.setColor(Color.white);
		g.fillRect(-750, -350, 1500, 700);
		for( int i = 0; i < frames.get((int)currentFrame).size() - 1; ++i ) {
	          ColorVector p1 = (ColorVector) frames.get((int)currentFrame).get( i );
	          ColorVector p2 = (ColorVector) frames.get((int)currentFrame).get( i + 1 );
	          
	          // Adding a null into the list is used
	          // for breaking up the lines when
	          // there are two or more lines
	          // that are not connected
	          if( !(p1 == null || p2 == null)) {
	        	 //g.setColor( p2.getColor());
                      g.setColor(p1.color);
                      
                      g.drawLine( p1.point.x, p1.point.y, p2.point.x, p2.point.y );  
	          }
		 }
	}

	public static void main( String[] args ) {
	      final Render app = new Render(frames);
	   }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
