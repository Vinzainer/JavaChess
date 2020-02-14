public class King extends ChessPiece{

    public King(String color, int posx, int posy){
        /* Constructor  String -> King */
        super("King", color, posx, posy);
    }

    public int[][] getMove(){
      	//System.out.println("88");
      	int[][] moveList = new int[64][2];
      	int x = super.position[0];
      	int y = super.position[1];
      	int j = 0;
      	if(x + 1 < 8){
        	moveList[j][0] = x+1;
        	moveList[j][1] = y;
        	j++;
        	if(y + 1 < 8){
        	  	moveList[j][0] = x+1;
        	  	moveList[j][1] = y+1;
          		j++;
        	}
        	if(y - 1 >= 0){
          		moveList[j][0] = x+1;
          		moveList[j][1] = y-1;
          		j++;
        	}
      	}
      	if(x - 1 >= 0){
        	moveList[j][0] = x-1;
        	moveList[j][1] = y;
        	j++;
        	if(y + 1 < 8){
          		moveList[j][0] = x-1;
          		moveList[j][1] = y+1;
          		j++;
        	}
        if(y - 1 >= 0){
          	moveList[j][0] = x-1;
          	moveList[j][1] = y-1;
          	j++;
        	}
      	}
      	if(y - 1 >= 0){
        	moveList[j][0] = x;
        	moveList[j][1] = y-1;
        	j++;
      	}
      	if(y + 1 < 8){
        	moveList[j][0] = x;
        	moveList[j][1] = y+1;
        	j++;
      	}
      	moveList[j][0] = -1;
      	moveList[j][1] = -1;
      	return moveList;
    }
}
