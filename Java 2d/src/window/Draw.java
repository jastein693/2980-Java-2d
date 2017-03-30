package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Draw extends JPanel implements MouseListener, MouseMotionListener, ActionListener{
	private List<List<Point>> frames = new ArrayList<List<Point>>();
	private List<Point> lines = new ArrayList<Point>();
	private JButton open;
	private int currentFrame = 0;
	Point lastMouseLocation = null;
	
	public Draw() {
		this.setPreferredSize(new Dimension(400, 400));
		this.setSize(new Dimension(400, 400));
		
		open = new JButton("Open");
		open.setHorizontalTextPosition(JButton.LEFT);
		open.addActionListener(this);
		
		JButton newFrame = new JButton("New Frame");
		newFrame.addActionListener(this);
		
		JButton right = new JButton (">");
		right.addActionListener(this);
		
		JButton left = new JButton ("<");
		left.addActionListener(this);
		
		JPanel menu = new JPanel();
		menu.add(open);
		menu.add(newFrame);
		menu.add(left);
		menu.add(right);
		
		this.add(menu);
		this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        frames.add(lines);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(e.getPoint().x > 100 && e.getPoint().x < 1800 && e.getPoint().y > 50 && e.getPoint().y < 950) {
			frames.get(currentFrame).add(lastMouseLocation);
			lastMouseLocation = e.getPoint();
			repaint();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Open") {
			
		}
		if(e.getActionCommand() == "New Frame") {
			frames.add(currentFrame + 1, new ArrayList<Point>());
			currentFrame += 1;
			repaint();
		}
		if(e.getActionCommand() == "<" && currentFrame != 0) {
			currentFrame -= 1;
			repaint();
		}
		if(e.getActionCommand() == ">" && currentFrame != frames.size()-1) {
			currentFrame += 1;
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getPoint().x > 100 && e.getPoint().x < 1800 && e.getPoint().y > 50 && e.getPoint().y < 950) {
			lastMouseLocation = e.getPoint();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getPoint().x > 100 && e.getPoint().x < 1800 && e.getPoint().y > 50 && e.getPoint().y < 950) {
			frames.get(currentFrame).add(null);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.white);
		g.fillRect(200, 150, 1500, 700);
		g.setColor(Color.black);
		g.drawRect(100, 50, 1700, 900);
		System.out.println(frames.get(currentFrame).size());
		for( int i = 0; i < frames.get(currentFrame).size() - 1; ++i ) {
	          Point p1 = (Point) frames.get(currentFrame).get( i );
	          Point p2 = (Point) frames.get(currentFrame).get( i + 1 );
	          
	          // Adding a null into the list is used
	          // for breaking up the lines when
	          // there are two or more lines
	          // that are not connected
	          if( !(p1 == null || p2 == null)) {
	        	 //g.setColor( p2.getColor());
	             g.drawLine( p1.x, p1.y, p2.x, p2.y );  
	          }
		 }
	}

}