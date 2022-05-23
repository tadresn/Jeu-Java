import java.util.HashMap;
import java.util.Stack;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * This class manages everything related to the game.
 */
 public class GameEngine
{
    private Parser aParser;
    private UserInterface aGui;
    private HashMap<String, Room> aRooms; 
    private Player aPlayer;
    private int aTimeCommand;
    
    /**
     * Constructeur par défaut
     */
    public GameEngine()
    {
        this.createRooms();
        this.aParser = new Parser();
        this.aTimeCommand = 0;
    }
    
    /**
     * Procédure qui initialise l'interface et affiche le message de bienvenue
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }
    
    /**
     * Création et initialisation des salles et des sorties
     */
    private void createRooms()
    {
        Room vSouthJaw;
        Room vMarshyJaw;
        Room vNorthJaw;
        Room vDangerousForest;
        Room vRightNostril;
        Room vLeftNostril;
        Room vRightEye;
        Room vRuinsOfTheLeftEye;
        Room vLastFloor;//déclarations des 9 lieux
        
        Item vLettre1 = new Item("Letter1", 0.5);
        Item vCle = new Item("Key", 1);
        Item vPierre1 = new Item("Rock1", 1);
        Item vCouteau = new Item("Knife", 1);
        Item vPierre2 = new Item("Rock2", 1);
        Item vPierre3 = new Item("Rock3", 1);
        Item vPierre4 = new Item("Rock4", 1);
        Item vLettre2 = new Item("Letter2", 0.5);
        Item vStele = new Item("Stele", 10);
        Item vCloche = new Item("Bell", 10);
        Item vMagicCookie = new Item("magicCookie", 0.5);
        //déclaration et initialisation des 11 items 
        
        vSouthJaw = new Room("in the South Jaw", "Images/SouthJaw.gif");
        vMarshyJaw = new Room("in the Marshy Jaw", "Images/MarshyJaw.gif");
        vNorthJaw = new Room("in the North Jaw", "Images/NorthJaw.gif");
        vDangerousForest = new Room("in the Dangerous Forest", "Images/DangerousForest.gif");
        vRightNostril = new Room("in the Right Nostril", "Images/RightNostril.gif");
        vLeftNostril = new Room("in the Left Nostril", "Images/LeftNostril.gif");
        vRightEye = new Room("in the Right Eye", "Images/RightEye.gif");
        vRuinsOfTheLeftEye = new Room("in the ruins of the Left Eye", "Images/RuinsoftheLeftEye.gif");
        vLastFloor= new Room("in the last floor of the lighthouse", "Images/LastFloorofthelighthouse.gif");
        //initialisations des 9 lieux
        
        vSouthJaw.setExit("north", vMarshyJaw);
        vSouthJaw.setExit("up",vLastFloor);
        vMarshyJaw.setExit("north", vNorthJaw);
        vMarshyJaw.setExit("south", vSouthJaw);
        vNorthJaw.setExit("north", vDangerousForest);
        vNorthJaw.setExit("south", vMarshyJaw);
        vDangerousForest.setExit("north", vRightNostril);
        vDangerousForest.setExit("south", vNorthJaw);
        
        vRightNostril.setExit("east", vLeftNostril);
        vRightNostril.setExit("south", vDangerousForest);
        vLeftNostril.setExit("north", vRuinsOfTheLeftEye);
        vLeftNostril.setExit("west", vRightNostril);
        vRightEye.setExit("east", vRuinsOfTheLeftEye);
        vRightEye.setExit("south", vRightNostril);
        vRuinsOfTheLeftEye.setExit("south", vLeftNostril);
        vRuinsOfTheLeftEye.setExit("west", vRightEye);
        vLastFloor.setExit("down",vSouthJaw);
        //positionnement des sorties de chaque lieu
        
        Beamer vBeamer = new Beamer("Beamer",1,vSouthJaw); //déclaration et initialisation du beamer
        
        vSouthJaw.addItem(vLettre1);
        vLastFloor.addItem(vCle);
        vNorthJaw.addItem(vCouteau);
        vNorthJaw.addItem(vPierre1);
        vRightNostril.addItem(vPierre2);
        vLeftNostril.addItem(vPierre3);
        vRuinsOfTheLeftEye.addItem(vPierre4);
        vRuinsOfTheLeftEye.addItem(vLettre2);
        vRightEye.addItem(vStele);
        vRightEye.addItem(vCloche); 
        vLastFloor.addItem(vMagicCookie);//ajout des items dans les salles
        
        vLastFloor.addItem(vBeamer);//ajout du beamer
        
        aRooms = new HashMap<String, Room>();
        
        aRooms.put("in the South Jaw", vSouthJaw);
        aRooms.put("in the Marshy Jaw", vMarshyJaw);
        aRooms.put("in the North Jaw", vNorthJaw);
        aRooms.put("in the Dangerous Forest", vDangerousForest);
        aRooms.put("in the Right Nostril", vRightNostril);
        aRooms.put("in the Left Nostril", vLeftNostril);
        aRooms.put("in the Right Eye", vRightEye);
        aRooms.put("in the ruins of the Left Eye", vRuinsOfTheLeftEye);
        aRooms.put("in the last floor of the lighthouse", vLastFloor);

        
        this.aPlayer = new Player(vSouthJaw, "Mugiwara"); //initialisation du player
    }
    
