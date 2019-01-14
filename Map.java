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
public class Map {
  private int seed;
  private Character tile[][];
  public Map(int newSeed) {
    seed = newSeed;
    tile = new Character[50][50];

  }

  public String print(){
    if (seed == 1){
      return "World 1 : 50x50 size";
    }
    return "NOT OPEN. ONLY ONE MAP CURRENTLY";
  }

  public boolean isWall(int x, int y){
    //check is tile is a wall
    return tile[x][y] != null;
  }
}
