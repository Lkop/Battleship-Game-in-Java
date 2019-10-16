package battleship;

import java.util.InputMismatchException;

/**
 *
 * @author Lkop
 */
public enum ShipDirection {

    HORIZONTAL,VERTICAL;
    
    public static ShipDirection fromString(String dirString){
      
        if("h".equals(dirString)){
            return HORIZONTAL;  
        }
        else if("v".equals(dirString)){
            return VERTICAL;
        }  
        else{
            throw new InputMismatchException();
        }
    }
}