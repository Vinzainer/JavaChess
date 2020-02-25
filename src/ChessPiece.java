public abstract class ChessPiece{

    private String pieceName;
    protected String color;
	protected int[] position;
	protected int nbMove;

    public ChessPiece(String name, String color, int posx, int posy){
        /* Constructor  String * String -> ChessPiece */
        pieceName = name;
        this.color = color;
        position = new int[2];
        position[0] = posx;
		position[1] = posy;
		nbMove = 0;
    }

    public String toString(){
		/* void -> String */
        return pieceName;
    }

    public String getColor(){
		/* void -> String
		   get-er for Color */
        return color;
    }

	public void addMove(){
		nbMove++;
	}


    public void setPosition(int posx, int posy){
		/* int * int -> void
		set-er for Position */
      	position[0] = posx;
      	position[1] = posy;
    }

    public int[] getPosition(){
		/* void -> int[]
		get-er for Position */
      return position;
    }

    private boolean inMoves(int x, int y, int[][] moves){
		/* int * int * int[][] -> boolean
		takes a move in 2 int and return true if it's in moves */
        int i = 0;
        while(moves[i][0] != -1){
			if(moves[i][0] == x && moves[i][1] == y){
				return true;
			}
          i++;
        }

      return false;
    }

    public void printMovesOnBoard(int[][] moves){
		/* int[][] -> void
		   std output : shows the moves in a more visual way */
		System.out.println(position[0] + " " + position[1]);
      	String str = "  | A | B | C | D | E | F | G | H |";
      	for(int i = 0; i < 8; i++){
		    str += "\n" + (i + 1) + " |";
        	for(int j = 0; j < 8; j++){
				if(position[0] == i && position[1] == j){
					str += " P |";
				}

          		else if(inMoves(i, j, moves)){
					str += " + |";
				  }
				else{
					str += " - |";
				}
        	}
		}
		System.out.println(str);
    }

    public abstract int[][] getMove();
}
