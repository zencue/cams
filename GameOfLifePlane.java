/*

 */
package cams;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
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
	
	public GameOfLifePlane(int width,int height,Cell[][] map,JFrame main) {
		super(width, height, map, main);
		initUI(width,height,map);
		
	}
    
    private void initUI(int width,int height,Cell[][] map){
		plane = new JPanel();
		
		plane.setBorder(BorderFactory.createLineBorder(Color.black));
		plane.setLayout(new GridLayout(width,height));
		plane.setVisible(true);

		GameOfLifePlane planeItSelf = this;
		for(int i =0;i<height;i++) {
			for(int j =0;j<width;j++) {
				Cell cell = new Cell(this,j,i);
				map[j][i] = cell;
				plane.add(cell);
                                
				
                                cell.addMouseListener(new MouseAdapter() {
                                
                            @Override 
                            public void mousePressed(MouseEvent e) {
                                isPressed = true;
                                int position[] = cell.getPos();
                                if( ((GameOfLifeElement)(cell.getElement())).checkAlive() == false ||
                                        ((GameOfLifeElement)(cell.getElement())) == null){
                                    GameOfLifeElement el = new GameOfLifeElement(position[0], position[1], planeItSelf, cell, true);
                                    cell.addElement(el);
                                    
                                	                
		            }else if(((GameOfLifeElement)(cell.getElement())).checkAlive() == true){
                                    cell.setBackground(Color.red);
                                    cell.setElement(null);
                                }
                                
                            }
		            @Override
		            public void mouseEntered(MouseEvent e) {
                                if(isPressed){
                                   GameOfLifeElement current = new GameOfLifeElement(e.getX(), e.getY(), planeItSelf, cell, true);
                                    cell.addElement(current); 
                                }
                                
		            }
                            @Override 
                            public void mouseReleased(MouseEvent e){
                                isPressed = false;
                            }
                                });
                                
		            
			
			
		}
		add(plane,BorderLayout.CENTER);
		
	}
    }
	@Override
	public void paintComponent(Graphics g) {
            // make a new map that is same as the one we currently have
            Cell[][] newMap = map.clone();
            // runthrough the length of the map array
            for(int i = 0; i < map[0].length; i++){
                // run through the components
                for(int j = 0; j < map.length; j++){
                    // get the element at this current cell of the map
                    GameOfLifeElement el = (GameOfLifeElement)map[i][j].getElement();
                    // if its null
                    if(el == null){
                        // make el a new element that is dead
                        el = new GameOfLifeElement(i, j, this, map[i][j], false);
                        // add it to the map
                        map[i][j].addElement(el);
                    }
                    // if element is alive
                    if(el.checkAlive() == true){
                        // add it to the new map
                        GameOfLifeElement newEl = new GameOfLifeElement(i, j, this, newMap[i][j], true);
                        
                        
                        newMap[i][j].addElement(newEl);
                    }else{
                        GameOfLifeElement newEl = new GameOfLifeElement(i, j, this, newMap[i][j], false);
                        
                        
                        newMap[i][j].addElement(newEl);
                        
                       // if not dont add it
                    }
                }
            }
            for(int i = 0; i < map[0].length; i++){
                for(int j = 0; j < map.length; j++){
                    this.map[i][j].addElement(newMap[i][j].getElement());
                }
            }
            // make the current map the new map
            this.map = newMap;
		
	
	}
	
    }

