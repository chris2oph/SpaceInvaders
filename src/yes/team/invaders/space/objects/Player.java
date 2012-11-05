// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: Player.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.invaders.space.objects;

import yes.team.framework.Game;
import yes.team.framework.Graphics;
import yes.team.invaders.space.Assets;
import yes.team.invaders.space.Lives;
import yes.team.framework.Pixmap;


public class Player extends GameObject
{
  // Objects and variables.
  Projectile    projectile;
  static private final float PROJECTILE_SPEED = 4.5f;
  private float deadTimer;
  private float respawnTimer;
  private float invunerableTimer;
  private Lives lives;
  
  
  public Player(Game _game)
  { // Constructor for the player class to set the objects and attributes of the player.
    super(_game);
    
    // Set the sprite, width and height of the player.
    this.sprite = Assets.getInstance().getPlayer();
    this.width = this.getSprite().getWidth();
    this.height = this.getSprite().getHeight();
    
    // Set the starting position of a player.
    this.xPosition = _game.getGraphics().getWidth()/2-this.getSprite().getWidth()/2;
    this.yPosition = _game.getGraphics().getHeight()-this.getSprite().getHeight();
   
    // Update the collision box.
    this.updateCollisionBox();
    
    // Initialise the variables and objects.
    this.projectile   = new Projectile(_game);
    this.deadTimer    = 0;
    this.respawnTimer = 0;
    this.invunerableTimer  = 0;
    this.lives        = Lives.getInstance();
  } // public Player()
  
  
  @Override 
  public void initialise() 
  { // Initialises the player, sets the player to active and sets the player sprite.
    this.sprite = Assets.getInstance().getPlayer();
    this.active = true;
    
    // Set the starting position of a player.
    this.xPosition = this.getGame().getGraphics().getWidth()/2-this.getSprite().getWidth()/2;
    this.yPosition = this.getGame().getGraphics().getHeight()-this.getSprite().getHeight();
   
    // Update the players collision box.
    this.updateCollisionBox();
    
    // Set the timers.
    this.deadTimer    = 0;
    this.respawnTimer = 0;
    
  } // public void initialise()
  
  
  @Override
  public void update(float _deltaTime)
  { // Updates the game object with regard to the deltaTime.
    
    // Determine that the object is active.
    if(this.active)
    {
      
      if(this.invunerableTimer > 0)
      { // Minus the invunerableTimer.
        this.invunerableTimer -= _deltaTime;
      } // end if
      
      // Call the out of screen check and handle.
      this.checkAndHandleOutOfScreen();
      
      // Update the collisonBox of the object.
      this.updateCollisionBox();
      
    } // end if
    else if(this.deadTimer > 0)
    {
      // Update the dead timer times.
      this.deadTimer -= _deltaTime;
      
      if(this.deadTimer <= 0)
      {
        // Begin respawn.
        this.respawnTimer = 0.5f;
      } // if(this.deadTimer <= 0)
      
      
    } // end else
    else if(this.respawnTimer > 0)
    {
      // Update the respawn timer time.
      this.respawnTimer -= _deltaTime;
      
      if(this.respawnTimer <= 0)
      {
        this.invunerableTimer = 1.0f;
        this.initialise();
      } // end if
    } // end else if(this.respawnTimer > 0)
    
    // Update the players projectile.
    this.projectile.update(_deltaTime);
    
  } // public void update(float _deltaTime)

  
  @Override
  public void move(float _deltaTime)
  { // Moves the object by its currently velocity.
    this.xPosition = (int)(this.xPosition + this.xVelocity);
    this.yPosition = (int)(this.yPosition + this.yVelocity);
  } // public void move(float _deltaTime)
  
  
  @Override
  public void render()
  { // Renders the player object to the screen.
    Graphics g = this.game.getGraphics();
    
    // Render the sprite if it exists and the player is not respawning.
    if(this.sprite != null && this.respawnTimer <= 0)
    {
      if(this.invunerableTimer > 0.0f)
      {
        // Cause the image to flash when invulnerable.
        int remainder = (int)(this.invunerableTimer*10)%2;
        // Image flashes every .2 of a second.
        if(remainder == 0)
        {
          g.drawPixmap(this.sprite, this.xPosition, this.yPosition);
        } // end if
      } // end if
      else
      {
        g.drawPixmap(this.sprite, this.xPosition, this.yPosition);
      } // end else 
    } // end if
    
    // Render the projectile.
    this.projectile.render();
  } // public void render(Game _game)
  
  
  @Override
  public void takeHit()
  { // Overriden method to handle a hit being taken.
    if(this.invunerableTimer <= 0)
    { // Makes sure that the player is not currently invicible
      // and sets the attributes accordingly.
      this.setSprite(Assets.getInstance().getPlayerExplosion());
      Assets.getInstance().getPlayerDeath().play(2.2f);
      this.active = false;
      this.lives.RemoveLife();
      this.deadTimer = 0.5f;     
    } // end if
  } // public void takeHit()
  
  
  public void fire()
  { // Method to fire a projectile from the current enemy position.
    // Sets the projectile object to active and sets the velocity of the projectile.
    Pixmap objectSprite = Assets.getInstance().getPlayerBullet();
    this.projectile.fire(this.xPosition+this.width/2-objectSprite.getWidth()/2, 
                         this.yPosition-objectSprite.getHeight(),
                         0.0f, -PROJECTILE_SPEED, objectSprite);
  } // public void fire
  
  
  public Projectile getProjectile()
  {
    return this.projectile;
  } // public Projectile getProjectile()
  
} // public class Player
