import java.awt.Color;

public class SourceWire extends Wire{
	private Wire wire;
	private Electron electron;
	private int finish;
	private int counter;
	private final int[][] speeds = new int[4][2] ;
	public SourceWire(int x, int y,Cell parent,int[] speed, String dir) {
		
		super(x,y,parent,Color.red, speed,  dir);
		speeds[0][0] = 1;
		speeds[0][1] = 0;
		speeds[1][0] = -1;
		speeds[1][1] = 0;
		
		speeds[2][1] = 1;
		speeds[2][0] = 0;
		speeds[3][1] = -1;
		speeds[3][0] = 0;
		counter = 0;
		finish = 3;
		wire = this;
		createSignals();
	}
	public void createSignals() {
		if(counter == finish) {
			for(int i =0;i<4;i++) {
				electron = new Electron(this.getX(),this.getY(),speeds[i]);
				WireworldPlane.addElectron(electron);
			}
			
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
