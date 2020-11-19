package fr.umlv.rotofight;



public class Gravity {

	private final String[] arrayDirection;
	private int currentDirection;
	private final long time;
	private long timer;
	private Direction gravity;
	
	/**
	 * Construct a gravity
	 * @param arrayDirection the Direction array  
	 * @param gravity
	 */
	public Gravity(String[] arrayDirection,Direction gravity,long gravityTime){
		if(gravityTime < 0){
			throw new IllegalArgumentException("time < 0");
		}
		this.arrayDirection = arrayDirection;
		this.currentDirection=0;
		this.gravity=gravity;
		timer=0;
		this.time = gravityTime;
	}
	
	
	/**
	 * return the new Direction
	 * @return  the new Direction
	 */
	public String changeGravity() {
		if(++currentDirection >= arrayDirection.length) {
			currentDirection=0;
		}
		return arrayDirection[currentDirection];
	}
	
	/**
	 * return true if the time is over
	 * @param time 
	 * @return true if the time is over 
	 */
	public boolean itsTime(long time) {
		if(timer==0) {
			timer=time;
		}
		return (time-timer) >= this.time;
	}
	/**
	 * change the Direction of the gravity according to the timer
	 * @param time 
	 */
	public void newGravity(long time) {
		if(itsTime(time)) {
			timer = 0;
			gravity= Direction.valueOf(changeGravity());
		}
	}
	/**
	 * return the currente Direction
	 * @return the currente Direction
	 */
	public Direction getDirection() {
		return gravity;
	}

}
