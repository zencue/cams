import java.awt.Color;

import javax.swing.JPanel;


public abstract class Arrow {
	private int x;
	private int y;
	private Color color;
	private Slot parent;
	private String dir;
	private int[] speed;
	
	public Arrow(int x,int y,Slot parent,Color color,int[] speed,String dir) {
		this.dir = dir;
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.color = color;
		parent.setBackground(color);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public abstract void addSignal(Signal signal);
	public abstract void removeSignal();
	public void setBackground(Color color) {
		parent.setBackground(color);
	}
	public Color getColor() {
		return color;
	}
	public abstract boolean doesContainSignal();
	public abstract Signal getSignal();
	public int[] getSpeed() {
		return this.speed;
	}
}
