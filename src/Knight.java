public class Knight extends ChessPiece{

    public Knight(String color, int posx, int posy){
        /* Constructor  String -> Knight */
        super("Knight", color, posx, posy);
    }

    public int[][] getMove(){
        int[][] moveList = new int[64][2];
        int x = super.position[0];
        int y = super.position[1];
        int j = 0;
        if(x + 2 < 8 ){
            if(y - 1 >= 0){
                moveList[j][0] = x + 2;
                moveList[j][1] = y - 1;
                j++;
            }
            if(y + 1 < 8){
                moveList[j][0] = x + 2;
                moveList[j][1] = y + 1;
                j++;
            }
        }
        if(x - 2 >= 0 ){
            if(y + 1 < 8){
                moveList[j][0] = x - 2;
                moveList[j][1] = y + 1;
                j++;
            }
            if(y - 1 >= 0){
                moveList[j][0] = x - 2;
                moveList[j][1] = y - 1;
                j++;
            }
        }
        if(y + 2 < 8 ){
            if(x - 1 >= 0){
                moveList[j][0] = x - 1;
                moveList[j][1] = y + 2;
                j++;
            }
            if(x + 1 < 8){
                moveList[j][0] = x + 1;
                moveList[j][1] = y + 2;
                j++;
            }
        }
        if(y - 2 >= 0 ){
            if(x + 1 < 8){
                moveList[j][0] = x + 1;
                moveList[j][1] = y - 2;
                j++;
            }
            if(x - 1 >= 0){
                moveList[j][0] = x - 1;
                moveList[j][1] = y - 2;
                j++;
            }   
        }
        moveList[j][0] = -1;
        return moveList;
    }
}
