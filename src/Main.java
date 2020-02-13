public class Main {


    public static void main(String[] args){

       ChessBoard b = new ChessBoard();

        System.out.println(b.toString());

        b.movePiece(0, 1, 2, 2);

        System.out.println(b.toString());

    }
}