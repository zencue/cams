import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class WireworldPlane extends Plane{
	private static ArrayList<Electron> electrons;
	private static int currentArrowType;
	private String dir;
	private int[] speed;
	public WireworldPlane(int width,int height,Cell[][] map,JFrame main) {
		super(width, height, map, main);
		electrons = new ArrayList<Electron>();
		
		currentArrowType = 1;
		this.dir = "right";
		this.speed = new int[] {1,0};
		
	}
	@Override
	public void paintComponent(Graphics g) {
		
		for(int i=0;i<electrons.size();i++) {
			int[] pos = electrons.get(i).getPos();
			Wire currentWire = (Wire)map[pos[0]][pos[1]].getElement();
			if(currentWire != null) {
				currentWire.removeElectron();
			}
			electrons.get(i).moveSignal();
			pos = electrons.get(i).getPos();
			Wire newWire = (Wire)map[pos[0]][pos[1]].getElement();
			if(newWire == null) {
				electrons.remove(i);
				i-=1;
			}
			else {
				newWire.addElectron(electrons.get(i));
			}
			
		}
		
		
		paintComponent(g);
		
	}
}
