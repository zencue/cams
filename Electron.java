
public class Electron {
	private int x;
	private int y;
	private int[] speed;
	
	public Electron(int x,int y,int[] speed) {
		
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	public void moveElectron() {
		
		this.x += speed[0];
		this.y += speed[1];
	}
	public int[] getPos() {
		
		return new int[] {this.x,this.y};
	}
	public String toString() {
		return "Electron position:"+x+" "+y;
	}
	public void setSpeed(int[] speed) {
		this.speed = speed;
	}
	public int[] getSpeed() {
		return speed;
	}
	
}
