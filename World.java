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
		boolean living = true; //Alive at the start of the game.
		int x = 1;
		int y = 1;

		boolean running = true;
		while(running){
			s.putString(0, 0, map.print(), Terminal.Color.DEFAULT, Terminal.Color.DEFAULT); //Prints the map. (the map need to be constantly printed or will be replaced)
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
			if (!living) { //The player is dead
				s.putString(0, 0, "GAME OVER. Press esc to exit.", Terminal.Color.DEFAULT, Terminal.Color.DEFAULT); //Display text and directions to exit game.
				running = false;
			}
			if (key != null) {
				if (key.getKind() == Key.Kind.Escape) {
					//every if here add: 1. see if the next movement run into a wall
					//2.if isWall is false, continue the action and putString (0,1) "keep going!" or so
					//3. if it is wall, putString at (0,1) about "invalid action"
					s.stopScreen();
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
