// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: GameScreen.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//     Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space;

// Import the files required for the class.
import java.util.ArrayList;
import java.util.List;

import yes.team.framework.Game;
import yes.team.framework.Graphics;
import yes.team.framework.Input.TouchEvent;
import yes.team.framework.Screen;
import yes.team.invaders.space.objects.GameButton;
import yes.team.invaders.space.objects.GameObject;
import yes.team.invaders.space.objects.Invader;
import yes.team.invaders.space.objects.Mothership;
import yes.team.invaders.space.objects.Obstacle;
import yes.team.invaders.space.objects.Player;
import android.graphics.Color;

// Definition and implementation of the GameScreen class.
public class GameScreen extends Screen
{
	// Definitions of the variables for the GameScreen including touch position,
	// touch timer, enemy and invader lists, max speed, obstacles, score, lives
	// and the pause button.
  private Player thePlayer;
  boolean touchBegan     = false;
  int startTouchPosX     = 0;
  float startAccelPosX   = 0;
  private static final double TOUCH_TIMER  = 0.2;
  double currentTouchTimer = 0;
  List<GameObject> playerEnemies   = new ArrayList<GameObject>();
  List<GameObject> invadersEnemies = new ArrayList<GameObject>();
  private static final float MAX_SPEED   = 7;
  private InvaderManager invaderManager;
  private Mothership mothership;
  private Obstacle obsLeft;
  private Obstacle obsRight;
  private Score score;
  private Lives lives;
  private GameButton pauseButton;
  
