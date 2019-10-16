package battleship;

import java.util.Random;

/**
 *
 * @author Lkop
 */
public class Field {
    
    private final int rows;
    private final int cols;
    
    private final Location table[][];
    
    private final Player player, op_player;
    
    
    public Field(int rows, int cols, Player player, Player op_player){
    
        this.rows = rows;
        this.cols = cols;
        this.player = player;
        this.op_player = op_player;
        
        table = new Location[rows][cols];
        
        for(int i=0; i<this.rows; i++){

            for(int j=0; j<this.cols; j++){

                table[i][j] = new Location(i, j);
            }
        }  
    }
    
    
    //print table final
    public void toStringNoShips(){
        
        System.out.print("     ");
        
        for(int j=1; j<=cols; j++){
        
            System.out.print(j);
            
            if(j<9)
                System.out.print("  ");
            else
                System.out.print(" ");
        }
        System.out.println();
        
        char ch = 'A';
        
        for(int i=0; i<rows; i++){
                    
            System.out.print(ch + " | ");
        
            for(int j=0; j<cols; j++){

                if(table[i][j].isShip()==false){
                    
                    if(table[i][j].isMarked()==true){
                    
                        System.out.print(" o");
                    }else{
                    
                        System.out.print(" .");
                    }
                    
                }else{  //ean uparxei karavi
                    
                    if(table[i][j].isMarked()==true){
                    
                        if(table[i][j].getShip().isSinking()==true){
                            System.out.print("x" + table[i][j].getShip().getLetter());
                        }else{
                            System.out.print(" x");
                        }
                    }else{
                    
                        System.out.print(" .");
                    }
                }

                System.out.print(" ");
            }
            
            System.out.println();
            ch++;
        }
        System.out.println();
    }
    
    //print table final
    public void toStringWithShips(){
        
        System.out.print("     ");
        
        for(int j=1; j<=cols; j++){
        
            System.out.print(j);
            
            if(j<9)
                System.out.print("  ");
            else
                System.out.print(" ");
        }
        System.out.println();
        
        char ch = 'A';
        
        for(int i=0; i<rows; i++){
                    
            System.out.print(ch + " | ");
        
            for(int j=0; j<cols; j++){

                if(table[i][j].isShip()==false){
                    
                    if(table[i][j].isMarked()==true){
                    
                        System.out.print(" o");
                    }else{
                    
                        System.out.print(" .");
                    }
                    
                }else{  //ean uparxei karavi
                    
                    if(table[i][j].isMarked()==true){
                    
                        if(table[i][j].getShip().isSinking()==true){
                            System.out.print("x" + table[i][j].getShip().getLetter());
                        }else{
                            System.out.print(" x");
                        }
                    }else{
                    
                        System.out.print(" " + table[i][j].getShip().getLetter());
                    }
                }

                System.out.print(" ");
            }
            
            System.out.println();
            ch++;
        }
        System.out.println();
    }
    
    public boolean placeShip(Ship s, boolean checkMarked){
            
        Location start = s.getStart();
        int row = start.getRow();
        int col = start.getCol();
        int length = s.getLength();
        ShipDirection dir = s.getDirection();
        
        if(row < 0 || row > rows-1)
            return false;

        if(col < 0 || col > cols-1)
            return false;

        if (dir.equals(ShipDirection.HORIZONTAL)) // Hortizontal
        {    
            if(col+length > cols-1)
                return false;
            
            for (int i = col; i < col+length; i++)
            {
                if(table[row][i].isEmpty()==false)
                    return false;
            }

            for (int i = col; i < col+length; i++)
            {
                table[row][i].setShip(s);
            }
        }
        else if (dir.equals(ShipDirection.VERTICAL)) // Vertical
        {
            if(row+length > rows-1)
                return false;
            
            for (int i = row; i < row+length; i++)
            {  
                if(table[i][col].isEmpty()==false)
                    return false;
            }
            for (int i = row; i < row+length; i++)
            {  
                table[i][col].setShip(s);
            }
        }
        return true;
    }
    
