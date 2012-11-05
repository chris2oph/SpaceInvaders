// ----------------------------------------------------------------
// Space Invaders.
// Screen.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework;

public abstract class Screen 
{
  protected final Game game;
  
  public Screen(Game game)
  {
    this.game = game;
  } // public Screen(Game game)
  
  // Updates the current game screen. Takes a _deltatime which holds the time since last frame.
  public abstract void update(float _deltaTime);
  
  // Presents the screen. Calls the render functions of the screen.
  public abstract void present(float _deltaTime);
  
  // Used for any potential pausing of the game screen when the game scene loses main focus.
  public abstract void pause();
  
  // Called when the game screen is resumed by reopening the application.
  public abstract void resume();
  
  // Called when the screen is to be released and disposed of.
  public abstract void dispose();
  
} // public abstract class Screen
