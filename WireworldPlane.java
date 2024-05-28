import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WireworldPlane extends Plane{
	private static ArrayList<Electron> electrons;
	private static ArrayList<SourceWire> sources;
	private static int currentArrowType;
	private JPanel plane;
	private String dir;
	private int[] speed;
	public WireworldPlane(int width,int height,Cell[][] map,JFrame main) {
		super(width, height, map, main);
		electrons = new ArrayList<Electron>();
		sources = new ArrayList<SourceWire>();
		currentArrowType = 1;
		this.dir = "right";
		this.speed = new int[] {1,0};
		initUI(width,height,map);
		
	}
	private void initUI(int width,int height,Cell[][] map){
		plane = new JPanel();
		
		plane.setBorder(BorderFactory.createLineBorder(Color.black));
		plane.setLayout(new GridLayout(width,height));
		plane.setVisible(true);

		WireworldPlane planeItSelf = this;
		for(int i =0;i<height;i++) {
			for(int j =0;j<width;j++) {
				Cell cell = new Cell(this,j,i);
				map[j][i] = cell;
				plane.add(cell);
				cell.addMouseListener(new MouseAdapter() {

		            @Override
		            public void mouseClicked(MouseEvent e) {
		            		int x = cell.getPos()[0];
		            		int y = cell.getPos()[1];
		            		Element wire = null;  		
		            		if(planeItSelf.getCurrentArrowType() == 1) {
		            			wire = new BasicWire(x,y,cell,planeItSelf.getSpeed(),planeItSelf.getDirection());
		            		}
		            		else if(planeItSelf.getCurrentArrowType() == 2) {
		            			wire = new SourceWire(x,y,cell,planeItSelf.getSpeed(),planeItSelf.getDirection());
		            			planeItSelf.addSource((SourceWire)wire);
		            		}
		                    cell.addElement(wire);
		                    
		                    
		                    plane.revalidate();
		            	
		            	
		                
		            }
		            boolean isPressed = false;
					int initialX;
		        	int initialY;
		            @Override
		            public void mousePressed(MouseEvent e) {
		            	
		            	initialX = e.getX();
		            	initialY = e.getY();
		            	isPressed = true;
		            	System.out.println(initialX);
		            	
		            }
		            @Override
		            public void mouseMoved(MouseEvent e) {
		            	
		            	
		            	
		            }
		            @Override 
		            public void mouseReleased(MouseEvent e) {
		            	isPressed = false;
		            }
		        });
			}
			
		}
		add(plane,BorderLayout.CENTER);
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
		
		for(int i=0;i<electrons.size();i++) {
			int[] pos = electrons.get(i).getPos();
			Wire currentWire = (Wire)map[pos[0]][pos[1]].getElement();
			if(currentWire != null) {
				currentWire.removeElectron();
			}
			electrons.get(i).moveSignal();
			pos = electrons.get(i).getPos();
			Wire newWire = (Wire)map[pos[0]][pos[1]].getElement();
			if(newWire == null) {
				electrons.remove(i);
				i-=1;
			}
			else {
				newWire.addElectron(electrons.get(i));
			}
			
		}
		for(int i = 0;i<sources.size();i++) {
			sources.get(i).createSignal();
		}
		
		
	}
	public static int getCurrentArrowType() {
		return currentArrowType;
	}
	public static void addElectron(Electron signal) {
		electrons.add(signal);
	}
	public static void addSource(SourceWire source) {
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
