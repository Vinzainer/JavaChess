public class King extends ChessPiece{

    public King(String color, int posx, int posy){
        /* Constructor  String -> King */
        super("King", color, posx, posy);
    }

    public int[][] getMove(){
      int[][] moveList = new int[64][2];
      int x = super.position[0];
      int y = super.position[1];
      int j = 0;

      moveList[j][0] = x+1;
      moveList[j][0] = y;
      j++;
      moveList[j][0] = x;
      moveList[j][0] = y+1;
      j++;
      moveList[j][0] = x-1;
      moveList[j][0] = y;
      j++;
      moveList[j][0] = x;
      moveList[j][0] = y-1;
      j++;
      moveList[j][0] = x+1;
      moveList[j][0] = y+1;
      j++;
      moveList[j][0] = x-1;
      moveList[j][0] = y-1;
      j++;
      moveList[j][0] = x+1;
      moveList[j][0] = y-1;
      j++;
      moveList[j][0] = x-1;
      moveList[j][0] = y+1;
      j++;

      moveList[j][0]

      return moveList;
    }
}
