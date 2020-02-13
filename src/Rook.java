public class Rook extends ChessPiece{


    public Rook(String color, int posx, int posy){
        /* Constructor  String -> Rook */
        super("Rook", color, posx, posy);
    }

    public String toString(){
        return super.toString();
    }

    public int[] getMove(){
      int[][] moveList = new int[64][2];

      for(int i = 0; i < 8; i++){

      }
    }
}
