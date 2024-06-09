import java.awt.Color;
import java.util.ArrayList;

public class SourceWire extends Wire{
	private Wire wire;
	
	private int finish;//variable which detectes when to make electrons
	private int counter;//variables which changes each time to rach a finish variable
	private final ArrayList<Integer[]> speeds ;//arraylist of speeds for electrons
	/**
	     * Constructor of the SourceWire class
	     *
	     * @param:int x- x coordinate of the wire
	     *@param:int y- y coordinate of the wire
	     *@param:Cell parent-cell where wires is placed
	     */
	public SourceWire(int x, int y,Cell parent) {
		
		super(x,y,parent,Color.red);
		speeds = new ArrayList<Integer[]>();

		//adding speeds in all directions
		speeds.add(new Integer[] {1,0});
		speeds.add(new Integer[] {-1,0});
		speeds.add(new Integer[] {0,1});
		speeds.add(new Integer[] {0,-1});
		counter = 5;
		finish = 5;
		//adding this sourcewire to array list of all sourcewires
		WireworldPlane.addSource(this);
		wire = this;
		//creating signals
		createSignals();
	}
	public void createSignals() {
		if(counter == finish) {
			for(int i =0;i<4;i++) {
				Electron e = new Electron(this.getX(),this.getY(),new int[] {speeds.get(i)[0],speeds.get(i)[1]});
				electrons.add(e);
				WireworldPlane.addElectron(e);
				
			}
			
			counter = 0;
		}
		else {
			counter+=1;
		}
	}
	public boolean doesContainElectrons() {
		return electrons.size() != 0;
	}
	public void addElectron(Electron electron) {
		electrons.add(electron);
		
	}
	public void removeElectrons() {
		electrons.clear();
	}
	public ArrayList<Electron> getElectrons() {
		return electrons;
	}
}

