package battleship;

import java.util.InputMismatchException;

/**
 *
 * @author Lkop
 */
public enum Command {
    
    // Οι εντολές, με κλήση του constructor για απόδοση των τιμών των πεδίων
    HELP("help", "\nΕντολές: save , load , exit \nΗ επιλογή θέσης γίνεται με τη μορφή B7(Κεφαλαία)\n"),
    SAVE("save", "\nsave : Αποθηκεύει την τρέχουσα κατάσταση του παιχνιδιού σε ένα αρχείο .txt\n"),
    LOAD("load", "\nload αρχείο.txt : Φορτώνει το παιχνίδι από το αρχείο αρχείο.txt\n"),
    EXIT("exit", "\nexit : τερματισμός του παιχνιδιού");

    // Πεδία
    private String commandString;
    private String helpText;

    // Constructor
    private Command(String cmd, String help){
        commandString = cmd;
        helpText = help;
    }
    
    public static Command fromString(String cs)
    {
       if(cs.equals("help") || cs.equals("HELP"))
            return HELP;
       else if(cs.equals("save") || cs.equals("SAVE"))
           return SAVE;
       else if(cs.equals("load") || cs.equals("LOAD"))
           return LOAD;
       else if(cs.equals("exit") || cs.equals("EXIT"))
           return EXIT;
       else
           throw new InputMismatchException();
    }
    
    public String getCommandString()
    {
        return commandString;
    }

    public String getHelpText()
    {
        return helpText;
    }
}
