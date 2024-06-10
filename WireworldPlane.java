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
    private int generation;//counter of generation
    private int population;//counter of the population
    private int[][]statistic;//statistic of he population
private String logicWiresEnteredDirection;//variable which detects the direction o fthe logic wire wheere the mouse is on
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
        speed = new int[]{1, 0};//initial speed
        isStopped = false;
        statistic = new int[5][2];

	//initial statistic
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
                		//getting type of wire
                    		if(cell.getElement() instanceof BasicWire) {//if the element is basicwire
                				type = 1;//sets right type
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
                        			addWireToStatistic(1,1);//adding wire to statistic array
                        			addWireToStatistic(type,-1);//deleting previous wire from this cell
                        			cell.removeElement();//remove element from the cell
                        			wire = new BasicWire(x,y,cell);//new Basic wire
                        			
                        			cell.addElement(wire);//adding wire to the cell

						//rest else statements work in the same way
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
			else if(cell.getElement() instanceof LogicWire) {
                    		int direction = ((LogicWire)cell.getElement()).getDirection();
                    		if(direction == 0) {
                    			logicWiresEnteredDirection = "right";//setting indicator of the direction as the appropariate direction
                    		}
                    		else if(direction == 1) {
                    			logicWiresEnteredDirection = "up";
                    		}
                    		else if(direction == 2) {
                    			logicWiresEnteredDirection = "left";
                    		}
                    		else if(direction == 3) {
                    			logicWiresEnteredDirection = "down";
                    		}
                    		else {
                    			logicWiresEnteredDirection = null;
                    		}
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
			logicWiresEnteredDirection = null;
                    }
                    @Override 
                    public void mouseReleased(MouseEvent e) {
                    	isPressed =false;//changing isPressed wire
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
				//changing the direction of the plane based on clicked keys
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
     * Method which updates periodically and is used to update plane
     *
     * @param:Graphics g
     */
    @Override
    public void paintComponent(Graphics g) {
        ArrayList<ArrayList> aliveElectrons = new ArrayList<ArrayList>();//electrons which are alive after cheking all specific cases
        if (!isStopped) {
        	generation+=1;//new generatiob
            for (int i = 0; i < electrons.size(); i++) {//going through all electrons
                Electron el = electrons.get(i);//getting electron
                int[] pos = el.getPos();//getting its position
               
                    Wire currentWire = (Wire) map[pos[0]][pos[1]].getElement();//getting current wire in the electron's cell
                    if (currentWire != null) {//removing electrons from previous wire
                        currentWire.removeElectrons();
                    }
                    el.moveElectron();//moving electron
                    pos = electrons.get(i).getPos();//new position of the electron
                    Wire newWire = (Wire) map[pos[0]][pos[1]].getElement();//new electron's wire

                    if (newWire == null) {//if electron's wire doesn't exist removing electron
                        electrons.remove(i);
                        i -= 1;
                    } else if (newWire.doesContainElectrons()) {//if there is electrons in the wire
                        electrons.remove(i);//remove all of them
                        
                        ArrayList<Electron> els = newWire.getElectrons();//getting arrylist of electrons
                        for (Electron j : els) {
                            
                            electrons.remove(j);//removing them
                        }
                        newWire.removeElectrons();//clering wire
                        i -= 1;
                    } else {//otherwise
			   // adding them in the aliveelectrons
                        ArrayList pair = new ArrayList();
                        pair.add(newWire);
                        pair.add(el);
                        aliveElectrons.add(pair);
                    }

                

            }
		//putting alive electrons in the newwires
            for (ArrayList i : aliveElectrons) {
                ((Wire) i.get(0)).addElectron((Electron) i.get(1));
            }
		//creating electrons from all sources
            for (int i = 0; i < sources.size(); i++) {
                sources.get(i).createSignals();
            }
		//checking logic conditions of the logicwires
            for (int i = 0; i < logicWires.size(); i++) {
                logicWires.get(i).logicChecking();
            }
		//sorting statistic
            mergeSort(0,statistic.length-1);
		//calculating population
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
     */
    public static int getCurrentWireType() {
        return currentWireType;
    }

    /**
     * Method which changes playandpause statuts isStopped
     *
     * @return: ActionEvent evt
     */
    public void playOrPauseWireWorld(java.awt.event.ActionEvent evt){
        isStopped = (!isStopped);
    }
    
    
    // 0 --> delete wire, 1 --> basic, 2 --> source, 3 --> delete wire, 4 --> or, 5 --> and, 6 --> xor
    public void setDeleteWire(java.awt.event.ActionEvent evt) {
        currentWireType = 0;
    }
	 /**
     * Method which sets basicwire as the currentWiretype
     *
     * @param:Action event evt
     */
    public void setBasicWire(java.awt.event.ActionEvent evt) {
        currentWireType = 1;
    }
	/**
     * Method which sets sourcewire as the currentWiretype
     *
     * @param:Action event evt
     */
    public void setSourceWire(java.awt.event.ActionEvent evt) {
        currentWireType = 2;
    }
/**
     * Method which sets orwire as the currentWiretype
     *
     * @param:Action event evt
     */
    public void setORWire(java.awt.event.ActionEvent evt) {
        currentWireType = 3;
    }
/**
     * Method which sets andwire as the currentWiretype
     *
     * @param:Action event evt
     */
    public void setANDWire(java.awt.event.ActionEvent evt) {
        currentWireType = 4;
    }
/**
     * Method which sets xorwire as the currentWiretype
     *
     * @param:Action event evt
     */
    public void setXORWire(java.awt.event.ActionEvent evt) {
        currentWireType = 5;
    }
	/**
     * Method which adds electron in the global electrons arrayList
     *
     * @param:Electron electron
     */
    public static void addElectron(Electron electron) {
        electrons.add(electron);
    }
/**
     * Method which removes electron from the global electrons arrayList
     *
     * @param:Electron electron
     */
    public static void removeElectron(Electron electron) {
        electrons.remove(electron);
    }
	/**
     * Method which adds source in the global sources arrayList
     *
     * @param:SourceWire source
     */
    public static void addSource(SourceWire source) {
        sources.add(source);
    }
	/**
     * Method which removes source in the global sources arrayList
     *
     * @param:SourceWire source
     */
    public static void removeSource(SourceWire source) {
        if (sources.indexOf(source) != -1) {
            sources.remove(source);
        }

    }
	/**
     * Accessor of the direction
     *
     * @return int[] speed - direction/speed which is chosen now
     */
    public int[] getDirection() {
    	return speed;
    }
	/**
     * Method which adds logicwire in the global logicwires arrayList
     *
     * @param:LogicWire logicWire
     */
    public static void addLogicWire(LogicWire logicWire) {
        logicWires.add(logicWire);
    }
/**
     * Method which removes logicwire from the global logicwires arrayList
     *
     * @param:LogicWire logicWire
     */
    public static void removeLogicWire(LogicWire logicWire) {
        if (logicWires.indexOf(logicWire) != -1) {
            logicWires.remove(logicWire);
        }

    }
	/**
     * The method which saves configuration file
     * @return: none
     */
    public void saveConfig() {
    	try {
        	int[][] numMap = new int[width][height];// creatin integer array to save the configuration
        	
        	for(int i =0;i<width;i++) {//going through map to check each cell
        		for(int j =0;j<height;j++) {
        			Cell cell = map[i][j];//taking the cell in specific position
        			Element el = cell.getElement();//taking the element from the cell

				//checking which instanse it is and based on that it is ggiving speciffic nubertype of the wire
        			if(el instanceof BasicWire) {
        				numMap[i][j] = 1;
        			}
        			else if(el instanceof SourceWire) {
        				numMap[i][j] = 2;
        			}
        			else if(el instanceof ORWire) {
					//getDirection is getting the direction of logic wire based on specefic number
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
        	String fileName = "config1";//creating a default name of the config file
    		
        	
        	int index = 1;
    		while(Files.exists(Paths.get(fileName+".ser"))) {//checking if this name already exists
    			
        		index+=1;//new index
        		fileName = fileName.substring(0,6)+Integer.toString(index);//new name with new index
    		}
    		
    		ObjectOutputStream out = new ObjectOutputStream(//creating serialization of the config 
        		    new FileOutputStream(fileName+".ser")
        		);
        	out.writeObject(numMap);//writing array there
        	out.flush();//flushing the stream
        	out.close();//closing the stream
    	}
    	catch(IOException e){
    		System.out.println(e);
    	}
    }
	/**
     * The method which reads the configuration file
     * @param: String path; - path of the file
     * @return: none
     */
    public void readConfig(String path) {
    	try {
    	    
    		ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));// reading the file
    		int[][] array = (int[][]) in.readObject();// reading file into int array
    		in.close();//closing stream
    		Cell[][] newMap = new Cell[width][height];//new map 
		//clearing all arraylists with specifi elements
    		sources.clear();
    		electrons.clear();
    		logicWires.clear();
    		//going through the config
    		for(int i =0;i<width;i++) {
        		for(int j =0;j<height;j++) {
        			int type = array[i][j];//getting type of the wire
        			Cell cell = map[i][j];//getting cell 
        			int x = cell.getPos()[0];//x of the cell in the map
            		int y = cell.getPos()[1];//y of the cell in the map
        			Element wire = null;//declaring wire
        			cell.removeElement();// removing element from there
        			int[] dir = null;// direction of the wire if wire is logic one
				//based on last difgit we can determine the direction of the wire
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

				//based on type putting choosing wire
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
        			
        			cell.addElement(wire);//adding wire into cell
        			
        		}
    		}
    		
    		plane.revalidate();//updating 
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
	/**
     * Adding s specific number of wires to statistic array
     * @param int type - type of the array
     * @param int add - number of wires
     * 
     * */
    private void addWireToStatistic(int type,int add) {
    	for(int i=0;i<statistic.length;i++) {//going through entire statistic
    		if(statistic[i][0] == type) {//if we found this type
    			statistic[i][1] = statistic[i][1]+add;//adding this number
    			break;//finishing loop
    		}
    	}
    }
	/**
     * Method which returns the statistic of the grid 
     * @return String str - appropriate form of the statistic
     * 
     * */
public String toStringStatistic() {
    	String str = "Generation: "+generation
    			+"\nPopulation: "+population;//adding population and generation
    	for(int i =0;i<statistic.length;i++) {//going through statistic array
    		int type = statistic[i][0];
		//adding specific wire based on the type in the array
    		if(type ==1) {
    			str+="\nBasic Wire: ";
    		}
    		else if(type ==2) {
    			str+="\nSource Wire: ";
    		}
    		else if(type ==3) {
    			str+="\nOR Wire: ";
    		}
    		else if(type ==4) {
    			str+="\nAND Wire: ";
    		}
    		else if(type ==5) {
    			str+="\nXOR Wire: ";
    		}
		//adding number next to wire
    		str+=statistic[i][1];
    		
    	}
    	if(logicWiresEnteredDirection != null) {//if there mouse is on the logic wire
			str+="\nDirection: "+logicWiresEnteredDirection;
		}
    	
    	
    	return str;
    	
    }
}
