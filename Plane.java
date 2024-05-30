import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Plane extends JPanel implements Runnable{
	private JPanel plane;
	protected JFrame main;
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
		
		
	}
	public JFrame getMain() {
		return main;
	}
	
	protected MouseAdapter panelMove(JPanel jpanel) {
		return new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
            		
            	
            	
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
            	System.out.println(e.getID());
            	
            	
            }
            @Override
            public void mouseMoved(MouseEvent e) {
            	
            	System.out.println(e.getID());
            	
            }
            @Override 
            public void mouseReleased(MouseEvent e) {
            	
            }
		};
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
	public Cell[][] getMap(){
		return map;
	}
}
