/*

 */
package cams;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
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
				cell.addMouseListener(new MouseAdapter() {

		            @Override
		            public void mouseClicked(MouseEvent e) {
		            	int position[] = cell.getPos();
                                if(cell.getElement() == null){
                                    
                                
		            	
		            			                
		            }
		            
		            @Override
		            public void mousePressed(MouseEvent e) {
		            	
		            	
		            	
		            }
		            @Override
		            public void mouseMoved(MouseEvent e) {
		            	
		            	
		            	
		            }
		            @Override 
		            public void mouseReleased(MouseEvent e) {
		            	
		            }
		        });
		            
			}
			
		}
		add(plane,BorderLayout.CENTER);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		
		
		
		
	}
	
    }
}
