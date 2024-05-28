import java.awt.Color;

public class SourceWire extends Wire{
	private Wire wire;
	private Electron electron;
	private int finish;
	private int counter;
	public SourceWire(int x, int y,Cell parent,int[] speed, String dir) {
		
		super(x,y,parent,Color.red, speed,  dir);
		
		counter = 0;
		finish = 3;
		wire = this;
		createSignal();
	}
	public void createSignal() {
		if(counter == finish) {
			electron = new Electron(this.getX(),this.getY(),getSpeed());
			WireworldPlane.addElectron(electron);
			counter = 0;
		}
		else {
			counter+=1;
		}
	}
	public boolean doesContainElectron() {
		return electron != null;
	}
	public void addElectron(Electron electron) {
		this.electron = electron;
		this.electron.setSpeed(getSpeed());
	}
	public void removeElectron() {
		electron = null;
	}
	public Electron getElectron() {
		return electron;
	}
}
