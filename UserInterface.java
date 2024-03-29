import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import java.net.URL;

import java.awt.GridLayout;
import javax.swing.JButton;

import javax.swing.Timer;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003) DB edited (2019)
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    
    private JPanel aPanelButton;
    private JButton aButtonQuit;
    private JButton aButtonHelp;
    private JButton aButtonNorth;
    private JButton aButtonEast;
    private JButton aButtonSouth;
    private JButton aButtonWest;
    private JButton aButtonUp;
    private JButton aButtonDown;
    private JButton aButtonLook;
    private JButton aButtonBack;
    
    private Timer aTimer;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
        this.aTimer = new Timer(20000, this);
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( ! pOnOff ) { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Island of Java" ); // change the title
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        JPanel vPanel = new JPanel();
        this.aImage = new JLabel();
        
        this.aPanelButton = new JPanel();
        aPanelButton.setLayout(new GridLayout(5,2));
        
        this.aButtonQuit = new JButton("quit");
        this.aButtonHelp = new JButton("help");
        this.aButtonNorth = new JButton("go north");
        this.aButtonEast = new JButton("go east");
        this.aButtonSouth = new JButton("go south");
        this.aButtonWest = new JButton("go west");
        this.aButtonUp = new JButton("go up");
        this.aButtonDown = new JButton("go down");
        this.aButtonLook = new JButton("look");
        this.aButtonBack = new JButton("back");
        
        aPanelButton.add(this.aButtonQuit);
        aPanelButton.add(this.aButtonHelp);
        aPanelButton.add(this.aButtonNorth);
        aPanelButton.add(this.aButtonEast);
        aPanelButton.add(this.aButtonSouth);
        aPanelButton.add(this.aButtonWest);
        aPanelButton.add(this.aButtonUp);
        aPanelButton.add(this.aButtonDown);
        aPanelButton.add(this.aButtonLook);
        aPanelButton.add(this.aButtonBack);
        

        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        vPanel.add(this.aPanelButton, BorderLayout.EAST);
        

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aEntryField.addActionListener( this );
        
        this.aButtonQuit.addActionListener(this);
        this.aButtonHelp.addActionListener(this);
        this.aButtonNorth.addActionListener(this);
        this.aButtonEast.addActionListener(this);
        this.aButtonSouth.addActionListener(this);
        this.aButtonWest.addActionListener(this);
        this.aButtonUp.addActionListener(this);
        this.aButtonDown.addActionListener(this);
        this.aButtonLook.addActionListener(this);
        this.aButtonBack.addActionListener(this);

        // to end program when window is closed
        this.aMyFrame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()
    
    /**
     * Procédure qui permet de désactiver les boutons
     */
    public void enableButtons()
    {
            this.aButtonQuit.setEnabled(false);
            this.aButtonHelp.setEnabled(false);
            this.aButtonNorth.setEnabled(false);
            this.aButtonEast.setEnabled(false);
            this.aButtonWest.setEnabled(false);
            this.aButtonSouth.setEnabled(false);
            this.aButtonUp.setEnabled(false);
            this.aButtonDown.setEnabled(false);
            this.aButtonLook.setEnabled(false);
            this.aButtonBack.setEnabled(false);
    }
    
    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        if (pE.getSource() == this.aButtonQuit) {
            this.aEngine.interpretCommand("quit");
            this.enableButtons();
        }
        else if (pE.getSource() == this.aButtonHelp) {
            this.aEngine.interpretCommand("help");
        }
        else if (pE.getSource() == this.aButtonNorth) {
            this.aEngine.interpretCommand("go north");
        }
        else if (pE.getSource() == this.aButtonEast) {
            this.aEngine.interpretCommand("go east");
        }
        else if (pE.getSource() == this.aButtonSouth) {
            this.aEngine.interpretCommand("go south");
        }
        else if (pE.getSource() == this.aButtonWest) {
            this.aEngine.interpretCommand("go west");
        }
        else if (pE.getSource() == this.aButtonUp) {
            this.aEngine.interpretCommand("go up");
        }
        else if (pE.getSource() == this.aButtonDown) {
            this.aEngine.interpretCommand("go down");
        }
        else if (pE.getSource() == this.aButtonLook) {
            this.aEngine.interpretCommand("look");
        }
        else if (pE.getSource() == this.aButtonBack) {
            this.aEngine.interpretCommand("back");
        }
        else if (pE.getSource() == this.aTimer) {
            this.println("Game Over !\nYou stayed more than 20 seconds in the Marshy Jaw where there was poisoned gas,so you lost!");
            this.aTimer.stop();
            this.aEngine.interpretCommand("quit");
        }
        else {
            this.processCommand();
            }
        // never suppress this line
    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
 
    /**
     * Accesseur de l'attribut aTimer
     * @return Timer l'attribut aTimer
     */
    public Timer getTimer()
    {
      return this.aTimer;
    }
} // UserInterface 

