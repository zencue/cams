import java.awt.Color;
import java.util.ArrayList;
/* Janura,Fedor and Dawson
 * 6/11/2024
 * This is a class of BasicWire which transports electron through the greed and also allow to split an electron if there are forks.
*/
public class BasicWire extends Wire{
	private Wire wire;
	
	private ArrayList<Electron> newElectrons;//array of electrons in the wire
	
	private final ArrayList<Integer[]> speeds ;//directions/speeds which will be used to to put on electrons
	/**
     * Constructor of the WireworldPlane class
     *
     * @param:int x - x coordinate of the wire
     * @param:int y - y coordinate of the wire
     * @param:Cell parent -cell of the wire
     *
     *
     */
	public BasicWire(int x, int y,Cell parent) {
		super(x,y,parent,Color.green);
		
		wire = this;
		speeds = new ArrayList<Integer[]>();

		//default speeds
		speeds.add(new Integer[] {1,0});
		speeds.add(new Integer[] {-1,0});
		speeds.add(new Integer[] {0,1});
		speeds.add(new Integer[] {0,-1});

		newElectrons = new ArrayList<Electron>();
		
	}
	/**
     * Method which adds electron into wire
     *
     * @param:Electron electron 
     * 
     *
     *
     */
	public void addElectron(Electron electron) {
		ArrayList<Integer[]> speedsCopy = (ArrayList<Integer[]>)speeds.clone();//copy of default directions
		
		if(newElectrons.size()!=0) {//if this electron is not new

			for(int i = 0;i<electrons.size();i++) {//cleaning the global arraylist of electrons
				WireworldPlane.removeElectron(electrons.get(i));
			}
			electrons.clear();//clearing arraylist of electorns of this basiw wrie
			boolean status = true;//
			for(int i = 0;i<newElectrons.size();i++) {//going through electrons which were put in this wire
				Electron el = newElectrons.get(i);//getting electron
					for(int j =0;j<speedsCopy.size();j++) {//going through all default directions
						if((int)speedsCopy.get(j)[0] == -el.getSpeed()[0]&&(int)speedsCopy.get(j)[1] == -el.getSpeed()[1]) {//deleting ability for electron to go in the oppositie direction
							speedsCopy.remove(j);//deleting this direction from defaults
							
							j=-1;
						}
						else if((int)speedsCopy.get(j)[0] == -electron.getSpeed()[0]&&(int)speedsCopy.get(j)[1] == -electron.getSpeed()[1]) {//checking the same thing but for the electron which were put htis time
							if((int)speedsCopy.get(j)[0] == el.getSpeed()[0]&&(int)speedsCopy.get(j)[1] == el.getSpeed()[1]) {//if there is electron in the wire which have opposite direction of the electron they will crush and be deleted
								status = false;//electrons crushed
								//removing electrons
								newElectrons.remove(el);
								WireworldPlane.removeElectron(electron);
								WireworldPlane.removeElectron(el);
								
								i=-1;
							}
							
							speedsCopy.remove(j);//removing this direction
							
							j=-1;
						}
						
						
						
					}
			}
			if(status) {//if crushed adding new electron with perpendicular direction
				electrons.add(electron);
				newElectrons.add(electron);
			}
		}
		else {//otherwise
			// adding it to electrons arraylists
			newElectrons.add(electron);
			electrons.add(electron);
			
			
				int[] prevSpeed = electron.getSpeed();//getting opposite direction of the electron
				
				for(int i =0;i<speedsCopy.size();i++) {//going through default ddirections
					if((int)speedsCopy.get(i)[0] == -prevSpeed[0]&&(int)speedsCopy.get(i)[1] == -prevSpeed[1]) {//deleting opposite directionn
						speedsCopy.remove(i);
						
						i=-1;
					}
					else if((int)speedsCopy.get(i)[0] == prevSpeed[0]&&(int)speedsCopy.get(i)[1] == prevSpeed[1]) {//deleting electron's direction
						speedsCopy.remove(i);
						i=-1;
					}
				}
		}
		
		
		
		for(int i=0;i<speedsCopy.size();i++) {//going through default directions
			Integer[] plus = speedsCopy.get(i);
			if(map[x+plus[0]][y+plus[1]].getElement()==null) {//checking if the next wire is empty 
				speedsCopy.remove(i);
				i-=1;
			}
			
			
		}
		
		for(int i =0;i<speedsCopy.size();i++) {//going through directions
			//making new electrons 
			Electron newElectron = new Electron(this.getX(),this.getY(),new int[] {speedsCopy.get(i)[0],speedsCopy.get(i)[1]});
			WireworldPlane.addElectron(newElectron);
			electrons.add(newElectron);
		}
		//changing color of the wire to show that it has electrons
		if(electrons.size()==0) {
			this.setBackground(Color.green);
		}
		else {
			this.setBackground(Color.red);
		}
		

	}
	/**
     *Method which cehcks if there are electrons in the wire
     * @return: boolean status
     * 
     */
	public boolean doesContainElectrons() {
		return electrons.size() != 0;
	}
	/**
     *Method which removes electrons from the wire
     * @return: none
     * 
     */
	public void removeElectrons() {
		electrons.clear();
		newElectrons.clear();
		setBackground(Color.green);
		
	}
	/**
     *Accessor of the electrons
     * @return: Electrons electrons - arrayllist of electrons
     * 
     */
	public ArrayList<Electron> getElectrons() {
		return electrons;
	}
}
