package battleship;

/**
 *
 * @author Lkop
 */
public class Location {
    
    private int row;
    private int col;
    private Ship ship;
    private boolean marked = false;
    
    
    public Location(int r, int c){
     
        row = r;
        col = c; 
    }
    
    public Ship getShip(){
    
        return ship;
    }
    
    public void setShip(Ship ship){
    
        this.ship = ship;
    }
    
    public boolean isShip(){
    
        if(ship==null)
            return false;
        
        return true;
    }
    
    public void mark(){
    
        marked = true;    
    }
    
    public boolean isMarked(){

        if(marked == true){
        
            return true;
        }
        return false;
    }

    public boolean isHit(){
    
        if(ship == null && marked == true){
        
            return true;
        }
        return false;
    }
    
    public boolean isEmpty(){
     
      if(ship == null && marked == false){
        
            return true;
        }
        return false;  
    }

    public int getRow(){
    
        return row;
    }
    
    public void setRow(int row){
    
        this.row = row;
    }
   
    public int getCol(){
        
        return col;
    }
    public void setCol(int col){
        
        this.col = col;
    }
    
}
