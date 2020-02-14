public class Main {
    public static void main(String[] args){
      Game test = new Game("Vinz", "Pierro");

      for(int i = 0; i < 100; i++){
        System.out.println(test.getBoard());
        test.nextMove();
        test.nextTurn();
      }
    }
}
