package fr.umlv.rotofight;

import java.awt.Color;


import fr.umlv.zen5.KeyboardKey;
/**
 * 
 * The Id of a player 
 * 
 */
public enum  User {
		PLAYER1(Color.RED,KeyboardKey.UP,KeyboardKey.DOWN,KeyboardKey.LEFT,KeyboardKey.RIGHT,KeyboardKey.SPACE),
		PLAYER2(Color.BLUE,KeyboardKey.Z,KeyboardKey.S,KeyboardKey.Q,KeyboardKey.D,KeyboardKey.X);
		private final Color color;
		private final KeyboardKey up;
		private final KeyboardKey down;
		private final KeyboardKey left;
		private final KeyboardKey right;
		private final KeyboardKey attack;
		
		private User(Color color,KeyboardKey up,KeyboardKey down,KeyboardKey left,KeyboardKey right, KeyboardKey attack) {
			this.color=color;
			this.up=up;
			this.down=down;
			this.left=left;
			this.right=right;
			this.attack=attack;
		}
		/**
		 * return the color of a User
		 * @return the color of a User
		 */
		public Color getColor() {
			return color;
		}
		/**
		 * Return true if the keyboardKey is the command of the attack
		 * @param k the KeyboardKey we want to check if it's the KeyboardKey of attack of the User
		 * @return true if the keyboardKey is the command of the attack 
		 */
		public boolean isAttack(KeyboardKey k) {
			return k==attack;
		}
		/**
		 * Return a Direction according to a KeyboardKey, None if is not a Direction.
		 * @param k the KeyboardKey which indicated a direction for the User
		 * @return a Direction according to the KeyboardKey
		 */
		public Direction pressed(KeyboardKey k) { 
			if(k==up) {
				return Direction.UP;
			}
			else if(k==down) {
				return Direction.DOWN;
			}
			else if(k==left) {
				return Direction.LEFT;
			}
			else if(k==right) {
				return Direction.RIGHT;
			}
			return Direction.NONE;
			
		}
		
		
}
