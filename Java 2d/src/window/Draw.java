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
	private List<List<ColorVector>> frames = new ArrayList<List<ColorVector>>();
	private List<ColorVector> lines = new ArrayList<ColorVector>();
	private JButton open;
	private int currentFrame = 0;
        private Color color= Color.black;
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
                
                JButton red = new JButton ("red");
		red.addActionListener(this);
                
                JButton blue = new JButton ("blue");
		blue.addActionListener(this);
                
                JButton green = new JButton ("green");
		green.addActionListener(this);
                
                JButton black = new JButton ("black");
		black.addActionListener(this);
		
		JPanel menu = new JPanel();
		menu.add(open);
		menu.add(newFrame);
		menu.add(left);
		menu.add(right);
                menu.add(red);
                menu.add(blue);
                menu.add(green);
                menu.add(black);
                
		
		this.add(menu);
		this.addMouseListener(this);
                this.addMouseMotionListener(this);
        
        frames.add(lines);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(e.getPoint().x > 100 && e.getPoint().x < 1800 && e.getPoint().y > 50 && e.getPoint().y < 950) {
			frames.get(currentFrame).add(new ColorVector(lastMouseLocation, color));
			lastMouseLocation = e.getPoint();
			repaint();
		}else{
                    //frames.get(currentFrame).add(null);
                    //lastMouseLocation = null;
                }
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Open") {
			
		}
		if(e.getActionCommand() == "New Frame") {
			frames.add(currentFrame + 1, new ArrayList<ColorVector>());
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
                if(e.getActionCommand() == "red") {
                    color = Color.red;
                }
                if(e.getActionCommand() == "blue") {
                    color = Color.blue;
                }
                if(e.getActionCommand() == "green") {
                    color = Color.green;
                }
                if(e.getActionCommand() == "black") {
                    color = Color.black;
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
            
		//System.out.println(frames.get(currentFrame).size());
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
	             g.drawLine( p1.point.x, p1.point.y, p2.point.x, p2.point.y );  
	          }
		 }
	}

}