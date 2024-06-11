import java.awt.Color;
import java.util.ArrayList;
/* Janura,Fedor and Dawson
 * 6/11/2024
 * This is a class of OR Wire which allows user to put logic or operand in the plane 
*/
public class ORWire extends LogicWire{
	/* Constructor of the ANDWIre class
	 * @param: int x - x position of the wire
  	 * @param: int y - y position of the wire
     	* @param: int[] direction - direction of the logic wire
	*/
	public ORWire(int x, int y, Cell parent,int[] direction) {
		super(x, y, parent,Color.yellow,direction);
		
	}

	public boolean condition() {
		return electrons.size()>0;
	}
	

	

}
