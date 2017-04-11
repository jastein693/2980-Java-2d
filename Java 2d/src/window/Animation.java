package window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation extends JFrame implements Runnable{

	public static void main(String[] args) {
		Animation animation = new Animation();
	}
	
	public Animation() {
		this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Animation");
       // JPanel menu = new JPanel();
        //JButton open = new JButton("Open");
        //menu.add(open);
        //this.add(menu);
        Draw draw = new Draw();
       
        this.setLayout(new BorderLayout());
        JButton open = new JButton("Play");
		open.setHorizontalTextPosition(JButton.LEFT);
		open.addActionListener(draw);
		
		JButton newFrame = new JButton("New Frame");
		newFrame.addActionListener(draw);
		
		JButton right = new JButton (">");
		right.addActionListener(draw);
		
		JButton left = new JButton ("<");
		left.addActionListener(draw);
                
        JButton red = new JButton ("red");
		red.addActionListener(draw);
                
        JButton blue = new JButton ("blue");
		blue.addActionListener(draw);
                
        JButton green = new JButton ("green");
		green.addActionListener(draw);
                
        JButton black = new JButton ("black");
		black.addActionListener(draw);
		
		JButton paint = new JButton ("Paint");
		paint.addActionListener(draw);
		
		JButton camera = new JButton ("Camera");
		camera.addActionListener(draw);
		
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
		
        JButton place1 = new JButton ("PlaceHolder");
        JButton place2 = new JButton ("PlaceHolder");
        
		this.add(menu, BorderLayout.PAGE_START);
		this.add(sidemenu, BorderLayout.LINE_START);
        
        this.add(place2, BorderLayout.LINE_END);
        this.add(place1, BorderLayout.PAGE_END);
        this.add(draw, BorderLayout.CENTER);
        //this.pack();
        //this.setContentPane(menu);
        
        //open.setSize(10, 10);
        //open.setVerticalAlignment((int) JButton.TOP_ALIGNMENT);
        this.setBounds(0, 0, 1920, 1080);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.add(open);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