    public boolean placeShipRandomly(Ship s, int maxTries, boolean checkMarked){
    
        Random random = new Random();
        
        int c, r, tries=0;
        boolean flag=true;
        
        while(true){
            
            if(maxTries != 0 && tries >= maxTries )
                break;
                
            flag = true;
            
            //Τυχαία επιλογή
            r = random.nextInt(rows);
            c = random.nextInt(cols);

            ShipDirection dir = ShipDirection.values()[random.nextInt(ShipDirection.values().length)];
            
            int length = s.getLength();

            if (dir.equals(ShipDirection.HORIZONTAL)) // Hortizontal
            {    
                if(c+length > cols-1){
                    flag = false;
                }
                
                if(flag!=false){
                    
                    for (int i = c; i < c+length; i++)
                    {
                        if(table[r][i].isEmpty()==false)
                            flag = false;
                    }

                    if(flag!=false){
                        
                        for (int i = c; i < c+length; i++)
                        {
                            table[r][i].setShip(s);
                        }
                    }
                }
            }
            else if (dir.equals(ShipDirection.VERTICAL)) // Vertical
            {
                if(r+length > rows-1){
                    flag = false;
                }

                if(flag!=false){
                    
                    for (int i = r; i < r+length; i++)
                    {  
                        if(table[i][c].isEmpty()==false)
                            flag = false;
                    }
                    
                    if(flag!=false){
                        
                       for (int i = r; i < r+length; i++)
                       {  
                           table[i][c].setShip(s);
                       }
                    }
                }
            }
            
            if(flag == true){
                s.setStart(table[r][c]);
                s.setDirection(dir);
            
                break;
            }
            tries++;
        }
        return flag;
    }

    public void removeShip(Ship s){
    
        Location start = s.getStart();
        int row = start.getRow();
        int col = start.getCol();
        int length = s.getLength();
        ShipDirection dir = s.getDirection();
        
        if (dir.equals(ShipDirection.HORIZONTAL)){    // Hortizontal
            
            for (int i = col; i < col+length; i++){
                
                table[row][i].setShip(null);
            }
        }else if (dir.equals(ShipDirection.VERTICAL)){  // Vertical
            
            for (int i = row; i < row+length; i++){  
                
                table[i][col].setShip(null);
            }
        } 
    }
    
    public void processValidMove(Location moveLoc){
    
        if (moveLoc.isEmpty()==true){
            
            moveLoc.mark();
            System.out.println(op_player.getName()+": Αστόχησε");
        }
        
        if (moveLoc.isShip()==true){
        
            moveLoc.mark();
            
            int r = moveLoc.getShip().getStart().getRow();
            int c = moveLoc.getShip().getStart().getCol();
            int length = moveLoc.getShip().getLength();
            ShipDirection dir = moveLoc.getShip().getDirection();

            boolean flag = false; //υποθετουμε βυθισμενο το καραβι
            
            if (dir.equals(ShipDirection.HORIZONTAL)) // Hortizontal
            {    
                for (int i = c; i < c+length; i++)
                {
                    if(table[r][i].isMarked()==false)
                        flag = true;
                }
            }
            else if (dir.equals(ShipDirection.VERTICAL)) // Vertical
            {
                for (int i = r; i < r+length; i++)
                {  
                    if(table[i][c].isMarked()==false)
                        flag = true;
                }
            }
            
            System.out.print(op_player.getName() + ": ");
            
            moveLoc.getShip().hit();          
            
            if (flag == false){
                moveLoc.getShip().setStatus(false);
                op_player.setScore(moveLoc.getShip().getPoints());
                System.out.println(op_player.getName() + ": " + moveLoc.getShip().getSinkMessage());
            }            
        }
        
        for(int i=0; i<7; i++){
            boolean apeil = player.returnShips(i).threaten(this);
            
            if(apeil==true)
                player.returnShips(i).setThreaten(true);
            
            if(i>=2 && i<=4 && apeil == true && player.returnShips(i).isHit(this)==false){
                
                Ship tmp_ship = new Destroyer(player.returnShips(i).getStart(),player.returnShips(i).getDirection(),player.returnShips(i).getLength());

                if(placeShipRandomly(player.returnShips(i), 1, false)==true)
                    removeShip(tmp_ship);
            }
            
            if(i>=5 && apeil == true && player.returnShips(i).isHit(this)==false){
                removeShip(player.returnShips(i));
                placeShipRandomly(player.returnShips(i), 0, false);  
            }
        }
    }

    public Location getLocation(int r, int c){

        return table[r][c];
    }
    
    public Location getLocation(String locString) throws InvalidLocationException{
    
        int r, c; //row, column
        
        r = Character.getNumericValue(locString.charAt(0)) - Character.getNumericValue('A');
        
        if(locString.length()==2){
            c = locString.charAt(1) - '1';   
            
        }else if(locString.length()==3){
            c = (locString.charAt(1) - '0')*10 + (locString.charAt(2) - '1');
            
        }else{
            c = -1;
        }
        
        if(r < 0 || r > op_player.playerField().getRows()-1){
            throw new InvalidLocationException("Row out of bounds");
        }
        
        if(c < 0 || c > op_player.playerField().getCols()-1){
            throw new InvalidLocationException("Column out of bounds");
        }
        
        return table[r][c];
    }

    public int getRows(){
    
        return rows;
    }
    
    public int getCols(){
    
        return cols;
    }
}
