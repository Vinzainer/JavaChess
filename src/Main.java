public class Main {


    public static void main(String[] args){

    	ChessBoard b = new ChessBoard();

        System.out.println(b.toString());

        b.movePiece(7,0,4,4);
        System.out.println(b.toString());
        ChessPiece b1 = b.getAt(4,4);
        int[][] ml = new int[64][2];
        //System.out.println("a");
        ml = b1.getMove();
        int[][] aml = new int[64][2];
        aml = b.availableMoves(ml, b1);

        b1.printMovesOnBoard(aml);

    }
}
