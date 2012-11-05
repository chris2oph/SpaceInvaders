// ----------------------------------------------------------------
// Space Invaders.
// AndroidInput.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework.implementation;

import java.util.List;

import yes.team.framework.Input;
import yes.team.framework.TouchHandler;
import android.content.Context;
import android.view.View;

public class AndroidInput implements Input 
{
  // Declare the input handlers. 
	AccelerometerHandler accelHandler;
	TouchHandler touchHandler;
	
	public AndroidInput(Context _context, View _view, float _scaleX, float _scaleY) 
	{ 
	  this.accelHandler = new AccelerometerHandler(_context);
	  this.touchHandler = new SingleTouchHandler(_view, _scaleX, _scaleY);
	} // public AndroidInput(Context context, View view, float scaleX, float scaleY)
	
	@Override
	public boolean isTouchDown(int _pointer) 
	{ // Determines if the touch is currently down. returns true for is and false for not.
		return this.touchHandler.isTouchDown(_pointer);
	} // public boolean isTouchDown(int pointer)
	
	@Override
	public int getTouchX(int _pointer) 
	{ // Returns the x position of the touch passed in.
		return this.touchHandler.getTouchX(_pointer);
	} // public int getTouchX(int pointer) 
	
	@Override
	public int getTouchY(int _pointer)
	{ // Returns the y position of the touch passed in.
		return this.touchHandler.getTouchY(_pointer);
	} // public int getTouchY(int pointer)
	
	@Override
	public float getAccelX() 
	{ // Returns the accelerometer X value.
		return this.accelHandler.getAccelX();
	} // public float getAccelX()
	
	@Override
	public float getAccelY() 
	{ // Returns the accelerometer Y value.
		return this.accelHandler.getAccelY();
	} // public float getAccelY() 
	
	@Override
	public float getAccelZ() 
	{ // Returns the accelerometer Z value.
		return this.accelHandler.getAccelZ();
	} // public float getAccelZ() 
	
	@Override
	public List<TouchEvent> getTouchEvents()
	{ // Returns a list of all the touch events to have occured.
		return this.touchHandler.getTouchEvents();
	} // public List<TouchEvent> getTouchEvents()

} // public class AndroidInput implements Input
