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

      for(int i = 1; i < 8; i++){
        if(x + i < 8){
          moveList[j][0] = x+i;
          j++;
        }
        if(x + i < 8){
          moveList[j][0] = x-i;
          j++;
        }
        if(y + i < 8){
          moveList[j][1] = y+i;
          j++;
        }
        if(y + i < 8){
          moveList[j][1] = y-i;
          j++;
        }
      }

      return moveList;
    }
}
