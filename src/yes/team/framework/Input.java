// ----------------------------------------------------------------
// Space Invaders.
// Input.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework;

import java.util.List;

public interface Input 
{ 
  // Creates a static class determining different TouchEvents.
  public static class TouchEvent
  {
    public static final int TOUCH_DOWN    = 0;
    public static final int TOUCH_UP      = 1;
    public static final int TOUCH_DRAGGED = 2;
    
    public int type;
    public int x, y;
    public int pointer;
  } // public static class TouchEvent
  
  // Determines if the touch is currently down. returns true for is and false for not.
  public boolean isTouchDown(int _pointer);
  
  // Returns the x position of the touch passed in.
  public int getTouchX(int _pointer);
  
  // Returns the y position of the touch passed in.
  public int getTouchY(int _pointer);
  
  // Returns the accelerometer X value.
  public float getAccelX();
  
  // Returns the accelerometer Y value.
  public float getAccelY();
  
  // Returns the accelerometer Z value.
  public float getAccelZ();
  
  // Returns a list of all the touch events to have occured.
  public List<TouchEvent> getTouchEvents();
  
} // public interface Input
 