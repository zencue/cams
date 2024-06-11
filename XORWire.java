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
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {
		if(electrons.size() == 1) {
			return true;
		}
		return false;
	}

}
