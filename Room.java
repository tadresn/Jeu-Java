import java.util.HashMap;
import java.util.Set;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * This class allows you to initialize rooms and manage everything related to rooms.
 */

 public class Room
{
    private String aDescription;
    private HashMap<String, Room> aExits;
    private String aImageName;
    private ItemList aItems;
    
    /**
     * Constructeur naturel qui crée une pièce décrite par la chaîne 'pDescription' et avec une image donnée
     * @param pDescription String pour initialiser aDescription
     * @param String du lien vers l'image
     */
    public Room(final String pDescription, final String pImage)
    {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aImageName = pImage;
        this.aItems = new ItemList();
    }
    
    /**
     * Accesseur pour aDescription
     * @return la description de la pièce
     */
    public String getDescription()
    {
        return this.aDescription;
    }
    
    /**
     * Procédure qui permet d'ajouter les sorties de la room.
     * @param pDirection String la direction de la sortie
     * @param pRoom Room la salle dans la direction donnée
     */
    public void setExit(final String pDirection, final Room pRoom)
    {
        aExits.put(pDirection, pRoom);
    }
    
    /**
     * Procédure qui permet d'ajouter les items dans les rooms
     * @param pItem Item à ajouter 
     */
    public void addItem(final Item pItem)
    {
        this.aItems.putItemList(pItem);
    }
    
    /**
     * Procédure qui permet de supprimer l'item passé en paramètre
     * @param Item que l'on souhaite supprimer
     */
    public void removeItem(final Item pItem)
    {
        this.aItems.removeItemList(pItem);
    }
    
    /**
     *Fonction qui retourne l'item portant le nom passé en paramètre
     *@param String de l'item que l'on souhaite récupérer
     */
    public Item getItemRoom(final String pNom)
    {
        return this.aItems.getItemList(pNom);
    }
    
    /**
     * Accesseur permettant d'accéder à la sortie
     * @return la pièce atteinte si nous nous déplaçons dans la 
     * direction 'pDirection'
     */
    public Room getExit(final String pDirection)
    {
       return this.aExits.get(pDirection);
    }
        
    /**
     * Fonction qui retourne les sortie disponible de la CurrentRoom
     * @return les sorties disponibles
     */
    public String getExitString()
    {
    String vS = "Exits :";
    Set<String> vKeys = this.aExits.keySet();
    for (String vExit : vKeys) {
        vS += " " + vExit;
    }
    return vS;
    }
    
    /**
     * Fontion qui retourne les items disponible dans la CurrentRoom
     * @return les items disponibles
     */
    public String getItemString()
    {
        return this.aItems.getItemListString();
    }

    /**
     * Renvoie une description détaillée de cette pièce
     * @return Une description de la pièce avec les sorties et les items
     */
    public String getLongDescription()
    {
        if (this.aItems.itemListIsEmpty()) {
        return "You are " + this.aDescription + "\n" + this.getExitString()
        + "\nThere is no item here.";
       }
       else {
        return "You are " + this.aDescription + "\n" + this.getExitString()
        + "\n" + getItemString();
       }
    }
           
    /**
     * Return une string décrivant le nom de l'image de la pièce
     */
    public String getImageName()
    {
        return this.aImageName;
    }
    
    /**
     * Fonction qui renvoie vrai ou faux selon que la Room passée en paramètre est une des sorties de la pièce ou pas.
     * @return true si la Room passée en paramètre est une sortie de la pièce et false sinon
     */
    public boolean isExit(final Room pRoom)
    {
        return this.aExits.containsValue(pRoom);
    }
            
}// Room

 
