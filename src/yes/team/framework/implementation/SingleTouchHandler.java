// ----------------------------------------------------------------
// Space Invaders.
// SingleTouchHandler.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework.implementation;

import java.util.ArrayList;
import java.util.List;

import yes.team.framework.Input.TouchEvent;
import yes.team.framework.TouchHandler;
import yes.team.framework.implementation.Pool.PoolObjectFactory;
import android.view.MotionEvent;
import android.view.View;

public class SingleTouchHandler implements TouchHandler 
{
  // Boolean to determine if touch is in effect.
  boolean isTouched;
  
  // Hold a variable for the X touch position.
  int touchX;
  int touchY;
  
  // Create a pool for the touch events.
  Pool<TouchEvent> touchEventPool;
  // Create a list of touchevents and a buffer.
  List<TouchEvent> touchEvents = new ArrayList<TouchEvent>(); 
  List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>(); 
  
  // Store a scale of the framebuffer.
  float scaleX;
  float scaleY;
  
  
  public SingleTouchHandler(View _view, float _scaleX, float _scaleY) 
  { 
    // Create 
    PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() 
    {
       @Override
       public TouchEvent createObject()
       {
         return new TouchEvent(); 
       } // public TouchEvent createObject()
    }; // PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() 
    
    
    this.touchEventPool = new Pool<TouchEvent>(factory, 100); 
    _view.setOnTouchListener(this);
    this.scaleX = _scaleX;
    this.scaleY = _scaleY; 
  } // public SingleTouchHandler(View _view, float _scaleX, float _scaleY)
  
  
  @Override
  public boolean onTouch(View _v, MotionEvent _event) 
  {
    synchronized(this)
    {
      TouchEvent touchEvent = touchEventPool.newObject();
      
      switch(_event.getAction())
      {
        case MotionEvent.ACTION_DOWN:
          touchEvent.type = TouchEvent.TOUCH_DOWN;
          this.isTouched = true;
          break;
        case MotionEvent.ACTION_MOVE:
          touchEvent.type = TouchEvent.TOUCH_DRAGGED;
          this.isTouched = true;
          break;
        case MotionEvent.ACTION_CANCEL:
        case MotionEvent.ACTION_UP:
          touchEvent.type = TouchEvent.TOUCH_UP;
          this.isTouched = true;
          break;
          
      } // switch(event.getAction())
      
      touchEvent.x = this.touchX = (int)(_event.getX() * this.scaleX);
      touchEvent.y = this.touchY = (int)(_event.getY() * this.scaleY);
      this.touchEventsBuffer.add(touchEvent);
      
      return true;
      
    } // end synchronized(this)
  } // public boolean onTouch(View _v, MotionEvent _event) 

  
  @Override
  public boolean isTouchDown(int _pointer) 
  { // Boolean to determine if the touch is currently down.
    synchronized(this) 
    { 
      if(_pointer == 0)
      {
        return this.isTouched;
      } // if(pointer == 0)
      else
      {
        return false;
      } // else
    } // end synchronized
    
  } // public boolean isTouchDown(int _pointer)       

  @Override
  public int getTouchX(int _pointer) 
  { // Returns the X position of the touch event.
    synchronized(this) 
    { 
      return this.touchX;
    } // end synchronized 
    
  } // public int getTouchX(int _pointer) 

  
  @Override
  public int getTouchY(int _pointer) 
  { // Returns the Y position of the touch event.
    synchronized(this) 
    { 
      return this.touchY;
    } // synchronized(this)
  } // public int getTouchY(int _pointer)

  
  @Override
  public List<TouchEvent> getTouchEvents() 
  { // Returns the list of touch events.
    
    synchronized(this) 
    { // Get the length of touchEvents.
      int len = this.touchEvents.size(); 
      
      // Free the touch pool.
      for(int i = 0; i < len; i++)
      {
        this.touchEventPool.free(this.touchEvents.get(i)); 
      } // end for
      
      // Add all the touch from the buffer and return them.
      this.touchEvents.clear(); 
      this.touchEvents.addAll(this.touchEventsBuffer);
      
      // Clear all the touch events from the buffer.
      this.touchEventsBuffer.clear();
      return this.touchEvents;
    } // synchronized(this)
  } // public List<TouchEvent> getTouchEvents()

} // public class SingleTouchHandler implements TouchHandler
