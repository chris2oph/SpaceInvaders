// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: GameObject.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space.objects;

// Import the files required by the class.
import yes.team.framework.Game;
import yes.team.framework.Graphics;
import yes.team.framework.Pixmap;
import android.graphics.Rect;

// Definition and implementation of the GameObject class.
public class GameObject 
{
  // Variables for the positions, width, height, velocity, sprite, alive and collision box
	// for an object.
  protected int     xPosition;
  protected int     yPosition;
  protected int     width;
  protected int     height;
  protected float   xVelocity;
  protected float   yVelocity;
  protected Pixmap  sprite;
  protected boolean active;
  protected Rect    collisionBox;
  protected Game    game;
  
  // Constructor for the GameObject class.
  public GameObject(Game _game)
  {
    // Initialise all variables using the default values.
    this.xPosition = 0;
    this.yPosition = 0;
    this.width     = 0;
    this.height    = 0;
    this.yVelocity = 0;
    this.xVelocity = 0;
    this.sprite    = null;
    this.active    = false;
    this.game      = _game;
    this.collisionBox = new Rect();
    this.collisionBox.left   = this.xPosition;
    this.collisionBox.right  = this.xPosition + this.width;
    this.collisionBox.top    = this.yPosition;
    this.collisionBox.bottom = this.yPosition + this.height; 
  } // public Object
  
  
  public void initialise()
  { // Initialise method for the GameObject class.
    
  } // public void initialise()
  
  
  public void render()
  { // Render method for the gameObject class.
	  // If the object is active, render it.
    if(this.active)
    {
      Graphics g = this.game.getGraphics();
      if(this.sprite != null)
      {
        g.drawPixmap(this.sprite, this.xPosition, this.yPosition);
      } // end if
    } // end if
  } // public void render(Game _game)
  
  
  public void update(float _deltaTime)
  { // Update method for the GameObject class. This method updates an object if it is active.
    if(this.active)
    {
      this.move(_deltaTime);
    } // end if
  } // public void update(double _deltaTime)
  
  
  public void move(float _deltaTime)
  { // Moves the object by the velocity in accordance to 60 frames per second.
    // Uses the deltaTime variable to reinforce the same rate of movement when applied to time.
    this.xPosition += this.xVelocity*60*_deltaTime;
    this.yPosition += this.yVelocity*60*_deltaTime;    
  } // public void move(float _deltaTime)
  
  
  public void checkAndHandleOutOfScreen()
  { // Checks if the object is out of the screen and handles a response accordingly.
    
    // Left of the screen.
    if(this.xPosition < 0)
    {
      this.xPosition = 0;
      this.xVelocity = 0;
    } // end if
    
    // Right of the screen.
    if(this.xPosition > game.getGraphics().getWidth()-this.sprite.getWidth())
    {
      this.xPosition = game.getGraphics().getWidth()-this.sprite.getWidth();
      this.xVelocity = 0;
    } // end if
    
    // Top of the screen.
    if(this.yPosition < 0)
    {
      this.yPosition = 0;
      this.yVelocity = 0;
    } // end if
    
    // Bottom of the screen.
    if(this.yPosition > game.getGraphics().getHeight()-this.sprite.getHeight())
    {
      this.yPosition = game.getGraphics().getHeight()-this.sprite.getHeight();
      this.yVelocity = 0;
    } // end if
  } // public void checkAndHandleOutOfScreen()
  
  
  public void updateCollisionBox()
  { // Updates the collision box of the object according to its position and dimensions.
    // Method can be overwritten if different dimensions are desired.
    this.collisionBox.left   = this.xPosition;
    this.collisionBox.right  = this.xPosition + this.width;
    this.collisionBox.top    = this.yPosition;
    this.collisionBox.bottom = this.yPosition + this.height; 
  } // public void updateCollisionBox()
  
  
  
  public void shutdown()
  { // Shuts down the object.
    
  } // public void shutdown()
  
  
  public void takeHit()
  { // Handled when an object is hit by a projectile.
    this.setActive(false);
  } // public void takeHit()
  
  
  public void setXPosition(int _position)
  { // Sets the x position of the object.
    this.xPosition = _position;
  } // public setXPostion(int _positon)
  
  
  public void setYPosition(int _position)
  { // Sets the y position of the object.
    this.yPosition = _position;
  } // public void setYPosition(int _position)
  
  
  public void setWidth(int _size)
  { // Sets the width of the object.
    this.width = _size;
  } // public void setWidth(int _size)
  
  
  public void setHeight(int _size)
  { // Sets the height of the object.
    this.height = _size;
  } // public void setHeight(int _size)
  
  
  public void setXVelocity(float _velocity)
  { // Sets the x velocity of the object.
    this.xVelocity = _velocity;
  } // public void setXVelocity(double _velocity)
  
  
  public void setYVelocity(float _velocity)
  { // Sets the y velocity of the object.
    this.yVelocity = _velocity;
  } // public void setYVelocity(double _velocity)
  
  
  public void setSprite(Pixmap _sprite)
  { // Sets the sprite of the object.
    this.sprite = _sprite;
  } // public void setSprite(Pixmap _sprite)
  
  
  public void setActive(boolean _active)
  { // Sets the active of the object.
    this.active = _active;
  } // public void setActive(boolean _active)
  
  
  public void setCollisionBox(Rect _box)
  { // Sets the collison box of the object.
    this.collisionBox = _box;
  } // public void setCollisionBox(Rect _box)
  
  
  public int getXPosition()
  { // Returns the x position of the object.
    return this.xPosition;
  } // public void getXPosition()
  
  
  public int getYPosition()
  { // Returns the y position of the object.
    return this.yPosition;
  } // public int getYPosition()
  
  
  public int getWidth()
  { // Returns the width of the object.
    return this.width;
  } // public int getWidth()
  
  
  public int getHeight()
  { // Returns the height of the object.
    return this.height;
  } // public int getHeight()
  
  
  public float getXVelocity()
  { // Returns the x velocity of the object.
    return this.xVelocity;
  } // public double getXVelocity()
  
  
  public float getYVelocity()
  { // Returns the y velocity of the object.
    return this.yVelocity;
  } // public double getYVelocity()
  
  
  public boolean getActive()
  { // Returns the active state of the object.
    return this.active;
  } // public boolean getActive()
  
  
  public Pixmap getSprite()
  { // Returns the objects sprite.
    return this.sprite;
  } // public Pixmap getSprite(Pixmap _sprite)
  
  
  public Game getGame()
  { // returns the game reference.
    return this.game;
  } // public Game getGame() 
  
  
  public Rect getCollisionBox()
  { // Returns the collison box of the object.
    return this.collisionBox;
  } // public Rect getCollisionBox()
  
} // public class GameObject 
