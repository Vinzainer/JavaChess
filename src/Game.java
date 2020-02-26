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
		/* void -> void 
		   handles the majority of a turn execution*/
		Scanner sc = new Scanner(System.in);
		int x1,y1,x2,y2;
		boolean endTurn = false;
		String color;
		ChessPiece piece;
		int[][] moves;
		if(turnCpt % 2 == 0){  //color checker 
			color = "White";
		}
		else color = "Black";
        System.out.println(color + " are on ! ");
		while(!endTurn){ 							//loop for wrong input
			System.out.println("\nChoose a piece :");
            System.out.print("Line number : ");
    		x1 = sc.nextInt() - 1;   				//scan for a position in the board then substract 1 to fit the list 
    		sc.nextLine();							//skip line 
            if(x1 < 0 || x1 > 7){					//security for OOB
                System.out.println("Out of board.");
                continue;
            }
			System.out.print("Line letter : ");
			y1 = letterToInt(sc.nextLine());		//scan for a letter and convert it to an int to fit the list
            if(y1 < 0 || y1 > 7){
                System.out.println("Out of board.");
                continue;
            }
			piece = board.getAt(x1, y1);
			if(piece == null){						//security if position is empty
				System.out.println("No piece at selected location");
				continue;
			}
			if(piece.getColor() != color){			//security if selected position has a piece of the wrong color
				System.out.println("Selected Piece is not yours");
				continue;
			}

			moves = piece.getMove();
			moves = board.availableMoves(moves, piece, true);
			
			if(moves[0][0] == -1){					//security if piece has no moves available
				System.out.println("No moves available on selected piece");
				continue;
			}

			piece.printMovesOnBoard(moves);

			System.out.println("Choose a move : ");		//
			System.out.print("Line number : ");			//
			x2 = sc.nextInt() - 1;						//Same as first 
			sc.nextLine();								//
			System.out.print("Line letter : ");			//
			y2 = letterToInt(sc.nextLine());			//

			int i = 0;
			while(moves[i][0] != -1){
				if(moves[i][0] == x2 && moves[i][1] == y2){
					endTurn = true;
					break;
				}
			  i++;
			}
			if(!endTurn){								//security if selected move is not in the available moves of the selected piece
				System.out.println("Selected move is not available");
				continue;
			}
			board.makeMove(x1, y1, x2, y2);

		}
    }

	public boolean winChecker(ChessPiece king){
		/* ChessPiece -> boolean
		   return if the king is in checkmate 
		   it calls for a function of board that checks if the pieces of the same color as the king
		   has a move list size over 0
		   if one piece can move and makes the king no longer in check it means its not in checkmate*/
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
