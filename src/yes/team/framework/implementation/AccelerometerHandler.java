// ----------------------------------------------------------------
// Space Invaders.
// AccelerometerHandler.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework.implementation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerHandler implements SensorEventListener 
{
  // Three floats the store the 3 dimensions of accelerometer movement.
  float accelX;    
  float accelY;
  float accelZ;
  
  
  public AccelerometerHandler(Context _context)
  {
    // Create a new sensor manager and apply the current context to it.
    SensorManager manager = (SensorManager)_context.getSystemService(Context.SENSOR_SERVICE);
    
    if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0)
    { // Get the accelerometer sensor.
      Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
      manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    } // end if
    
  } // public AccelerometerHandler(Context context)
  
  
  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) 
  { // nothing to do here.
    
  } // public void onAccuracyChanged(Sensor sensor, int accuracy)

  
  @Override
  public void onSensorChanged(SensorEvent event) 
  { // Add the new values of the accelerometer when the values change.
    this.accelX = event.values[0];
    this.accelY = event.values[1];
    this.accelZ = event.values[2];
  } // public void onSensorChanged(SensorEvent event)
  
  
  public float getAccelX()
  { // Returns the x value of the accelerometer.
    return this.accelX;
  } // public float getAccelX()
  
  
  public float getAccelY()
  { // Returns the y accelerometer value.
    return this.accelY;
  } // public float getAccelY()
  
  
  public float getAccelZ()
  { // Returns the z value of the accelerometer.
    return this.accelZ;
  } // public float getAccelZ()
  
} // public class AccelerometerHandler implements SensorEventListener
