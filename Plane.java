import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Plane extends JPanel implements Runnable{
	private JPanel plane;
	private JFrame main;
	private int width;
	private int height;
	private Thread animator;
	private final int DELAY  = 1000;
	protected Cell[][] map;
	
	public Plane(int width,int height,Cell[][] map,JFrame main) {
		super();
		this.main = main;
		this.width = width;
		this.height = height;
		this.map = map;
		initUI(width,height,map);
		
	}
	public JFrame getMain() {
		return main;
	}
	
	private void initUI(int width,int height,Cell[][] map){
		plane = new JPanel();
		
		plane.setBorder(BorderFactory.createLineBorder(Color.white));
		plane.setLayout(new GridLayout(width,height));
		plane.setVisible(true);
		
		
		for(int i =0;i<height;i++) {
			for(int j =0;j<width;j++) {
				Cell cell = new Cell(this,j,i);
				map[j][i] = cell;
				plane.add(cell);
				
			}
			
		}
		
		add(plane,BorderLayout.CENTER);
		

	}
	
	
	@Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }
	
	@Override
	public void run() {
		
		long beforeTime,timeDiff,sleep;
		beforeTime = System.currentTimeMillis();
		while(true) {
			main.repaint();
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;
			if(sleep<0) {
				sleep = 2;
			}
			try {
				Thread.sleep(sleep);
			}
			catch(Exception e) {
				
			}
			beforeTime = System.currentTimeMillis();
		}
	}
}
