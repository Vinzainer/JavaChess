public class Chess{

    private ChessBoard board;
    private int score;
    private int turnCpt;

    public Chess(){
        /* Contructor for Chess 
           Creates and initialise */
        board = new ChessBoard();
        turnCpt = 0;

    }

    public void nextTurn(){
        /* adds 1 to turnCpt */
        turnCpt += 1;
    }

}