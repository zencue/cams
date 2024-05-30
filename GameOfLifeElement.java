/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cams;

import java.awt.Color;

/**
 *
 * @author darek1849
 */
public class GameOfLifeElement extends Element{
    private Cell parent;
    private boolean alive;
    private int x;
    private int y;
    private GameOfLifePlane plane;
    private int[][] neighbours = new int[8][2];
    
    
    
    public GameOfLifeElement(int xPos, int yPos, GameOfLifePlane plane, Cell parent){
        this.alive = true;
        this.parent = parent;
        parent.setBackground(Color.black);
        this.x = xPos;
        this.y = yPos;
        this.plane = plane;
        neighbours[0][0] = x;
        neighbours[0][1] = y+1;
        neighbours[1][0] = x+1;
        neighbours[1][1] = y+1;
        neighbours[2][0] = (x+1);
        neighbours[2][1] = y;
        neighbours[3][0] = x+1;
        neighbours[3][1] = y-1;
        neighbours[4][0] = x;
        neighbours[4][1] = y-1;
        neighbours[5][0] = x-1;
        neighbours[5][1] = y-1;
        neighbours[6][0] = x-1;
        neighbours[6][1] = y;
        neighbours[7][0] = x-1;
        neighbours[7][1] = y+1;
    }
    
   
    
    public int aliveNeighbours(){
        int numAliveNbs = 0;
        if(plane.map[neighbours[0][0]][neighbours[0][1]].getElement() == null){
           
        }else{
            numAliveNbs++;
        }
        return numAliveNbs;
    }
    
    public boolean checkAlive(){
        
        if(this.aliveNeighbours() >= 2 || this.aliveNeighbours() <=3){
            return true;
        }else{
            return false;
        }
    }
    
    
}
