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

public abstract class Plane extends JPanel implements Runnable {

    private JPanel plane;
    
    protected boolean isStopped;

    protected JFrame main;
    private int width;
    private int height;
    private Thread animator;
    private int DELAY = 500;
    protected Cell[][] map;

    public Plane(int width, int height, Cell[][] map, JFrame main) {
        super();
        this.main = main;
        this.width = width;
        this.height = height;
        this.map = map;
        this.isStopped = false;

    }

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

    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while (true) {

                main.repaint();
                timeDiff = System.currentTimeMillis() - beforeTime;
                sleep = DELAY - timeDiff;
                if (sleep < 0) {
                    sleep = 2;
                }
                try {
                    Thread.sleep(sleep);
                } catch (Exception e) {

                }
                beforeTime = System.currentTimeMillis();
            
        }
    }

    public Cell[][] getMap() {
        return map;
    }
}
