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
            neighbours.add(this.x);
            neighbours.add((this.y)+1);
        }
        if(y+1 >= 100 || x+1 >= 100){
            
        }else{
        neighbours.add((this.x)+1);
        neighbours.add((this.y)+1);
        }
        if(x+1 >= 100){
            
        }else{
        neighbours.add((this.x)+1);
        neighbours.add(this.y);
        }
        if(x+1 >= 100 || y-1 < 0){
            
        }else{
        neighbours.add((this.x)+1);
        neighbours.add((this.y)-1);
        }
        if(y-1 < 0){
            
        }else{
        neighbours.add(this.x);
        neighbours.add((this.y)-1);
        }
        if(x-1 < 0 || y-1 < 0){
            
        }else{
            neighbours.add((this.x)-1);
            neighbours.add((this.y)-1);  
        }
        if(x-1 < 0){
            
        }else{
            neighbours.add((this.x)-1);
            neighbours.add(this.y);
        }
        if(x-1 < 0 || y+1 >= 100){
            
        }else{
        neighbours.add((this.x)-1);
        neighbours.add((this.y)+1);
        }
        this.alive = isAlive;
        if(isAlive){
        parent.setBackground(Color.black);
        }else{
            parent.setBackground(Color.white);
        }
        
    }
    
   
    /**
     * This method runs through an elements array list of neighbours and 
     * @return returns an integer, number of neighbours that are alive
     */
    public int aliveNeighbours(){
        // originally number of alive neighbours is zero
        int numAliveNbs = 0;
        // run through the neighbours array list. We go up by 2 as we will
        // reference neighbours at k and then at k+1 as k and k+1 are an 
        // x,y coordinate pair
            for(int k = 0; k < neighbours.size(); k+=2){
                // create a new cell that is the same as the one on the map at
                // the first neighbour's coordinates
                Cell neighbour = plane.map[neighbours.get(k)][neighbours.get(k+1)];
                // if that new cell we made is null, or it is not alive
                // we don't update num alive neighbours
                if(neighbour.getElement() == null || ((GameOfLifeElement)(neighbour.getElement())).getAlive() == false){
           
                }else{// the cell is alive therefore the cell we are referencing
                    // has antoher alive neighbour so we update that.

                    numAliveNbs+=1;

                }
            }
        // after running through array list of neighbours we return the number
        // of alive neighbours
        return numAliveNbs;
    }
    /**
     * This method allows us to set an element alive or dead as we please
     * @param alive a boolean true or false in regards to the livelyness of
     * referenced cell.
     */
    public void setAlive(boolean alive){
        // this alive is set to param
        this.alive = alive;
        // if the cell is alive
        if(alive){
            // it will be black
            parent.setBackground(Color.black);
        }else{
            // otherwise dead ones will be white
            parent.setBackground(Color.white);
        }
    }
    /**
     * This method just accesses an elements alive status
     * @return a boolean true or false regarding if the cell is alive
     */
    public boolean getAlive(){
        return alive;
    }
    /**
     * This method check alive returns a boolean updating the status of 
     * an element. It checks if a cell *should* be alive, not necessarily
     * returning the current status of a cell. It follows the rules of 
     * Game of Life:
     * 1. if a cell is alive and has 2 or 3 alive neighbours it stays alive
     * 2. if a cell is alive and has less than 2 alive neighbours, it dies
     * due to "underpopulation"
     * 3. if a cell is alive but has more than 3 alive neighbours it dies due to
     * "overpopulation"
     * 4. if a cell is dead, but has exactly 3 alive neighbours, it is 
     * resuscitated and becomes alive again
     * @return a boolean telling us if an element should be alive or not
     */
    public boolean checkAlive(){
        // gives integer of neighbours that are alive
        int proof = this.aliveNeighbours();
        // if cell is alive and has less than two alive neighbours it dies
        if(this.getAlive() == true && proof < 2){
            
            return false;
            // if its alive and has more than 3 alive neighbours
            // its dead
        }else if(this.getAlive() == true && proof > 3){
            
            return false;
            // if its dead has 3 alive neighbours it is alive
        }
        else if(this.getAlive() == true && (proof ==3 || proof ==2)){
            
            return true;
            // if its dead has 3 alive neighbours it is alive
        }
        else if(this.getAlive() == false && proof == 3){
            
            return true;
            // if its null and has 3 alive neighbours it becomes alive
        }else if(this == null && proof == 3){
            
            return true;
        }
        // if none of the criteria apply just return the status of the cell 
        // currently
            return alive;    
    }
 
}
