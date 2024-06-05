import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class LogicWire extends Wire{
	protected int[] direction;
	protected ImageIcon img;
	public LogicWire(int x, int y, Cell parent, Color color, int[] direction) {
		super(x, y, parent, color);
		this.direction = direction;
		WireworldPlane.addLogicWire(this);
	}
	public  boolean logicChecking() {
		if(condition()) {
			for(Electron i:electrons) {
				WireworldPlane.removeElectron(i);
			}
			electrons.clear();
			electrons.add(new Electron(x,y,direction));
			WireworldPlane.addElectron(electrons.get(0));
			return true;
		}
		else {
			for(Electron i:electrons) {
				WireworldPlane.removeElectron(i);
			}
			electrons.clear();
		}
		return false;
	}
	public abstract boolean condition();
	@Override
	public void addElectron(Electron electron) {
		int[] elDirection = electron.getSpeed();
		if(elDirection != direction && elDirection[0] != -direction[0]&& elDirection[1] != -direction[1]) {
			electrons.add(electron);
		}
		else {
			WireworldPlane.removeElectron(electron);
		}
		
	}
	@Override
	public void removeElectrons() {
		electrons.clear();
		
	}

	@Override
	public boolean doesContainElectrons() {
		return electrons.size() != 0;
	}
	public ImageIcon getImg() {
		return img;
	}
	@Override
	public ArrayList<Electron> getElectrons() {
		return electrons;
		
	}
	public int getDirection() {
		int a1 = direction[0];
		int a2 = direction[1];
		if(a1 == 1 && a2 == 0) {
			return 0;
		}
		else if(a1 == 0 && a2 == -1) {
			return 1;
		}
		else if(a1 == -1 && a2 == 0) {
			return 2;
		}
		else if(a1 == 0 && a2 == -1) {
			return 3;
		}
		return -1;
	}

}
