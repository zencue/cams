import java.awt.Color;
import java.util.ArrayList;

public abstract class Wire extends Element{
	protected int x;//x coordinate of the wire
	protected int y;//y coordinate of the wire
	protected Cell parent;//variable which contains cell where wire were put 
	protected ArrayList<Electron> electrons;//arraylist of electrons which wire contains
	protected Cell[][]map;//array of cells
	/**
     * Constructor of the WireworldPlane class
     *
     * @param:int x - x coordinate of the wire
     * @param:int y - y coordinate of the wire
     * @param:Cell parent - 
     * @param:Color color - color which wire is using
     *
     *
     */
	public Wire(int x,int y,Cell parent,Color color) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.map = parent.getPlane().getMap();
		this.electrons = new ArrayList<Electron>();
		parent.setBackground(color);
	}
	/**
     * Accessor of the position of the wire
     *@param int[] pos 
     *
     */
	public int[] getPos() {
		return new int[]{x,y};
	}
	/**
     * Accessor of the x-coordinate of the wire
     *@return int x 
     *
     */
	public int getX() {
		return x;
	}
	/**
     * Accessor of the y-coordinate of the wire
     *@return int xy
     *
     */
	public int getY() {
		return y;
	}
	/**
     * Method which adds electrons in the array electrons
     *@param Electron electron
     *
     */
	public abstract void addElectron(Electron electron);
	/**
     * Method which removes electrons in the array electrons
     *@param none
     *
     */
	public abstract void removeElectrons();
	/**
     * Method which sets the background of the cell to show which wire is placed
     *@param Color color- required color
     *
     */
	public void setBackground(Color color) {
		parent.setBackground(color);
	}
	/**
     * Accessor of the parent of the wire
     *@return Cell parent 
     *
     */
	public Cell getParent() {
		return parent;
	}
	/**
     * Method which checks if there are electrons in the wire
     *@return boolean status 
     *
     */
	public abstract boolean doesContainElectrons();
	/**
     * Accessor of the electrons
     *@return ArrayList<Electron> electrons
     *
     */
	public abstract ArrayList<Electron>  getElectrons();


}
