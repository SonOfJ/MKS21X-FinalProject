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
import java.util.*;
import java.io.*;
public class Map {
  private int seed;
  private Character tile[][];
  private String row[];

  public Map(int newSeed) {
    seed = newSeed;
    tile = new Character[50][50];
    row = new String[50];
    Scanner reader = new
    if (seed == 1){
    row[0] = "##################################################";
    row[1] = "#   #            #      #                        #";
    row[2] = "#                #      #                        #";
    row[3] = "#                #      ##########               #";
    row[4] = "#   #            #      #                        #";
    row[5] = "#   #                                  ###########";
    row[6] = "#   #                                            #";
    row[7] = "#   #            #      #                        #";
    row[8] = "#   #            #      #                        #";
    row[9] = "#   ##############################     ###########";
    row[10] = "#                                                #";
    row[11] = "#                                                #";
    row[12] = "####################                             #";
    row[13] = "#     #     #      #                             #";
    row[14] = "#     #     #      #                             #";
    row[15] = "#     #     #      #                             #";
    row[16] = "#     #     #      #############     #############";
    row[17] = "#                                                #";
    row[18] = "#                                                #";
    row[19] = "#     #     #      #############     #############";
    row[20] = "#     #     #      #                             #";
    row[21] = "#     #     #      #                             #";
    row[22] = "#     #     #      #     #           #           #";
    row[23] = "####################     #           #           #";
    row[24] = "#    #        #    #     #           #           #";
    row[25] = "#             #    #     #           #           #";
    row[26] = "#             #    #     #           #           #";
    row[27] = "#    #        #    #######           #############";
    row[28] = "#    #        ######                             #";
    row[29] = "######                                           #";
    row[30] = "#                                    #           #";
    row[31] = "#################    #############################";
    row[32] = "#     #                                          #";
    row[33] = "#     #                                          #";
    row[34] = "#     #     #   ######  ######      ##############";
    row[35] = "#     #     #   #            #   ####            #";
    row[36] = "#     #     #   #            #                   #";
    row[37] = "#           #   #            #                   #";
    row[38] = "#           #   #            #   ####            #";
    row[39] = "#############   ##############      ##############";
    row[40] = "#                                                #";
    row[41] = "#############                                    #";
    row[42] = "#           #                                    #";
    row[43] = "#  #######  #                                    #";
    row[44] = "#  #     #  #                                    #";
    row[45] = "#  #  ####  #                                    #";
    row[46] = "#  #        #                                    #";
    row[47] = "#  ##########                                    #";
    row[48] = "#                                                #";
    row[49] = "##################################################";
    for(int i = 0; i < 50; i = i + 1) {
      for(int j = 0; j < 50; j = j + 1) {
        tile[i][j] = row[i].charAt(j);
      }
    }
  }
}

  public String getRow(int rowNum) {
    return row[rowNum];
  }

  public String print(){
    if (seed == 1){
      return "World 1 : 50x50 size";
    }
    return "NOT OPEN. ONLY ONE MAP CURRENTLY";
  }

  public boolean isWall(int y, int x){
    //check is tile is a wall
    return tile[y][x] != ' ';
  }
}
