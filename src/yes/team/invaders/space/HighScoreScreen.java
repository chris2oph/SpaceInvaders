//----------------------------------------------------------------
//Project: SpaceInvaders
//File: HighScoreScreen.java
//Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//    Thomas Gillett
//----------------------------------------------------------------

package yes.team.invaders.space;

import java.util.List;

import yes.team.framework.Game;
import yes.team.framework.Input.TouchEvent;
import yes.team.framework.Screen;
import yes.team.invaders.space.objects.GameButton;

public class HighScoreScreen extends Screen
{
  //Variable declarations for the start and options buttons.
  GameButton backButton;
 
  public HighScoreScreen(Game _game)
  { // Constructor for high score. Creates a new button and call super.
    super(_game);
    this.backButton = new GameButton(_game);
   
    // Set the sprite, x position, y position and collision box for the options button.
    this.backButton.setSprite(Assets.getInstance().getBackButton());
    this.backButton.setXPosition(_game.getGraphics().getWidth()/2-this.backButton.getWidth()/2);
    this.backButton.setYPosition(_game.getGraphics().getHeight() - this.backButton.getHeight() - 20);
    this.backButton.updateCollisionBox();
    this.initialise();
  } // public HighScoreScreen
  
  
  public void initialise()
  { // Initialise and draw the scores when you need to.
    this.game.getGraphics().drawPixmap(Assets.getInstance().getBackground(),0,0);
    
    List<Integer> scores = HighScores.getInstance().getHighScores();
    
    int length = scores.size();
    for(int i=0;i<length;i++)
    {
      int x = game.getGraphics().getWidth()/2-20;
      int y = i*30 + 80; 
      NumberOutput scoreOutput = new NumberOutput(this.game,x,y,21,21,Assets.getInstance().getNumberSpr(),13);
      scoreOutput.setNumberOutput(scores.get(i).intValue());
      scoreOutput.render();
    } // end for
    
  } // public void initialise()
  
  
  @Override
  public void update(float _deltaTime)
  {
    List<TouchEvent> touchEvents = this.game.getInput().getTouchEvents();
    
    int length = touchEvents.size();
   
    // Cycle through the touch events and determine if a touch occured at the menu button.
    for(int i=0;i<length;i++)
    {
      TouchEvent event = touchEvents.get(i);
      
      if(event.type == TouchEvent.TOUCH_UP)
      {
        // If the start button is pressed, start the game.
        if(this.backButton.inBounds(event))
        { // Create a new game screen.
          Assets.getInstance().getClick().play(1);
          this.game.popScreen();
        } // end if
      } //end if
    } // end for
    
  } // public void update(float deltaTime)
  
  
  @Override
  public void present(float _deltaTime) 
  { // Present method for the MainMenuScreen. This method draws the main menu screen.
    this.game.getGraphics().drawPixmap(Assets.getInstance().getHighScoreTitle(), 
        this.game.getGraphics().getWidth()/2-Assets.getInstance().getHighScoreTitle().getWidth()/2, 10);
    this.backButton.render();
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
    // Leave blank.
  } // public void dispose()  
  
} // public class HighScoreScreen
