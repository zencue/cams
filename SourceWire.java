import java.awt.Color;
import java.util.ArrayList;
/* Janura,Fedor and Dawson
 * 6/11/2024
 * This is a class of SourceWire which allows user to make electrons and use sources to place hem in the grid
*/
public class SourceWire extends Wire{
	
	
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
		//creating electrons
		createSignals();
	}
	/**
	     * Method which creates electrons
	     *
	     * @param none
	     */
	public void createSignals() {
		if(counter == finish) {//if cunter reached finish
			for(int i =0;i<4;i++) {//creating electrons
				Electron e = new Electron(this.getX(),this.getY(),new int[] {speeds.get(i)[0],speeds.get(i)[1]});
				electrons.add(e);
				WireworldPlane.addElectron(e);//adding to arraylist of electrons
				
			}
			
			counter = 0;
		}
		else {//otherwise adding one to counter
			counter+=1;
		}
	}
	/**
	     * Method which checks if wire contins electrons
	     *
	     * @return boolean status
	     */
	public boolean doesContainElectrons() {
		return electrons.size() != 0;
	}
	/**
	     * Method which adds electrons to wire's arraylist
	     *
	     * @param Electron electron
	     */
	public void addElectron(Electron electron) {
		electrons.add(electron);
		
	}
	/**
	     * Method which deletes all electrons from the arraylist
	     *
	     * @param none
	     */
	public void removeElectrons() {
		electrons.clear();
	}
	/**
	     * Method which returns arraylist of the electrons
	     *
	     * @param none
	     */
	public ArrayList<Electron> getElectrons() {
		return electrons;
	}
}

