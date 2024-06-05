
public class Electron {
	private int x;//the x position of the electron
	private int y;//the y position of the electron
	private int[] speed;////the speed and direction of the electron
	
	/**
	 * Constructor of the Electron class
	 * @param:int x - x value
	 * @param:int y - y value
	 * @param:int[] speed - the speed and direction of the electron
	 */
	public Electron(int x,int y,int[] speed) {
		
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	/**
	 * Method which changes position of the electron
	 * @param:none
	 */
	public void moveElectron() {
		
		this.x += speed[0];
		this.y += speed[1];
	}
	/**
	 * Accessor of the position
	 * @return: int[]{x,y} - x and y values
	 */
	public int[] getPos() {
		
		return new int[] {this.x,this.y};
	}
	/**
	 * The method which displays Electron data properly
	 * @return: String str - data of the class 
	 */
	public String toString() {
		return "Position:"+x+" "+y
				+"Speed: {"+speed[0]+", "+speed[1]+"}";
	}
	/**
	 * Mutator of the speed
	 * @param: int[] speed
	 */
	public void setSpeed(int[] speed) {
		this.speed = speed;
	}
	/**
	 * Accessor of the speed
	 * @return: int[] speed 
	 */
	public int[] getSpeed() {
		return speed;
	}
	
}
