import java.util.Scanner;

public class Game{
    private ChessBoard board;
    private Player player1;
    private Player player2;
    private int turnCpt;

    public Game(String n1, String n2){
        board = new ChessBoard();
        player1 = new Player(n1);
        player2 = new Player(n2);
        turnCpt = 0;
    }

    public void nextMove(){
      Scanner sc = new Scanner(System.in);
      int x1,y1,x2,y2;
      if(turnCpt % 2 == 0){
        System.out.println("What is your next move ?");
        x1 = sc.nextInt();
        y1 = sc.nextInt();
        x2 = sc.nextInt();
        y2 = sc.nextInt();

        ChessPiece p = board.getAt(x1,y1);
        if(p.getColor() == "White"){
          int[][] moves = p.getMove();
          int[][] realMoves = board.availableMoves(moves, p);

          for(int[] move : moves){
            if(move[0] == x2 && move[1] == y2){
              board.movePiece(x1,y1,x2,y2);
            }
          }
        }
      }
      else{
        System.out.println("What is your next move ?");
        x1 = sc.nextInt();
        y1 = sc.nextInt();
        x2 = sc.nextInt();
        y2 = sc.nextInt();

        ChessPiece p = board.getAt(x1,y1);
        if(p.getColor() == "Black"){
          int[][] moves = p.getMove();
          int[][] realMoves = board.availableMoves(moves, p);

          for(int[] move : moves){
            if(move[0] == x2 && move[1] == y2){
              board.movePiece(x1,y1,x2,y2);
            }
          }
        }
      }
    }

    public void nextTurn(){
        /* adds 1 to turnCpt */
        turnCpt += 1;
    }

    public ChessBoard getBoard(){
      return board;
    }

}
