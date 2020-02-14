public class Player{
  private String name;
  private int score;

  public Player(String n){
    name = n;
    score = 0;
  }

  public String toString(){
    String s = "Player : " + name + ". Score : " + score + ".";
    return s;
  }

  public int getScore(){
    return score;
  }


}
