public class ChessBoard {

    private ChessPiece[][] board;

    public ChessBoard(){
        /* Constructor    -> ChessBoard
           Create an 8*8 ChessPiece[][] and initialise it to the standard piece disposition
           empty cases are null*/

        board = new ChessPiece[8][8]; 
        
        for (int i = 0; i < 8; i++){
            if (i == 0 || i == 7){
                board[0][i] = new Rook("Black");
                board[7][i] = new Rook("White");
            }
            else if (i == 1 || i == 6){
                board[0][i] = new Knight("Black");
                board[7][i] = new Knight("White");
            }
            else if (i == 2 || i == 5){
                board[0][i] = new Bishop("Black");
                board[7][i] = new Bishop("White");
            }
            else if (i == 3){
                board[0][i] = new Queen("Black");
                board[7][i] = new Queen("White");
            }
            else if (i == 4){
                board[0][i] = new King("Black");
                board[7][i] = new King("White");
            }

            board[1][i] = new Pawn("Black");
            board[6][i] = new Pawn("White");

        }

    }

    public String toString(){
        /* this -> String */
        String str = "";
        for (int i = 0; i < 8; i++){
            str += "\n|";
            for (int j = 0; j < 8; j++){
                if (board[i][j] == null){
                    str += " + |";
                }
                else {
                    str += board[i][j].toString().substring(0,2) + board[i][j].getColor().charAt(0) + "|";
                }
            }
        }
        return str;
    }

    public int setAt(ChessPiece piece ,int posx ,int posy){
        /* ChessPiece * int * int -> int
           Set-er  for Board 
           return 1 if successfull*/
        if(posx >= 8 || posx < 0) return 0;
        if(posx >= 8 || posx < 0) return 0;

        if(piece == null){            //
            board[posx][posy] = null; //Maybe useless
        }                             //
        else{
            board[posx][posy] = piece;
        }

        return 1;
    }

    public ChessPiece getAt(int posx, int posy){
        /* int * int -> ChessPiece
           get-er for Board */
        return board[posx][posy];
    }

}