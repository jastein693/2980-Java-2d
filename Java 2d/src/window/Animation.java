package window;

import java.awt.event.ActionEvent;

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
        this.add(new Draw());
        this.pack();
        //this.setContentPane(menu);
        
        //open.setSize(10, 10);
        //open.setVerticalAlignment((int) JButton.TOP_ALIGNMENT);
        this.setBounds(0, 0, 1920, 1080);
        //this.add(open);
       
       
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
