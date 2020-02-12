public class ChessBoard {

    private ChessPiece[][] board;

    public ChessBoard(){
        /* Constructor    -> ChessBoard
           Create an 8*8 ChessPiece[][] and initialise it to the standard piece disposition
           empty cases are null*/

        board = new ChessPiece[8][8]; 
        
        for (int i = 0; i < 8; i++){
            if (i == 0 || i == 7){
                board[0][i] = new Rook("black");
                board[7][i] = new Rook("White");
            }
            else if (i == 1 || i == 6){
                board[0][i] = new Knight("black");
                board[7][i] = new Knight("White");
            }
            else if (i == 2 || i == 5){
                board[0][i] = new Bishop("black");
                board[7][i] = new Bishop("White");
            }
            else if (i == 3){
                board[0][i] = new Queen("black");
                board[7][i] = new Queen("White");
            }
            else if (i == 4){
                board[0][i] = new King("black");
                board[7][i] = new King("White");
            }

            board[1][i] = new Pawn("black");
            board[6][i] = new Pawn("White");

        }

    }

    public String toString(){
        /* this -> String */
        String str = "";
        for (int i = 0; i < 8; i++){
            str += "\n";
            for (int j = 0; j < 8; j++){
                if (board[i][j] == null){
                    str += "null ";
                }
                else {
                    str += board[i][j].toString() + " ";
                }
            }
        }
        return str;
    }

}