/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cams;

import java.awt.Color;
import java.util.ArrayList;

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
    // change to array list
    private ArrayList<Integer> neighbours = new ArrayList<Integer>();
    
    
    
    public GameOfLifeElement(int xPos, int yPos, GameOfLifePlane plane, Cell parent,boolean isAlive){
        
        this.parent = parent;
        
        this.x = xPos;
        this.y = yPos;
        this.plane = plane;
        // add if x+1 x-1 y+1 or y-1 is out of bounds dont add that neighbour
        if(y+1 >= 100){
            
        }else{
            neighbours.add(x);
            neighbours.add(y+1);
        }
        if(y+1 >= 100 || x+1 >= 100){
            
        }else{
        neighbours.add(x+1);
        neighbours.add(y+1);
        }
        if(x+1 >= 100){
            
        }else{
        neighbours.add(x+1);
        neighbours.add(y);
        }
        if(x+1 >= 100 || y-1 < 0){
            
        }else{
        neighbours.add(x+1);
        neighbours.add(y-1);
        }
        if(y-1 < 0){
            
        }else{
        neighbours.add(x);
        neighbours.add(y-1);
        }
        if(x-1 < 0 || y-1 < 0){
            
        }else{
            neighbours.add(x-1);
            neighbours.add(y-1);  
        }
        if(x-1 < 0){
            
        }else{
            neighbours.add(x-1);
            neighbours.add(y);
        }
        if(x-1 < 0 || y+1 >= 100){
            
        }else{
        neighbours.add(x-1);
        neighbours.add(y+1);
        }
        this.alive = isAlive;
        if(isAlive){
        parent.setBackground(Color.black);
        }else{
            parent.setBackground(Color.white);
        }
    }
    
   
    
    public int aliveNeighbours(){
        int numAliveNbs = 0;
        
            for(int k = 0; k < neighbours.size()/2; k+=2){

                if(plane.map[neighbours.get(k)][neighbours.get(k+1)].getElement() == null){
           
        }else{
                  
            numAliveNbs++;
        }
            }
        
        
        return numAliveNbs;
    }
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    public boolean checkAlive(){
        int proof = this.aliveNeighbours();
        if(proof >= 2 && proof <=3){
            return true;
        }else{
            return false;
        }
    }
    
    
}
