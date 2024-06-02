import java.awt.Color;
import java.util.ArrayList;

public abstract class Wire extends Element{
	protected int x;
	protected int y;
	protected Cell parent;
	protected ArrayList<Electron> electrons;
	protected Cell[][]map;
	
	public Wire(int x,int y,Cell parent,Color color) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.map = parent.getPlane().getMap();
		this.electrons = new ArrayList<Electron>();
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
	public abstract void addElectron(Electron electron);
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
