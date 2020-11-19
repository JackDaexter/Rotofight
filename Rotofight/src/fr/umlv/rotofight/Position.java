package fr.umlv.rotofight;


/**
 * 
 * The position is the coordinated of a object in the game
 * Position have an abscissa and an ordinated
 *
 */
class Position {
	private int x;
	private int y;

	/**
	 * Construct a Position with coordinated
	 * @param x the abscissa
	 * @param y the ordinated
	 */
	public Position(int x,int y){
		if(x < 0) {
			throw new IllegalArgumentException("x < 0");
		}
		if(y < 0) {
			throw new IllegalArgumentException("y < 0");
		}
		this.x= x;
		this.y=y;
	}
	
	
	/**
	 * return the abscissa
	 * @return x the abscissa
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * return the ordinated
	 * @return y the ordinated
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * change the coordinated according to the direction 
	 * @param map the Map to check if a wall is strike
	 * @param dir the Direction to change the position
	 * @return false if the coordinated strike a wall
	 */
	public boolean moveTo(Map map ,Direction dir,Player p) {
		if(!(map.presentObject(dir.newX(x), dir.newY(y)))) {
			if( (p.getY()==dir.newY(y)) && (p.getX()==dir.newX(x))){
				return false;
			}
			x = dir.newX(x);
			y = dir.newY(y);
			return true;
		}
		return false;
	}
	
	public boolean moveTo(Map map ,Direction dir) {
		if(!(map.presentObject(dir.newX(x), dir.newY(y)))) {
			x = dir.newX(x);
			y = dir.newY(y);
			return true;
		}
		return false;
	}
	/**
	 * return true if px and py is the same coordinated 
	 * @param px the abscissa
	 * @param py the ordinated
	 * @return true if px and py is the same coordinated 
	 */
	public boolean touch(int px,int py) {
		return x==px && y==py;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Position)) {
			return false;
		}
		Position g=(Position) obj;
		return x==g.x && y==g.y;
	}
	
	
	
	@Override
	public int hashCode() {
		return Integer.hashCode(x)^Integer.hashCode(y);
	}
		
}
