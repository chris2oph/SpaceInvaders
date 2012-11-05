// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: GameOverScreen.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//     Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space;

// Import required files by the class.
import java.util.List;
import yes.team.framework.Game;
import yes.team.framework.Graphics;
import yes.team.framework.Input.TouchEvent;
import yes.team.framework.Screen;

// Definition and implementation of the GameOverScreen class. 
public class GameOverScreen extends Screen 
{
	// Boolean for checking for screen taps.
  boolean readyForTap = false;
  float tapTimer;
	
  public GameOverScreen(Game _game)
  {
    // Call the super class constructor.
    super(_game);
  
    // Draw the overlay membrane.
    game.getGraphics().drawPixmap(Assets.getInstance().getMembrane(), 0, 0);
    tapTimer = 1.5f;
  } // public GameOverScreen(Game game)
  
  
  @Override
  public void update(float _deltaTime)
  { // Update method for the GameOverScreen. This method updates the screen based on player input etc.
    if(tapTimer > 0)
    {
      tapTimer -= _deltaTime;
    } // end if
    // Handle touch for our scene.
	  List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	  int length = touchEvents.size();
	  
	  // Loop through the touch events and check for a touch down event.
	  for(int i=0;i<length;i++)
	  {
	    TouchEvent event = touchEvents.get(i);
	    
	    if(event.type == TouchEvent.TOUCH_DOWN)
	    {
	      if(this.tapTimer <= 0)
	      {
	        // Go to the game screen and reset the invaders.
	        game.setScreen(new GameScreen(game));
	        InvaderManager.getInstance().reset();
	      } // end if
	    } // end if
	  } // end for   
  } // public void update(float deltaTime)

  
  @Override
  public void present(float _deltaTime) 
  { // Present method for the GameOverScreen. This method draws to screen.
	  Graphics g = game.getGraphics();
	  g.drawPixmap(Assets.getInstance().getGameOver(), (game.getGraphics().getWidth() / 2) - (Assets.getInstance().getGameOver().getWidth() / 2),
	  		(game.getGraphics().getHeight() / 2) - (Assets.getInstance().getGameOver().getHeight() / 2), 0, 0, 
	  		Assets.getInstance().getGameOver().getWidth(), 
	  		Assets.getInstance().getGameOver().getHeight());
  } // public void present(float deltaTime)   

  
  @Override
  public void pause() 
  { // Pause method for the GameOverScreen.
    // Auto-generated method stub
  } // public void pause()
  
  
  @Override
  public void resume() 
  { // Resume method for the GameOverScreen.
    // Auto-generated method stub 	
  } // public void resume()
  
  
  @Override
  public void dispose() 
  { // Dispose method for the GameOverScreen.
    // Auto-generated method stub
  } // public void dispose()
} // public class MainMenuScreen extends Screen
