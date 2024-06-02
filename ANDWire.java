import java.awt.Color;

public class ANDWire extends LogicWire{

	public ANDWire(int x, int y, Cell parent,  int[] direction) {
		super(x, y, parent, Color.cyan, direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {
		// TODO Auto-generated method stub
		return electrons.size() == 2;
	}

}
