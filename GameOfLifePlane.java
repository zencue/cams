/*

 */
package cams;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
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
                                
				cell.addMouseListener(new MouseInputAdapter() {

		            @Override
		            public void mouseClicked(MouseEvent e) {
                                System.out.println("Clicked");
		            	int position[] = cell.getPos();
                                if(cell.getElement() == null){
                                    GameOfLifeElement el = new GameOfLifeElement(position[0], position[1], planeItSelf, cell);
                                    cell.addElement(el);
                                    
                                	                
		            }else{
                                    cell.setBackground(Color.red);
                                    cell.setElement(null);
                                }
                            }
		            @Override
		            public void mousePressed(MouseEvent e) {
		            	trackXY.clear();
                                trackXY.add(cell.getPos()[0]);
                                System.out.println("Pressed");
                                trackXY.add(cell.getPos()[1]);
		            	
		            }
		            @Override
		            public void mouseDragged(MouseEvent e) {
                                System.out.println("Dragged");
		            	if(cell.getPos()[0] != trackXY.get(0) &&
                                        cell.getPos()[1] != trackXY.get(1)){
                                    trackXY.add(cell.getPos()[0]);
                                    System.out.println("Tracks x");
                                    trackXY.add(cell.getPos()[1]);
                                    System.out.println("Tracks y");
                                }
		            	
		            	
		            }
		            @Override 
		            public void mouseReleased(MouseEvent e) {
                                System.out.println("Released");
		            	for(int i = 0; i < trackXY.size(); i+=2){
                                    GameOfLifeElement current = new GameOfLifeElement(trackXY.get(i), trackXY.get(i+1), planeItSelf, cell);
                                    cell.addElement(current);
                                }
		            }
		        });
		            
			
			
		}
		add(plane,BorderLayout.CENTER);
		
	}
    }
	@Override
	public void paintComponent(Graphics g) {
		
		
		
		
	}
	
    }