    /**
     * Affiche les informations liées à la situation courante
     */
    private void printLocationInfo()
    {
        this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription() );
        if ( this.aPlayer.getCurrentRoom().getImageName() != null )
            this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
    }
        
    /**
     * Procédure exécutée lorsqu'on tape la commande "go"
     * @param pCommand Command commande tapée au clavier
     */
    private void goRoom(final Command pCommand)
    {   
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Go where ?");
            return;
        } //s'il n'y a pas de second mot on affiche "Go where ?"
        
        String vDirection;
        vDirection = pCommand.getSecondWord();
        //vDirection est le second mot de pCommand
        
        Room vNextRoom = this.aPlayer.getCurrentRoom().getExit(vDirection);
        //initialisation de vNextRoom en fonction de vDirection
        if (vNextRoom == null) {
            this.aGui.println("There is no door !");
        } //on affiche "There is no door!" si il n'y a pas de prochaine Room
        else {
            this.aPlayer.ajoutePrecedRooms(this.aPlayer.getCurrentRoom());
            this.aPlayer.setCurrentRoom(vNextRoom);
            printLocationInfo();
        } 
        //sinon on change de lieu et on affiche les directions possible du nouveau lieu
        
        if (vDirection.equals("north")) {
            timeCommand();
        }
        //on incrémente le timer à chaque fois qu'on utilise la commande go north
        
        if (this.aPlayer.getCurrentRoom().getDescription().equals("in the Marshy Jaw")){
            this.aGui.getTimer().restart();
            this.aGui.println("You have 20 seconds to leave the Marshy Jaw where there is a poisoned gas !");
        }
        else {
            if (this.aGui.getTimer().isRunning()){
                this.aGui.getTimer().stop();
            }
            
        }
        //On active le Timer si le joueur est dans la salle Marshy Jaw
    }
     
     
    /**
     * Affiche le message de bienvenue
     */
    private void printWelcome()
     {
        this.aGui.print( "\n" );
        this.aGui.println( "Welcome to the World of Zuul!" );
        this.aGui.println( "World of Zuul is a new, incredibly boring adventure game." );
        this.aGui.println( "Type 'help' if you need help." );
        this.aGui.print( "\n" );
        printLocationInfo();     
     }
    
    /**
     * Affiche un message lorsqu'on tape la commande "help" 
     */
    private void printHelp()
    {
        this.aGui.println( "You are lost. You are alone. You wander" );
        this.aGui.println( "around at South Jaw." + "\n" );
        this.aGui.println( "Your command words are: " + this.aParser.getCommandString() );  
    }
    

    
    /**
     * Procédure qui appelle la bonne méthode en fonction de la commande string 
     * passer en paramètre
     * @param pCommandLine String commande tapée au clavier
     */
    public void interpretCommand(final String pCommandLine)
    {
       this.aGui.println( "> " + pCommandLine);
       Command vCommand = this.aParser.getCommand( pCommandLine );
       
        if (vCommand.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
            return;
       }
       
       String vCommandWord = vCommand.getCommandWord();
       
       if (vCommandWord.equals("help")) {
             this.printHelp();
             }
       else  if (vCommandWord.equals("go")) {
                 this.goRoom(vCommand);
              }
       else if (vCommandWord.equals("look")) {
                 this.look(vCommand);
              }
       else if (vCommandWord.equals("take")) {
                 this.take(vCommand);
                 }
       else if (vCommandWord.equals("drop")) {
                 this.drop(vCommand);
                 }
       else if (vCommandWord.equals("back")){
                this.back(vCommand);
                 }
       else if (vCommandWord.equals("test")){
           this.test(vCommand);
        }
       else if (vCommandWord.equals("inventaire")){
           this.inventaireItem(vCommand);
        }
       else if (vCommandWord.equals("eat")){
           this.eat(vCommand);
       }
       else if (vCommandWord.equals("quit")) {
                this.quit(vCommand);
                }
       else if (vCommandWord.equals("charge")){
           this.charge(vCommand);
       }
       else if (vCommandWord.equals("fire")){
           this.fire(vCommand);
       }
        
    }
    
    /**
     * Permet de quitter le jeu
     * @param Command tapée au clavier
     */
    private void quit(final Command pCommand)
    {
        if (pCommand.hasSecondWord() ) {
            this.aGui.println("Quit what ?");
        }
        else {
               this.endGame();
               this.aGui.enableButtons();
        }
    }
    
    /**
     * Procédure qui affiche le message de fin
     */
    public void endGame()
    {
        this.aGui.println("Thank you for playing. Good bye.");
        this.aGui.enable(false);
    }
    
    /**
     * Procédure qui permet de réafficher la description de la salle et ses sorties
     * @param Command tapée au clavier
     */
    private void look(final Command pCommand) 
    {
        if (pCommand.hasSecondWord()) {
            this.aGui.println("I don't know how to look at something in particular yet.");
        } 
        else {
            this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        }
    }
    
    /**
     * Procédure qui permet d'attraper un objet (et de le supprimer de la Room dans laquelle il était)
     * @param Command tapée au clavier
     */
    private void take(final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){
            this.aGui.println("take what ?");
            return;
        }
        
        String vNom = pCommand.getSecondWord();
        
        Item vItem = this.aPlayer.getCurrentRoom().getItemRoom(vNom);
        
        if (vItem==null){
            this.aGui.println("This item is not here !");
        }
        else {
            if (this.aPlayer.canTake(vItem)) {
            this.aPlayer.takeItem(vItem);
            this.aPlayer.getCurrentRoom().removeItem(vItem);
            this.aGui.println("You took the " + vItem.getNom());
            }
            else{
                this.aGui.println("Your weight limit doesn't allow you to take this item !");
            }
        }
        
    }
    
    /**
     * Procédure qui permet de jeter un objet (et de l'ajouter dans la room dans laquelle le joueur était)
     * @param Command tapée au clavier
     */
    private void drop(final Command pCommand)
    {
         if (!pCommand.hasSecondWord()){
            this.aGui.println("drop what ?");
            return;
        }
        
        String vNom = pCommand.getSecondWord();
        
        Item vItem = this.aPlayer.getItemPlayer(vNom);
        
        if (vItem == null) {
            this.aGui.println("You don't have this item !");
        }
        else {
            this.aPlayer.getCurrentRoom().addItem(vItem);
            this.aPlayer.dropItem(vItem);
            this.aGui.println("You drop the item " + vItem.getNom());
        }
        
    }
    
    /**
     * Procédure qui permet de revenir dans la salle précédente
     * @param Command tapée au clavier
     */
    private void back(final Command pCommand)
    {
        if (pCommand.hasSecondWord()){
            this.aGui.println("The back command doesn't accept a second word.");
            return;
        }
        if (this.aPlayer.precedRoomsEmpty()) {
               this.aGui.println("You are in the first room you were in. Going back is therefore not possible.");
        }
        else {
            if (this.aPlayer.getCurrentRoom().isExit(this.aPlayer.peekPrecedRooms())){
              this.aPlayer.setCurrentRoom(this.aPlayer.getPrecedRoom());
              printLocationInfo();
            }
            else {
                this.aPlayer.clearPrecedRooms();
                this.aGui.println("You have passed through a trap door you cannot go back !");
            }
                
        }
    }
    
    /**
     * Procédure qui permet de lancer des commandes présente dans un fichier
     * @param Command tapée au clavier
     */
    private void test(final Command pCommand)
    {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Test what ?");
            return;
        }
        String vNomFichier = pCommand.getSecondWord();
        Scanner vSc = null;
        try {
            vSc = new Scanner(new File("./" + vNomFichier + ".txt"));
            
            while (vSc.hasNextLine()) {
                String vCommand = vSc.nextLine();
                interpretCommand(vCommand);
            }
        }
        catch (final FileNotFoundException pFNFE) {
            this.aGui.println("File not found");
        }
        finally {
            if (vSc != null) {
                vSc.close();
            }
        }
    }
      
    /**
     * Procédure qui permet d'obtenir l'inventaire des items du joueur
     * @param Command tapée au clavier
     */
    private void inventaireItem(final Command pCommand)
    {
        if (pCommand.hasSecondWord()) {
            this.aGui.println("Inventaire doesn't accept a second word !");
            return;
        }
        
        if (this.aPlayer.itemIsEmpty()){
        this.aGui.println("You don't have item and your weight is " + this.aPlayer.getPoids());
        }
        else{
         this.aGui.println(this.aPlayer.getItemPlayerString() + "\nWeight : " + this.aPlayer.getPoids());
        }
    }
    
    /**
     * Procédure eat qui permet au joueur de manger
     * @param Command tapée au clavier
     */
    private void eat(final Command pCommand)
    {
       if (!pCommand.hasSecondWord()) {
            this.aGui.println("Eat what ?");
            return;
       }  
       
       String vNom = pCommand.getSecondWord();
       Item vItem = this.aPlayer.getItemPlayer(vNom);
       
       if (vItem == null) {
           this.aGui.println("You don't have this item !");
       }
       else {
           if (vNom.equals("magicCookie")) {
           this.aPlayer.setPoidsMax(this.aPlayer.getPoidsMax()*2);
           this.aPlayer.dropItem(vItem);
           this.aGui.println("You ate the magic cookie. So you can carry heavier items !");
        }
        else {
            this.aGui.println("You can't eat this item !");
        }
       }  
    }
    
    /**
     * Procédure qui permet de calculer le nombre de go north utilisé
     * Si le nombre de fois est supérieur à 6 le jeu s'arrête et le joueur a perdu
     * Sinon on affiche le nombre de fois restant pour utilisé la commande
     */
    private void timeCommand()
    {
        this.aTimeCommand = this.aTimeCommand + 1;
        int vCompteur = 6 - this.aTimeCommand;
        if (this.aTimeCommand > 6) {
            this.aGui.println("Game Over ! \nYou used the go north command more than 6 times so you lost !");
            this.interpretCommand("quit");
            return;
        }
        else {
            if (vCompteur == 0){
            this.aGui.println("You can't use anymore the go north command !");
            return;
            }
            else {
            this.aGui.println("You can use the go north command " + vCompteur + " more times !");
            }
        }
    }
     
    /**
     * Procédure qui permet de charger un beamer
     * @param Command tapée au clavier
     */
    private void charge(final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){
            this.aGui.println("Charge what ?");
            return;
        }
        
        String vNom = pCommand.getSecondWord();
        Item vItem = this.aPlayer.getItemPlayer(vNom);
        
        if (vItem == null){
            this.aGui.println("You don't have this in your inventory !");
            return;
        }
        
        if (vItem.getClass() != Beamer.class){
            this.aGui.println("This item is not a Beamer !");
        }
        else {
            Beamer vBeamer = (Beamer) vItem;
            vBeamer.chargeBeamer(this.aPlayer.getCurrentRoom());
            this.aGui.println("The beamer is charged !");
        }//si l'item n'est pas un Beamer on affiche un message sinon on charge le beamer 
   
    }
    
    /**
     * Procédure qui permet de lancer un beamer
     * @param Command tapée au clavier
     */
    private void fire(final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){
            this.aGui.println("Fire what ?"); 
            return;
        }
        
        String vNom = pCommand.getSecondWord();
        Item vItem = this.aPlayer.getItemPlayer(vNom);
        
        if (vItem == null){
            this.aGui.println("You don't have this in your inventory !");
            return;
        }
        
        if (vItem.getClass() != Beamer.class){
            this.aGui.println("This item is not a Beamer !");
        }
        else {
            Beamer vBeamer = (Beamer) vItem;
            if (!vBeamer.getCharged()){
                this.aGui.println("Your beamer is not charged !");
            }
            else {
                this.aPlayer.setCurrentRoom(vBeamer.getRoomEnregistre());
                vBeamer.setCharged(false);
                printLocationInfo();
                this.aGui.println("You are right now in the room where the beamer was charged !");
           }
       }//si l'item n'est pas un Beamer on affiche un message sinon on teste si le beamer est chargé, si ce n'est pas le cas on affiche un message sinon 
        //on change de room et on décharge le beamer
    }
}// GameEngine

