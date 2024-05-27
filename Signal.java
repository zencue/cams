
public class Signal{
	private int x;
	private int y;
	private int[] speed;
	
	public Signal(int x,int y,int[] speed) {
		
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	public void moveSignal() {
		
		this.x += speed[0];
		this.y += speed[1];
	}
	public int[] getPos() {
		
		return new int[] {this.x,this.y};
	}
	public String toString() {
		return x+" "+y;
	}
	public void setSpeed(int[] speed) {
		this.speed = speed;
	}
}
