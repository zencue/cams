import java.awt.Color;
/* Janura,Fedor and Dawson
 * 6/11/2024
 * This is a class of XOR Wire which allows user to put logic xor operand in the plane 
*/
public class XORWire extends LogicWire{
	/* Constructor of the XORWIre class
	 * @param: int x - x position of the wire
  	 * @param: int y - y position of the wire
     	* @param: int[] direction - direction of the logic wire
	*/
	public XORWire(int x, int y, Cell parent, int[] direction) {
		super(x, y, parent, Color.MAGENTA, direction);
		
	}
	/* Methods which checks "logical" condition for specific type of the logic wire
	 * @return: boolean status- logical checking
	*/
	@Override
	public boolean condition() {
		if(electrons.size() == 1) {//if there is only 1 electron in the wire, which is basically reprasination of the xor operand.
			return true;
		}
		return false;
	}

}
