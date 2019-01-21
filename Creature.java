abstract class Creature {
  private int x,y;
  private int health, atk;

  public Creature(int X, int Y, int hp, int ATK){
    this.x = X;
    this.y = Y;
    this.health = hp;
    this.atk = ATK;
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

  public int attack(){
    return atk;
  }

  public boolean isAlive(){
    return health > 0;
  }

  public void takeDmg(int dmg){
    health = health - dmg;
  }


}
