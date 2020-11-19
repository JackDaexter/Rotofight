package fr.umlv.rotofight;


import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import java.util.Objects;

import fr.umlv.zen5.KeyboardKey;


/**
 * 
 *The Player have a number, a Position,Direction and Swords 
 *
 */
public class Player extends Position{
	private int lifePoint;
	private final User user;
    private final Weapons weapon;
    private Direction dir;
    private Direction dirattack;
    private Eyebrows leftEyebrows;
    private Eyebrows rightEyebrows;
    private int size;
    
    /**
     * Construct  a player with his num and is coordinnated
     * @param num the number of the User
     * @param x the abscissa 
     * @param y the ordinated
     * @param nbSword How many sword the player want
     */
	public Player(User user,int x,int y,int nbSword) {
		super(x,y);
		if(nbSword < 0) {
			throw new IllegalArgumentException("number of sword can't be negative ");
		}
		this.user = Objects.requireNonNull(user);
		lifePoint = 10;
		weapon = new Weapons(nbSword);
		dir = Direction.NONE;
		dirattack = Direction.RIGHT;
		leftEyebrows = new Eyebrows(x,y,0);
		rightEyebrows = new Eyebrows(x-2,y,0);
		size = 16;
	}
	
	/**
	 * Give the player size
	 * @return size of the player
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Change the user size
	 * @param newSize Take the new size of the player
	 */
	public void setSize(int newSize) {
		if(newSize < 0) {
			throw new IllegalArgumentException("newSize cannot be negative");
		}
		size = newSize;
	}
	
	
		@Override
		public String toString() {
			return user.name() + super.toString();
		}
		
		
		/**
		 * change the direction of the player can't do a double jump
		 * @param dir the new Direction 
		 * @param g the Direction of the Gravity to forbid the double jump 
		 */
		private void setDirection(Map m,Direction dir,Direction g) {
			if(dir==g.directionopposed() && moveTo(m,g) ) {
				dir=Direction.NONE;
			}
			this.dir=dir;
		}
		/**
		 * Throw a sword 
		 * @return false if the player haven't enough sword 
		 */
		public boolean attack() {

			return weapon.throwSword(getX(), getY(), dirattack);
		}
		
		/**
		 * Reduce the life point if Player p touch Player
		 * @param p The player who attack
		 * @return true if p touch this, false
		 */
		public boolean touch(Player p) {
			
			if(p.weapon.touch(getX(), getY())) {
				leftEyebrows.setDegree(2*Math.PI/3);
				rightEyebrows.setDegree(Math.PI/3);
				lifePoint--;
				return true;
			}
			return false;
		}
		/**
		 * check the KeyboardKey to change the Player Direction or to throw a sword
		 * @param k the KeyboardKey
		 * @param map
		 * @param gravity the Direction of the Gravity
		 */
		public void press(KeyboardKey k,Map map,Direction gravity,int posXMap,int posYMap,Player p) {
			if(user.isAttack(k))
				attack();
			else {
				setDirection(map,user.pressed(k),gravity);
			}
			move(map,gravity,posXMap,posYMap,p);
		}
		
		
		public void jump(Map m,Direction gravity) {
			
			if(dir.areRevese(gravity)) {
				while(moveTo(m,dir));
			}
		}
		/**
		 * move the player according to his Direction and the Gravity
		 * @param m the Map to limit the move
		 * @param gravity the Direction of the Gravity
		 */
		public void move(Map m,Direction gravity,int posXMap,int posYMap,Player p) {
			if(dir==Direction.NONE) {
				moveTo(m,gravity,p);
			}
			else if(dir.perpendicular(gravity)) {
					dirattack=dir;
			}
			moveTo(m,dir,p);
			jump(m,gravity);
			if(dirattack==Direction.RIGHT || dirattack == Direction.DOWN) {
				
				leftEyebrows.setLeft(dirattack.newY(((getY()*size) + posXMap+5)), dirattack.newX(((getX()*size)+posYMap)), gravity, 0,size);
				rightEyebrows.setRight(((getY()*size) + posXMap+5), ((getX()*size)+posYMap), gravity, 0);
			}
			else {
				leftEyebrows.setLeft(((getY()*size) + posXMap), ((getX()*size)+posYMap), gravity, 0,size);
				rightEyebrows.setRight(dirattack.newY((getY()*size) + posXMap), dirattack.newX(((getX()*size)+posYMap)), gravity, 0);
			}
			
			weapon.move(m);
		
		}

		/**
		 * return true if the lifePoint is inferior or equal to 0
		 * @return true if the lifePoint is inferior or equal to 0
		 */
		public boolean isDead() {
			return lifePoint <= 0;
		}
		/**
		 * display the Player
		 * @param context
		 * @param gravity  the Direction of the Gravity
		 */
		public void displayPlayer(Graphics2D graphics,Direction gravity,float height,float width){ 

			graphics.setColor(user.getColor());
		 	int posX = 0,posY =0;
		 	posX = getX() * size;
		  	posY = getY() * size;
	        graphics.fill(new  Ellipse2D.Float(posY + height,posX + width,size, size));
   
			     
	        leftEyebrows.display(graphics, gravity,height,width,size);
			rightEyebrows.display(graphics, gravity,height,width,size);
			weapon.displaySword(graphics, user,getY() + height, getX() + width,size);
			 
			 
		}
	}


