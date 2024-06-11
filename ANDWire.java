import java.awt.Color;
/* Janura,Fedor and Dawson
 * 6/11/2024
 * This is a class of AND logci wire. It extends the logic wires and inherits all neccassary logic wires methods. 
*/
public class ANDWire extends LogicWire{
	/* Constructor of the ANDWIre class
	 * @param: int x - x position of the wire
  	 * @param: int y - y position of the wire
     	* @param: int[] direction - direction of the logic wire
	*/
	public ANDWire(int x, int y, Cell parent,  int[] direction) {
		super(x, y, parent, Color.cyan, direction);
		
	}
	/* Methods which checks "logical" condition for specific type of the logic wire
	 * @return: boolean status- logical checking
	*/
	@Override
	public boolean condition() {
		
		return electrons.size() == 2;//if both entrance are "1", which means there are electrons there
	}

}
