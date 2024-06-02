import java.awt.Color;
import java.util.ArrayList;

public class ORWire extends LogicWire{
	
	public ORWire(int x, int y, Cell parent,int[] direction) {
		super(x, y, parent,Color.yellow,direction);
		
	}

	public boolean condition() {
		return electrons.size()>0;
	}
	

	

}
