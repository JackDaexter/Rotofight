package fr.umlv.rotofight;


import java.awt.Shape;
import java.awt.geom.AffineTransform;
/**
 * All the direction you can do in a game
 *
 */

public enum Direction {
	DOWN(1,0,Math.PI/2),UP(-1,0,3*Math.PI/2),LEFT(0,-1,0),RIGHT(0,1,Math.PI),NONE(0,0,Math.PI/2);
	private final int dx,dy;
	private final double degree;
	
	/**
	 * Constructs a Direction with is the speed of abscissa,ordinate and is degree in radian.
	 * @param dx the speed of abscissa
	 * @param dy the speed of ordinated
	 * @param degree the radian of the direction
	 */
	private Direction(int dx,int dy,double degree) {
		this.dx=dx;
		this.dy=dy;
		this.degree=degree;
	}
	/**
	 * return the sum of x and Direction.dx
	 * @param x the abscissa
	 * @return the sum of x and Direction.dx
	 */
	public int newX(int x) {
		return x+dx;
	}
	/**
	 * return the sum of y and Direction.dy
	 * @param y the ordinate
	 * @return the sum of y and Direction.dy
	 */
	public int newY(int y) {
		return y+dy;
	}
	/**
	 * Return true if the Direction is vertical
	 * @return true if Direction is vertical
	 */
	public boolean vertical() {
		return dx!=0;
	}
	/**
	 * return true if the direction is horizontal
	 * @return true if direction is horizontal
	 */
	public boolean horizontal() {
		return !vertical();
	}
	
	/**
	 * return true if the direction is perpenticular to the Direction d
	 * @param d The direction we want to compare
	 * @return true if the Directions are opposed
	 */
	public boolean perpendicular(Direction d){
		return this.vertical() && d.horizontal() || this.horizontal() && d.vertical();
	}
	
	/**
	 * Return true if the Direction is the reverse of the Direction d
	 * @param d The direction we want to compare
	 * @return true if the Directions are reverse one another 
	 */
	public boolean areRevese(Direction d){
		return Math.abs(this.dx -d.dx)==2 || Math.abs(this.dy - d.dy)== 2;
	}
	
	/**
	 * Return the first Direction opposed 
	 * @return the Direction opposed
	 */
	public Direction directionopposed(){
		Direction d[]=Direction.values();
		for(int i=0; i< d.length;i++ ) {
			if(areRevese(d[i]))
				return d[i];
		}
		return this;
	}
	/**
	 * return a String's array of the different Direction name 
	 * @param s String of the number's Direction 
	 * @return  String's array of the different Direction name 
	 */
	public String[] arrayD(String s) {
		String[] as=new String[Integer.parseInt(s)];
		Direction[] da=values();
		for(int i=0;i<as.length;i++) {
			as[i]=da[i].name();
		}
		return as;
	}
	/**
	 * return rotation of an abscissa according to the Direction
	 * @param x the abscissa 
	 * @return the rotate abscissa
	 */
	public int rotateX(int x){ 
		 return x -( 16 *( this==LEFT? dy:0));
	 
	}
	/**
	 *  return a rotation of an ordinate according to the Direction
	 * @param y the ordinate 
	 * @return the rotate ordinate
	 */
	public int rotateY(int y){ 
		 return y - (16 *( this==UP? dx:0));
	 
	}
	/**
	 *  return a rotation of a height from Shape according to the verticalty of the Direction
	 * @return the rotate height
	 */
	public int rotateHeight(int pos){ 
		 return (vertical()? pos/2:2);
	 
	}
	/**
	 * return a rotation of a width from Shape according to the verticalty of the Direction
	 * @return the rotate width
	 */
	public int rotateWidth(int pos){ 
		 return (vertical()? 2:pos/2);
	 
	}
	
	/**
	 * Do the rotation of a Shape according to the Direction
	 * @param s The Shape we want to rotate
	 * @param x the abscissa of the Shape
	 * @param y the ordinate of the Shape
	 * @return The rotate Shape.
	 */
	public Shape display(Shape s,int x,int y){ 
			 return AffineTransform.getRotateInstance(degree,x,y).createTransformedShape(s);
		 
	}
}
