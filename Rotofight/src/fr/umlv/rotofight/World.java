package fr.umlv.rotofight;

import java.awt.Color;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;

/**
 * The world is all the object's game
 * 
 *
 */

public class World {
	private final Map map;
	private final Player p1;
	private final Player p2;
	private final float height;
	private final float width;
	
	/**
	 * Construct the World with the Players, the Map,and his Size
	 * @param map the Map of the game
	 * @param p1 the first Player
	 * @param p2 the second Player
	 * @param p3 the height of the World
	 * @param p4 the width of the world
	 */
	public World(Map map,Player p1,Player p2,double height,double width) {
		if(height < 0 ) {
			throw new IllegalArgumentException("height < 0");
		}
		
		if(width < 0) {
			throw new IllegalArgumentException("width < 0");
		}
		
		this.map=Objects.requireNonNull(map);
		this.p1= Objects.requireNonNull(p1);
		this.p2=Objects.requireNonNull(p2);
		this.height = Math.round(height);
		this.width = Math.round(width);
	}
	
	/**
	 * Display all the element of the World
	 * @param context 
	 * @param gravity the Direction of the actual Gravity
	 */
	private void displayElement(ApplicationContext context,Direction gravity) {
	      float[] elem = map.positionMap(width, height);
	      context.renderFrame(graphics -> {
	    	   graphics.clearRect(0, 0, Math.round(width), Math.round(height));
	    	   p1.displayPlayer(graphics,gravity,elem[0],elem[1]);
	    	   p2.displayPlayer(graphics,gravity,elem[0],elem[1]);
	    	   map.displayWall(graphics,width,height); 
	      });
	}
	
	/**
	 * Do some action if the player doesn't tap on the keyboard
	 * @param context
	 * @param gravity the Direction of the actual Gravity
	 */
	private void eventNull(ApplicationContext context,Gravity gravity) {
		try {
			float[] elem = map.positionMap(width, height);
			TimeUnit.MILLISECONDS.sleep(35);
			gravity.newGravity(System.currentTimeMillis());
			p1.press(KeyboardKey.UNDEFINED,map,gravity.getDirection(),Math.round(elem[0]),Math.round(elem[1]),p2);
			p2.press(KeyboardKey.UNDEFINED,map,gravity.getDirection(),Math.round(elem[0]),Math.round(elem[1]),p1);
			p1.touch(p2);
			p2.touch(p1);
			displayElement(context,gravity.getDirection());	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This function zoom or zoom out in function of the option choose by the user
	 * @param height of the map
	 * @param width of the map
	 * @param k the key tap by the user
	 */
	private void mapZoom(int height,int width,int bloc,KeyboardKey k) {
		if(k == KeyboardKey.SHIFT) {
			map.setHeight(height + 2);
			map.setWidth(width + 10);
			map.setblocSize(bloc + 2);
		}
		else{
			map.setHeight(height - 2);
			map.setWidth(width - 10);
			map.setblocSize(bloc - 2);
		}
	}
	
	/**
	 * Zoom on the map and on the player
	 * @param context
	 * @param k the key tap by the user
	 */
	private void woldZoom(KeyboardKey k,ApplicationContext context) {
		ScreenInfo screenInfo = context.getScreenInfo();
	    float width_ = screenInfo.getWidth();
		int size = p1.getSize();
	    int height = map.getHeight();
		int width = map.getWidth();
		int bloc = map.getBlocSize();
		
		if( ( (((bloc)) * (width))/2 <= width_) && k == KeyboardKey.SHIFT) {
			mapZoom(height, width, bloc,k);
			p1.setSize(size + 2);
			p2.setSize(size + 2);
		}
		else if(((bloc-2) >= 16 ) && k == KeyboardKey.ALT) {
			mapZoom(height, width, bloc,k);
			p1.setSize(size - 2);
			p2.setSize(size - 2);
		}
	}
	
	
	/**
	 * Take the key press and do some action in function of 
	 * @param k Keyboard press
	 * @param gravity the Direction of the actual Gravity
	 * @param context 
	 */
	private boolean pressPlayerOrOption(KeyboardKey k,Direction gravity,ApplicationContext context) {
		if(k == KeyboardKey.P) {
			context.exit(0);
			return false;
		}
		if(k == KeyboardKey.SHIFT || k == KeyboardKey.ALT) {
			woldZoom(k,context);
			return true;
		}
		
		float[] mapPosition = map.positionMap(width, height);
		
		p1.press(k,map,gravity,Math.round(mapPosition[0]),Math.round(mapPosition[1]),p2);
		p2.press(k,map,gravity,Math.round(mapPosition[0]),Math.round(mapPosition[1]),p1);
		p1.touch(p2);
		p2.touch(p1);
		return true;
	}
	
	private void displayWinner(Player p1, Player p2) {
		if(p1.isDead()) {
			System.out.println("P2 have win this game");
		}
		else if(p2.isDead()){
			System.out.println("P1 have win this game");
		}
		else {
			System.out.println("No Match    ");
		}
	}
	/**
	 * The principal function of the program
	 * @param gravity the Gravity of the game
	 */
	public void play(Gravity gravity) {
		Application.run(Color.BLACK, context -> {
			gravity.newGravity(System.currentTimeMillis());
			displayElement(context,gravity.getDirection());	
			while(!(p1.isDead()) && !(p2.isDead())) {
				Event event = context.pollOrWaitEvent(3);
				if(event == null) {
					eventNull(context,gravity);
					continue;
				}
			    Action action = event.getAction(); 
				if (action == Action.KEY_PRESSED) {
					KeyboardKey take = event.getKey();
					pressPlayerOrOption(take,gravity.getDirection(),context);
				}
				displayElement(context,gravity.getDirection());	
			}
			displayWinner(p1,p2);
			context.exit(0);
		});
	}
}
