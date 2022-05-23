
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game. 
 * This class is used to initialize items and manage everything related to items.
 */
public class Item
{
  private String aNom;
  private double aPoids;
  
  /**
   * Constructeur de la classe Item
   * @param String le nom, c'est-à-dire la description de l'item
   * @param double le poids de l'item
   */
  public Item(final String pNom, final double pPoids)
  {
      this.aNom = pNom;
      this.aPoids = pPoids;
  }
  
  /**
   * Accesseur qui permet d'accéder au nom de l'item.
   * @return le nom de l'item
   */
  public String getNom()
  {
      return this.aNom;
  }
  
  /**
   * Accesseur qui permet d'accéder au poids de l'item.
   * @return le poids de l'item 
   */
  public double getPoids()
  {
      return this.aPoids;
  }
  
}
