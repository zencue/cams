
import java.awt.Color;

import javax.swing.*;
import java.awt.event.*;

public class Slot extends JPanel{
	private  Slot  slot;
	private int x,y;
	private  Board board;
	private Arrow arrow;
	private boolean isTaken;
	
	public Slot(Board map,int x,int y) {
		super();
		this.x = x;
		this.y = y;
		initUI();
		isTaken = false;
		board = map;
		slot = this;
	}
	private void initUI() {
		
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.lightGray);
		setSize(100,100);
//		slot.addMouseListener(new SlotListener());
		
		addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
            	
            		            		
            		if(Board.getCurrentArrowType() == 1) {
            			arrow = new BasicArrow(x,y,slot,board.getSpeed(),board.getDirection());
            		}
            		else if(Board.getCurrentArrowType() == 2) {
            			arrow = new SourceArrow(x,y,slot,board.getSpeed(),board.getDirection());
            			board.addSource((SourceArrow)arrow);
            		}
                    
                    isTaken = true;
                    
                    board.revalidate();
            	
            	
                
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
            	System.out.println(initialX);
            	if(isPressed) {
            		board.setLocation(board.getX()+(initialX-e.getX()),board.getY()+(initialX-e.getY()));
            	}
            	
            	
            }
            @Override 
            public void mouseReleased(MouseEvent e) {
            	isPressed = false;
            }
        });
		
		

	}
	public void setArrow(Arrow arrow) {
		this.arrow = arrow;
	}
	public  Arrow getArrow() {
		return this.arrow;
	}
}

