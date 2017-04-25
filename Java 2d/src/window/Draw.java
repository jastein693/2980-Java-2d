package window;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Draw extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, ActionListener{
	private List<List<ColorVector>> frames = new ArrayList<List<ColorVector>>();
	private List<ColorVector> lines = new ArrayList<ColorVector>();
	private int currentFrame = 0;
    private Color color= Color.black;
	Point lastMouseLocation = null;
	Point lastMouseLocationCamera = null;
	float cameraX = 0;
    float cameraY = 0;
    int mode = 0;
    int canvasWidth = 1920;
    int canvasHeight = 1080;
    public float lineWidth = 1f;
    public int frameRate = 24;
    float cameraZoom = 0.7f;
    private boolean onionSkin = false;
	
	public Draw() {
		//this.setLayout(new BorderLayout());
		//this.setPreferredSize(new Dimension(400, 400));
		//this.setSize(new Dimension(400, 400));
		
		
		this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        
        frames.add(lines);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(mode == 0) {
			
			ColorVector temp = new ColorVector(lastMouseLocation, color);
			temp.width = lineWidth;
			frames.get(currentFrame).add(temp);
			lastMouseLocation = new Point((int) ((e.getPoint().x - (this.getWidth()/2))/cameraZoom - cameraX), (int) ((e.getPoint().y - (this.getHeight()/2)) / cameraZoom - cameraY));
			repaint();
		}
		
		if( mode == 1) {
			int diffX = lastMouseLocationCamera.x - (e.getPoint().x - (this.getWidth()/2));
	        int diffY = lastMouseLocationCamera.y - (e.getPoint().y - (this.getHeight()/2));
	        
	        
	        cameraX -= diffX / cameraZoom;
	        cameraY -= diffY / cameraZoom;
	        
	        
	        lastMouseLocationCamera = new Point(e.getPoint().x - (this.getWidth()/2), e.getPoint().y - (this.getHeight()/2));
	        
	        repaint();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Play") {
			JFrame rFrame = new JFrame();
			Render render = new Render(frames);
			render.setDoubleBuffered(true);
			rFrame.add(render);
			rFrame.setVisible(true);
			rFrame.setBounds(0, 0, canvasWidth, canvasHeight);
			//render.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			rFrame.setTitle("Play");
			render.frameRate = frameRate;
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
        if(e.getActionCommand() == "Onion Skin") {
        	onionSkin = !onionSkin;
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
		
		
			lastMouseLocation = new Point((int) ((e.getPoint().x - (this.getWidth()/2))/cameraZoom - cameraX) , (int) ((e.getPoint().y - (this.getHeight()/2)) / cameraZoom - cameraY));
			lastMouseLocationCamera = new Point(e.getPoint().x - (this.getWidth()/2), e.getPoint().y - (this.getHeight()/2));
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(mode == 0) {
			frames.get(currentFrame).add(null);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		 Graphics2D g2d = (Graphics2D)g;
		 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		//g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		g.setColor(Color.black);
		g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
		 
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
		g.fillRect(-canvasWidth/2, -canvasHeight/2, canvasWidth, canvasHeight);
		
		 
		
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
                        g2d.setStroke(new BasicStroke(p1.width,              
                                BasicStroke.CAP_ROUND,   
                                BasicStroke.JOIN_MITER));
                        g.drawLine( p1.point.x, p1.point.y, p2.point.x, p2.point.y );  
                         
	             
	          }
		 }

		if(currentFrame != 0 && onionSkin) {
			for( int i = 0; i < frames.get(currentFrame-1).size() - 1; ++i ) {
		          ColorVector p1 = (ColorVector) frames.get(currentFrame-1).get( i );
		          ColorVector p2 = (ColorVector) frames.get(currentFrame-1).get( i + 1 );
		          
		          // Adding a null into the list is used
		          // for breaking up the lines when
		          // there are two or more lines
		          // that are not connected
		          if( !(p1 == null || p2 == null)) {
		        	 //g.setColor( p2.getColor());
		        	  	g2d.setColor( new Color(p1.color.getRed()/255, p1.color.getGreen()/255, p1.color.getBlue()/255, .3f));
	                      g2d.setStroke(new BasicStroke(p1.width,              
	                              BasicStroke.CAP_ROUND,   
	                              BasicStroke.JOIN_MITER));
	                      g.drawLine( p1.point.x, p1.point.y, p2.point.x, p2.point.y );  
		          }
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