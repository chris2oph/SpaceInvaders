// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: Assets.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space;

// Import the files required by the class.
import yes.team.framework.Pixmap;
import yes.team.framework.Sound;

// Definition and implementation of the Assets class.
public class Assets 
{
  // Singleton instance of the Assets class.
	private static Assets instance = null; 
  
	// Assets - Images.
	private Pixmap player;
	private Pixmap playerExplosion;
	private Pixmap invader[] = new Pixmap[3];
	private Pixmap startButton; 
	private Pixmap optionsButton;
	private Pixmap playerBullet;
	private Pixmap touchButton;
	private Pixmap backButton;
	private Pixmap resumeButton;
	private Pixmap mainMenuButton;
	private Pixmap highScoreButton;
	private Pixmap cross;
	private Pixmap tick;
	private Pixmap background;
	private Pixmap scoreSpr;
	private Pixmap livesSpr;
	private Pixmap topBar;
	private Pixmap membrane;
	private Pixmap mothership;
	private Pixmap highScoreTitle;
	
  
	//Obstacle - Images.
	private Pixmap obstacleSpr;
	private Pixmap obstacleCornerSpr;
	private Pixmap obstacleCornerRSpr;
	private Pixmap invaderBullet;
	private Pixmap numberSpr;
  
	// Title images.
	private Pixmap titleImage;
	private Pixmap gameOver;
	private Pixmap pauseLabel;
  
	private Pixmap explosion;
	
