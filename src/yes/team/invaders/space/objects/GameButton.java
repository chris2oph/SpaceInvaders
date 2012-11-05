package yes.team.invaders.space.objects;

import yes.team.framework.Game;
import yes.team.framework.Input.TouchEvent;
import yes.team.framework.Pixmap;
import android.graphics.Rect;

public class GameButton extends GameObject
{

  public GameButton(Game _game) 
  {
    super(_game);
    this.active = true;
  } // public GameButton(Game _game) 
  
  public boolean inBounds(TouchEvent _event) 
  { 
    Rect box = this.collisionBox;
    
    if(_event.x > box.left && _event.x < box.right - 1 &&
    _event.y > box.top && _event.y < box.bottom - 1)
    {
      return true;
    } // end if
    else
    {
      return false;
    } // end else
  } // private boolean inBounds(TouchEvent event, int x, int y, int width, int height)
 
  
  @Override
  public void setSprite(Pixmap _sprite)
  {
    super.sprite = _sprite;
    this.width   = _sprite.getWidth();
    this.height  = _sprite.getHeight();
  } // public void setSprite(Pixmap _sprite)
  
} // public class GameButton
