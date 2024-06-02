import java.awt.Color;

public class XORWire extends LogicWire{

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
