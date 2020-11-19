package fr.umlv.rotofight.main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import fr.umlv.rotofight.Direction;
import fr.umlv.rotofight.Gravity;
import fr.umlv.rotofight.World;
import fr.umlv.rotofight.io.Loader;


public class Main {
	 
	public static void main(String[] args) throws IOException, InterruptedException{
		String filename = "default-level.txt" ;
		String gravityChoose = "2";
		long gravityPhase = 10000;
		int nbSword = 20;
		
		for(int i = 0; i < args.length;i++) {
			filename = args[0];
			if(i == 1){gravityChoose = args[i];}
			else if(i == 2){gravityPhase = Long.parseLong(args[i]);}
			else if(i == 3){nbSword = Integer.parseInt(args[i]);}
		}
		
		Gravity gravity = new Gravity(Direction.DOWN.arrayD(gravityChoose),Direction.DOWN,gravityPhase);
		Path saveFilePath =Paths.get(filename);
		try(var reader = Files.newBufferedReader(saveFilePath, StandardCharsets.ISO_8859_1)) {
			   	   World w = Loader.loadWorld(reader,nbSword);
				   w.play(gravity); 
		}	   
	}
}
