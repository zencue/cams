import java.awt.Color;
import java.util.ArrayList;

public abstract class Wire extends Element{
	protected int x;
	protected int y;
	protected Cell parent;
	
	
	public Wire(int x,int y,Cell parent,Color color) {
		this.x = x;
		this.y = y;
		this.parent = parent;

		parent.setBackground(color);
	}
	public int[] getPos() {
		return new int[]{x,y};
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public abstract void addElectron(Electron signal);
	public abstract void removeElectrons();
	public void setBackground(Color color) {
		parent.setBackground(color);
	}
	public Cell getParent() {
		return parent;
	}
	public abstract boolean doesContainElectrons();
	public abstract ArrayList<Electron>  getElectrons();

}
