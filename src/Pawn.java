public class Pawn extends ChessPiece{

    public Pawn(String color, int posx, int posy){
        /* Constructor  String -> Pawn */
        super("Pawn", color, posx, posy);
    }

    public String toString(){
        return super.toString();
    }

    public int[][] getMove(){
      int[][] moveList = new int[64][2];
      int x = super.position[0];
      int y = super.position[1];
      int j = 0;

      if(super.color == "Black"){
        moveList[j][0] = x + 1;
        moveList[j][1] = y;
        j++;
        if(x == 1){
          moveList[j][0] = x + 2;
          moveList[j][1] = y;
          j++;
        }
      }
      else{
        moveList[j][0] = x - 1;
        moveList[j][1] = y;
          if(x == 6){
            j++;
            moveList[j][0] = x - 2;
            moveList[j][1] = y;
          j++;
        }
      }

      moveList[j][0] = -1;
      return moveList;
    }
}
