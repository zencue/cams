import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/* Janura,Fedor and Dawson
 * 6/11/2024
 * This is a class of LogicWire which is a superclass of ANDWire,OrWire and XORWire and has all necessary logic methods, so it is really easy to create new logic wires.
*/
public abstract class LogicWire extends Wire{
	protected int[] direction;//direction of the wire
	/**
     * Constructor of the LogicWire class
     *
     * @param:int x - x coordinate of the wire
     * @param:int y - y coordinate of the wire
     * @param:Cell parent - cell where wire is placed
     * @param:Colro color -  color of the wire
     *@param:int[]  direction - direction of the wire
     */
	public LogicWire(int x, int y, Cell parent, Color color, int[] direction) {
		super(x, y, parent, color);
		this.direction = direction;
		WireworldPlane.addLogicWire(this);//adding wire to logic wire arraylist
	}
	/**
     * Method which checks the logic condition of the wire
     *
     * @return boolean status
     */
	public  boolean logicChecking() {
		if(condition()) {//if condition is true
			for(Electron i:electrons) {//going through all electrons in the wire
				WireworldPlane.removeElectron(i);//removing them from the arraylist of logicwires
			}
			electrons.clear();//clearing electrons in logicwire
			electrons.add(new Electron(x,y,direction));//creating new one
			WireworldPlane.addElectron(electrons.get(0));//adding it to the arraylist
			return true;//returnin true
		}
		else {//otherwise
			//just removing electrons
			for(Electron i:electrons) {
				WireworldPlane.removeElectron(i);
			}
			electrons.clear();
		}
		return false;
	}
	/**
     * Condition of the logicwire
     *
     * @return boolean status
     */
	public abstract boolean condition();
	@Override
	/**
     * Method which adds electron in the logicwire
     *
     * @return boolean status
     */
	public void addElectron(Electron electron) {
		int[] elDirection = electron.getSpeed();
		if(elDirection != direction && elDirection[0] != -direction[0]&& elDirection[1] != -direction[1]) {//if electron came from proper direction
			electrons.add(electron);//adding that
		}
		else {
			WireworldPlane.removeElectron(electron);//removing
		}
		
	}
	/**
     * Method which removes all electrons
     *
     * @return none
     */
	@Override
	public void removeElectrons() {
		electrons.clear();
		
	}
	/**
     * Method which checks if wire contain electrons
     *
     * @return status
     */
	@Override
	public boolean doesContainElectrons() {
		return electrons.size() != 0;
	}
	/**
     *Accessor of the electrons
     *
     * @return ArrayList<electrons>
     */
	@Override
	public ArrayList<Electron> getElectrons() {
		return electrons;
		
	}
	/**
     *Accessor of the  direction
     *
     * @return int intDirection - integer for mof the direction
     */
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
		else if(a1 == 0 && a2 == 1) {
			return 3;
		}
		return -1;
	}

}
