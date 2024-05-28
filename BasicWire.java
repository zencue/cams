import java.awt.Color;

public class BasicWire extends Wire{
	private Wire wire;
	private Electron electron;
	
	public BasicWire(int x, int y,Cell parent,int[] speed, String dir) {
		super(x,y,parent,Color.green,speed,dir);
		wire = this;
		
	}
	
	public void addElectron(Electron electron) {
		
		this.electron = electron;
		
		this.electron.setSpeed(this.getSpeed());
		setBackground(Color.red);

	}
	public boolean doesContainElectron() {
		return electron != null;
	}
	public void removeElectron() {
		electron = null;
		setBackground(Color.green);
		
	}
	public Electron getElectron() {
		return electron;
	}
}
