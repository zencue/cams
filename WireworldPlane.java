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
	private static int currentWireType;
	private JPanel plane;
	private String dir;
	private int[] speed;
	public WireworldPlane(int width,int height,Cell[][] map,JFrame main) {
		super(width, height, map, main);
		electrons = new ArrayList<Electron>();
		sources = new ArrayList<SourceWire>();
		currentWireType = 1;
		this.dir = "right";
		this.speed = new int[] {1,0};
		initUI(width,height,map);
		
	}
	private void initUI(int width,int height,Cell[][] map){
		plane = new JPanel();
		
		plane.setBorder(BorderFactory.createLineBorder(Color.black));
		plane.setLayout(new GridLayout(width,height));
		plane.setVisible(true);
		plane.addMouseListener(panelMove(this));
		WireworldPlane planeItSelf = this;
		for(int i =0;i<height;i++) {
			for(int j =0;j<width;j++) {
				Cell cell = new Cell(this,j,i);
				map[j][i] = cell;
				plane.add(cell);
				cell.addMouseListener(new MouseAdapter() {
					boolean status = false;
		            @Override
		            public void mouseClicked(MouseEvent e) {
		            		
		            	
		            	
		                
		            }
		            
		            @Override
		            public void mousePressed(MouseEvent e) {
		            	status = true;
		            	int x = cell.getPos()[0];
	            		int y = cell.getPos()[1];
	            		if(status) {
	            			Element wire = null;  		
		            		if(WireworldPlane.getCurrentWireType() == 1) {
		            			wire = new BasicWire(x,y,cell);
		            			cell.addElement(wire);
		            		}
		            		else if(WireworldPlane.getCurrentWireType() == 2) {
		            			
		            			wire = new SourceWire(x,y,cell);
		            			planeItSelf.addSource((SourceWire)wire);
		            			cell.addElement(wire);
		            		}
		            		else if(WireworldPlane.getCurrentWireType() == 0) {
		            			
		            			cell.removeElement();
		            		}
		                    
		                    
		                    
		                    plane.revalidate();
	            		}
		            	
		            }
		            @Override
		            public void mouseMoved(MouseEvent e) {
		            	int x = cell.getPos()[0];
	            		int y = cell.getPos()[1];
	            		if(status) {
	            			Element wire = null;  		
		            		if(WireworldPlane.getCurrentWireType() == 1) {
		            			wire = new BasicWire(x,y,cell);
		            		}
		            		else if(WireworldPlane.getCurrentWireType() == 2) {
		            			
		            			wire = new SourceWire(x,y,cell);
		            			planeItSelf.addSource((SourceWire)wire);
		            		}
		                    cell.addElement(wire);
		                    
		                    
		                    plane.revalidate();
	            		}
	            		
		            	
		            	
		            }
		            @Override 
		            public void mouseReleased(MouseEvent e) {
		            	status = false;
		            }
		        });
			}
			
		}
		add(plane,BorderLayout.CENTER);
		plane.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				System.out.println(key);
				if(key == '2') {
					currentWireType = 2;
				}
				else if(key == '1') {
					currentWireType = 1;
				
				}
				else if(key == '0') {
					currentWireType = 0;
				
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
		ArrayList<Integer[]>proccessedWires = new ArrayList<Integer[]>();
		ArrayList<ArrayList> aliveElectrons = new ArrayList<ArrayList>();
		for(int i=0;i<electrons.size();i++){
			Electron el = electrons.get(i);
			int[] pos = el.getPos();
			boolean status = true;
//			for(Integer[] j:proccessedWires) {
//				if(j[0] == pos[0] && j[1] == pos[1]) {
//					status = false;
//					break;
//				}
//			}
			if(status) {
					Wire currentWire = (Wire)map[pos[0]][pos[1]].getElement();
					if(currentWire != null) {
						currentWire.removeElectrons();
					}
					el.moveElectron();
					pos =electrons.get(i).getPos();
					Wire newWire = (Wire)map[pos[0]][pos[1]].getElement();
					
					if(newWire == null) {
						electrons.remove(i);
						i-=1;
					}
					else{
//						newWire.addElectron(electrons.get(i));
						ArrayList pair = new ArrayList();
						pair.add(newWire);
						pair.add(el);
						aliveElectrons.add(pair);
					}
					
					
//					proccessedWires.add(new Integer[] {pos[0],pos[1]});
			}
			
			
		}
		for(ArrayList i:aliveElectrons) {
			((Wire)i.get(0)).addElectron((Electron)i.get(1));
		}
		for(int i = 0;i<sources.size();i++) {
			sources.get(i).createSignals();
		}
		
		
	}
	public static int getCurrentWireType() {
		return currentWireType;
	}
	public static void addElectron(Electron electron) {
		electrons.add(electron);
	}
	public static void removeElectron(Electron electron) {
		electrons.remove(electron);
	}
	public static void addSource(SourceWire source) {
		sources.add(source);
	}
	public static void removeSource(SourceWire source) {
		if(sources.indexOf(source)!=-1) {
			sources.remove(source);
		}
		
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
