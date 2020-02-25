import java.util.Scanner;

public class Game{
    private ChessBoard board;
    private Player player1;
    private Player player2;
    private int turnCpt;

    public Game(String n1, String n2){
		/* String * String -> Game
		   Constructor of Game */
        board = new ChessBoard();
        player1 = new Player(n1);
        player2 = new Player(n2);
        turnCpt = 0;
	}

	public Game(Game game){
		/* Game -> Game
		   Constructor copy of Game (should be a deepCopy)*/
		board = game.getBoard();
		player1 = game.getPlayer1();
		player2 = game.getPlayer2();
		turnCpt = game.getTurn();
	}

	private int letterToInt(String str){
		char letter = str.toUpperCase().charAt(0);
		return (int)letter - (int)'A';
	}

    public void nextMove(){
		Scanner sc = new Scanner(System.in);
		ChessBoard tmpBoard;
		int x1,y1,x2,y2;
		boolean endTurn = false;
		String color;
		ChessPiece piece;
		int[][] moves;
		if(turnCpt % 2 == 0){
			color = "White";
		}
		else color = "Black";
        System.out.println(color + " are on ! ");
		while(!endTurn){
			System.out.println("\nChoose a piece :");
            System.out.print("Line number : ");
    		x1 = sc.nextInt() - 1;
    		sc.nextLine();
            if(x1 < 0 || x1 > 7){
                System.out.println("Out of board.");
                continue;
            }
			System.out.print("Line letter : ");
			y1 = letterToInt(sc.nextLine());
            if(y1 < 0 || y1 > 7){
                System.out.println("Out of board.");
                continue;
            }
			piece = board.getAt(x1, y1);
			if(piece == null){
				System.out.println("No piece at selected location");
				continue;
			}
			if(piece.getColor() != color){
				System.out.println("Selected Piece is not yours");
				continue;
			}

			moves = piece.getMove();
			moves = board.availableMoves(moves, piece, true);
			piece.printMovesOnBoard(moves);

			if(moves[0][0] == -1){
				System.out.println("No moves available on selected piece");
				continue;
			}

			System.out.println("Choose a move : ");
			System.out.print("Line number : ");
			x2 = sc.nextInt() - 1;
			sc.nextLine();
			System.out.print("Line letter : ");
			y2 = letterToInt(sc.nextLine());

			int i = 0;
			while(moves[i][0] != -1){
				if(moves[i][0] == x2 && moves[i][1] == y2){
					endTurn = true;
					//sc.close(); //breaks everything
					break;
				}
			  i++;
			}
			if(!endTurn){
				System.out.println("Selected move is not available");
				continue;
			}
			board.makeMove(x1, y1, x2, y2);

		}
    }

	public boolean winChecker(ChessPiece king){
		return board.checkMate(king);
	}

    public void nextTurn(){
		/* void -> void
		   adds 1 to turnCpt */
        turnCpt += 1;
    }

    public ChessBoard getBoard(){
		/* void -> ChessBoard
		get-er for board */
      	return board;
	}

	public Player getPlayer1(){
		/* void -> Player
		   get-er fir player1 */
		return player1;
	}

	public Player getPlayer2(){
		/* void -> Player
		   get-er for player2 */
		return player2;
	}

	public int getTurn(){
		/* void -> int
		   get-er for turnCpt*/
		return turnCpt;
	}

}
