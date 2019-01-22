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
			System.out.println("Provide only an appropriate map number to generate a map. Current # of map: 3");
			System.out.println("Map numbers: ");
			System.exit(0);
		}
		try {
			Integer.parseInt(args[0]); //Is the input an integer?
		} catch (NumberFormatException e) {
			System.out.println("Provide only an appropriate map number to generate a map. Current # of map: 3");
			System.out.println("Map numbers: ");
			System.exit(0);
		}
		if (Integer.parseInt(args[0]) > 3 || Integer.parseInt(args[0]) < 1) { //Correct map number?
			System.out.println("Invalid map number. Current # of map: 3");
			System.out.println("Map numbers: ");
			System.exit(0);
		}
		Map map = new Map(Integer.parseInt(args[0])); //Finds the appropriate map.
		Terminal terminal = TerminalFacade.createTextTerminal(); //open a terminal window
		terminal.enterPrivateMode();
		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);
		int x = 1;
		int y = 2;
		boolean running = true;
		Player Player = new Player(y, x, 50); //Player has 50 HP.
		int monsterNum = 5; //5 monsters per map.
		while(running){
			String playerStats = "PLAYER: " + Player.getHP() + " HP"; //Display player HP
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

			while (map.isMonster(y, x)) { //Step on top of monster to engage in combat.
				Monster M = new Monster(y, x, 10); //Monster has 10 HP.
				String monsterStats = "MONSTER: " + M.getHP() + " HP";
				String instructions = "You have encountered a monster. Press z to attack.";
				while (Player.isAlive() && M.isAlive()) {
					terminal.moveCursor(0, 32);
					for(int i = 0; i < monsterStats.length(); i = i + 1) {
						terminal.putCharacter(monsterStats.charAt(i)); //Print monster HP.
					}
					terminal.moveCursor(0, 33);
					for(int i = 0; i < instructions.length(); i = i + 1) {
						terminal.putCharacter(instructions.charAt(i)); //Print instructions.
					}
					Key k = terminal.readInput();
					if (k != null) {
						if (k.getCharacter() == 'z') {
						int playerDmg = Player.attack();
						int monsterDmg = M.attack();
						M.takeDmg(playerDmg);
						Player.takeDmg(monsterDmg);
						String text = "You dealt " + playerDmg + " damage and took " + monsterDmg + " damage."; //Print battle progress.
						terminal.moveCursor(0, 34);
						for(int i = 0; i < text.length(); i = i + 1) {
							terminal.putCharacter(text.charAt(i));
						}

						String textPhp = "Your current HP: " + Player.getHP(); //Print player health.
						terminal.moveCursor(0, 35);
						for(int i = 0; i < textPhp.length(); i = i + 1) {
							terminal.putCharacter(textPhp.charAt(i));
						}

						String textMhp = "Monster's HP: " + M.getHP() + "."; //Print monster health.
						terminal.moveCursor(0, 36);
						for(int i = 0; i < textMhp.length(); i = i + 1) {
							terminal.putCharacter(textMhp.charAt(i));
						}

						if (!M.isAlive()) { //If monster died.
							map.changeTile(y, x);
							monsterNum -= 1;
						}

						if (!Player.isAlive()) { //The player is dead.
							map.changeTile(y, x); //To leave the while(isMonster) loop.
						}

						}
						if (k.getKind() == Key.Kind.Escape) {
							terminal.exitPrivateMode();
							running = false;
							map.changeTile(y, x);
						}
					}
				}
				terminal.clearScreen();
			}
			if (key != null) {
				if (key.getKind() == Key.Kind.Escape) {
					terminal.exitPrivateMode();
					running = false;
				}
				if (key.getKind() == Key.Kind.ArrowLeft) {
					if (!map.isWall(y, x - 1)) {
						terminal.moveCursor(x,y);
						terminal.putCharacter(' ');
						x--;
					}
				}
				if (key.getKind() == Key.Kind.ArrowRight) {
					if (!map.isWall(y, x + 1)) {
						terminal.moveCursor(x,y);
						terminal.putCharacter(' ');
						x++;
					}
				}
				if (key.getKind() == Key.Kind.ArrowUp) {
					if (!map.isWall(y - 1, x)) {
						terminal.moveCursor(x,y);
						terminal.putCharacter(' ');
						y--;
					}
				}
				if (key.getKind() == Key.Kind.ArrowDown) {
					if (!map.isWall(y + 1, x)) {
						terminal.moveCursor(x,y);
						terminal.putCharacter(' ');
						y++;
					}
				}
			}

			if (!Player.isAlive()) { //The player is dead.
				running = false;
				terminal.exitPrivateMode();
				System.out.println("");
				System.out.println("");
				System.out.println("YOU DIED.");
			}

			if (monsterNum == 0){ //If all monsters died.
				running = false;
				terminal.exitPrivateMode();
				System.out.println("");
				System.out.println("");
				System.out.println("YOU WIN!!!");
			}
		}
	}
}
