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
	public int[] getPos() {
		return new int[] {x,y};
	}
}
