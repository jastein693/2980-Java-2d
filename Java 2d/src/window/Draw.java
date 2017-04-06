package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Draw extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, ActionListener{
	private List<List<ColorVector>> frames = new ArrayList<List<ColorVector>>();
	private List<ColorVector> lines = new ArrayList<ColorVector>();
	private JButton open;
	private int currentFrame = 0;
    private Color color= Color.black;
	Point lastMouseLocation = null;
	float cameraX = 0;
    float cameraY = 0;
    int mode = 0;
    
    float cameraZoom = 1;
	
	public Draw() {
		this.setLayout(new BorderLayout());
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
		
		JButton paint = new JButton ("Paint");
		paint.addActionListener(this);
		
		JButton camera = new JButton ("Camera");
		camera.addActionListener(this);
		
		JPanel menu = new JPanel();
		JPanel sidemenu = new JPanel();
		sidemenu.setLayout(new BoxLayout(sidemenu, BoxLayout.Y_AXIS));
		
		menu.add(open);
		menu.add(newFrame);
		menu.add(left);
		menu.add(right);
		
        sidemenu.add(red);
        sidemenu.add(blue);
        sidemenu.add(green);
        sidemenu.add(black);
        sidemenu.add(paint);
        sidemenu.add(camera);
		
		this.add(menu, BorderLayout.PAGE_START);
		this.add(sidemenu, BorderLayout.LINE_START);
		this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        
        frames.add(lines);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(e.getPoint().x > 100 && e.getPoint().x < 1800 && e.getPoint().y > 50 && e.getPoint().y < 950 && mode == 0) {
			frames.get(currentFrame).add(new ColorVector(lastMouseLocation, color));
			lastMouseLocation = new Point((int) ((e.getPoint().x - (this.getWidth()/2))/cameraZoom) , (int) ((e.getPoint().y - (this.getHeight()/2)) / cameraZoom));
			repaint();
		}
		
		if(e.getPoint().x > 100 && e.getPoint().x < 1800 && e.getPoint().y > 50 && e.getPoint().y < 950 && mode == 1) {
			int diffX = lastMouseLocation.x - (e.getPoint().x - (this.getWidth()/2));
	        int diffY = lastMouseLocation.y - (e.getPoint().y - (this.getHeight()/2));
	        
	        
	        cameraX -= diffX / cameraZoom;
	        cameraY -= diffY / cameraZoom;
	        
	        
	        lastMouseLocation = new Point(e.getPoint().x - (this.getWidth()/2), e.getPoint().y - (this.getHeight()/2));
	        
	        repaint();
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
        if(e.getActionCommand() == "Paint") {
        	mode = 0;
        }
        if(e.getActionCommand() == "Camera") {
        	mode = 1;
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
		
			lastMouseLocation = new Point((int) ((e.getPoint().x - (this.getWidth()/2))/cameraZoom) , (int) ((e.getPoint().y - (this.getHeight()/2)) / cameraZoom));
		
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getPoint().x > 100 && e.getPoint().x < 1800 && e.getPoint().y > 50 && e.getPoint().y < 950 && mode == 0) {
			frames.get(currentFrame).add(null);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		 Graphics2D g2d = (Graphics2D)g;
		 g.setColor(Color.black);
			g.drawRect(100, 50, 1700, 900);
	        ///Fill the background
	        //g2d.setColor(Color.CYAN);
	        //g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	        
	        
	        //Create the transform to recenter the camera
	        //and scale and invert y (we want y up)
	        
	        AffineTransform transform = new AffineTransform();
	        transform.scale(cameraZoom, cameraZoom);
	       transform.translate(this.getWidth()/2 / cameraZoom, this.getHeight()/2 / cameraZoom);
	       transform.translate(cameraX, cameraY);
	        //transform.translate(this.getWidth()/(2 * cameraZoom) + cameraX, this.getHeight()/(2*cameraZoom)  +cameraY);
	        
	        
	        
	        g2d.setTransform(transform);
		
		g.setColor(Color.white);
		//g.fillRect(200, 150, 1500, 700);
		g.fillRect(-750, -350, 1500, 700);
		
            
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

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		 //Don't add and subtract from zoom!!!!
        //Instead, multiply
        
        if(e.getPreciseWheelRotation() > 0)
        {
            cameraZoom *= 1.1;
        }
        else if(e.getPreciseWheelRotation() < 0)
        {
            cameraZoom /= 1.1;
        }
        
       
        
        repaint();
		
	}

}