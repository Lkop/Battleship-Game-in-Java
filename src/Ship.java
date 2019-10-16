package battleship;

/**
 *
 * @author Lkop
 */
public abstract class Ship {
    
    private final String letter;
    private final int length;
    private final int points;
    
    private Location start;
    private ShipDirection dir;
    
    private boolean status=true;
    private boolean threaten=false;
    
    public Ship(String letter, int points, int length)
    {
        this.letter = letter;
        this.length = length;
        this.points = points;
    }
    
    public Ship(Location start, ShipDirection dir, int length)
    {
        this.letter = "";
        this.length = length;
        this.points = 0;
        this.start = start;
        this.dir = dir;
    }
    
    public String getLetter()
    {
        return letter;
    }
    
    public String getFullName()
    {
        if(letter.equals("A")){
            return "AircraftCarrier";
        }else if(letter.equals("D")){
            return "Destroyer";
        }else
            return "Submarine";
    }
    
    public int getLength()
    {
        return length;
    }
    
    public int getPoints()
    {
        return points;
    }
    
    public Location getStart()
    {
        return start;
    }
    
    public boolean getStatus()
    {
        return status;
    }

    public void setStart(Location start)
    {
        this.start = start;
    }
    
    public ShipDirection getDirection()
    {
        return dir;
    }
    
    public void setDirection(ShipDirection dir)
    {
        this.dir = dir;
    }
    
    public void setStatus(boolean status)
    {
        this.status = status;
    }
    
    public void setThreaten(boolean threaten)
    {
        this.threaten = threaten;
    }
    
    public boolean isSinking()
    {
        return !status;
    }
    
    public void hit(){
    
        System.out.println(getHitMessage());
    }

    public boolean isHit(Field f){
    
        boolean flag = false;

        //Συντεταγμενες πινακα
        int r = start.getRow();
        int c = start.getCol();

        if (dir.equals(ShipDirection.HORIZONTAL)) // Hortizontal
        {    
            for (int i = c; i < c+length; i++)
            {
                if(f.getLocation(r,i).isMarked())
                    flag = true;
            }                
        }
        else if (dir.equals(ShipDirection.VERTICAL)) // Vertical
        {  
            for (int i = r; i < r+length; i++)
            {  
                if(f.getLocation(i,c).isMarked())
                    flag = true;
            }
        }
        return flag;
    }
    
    public String getHitMessage(){
    
        return "Πέτυχε ένα πλοίο";
    }
    
    public String getSinkMessage(){
    
        return "Βύθισε ένα " + getFullName();
    }
    
    public boolean threaten(Field f){

        int r = this.getStart().getRow();
        int c = this.getStart().getCol();

        boolean flag = false; //υποθετουμε οτι το πλοιο δεν απειλειται

        if (dir.equals(ShipDirection.HORIZONTAL)) // Hortizontal
        {    
            for (int i = c; i < c+length; i++)
            {
                for(int j=1; j<=4; j++)
                { 
                    //πανω
                    int new_r = r-j;
                    int new_c = i;
                    
                    if(new_r>=0 && new_r<f.getRows() && new_c>=0 && new_c<f.getCols()){
                        if(f.getLocation(new_r, new_c).isMarked() == true)
                            flag = true;
                    }
                    //κατω
                    new_r = r+j;
                    new_c = i;
                    
                    if(new_r>=0 && new_r<f.getRows() && new_c>=0 && new_c<f.getCols()){
                        if(f.getLocation(new_r, new_c).isMarked() == true)
                            flag = true;
                    }
                    //δεξια
                    new_r = r;
                    new_c = i+j;
                    
                    if(new_r>=0 && new_r<f.getRows() && new_c>=0 && new_c<f.getCols()){
                        if(f.getLocation(new_r, new_c).isMarked() == true)
                            flag = true;
                    }
                    //αριστερα
                    new_r = r;
                    new_c = i-j;
                    
                    if(new_r>=0 && new_r<f.getRows() && new_c>=0 && new_c<f.getCols()){
                        if(f.getLocation(new_r, new_c).isMarked() == true)
                            flag = true;
                    }
                }
                
            }
        }
        else if (dir.equals(ShipDirection.VERTICAL)) // Vertical
        {
            for (int i = r; i < r+length; i++)
            {  
                for(int j=1; j<=4; j++)
                {
                    //πανω
                    int new_r = i-j;
                    int new_c = c;

                    if(new_r>=0 && new_r<f.getRows() && new_c>=0 && new_c<f.getCols()){
                        if(f.getLocation(new_r, new_c).isMarked() == true)
                            flag = true;
                    }
                    //κατω
                    new_r = i+j;
                    new_c = c;

                    if(new_r>=0 && new_r<f.getRows() && new_c>=0 && new_c<f.getCols()){
                        if(f.getLocation(new_r, new_c).isMarked() == true)
                            flag = true;
                    }
                    //δεξια
                    new_r = i;
                    new_c = c+j;

                    if(new_r>=0 && new_r<f.getRows() && new_c>=0 && new_c<f.getCols()){
                        if(f.getLocation(new_r, new_c).isMarked() == true)
                            flag = true;
                    }
                    //αριστερα
                    new_r = i;
                    new_c = c-j;

                    if(new_r>=0 && new_r<f.getRows() && new_c>=0 && new_c<f.getCols()){
                        if(f.getLocation(new_r, new_c).isMarked() == true)
                            flag = true;
                    }
                }
            }
        } 
        return flag;
    }

}
