// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: LoadingScreen.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//     Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space;

// Import the files required for the class.
import yes.team.framework.Graphics;
import yes.team.framework.Graphics.PixmapFormat;
import yes.team.framework.Screen;
import yes.team.framework.implementation.AndroidGame;

// Definition and implementation of the LoadingScreen class.
public class LoadingScreen extends Screen 
{
	  
  public LoadingScreen(AndroidGame game)
  { // Constructor for the LoadingScreen class.
  	// Run the superclasses constructor.
  	super(game);
  } // public LoadingScreen(AndroidGame game)
  
  
  @Override
  public void update(float deltaTime)
  { // Update method for the LoadingScreen class. This method updates based on the 
    // player input etc.
    
  	// Load the assets and set the screen to the main menu screen.
  	this.loadAssets(); 
  	game.setScreen(new MainMenuScreen(game));
  } // public void update(float deltaTime)
  
  
  private void loadAssets()
  { // LoadAssets method for the LoadingScreen class. This method loads all of the assets 
    // for the game.
    
  	// Load the graphics, sounds and settings for the game.
    this.loadGraphics();
    this.loadSounds();
    Settings.getInstance().load(game.getFileIO());
    HighScores.getInstance().load(game.getFileIO());
  } // private void loadAssets()
  
  
  private void loadGraphics()
  { // LoadGraphics method for the LoadingScreen class. This method loads all
    // of the graphics for the game.
	  // Load all the graphical assets into the game,
	  Graphics g = game.getGraphics();
	  Assets assets = Assets.getInstance();
	  
	  assets.setStartButton(g.newPixmap("start.png", PixmapFormat.RGB565));
	  assets.setPlayer(g.newPixmap("player.png", PixmapFormat.ARG8888));
	  assets.setPlayerBullet(g.newPixmap("bulletUp.png", PixmapFormat.ARG8888));
	  assets.setExplosion(g.newPixmap("explosion.png", PixmapFormat.ARG8888));
	  assets.setInvaderBullet(g.newPixmap("bulletDown.png", PixmapFormat.ARG8888));
	  assets.setInvader(0, g.newPixmap("invader0.png", PixmapFormat.ARG8888));
	  assets.setInvader(1, g.newPixmap("invader1.png", PixmapFormat.ARG8888));
	  assets.setInvader(2, g.newPixmap("invader2.png", PixmapFormat.ARG8888));
	  assets.setStartButton(g.newPixmap("start.png", PixmapFormat.RGB565));
	  assets.setObstacleSpr(g.newPixmap("obstacle_block.png", PixmapFormat.ARG8888));
	  assets.setObstacleCornerSpr(g.newPixmap("obstacle_corner.png", PixmapFormat.ARG8888));
	  assets.setObstacleCornerRSpr(g.newPixmap("obstacle_corner_R.png", PixmapFormat.ARG8888));
	  assets.setTitleImage(g.newPixmap("title_screen.png", PixmapFormat.RGB565));
	  assets.setOptionsButton(g.newPixmap("options_button.png", PixmapFormat.RGB565));
	  assets.setMainMenuButton(g.newPixmap("mainmenu_button.png", PixmapFormat.RGB565));
	  assets.setTouchButton(g.newPixmap("touch.png", PixmapFormat.RGB565));
	  assets.setBackButton(g.newPixmap("back_button.png", PixmapFormat.RGB565));
	  assets.setNumberSpr(g.newPixmap("numbers.png", PixmapFormat.ARG8888));
	  assets.setTick(g.newPixmap("tick.png", PixmapFormat.RGB565));
	  assets.setCross(g.newPixmap("cross.png", PixmapFormat.RGB565));
	  assets.setBackground(g.newPixmap("background.png", PixmapFormat.RGB565));
	  assets.setScoreSpr(g.newPixmap("scoreSpr.png", PixmapFormat.ARG8888));
	  assets.setLivesSpr(g.newPixmap("livesSpr.png", PixmapFormat.ARG8888));
	  assets.setTopBar(g.newPixmap("topBar.png", PixmapFormat.RGB565));
	  assets.setPlayerExplosion(g.newPixmap("player_explode.png", PixmapFormat.ARG8888));
	  assets.setMembrane(g.newPixmap("membrane.png", PixmapFormat.ARG8888));
	  assets.setResumeButton(g.newPixmap("resume.png", PixmapFormat.RGB565));
	  assets.setGameOver(g.newPixmap("gameOver.png", PixmapFormat.RGB565));
	  assets.setPausedTitle(g.newPixmap("paused.png", PixmapFormat.RGB565)); 
	  assets.setMothership(g.newPixmap("mothership.png", PixmapFormat.ARG8888));
	  assets.setHighScoreButton(g.newPixmap("highScoreButton.png", PixmapFormat.RGB565));
	  assets.setHighScoreTitle(g.newPixmap("highScoreTitle.png", PixmapFormat.ARG8888));
  } // private void loadGraphics()
  
  
  private void loadSounds()
  { // LoadSounds method for the LoadingScreen class. This method loads all of the sounds 
    // for the game.
    Assets assets = Assets.getInstance();
  	
    // Load all of the sounds.
    assets.setClick(game.getAudio().newSound("click.ogg"));
  	assets.setInvaderMove(game.getAudio().newSound("invaderMove.wav"));
	  assets.setInvaderDeath(game.getAudio().newSound("invaderDeath.wav"));
	  assets.setPlayerDeath(game.getAudio().newSound("playerDeath.wav"));
	  assets.setShoot(game.getAudio().newSound("shoot.wav"));
  } // private void loadSounds()

  
  @Override
  public void present(float _deltaTime) 
  { // Present method for the LoadingScreen class. 
    // Leave blank.
  } // public void present(float _deltaTime)

  
  @Override
  public void pause() 
  { // Pause method for the LoadingScreen class.
    // Leave blank.
  } // public void pause()

  
  @Override
  public void resume() 
  { // Resume method for the LoadingScreen class.
    // Leave blank.
  } // public void resume()

  
  @Override
  public void dispose() 
  { // Dispose method for the LoadingScreen class.
    // Leave blank.
  } // public void dispose()
} // public class LoadingScreen extends Screen
