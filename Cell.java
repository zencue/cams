import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.BorderFactory;

public class Cell extends JPanel{
	
	private int x,y;//coordinates of the cell
	private static Color color;//color of the cell
	private  Plane plane;//plane wjere cell is placed
	private Element el;//element to contain(either wire or GOL element)
	
	/**
     * Constructor of the Cell class
     *@param:Plane parent 
     * @param:int x 
     * @param:int y 
     *
     */
	public Cell(Plane plane,int x,int y) {
		super();
		this.x = x;
		this.y = y;
		initUI();
		
		this.plane = plane;
		
		color = Color.lightGray;
		
	}
	/**
     *Method which makes cell looks nice
     */
	private void initUI() {
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.lightGray);
		setSize(100,100);
	}
	/**
	     *Accessor of the Plane
	     *@return Plane plane
	     *
     */
	public Plane getPlane() {
		return plane;
	}
	/**
	     *Mutator of theelement
	     *@param Element el
	     *
     */
	public void setElement(Element el) {
		this.el = el;
	}
	/**
	     *Accessor of th eelement
	     *@return Element el
	     *
     */
	public  Element getElement() {
		return this.el;
	}
	public void addElement(Element el) {
		this.el = el;
	}
	/**
	     *Method which removes element from the cell 
	     *@return Element el
	     *
     */
	public void removeElement() {
		if(el instanceof SourceWire) {
			((WireworldPlane)plane).removeSource((SourceWire)el);
		}
		
		this.el = null;
		setBackground(Color.lightGray);
	}
	/**
	     *Accessor of the position of the cell
	     *@return int[] {x,y} - postition
	     *
     */
	public int[] getPos() {
		return new int[] {x,y};
	}
	/**
	     *Accessor of the color of the cell
	     *@return Coor color
	     *
     */
	public Color getColor() {
		return color;
	}
}
