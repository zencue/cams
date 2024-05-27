
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Board extends JPanel implements Runnable{
	private JPanel board;
	private JFrame main;
	private int width;
	private int height;
	private Thread animator;
	private final int DELAY  = 1000;
	private static ArrayList<Signal> signals;
	private static ArrayList<SourceArrow> sources;
	private Slot[][] map;
	private static int currentArrowType;
	private String dir;
	private int[] speed;
	
	
	public Board(int width,int height,Slot[][] map,JFrame main) {
//		this.currentArrow = new BasicArrow();;
		super();
		this.main = main;
		this.width = width;
		this.height = height;
		this.map = map;
		initUI(width,height,map);
		signals = new ArrayList<Signal>();
		sources = new ArrayList<SourceArrow>();
		currentArrowType = 1;
		this.dir = "right";
		this.speed = new int[] {1,0};
		
	}
	
	public JFrame getMain() {
		return main;
	}
	
	private void initUI(int width,int height,Slot[][] map){
		board = new JPanel();
		
		board.setBorder(BorderFactory.createLineBorder(Color.white));
		board.setLayout(new GridLayout(width,height));
		board.setVisible(true);
		
		
		for(int i =0;i<height;i++) {
			for(int j =0;j<width;j++) {
				Slot slot = new Slot(this,j,i);
				if(j == 30 && i == 30) {
					slot.setBackground(Color.black);
				}
				map[j][i] = slot;
				board.add(slot);
				
			}
			
		}
		
		add(board,BorderLayout.CENTER);
		main.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				
				if(key == '2') {
					currentArrowType = 2;
				}
				else if(key == '1') {
					currentArrowType = 1;
				
				}
				
			 }

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				
				if(code == 40) {
					dir = "down";
					setSpeed(new int[]{0,1});
				}
				else if(code == 39) {
					dir = "right";
					setSpeed(new int[]{1,0});
				}
				else if(code == 38) {
					dir = "up";
					setSpeed(new int[]{0,-1});
				}
				else if(code == 37) {
					dir = "left";
					setSpeed(new int[]{-1,0});
					
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});

	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		for(int i=0;i<signals.size();i++) {
			int[] pos = signals.get(i).getPos();
			Arrow curArrow = map[pos[0]][pos[1]].getArrow();
			if(curArrow != null) {
				curArrow.removeSignal();
			}
			signals.get(i).moveSignal();
			pos = signals.get(i).getPos();
			
			if(map[pos[0]][pos[1]].getArrow() == null) {
				signals.remove(i);
				i-=1;
			}
			else {
				map[pos[0]][pos[1]].getArrow().addSignal(signals.get(i));
			}
			
		}
		for(int i = 0;i<sources.size();i++) {
			sources.get(i).createSignal();
		}
		
		super.paintComponent(g);
		
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
	public static int getCurrentArrowType() {
		return currentArrowType;
	}
	
	public static void addSignal(Signal signal) {
		signals.add(signal);
	}
	public static void addSource(SourceArrow source) {
		sources.add(source);
	}
	public int[] getSpeed() {
		return speed;
	}
	public String getDirection() {
		return dir;
	}
	public void setSpeed(int[] speed) {
		this.speed = speed;
	}
}
