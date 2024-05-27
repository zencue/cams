import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class BasicArrow extends Arrow{
	private Arrow arrow;
	private Signal signal;
	
	public BasicArrow(int x, int y,Slot parent,int[] speed, String dir) {
		super(x,y,parent,Color.green,speed,dir);
		arrow = this;
		
	}
	
	public void addSignal(Signal signal) {
		
		this.signal = signal;
		System.out.println(getSpeed()[0]+" "+getSpeed()[1]);
		this.signal.setSpeed(this.getSpeed());
		setBackground(Color.red);

	}
	public boolean doesContainSignal() {
		return signal != null;
	}
	public void removeSignal() {
		signal = null;
		setBackground(this.getColor());
	}
	public Signal getSignal() {
		return signal;
	}
}
