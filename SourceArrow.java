import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SourceArrow extends Arrow{
	private Arrow arrow;
	private Signal signal;
	private int finish;
	private int counter;
	public SourceArrow(int x, int y,Slot parent,int[] speed, String dir) {
		
		super(x,y,parent,Color.red, speed,  dir);
		
		counter = 0;
		finish = 3;
		arrow = this;
		createSignal();
	}
	public void createSignal() {
		if(counter == finish) {
			signal = new Signal(this.getX(),this.getY(),getSpeed());
			Board.addSignal(signal);
			counter = 0;
		}
		else {
			counter+=1;
		}
	}
	public boolean doesContainSignal() {
		return signal != null;
	}
	public void addSignal(Signal signal) {
		this.signal = signal;
		this.signal.setSpeed(getSpeed());
	}
	public void removeSignal() {
		signal = null;
	}
	public Signal getSignal() {
		return signal;
	}
}
