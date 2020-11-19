package fr.umlv.rotofight.io;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import fr.umlv.rotofight.Map;
import fr.umlv.rotofight.Player;
import fr.umlv.rotofight.User;
import fr.umlv.rotofight.World;

/**
 * The Loader load a World according to a file
 */
public class Loader {
	/**
	 * 
	 * @param map
	 * @param height
	 * @param width
	 * @param player
	 */
	private static void verificationElement(Map map,int height,int width,int player) {
		int secondWidth = 0;
		if(player < 2 || player > 2) {
    		throw new IllegalStateException("There are too much or less player");
    	}
		if(height == width) {
			throw new IllegalStateException("The map is not a rectangle");
		}
		for(int i = 0;i < map.sizeElement(height-1);i++) {
			if(map.presentObject(height-1, i)) {
				secondWidth++;
			}
		}
		if(secondWidth != width) {
			throw new IllegalStateException("This is not a rectangle");
		}
	}
	/**
	 * 
	 * @param array
	 * @param map
	 * @param file
	 * @param width
	 * @return
	 */
	private static int addWall(String[] array,Map map,String file,int width) {
		int index = file.indexOf("W");
		int newWidth = 0;
		
		if(index > 0) {
			map.add(load(array));
			
			for(int i = index; ((i < array.length) && array[i].contains("W")) ;i++){
				newWidth++;
			}
			if(newWidth > width && width == 0) {
				return newWidth;
			}
		}
		return width;
	}
	/**
	 * Initialize map 
	 * @param map
	 * @param widthMap
	 * @param heightMap
	 */
	private static void initMapMensuration(Map map,int widthMap,int heightMap) { 
		map.setHeight(heightMap);
    	map.setWidth(widthMap);
	}
	/**
	 * Initialize the world width and height 
	 * @param worldSize
	 */
	private static void initWorldSize(double[] worldSize) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		worldSize[0] = screenSize.getHeight();
		worldSize[1] = screenSize.getWidth();
	}
	
	/**
	 * load a World 
	 * @param br the file to read
	 * @return a World 
	 * @throws IOException
	 */
	public static World loadWorld(BufferedReader br,int number) throws IOException {
    	int nbPlayer = 0;
    	int widthMap = 0;
    	int heightMap = 0;
    	int[] playerPosition=new int[4];
    	double[] worldSize = new double[2];
    	Map map=new Map();
		String line=br.readLine();
    	while(line!=null) {
    		String[] array=line.split("");
    		heightMap += ((line.indexOf("W") >= 0) ? 1 : 0);
    		widthMap = addWall(array, map,line,widthMap);
    		nbPlayer += loadPlayer(array,playerPosition,heightMap);
    		line=br.readLine();
    	}
    	verificationElement(map,heightMap,widthMap,nbPlayer);
    	initMapMensuration(map, widthMap, heightMap);
    	initWorldSize(worldSize);
    	return new  World(map,new Player(User.PLAYER1,playerPosition[0],playerPosition[1],number),new Player(User.PLAYER2,playerPosition[2],playerPosition[3],number),worldSize[0],worldSize[1]);
    }
	/**
	 * return a ArrayList<Boolean> correspondent to a line's Map 
	 * add true to the value "W"
	 * @param s a String array with 4 values " "space ,"O" player One,"T"player Two and "W" Wall
	 * @return a ArrayList<Boolean>
	 */
	public static ArrayList<Boolean> load(String[] s) {
		ArrayList<Boolean> list=new ArrayList<>();
		Arrays.asList(s).forEach(c-> list.add( c.equals("W")));
		
    	return list;
	}
	/**
	 * Load the Player's coordinated 
	 * @param s a String array with 4 values " "space ,"O" player One,"T"player Two and "W" Wall
	 * @param pl an integer array of the Players's coefficient
	 * @param abs the abscissa
	 */
	public static int loadPlayer(String[] s,int[] pl,int abs) {
		for(int i=0;i<s.length;i++) {
			if(s[i].equals("O")) {
				pl[0]=abs;
				pl[1]=i;
				return 1;
			}
			else if(s[i].equals("T")){
				pl[2]=abs;
				pl[3]=i;
				return 1;
			}
				
		}
		return 0;
	}
}
