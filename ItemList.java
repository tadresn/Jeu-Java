import java.util.HashMap;
import java.util.Set;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * This class is used to manage lists of items.
 */
public class ItemList
{
    private HashMap<String, Item> aItemList;
    
    /**
     * Constructeur de la classe ItemList
     */
    public ItemList()
    {
        this.aItemList = new HashMap<String, Item>();
    }
    
    /**
     * Fonction qui retourne l'item portant le nom passé en paramètre
     * @param String de l'item que l'on souhaite récupérer
     * @return l'item correspondant au nom passer en paramètre
     */
    public Item getItemList(final String pNom)
    {
        return this.aItemList.get(pNom);
    }
    
    /**
     * Procédure qui permet d'ajouter l'item passé en paramètre
     * @param Item que l'on souhaite ajouter dans la ItemList
     */
    public void putItemList(final Item pItem)
    {
        this.aItemList.put(pItem.getNom(), pItem);
    }
    
    /**
    * Procédure qui permet de retirer l'item passé en paramètre
    * @param Item que l'on souhaite retirer de la ItemList
    */
    public void removeItemList(final Item pItem)
    {
        this.aItemList.remove(pItem.getNom());
    }
    
    
     /**
     * Fontion qui retourne les items sous forme de String
     * @return les items présent dans ItemList
     */
    public String getItemListString()
    {
        String vS = "Items :";
        Set <String> vKeys = this.aItemList.keySet();
        for (String vItem : vKeys) {
            vS += " " + vItem;
        }

        return vS;
    }
    
    /**
     * Fonction qui vérifie si l'ItemList est vide
     * @return vrai si l'ItemList est vide, faux sinon
     */
    public boolean itemListIsEmpty()
    {
        return this.aItemList.isEmpty();
    }
 
}
