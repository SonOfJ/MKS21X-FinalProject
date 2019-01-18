/*
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
*/
import java.util.*;
import java.io.*;
public class Map {
  private int seed;
  private Character tile[][];
  private String row[];
  public Map(int newSeed) {
    seed = newSeed;
    tile = new Character[3][6];
    row = new String[3];

    Scanner reader = new Scanner("World1.txt"); //Reader to read the text file that contains the map.
    int count = 0;
    while (reader.hasNext()) { //If there is still something to read
      row[count] = reader.nextLine();
      count = count + 1;
    }
    /*
    for(int i = 0; i < 3; i = i + 1) {
      for(int j = 0; j < 6; j = j + 1) {
        tile[i][j] = row[i].charAt(j);
      }
    }
    */
  }
  public String printArray() {
    String kai = "";
    for(int i = 0; i < row.length; i = i + 1) {
      kai = kai + row[i];
    }
    return kai;
  }
  public String getRow(int rowNum) {
    return row[rowNum];
  }
  public String print() {
    if (seed == 1){
      return "World 1 : 50x50 size";
    }
    return "NOT OPEN. ONLY ONE MAP CURRENTLY";
  }
  public boolean isWall(int y, int x){
    //check is tile is a isWall
    return tile[y][x] != ' ';
  }
  public static void main(String[] args) {
    Map map = new Map(1);
    System.out.println(map.printArray());
  }
}
