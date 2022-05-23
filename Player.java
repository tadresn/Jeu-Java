import java.util.Stack;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * This class is used to initialize players and manage everything related to players.
 */
public class Player
{
   private Room aCurrentRoom;
   private String aName;
   private double aPoids;
   private double aPoidsMax;
   private Stack<Room> aPrecedRooms;
   private ItemList aItemsPlayer;
   
   /**
    * Constructeur qui initialise les attributs de la classe Player
    * @param Room de départ
    * @param String nom du joueur
    */
   public Player(final Room pRoom, final String pName)
   {
       this.aCurrentRoom = pRoom;
       this.aName = pName;
       this.aPoids = 0;
       this.aPoidsMax = 7; 
       this.aPrecedRooms = new Stack<Room>();
       this.aItemsPlayer = new ItemList();
   }
   
   /**
    * Accesseur qui permet d"accéder à aCurrentRoom
    * @return la room courante
    */
   public Room getCurrentRoom()
   {
       return this.aCurrentRoom;
   }
   
   /**
    * Modificateur de aCurrentRoom
    * @param Room valeur que prend aCurrentRoom 
    */
   public void setCurrentRoom(final Room pRoom)
   {
       this.aCurrentRoom = pRoom;
   }
   
   /**
    * Accesseur qui permet d'accéder au nom
    * @return le nom du player
    */
   
   public String getName()
   {
       return this.aName;
   }
   
   /**
    * Accesseur qui permet d'accéder au poids actuelle du joueur 
    * @return le poids que porte le player
    */
   public double getPoids()
   {
       return this.aPoids;
   }
   
   /**
    * Accesseur qui permet d'accéder au poids maximum que le joueur peut porter
    * @return le poids maximum que peut porter le player
    */
   public double getPoidsMax()
   {
       return this.aPoidsMax;
   }
   
   /**
    * Modificateur de aPoidsMax
    * @param double nouvelle valeur que prend aPoidsMax
    */
   public void setPoidsMax(final double pPoidsMax)
   {
       this.aPoidsMax = pPoidsMax;
   }
   
   /**
    * Fonction qui renvoie et supprime le dernier élément de la pile aPrecedRooms
    * @return le dernier élément de la pile
    */
   public Room getPrecedRoom()
   {
       return this.aPrecedRooms.pop();
   }
   
   /**
    * Procédure qui ajoute une room dans la pile aPrecedRooms
    * @param Room que l'on veut ajouter
    */
   public void ajoutePrecedRooms(final Room pPrecedRoom)
   {
       this.aPrecedRooms.push(pPrecedRoom);
   }
   
   /**
    * Fonction qui vérifie si la pile aPrecedRooms est vide ou pas
    * @return true si la pile est vide false sinon
    */
   public boolean precedRoomsEmpty()
   {
       return this.aPrecedRooms.empty();
   }
   
   /**
    * Fonction qui permet de récuperer l'item portant le nom passé en paramètre
    * @param String de l'item que l'on souhaite récupérer
    * @return l'item correspondant au nom passer en paramètre
    */
   public Item getItemPlayer(final String pNom)
   {
       return this.aItemsPlayer.getItemList(pNom);
   }
   
   /**
    * Procédure qui vérifie si le joueur peut prendre un item passé en paramètre
    * @return vrai si oui, false sinon
    */
   public boolean canTake(final Item pItem)
   {
       return this.aPoids + pItem.getPoids() <= this.aPoidsMax;
   }
   
   /**
    * Procédure qui permet de ramasser l'item passé en paramètre
    * @param Item que l'on souhaite ramasser
    */
   public void takeItem(final Item pItem)
   {
       this.aItemsPlayer.putItemList(pItem);
       this.aPoids = this.aPoids + pItem.getPoids();
   }
   
   /**
    * Procédure qui permet de jeter l'item passé en paramètre
    * @param Item que l'on souhaite jeter
    */
   public void dropItem(final Item pItem)
   {
       this.aItemsPlayer.removeItemList(pItem);
       this.aPoids = this.aPoids - pItem.getPoids();
   }
    
   /**
    * Fonction qui retourne tous les items que posséde le joueur
    * @return une String des items que posséde le joueur
    */
   public String getItemPlayerString()
   {
       return this.aItemsPlayer.getItemListString();
   }
   
   /**
    * Fonction qui dit si la ItemList du joueur est vide
    * @return true si c'est vide false sinon
    */
   public boolean itemIsEmpty()
   {
       return this.aItemsPlayer.itemListIsEmpty();
   }
   
   /**
    * Fonction qui renvoie l'élément de tête de la pile sans la supprimer
    * @retunr l'élément de tête de la pile
    */
   public Room peekPrecedRooms()
   {
       return this.aPrecedRooms.peek();
   }
   
   /**
    * Procédure qui supprime tout les éléments de la pile
    */
   public void clearPrecedRooms()
   {
       this.aPrecedRooms.clear();
   }
}
