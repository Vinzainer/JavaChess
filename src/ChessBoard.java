import java.util.Scanner;
import java.lang.Math;

public class ChessBoard {

    private ChessPiece[][] board;

    public ChessBoard(){
        /* Constructor    -> ChessBoard
           Create an 8*8 ChessPiece[][] and initialise it to the standard piece disposition
           empty cases are null*/

        board = new ChessPiece[8][8];

        for (int i = 0; i < 8; i++){
            if (i == 0 || i == 7){
                board[0][i] = new Rook("Black",0,i);
                board[7][i] = new Rook("White",7,i);
            }
            else if (i == 1 || i == 6){
                board[0][i] = new Knight("Black",0,i);
                board[7][i] = new Knight("White",7,i);
            }
            else if (i == 2 || i == 5){
                board[0][i] = new Bishop("Black",0,i);
                board[7][i] = new Bishop("White",7,i);
            }
            else if (i == 3){
                board[0][i] = new Queen("Black",0,i);
                board[7][i] = new Queen("White",7,i);
            }
            else if (i == 4){
                board[0][i] = new King("Black",0,i);
                board[7][i] = new King("White",7,i);
            }

            board[1][i] = new Pawn("Black",1,i);
            board[6][i] = new Pawn("White",6,i);

        }

    }

    public boolean checkChecker(ChessPiece king , int[][] moves ){

        for(int[] move : moves){
            if(move[0] == -1) break;
            if(king.getPosition()[0] == move[0] && king.getPosition()[1] == move[1]){
                return true;
            }
        }
        return false;
    }

