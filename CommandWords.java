 /**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2019.09.25
 */
public class CommandWords
{
    // a constant array that will hold all valid command words
    private static final String[] VALIDCOMMANDS = {"go","quit","help","look","take","back","test", "drop", "inventaire", "eat", "charge", "fire"};

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // rien
    }
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand( final String pString )
    {
        for ( int i=0; i<VALIDCOMMANDS.length; i++ ) {
            if ( VALIDCOMMANDS[i].equals( pString ) )
                return true;
        } // for
        // if we get here, the string was not found in the commands
        return false;
    } // isCommand()
    
    /**
     * Retourne toutes les commandes valides
     * @return les commandes valides
     */
    public String getCommandList()
    {
        String vS = " ";
        for (String pCommand : VALIDCOMMANDS){
            vS = vS + pCommand + " " ;
        }
        return vS;
    }
        
} // CommandWords
