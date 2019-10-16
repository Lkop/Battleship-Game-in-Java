package battleship;

/**
 *
 * @author Lkop
 */
public class Destroyer extends Ship{
    
    public Destroyer(){
    
        super("D",2,3);
    }
    
    public Destroyer(Location start, ShipDirection dir, int length)
    {
        super(start,dir,length);
    }
}
