// ----------------------------------------------------------------
// Space Invaders.
// Game.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework;

public interface Game 
{
  // Returns the input manager implementation for the game.
  public Input getInput();
  
  // Returns the file input and output manager for the game.
  public FileIO getFileIO();
  
  // Returns the graphics manager for the game.
  public Graphics getGraphics();
  
  // Returns the audio manager for the game.
  public Audio getAudio();
  
  // Sets the current screen or scene of the game.
  public void setScreen(Screen _screen);
  
  // Pushes the screen parameter on to the screen stack for transition between screens.
  public void pushScreen(Screen _screen);
  
  // Pops a screen off the screen stack and places it as the current screen.
  public void popScreen();
  
  // Returns the current active screen of the game.
  public Screen getCurrentScreen();
  
  // Returns the start screen of the game.
  public Screen getStartScreen();
  
  // Set whether the phone is locking or not.
  public void setLocking(boolean _locking);
  
  // Renders the game screen at position 0 on the stack. Used for providing a seamless transition back to previous screens.
  public void renderBottomScreenStack();
  
  // Renders the current game screen. Used just before the game over screen is called.
  public void renderCurrentScreen();
  
  // Clears the stack from any screens.
  public void clearScreenStack();
  
} // public interface Game
