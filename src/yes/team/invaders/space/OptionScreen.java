// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: OptionScreen.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space;

// Import the files required for the class.
import java.util.List;
import yes.team.framework.Game;
import yes.team.framework.Graphics;
import yes.team.framework.Input.TouchEvent;
import yes.team.framework.Screen;
import yes.team.invaders.space.objects.GameButton;

//Definition and implementation of the OptionScreen class.
public class OptionScreen extends Screen 
{ 
  // Button variables for the back and touch buttons.
  GameButton back;
  GameButton touch;

  // Constructor for the OptionScreen class.
  public OptionScreen(Game _game)
  {
  	// Class the superclass constructor.
  	super(_game); 
  	
  	// Set the variables for the back and touch buttons to be new buttons.
  	this.back = new GameButton(_game);
  	this.touch = new GameButton(_game);
  	
  	// Set the sprite, x position, y position and collision box for the touch button.
  	this.touch.setSprite(Assets.getInstance().getTouchButton());
  	this.touch.setXPosition(_game.getGraphics().getWidth()/2-touch.getWidth()/2);
  	this.touch.setYPosition(195);
  	this.touch.updateCollisionBox();
  	
  	// Set the sprite, x position, y position and collision box for the back button.
  	this.back.setSprite(Assets.getInstance().getBackButton());
  	this.back.setXPosition(_game.getGraphics().getWidth()/2-back.getWidth()/2);
  	this.back.setYPosition(this.touch.getYPosition() + this.touch.getHeight() + 40);
  	this.back.updateCollisionBox();
  } // public MainMenuScreen(Game game)

  
  @Override
  public void update(float deltaTime)
  { // Update method for the OptionScreen class. This method updates the OptionScreen 
    // when the player interacts etc.
  	// Create a local list and set it to the list of touch events for the game.
    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
    
    int length = touchEvents.size();
	 
    // Cycle through the touch events and determine if a touch occured at the menu button.
    for(int i=0;i<length;i++)
    {
	    TouchEvent event = touchEvents.get(i);
	   
	    if(event.type == TouchEvent.TOUCH_UP)
	    {
	    	// If the touch button is pressed, change the type of control for the player.
        if(this.touch.inBounds(event))
        { // Create a new game screen.
        	Settings.getInstance().setTouchControlOn(!Settings.getInstance().getTouchControlOn());
        } // end if
        
        // If the back button is pressed, return to the previous screen.
        if(this.back.inBounds(event))
        {
        	// Save the settings.
        	Settings.getInstance().save(game.getFileIO());
        	// Render the last screen before the OptionScreen.
        	game.renderBottomScreenStack();
        	Graphics g = game.getGraphics();
        	g.drawPixmap(Assets.getInstance().getMembrane(), 0, 0); 
        	// Pop the OptionScreen from the screen stack.
        	game.popScreen();
        } // end if
	    } // end if
    } // end for
  } // public void update(float deltaTime)

  
  @Override
  public void present(float deltaTime) 
  { // Present method for the OptionScreen class. This method draws the OptionScreen.
  	// Create a graphics variable and set it to the game graphics.
    Graphics g = game.getGraphics();
    Assets assets = Assets.getInstance();
    
    // Render the screen at the bottom of the screen stack.
    game.renderBottomScreenStack();
    g.drawPixmap(Assets.getInstance().getMembrane(), 0, 0);

    // Render the back and touch buttons.
    this.back.render();
    this.touch.render();
    
    // If the touch control is on, draw the tick sprite.
    if(Settings.getInstance().getTouchControlOn())
    { // Touch tick is on.
      g.drawPixmap(assets.getTick(), 265, touch.getYPosition());
    } // end if
    // Else draw the cross sprite.
    else
    {
      g.drawPixmap(assets.getCross(), 260, touch.getYPosition());
    } // end else
  } // public void present(float deltaTime) 

  
  @Override
  public void pause() 
  { // Pause method for the OptionScreen class.
    // Leave blank. 
  } // public void pause()

  
  @Override
  public void resume() 
  { // Resume method for the OptionScreen class.
    // Leave blank.
  
  } // public void resume()

  
  @Override
  public void dispose() 
  { // Dispose method for the OptionScreen class.
  
  } // public void dispose()
} // public class OptionScreen extends Screen
