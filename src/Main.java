public class Main {
    public static void main(String[] args){
      Game test = new Game("Vinz", "Pierro");
      String color = null;

      for(int i = 0; i < 100; i++){
        System.out.println(test.getBoard());
		test.nextMove();
        //System.out.println(test.getBoard().checkChecker("White"));
        if(test.getTurn() %2 == 0){
            color = "white";
        }
        else color = "Black";
        if(test.winChecker(color)){
            test.getBoard().toString();
            System.out.println(color + " wins");
            break;
        } 
        test.nextTurn();
        }
    }
}
