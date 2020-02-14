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

        return 1;
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

    public int[][] availableMoves(int[][] moves, ChessPiece piece){
        /* int[][] * ChessPiece -> int[][]
           change move to only the legal moves available and adds other special moves for some pieces if available
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
            System.out.println("inKing");
            King tmpKing = (King)piece; // Should always be a king
            if(tmpKing.getColor() == "Black"){
                ChessPiece tmpPiece = board[0][0];
                if(tmpPiece instanceof Rook){
                    Rook tmpRook = (Rook)tmpPiece;
                    if(tmpRook.castleable() && tmpKing.Castleable() && vertEmpty(pieceX, pieceY, 0, 1)){
                        int[] nMove = {0,1};
                        aMoves[j] = nMove;
                        j++;
                    }
                }
                tmpPiece = board[0][7];
                if(tmpPiece instanceof Rook){
                    Rook tmpRook = (Rook)tmpPiece;
                    if(tmpRook.castleable() && tmpKing.Castleable() && vertEmpty(pieceX, pieceY, 0, 6)){
                        int[] nMove = {0,6};
                        aMoves[j] = nMove;
                        j++;
                    }
                }
            }
            else{
                System.out.println("caseWhite");
                ChessPiece tmpPiece = board[7][0];
                if(tmpPiece instanceof Rook){
                    Rook tmpRook = (Rook)tmpPiece;
                    System.out.println(tmpKing.Castleable() + " " + tmpRook.castleable());
                    if(tmpRook.castleable() && tmpKing.Castleable() && vertEmpty(pieceX, pieceY, 7, 1)){
                        int[] nMove = {7,1};
                        aMoves[j] = nMove;
                        j++;
                    }
                }
                tmpPiece = board[0][7];
                if(tmpPiece instanceof Rook){
                    Rook tmpRook = (Rook)tmpPiece;
                    System.out.println(tmpKing.Castleable() + " " + tmpRook.castleable());
                    if(tmpRook.castleable() && tmpKing.Castleable() && vertEmpty(pieceX, pieceY, 7, 6)){
                        int[] nMove = {7,6};
                        aMoves[j] = nMove;
                        j++;
                    }
                }
            }

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
        else if(piece instanceof Rook){
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
        else if(piece instanceof Bishop){
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
        else if( piece instanceof Queen){
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
        else if( piece instanceof Pawn){
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
                        if(board[pieceX - 1][pieceY - 1] != null && board[pieceX - 1][pieceY - 1].getColor() != "White"){
                            int[] nmove = new int[2];
                            nmove[0] = pieceX - 1;
                            nmove[1] = pieceY - 1;
                            aMoves[j] = nmove;
                            j++;
                        }
                    }
                    if(pieceY + 1 < 8){
                        if(board[pieceX -1][pieceY + 1] != null && board[pieceX - 1][pieceY + 1].getColor() != "White"){
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
                        if(board[pieceX + 1][pieceY - 1] != null && board[pieceX + 1][pieceY - 1].getColor() != "Black"){
                            int[] nmove = new int[2];
                            nmove[0] = pieceX + 1;
                            nmove[1] = pieceY - 1;
                            aMoves[j] = nmove;
                            j++;
                        }
                    }
                    if(pieceY + 1 < 8){
                        if(board[pieceX + 1][pieceY + 1] != null && board[pieceX + 1][pieceY + 1].getColor() != "Black"){
                            System.out.println(pieceX + " " + pieceY);
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
        return aMoves;
    }
}