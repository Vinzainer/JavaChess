public class Main {


    public static void main(String[] args){

       ChessBoard b = new ChessBoard();

        System.out.println(b.toString());

        b.movePiece(0,0,4,4);
        System.out.println(b.toString());
        ChessPiece r1 = b.getAt(4,4);
        int[][] ml = new int[64][2];
        ml = r1.getMove();

        for(int i = 0; i < 64; i++){
          if(ml[i][0] == -1) break;

          System.out.println(ml[i][0] + ", " + ml[i][1]);
        }
    }
}
