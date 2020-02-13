public class Knight extends ChessPiece{

    public Knight(String color, int posx, int posy){
        /* Constructor  String -> Knight */
        super("Knight", color, posx, posy);
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
