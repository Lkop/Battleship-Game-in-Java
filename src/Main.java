package battleship;

/**
 *
 * @author Lkop
 */
public class Main {

    public static void main(String[] args) {
       
        Game battleship = new Game();
        
        battleship.init();
        battleship.play();
        battleship.showResult();
    }
    
}
