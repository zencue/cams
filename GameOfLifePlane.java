/*
 */
package cams;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author darek1849
 */
public class GameOfLifePlane extends Plane {

    ArrayList<Integer> trackXY = new ArrayList<Integer>();
    private JPanel plane;
    private boolean isPressed;
    

    public GameOfLifePlane(int width, int height, Cell[][] map, JFrame main) {
        super(width, height, map, main);
        initUI(width, height, map);

    }

    private void initUI(int width, int height, Cell[][] map) {
        plane = new JPanel();

        plane.setBorder(BorderFactory.createLineBorder(Color.black));
        plane.setLayout(new GridLayout(width, height));
        plane.setVisible(true);
        // game of life plane is just itself
        GameOfLifePlane planeItSelf = this;
        // run through the 2d array that is this plane
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // create a new cell on this plane with x of j and
                // y of i
                Cell cell = new Cell(this, j, i);
                // add that cell to the map at the same point
                // (map is 2d array of cells)
                map[j][i] = cell;
                // also add this cell to the plane
                plane.add(cell);

                // add listener for the cell
                cell.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        // whe mouse is pressed that boolean is true
                        isPressed = true;
                        // get the x and y position of the cell
                        int position[] = cell.getPos();
                        // if the element on that cell is dead or if
                        // there is no element (null) we add one
                        if (((GameOfLifeElement) (cell.getElement())).checkAlive() == false
                                || ((GameOfLifeElement) (cell.getElement())) == null) {
                            // create a new game of life element at the referenced x and y
                            // on our game of life plane, parent cell is the cell on the plane
                            // and the cell will be alive as we have clicked on it while it was dead
                            GameOfLifeElement el = new GameOfLifeElement(position[0], position[1], planeItSelf, cell, true);
                            // add that element to the cell
                            cell.addElement(el);
                            // otherwise is the element is alive
                        } else if (((GameOfLifeElement) (cell.getElement())).checkAlive() == true) {
                            // the element is red
                            cell.setBackground(Color.red);
                            // and set to null to kill it
                            cell.addElement(null);
                        }

                    }
                    /**
                     * Mouse entered event for when user is dragging mouse on 
                     * screen
                     */
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // if the mouse is pressed when it enters a new node
                        if (isPressed) {
                            // grab the current position and save in an array
                            int position[] = cell.getPos();
                            // if that cell has no element or if it is dead
                            if (((GameOfLifeElement) (cell.getElement())).checkAlive() == false
                                    || ((GameOfLifeElement) (cell.getElement())) == null) {
                                // instantiate a new one
                                GameOfLifeElement el = new GameOfLifeElement(position[0], position[1], planeItSelf, cell, true);
                                // and add it to that cell on the plane
                                cell.addElement(el);
                                // otherwise if it is alive
                            } else if (((GameOfLifeElement) (cell.getElement())).checkAlive() == true) {
                                // set color to red and kill it
                                cell.setBackground(Color.red);
                                // set to null to kill
                                cell.addElement(null);
                            }
                        }
                    }
                    /**
                     * When mouse is released we just set the is pressed 
                     * boolean to false for mouse enetered event
                     */
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        isPressed = false;
                    }
                });

            }
            add(plane, BorderLayout.CENTER);

        }
    }
   
    @Override
    public void paintComponent(Graphics g) {
        int births = 0;
        int deaths = 0;
        int alive = 0;
        // while program is not paused
        if (!isStopped) {
            births = 0;
            deaths = 0;
            alive = 0;
            // make a new map that is same as the one we currently have
            Cell[][] newMap = new Cell[map.length][map[0].length];
            // runthrough the length of the map array
            for (int i = 0; i < map[0].length; i++) {
                // run through the components
                for (int j = 0; j < map.length; j++) {
                    // get the element at this current cell of the map
                    GameOfLifeElement el = (GameOfLifeElement) map[i][j].getElement();
                    // if its null
                    if (el == null) {

                        // make el a new element that is dead
                        el = new GameOfLifeElement(i, j, this, map[i][j], false);
                        // add it to the map
                        map[i][j].addElement(el);
                    }
                    if (newMap[i][j] == null) {
                        newMap[i][j] = new Cell(this, j, i);
                    }
                    // if element is alive
                    if (el.checkAlive() == true) {
                        alive++;
                        // add it to the new map
                        GameOfLifeElement newEl = new GameOfLifeElement(i, j, this, map[i][j], true);

                        newMap[i][j].addElement(newEl);
                        // if its dead add it to map
                    }else {
                        
                        GameOfLifeElement newEl = new GameOfLifeElement(i, j, this, map[i][j], false);
                        
                        newMap[i][j].addElement(newEl);

                        // if not dont add it
                    }
                    if(el.getAlive() == true && el.checkAlive() == false){
                        ((GameOfLifeElement)el).parent.setBackground(new Color(173,216,230));
                        deaths++;
                    }
                    if(el.getAlive() == false && el.checkAlive() == true){
                        births++;
                    }
                }
            }
            
            for (int i = 0; i < map[0].length; i++) {
                for (int j = 0; j < map.length; j++) {
                    this.map[i][j].addElement(newMap[i][j].getElement());
                }
            }
            CAMS_GameOfLife.numDeaths.setText("Deaths: " + deaths);
            CAMS_GameOfLife.numDeaths.setFont(new Font("Monospaced", Font.BOLD, 12));
            CAMS_GameOfLife.numBirths.setText("Births: " + births);
            CAMS_GameOfLife.numBirths.setFont(new Font("Monospaced", Font.BOLD, 12));
            CAMS_GameOfLife.numAlive.setText("Number Alive: " + alive);
            CAMS_GameOfLife.numAlive.setFont(new Font("Monospaced", Font.BOLD, 12));
        }
        
        
        
        // make the current map the new map
//            this.map = newMap;
    }

    public void saveConfig() {
        try {
//    		File config = new File("config.txt");
//        	boolean status = config.createNewFile();
            int[][] numMap = new int[map.length][map[0].length];

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    Cell cell = map[i][j];
                    Element el = cell.getElement();
                    if (((GameOfLifeElement) el).getAlive()) {
                        numMap[i][j] = 1;
                    } else {
                        numMap[i][j] = 0;
                    }

                } 
            }
            String fileName = "config1";//creating a default name of the config file
    		
        	
        	int index = 1;
    		while(Files.exists(Paths.get(fileName+".ser"))) {//checking if this name already exists
    			
        		index+=1;//new index
        		fileName = fileName.substring(0,6)+Integer.toString(index);//new name with new index
    		}
            ObjectOutputStream out = new ObjectOutputStream(
                        new FileOutputStream(fileName + ".ser")
                );
                out.writeObject(numMap);
                out.flush();
                out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void readConfig(String path) {
        try {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            int[][] array = (int[][]) in.readObject();
            in.close();
            Cell[][] newMap = new Cell[map.length][map[0].length];

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    int type = array[i][j];
                    Cell cell = map[i][j];
                    int x = cell.getPos()[0];//x of the cell in the map
                    int y = cell.getPos()[1];
                    Element elementa = null;//declaring wire
                    cell.removeElement();

                    if (type == 1) {
                        elementa = new GameOfLifeElement(i, j, this, cell, true);
                    } else {
                        elementa = new GameOfLifeElement(i, j, this, cell, false);
                    }
                    cell.addElement(elementa);

                }
            }

            plane.revalidate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
