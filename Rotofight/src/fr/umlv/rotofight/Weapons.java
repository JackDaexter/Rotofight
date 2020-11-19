package fr.umlv.rotofight;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * The Weapons is a Sword List
 * and a maximum number of Sword
 * 
 */
public class Weapons {
	private final ArrayList<Sword> swords;
	private int number;
	
	/**
	 * 
	 * Construct the Weapons initialize the ArrayList
	 */
	public Weapons(int nbSword) {
		this.swords=new ArrayList<>();
		this.number =nbSword;
		if(number < 0) {
			throw new IllegalArgumentException("Number of sword can't be negative");
		}
	}
	
	/**
	 * add a new Sword into the ArrayList if the number of Swords are superior to 0 
	 * reduce the maximum number of Sword
	 * @param x the abscissa of the Sword
	 * @param y the ordinated of the Sword 
	 * @param dir the Direction of the Sword
	 * @return true if a new Sword is adding
	 */
	public boolean throwSword(int x,int y,Direction dir) {
		if(number < 1) {
			return false;
		}
		number--;
		swords.add(new Sword(x,y,dir));
		return true;
	}
	
	/**
	 * move all the Sword of the Weapons
	 * remove a Sword if it's strike a wall 
	 * @param map the Map to check if a wall is strike
	 */
	public void move(Map map) {
		Iterator  <Sword> it = swords.iterator();
		while(it.hasNext()) {
			Sword s=it.next();
			if(!(s.move(map))) {
				number++;
				it.remove();
			}
		}
		

	}
	
	/**
	 * remove the Sword s from the Weapons
	 * @param s the Sword to remove
	 */
	public void remove(Sword s) {
		number++;
		swords.remove(s);

	}
	/**
	 * Check if the coordinated px,py have the same coordinated of a Sword from the Weapons
	 * remove the Sword if it's true
	 * @param px the abscissa
	 * @param py the ordinated
	 * @return true if one Sword have the same coordinated than px,py
	 */
	public boolean touch(int px,int py) {
		for(Sword s :swords) {
			if(s.touch(px, py)) {
				remove(s);
				
				return true;
			}
		}
		return false;
	}
	/**
	 * display all the Swords from the Weapons
	 * @param context
	 * @param num the User's Player
	 */
	public void displaySword(Graphics2D graphics,User num,float height,float width,int size) {
		for(Sword s :swords) {
			s.displaySword(graphics, num,Math.round(height),Math.round(width),size);
		}

	}
}
