import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WireworldPlane extends Plane{
	private static ArrayList<Electron> electrons;//array of the all electrons in the plane
	private static ArrayList<SourceWire> sources;//array of the all sources in the plane
	private static ArrayList<LogicWire> logicWires;//array of the all logicWires in the plane
	private static int currentWireType;//the index of the type of the wire, to indicate whic hwire user wnat to use
	private JPanel plane;
	private boolean isStopped;
	private int[] speed;//direction, which user chose to put logci wires
	private boolean isPressed;// variables which check if mouse is pressed
	/**
	 * Constractor of the WireworldPlane class
	 * @param:int width - the width of grid(number of cells in the width)
	 * @param:int height - the height of grid(number of cells in the height)
	 * @param:Cell[][] map -  array of the cells 
	 * @param:JFrame main - main JFrame class
	 * 
	 * */
	public WireworldPlane(int width,int height,Cell[][] map,JFrame main) {
		super(width, height, map, main);
		electrons = new ArrayList<Electron>();
		sources = new ArrayList<SourceWire>();
		logicWires = new ArrayList<LogicWire>();
		currentWireType = 1;
		initUI(width,height,map);
		speed = new int[] {1,0};
		isStopped = false;
	}
	/**
	 *Method which creates all important gui elements on the gridd
	 * @param:int width - the width of grid(number of cells in the width)
	 * @param:int height - the height of grid(number of cells in the height)
	 * @param:Cell[][] map -  array of the cells 
	 * 
	 * */
	private void initUI(int width,int height,Cell[][] map){
		plane = new JPanel();//plane which has gridlayout
		
		plane.setBorder(BorderFactory.createLineBorder(Color.black));// creating border
		plane.setLayout(new GridLayout(width,height));//setting the gridd
		plane.setVisible(true);
		JPanel toolPanel = new JPanel();
		JButton play = new JButton("Play");
		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isStopped = false;
				
			}
			
		});
        JButton pause = new JButton("Pause");
        pause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isStopped = true;
				
			}
			
		});
       JButton  wwHomeBtn = new javax.swing.JButton();

       
        wwHomeBtn.setText("HOME");
        wwHomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ((CAMS_WireWorld)main).wwHomeBtnActionPerformed(evt);
           }
        });
        toolPanel.add(wwHomeBtn);
        toolPanel.add(play);
        toolPanel.add(pause);
