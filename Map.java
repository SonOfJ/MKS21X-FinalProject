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
  private Random randgen;
  public Map(int newSeed) {
    seed = newSeed;
    tile = new Character[31][60];
    row = new String[31];
    try {
      Scanner reader = new Scanner(new File("World1.txt")); //Reader to read the text file that contains the map.
      int count = 1;
      while (reader.hasNext()) { //If there is still something to read
        row[count] = reader.nextLine();
        count = count + 1;
      }
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
    for(int i = 1; i < 31; i = i + 1) {
      for(int j = 0; j < 60; j = j + 1) {
        tile[i][j] = row[i].charAt(j);
      }
    }
    createMonster();
  }

  public String getRow(int rowNum) {
    return row[rowNum];
  }

  public String print() {
    if (seed == 1){
      return "World 1";
    }
    if (seed == 2){
      return "World 2";
    }
    if (seed == 3){
      return "World 3";
    } else {
      return "Invalid World Number.";
    }
  }

  public boolean isWall(int y, int x){
    //check is tile is a isWall
    return tile[y][x] == '#';
  }

  public void createMonster(){
    for (int i = 0; i < 5; i++){
      boolean isCreated = false;
      while (isCreated == false){
        int y = (int)(Math.random() * 29 + 1);
        int x = (int)(Math.random() * 59);
        if (tile[y][x] == ' ' && (y != 2 && x != 1)){
          tile[y][x] = '@';
          isCreated = true;
        }
      }
    }
  }

  public boolean isMonster(int y, int x){
    //check is tile is a isWall
    return tile[y][x] == '@';
  }

}
