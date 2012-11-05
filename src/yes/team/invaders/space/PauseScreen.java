// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: PauseScreen.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space;

// Import the required files for the class.
import java.util.List;
import yes.team.framework.Game;
import yes.team.framework.Graphics;
import yes.team.framework.Input.TouchEvent;
import yes.team.framework.Screen;
import yes.team.invaders.space.objects.GameButton;

// Definition and implementation of the PauseScreen class. This 
// class controls the screen which appears when the player presses 
// pause in-game.
public class PauseScreen  extends Screen
{
	// Button variables for the resume and options GameButtons.
  private GameButton resumeButton;
  private GameButton optionsButton;
  private GameButton mainMenuButton; 
  
  public PauseScreen(Game _game)
  { // Constructor for the PauseScreen class.
  	// Call the superclasses instance of the constructor.
  	super(_game);
  	
  	// Set the local buttons to be new buttons based on the passed
  	// in game value.
  	this.resumeButton   = new GameButton(_game);
  	this.optionsButton  = new GameButton(_game);
  	this.mainMenuButton = new GameButton(_game);
  	
  	// Set the sprite, x position, y position and collision box for the resume button.
  	this.resumeButton.setSprite(Assets.getInstance().getResumeButton());
  	this.resumeButton.setXPosition(_game.getGraphics().getWidth()/2-this.resumeButton.getWidth()/2);
  	this.resumeButton.setYPosition(165);
  	this.resumeButton.updateCollisionBox();
  	
  	// Set the sprite, x position, y position and collision box for the options button.
  	this.optionsButton.setSprite(Assets.getInstance().getOptionsButton());
  	this.optionsButton.setXPosition(_game.getGraphics().getWidth()/2-this.optionsButton.getWidth()/2);
  	this.optionsButton.setYPosition(this.resumeButton.getYPosition() + this.resumeButton.getHeight() + 40);
  	this.optionsButton.updateCollisionBox();
  	
  	// Set the attributes for the main menu button.
  	this.mainMenuButton.setSprite(Assets.getInstance().getMainMenuButton());
    this.mainMenuButton.setXPosition(_game.getGraphics().getWidth()/2-this.mainMenuButton.getWidth()/2);
    this.mainMenuButton.setYPosition(this.optionsButton.getYPosition() + this.optionsButton.getHeight() + 40);
    this.mainMenuButton.updateCollisionBox();
  	
  } // public PauseScreen(Game _game)  
  
  
  @Override
  public void update(float deltaTime)
  { // Update method for the PauseScreen class. This method updates the pausescreen based
    // on player input etc.
    
  	// Create a local list of touch events and set it to be the 
  	// list of game touch events.
    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	
    int length = touchEvents.size();
	
    // Cycle through the touch events and determine if a touch occured at the menu button.
    for(int i=0;i<length;i++)
    {
	    TouchEvent event = touchEvents.get(i);
	    if(event.type == TouchEvent.TOUCH_UP)
	    {
	    	// Check for a press of the resume button.
        if(this.resumeButton.inBounds(event))
        { // Create a new game screen.
          Assets.getInstance().getClick().play(1);
          game.popScreen();
        } // end if
	        
        // Check for a press of the options button.
        if(this.optionsButton.inBounds(event))
        {
          Assets.getInstance().getClick().play(1);
          game.pushScreen(this);
          game.setScreen(new OptionScreen(game));
        } // end if
        if(this.mainMenuButton.inBounds(event))
        {
          Assets.getInstance().getClick().play(1);
          game.clearScreenStack();
          game.setScreen(new MainMenuScreen(game));          
        } // end if
      } // end if
    } // end for
	} // public void update(float deltaTime)
  
  
  @Override
  public void present(float deltaTime) 
  { // Present method for the PauseScreen class. This method draws the PauseScreen.
  	// Create a local graphics variable and set it to the game graphics.
    Graphics g = this.game.getGraphics();
    
    game.renderBottomScreenStack();
    // Draw the transparent overlay for the pause menu.
    g.drawPixmap(Assets.getInstance().getMembrane(), 0, 0);
    // Draw the graphics for the pause label.
    g.drawPixmap(Assets.getInstance().getPauseLabel(), 
    game.getGraphics().getWidth()/2 - Assets.getInstance().getPauseLabel().getWidth()/2, 50);
        
    // Render the resume and options buttons.
    this.resumeButton.render();
    this.optionsButton.render();
    this.mainMenuButton.render();
  } // public void present(float deltaTime)
  
  
  @Override
  public void pause() 
  { // Pause method for the PauseScreen class.
    // Leave blank.
  } // public void pause()

  
  @Override
  public void resume() 
  { // Resume method for the PauseScreen class.
    this.game.setLocking(false);
  } // public void resume()

  
  @Override
  public void dispose() 
  { // Dispose method for the PauseScreen class.
  
  } // public void dispose()  
} // public class PauseScreen  extends Screen
