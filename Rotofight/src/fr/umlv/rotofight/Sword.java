package fr.umlv.rotofight;


import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;



/**
 * 
 * The Sword hurt a Player 
 * The Sword have a position and a Direction 
 *
 */
public class Sword extends Position {
	private final Direction direction;
	
	/**
	 * Construct  a Sword with it Direction and it coordinated
	 * @param x  the abscissa 
	 * @param y the ordinated
	 * @param direction The direction of the Sword
	 */
	public Sword(int x,int y,Direction direction) {
		super(x,y);
		this.direction=direction;
	}
	
	/**
	 * move the Sword
	 * @param map the Map to check if a wall is strike
	 * @return false if the  Sword strike a wall
	 */
	public boolean move(Map map) {
		return moveTo(map,direction);
		
	}
	
	/**
	 * This function return a boolean in function of if a player has been touch
	 * @param px position x of the player
	 * @param py position y of the player
	 * @return true if the sword touch someone or false if not
	 */
	public boolean touch(int px,int py) {
		return super.touch(px,py);
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	/**
	 * Display the sword
	 * @param context
	 * @param num the User's color
	 */
	public void displaySword(Graphics2D graphics,User num,int height,int width,int size) {
		/*context.renderFrame(graphics -> {*/
			 	graphics.setColor(num.getColor());
			 	int posX = 0,posY =0;
			 	posX = getX() * size ;
    		  	posY = getY() * size;
    		  	graphics.fill(direction.display(new  Rectangle2D.Float((posY + height),(posX + width), 11, 4),(posY + height),(posX + width)));

	}
	
}
