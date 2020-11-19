package fr.umlv.rotofight;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;


/**
 * 
 *The eyebrows indicated the direction of the next attack
 *
 */
public class Eyebrows {
	private int x;
	private int y;
	private double degree;
	
	/**
	 * Initialise the differents fields
	 * @param x
	 * @param y
	 * @param degree
	 */
	public Eyebrows(int x,int y,double degree) {
		this.x=x;
		this.y=y;
		this.degree=degree;
		
	}
	/**
	 * Turn the eyebrows on the left
	 * @param x
	 * @param y
	 * @param gravity
	 * @param degree
	 */
	void setLeft(int x,int y,Direction gravity,double degree,int size) {
		this.x = gravity.rotateX(x)+(gravity.horizontal()? 0 : size/2);
		this.y = gravity.rotateY(y)+(gravity.horizontal()? size/2 : 0);
		this.degree = degree;
	}
	/**
	 * Turn the eyebrows on the right
	 * @param x
	 * @param y
	 * @param gravity
	 * @param degree
	 */
	void setRight(int x,int y,Direction gravity,double degree) {
		this.x=gravity.rotateX(x)-(gravity.horizontal()? 0 : 2);
		this.y=gravity.rotateY(y)-(gravity.horizontal()? 2 : 0);
		this.degree=degree;
	}
	
	/**
	 * Change the degree value
	 * @param degree
	 */
	void setDegree(double degree) {
		this.degree= degree;
	}
	/**
	 * Send back the affine tranformation of the function
	 * @return
	 */
	AffineTransform touchLeft() {
		return AffineTransform.getRotateInstance(degree,x, y);
	}
	
	/**
	 * Display the Eyebrows
	 * @param context
	 * @param gravity
	 */
	public void display(Graphics2D graphics,Direction gravity,float height,float width,int size){ 
		graphics.setColor(Color.WHITE);
		var rect=new  Rectangle2D.Float(x,y,gravity.rotateHeight(size),gravity.rotateWidth(size));
		graphics.fill(touchLeft().createTransformedShape(rect));
			 
	}

}
