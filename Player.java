import java.util.*;
import java.io.*;
public class Player{
  private Random randgen;
  private int x,y;
  private int health, atk;
  public Player(int X, int Y, int hp){
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
    return randgen.nextInt(11);
  }

  public boolean isAlive(){
    return health > 0;
  }

  public void takeDmg(int dmg){
    health = health - dmg;
  }
}
