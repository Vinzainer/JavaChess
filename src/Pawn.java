public class Pawn extends ChessPiece{

    public Pawn(String color, int posx, int posy){
        /* Constructor  String -> Pawn */
        super("Pawn", color, posx, posy);
    }

    public int[] getMove(){

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