    public ChessBoard(ChessBoard chessBoard){
        ChessPiece[][] tmp = new ChessPiece[8][8];
        ChessPiece piece = null;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                piece = chessBoard.getAt(i,j);
                if(piece == null){
                    tmp[i][j] = null;
                }
                else{
                    if(piece instanceof King){
                        tmp[i][j] = new King(piece.getColor(), i, j);
                    }
                    else if(piece instanceof Queen){
                        tmp[i][j] = new Queen(piece.getColor(), i, j);
                    }
                    else if(piece instanceof Rook){
                        tmp[i][j] = new Rook(piece.getColor(), i, j);
                    }
                    else if(piece instanceof Bishop){
                        tmp[i][j] = new Bishop(piece.getColor(), i, j);
                    }
                    else if(piece instanceof Knight){
                        tmp[i][j] = new Knight(piece.getColor(), i, j);
                    }
                    else if(piece instanceof Pawn){
                        tmp[i][j] = new Pawn(piece.getColor(), i, j);
                    }
                }
            }
        }
        board = tmp;
    }

    private char intToChar(int i){
        return (char)(i + 65);
    }

    public String toString(){
        /* this -> String */
        String str = "\n";
        str += "  | A | B | C | D | E | F | G | H |";
        for (int i = 0; i < 8; i++){
            str += "\n" + (i + 1) + " |";
            for (int j = 0; j < 8; j++){
                if (board[i][j] == null){
                    str += " + |";
                }
                else {
                    str += board[i][j].toString().substring(0,2) + board[i][j].getColor().charAt(0) + "|";
                }
            }
        }
        return str += "\n";
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

    public ChessPiece[][] getBoard(){
        return board;
    }

    public int movePiece(int pieceX, int pieceY, int posx, int posy){
        /* int * int * int * int -> void
           move the piece at point 1 to point 2
           point 1 becomes null after */
        if(posx >= 8 || posx < 0 || posy >= 8 || posy < 0) return 0;            //out of range
        if(pieceX >= 8 || pieceX < 0 || pieceY >= 8 || pieceY < 0) return 0;    //out of range
        ChessPiece tmpPiece = board[pieceX][pieceY];
        if(tmpPiece == null) return 0;                                          //no piece at board[pieceX][pieceY]
        if(board[posx][posy] != null && tmpPiece.getColor() == board[posx][posy].getColor()) return 0;       //piece of same color
        board[posx][posy] = tmpPiece;
        board[pieceX][pieceY] = null;
        tmpPiece.setPosition(posx,posy);
        board[posx][posy].addMove();

        return 1;
    }

    private boolean castle(ChessPiece piece1, ChessPiece piece2){
        int xr;
        int yr;
        int xk;
        int yk;
        String color = piece1.getColor();
        ChessPiece rook, king;
        if(color != piece2.getColor()){
            return false;
        }

        if(piece1 instanceof Rook){
            rook = piece1;
            xr = piece1.getPosition()[0];
            yr = piece1.getPosition()[1];
            king = piece2;
            xk = piece2.getPosition()[0];
            yk = piece2.getPosition()[1];
        }
        else{
            rook = piece2;
            xr = piece2.getPosition()[0];
            yr = piece2.getPosition()[1];
            king = piece1;
            xk = piece1.getPosition()[0];
            yk = piece1.getPosition()[1];
        }

        if(xr != xk){
            return false;
        }

        //big castle
        if(yr == 0){
            movePiece(xr, yr, xr, 3);
            movePiece(xk, yk, xk, 2);
        }
        //small castle
        else{
            movePiece(xr, yr, xr, 5);
            movePiece(xk, yk, xk, 6);
        }
        return true;
    }

    public boolean makeMove(int x1, int y1, int x2, int y2){
        ChessPiece piece = board[x1][y1];
        if(piece instanceof King && Math.abs(y1 - y2) >= 2){
            if(y1 - y2 < 0){
                castle(piece, board[x1][7]);
            }
            else castle(piece, board[x1][0]);
        }
        else{
            movePiece(x1, y1, x2, y2);
        }
        if(piece instanceof Pawn && ((Pawn)piece).upgradable()){
            upgrade(piece);
        }
        return true;
    }


    private boolean vertEmpty(int x1, int y1, int x2, int y2){
        /* int * int * int * int -> boolean
        utility function: check if the vertical line are empty between 2 points
        return true if empty and the second point is either null or of the opposite color */
        ChessPiece movingPiece = board[x1][y1];

        int dx = x1 - x2;
        int dy = y1 - y2;

        if(dx < 0){
            for(int i = x1 + 1; i <= x2; i++){
                ChessPiece tmpPiece = board[i][y1];
                if(i == x2 && tmpPiece != null && tmpPiece.getColor() != movingPiece.getColor()){
                    return true;
                }
                if(tmpPiece != null){
                    return false;
                }
            }
        }
        else if(dx > 0){
            for(int i = x1 - 1; i >= x2; i--){
                ChessPiece tmpPiece = board[i][y1];
                if(i == x2 && tmpPiece != null && tmpPiece.getColor() != movingPiece.getColor()){
                    return true;
                }
                if(tmpPiece != null){
                    return false;
                }
            }
        }
        else if(dy < 0){
            for(int i = y1 + 1; i <= y2; i++){
                ChessPiece tmpPiece = board[x1][i];
                if(i == y2 && tmpPiece != null && tmpPiece.getColor() != movingPiece.getColor()){
                    return true;
                }
                if(tmpPiece != null){
                    return false;
                }
            }
        }
        else{
            for(int i = y1 - 1; i >= y2; i--){
                ChessPiece tmpPiece = board[x1][i];
                if(i == y2 && tmpPiece != null && tmpPiece.getColor() != movingPiece.getColor()){
                    return true;
                }
                if(tmpPiece != null){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean crossEmpty(int x1, int y1, int x2, int y2){
        /* int * int * int * int -> boolean
            utility function: check if the cross line are empty between 2 points
            return true if empty and the second point is either null or of the opposite color */
        ChessPiece movingPiece = board[x1][y1];

        int dx = x1 - x2;
        int dy = y1 - y2;

        if(dx < 0 && dy < 0){
            for(int i = x1 + 1, j = y1 + 1; i <= x2; i++,j++){
                ChessPiece tmpPiece = board[i][j];
                if(i == x2 && tmpPiece != null && tmpPiece.getColor() != movingPiece.getColor()){
                    return true;
                }
                if(tmpPiece != null){
                    return false;
                }
            }
        }
        else if(dx < 0 && dy > 0){
            for(int i = x1 + 1, j = y1 - 1; i <= x2; i++,j--){
                ChessPiece tmpPiece = board[i][j];
                if(i == x2 && tmpPiece != null && tmpPiece.getColor() != movingPiece.getColor()){
                    return true;
                }
                if(tmpPiece != null){
                    return false;
                }
            }
        }
        else if(dx > 0 && dy < 0){
            for(int i = x1 - 1, j = y1 + 1; i >= x2; i--,j++){
                ChessPiece tmpPiece = board[i][j];
                if(i == x2 && tmpPiece != null && tmpPiece.getColor() != movingPiece.getColor()){
                    return true;
                }
                if(tmpPiece != null){
                    return false;
                }
            }
        }
        else if(dx > 0 && dy > 0){
            for(int i = x1 - 1, j = y1 - 1; i >= x2; i--,j--){
                ChessPiece tmpPiece = board[i][j];
                if(i == x2 && tmpPiece != null && tmpPiece.getColor() != movingPiece.getColor()){
                    return true;
                }
                if(tmpPiece != null){
                    return false;
                }
            }
        }

        return true;
    }

    public int[][] availableMoves(int[][] moves, ChessPiece piece, boolean checkChecker){
        /* int[][] * ChessPiece * boolean -> int[][]
           change move to only the legal moves available and adds other special moves for some pieces if available
           rules :
            -Piece can eat piece of other color exept for the pawn who need to be on specific position (explained in commment)
            -King can castle if conditions are met
            -if a move put yourself in check it is not a available move. However the boolean checkChecker handle if you want to check
                if you are in check or not
            -Pawn can move 2 postion if they haven't move from their initial position
            */
        int[][] aMoves = new int[64][2];
        int pieceX = piece.getPosition()[0];
        int pieceY = piece.getPosition()[1];
        int j = 0;
        if(piece instanceof Knight){
            for(int[] move : moves){
                if(move[0] == -1) break;
                int x = move[0];
                int y = move[1];
                if(board[x][y] == null || board[x][y].getColor() != piece.getColor()){
                    aMoves[j] = move;
                    j++;
                }
            }
        }
        else if(piece instanceof King){
            if(piece != null && checkChecker && inCheck(piece)){                //cannot castle if in check
                King tmpKing = (King)piece;                         // Should always be a king / cast to King to use caslteable
                if(tmpKing.getColor() == "Black"){                      //black case
                        ChessPiece tmpPiece = board[0][0];
                        if(tmpPiece instanceof Rook){
                            Rook tmpRook = (Rook)tmpPiece;
                            if(tmpRook.castleable() && tmpKing.castleable() && vertEmpty(pieceX, pieceY, 0, 1)){        //cannot castle if the king or the rook has moved
                                if(!inCheck(new King("Black",0,3)) && !inCheck(new King("Black",0,2))){                 //cannot castle if the cases is under influence of opponent piece
                                    int[] nMove = {0,2};
                                    aMoves[j] = nMove;
                                    j++;
                                }
                            }
                        }
                        tmpPiece = board[0][7];
                        if(tmpPiece instanceof Rook){
                            Rook tmpRook = (Rook)tmpPiece;
                            if(tmpRook.castleable() && tmpKing.castleable() && vertEmpty(pieceX, pieceY, 0, 6)){
                                if(!inCheck(new King("Black",0,6)) && !inCheck(new King("Black",0,5))){
                                    int[] nMove = {0,6};
                                    aMoves[j] = nMove;
                                    j++;
                                }
                            }
                        }
                    }
                    else{                       //white case
                        ChessPiece tmpPiece = board[7][0];
                        if(tmpPiece instanceof Rook){
                        Rook tmpRook = (Rook)tmpPiece;
                        if(tmpRook.castleable() && tmpKing.castleable() && vertEmpty(pieceX, pieceY, 7, 1)){
                            if(!inCheck(new King("White",7,2)) && !inCheck(new King("White",7,3))){
                                int[] nMove = {7,2};
                                aMoves[j] = nMove;
                                j++;
                            }
                        }
                    }
                    tmpPiece = board[0][7];
                    if(tmpPiece instanceof Rook){
                        Rook tmpRook = (Rook)tmpPiece;
                        if(tmpRook.castleable() && tmpKing.castleable() && vertEmpty(pieceX, pieceY, 7, 6)){
                            if(!inCheck(new King("White",7,6)) && !inCheck(new King("White",7,5))){
                                int[] nMove = {7,6};
                                aMoves[j] = nMove;
                                j++;
                            }
                        }
                    }
                }
            }
            
            for(int[] move : moves){    //handles non special moves
                if(move[0] == -1) break;
                int x = move[0];
                int y = move[1];
                if(board[x][y] == null || board[x][y].getColor() != piece.getColor()){
                    aMoves[j] = move;
                    j++;
                }
            }
        }
        else if(piece instanceof Rook){  //no special rules
            for(int[] move : moves){
                if(move[0] == -1) break;
                int x = move[0];
                int y = move[1];

                if(vertEmpty(pieceX, pieceY, x, y)){
                    aMoves[j] = move;
                    j++;
                }

            }
        }
        else if(piece instanceof Bishop){   //no special rules 
            for(int[] move : moves){
                if(move[0] == -1) break;
                int x = move[0];
                int y = move[1];

                if(crossEmpty(pieceX, pieceY, x, y)){
                    aMoves[j] = move;
                    j++;
                }

            }
        }
        else if( piece instanceof Queen){  //no special rules
            for(int[] move : moves){
                if(move[0] == -1) break;
                int x = move[0];
                int y = move[1];
                if(x != piece.getPosition()[0] && y != piece.getPosition()[1]){
                    if(crossEmpty(pieceX, pieceY, x, y)){
                        aMoves[j] = move;
                        j++;
                    }
                }
                else{
                    if(vertEmpty(pieceX, pieceY, x, y)){
                        aMoves[j] = move;
                        j++;
                    }
                }
            }
        }
        else if(piece instanceof Pawn){         
            for(int[] move : moves){
                if(move[0] == -1) break;
                int y = move[1];
                int x = move[0];
                if(board[x][y] == null){
                    aMoves[j] = move;
                    j++;
                }
            }

            if(piece.getColor() == "White"){
                if(pieceX - 1 >= 0){
                    if(pieceY -1 >= 0){
                        if(board[pieceX - 1][pieceY - 1] != null && board[pieceX - 1][pieceY - 1].getColor() != "White"){       //check if they are on their initial position
                            int[] nmove = new int[2];
                            nmove[0] = pieceX - 1;
                            nmove[1] = pieceY - 1;
                            aMoves[j] = nmove;
                            j++;
                        }
                    }
                    if(pieceY + 1 < 8){
                        if(board[pieceX -1][pieceY + 1] != null && board[pieceX - 1][pieceY + 1].getColor() != "White"){        //check if they are on their initial position
                            int[] nmove = new int[2];
                            nmove[0] = pieceX - 1;
                            nmove[1] = pieceY + 1;
                            aMoves[j] = nmove;
                            j++;
                        }
                    }
                }
            }
            else{
                if(pieceX + 1 < 8){
                    if(pieceY - 1 >= 0){
                        if(board[pieceX + 1][pieceY - 1] != null && board[pieceX + 1][pieceY - 1].getColor() != "Black"){       //check if they are on their initial position
                            int[] nmove = new int[2];
                            int[] nmove = new int[2];
                            nmove[0] = pieceX + 1;
                            nmove[1] = pieceY - 1;
                            aMoves[j] = nmove;
                            j++;
                        }
                    }
                    if(pieceY + 1 < 8){
                        if(board[pieceX + 1][pieceY + 1] != null && board[pieceX + 1][pieceY + 1].getColor() != "Black"){          //check if they are on their initial position
                            int[] nmove = new int[2];
                            nmove[0] = pieceX + 1;
                            nmove[1] = pieceY + 1;
                            aMoves[j] = nmove;
                            j++;
                        }
                    }
                }
            }
        }
        aMoves[j][0] = -1;
        if(checkChecker){                   // removing moves that puts you or keeps you in check
            int[][] fMoves = new int[64][2];
            ChessBoard boardCopy;
            int i = 0;
            for(int[] move : aMoves){
                if(move[0] == -1) break;
                boardCopy = new ChessBoard(this);
                boardCopy.makeMove(pieceX, pieceY, move[0], move[1]);
                if(!boardCopy.inCheck(boardCopy.getKing(piece.getColor()))){
                    fMoves[i] = move;
                    i++;
                }
            }
            fMoves[i][0] = -1;
            return fMoves;
        }
        return aMoves;
    }

    public boolean checkMate(ChessPiece king){
        /* ChessPiece -> boolean 
           return true if the player of the piece color is in checkMate
           king should be a King but it works for other pieces too 
           return false if you have at least one move that puts you out of check otherwise return true */
        String color = king.getColor();
        int[][] aMoves;
        for(ChessPiece[] line : board){
            for(ChessPiece piece : line){
                if(piece != null && piece.getColor() == color){
                    aMoves = availableMoves(piece.getMove(), piece, true);
                    if(aMoves[0][0] != -1 ){
                        return false;
                    }
                }
            }
        }    

        return true;
    }

    public boolean inCheck(ChessPiece king){
        /* ChessPiece -> boolean
           return true if king is in check
           king should be King but works with other pieces too
            */
        String color = king.getColor();
        for(ChessPiece[] line : board){
            for(ChessPiece piece : line){
                if(piece != null && piece.getColor() != color && checkChecker(king, availableMoves(piece.getMove(), piece, false))){
                    return true;
                }
            }
        }
        return false;
    }

    public ChessPiece getKing(String color){
        /* String -> ChessPiece 
           returns a king of the color in parameter 
           if there is no king of the color return null*/
        for(ChessPiece[] line : board){
            for(ChessPiece piece : line){
                if(piece != null && piece instanceof King && piece.getColor() == color){
                    return piece;
                }
            }
        }
        //System.out.println("out");

        return null;
    }

    public boolean upgrade(ChessPiece piece){
        /* to do  */
        int choice;
        System.out.println("You need to change your pawn into a: \n1.Queen\n2.Rook\n3.Bishop\n4.Knight");
        System.out.println("Choose your fighter : ");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();

        ChessPiece newPiece;
        int x = piece.getPosition()[0];
        int y = piece.getPosition()[1];
        String color = piece.getColor();

        switch (choice){
            case 1:
                newPiece = new Queen(color, x, y);
                setAt(newPiece,x,y);
                return true;
            case 2:
                newPiece = new Rook(color, x, y);
                setAt(newPiece,x,y);
                return true;
            case 3:
                newPiece = new Bishop(color, x, y);
                setAt(newPiece,x,y);
                return true;
            case 4:
                newPiece = new Knight(color, x, y);
                setAt(newPiece,x,y);
                return true;
            default:
                System.out.println("Wrong choice.");
        };
        return false;
    }
}
