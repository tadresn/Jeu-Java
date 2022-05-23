
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game. 
 * This class allows to create a beamer which is a kind of item.
 */
public class Beamer extends Item
{
   private boolean aCharged;
   private Room aRoomEnregistre;
   
   /**
    * Constructeur de la classe Beamer
    * @param String nom du beamer
    * @param double poids du beamer
    * @param Room salle enregistré du beamer
    */
   public Beamer(final String pName,final double pPoids,final Room pRoomEnregistre)
   {
       super(pName, pPoids);
       this.aCharged = false;
       this.aRoomEnregistre = pRoomEnregistre;
   }
   
   /**
    * Accesseur de l'attribut aCharged
    * @return boolean aCharge
    */
   public boolean getCharged()
   {
       return this.aCharged;
   }
   
   /**
    * Accesseur de l'attribut aRoomEnregistre
    * @return Room aRoomEnregistre
    */
   public Room getRoomEnregistre()
   {
       return this.aRoomEnregistre;
   }
   
   /**
    * Modificateur de l'attribut aCharged
    * @param boolean que va prendre l'attribut
    */
   public void setCharged(final boolean pBoolean)
   {
       this.aCharged = pBoolean;
   }
   
   /**
    * Procédure qui permet de charger le beamer
    * @param Room que l'on veut enregistrer dans le beamer
    */
   public void chargeBeamer(final Room pRoomEnregistre)
   {
       this.aCharged = true;
       this.aRoomEnregistre = pRoomEnregistre;
   }
   
}
