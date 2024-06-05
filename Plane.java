import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Plane extends JPanel implements Runnable{
	private JPanel plane;//JPanel value of the actual plane
	protected JFrame main;//JFrame which plane was created from
	protected int width;//width of the grid plane
	protected int height;//height of the grid plane
	private Thread animator;
	private final int DELAY  = 1000;//variable which measure the length of the delay
	protected Cell[][] map;//the array of the instance of class Cell
	/**
	 * Constructor of the Plane class
	 * @param:int width - the width of grid(number of cells in the width)
	 * @param:int height - the height of grid(number of cells in the height)
	 * @param:Cell[][] map -  array of the cells 
	 * @param:JFrame main - main JFrame class
	 */
	public Plane(int width,int height,Cell[][] map,JFrame main) {
		super();
		this.main = main;
		this.width = width;
		this.height = height;
		this.map = map;
		
		
	}
	/**
	 * Accessor of the main variable;
	 * @return: JFrame main
	 */
	public JFrame getMain() {
		return main;
	}
	/**
	 * Method which creates the peer of the canvas. This peer allows you to change the user interface of the canvas without changing its functionality.
	 * @return: none
	 */
	@Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }
	
	@Override
	public void run() {
		
		long beforeTime,timeDiff,sleep;//declaring all necessary methods
		beforeTime = System.currentTimeMillis();//saving current time
		while(true) {//Infinite loop
			main.repaint();//repainting main JFrame
			timeDiff = System.currentTimeMillis() - beforeTime;//calculating time difference between before time and current one
			sleep = DELAY - timeDiff;//setting sleep value based on difference between delay and timedifference
			if(sleep<0) {//setting minimum sleep value
				sleep = 2;
			}
			try {
				Thread.sleep(sleep);//sleeping the loop for sleep's value
			}
			catch(Exception e) {
				
			}
			beforeTime = System.currentTimeMillis();//resaving current time
		}
	}
	/**
	 * Accessor of the array of cell instances
	 * @return: Cell[][] map - array of the cells
	 */
	public Cell[][] getMap(){
		return map;
	}
}
