public abstract class ChessPiece{

    private String pieceName;
    private String color;

    public ChessPiece(String name, String color){
        /* Constructor  String * String -> ChessPiece */
        pieceName = name;
        this.color = color;
    }

    public String toString(){
        return pieceName;
    }

    public String getColor(){
        return color;
    }

}
