public class Rook extends ChessPiece implements Castleable{

    public Rook(String color, int posx, int posy){
        /* Constructor  String -> Rook */
        super("Rook", color, posx, posy);
    }

    public String toString(){
		/* void -> String */
        return super.toString();
    }

    public int[][] getMove(){
        /* void -> int[][]
         get the list of potential move for a piece */
      	int[][] moveList = new int[64][2];
      	int x = super.position[0];
      	int y = super.position[1];
      	int j = 0;

      	for(int i = 1; i < 8; i++){
        	if(x + i < 8 && (x + i >= 0)){
          	moveList[j][0] = x+i;
          	moveList[j][1] = y;
          	j++;
        	}
        	if(x - i < 8 && (x - i >= 0)){
        	  	moveList[j][0] = x-i;
        	  	moveList[j][1] = y;
        	  	j++;
        	}
        	if(y + i < 8 && (y + i >= 0)){
        	  	moveList[j][1] = y+i;
        	  	moveList[j][0] = x;
        	  	j++;
        	}
        	if(y - i < 8 && (y - i >= 0)){
        	  	moveList[j][1] = y-i;
        	  	moveList[j][0] = x;
        	  	j++;
        	}
      	}
      	moveList[j][0] = -1;
      	return moveList;
	}
	
	public boolean castleable(){
		return super.nbMove == 0;
	}
	
}
