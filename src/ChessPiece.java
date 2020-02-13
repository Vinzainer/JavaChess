public abstract class ChessPiece{

    private String pieceName;
    private String color;
    protected int[] position;

    public ChessPiece(String name, String color, int posx, int posy){
        /* Constructor  String * String -> ChessPiece */
        pieceName = name;
        this.color = color;
        position = new int[2];
        position[0] = posx;
        position[1] = posy;
    }

    public String toString(){
        return pieceName;
    }

    public String getColor(){
        return color;
    }

    public void setPosition(int posx, int posy){
      position[0] = posx;
      position[1] = posy;
    }

    public int[] getPosition(){
      return position;
    }

    public abstract int[][] getMove();
}
