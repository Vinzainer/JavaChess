public class Pawn extends ChessPiece implements Upgradable{

    public Pawn(String color, int posx, int posy){
        /* Constructor  String -> Pawn */
        super("Pawn", color, posx, posy);
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

    	if(super.color == "Black"){
    		moveList[j][0] = x + 1;
        	moveList[j][1] = y;
        	j++;
        	if(x == 1){
        	  	moveList[j][0] = x + 2;
        	  	moveList[j][1] = y;
        	  	j++;
        	}
    	}
    	else{
    	    moveList[j][0] = x - 1;
    	    moveList[j][1] = y;
    	    j++;
    	    if(x == 6){
    	        moveList[j][0] = x - 2;
    	        moveList[j][1] = y;
    	        j++;
    	    }
    	}
    	moveList[j][0] = -1;
    	return moveList;
    }

    public boolean upgradable(){
      if(super.color == "White" && super.position[0] == 0) return true;
      if(super.color == "Black" && super.position[0] == 7) return true;
      return false;
    }
}
