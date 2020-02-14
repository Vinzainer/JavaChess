public class Main {


    public static void main(String[] args){

    	ChessBoard b = new ChessBoard();

        System.out.println(b.toString());

        b.movePiece(7,3,4,4);
        b.movePiece(1, 0, 5, 2);
        System.out.println(b.toString());
        ChessPiece b1 = b.getAt(5,2);
        int[][] ml = new int[64][2];
        //System.out.println("a");
        ml = b1.getMove();
        int[][] aml = new int[64][2];
        aml = b.availableMoves(ml, b1);

        b1.printMovesOnBoard(aml);

    }
}
