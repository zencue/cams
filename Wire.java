import java.awt.Color;

public abstract class Wire extends Element{
	private int x;
	private int y;
	private Cell parent;
	private String dir;
	private int[] speed;
	
	public Wire(int x,int y,Cell parent,Color color,int[] speed,String dir) {
		this.dir = dir;
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.parent = parent;

		parent.setBackground(color);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public abstract void addElectron(Electron signal);
	public abstract void removeElectron();
	public void setBackground(Color color) {
		parent.setBackground(color);
	}

	public abstract boolean doesContainElectron();
	public abstract Electron getElectron();
	public int[] getSpeed() {
		return this.speed;
	}
}
