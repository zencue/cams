import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class WireworldPlane extends Plane {

    private static ArrayList<Electron> electrons;//array of the all electrons in the plane
    private static ArrayList<SourceWire> sources;//array of the all sources in the plane
    private static ArrayList<LogicWire> logicWires;//array of the all logicWires in the plane
    private static int currentWireType;//the index of the type of the wire, to indicate whic hwire user wnat to use
    private JPanel plane;
    private int[] speed;//direction, which user chose to put logci wires
    private boolean isPressed;// variable which check if mouse is pressed
    private boolean isStopped;//variable which checks if program is stopped
    private int generation;
    private int population;
    private int[][]statistic;

    /**
     * Constructor of the WireworldPlane class
     *
     * @param:int width - the width of grid(number of cells in the width)
     * @param:int height - the height of grid(number of cells in the height)
     * @param:Cell[][] map - array of the cells
     * @param:JFrame main - main JFrame class
     *
     *
     */
    public WireworldPlane(int width, int height, Cell[][] map, JFrame main) {
        super(width, height, map, main);
        electrons = new ArrayList<Electron>();
        sources = new ArrayList<SourceWire>();
        logicWires = new ArrayList<LogicWire>();
        currentWireType = 1;
        initUI(width, height, map);
        speed = new int[]{1, 0};
        isStopped = false;
        statistic = new int[5][2];
        statistic[0][0] = 1;
        statistic[1][0] = 2;
        statistic[2][0] = 3;
        statistic[3][0] = 4;
        statistic[4][0] = 5;
    }

    /**
     * Method which creates all important gui elements on the gridd
     *
     * @param:int width - the width of grid(number of cells in the width)
     * @param:int height - the height of grid(number of cells in the height)
     * @param:Cell[][] map - array of the cells
     *
     *
     */
    private void initUI(int width, int height, Cell[][] map) {
        plane = new JPanel();//plane which has gridlayout

        plane.setBorder(BorderFactory.createLineBorder(Color.black));// creating border
        plane.setLayout(new GridLayout(width, height));//setting the gridd
        plane.setVisible(true);
        WireworldPlane planeItSelf = this;// the instance of this class to call it in the cell mouse adapter
        //going through entire map
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell(this, j, i);//creating new cell
                map[j][i] = cell;// putting in the map
                plane.add(cell);//adding in the plane
                cell.addMouseListener(new MouseAdapter() {//setting mouse listener, to check if cell is clicked
        			boolean status;
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                    	if(e.getButton() == 1){
                    		isPressed = true;//setting isPressed tru
                        	int x = cell.getPos()[0];//x of the cell in the map
                    		int y = cell.getPos()[1];//y of the cell in the map
                    		int type =-1;
                			
                    		if(cell.getElement() instanceof BasicWire) {
                				type = 1;
                			}
                			else if(cell.getElement() instanceof SourceWire) {
                				type = 2;
                			}
                			else if(cell.getElement() instanceof ORWire) {
                				type = 3;
                			}
                			else if(cell.getElement() instanceof ANDWire) {
                				type = 4;
                			}
                			else if(cell.getElement() instanceof XORWire) {
                				type = 5;
                			}
                    			Element wire = null;//declaring wire
                        		if(WireworldPlane.getCurrentWireType() == 1) {//putting basic wire
                        			addWireToStatistic(1,1);
                        			addWireToStatistic(type,-1);
                        			cell.removeElement();
                        			wire = new BasicWire(x,y,cell);
                        			
                        			cell.addElement(wire);
                        		}
                        		else if(WireworldPlane.getCurrentWireType() == 2) {//putting source wire
                        			addWireToStatistic(2,1);
                        			addWireToStatistic(type,-1);
                        			cell.removeElement();
                        			wire = new SourceWire(x,y,cell);
                        			
                        			cell.addElement(wire);
                        		}
                        		else if(WireworldPlane.getCurrentWireType() == 3) {//putting OR wire
                        			addWireToStatistic(3,1);
                        			addWireToStatistic(type,-1);
                        			cell.removeElement();
                        			wire = new ORWire(x,y,cell,getDirection());
                        			
                        			cell.addElement(wire);
                        		}
                        		else if(WireworldPlane.getCurrentWireType() == 4) {//putting AND wire
                        			addWireToStatistic(4,1);
                        			addWireToStatistic(type,-1);
                        			cell.removeElement();
                        			wire = new ANDWire(x,y,cell,getDirection());
                        			
                        			cell.addElement(wire);
                        		}
                        		else if(WireworldPlane.getCurrentWireType() == 5) {//putting XOR wire
                        			addWireToStatistic(5,1);
                        			addWireToStatistic(type,-1);
                        			cell.removeElement();
                        			wire = new XORWire(x,y,cell,getDirection());
                        			
                        			cell.addElement(wire);
                        		}
                        		else if(WireworldPlane.getCurrentWireType() == 0) {//removing wire
                        			
                        			
                        			addWireToStatistic(type,-1);
                        			cell.removeElement();
                        		}
                                
                                
                           
                                plane.revalidate();//updating wire
                    	}
                    	
                		
                    	
                    }
                    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                    	
                    	if(isPressed) {//doing the same thing as mousePressed if mouse is still pressed
                    		int x = cell.getPos()[0];
                    		int y = cell.getPos()[1];
                    		
                    			Element wire = null;  		
                    			int type =-1;
                    			
                        		if(cell.getElement() instanceof BasicWire) {
                    				type = 1;
                    			}
                    			else if(cell.getElement() instanceof SourceWire) {
                    				type = 2;
                    			}
                    			else if(cell.getElement() instanceof ORWire) {
                    				type = 3;
                    			}
                    			else if(cell.getElement() instanceof ANDWire) {
                    				type = 4;
                    			}
                    			else if(cell.getElement() instanceof XORWire) {
                    				type = 5;
                    			}
                        			
                            		if(WireworldPlane.getCurrentWireType() == 1) {//putting basic wire
                            			addWireToStatistic(1,1);
                            			addWireToStatistic(type,-1);
                            			cell.removeElement();
                            			wire = new BasicWire(x,y,cell);
                            			
                            			cell.addElement(wire);
                            		}
                            		else if(WireworldPlane.getCurrentWireType() == 2) {//putting source wire
                            			addWireToStatistic(2,1);
                            			addWireToStatistic(type,-1);
                            			cell.removeElement();
                            			wire = new SourceWire(x,y,cell);
                            			
                            			cell.addElement(wire);
                            		}
                            		else if(WireworldPlane.getCurrentWireType() == 3) {//putting OR wire
                            			addWireToStatistic(3,1);
                            			addWireToStatistic(type,-1);
                            			cell.removeElement();
                            			wire = new ORWire(x,y,cell,getDirection());
                            			
                            			cell.addElement(wire);
                            		}
                            		else if(WireworldPlane.getCurrentWireType() == 4) {//putting AND wire
                            			addWireToStatistic(4,1);
                            			addWireToStatistic(type,-1);
                            			cell.removeElement();
                            			wire = new ANDWire(x,y,cell,getDirection());
                            			
                            			cell.addElement(wire);
                            		}
                            		else if(WireworldPlane.getCurrentWireType() == 5) {//putting XOR wire
                            			addWireToStatistic(5,1);
                            			addWireToStatistic(type,-1);
                            			cell.removeElement();
                            			wire = new XORWire(x,y,cell,getDirection());
                            			
                            			cell.addElement(wire);
                            		}
                            		else if(WireworldPlane.getCurrentWireType() == 0) {//removing wire
                            			
                            			
                            			addWireToStatistic(type,-1);
                            			cell.removeElement();
                            		}
                                    
        	                    
        	                    
        	                    
        	                    plane.revalidate();
                    	}
                    	else if(cell.getElement()==null){//if there is no wires in the cell
                    		cell.setBackground(Color.WHITE);
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
//           
        add(plane, BorderLayout.CENTER);//putting the Grid plane inside this WireWorld panel
        this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
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
				// TODO Auto-generated method stub
				
			}
        	
        });
        this.setFocusable(true);
    }

    /**
     * Method which updates periodically
     *
     * @param:Graphics g
     *
     *
     *
     */
    @Override
    public void paintComponent(Graphics g) {
        ArrayList<ArrayList> aliveElectrons = new ArrayList<ArrayList>();//electrons which are alive after cheking all specific cases
        if (!isStopped) {
        	generation+=1;
            for (int i = 0; i < electrons.size(); i++) {//going through all electrons
                Electron el = electrons.get(i);
                int[] pos = el.getPos();
                boolean status = true;
                if (status) {
                    Wire currentWire = (Wire) map[pos[0]][pos[1]].getElement();
                    if (currentWire != null) {
                        currentWire.removeElectrons();
                    }
                    el.moveElectron();
                    pos = electrons.get(i).getPos();
                    Wire newWire = (Wire) map[pos[0]][pos[1]].getElement();

                    if (newWire == null) {
                        electrons.remove(i);
                        i -= 1;
                    } else if (newWire.doesContainElectrons()) {
                        electrons.remove(i);
                        int counter = 0;
                        ArrayList<Electron> els = newWire.getElectrons();
                        for (Electron j : els) {
                            counter += 1;
                            electrons.remove(j);
                        }
                        newWire.removeElectrons();
                        i -= 1;
                    } else {
                        ArrayList pair = new ArrayList();
                        pair.add(newWire);
                        pair.add(el);
                        aliveElectrons.add(pair);
                    }

                }

            }
            for (ArrayList i : aliveElectrons) {
                ((Wire) i.get(0)).addElectron((Electron) i.get(1));
            }
            for (int i = 0; i < sources.size(); i++) {
                sources.get(i).createSignals();
            }
            for (int i = 0; i < logicWires.size(); i++) {
                logicWires.get(i).logicChecking();
            }
            mergeSort(0,statistic.length-1);
            population = 0;
            for(int i = 0;i<statistic.length;i++) {
            	population +=statistic[i][1];
            }
            
        }
    }

    /**
     * Accessor of the current wire type
     *
     * @return: int currentWireType
     *
     *
     *
     */
    public static int getCurrentWireType() {
        return currentWireType;
    }

    
    public void playOrPauseWireWorld(java.awt.event.ActionEvent evt){
        isStopped = (!isStopped);
    }
    
    
    // 0 --> delete wire, 1 --> basic, 2 --> source, 3 --> delete wire, 4 --> or, 5 --> and, 6 --> xor
    public void setDeleteWire(java.awt.event.ActionEvent evt) {
        currentWireType = 0;
    }

    public void setBasicWire(java.awt.event.ActionEvent evt) {
        currentWireType = 1;
    }

    public void setSourceWire(java.awt.event.ActionEvent evt) {
        currentWireType = 2;
    }

    public void setORWire(java.awt.event.ActionEvent evt) {
        currentWireType = 3;
    }

    public void setANDWire(java.awt.event.ActionEvent evt) {
        currentWireType = 4;
    }

    public void setXORWire(java.awt.event.ActionEvent evt) {
        currentWireType = 5;
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
        if (sources.indexOf(source) != -1) {
            sources.remove(source);
        }

    }
    public int[] getDirection() {
    	return speed;
    }

    public static void addLogicWire(LogicWire logicWire) {
        logicWires.add(logicWire);
    }

    public static void removeLogicWire(LogicWire logicWire) {
        if (logicWires.indexOf(logicWire) != -1) {
            logicWires.remove(logicWire);
        }

    }
    public void saveConfig() {
    	try {
//    		File config = new File("config.txt");
//        	boolean status = config.createNewFile();
        	int[][] numMap = new int[width][height];
        	
        	for(int i =0;i<width;i++) {
        		for(int j =0;j<height;j++) {
        			Cell cell = map[i][j];
        			Element el = cell.getElement();
        			if(el instanceof BasicWire) {
        				numMap[i][j] = 1;
        			}
        			else if(el instanceof SourceWire) {
        				numMap[i][j] = 2;
        			}
        			else if(el instanceof ORWire) {
        				numMap[i][j] = 3*10+((LogicWire)el).getDirection();
        			}
        			else if(el instanceof ANDWire) {
        				numMap[i][j] = 4*10+((LogicWire)el).getDirection();
        			}
        			else if(el instanceof XORWire) {
        				numMap[i][j] = 5*10+((LogicWire)el).getDirection();
        			}
        			else {
        				numMap[i][j] = 0;
        			}
        		}
        		
        	}
        	String fileName = "config1";
    		
        	
        	int index = 1;
    		while(Files.exists(Paths.get(fileName+".ser"))) {
    			
        		index+=1;
        		fileName = fileName.substring(0,6)+Integer.toString(index);
    		}
    		
    		ObjectOutputStream out = new ObjectOutputStream(
        		    new FileOutputStream(fileName+".ser")
        		);
        	out.writeObject(numMap);
        	out.flush();
        	out.close();
    	}
    	catch(IOException e){
    		System.out.println(e);
    	}
    }
    public void readConfig(String path) {
    	try {
    	    
    		ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
    		int[][] array = (int[][]) in.readObject();
    		in.close();
    		Cell[][] newMap = new Cell[width][height];
    		sources.clear();
    		electrons.clear();
    		logicWires.clear();
    		
    		for(int i =0;i<width;i++) {
        		for(int j =0;j<height;j++) {
        			int type = array[i][j];
        			Cell cell = map[i][j];
        			int x = cell.getPos()[0];//x of the cell in the map
            		int y = cell.getPos()[1];
        			Element wire = null;//declaring wire
        			cell.removeElement();
        			int[] dir = null;
        			if(type>=10) {
        				int dirType = type%10;
        				if(dirType == 0) {
        					dir = new int[] {1,0};
        				}
        				else if(dirType == 1) {
        					dir = new int[] {0,-1};
        				}
        				else if(dirType == 2) {
        					dir = new int[] {-1,0};
        				}
        				else if(dirType == 3) {
        					dir = new int[] {0,-1};
        				}
        			}
        			if(type == 1) {
        				wire = new BasicWire(x,y,cell);
        			}
        			else if( type ==2) {
        				wire = new SourceWire(x,y,cell);
        				
        			}
        			else if( type/10 ==3) {
        				wire = new ORWire(x,y,cell,dir);
        				
        			}
        			else if( type/10 ==4) {
        				wire = new ANDWire(x,y,cell,dir);
        				
        			}
        			else if( type/10 ==5) {
        				wire = new XORWire(x,y,cell,dir);
        				
        			}
        			
        			cell.addElement(wire);
        			
        		}
    		}
    		
    		plane.revalidate();
    	}
    	catch(Exception e){
    		System.out.println(e);
    	}
    }
    /**
     * The method which sort the array using merge sort
     * @parameter: int l; - the index of the left bound
     * @parameter: int r - the index of the right bound
     * @return: none
     */
    private  void mergeSort(int l,int r){
        if(l<r){//base case
            
            int m = l+(r-l)/2;//middle index
            mergeSort(l,m);//the same partition for the left part
            mergeSort(m+1,r);//the same partition of the right part
            
            merge(l,m,r);
            
            
        }
    }
    /**
     * The method which merge the sort in ascending order
     * @parameter: int l; - the index of the left bound
     * @parameter: int m - the middle index;
     * @parameter: int r - the index of the right bound
     * @return: none
     */
    private  void merge(int l,int m,int r){
        int n1 = m-l+1;//length of the left part
        int n2 = r-m;//length of the right part
        int[][] L = new int[n1][2];//left part
        int[][] R = new int[n2][2];//right part
        for(int i =0;i<n1;i++){//copy left part
            
            L[i] = statistic[l+i];
        }
        for(int i =0;i<n2;i++){//copy right part
            
            R[i] = statistic[m+1+i];
        }
        
        int i=0;//index of left part
        int j =0;//index of right part
        int k =l;// index of the next element
        while(i<n1&& j<n2){// until we achieve last element of one of the parts
            
         // checking which is smaller from the each parts
            if((L[i][1]>=R[j][1])){
                statistic[k] = L[i];// put in the array from the left part
                i++;// increasing id
            }
            else{
                statistic[k] = R[j];// put in the array from the right part
                j++;
            }
            k++;//next element
        }
        
        //fill the array with remained elements from left part
        while((i<n1)){
            
            statistic[k] =  L[i];
            i++;
            k++;
            
        }
        //fill the array with the remained elements from the right part
        while((j<n2)){
            
            statistic[k] =  R[j];
            j++;
            k++;
        }
    }
    private void addWireToStatistic(int type,int add) {
    	for(int i=0;i<statistic.length;i++) {
    		if(statistic[i][0] == type) {
    			statistic[i][1] = statistic[i][1]+add;
    			break;
    		}
    	}
    }
}
