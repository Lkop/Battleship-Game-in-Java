package battleship;

import java.util.Random;

/**
 *
 * @author Lkop
 */
public class ComputerPlayer extends Player {
    
    private static int cpu_id = 1;
    private Random random = new Random();
    
    
    public ComputerPlayer(){
    
        super("CPU_"+cpu_id);
        cpu_id++;
        
    }
    
    @Override
    public Location selectMove() throws InvalidLocationException{
        
        //row, column     
        int r = random.nextInt(playerField().getRows());
        int c = random.nextInt(playerField().getCols());
        
        return playerField().getLocation(r, c);
    }
    
    @Override
    public void placeShips(Field otherField){
    
        //φτιαχνουμε τα odj Ship(submarine destroyer aircraftcarrier)
        initShips();
        
  
        for(int i=0; i<7; i++){
            
            otherField.placeShipRandomly(returnShips(i), 0, true);
        }
    }
}
