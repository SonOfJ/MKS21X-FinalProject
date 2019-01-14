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
			System.out.println("Provide only an appropriate map number to generate a map.");
			System.out.println("Map numbers: ");
			System.exit(0);
		}
		try {
			Integer.parseInt(args[0]); //Is the input an integer?
		} catch (NumberFormatException e) {
			System.out.println("Provide only an appropriate map number to generate a map.");
			System.out.println("Map numbers: ");
			System.exit(0);
		}
		if (Integer.parseInt(args[0]) < 1) { //Correct map number?
			System.out.println("Invalid map number.");
			System.out.println("Map numbers: ");
			System.exit(0);
		}
		Map map = new Map(Integer.parseInt(args[0])); //Finds the appropriate map.

		Terminal terminal = TerminalFacade.createTextTerminal(); //open a terminal window
		terminal.enterPrivateMode();
		TerminalSize size = terminal.getTerminalSize();
		int maxX = size.getRows();
		int maxY = size.getColumns();
		terminal.setCursorVisible(false);

		boolean living = true; //Alive at the start of the game.
		int x = 0;
		int y = 0;

		boolean running = true;
		while(running){
			putString(0, 0, terminal, map.print()); //Prints the map. (the map need to be constantly printed or will be replaced)
			Key key = terminal.readInput();
			terminal.moveCursor(x,y);
			terminal.applyForegroundColor(Terminal.Color.BLACK);
			//applySGR(a,b) for multiple modifiers (bold,blink) etc.
			terminal.applySGR(Terminal.SGR.ENTER_UNDERLINE);
			terminal.putCharacter('\u00a4');
			//terminal.putCharacter(' ');
			terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			terminal.applySGR(Terminal.SGR.RESET_ALL);
			if (!living) { //The player is dead
				putString(0, 0, terminal, "GAME OVER. Press z to exit."); //Display text and directions to exit game.
				running = false;
			}
			terminal.moveCursor(size.getColumns()-5,5);
			terminal.applySGR(Terminal.SGR.ENTER_BOLD);
			terminal.moveCursor(size.getColumns()-5,6);
			terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			if (key != null) {
				if (key.getKind() == Key.Kind.Escape) {
					terminal.exitPrivateMode();
					running = false;
				}
				if (key.getKind() == Key.Kind.ArrowLeft && x != 0) { //Left boundaries.
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					x--;
				}
				if (key.getKind() == Key.Kind.ArrowRight && x != 79) { //Right boundaries.
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					x++;
				}
				if (key.getKind() == Key.Kind.ArrowUp && y != 0) { //Upper boundaries.
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					y--;
				}
				if (key.getKind() == Key.Kind.ArrowDown && y != 23) { //Lower boundaries.
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					y++;
				}
			}
		}
	}
}