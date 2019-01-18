public abstract class Creature {
  private int x,y;
  private int health;

  public Creature(int X, int Y, int hp){
    this.x = X;
    this.y = Y;
    this.health = hp;
  }

  public abstract int getHP();
}
