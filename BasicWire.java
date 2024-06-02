import java.awt.Color;
import java.util.ArrayList;
public class BasicWire extends Wire{
	private Wire wire;
	
	private ArrayList<Electron> newElectrons;
	
	private final ArrayList<Integer[]> speeds ;
	
	public BasicWire(int x, int y,Cell parent) {
		super(x,y,parent,Color.green);
		
		wire = this;
		speeds = new ArrayList<Integer[]>();
		speeds.add(new Integer[] {1,0});
		speeds.add(new Integer[] {-1,0});
		speeds.add(new Integer[] {0,1});
		speeds.add(new Integer[] {0,-1});

		newElectrons = new ArrayList<Electron>();
		
	}
	
	public void addElectron(Electron electron) {
		ArrayList<Integer[]> speedsCopy = (ArrayList<Integer[]>)speeds.clone();
		
		if(newElectrons.size()!=0) {

			for(int i = 0;i<electrons.size();i++) {
				WireworldPlane.removeElectron(electrons.get(i));
			}
			electrons.clear();
			boolean status = true;
			for(int i = 0;i<newElectrons.size();i++) {
				Electron el = newElectrons.get(i);
					for(int j =0;j<speedsCopy.size();j++) {
						if((int)speedsCopy.get(j)[0] == -el.getSpeed()[0]&&(int)speedsCopy.get(j)[1] == -el.getSpeed()[1]) {
							speedsCopy.remove(j);
							
							j=-1;
						}
						else if((int)speedsCopy.get(j)[0] == -electron.getSpeed()[0]&&(int)speedsCopy.get(j)[1] == -electron.getSpeed()[1]) {
							if((int)speedsCopy.get(j)[0] == el.getSpeed()[0]&&(int)speedsCopy.get(j)[1] == el.getSpeed()[1]) {
								status = false;
								newElectrons.remove(el);
								WireworldPlane.removeElectron(electron);
								WireworldPlane.removeElectron(el);
								
								i=-1;
							}
							
							speedsCopy.remove(j);
							
							j=-1;
						}
						
						
						
					}
			}
			if(status) {
				electrons.add(electron);
				newElectrons.add(electron);
			}
		}
		else {
			newElectrons.add(electron);
			electrons.add(electron);
			
			
				int[] prevSpeed = electron.getSpeed();
				
				for(int i =0;i<speedsCopy.size();i++) {
					if((int)speedsCopy.get(i)[0] == -prevSpeed[0]&&(int)speedsCopy.get(i)[1] == -prevSpeed[1]) {
						speedsCopy.remove(i);
						
						i=-1;
					}
					else if((int)speedsCopy.get(i)[0] == prevSpeed[0]&&(int)speedsCopy.get(i)[1] == prevSpeed[1]) {
						speedsCopy.remove(i);
						i=-1;
					}
				}
		}
		
		
		
		for(int i=0;i<speedsCopy.size();i++) {
			Integer[] plus = speedsCopy.get(i);
			if(map[x+plus[0]][y+plus[1]].getElement()==null) {
				speedsCopy.remove(i);
				i-=1;
			}
			
			
		}
		
		for(int i =0;i<speedsCopy.size();i++) {
			Electron newElectron = new Electron(this.getX(),this.getY(),new int[] {speedsCopy.get(i)[0],speedsCopy.get(i)[1]});
			WireworldPlane.addElectron(newElectron);
			electrons.add(newElectron);
		}
		
		if(electrons.size()==0) {
			this.setBackground(Color.green);
		}
		else {
			this.setBackground(Color.red);
		}
		

	}
	public boolean doesContainElectrons() {
		return electrons.size() != 0;
	}
	public void removeElectrons() {
		electrons.clear();
		newElectrons.clear();
		setBackground(Color.green);
		
	}
	public ArrayList<Electron> getElectrons() {
		return electrons;
	}
}
