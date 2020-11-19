package fr.umlv.rotofight;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import java.util.ArrayList;




/**
 * 
 * Representation of the map's game
 * a two dimension list of boolean
 */
public class Map {
	 private final ArrayList<ArrayList<Boolean>> wall;
	 private int blocSize;
	 private int width;
	 private int height;
	 /**
	  * Construct a Map
	  */
	 public Map(){
		wall = new ArrayList<>();
		blocSize = 16;
		width = 0;
		height = 0;
	 }

	 /**
	  * Change the value of bloc size
	  * @param newSize is new Size of bloc
      * @throws 
	  */
	public void setblocSize(int newSize) {
		if(newSize < 16) {
			throw new IllegalArgumentException("Bloc is too small");
		}
		blocSize = newSize;
	}
	
	/**
	  * Change the value of Width of the map
	  * @param width is new width of the map
	  */
	public void setWidth(int width){
		if(width < 0) {
			throw new IllegalArgumentException("width not allowed");
		}
		this.width = width;
	}
	
	/**
	  * Change the value of height of the map
	  * @param height is new height of the map
	  */
	public void setHeight(int height){
		if(height < 0) {
			throw new IllegalArgumentException("height not allowed");
		}
		this.height = height;
	}
	
	/**
	 * Give the value of map height
	 * @return height of map
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * Give the value of map width
	 * @return width of map
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Give the value of bloc size
	 * @return value of bloc size
	 */
	public int getBlocSize() {
		return blocSize;
	}
	
	/**
	 * Put the map on the middle of the screen width some calculus
	 * @param width width of the screen
	 * @param height height of the screen
	 * @return array width the new map position
	 */
	public float[] positionMap(float width,float height) {
		float[] mapPosition = new float[2];
		float centerX = (width / 2) - (this.width * 9);
		float centerY = (height / 2) - (this.height * 9);
		mapPosition[0] = centerX;
		mapPosition[1] = centerY;
		
		return mapPosition;
	}
	 
	 /**
	  * return the size of the Map's ArrayList
	  * @return the size of the Map's ArrayList
	  */
	public int size() {
		return wall.size();
	}
	
	/**
	 * 
	 * return the size of the i-th line from the Map's ArrayList
	 * @param i the line number of the Map's ArrayList
	 * @return the size of the i-th line from the Map's ArrayList
	 */
	public int sizeElement(int i) {
		return wall.get(i).size();
	}
	
	/**
	 * add a new ArrayList<Boolean> into the Map's ArrayList
	 * @param alb the ArrayList<Boolean> to add
	 */
	public void add(ArrayList<Boolean> alb) {
		wall.add(alb);
	}
	

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(int i =0 ;i< wall.size();i++) {
			for(int j= 0; j<wall.get(i).size();j++) {
				sb.append(presentObject(i, j)? "W":" ");
			}
			sb.append('\n');
		}
		return sb.toString();
	}
	
	/**
	 * return true if x and y are the coordinated of a wall
	 * @param x the abscissa
	 * @param y the ordinated
	 * @return true if x and y are the coordinated of a wall,false
	 */
	public boolean presentObject(int x , int y) {
		return wall.get(x).get(y);
	}
	
	
	/**
	 * display the map
	 * @param context
	 */
	public void displayWall(Graphics2D graphics,float width,float height) {
		float[] elem = positionMap(width, height);
		
			int posX = 0,posY = 0;
			for(int i = 0; i < size(); i++) {
		    	  for(int j = 0; j < sizeElement(i);j++) {
		    		   if(presentObject(i,j)) {
			    		  	graphics.setColor(Color.LIGHT_GRAY);
			    		  	posX = j * blocSize;
			    		  	posY = i * blocSize;
			    		  	graphics.fill(new  Rectangle2D.Float((elem[0] + posX),(elem[1] + posY), blocSize, blocSize));
		    		   }
			      } 
		     }
	}
}

