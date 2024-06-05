import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.BorderFactory;

public class Cell extends JPanel{
	private  Cell  cell;
	private int x,y;
	private static Color color;
	private  Plane plane;
	private Element el;
	private boolean isTaken;
	private ImageIcon img;
	private JLabel bg;
	private static boolean isPressed;
	
	public Cell(Plane plane,int x,int y) {
		super();
		this.x = x;
		this.y = y;
		initUI();
		isTaken = false;
		this.plane = plane;
		cell = this;
		color = Color.lightGray;
		
	}
	private void initUI() {
		 bg = new JLabel(img);
		 bg.setSize(this.getWidth(),this.getHeight());
		 bg.setBorder(BorderFactory.createEmptyBorder());
		add(bg,BorderLayout.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.lightGray);
		setSize(100,100);
		addMouseListener(new MouseAdapter() {//setting mouse listener, to check if cell is clicked
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
                			wire = new ORWire(x,y,cell,((WireworldPlane)plane).getDirection());
                			
                			cell.addElement(wire);
                		}
                		else if(WireworldPlane.getCurrentWireType() == 4) {//putting AND wire
                			cell.removeElement();
                			wire = new ANDWire(x,y,cell,((WireworldPlane)plane).getDirection());
                			
                			cell.addElement(wire);
                		}
                		else if(WireworldPlane.getCurrentWireType() == 5) {//putting XOR wire
                			cell.removeElement();
                			wire = new XORWire(x,y,cell,((WireworldPlane)plane).getDirection());
                			
                			cell.addElement(wire);
                		}
                		else if(WireworldPlane.getCurrentWireType() == 0) {//removing wire
                			
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
	            			wire = new ORWire(x,y,cell,((WireworldPlane)plane).getDirection());
	            			
	            			cell.addElement(wire);
	            		}
	            		else if(WireworldPlane.getCurrentWireType() == 4) {
	            			cell.removeElement();
	            			wire = new ANDWire(x,y,cell,((WireworldPlane)plane).getDirection());
	            			
	            			cell.addElement(wire);
	            		}
	            		else if(WireworldPlane.getCurrentWireType() == 5) {
	            			cell.removeElement();
	            			wire = new XORWire(x,y,cell,((WireworldPlane)plane).getDirection());
	            			
	            			cell.addElement(wire);
	            		}
	            		else if(WireworldPlane.getCurrentWireType() == 0) {
	            			
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
		
		
//		slot.addMouseListener(new SlotListener());
	}
	public void setImage(ImageIcon img) {
		this.img = img;
		bg.setIcon(img);
		
	}
	public Plane getPlane() {
		return plane;
	}
	public void setElement(Element el) {
		this.el = el;
	}
	public  Element getElement() {
		return this.el;
	}
	public void addElement(Element el) {
		this.el = el;
	}
	public void removeElement() {
		if(el instanceof SourceWire) {
			((WireworldPlane)plane).removeSource((SourceWire)el);
		}
		
		this.el = null;
		setBackground(Color.lightGray);
	}
	public int[] getPos() {
		return new int[] {x,y};
	}
	public Color getColor() {
		return color;
	}
}