	// Assets - Sounds.
	private Sound click;
	private Sound invaderMove;
	private Sound invaderDeath;
	private Sound playerDeath;
	private Sound shoot;
  
	
	private Assets()
	{ // Constructor.
		// No code required.
	} // private AssetManager()
  
  
  public static synchronized Assets getInstance()
  { // GetInstance method for the Assets class. This method returns an instance of the Assets
    // class if one does not already exist.
    if(instance == null)
    {
      instance = new Assets();
    } // end if
    return instance;
  } // public static synchronized AssetManager getInstance()

  
  public Pixmap getPlayer()
  { // GetPlayer method for the Assets class. This method returns the player pixmap.
  	return this.player;
  } // public Pixmap getPlayer()

  
  public Pixmap getInvader(int _type)
  { // GetInvader method for the Assets class. This method returns the invader pixmap.
  	return this.invader[_type];
  } // public Pixmap getBackground()

  
  public Pixmap getStartButton()
  { // GetStartButton method for the Assets class. This method returns the start button pixmap.
  	return this.startButton;
  } // public Pixmap getStartButton()

  
  public Pixmap getPlayerBullet()
  { // GetPlayerBullet method for the Assets class. This method returns the player bullet pixmap.
  	return this.playerBullet;
  } // public Pixmap getPlayerBullet() 

  
  public Pixmap getInvaderBullet()
  { // GetInvaderBullet method for the Assets class. This method returns the invader bullet pixmap.
  	return this.invaderBullet;
  } // public Pixmap getInvaderBullet()   

  
  public Pixmap getResumeButton() 
  { // GetResumeButton method for the Assets class. This method returns the resume button pixmap.
  	return this.resumeButton;
  } // public Pixmap getResumeButton()

  
  public Pixmap getExplosion()
  { // GetExplosion method for the Assets class. This method returns the explosion pixmap.
  	return this.explosion;
  } // public Pixmap getExplosion() 

  
  public Pixmap getObstacleSpr()
  { // GetObstacleSpr method for the Assets class. This method returns the obstacle spr pixmap.
  	return this.obstacleSpr;
  } //  public Pixmap getObstacleSpr()

  
  public Pixmap getObstacleCornerSpr()
  { // GetObstacleCornerSpr method for the Assets class. This method returns the obstacle corner spr pixmap.
  	return this.obstacleCornerSpr;
  } // public Pixmap getObstacleCornerSpr()

  
  public Pixmap getObstacleCornerRSpr()
  { // GetObstacleCornerRSpr method for the Assets class. This method returns the obstacle corner R Spr pixmap.
  	return this.obstacleCornerRSpr;
  } // public Pixmap getObstacleCornerRSpr()

  
  public Pixmap getNumberSpr()
  { // GetNumberSpr method for the Assets class. This method returns the Number Spr pixmap.
  	return this.numberSpr;
  } // public Pixmap getNumberSpr()

  
  public Pixmap getScoreSpr()
  { // GetScoreSpr method for the Assets class. This method returns the Score Spr pixmap.
    return this.scoreSpr;
  } // public Pixmap getScoreSpr()

  
  public Pixmap getLivesSpr()
  { // GetLivesSpr method for the Assets class. This method returns the Lives Spr pixmap.
  	return this.livesSpr;
  } // public Pixmap getLivesSpr()

  
  public Pixmap getTitleImage()
  { // GetTitleImage method for the Assets class. This method returns the Title Image pixmap.
  	return this.titleImage;
  } // public Pixmap getTitleImage()

  
  public Pixmap getOptionsButton()
  { // getOptionsButton method for the Assets class. This method returns the Options button Image pixmap.
  	return this.optionsButton;
  } // public Pixmap getButtonImage()
  
  
  public Pixmap getMainMenuButton()
  { // getMainMenuButton method for the Assets class. This method returns the Menu menu button Image pixmap.
    return this.mainMenuButton;
  } // public Pixmap getMainMenuButton()

  
  public Pixmap getTouchButton()
  { // GetTouchButton method for the Assets class. This method returns the Touch Button pixmap.
  	return this.touchButton;
  } // public Pixmap accelerometerButton()

  
  public Pixmap getBackButton()
  { // GetBackButton method for the Assets class. This method returns the Back Button pixmap.
  	return this.backButton;
  } // public Pixmap backButton()

  
  public Pixmap getCross()
  { // GetCross method for the Assets class. This method returns the Cross pixmap.
  	return this.cross;
  } // public Pixmap cross()

  
  public Pixmap getTick()
  { // GetTick method for the Assets class. This method returns the Tick pixmap.
  	return this.tick;
  } // public Pixmap tick()

  
  public Pixmap getBackground()
  { // GetBackground method for the Assets class. This method returns the background pixmap.
  	return this.background;
  } // public Pixmap getBackground() 

  
  public Pixmap getTopBar()
  { // GetTopBar method for the Assets class. This method returns the top bar pixmap.
  	return this.topBar;
  } // public Pixmap getTopBar()

  
  public Pixmap getPlayerExplosion()
  { // GetPlayerExplosion method for the Assets class. This method returns the player explosion pixmap.
  	return this.playerExplosion;
  } // public Pixmap getPlayerExplosion() 

  
  public Pixmap getMembrane()
  { // GetMembrane method for the Assets class. This method returns the membrane pixmap.
  	return this.membrane;
  } // public Pixmap getMembrane()

  
  public Pixmap getGameOver()
  { // GetGameOver method for the Assets class. This method returns the game over pixmap.
  	return this.gameOver;
  } // public Pixmap getGameOver()

  
  public Pixmap getPauseLabel()
  { // GetPauseLabel method for the Assets class. This method returns the pause label pixmap.
  	return this.pauseLabel;
  } // public Pixmap getPauseLabel()


