package cams;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
/* Janura,Fedor and Dawson
 * 6/11/2024
 * This is super class of Planes for wireworld and gameoflife.
*/
public abstract class Plane extends JPanel implements Runnable {

    
    protected static boolean isStopped;//indicator of play nd pause

    protected JFrame main;//main jframe where the instance came from
    public int width;//width of the grid
    public int height;//height of the grid
    private Thread animator;
    private int DELAY = 500;
    protected Cell[][] map;
     /**
     * Constructor of the Plane
     * @param: int width - width of the grid
     *@param: int height - height of the grid
     *@param: Cell[][] map - array of the cells which represents the map/grid
      *@param: JFrame main- main jframe where the instance came from
     */
    public Plane(int width, int height, Cell[][] map, JFrame main) {
        super();
        this.main = main;
        this.width = width;
        this.height = height;
        this.map = map;
        this.isStopped = false;

    }
     /**
     * Accessor of the main
     * @return: JFrame main
     */
    public JFrame getMain() {
        return main;
    }
    /**
     * when play or pause event happens is stopped is set to opposite of
     * current status (play become pause and vice versa)
     * @param evt often a button being clicked event
     */
    public void playOrPause(java.awt.event.ActionEvent evt) {
        isStopped = !isStopped;
    }
    // allows user to set speed to 2x
    public void speedX2(java.awt.event.ActionEvent evt) {
        // set delay to half of regular delay so updates happen 2x as fast
        // therefore speed is 2x
        DELAY = 250;
    }
    /**
     * sets speed to normal, 500 mili delay
     * @param evt often buton being clicked
     */
    public void speedX1(java.awt.event.ActionEvent evt) {
        // delay to og speed
        DELAY = 500;
    }
    /**
     * Sets update speed to double the regular resulting in half speed
     * @param evt button click
     */
    public void halfSpeed(java.awt.event.ActionEvent evt){
        // delay 2x regular
    DELAY = 1000;
    }
    /**
     * Make program update at 4x speed
     * @param evt some button clicked event
     */
    public void speedX4(java.awt.event.ActionEvent evt) {
        // delay is 1/4 of og delay
        DELAY = 125;
    }
    /**
	 * Method which creates the peer of the canvas. This peer allows you to change the user interface of the canvas without changing its functionality.
	 * @return: none
	 */
    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }
    
    @Override
    public void run() {

        long beforeTime,timeDiff,sleep;//declaring all necessary methods
		beforeTime = System.currentTimeMillis();//saving current time
        while(true) {//Infinite loop
			main.repaint();//repainting main JFrame
                timeDiff = System.currentTimeMillis() - beforeTime;//calculating time difference between before time and current one
			sleep = DELAY - timeDiff;//setting sleep value based on difference between delay and timedifference
                if(sleep<0) {//setting minimum sleep value
				sleep = 2;
			}
                try {
				Thread.sleep(sleep);//sleeping the loop for sleep's value
			}
			catch(Exception e) {
				
			}
			beforeTime = System.currentTimeMillis();//resaving current time
            
        }
    }
    /**
	 * Accessor of the array of cell instances
	 * @return: Cell[][] map - array of the cells
	 */
    public Cell[][] getMap() {
        return map;
    }
}