// the instance of this class to call it in the cell mouse adapter
		//going through entire map
		for(int i =0;i<height;i++) {
			for(int j =0;j<width;j++) {
				Cell cell = new Cell(this,j,i);//creating new cell
				map[j][i] = cell;// putting in the map
				plane.add(cell);//adding in the plane
				
				cell.addMouseListener(new MouseAdapter() {//setting mouse listener, to check if cell is clicked
					boolean status;
		            @Override
		            public void mouseClicked(MouseEvent e) {
		            }
		            
		            @Override
		            public void mousePressed(MouseEvent e) {
		            	isPressed = true;//setting isPressed tru
		            	int x = cell.getPos()[0];//x of the cell in the map
	            		int y = cell.getPos()[1];//y of the cell in the map
	            		
	            			Element wire = null;//declaring wire
		            		if(WireworldPlane.getCurrentWireType() == 1) {//putting basic wire
		            			cell.removeElement();
		            			wire = new BasicWire(x,y,cell);
		            			cell.addElement(wire);
		            		}
		            		else if(WireworldPlane.getCurrentWireType() == 2) {//putting source wire
		            			cell.removeElement();
		            			wire = new SourceWire(x,y,cell);
		            			cell.addElement(wire);
		            		}
		            		else if(WireworldPlane.getCurrentWireType() == 3) {//putting OR wire
		            			cell.removeElement();
		            			wire = new ORWire(x,y,cell,speed);
		            			
		            			cell.addElement(wire);
		            		}
		            		else if(WireworldPlane.getCurrentWireType() == 4) {//putting AND wire
		            			cell.removeElement();
		            			wire = new ANDWire(x,y,cell,speed);
		            			
		            			cell.addElement(wire);
		            		}
		            		else if(WireworldPlane.getCurrentWireType() == 5) {//putting XOR wire
		            			cell.removeElement();
		            			wire = new XORWire(x,y,cell,speed);
		            			
		            			cell.addElement(wire);
		            		}
		            		else if(WireworldPlane.getCurrentWireType() == 0) {//removing wire
		            			
		            			cell.removeElement();
		            		}
		                    
		                    
		                    
		                    plane.revalidate();//updating wire
	            		
		            	
		            }
		            
		            @Override
		            public void mouseEntered(MouseEvent e) {
		            	if(isPressed) {//doing the same thing as mousePressed if mouse is still pressed
		            		int x = cell.getPos()[0];
		            		int y = cell.getPos()[1];
		            		
		            			Element wire = null;  		
			            		if(WireworldPlane.getCurrentWireType() == 1) {
			            			cell.removeElement();
			            			wire = new BasicWire(x,y,cell);
			            			cell.addElement(wire);
			            		}
			            		else if(WireworldPlane.getCurrentWireType() == 2) {
			            			cell.removeElement();
			            			wire = new SourceWire(x,y,cell);
			            			cell.addElement(wire);
			            		}
			            		else if(WireworldPlane.getCurrentWireType() == 3) {
			            			cell.removeElement();
			            			wire = new ORWire(x,y,cell,speed);
			            			
			            			cell.addElement(wire);
			            		}
			            		else if(WireworldPlane.getCurrentWireType() == 4) {
			            			cell.removeElement();
			            			wire = new ANDWire(x,y,cell,speed);
			            			
			            			cell.addElement(wire);
			            		}
			            		else if(WireworldPlane.getCurrentWireType() == 5) {
			            			cell.removeElement();
			            			wire = new XORWire(x,y,cell,speed);
			            			
			            			cell.addElement(wire);
			            		}
			            		else if(WireworldPlane.getCurrentWireType() == 0) {
			            			
			            			cell.removeElement();
			            		}
			                    
			                    
			                    
			                    plane.revalidate();
		            	}
		            	else if(cell.getElement()==null){//if there is no wires in the cell
		            		cell.setBackground(Color.blue);
		            	}
		            	
		            }
		            @Override 
		            public void mouseExited(MouseEvent e) {
		            	if(!isPressed && cell.getElement()==null) {//showing which wire would be placed if user clicked on the cell
		            		cell.setBackground(cell.getColor());
		            	}
		            }
		            @Override 
		            public void mouseReleased(MouseEvent e) {
		            	isPressed =false;
		            }
		        });
			}
			
		}
		add(toolPanel, BorderLayout.PAGE_START);
		add(plane,BorderLayout.CENTER);//putting the Grid plane inside this WireWorld panel
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
				char key = e.getKeyChar();
				//changing type of ire based on clicked key
				if(key == '2') {
					currentWireType = 2;
				}
				else if(key == '1') {
					currentWireType = 1;
				
				}
				else if(key == '3') {
					currentWireType = 3;
				
				}
				else if(key == '4') {
					currentWireType = 4;
				
				}
				else if(key == '5') {
					currentWireType = 5;
				
				}
				else if(key == '0') {
					currentWireType = 0;
				
				}
				
			 }

			@Override
			public void keyPressed(KeyEvent e) {
				
				//changing direction of the wire based on clicked wire
				if(e.getKeyCode() == 39) {
					speed = new int[] {1,0};
				}
				else if(e.getKeyCode() == 38) {
					speed = new int[] {0,-1};
				}
				else if(e.getKeyCode() == 37) {
					speed = new int[] {-1,0};
				}
				else if(e.getKeyCode() == 40) {
					speed = new int[] {0,1};
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});
		
	}
	/**
	 *Method which uoodates periodically
	 * @param:Graphics g
	 *
	 * 
	 * */
	@Override
	public void paintComponent(Graphics g) {
		if(!isStopped) {
			ArrayList<ArrayList> aliveElectrons = new ArrayList<ArrayList>();//electrons which are alive after cheking all specific cases
			for(int i=0;i<electrons.size();i++){//going through all electrons
				Electron el = electrons.get(i);
				int[] pos = el.getPos();
				boolean status = true;
//				for(Integer[] j:proccessedWires) {
//					if(j[0] == pos[0] && j[1] == pos[1]) {
//						status = false;
//						break;
//					}
//				}
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
						else if(newWire.doesContainElectrons()) {
							electrons.remove(i);
							int counter = 0;
							ArrayList<Electron> els = newWire.getElectrons();
							for(Electron j: els) {
								counter+=1;
								electrons.remove(j);
							}
							newWire.removeElectrons();
							i-=1;
						}
						else{
//							newWire.addElectron(electrons.get(i));
							ArrayList pair = new ArrayList();
							pair.add(newWire);
							pair.add(el);
							aliveElectrons.add(pair);
						}
						
						
//						proccessedWires.add(new Integer[] {pos[0],pos[1]});
				}
				
				
			}
			for(ArrayList i:aliveElectrons) {
				((Wire)i.get(0)).addElectron((Electron)i.get(1));
			}
			for(int i = 0;i<sources.size();i++) {
				sources.get(i).createSignals();
			}
			for(int i = 0;i<logicWires.size();i++) {
				logicWires.get(i).logicChecking();
			}
		}
		
		
		
	}
	/**
	 *Accessor of the current wire type
	 * @return: int currentWireType
	 *
	 * 
	 * */
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
	public static void addLogicWire(LogicWire logicWire) {
		logicWires.add(logicWire);
	}
	public static void removeLogicWire(LogicWire logicWire) {
		if(logicWires.indexOf(logicWire)!=-1) {
			logicWires.remove(logicWire);
		}
		
	}
}
