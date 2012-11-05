// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: Projectile.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space.objects;

// Import the files required by the class.
import java.util.List;
import yes.team.framework.Game;
import yes.team.framework.Pixmap;
import yes.team.invaders.space.Assets;
import android.graphics.Rect;

// Definition and implementation for the Projectile class.
public class Projectile extends GameObject
{
  // Constructor for the pProjectile class.
  public Projectile(Game _game)
  { // Call the superclass constructor.
    super(_game);
  } // public Projectile(Game _game)
  
  
  public void fire(int _x, int _y, float _xVel, float _yVel, Pixmap _sprite)
  { // Fire method for the Projectile class. This method fires a projectile 
    // from the passed location with the specified x and y velocity and the passed
    // sprite.
	  // If the projectile is not already active, set the variables using 
	  // the passed in values.
    if(!this.getActive())
    {
      Assets.getInstance().getShoot().play(1.0f);
      this.xPosition = _x;
      this.yPosition = _y;
      this.width     = _sprite.getWidth();
      this.height    = _sprite.getHeight();
      this.xVelocity = _xVel;
      this.yVelocity = _yVel;
      this.sprite    = _sprite;
      this.active    = true;
    } // end if
  } // public void fire(int _x, int _y, Pixmap _sprite)
  
  
  public void collisionDetection(List<GameObject> _objects)
  { // CollisionDetection method for the Projectile class. This method checks to 
    // see if the projectile has collided with any of the objects in the list which
    // is passed in.
    
	  // If the projectile is active, loop through the list and check for collisions.
    if(this.active)
    {
      int length = _objects.size();
      GameObject object = null;
      for(int i=0;i<length;i++)
      {
        object = _objects.get(i);
        if(object.active)
        {
          if(this.checkCollision(object))
          {
            this.active = false;
            object.takeHit();   
            break;
          } // end if
        } // end if
      } // end for
    } // end if
  } // public boolean collisionDetection(List<GameObject> _objects)
  
  
  public boolean checkCollision(GameObject _object)
  { // CheckCollision method for the Projectile class. This method checks for a collision
    // between the projectile and a single object.
    Rect enemyCollisionBox = _object.getCollisionBox();
    
    // Check for a collision between the projectile and the object which
    // was passed in.
    if(this.xPosition > enemyCollisionBox.right)
    {
      return false;
    } // end if
    else if(this.xPosition + this.width < enemyCollisionBox.left)
    {
      return false;
    } // end else if
    else if(this.yPosition > enemyCollisionBox.bottom)
    {
      return false;
    } // end else if
    else if(this.yPosition + this.height < enemyCollisionBox.top)
    {
      return false;
    } // end else if
    
    return true;
  } // public boolean checkCollision(Object _object)
  
  
  @Override
  public void update(float _deltaTime)
  { // Update method for the Projectile method. This method updates the projectile.
    // Call the superclass update method.
    super.update(_deltaTime);
    
    // Check if the projectile is out of the screen.
    this.checkAndHandleOutOfScreen();
  } // public void update(float _deltaTime)
  
  
  @Override
  public void checkAndHandleOutOfScreen()
  { // CheckAndHandleOutOfScreen method for the Projectile class. This method resets the 
    // projectile if it goes off the screen.
    
	  // Check for the projectile being off the screen.
    if(this.yPosition + this.height < 0)
    {
      this.active = false;
    } // end if
    else if(this.yPosition > game.getGraphics().getHeight())
    {
      this.active = false;
    } // end else if
  } // public void checkAndHandleOutOfScreen()
} // public class Projectile extends Object
