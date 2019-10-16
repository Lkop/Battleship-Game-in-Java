package battleship;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Lkop
 */
public class Game {

    private static Scanner input = new Scanner(System.in);
    
    //Παίκτες δεν ξέρουμε αν θα είναι Human ή Computer
    private Player[] p = new Player[2];
       
    //Παίκτης που αρχίζει πρώτος
     private int start_player;
    
    // Το πλήθος γύρων που θα παιχτούν
    private int rounds;
    
    
   
    
    //Αρχικοποίηση
    public void init(){
    
        System.out.print("Δώστε όνομα 1ου παίκτη, για να παίξει ο υπολογιστής γράψτε 'CPU': ");
        String name_1 = input.nextLine();
        
        System.out.print("Δώστε όνομα 2ου παίκτη, για να παίξει ο υπολογιστής γράψτε 'CPU': ");
        String name_2 = input.nextLine();
       
        int rows, cols;
        do{
            System.out.print("Δώστε γραμμές (10-15) : ");
            rows = input.nextInt();
        }while(rows<10 || rows>15);
        
        do{
            System.out.print("Δώστε στήλες (10-15) : ");
            cols = input.nextInt();
        }while(cols<10 || cols>15);
        
        do{
            System.out.print("Δώστε γύρους: ");
            rounds = input.nextInt();
        }while(rounds <= 0);
        
        System.out.println();
        
        //Τυχαία επιλογή παίκτη που αρχίζει
        Random rand = new Random();
        start_player = rand.nextInt(2);
        
        
        if (name_1.equals("CPU") || name_1.equals("cpu")){
        
            p[0] = new ComputerPlayer(); 
        }else{
        
            p[0] = new HumanPlayer(name_1);
        }
        
        if (name_2.equals("CPU") || name_2.equals("cpu")){
        
           p[1] = new ComputerPlayer();
        }else{
        
           p[1] = new HumanPlayer(name_2);
        }
        

        p[0].initField(rows, cols, p[1], p[0]);
        p[1].initField(rows, cols, p[0], p[1]);
        
        placeShips();
        
        System.out.println("Το ταμπλό που παίζει ο " + p[0].getName() + " : (Για λόγους debugging)");
        p[0].playerField().toStringWithShips();
        System.out.println("Το ταμπλό που παίζει ο " + p[1].getName() + " : (Για λόγους debugging)");
        p[1].playerField().toStringWithShips();
         

    }

    public void placeShips(){
    
        p[0].placeShips(p[1].playerField());
        System.out.println(p[0].getName() + " βλέπει: ");
        p[0].playerField().toStringNoShips();
        p[1].placeShips(p[0].playerField());
        System.out.println(p[1].getName() + " βλέπει: ");
        p[1].playerField().toStringNoShips();
    }
    
    //Κυρίως παιχνίδι
    public void play()
    {
        for (int i = 0; i < rounds; i++)  {
            System.out.println((i+1) + "ος γύρος.");
            playRound();
            //p[0].playerField().toStringWithShips();
            //p[1].playerField().toStringWithShips();
            
            if(p[0].hasWon() == true || p[1].hasWon() == true)
                return;
        }
    }
    
    private void playRound()
    {        
        int i = start_player;
        
        for(int j=0; j<2; j++){

            try{
                Location move = p[j].selectMove();
                
                if(move.isMarked()==true)
                    j--;
                else{
                    p[j].playerField().processValidMove(move);
                    p[j].playerField().toStringNoShips();
                    
                    if(p[j].hasWon() == true)
                        return;
                }
            }catch(MoveIsCommandException e){
               
                if(e.getCommand().equals(Command.EXIT)){
                
                    System.out.println("Θέλετε σίγουρα να κάνετε έξοδο? y->(ΝΑΙ) n->(ΟΧΙ) : ");
                    String yes_no;
                    
                    do{
                        yes_no = input.nextLine();
                    }while(yes_no.equals("y")==false && yes_no.equals("n")==false);
                            
                    if(yes_no.equals("y"))
                        System.exit(0);
                }
                
                if(e.getCommand().equals(Command.HELP))
                    System.out.println(e.getCommand().getHelpText());
                    
                j--;
            }catch(InvalidLocationException e){
                System.out.println(e.getMessage());
                j--;
            }
           
        }
    }
    
    //Τέλος του παιχνιδιού - Προβολή αποτελεσμάτων
    public void showResult(){
        
        if (p[0].getScore() > p[1].getScore()) {
            System.out.println("Νικητής είναι ο παίκτης " + p[0].getName() + " με σκορ " + p[0].getScore() + "-" + p[1].getScore() + "\n");
        }
        else if (p[1].getScore() > p[0].getScore()) {
            System.out.println("Νικητής είναι ο παίκτης " + p[1].getName() + " με σκορ " + p[1].getScore() + "-" + p[0].getScore() + "\n");
        }
        else {
            System.out.println("Ισοπαλία!\n");
        }
        
        //εκτυπώνουμε τα ταμλπο με τα πλοια
        System.out.println("Το ταμπλό που έπαιζε " + p[0].getName() + ":\n");
        p[0].playerField().toStringWithShips();
        System.out.println("Το ταμπλό που έπαιζε " + p[1].getName() + ":\n");
        p[1].playerField().toStringWithShips();
        
    }
}
