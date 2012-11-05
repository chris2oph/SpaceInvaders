// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: Lives.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space;

// Import the files required for the class.
import yes.team.framework.Pixmap;
import yes.team.framework.Game;
import yes.team.framework.Graphics;
import yes.team.invaders.space.Assets;

// Definition and implementation of the Lives class. This class handles
// the amount of lives for the game.
public class Lives 
{
  // Singleton instance of the Assets class.
  private static Lives instance = null;
  
  // Variables for the number of lives, if gameover occurs, the sprite,
  // the lives output and the game. Also, a constant definition for the 
  // max number of lives.
	private int lives;
	static final private int START_LIVES = 3;
	private boolean gameOver;
	private Pixmap livesSpr;
	private NumberOutput livesOutput;
	private Game game;
	
	
	private Lives()
	{ // Constructor for the Lives class.
	  
	} // private Lives()
	
	
	public void initialise(Game _game)
	{ // Initialise method for the Lives class. This method initialises 
	  // all of the variables for the Lives class to their default values.
		// Initialise variables to their default values.
	  this.game     = _game;
	  this.lives    = Lives.START_LIVES;
	  this.gameOver = false;
	  this.livesSpr = Assets.getInstance().getLivesSpr();
	  this.livesOutput = new NumberOutput(_game,300,5,21,21,Assets.getInstance().getNumberSpr(),13);
	  this.livesOutput.setNumberOutput(0);
	} // public void initialise(Game _game)
	
	
  public static Lives getInstance()
  { // GetInstance method for the Lives class. This method returns an instance of the 
    // class if one does not already occur.
  	if(instance == null)
	  {
	    instance = new Lives();
	  }
	  return instance;
  } // public static synchronized AssetManager getInstance()
	
  
	public void render()
	{ // Render method for the Lives class. This method renders the lives to screen.
	  
		// Create a local graphics variable and set it to the game graphics.
		Graphics g = this.game.getGraphics();
		
		if(this.livesSpr != null)
	  { // If the lives sprite is not null, draw it to screen.
	    g.drawPixmap(this.livesSpr, 200, 0);
	  } // end if
		
		// Set the number of lives to be the current lives value and
		// draw the value to screen.
		this.livesOutput.setNumberOutput(lives);
		this.livesOutput.render();
	} // public void render()
	
	
	public void resetGame()
	{ // ResetGame method for the Lives class. This method resets the lives when
	  // game over occurs or the player resets the game.
		this.lives = Lives.START_LIVES;
		this.gameOver = false;
	} // public void resetScore()

	
	public void RemoveLife()
	{ // RemoveLife method for the Lives class. This method removes lives as the player is 
	  // hit by invader bullets.
	  
		// Decrement the amount of lives.
		this.lives--;
		
		// If the amount of lives is less than 0, switch to game over.
	    if(this.lives < 0)
	    { 
	      this.game.renderCurrentScreen();
	      HighScores.getInstance().addHighScore(Score.getInstance().getScore());
	      HighScores.getInstance().save(game.getFileIO());
	      this.game.setScreen(new GameOverScreen(this.game));
	    } // end if
	} // public void AddScore(int _score)
	
	
	public int getLives() 
	{ // GetLives method for the Lives method. This method returns the number of lives.
	  return this.lives; 
	} // public int getLives()
	
	
	public boolean getGameOver()  
	{ // GetGameOver method for the Lives method. This method gets a boolean for 
	  // if the game is over.
	  return this.gameOver; 
	} // public boolean getGameOver()
	
	
	public void setLives(int _lives) 
	{ // SetLives method for the Lives class. This method sets the number of lives based on the 
	  // passed in value.
	  this.lives = _lives; 
	} // public void setLives(int _lives)
	
} // public class Lives 
