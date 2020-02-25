public class Main {
    public static void main(String[] args){
      Game test = new Game("Vinz", "Pierro");
      String color = null;
      String en_color = null;

      for(int i = 0; i < 100; i++){
        System.out.println(test.getBoard());
		test.nextMove();
        //System.out.println(test.getBoard().checkChecker("White"));
        if(test.getTurn() %2 == 0){
            color = "White";
            en_color = "Black";
        }
        else {
            color = "Black";
            en_color = "White";
        }

        ChessBoard board = test.getBoard();
        ChessPiece king = board.getKing(en_color);

        if(test.winChecker(king)){
            System.out.println(test.getBoard().toString());
            System.out.println(color + " wins");
            break;
        }
        else if(board.inCheck(king)){
            System.out.println("\nCHECK !");
        }
        test.nextTurn();
        }
    }
}
