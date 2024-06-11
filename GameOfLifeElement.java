/*
Dawson, Janura, Fedor
June 11, 2024
This class is all about the game of life elements. A game of life element
is an element that exists on a cell in a map on a plane in game of life.
A game of life element has a parent cell, a boolean status whether its alive
or dead, an x and y position relative to it's plane, a GameOfLifePlane it 
resides on, and an array list of neighbours that determine if it should
be alive. A game of life element will change colour based on how alive it is
 */
package cams;

import java.awt.Color;
import java.util.ArrayList;

/**
 * 
 * @author darek1849
 */
public class GameOfLifeElement extends Element{
    // GOLElements will have a parent cell, alive or dead, xy coords,
    // a plane its on, and an array list of neighbours
    public Cell parent;
    private boolean alive;
    private int x;
    private int y;
    private GameOfLifePlane plane;
    // change to array list
    private ArrayList<Integer> neighbours = new ArrayList<Integer>();
    
    
    /**
     * This constructor creates a game of life element
     * @param xPos an integer x position
     * @param yPos a y position
     * @param plane the plane this element is on
     * @param parent the cell this element is in
     * @param isAlive whether its alive or not
     */
    public GameOfLifeElement(int xPos, int yPos, GameOfLifePlane plane, Cell parent,boolean isAlive){
        this.parent = parent;
        this.x = xPos;
        this.y = yPos;
        this.plane = plane;
        //because some cells on the plane (like the corners) will only have
        // 3 neighbours (the rest will be out of bounds) there are a series of
        // error checks to determine if a neighbour should be added to an
        // elements list of neighbours
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
        // determine if cell currently is alive or not
        this.alive = isAlive;
        // if it is, make it black
        if(isAlive){
        parent.setBackground(Color.black);
        // if not, set white
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
                    // has another alive neighbour so we update that.
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
     * This method just accesses an element's alive status
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