  public Pixmap getMothership()
  {
    return this.mothership;
  } // public Pixmap getMothership()
  
  
  public Pixmap getHighScoreButton()
  {
    return this.highScoreButton;
  } // public Pixmap getHighScoreButton()
  
  
  public Pixmap getHighScoreTitle()
  {
    return this.highScoreTitle;
  } // public Pixmap getHighScoreTitle()
  
  
  public Sound getClick()
  { // GetClick method for the Assets class. This method returns the click sound.
  	return this.click;
  } // public Sound getClick() 

  
  public Sound getInvaderMove()
  { // GetInvaderMove method for the Assets class. This method returns the invader move sound.
  	return this.invaderMove;
  } // public Sound getInvaderMove() 

  
  public Sound getInvaderDeath()
  { // GetInvaderDeath method for the Assets class. This method returns the invader death sound.
  	return this.invaderDeath;
  } // public Sound getInvaderDeath() 

  
  public Sound getPlayerDeath()
  { // GetPlayerDeath method for the Assets class. This method returns the player death sound.
  	return this.playerDeath;
  } // public Sound getPlayerDeath() 

  
  public Sound getShoot()
  { // GetShoot method for the Assets class. This method returns the shoot sound.
  	return this.shoot;
  } // public Sound getShoot() 
  
    
  public void setPlayer(Pixmap _image)
  { // SetPlayer method for the Assets class. This method sets the player pixmap.
  	this.player = _image;
  } // public void setPlayer(Pixmap _image)

  
  public void setInvader(int _type, Pixmap _image)
  { // SetInvader method for the Assets class. This method sets the invader pixmap.
  	this.invader[_type] = _image;
  } // public void setBackground(Pixmap _image)

  
  public void setStartButton(Pixmap _image)
  { // SetStartButton method for the Assets class. This method sets the start button pixmap.
  	this.startButton = _image;
  } // public void setStartButton(Pixmap _image)

  
  public void setResumeButton(Pixmap _image)
  { // SetResumeButton method for the Assets class. This method sets the resume button pixmap.
  	this.resumeButton = _image;
  } // public void setResumeButton(Pixmap _image)

  
  public void setPlayerBullet(Pixmap _image)
  { // SetPlayerBullet method for the Assets class. This method sets the player bullet pixmap.
  	this.playerBullet = _image;
  } // public void setPlayerBullet(Pixmap _image)

  
  public void setObstacleSpr(Pixmap _image)
  { // SetObstacleSpr method for the Assets class. This method sets the Obstacle Spr pixmap.
  	this.obstacleSpr = _image;
  } // public void setObstacleSpr(Pixmap _image)

  
  public void setObstacleCornerSpr(Pixmap _image)
  { // SetObstacleCornerSpr method for the Assets class. This method sets the Obstacle Corner Spr pixmap.
  	this.obstacleCornerSpr = _image;
  } // setObstacleCornerSpr(Pixmap _image)

  
  public void setObstacleCornerRSpr(Pixmap _image)
  { // setObstacleCornerRSpr method for the Assets class. This method sets the Obstacle Corner R Spr pixmap.
  	this.obstacleCornerRSpr = _image;
  } // public void setObstacleCornerRSpr(Pixmap _image)

  
  public void setTitleImage(Pixmap _image)
  { // setTitleImage method for the Assets class. This method sets the Title Image pixmap.
  	this.titleImage = _image;
  } // public void setTitleImage(Pixmap _image) 

  
  public void setInvaderBullet(Pixmap _image)
  { // setInvaderBullet method for the Assets class. This method sets the Invader Bullet pixmap.
  	this.invaderBullet = _image;
  } // public void setInvaderBullet(Pixmap _image)  

  
  public void setNumberSpr(Pixmap _image)
  { // setNumberSpr method for the Assets class. This method sets the Number Sprite pixmap.
  	this.numberSpr = _image;
  } // public void setNumberSpr(Pixmap _image)

  
  public void setExplosion(Pixmap _image)
  { // setExplosion method for the Assets class. This method sets the Explosion pixmap.
  	this.explosion = _image;
  } // public void setExplosion(Pixmap _image)  

  
  public void setOptionsButton(Pixmap _image)
  { // setOptions method for the Assets class. This method sets the Options pixmap.
  	this.optionsButton = _image;
  } // public void setOptions(Pixmap _image)
  
  
  public void setMainMenuButton(Pixmap _image)
  { // setMainMenuButton method to set the main menu button pixmap.
    this.mainMenuButton = _image;
  } // public void setMainMenuButton(Pixmap _image)

  
  public void setTouchButton(Pixmap _image)
  { // setTouchButton method for the Assets class. This method sets the Touch Button pixmap.
  	this.touchButton = _image;
  } // public void setTouchButton(Pixmap _image)

  
  public void setBackButton(Pixmap _image)
  { // setBackButton method for the Assets class. This method sets the Back Button pixmap.
  	this.backButton = _image;
  } // public Pixmap backButton()

  
  public void setCross(Pixmap _image)
  { // setCross method for the Assets class. This method sets the Cross pixmap.
  	this.cross = _image;
  } // public Pixmap cross()

  
  public void setTick(Pixmap _image)
  { // setTick method for the Assets class. This method sets the tick pixmap.
  	this.tick = _image;
  } // public Pixmap tick()

  
  public void setBackground(Pixmap _image)
  { // setBackground method for the Assets class. This method sets the background pixmap.
  	this.background = _image;
  } // public void setPixmap(Pixmap _image)

  
  public void setScoreSpr(Pixmap _image)
  { // setScoreSpr method for the Assets class. This method sets the Score Spr pixmap.
  	this.scoreSpr = _image;
  } // public void setScoreSpr(Pixmap _image)

  
  public void setLivesSpr(Pixmap _image)
  { // setLivesSpr method for the Assets class. This method sets the Lives Spr pixmap.
  	this.livesSpr = _image;
  } //  public void setLivesSpr(Pixmap _image)

  
  public void setTopBar(Pixmap _image)
  { // setTopBar method for the Assets class. This method sets the Top Bar pixmap.
  	this.topBar = _image;
  } // public void setTopBar(Pixmap _image)

  
  public void setPlayerExplosion(Pixmap _image)
  { // setPlayerExplosion method for the Assets class. This method sets the Player Explosion pixmap.
  	this.playerExplosion = _image;
  } // public void setPlayerExplosion(Pixmap _image)

  
  public void setMembrane(Pixmap _image)
  { // setMembrane method for the Assets class. This method sets the Membrane pixmap.
  	this.membrane = _image;
  } // public void setMembrane(Pixmap _image)

  
  public void setGameOver(Pixmap _image)
  { // setGameOver method for the Assets class. This method sets the Game Over pixmap.
  	this.gameOver = _image;
  } // public void setGameOver(Pixmap _image)

  
  public void setPausedTitle(Pixmap _image)
  { // setPausedTitle method for the Assets class. This method sets the Paused Title pixmap.
  	this.pauseLabel = _image;
  } // public void setPausedTitle(Pixmap _image)


