package battleship;

import java.util.Scanner;

/**
 *
 * @author Lkop
 */
public class HumanPlayer extends Player{
    
    public HumanPlayer(String name){
    
        super(name);
    }
    
    @Override
    public Location selectMove() throws InvalidLocationException, MoveIsCommandException{
    
        System.out.print("Επιλογή του παίκτη " + getName() + ": ");
        
        Scanner input = new Scanner(System.in);
        String position = input.nextLine();

        if(position.length()>3){
            Command com = Command.fromString(position);
            throw new MoveIsCommandException(com);
        }

        return playerField().getLocation(position);
    }
    
    @Override
    public void placeShips(Field otherField){
    
        Scanner input = new Scanner(System.in);
        
        //φτιαχνουμε τα odj Ship(submarine destroyer aircraftcarrier)
        initShips();
        
        System.out.print("Θέλετε να τοποθετηθούν όλα τα πλοία αυτόματα σε τυχαίες θέσεις? y ->(ΝΑΙ) n ->(ΟΧΙ): ");
        String yes_no = input.nextLine();
        
        System.out.println();
        
        for(int i=0; i<7; i++){

            if(yes_no.equals("y")){
                
                otherField.placeShipRandomly(returnShips(i), 0, true);
            }else{
            
                System.out.print("Δώσε την πλώρη απο το "+returnShips(i).getFullName()+" : ");
                String position = input.nextLine();


                int r, c; //row, column
                
                if(position.charAt(0)=='A')
                    r = Character.getNumericValue(position.charAt(0)) - Character.getNumericValue('A');
                else
                    r = Character.getNumericValue(position.charAt(0)) - Character.getNumericValue('a');

                if(position.length()==2){
                    c = position.charAt(1) - '1';   

                }else if(position.length()==3){
                    c = (position.charAt(1) - '0')*10 + (position.charAt(2) - '1');  
                }else{
                    c = -1;
                }

                returnShips(i).setStart(otherField.getLocation(r, c));


                System.out.print("Δώσε την κατεύθυνση (v) για κάθετα ή (h) για οριζόντια του "+returnShips(i).getFullName()+" : ");
                String dir = input.nextLine();


                returnShips(i).setDirection(ShipDirection.fromString(dir));


                //ελεχοσ εαν τη θεση την εχει επιλεξει ο αντιπαλος
                boolean is_marked = true;

                try{
                    is_marked = otherField.getLocation(position).isMarked();
                }catch(InvalidLocationException e){
                    System.out.println(e.getMessage());
                    i--;
                    continue;
                }


                if(otherField.placeShip(returnShips(i), is_marked)==false){
                    i--;
                    System.out.println("Μη διαθέσιμη θέση.");
                }
            }
        }
    }
}
