// ----------------------------------------------------------------
// Space Invaders.
// Audio.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework;

import java.util.List;

import android.view.View.OnTouchListener;

import yes.team.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener 
{
  // Boolean to determine if the touch is currently down.
  public boolean isTouchDown(int pointer);
  
  // Returns the X position of the touch event.
  public int getTouchX(int pointer);
  
  // Returns the Y position of the touch event.
  public int getTouchY(int pointer);
  
  // Returns the list of touch events.
  public List<TouchEvent> getTouchEvents();
  
} // public interface TouchHandler extends OnTouchListener
 