  public void setMothership(Pixmap _image)
  {
    this.mothership = _image;
  } // public void setMothership(Pixmap _image)

  
  public void setHighScoreButton(Pixmap _image)
  {
    this.highScoreButton = _image;
  } // public void setHighScoreButton(Pixmap _image)
  
  
  public void setHighScoreTitle(Pixmap _image)
  {
    this.highScoreTitle = _image;
  } // public void setHighScoreButton(Pixmap _image)
  
  
  public void setClick(Sound _sound)
  { // setClick method for the Assets class. This method sets the Click sound.
  	this.click = _sound;
  } // public void setClick(Sound _sound)

  
  public void setInvaderMove(Sound _sound)
  { // setInvaderMove method for the Assets class. This method sets the Invader Move sound.
  	this.invaderMove = _sound;
  } // public void setInvaderMove(Sound _sound)

  
  public void setInvaderDeath(Sound _sound)
  { // setInvaderDeath method for the Assets class. This method sets the Invader death sound.
  	this.invaderDeath = _sound;
  } // public void setInvaderDeath(Sound _sound)

  
  public void setPlayerDeath(Sound _sound)
  { // setPlayerDeath method for the Assets class. This method sets the player death sound.
  	this.playerDeath = _sound;
  } // public void setPlayerDeath(Sound _sound)

  
  public void setShoot(Sound _sound)
  { // setShoot method for the Assets class. This method sets the Shoot sound.
  	this.shoot = _sound;
  } // public void setShoot(Sound _sound)
  
} // public class Assets
