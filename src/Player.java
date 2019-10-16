package battleship;

/**
 *
 * @author Lkop
 */
public abstract class Player {
    
    private final String name;
    private int score=0;
    
    private Field field;
    
    private Ship[] ships = new Ship[7];

    
    
    public Player(String name){
    
        this.name = name;
    }
    
    public Field playerField(){
        
        return field;
    }
    
    public Field initField(int r, int c, Player player, Player op_player){
    
        field = new Field(r, c, player, op_player);
        return field;
    }
    
    public void initShips(){
    
        ships[0] = new AircraftCarrier();
        ships[1] = new AircraftCarrier();
        
        ships[2] = new Destroyer();
        ships[3] = new Destroyer();
        ships[4] = new Destroyer();
        
        ships[5] = new Submarine();
        ships[6] = new Submarine();
    
    }
    
    public Ship returnShips(int i){

        return ships[i];
    }
    
    
    public int getScore(){
    
        return score;
    }
    
    public String getName(){
    
        return name;
    }
    
    public Field otherField(Player p){
    
        return p.playerField();
    }
    
    public boolean hasWon(){
        
        //Συνολο ποντων με ολα τα πλοια βυθισμενα
        if(score==22)
            return true;
        
        return false;
    }
    
    public void setScore(int score){
    
        this.score += score;
    }
    
    public abstract Location selectMove() throws InvalidLocationException;
    public abstract void placeShips(Field otherField);
}
