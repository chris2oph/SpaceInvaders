// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: Score.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space;

// Import the required files for the class.
import yes.team.framework.Pixmap;
import yes.team.framework.Game;
import yes.team.framework.Graphics;
import yes.team.invaders.space.Assets;

// Definition and declaration of the Score class. This class 
// stores and displays the score for the game.
public class Score 
{
  // Singleton instance of the Assets class.
  private static Score instance = null;
  
  // Variables for the score, sprite, score which is output 
  // to screen and an instance of the game.
	private int score;
	private Pixmap scoreSpr;
	private NumberOutput scoreOutput;
	private Game game;
	
	
	private Score()
	{ // Constructor for the Score class.
	  
	} // private Score()
	
	
	public void initialise(Game _game)
	{ // Initialise method for the Score class. This method initialises
	  // all variables for the class.
	  
    // Set the local game variable to the passed in game value.
    this.game = _game;
    // Set the initial score to 0.
    this.score = 0;
    // Set the local sprite to the score sprite stored in the Assets class.
    this.scoreSpr = Assets.getInstance().getScoreSpr();
    // Set the local score output to a new NumberOutput.
    this.scoreOutput = new NumberOutput(_game,90,5,21,21,Assets.getInstance().getNumberSpr(),13);
    // Set the current number output to be 0.
    this.scoreOutput.setNumberOutput(0);
	} // public void initialise(Game _game)
	
	
  public static Score getInstance()
  { // GetInstance method for the Score class. This method returns a new 
    // instance of the score class if one does not already exist.
    
    if(instance == null)
    {
      instance = new Score();
    }
	  return instance;
  } // public static synchronized AssetManager getInstance()
	
    
	public void render()
	{ // Render method for the Score class. This method renders the
    // score sprite and value to screen.
	  
		// Create a new graphics variable and set it to be the graphics
		// of the current game.
		Graphics g = this.game.getGraphics();
		
		// If the score sprite is not null, draw the score sprite to screen.
		if(this.scoreSpr != null)
    {
      g.drawPixmap(this.scoreSpr, 0, 5);
    } // end if
		
		// Set the score output to be the score and render the score value.
		this.scoreOutput.setNumberOutput(score);
		this.scoreOutput.render();
	} // public void render()
	
	
	public void resetScore()
	{ // ResetScore method for the Score class. This method resets the score when
	  // the game is reset.
		
		this.score = 0;       // Set the score value to 0.
	} // public void resetScore()
	
	
	public void AddScore(int _score)
	{ // AddScore method for the Score class. This method adds the passed in
	  // value to the current score value.
		// Add the passed in value to the current score value.
		this.score += _score;
	} // public void AddScore(int _score)
	
	
	public int getScore() 
	{ // GetScore method for the score class. This method returns the current 
	  // score value.
		return this.score; 
	} // public int getScore()
	
} // public class Score