  // Constructor for the GameScreen class.
  public GameScreen(Game _game) 
  {
  	// Call the superclass constructor.
	  super(_game);
	  
	  // Create the player.
	  this.thePlayer = new Player(_game);
	  this.thePlayer.setActive(true);
	  
	  // Create the invader manager.
	  this.invaderManager = InvaderManager.getInstance();
	  this.invaderManager.initialise(_game);

    this.mothership = Mothership.getInstance();
    this.mothership.initialise(_game);
	  
	  // Create the obstacles.
	  this.obsLeft = new Obstacle(_game);
	  this.obsRight = new Obstacle(_game);
	  
	  // Set the obstacle positions.
	  int obsYPos = (_game.getGraphics().getHeight() / 4) * 3;
	  int obsLXPos = (_game.getGraphics().getWidth() / 6) * 1;
	  int obsRXPos = (_game.getGraphics().getWidth() / 6) * 4;
	
	  this.obsLeft.setXPosition(obsLXPos);
	  this.obsLeft.setYPosition(obsYPos);
	  this.obsRight.setXPosition(obsRXPos);
	  this.obsRight.setYPosition(obsYPos);
	  
	  // Call initialise to setup the blocks.
	  this.obsLeft.initialise();
	  this.obsRight.initialise();
	  
	  // Initialise the score and lives.
	  this.score = Score.getInstance();
	  this.score.initialise(_game);
	  this.lives = Lives.getInstance();
	  this.lives.initialise(_game);
	  
	  // Initialise the pause GameButton details.
	  this.pauseButton = new GameButton(_game);
	  this.pauseButton.setSprite(Assets.getInstance().getTopBar());
	  this.pauseButton.setXPosition(0);
	  this.pauseButton.setYPosition(0);
	  this.pauseButton.updateCollisionBox();
	  
	  // Loop through the enemies and add them to the playerEnemies list.
	  this.invadersEnemies.add(this.thePlayer);
	  this.playerEnemies.add(this.mothership);
	  
	  List<Invader> invaderList = this.invaderManager.getInvadersList();
	  int length = invaderList.size();
	  
	  for(int i=0;i<length;i++)
	  {
	    this.playerEnemies.add(invaderList.get(i));
	  } // end for
	  
	  // Adds all of the blocks from the obstacles to the game object list.
	 
	  int lengthRow = this.obsLeft.getNumBlocksH();
	  int lengthCol = this.obsLeft.getNumBlocksW();
	  
	  for(int i =0;i<lengthCol;i++)
	  {
	    for(int u=0;u<lengthRow;u++)
	    {
		    this.playerEnemies.add(this.obsLeft.blocks[i][u]);
		    this.playerEnemies.add(this.obsRight.blocks[i][u]);
		    this.invadersEnemies.add(this.obsLeft.blocks[i][u]);
		    this.invadersEnemies.add(this.obsRight.blocks[i][u]);
	    } // end for
	  } // end for	  
	  
	  this.mothership.reset();

  } // public GameScreen(Game _game)

  
  @Override
  public void update(float _deltaTime) 
  { // Update method for the GameScreen class. This method updates the screen based on
    // the player input etc.
    
    // Handle any inputs.
    this.handleInput();
  
    // If there was a touch, increment the touch timer.
    if(this.touchBegan)
    {
      this.currentTouchTimer -= _deltaTime;
    } // end if
  
    // Update the player.
    this.thePlayer.update(_deltaTime);
  
    // Update the projectile.
    this.thePlayer.getProjectile().collisionDetection(this.playerEnemies);
  
    // Update the invaders.
    this.invaderManager.update(_deltaTime, this.invadersEnemies); 
    this.mothership.update(_deltaTime);
  } // public void update(float _deltaTime)
  
   
  public void handleInput()
  { // HandleInput method for the GameScreen class. This method handles 
    // any inputs.
    
	  // Handle touch for our scene.
	  List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	  int length = touchEvents.size();
	  
	  // Loop through all of the events.
	  for(int i=0;i<length;i++)
	  {
	    TouchEvent event = touchEvents.get(i);
	    if(event.type == TouchEvent.TOUCH_DOWN)
	    {
		    if(!this.touchBegan)
		    {
		      this.currentTouchTimer = GameScreen.TOUCH_TIMER;
		      this.touchBegan = true;
		      this.startTouchPosX = event.x;
		    } // end if
	    } // end if
	
	    if(Settings.getInstance().getTouchControlOn())
	    {
	      this.handleTouch(event);
	    } // end if 
	     
	    if(event.type == TouchEvent.TOUCH_UP)
	    {
	      if(this.pauseButton.inBounds(event))
	      {
		      this.touchBegan = false;
		      this.game.pushScreen(this);
		      this.game.setScreen(new PauseScreen(this.game));
	      } // end if
	    
	      if(this.touchBegan)
	      {
		      if(this.thePlayer.getActive())
		      {
		        if(this.currentTouchTimer > 0)
		        {
		          this.thePlayer.fire();
		        } // end if
		      } // end if
	        this.touchBegan = false;
	       } // end if  
	    } // end if	    
	  } // end for
	  	  
	  if(!Settings.getInstance().getTouchControlOn())
	  {
	    this.handleAccelerometer();
	  } // end if
  } // public void handleInput()
  
  
  public void handleTouch(TouchEvent _event)
  { // HandleTouch method for the GameScreen class.
    
  	// If the player uses a drag touch event.
	  if(_event.type == TouchEvent.TOUCH_DRAGGED)
	  {
	  	// Update the player based on the touch event.
	    if(this.thePlayer.getActive())
	    {
		    this.thePlayer.setXVelocity(0);
		    this.thePlayer.setXVelocity(_event.x-this.startTouchPosX);
		    this.thePlayer.move(0);
		    this.startTouchPosX = _event.x;
	    } // end if
	  } // end if
	  
	  if(_event.type == TouchEvent.TOUCH_UP)
	  {
	    this.thePlayer.setXVelocity(0);
	  } // end if	  
  } // public void handleTouch()
  
  
  public void handleAccelerometer()
  { // HandleAccelerometer method for the GameScreen class.
	  float speed = this.startAccelPosX-game.getInput().getAccelX() * 2.5f;
	  
	  // If the speed in greater than the max speed, limit it.
	  if(speed > GameScreen.MAX_SPEED)
	  {
	    speed = GameScreen.MAX_SPEED;
	  } // end if
	  
	  // If the speed is less than the negative max speed, set it to the negative max speed.
	  if(speed < -GameScreen.MAX_SPEED)
	  {
	    speed = -GameScreen.MAX_SPEED;
	  } // end if
	  
	  // If the player is active, the x velocity and move the player.
	  if(this.thePlayer.getActive())
	  {
	    this.thePlayer.setXVelocity(speed);
	    this.thePlayer.move(0);
	  } // end if
  } // public void handleAccelerometer()
  
  
  @Override
  public void present(float deltaTime) 
  { // Present method for the GameScreen class. This method draws the screen.
    
  	// Create a local graphics variable and set it to the game graphics and clear the 
  	// graphics to black.
	  Graphics g = this.game.getGraphics();
	  g.clear(Color.BLACK);
	  
	  // Draw Pixmap for the background.
	  g.drawPixmap(Assets.getInstance().getBackground(), 0, 0);
	  
	  // Render the invaders, player, obstacles, pause button, score and lives.
	  this.invaderManager.render();
	  this.thePlayer.render();
	  this.obsLeft.render();
	  this.obsRight.render();
	  this.pauseButton.render();
	  this.score.render();
	  this.lives.render();
    this.mothership.render();  
  } // public void present(float _deltaTime)

  
  @Override
  public void pause() 
  { // Pause method for the GameScreen class.
    this.game.setLocking(true);
    this.game.pushScreen(this);
    this.game.setScreen(new PauseScreen(game));
  } // public void pause()

  
  @Override
  public void resume() 
  { // Resume method for the GameScreen class.
    
  } // public void resume()

  
  @Override
  public void dispose() 
  { // Dispose method for the GameScreen class.
  
  } // public void dispose()
} // public class GameScreen extends Screen
