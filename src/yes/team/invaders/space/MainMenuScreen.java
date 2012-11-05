// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: MainMenuScreen.java
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


// Definition and implementation of the MainMenuScreen class. This class controls
// the main menu seen at the start of the game.
public class MainMenuScreen extends Screen 
{
  // Variable declarations for the start and options buttons.
  GameButton startButton;
  GameButton optionsButton;
  GameButton highScoresButton;
  
  public MainMenuScreen(Game _game)
  { // Constructor for the MainMenuScreen class.
    
  	// Call the superclass constructor.
	  super(_game);
	  
	  // Set the start and options buttons to be new buttons.
	  this.startButton = new GameButton(_game);
	  this.optionsButton = new GameButton(_game);
	  this.highScoresButton = new GameButton(_game);
	  
	  // Set the sprite, x position, y position and collision box for the start button.
	  this.startButton.setSprite(Assets.getInstance().getStartButton());
	  this.startButton.setXPosition(_game.getGraphics().getWidth()/2-this.startButton.getWidth()/2);
	  this.startButton.setYPosition(165);
	  this.startButton.updateCollisionBox();
	  
	  // Set the sprite, x position, y position and collision box for the options button.
	  this.optionsButton.setSprite(Assets.getInstance().getOptionsButton());
	  this.optionsButton.setXPosition(_game.getGraphics().getWidth()/2-this.optionsButton.getWidth()/2);
	  this.optionsButton.setYPosition(this.startButton.getYPosition() + this.startButton.getHeight() + 27);
	  this.optionsButton.updateCollisionBox();
	  
	  // Set the sprite, x position, y position and collison box for the high scores button.
	  this.highScoresButton.setSprite(Assets.getInstance().getHighScoreButton());
	  this.highScoresButton.setXPosition(_game.getGraphics().getWidth()/2-this.highScoresButton.getWidth()/2);
	  this.highScoresButton.setYPosition(this.optionsButton.getYPosition() + this.optionsButton.getHeight() + 27);
    this.highScoresButton.updateCollisionBox();
    
  } // public MainMenuScreen(Game game)
  
  
  @Override
  public void update(float _deltaTime)
  { // Update method for the MainMenuScreen class. This method updates the screen
    // based on the player input etc.
    
  	// Create a local list and set it to contain the list of game touch
  	// events.
    List<TouchEvent> touchEvents = this.game.getInput().getTouchEvents();
   
    int length = touchEvents.size();
   
    // Cycle through the touch events and determine if a touch occured at the menu button.
    for(int i=0;i<length;i++)
    {
    	TouchEvent event = touchEvents.get(i);
    	
    	if(event.type == TouchEvent.TOUCH_UP)
    	{
    		// If the start button is pressed, start the game.
    		if(this.startButton.inBounds(event))
    		{ // Create a new game screen.
    			Assets.getInstance().getClick().play(1);
    			this.game.setScreen(new GameScreen(this.game));
    		} // end if
    		
    		// If the options button is pressed, go to the options screen.
    		if(this.optionsButton.inBounds(event))
    		{
    			this.game.pushScreen(this);
    			this.game.setScreen(new OptionScreen(this.game));
    		} // end if
    		
    		// If the high score button is pressed, go to the options screen.
        if(this.highScoresButton.inBounds(event))
        {
          this.game.pushScreen(this);
          this.game.setScreen(new HighScoreScreen(this.game));
        } // end if
    		
    	} // end if
    } // end for
  } // public void update(float deltaTime)

  
  @Override
  public void present(float deltaTime) 
  { // Present method for the MainMenuScreen. This method draws the main menu screen.
    
  	// Create a graphics variable and assign the game graphcis to it.
  	Graphics g = this.game.getGraphics();
	  Assets assets = Assets.getInstance();
	  
	  // Draw the title image.
	  g.drawPixmap(assets.getTitleImage(), 0, 0);
	  
	  // Draw the start and options buttons.
	  this.startButton.render();
	  this.optionsButton.render();
	  this.highScoresButton.render();
  } // public void present(float deltaTime) 

  
  @Override
  public void pause() 
  { // Pause method for the MainMenuScreen class.
    // Leave blank.
  } // public void pause()

  
  @Override
  public void resume() 
  { // Resume method for the MainMenuScreen class.
    // Leave blank.
  } // public void resume()

  
  @Override
  public void dispose() 
  { // Dispose method for the MainMenuScreen class.
  
  } // public void dispose() 
  
} // public class MainMenuScreen extends Screen
