  /**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * This class is used to initialize commands and manage everything related to commands.
 */
 public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     * Constructeur naturel
     * @param pCommandWord String pour initialiser aCommandWord
     * @param pSecondWord String pour initialiser aSecondWord
     */
    public Command(final String pCommandWord, final String pSecondWord)
    {
       this.aCommandWord = pCommandWord;
       this.aSecondWord = pSecondWord;
    }
    
    /**
     * Accesseur pour aCommandWord
     * @return le premier mot
     */
    public String getCommandWord()
    {
       return this.aCommandWord;
    }
    
    /**
     * Accesseur pour aSecondWord
     * @return le second mot
     */
    public String getSecondWord()
    {
        return this.aSecondWord;
    }
    
    /**
     * Permet de vérifier qu'un second mot a bien été tapé
     * @return true si il y'a un second mot sinon elle renvoie false
     */
    public boolean hasSecondWord()
    {
        return this.aSecondWord != null;
    }
    
    /**
     * Permet de vérifier si le premier mot est null
     * @return true si le premier mot est null sinon elle renvoie false
     */
    public boolean isUnknown()
    {
        return this.aCommandWord == null;
    }
} // Command
