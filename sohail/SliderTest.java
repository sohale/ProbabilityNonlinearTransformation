package sohail;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;

import javax.swing.JFrame;

public class SliderTest extends JFrame {

	private static final long serialVersionUID = 111111L;
	public static void main(String[] args) {
		SliderTest j=new SliderTest();
		j.setSize(600, 400);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("bye.");
	}
	public void paint(Graphics g){
		super.paint(g);
		//java.util.Timer
		//Timer t = new Timer();
		double t=System.currentTimeMillis()/1000.0 % 1000;
		//Timer.this
		g.drawString(t+"", 200,200);
		g.drawOval(50,	 50, 10, (int) (10+Math.random()));
		for (int i=0;i<100;i++)
			for (int j=0;j<100;j++){
				g.setColor(new Color(i % 20,5,5));
				g.drawLine(i, i, 0, 200);
			}
		repaint();
	}

}
