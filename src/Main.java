public class Main {


    public static void main(String[] args){

       ChessBoard b = new ChessBoard();

        System.out.println(b.toString());

        b.movePiece(0,4,4,4);
        System.out.println(b.toString());
        ChessPiece b1 = b.getAt(0,4);
        int[][] ml = new int[64][2];
        ml = b1.getMove();
        System.out.println("a");

        for(int i = 0; i < 64; i++){
          if(ml[i][0] == -1) break;

          System.out.println(ml[i][0] + ", " + ml[i][1]);
        }
    }
}
