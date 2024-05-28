import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.BorderFactory;

public class Cell extends JPanel{
	private  Cell  cell;
	private int x,y;
	private  Plane plane;
	private Element el;
	private boolean isTaken;
	
	public Cell(Plane plane,int x,int y) {
		super();
		this.x = x;
		this.y = y;
		initUI();
		isTaken = false;
		this.plane = plane;
		cell = this;
	}
	private void initUI() {
		
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.lightGray);
		setSize(100,100);
//		slot.addMouseListener(new SlotListener());
		
		addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
            	
            		            		
//            		if(Plane.getCurrentArrowType() == 1) {
//            			el = new BasicWire(x,y,slot,board.getSpeed(),board.getDirection());
//            		}
//            		else if(Plane.getCurrentArrowType() == 2) {
//            			el = new SourceWire(x,y,slot,board.getSpeed(),board.getDirection());
//            			plane.addSource((SourceArrow)arrow);
//            		}
//                    
                    isTaken = true;
                    
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
	public void setElement(Element el) {
		this.el = el;
	}
	public  Element getElement() {
		return this.el;
	}
}
