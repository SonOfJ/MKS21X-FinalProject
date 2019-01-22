import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;
public class World {
	public static void putString(int r, int c,Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}
	public static void main(String[] args) {
		if (args.length != 1) { //Only one argument is needed.
			System.out.println("Provide only an appropriate map number to generate a map. Current # of map: 1");
			System.out.println("Map numbers: ");
			System.exit(0);
		}
		try {
			Integer.parseInt(args[0]); //Is the input an integer?
		} catch (NumberFormatException e) {
			System.out.println("Provide only an appropriate map number to generate a map. Current # of map: 1");
			System.out.println("Map numbers: ");
			System.exit(0);
		}
		if (Integer.parseInt(args[0]) != 1) { //Correct map number?
			System.out.println("Invalid map number. Current # of map: 1");
			System.out.println("Map numbers: ");
			System.exit(0);
		}
		Map map = new Map(Integer.parseInt(args[0])); //Finds the appropriate map.
		Terminal terminal = TerminalFacade.createTextTerminal(); //open a terminal window
		Screen s = new Screen(terminal);
		s.startScreen();
		terminal.enterPrivateMode();
		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);
		int x = 1;
		int y = 2;
		boolean running = true;
		Player Player = new Player(y, x, 50);
		while(running){
			String playerStats = "PLAYER: " + Player.getHP() + " HP";
			terminal.moveCursor(0, 0);
			for (int i = 0; i < playerStats.length(); i = i + 1) {
				terminal.putCharacter(playerStats.charAt(i));
			}
			terminal.moveCursor(0,31);
			for (int i = 0; i < map.print().length(); i++){
				terminal.putCharacter(map.print().charAt(i)); //Prints the map. (the map need to be constantly printed or will be replaced)
			}
			for (int j = 1; j<31; j++){
				for (int i = 0; i<60; i++){
					if (map.isWall(j, i)){
						terminal.moveCursor(i,j);
						terminal.putCharacter('#');
					}
					if (map.isMonster(j, i)){
						terminal.moveCursor(i,j);
						terminal.putCharacter('@');
					}
				}
			}
			Key key = terminal.readInput();
			terminal.moveCursor(x,y);
			terminal.applyForegroundColor(Terminal.Color.YELLOW);
			//applySGR(a,b) for multiple modifiers (bold,blink) etc.
			terminal.applySGR(Terminal.SGR.ENTER_UNDERLINE);
			terminal.putCharacter('\u00a4');
			//terminal.putCharacter(' ');
			terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			terminal.applySGR(Terminal.SGR.RESET_ALL);
			if (Player.getHP() == 0) { //The player is dead
				s.clear();
				String deathText = "GAME OVER. Press esc to exit.";
				terminal.moveCursor(0, 0);
				for( int i = 0; i < deathText.length(); i = i + 1) {
					terminal.putCharacter(deathText.charAt(i));
				}
				if (key.getKind() == Key.Kind.Escape) {
					running = false;
				}
			}
			while (map.isMonster(y, x)) {
				Monster M = new Monster(y, x, 10);
				String monsterStats = "MONSTER: " + M.getHP() + " HP";
				String instructions = "You have encountered a monster. Press z to attack.";
				while (Player.isAlive() && M.isAlive()) {
					terminal.moveCursor(0, 32);
					for(int i = 0; i < monsterStats.length(); i = i + 1) {
						terminal.putCharacter(monsterStats.charAt(i));
					}
					terminal.moveCursor(0, 33);
					for(int i = 0; i < instructions.length(); i = i + 1) {
						terminal.putCharacter(instructions.charAt(i));
					}
					Key k = terminal.readInput();
					if (k != null) {
						if (k.getKind() == k.Kind.ArrowUp) {
						int playerDmg = Player.attack();
						int monsterDmg = M.attack();
						M.takeDmg(playerDmg);
						Player.takeDmg(monsterDmg);
						String text = "You dealt " + playerDmg + " damage and took " + monsterDmg + " damage.";
						terminal.moveCursor(0, 34);
						for(int i = 0; i < text.length(); i = i + 1) {
							terminal.putCharacter(text.charAt(i));
						}
						if (!M.isAlive()) {
							map.changeTile(y, x);
						}
						}
						if (k.getKind() == k.Kind.Escape) {
							running = false;
							map.changeTile(y, x);
						}
					}
				}
			}
			if (key != null) {
				if (key.getKind() == Key.Kind.Escape) {
					//every if here add: 1. see if the next movement run into a wall
					//2.if isWall is false, continue the action and putString (0,1) "keep going!" or so
					//3. if it is wall, putString at (0,1) about "invalid action"
					running = false;
				}
				if (key.getKind() == Key.Kind.ArrowLeft) { //Left boundaries.
					/*
					if (Map.isWall(y,x-1)){
						putString(0, 53, terminal, "Invalid Action");
					}
					else{
					*/
					if (!map.isWall(y, x - 1)) {
						terminal.moveCursor(x,y);
						terminal.putCharacter(' ');
						x--;
						//putString(0, 53, terminal, key + " ");
					}
				}
				if (key.getKind() == Key.Kind.ArrowRight) { //Right boundaries.
					/*
					if (Map.isWall(y,x+1)){
						putString(0, 53, terminal, "Invalid Action");
					}
					else{
					*/
					if (!map.isWall(y, x + 1)) {
						terminal.moveCursor(x,y);
						terminal.putCharacter(' ');
						x++;
						//putString(0, 53, terminal, key + " ");
					}
				}
				if (key.getKind() == Key.Kind.ArrowUp) { //Upper boundaries.
					/*
					if (Map.isWall(y-1,x)){
						putString(0, 53, terminal, "Invalid Action");
					}
					else{
					*/
					if (!map.isWall(y - 1, x)) {
						terminal.moveCursor(x,y);
						terminal.putCharacter(' ');
						y--;
						//putString(0, 53, terminal, key + " ");
					}
				}
				if (key.getKind() == Key.Kind.ArrowDown) { //Lower boundaries.
					/*
					if (Map.isWall(y+1,x)){
						putString(0, 53, terminal, "Invalid Action");
					}
					else{
					*/
					if (!map.isWall(y + 1, x)) {
						terminal.moveCursor(x,y);
						terminal.putCharacter(' ');
						y++;
						//putString(0, 53, terminal, key + " ");
					}
				}
			}
		}
	}
}
