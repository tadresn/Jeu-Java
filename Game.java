/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game. 
 * It allows you to create the game and intialize its internal card as well as its interface.
 */
 public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;
    
    /**
     * Crée le jeu et initialise sa carte interne. 
     * Crée l'interface et crée un lien vers celle-ci.
     */
    public Game()
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface(this.aEngine);
        this.aEngine.setGUI(this.aGui);
    }
 
}// Game
