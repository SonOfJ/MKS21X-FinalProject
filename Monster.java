import java.util.*;
import java.io.*;
public class Monster{
  private Random randgen;
    private int x,y;
    private int health, atk;
    public Monster(int X, int Y, int hp){
      this.x = X;
      this.y = Y;
      this.health = hp;
    }

    public int getHP(){
      return health;
    }

    public int getX(){
      return x;
    }

    public int getY(){
      return y;
    }

    public void setX(int X){
      x = X;
    }

    public void setY(int Y){
      y = Y;
    }

    public void setHP(int hp){
      health = hp;
    }

    public int attack(){
      return (int)(Math.random() * 5);
    }

    public boolean isAlive(){
      return health > 0;
    }

    public void takeDmg(int dmg){
      health = health - dmg;
    }


}
