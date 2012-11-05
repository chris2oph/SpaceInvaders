// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: Settings.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space;

// Import the required files for the class.
import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.IOException;
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter;
import android.util.Log;
import yes.team.framework.FileIO;

// Definition and declaration of the Settings 
// class. This class saves the settings of the
// game such as if the user has selected the 
// use of the accelerometer or touch for 
// player movement.
public class Settings 
{
  
  // Singleton instance of the Settings class.
  private static Settings instance = null; 
  
  // Boolean to determine if the player is using 
  // touch control.
  private boolean touchControlOn = true;
  
  
  private Settings()
  { // Constructor for the Settings class.
    // No code required.
  } // Private AssetManager().
  
  
  public static synchronized Settings getInstance()
  { // GetInstance method which returns a new instance 
    // of the Settings class if one does not already 
    // exist.
    if(instance == null)
    {
      instance = new Settings();
    }
    return instance;
  } // public static synchronized AssetManager getInstance()
  
  
  public void load(FileIO _files) 
  { // Load method for the Settings class which loads
    // a file from memory for the stored game settings.
    BufferedReader in = null;
    
    // Check for the settings file
    try 
    {
      // If the file is found, use the value defined in the file 
      // for the control type.
      in = new BufferedReader(new InputStreamReader(_files.readFileExternal(".spaceinvadersSettings")));
      this.touchControlOn = Boolean.parseBoolean(in.readLine()); 
    } // end try
    catch(IOException _e) 
    {
      
      Log.d("EN0618", _e.getMessage());
      try
      {
        // Use defaults.
        in = new BufferedReader(new InputStreamReader(_files.readFileInternal(".spaceinvadersSettings")));
        this.touchControlOn = Boolean.parseBoolean(in.readLine());
      } // end trye
      catch(IOException _e2)
      {
        // Use defaults.
        Log.d("EN0618", _e2.getMessage());
      } // end catch
      
    } // end catch
    catch(NumberFormatException _e) 
    {
      // Use defaults.
    } // end catch
    finally 
    { 
      try 
      {
        if(in != null)
        {
          // Close the settings file.
          in.close();
        } // end if
      } // end try
      catch(IOException e) 
      {
        
      } // end catch
    } // end finally
  } // public void load(FileIO _files)
  
  
  public void save(FileIO _files) 
  { // Save method for the Settings class. This method saves
    // the settings to memory for future use by the game.
    
    BufferedWriter out = null;
   
    // Check for the file for the settings.
    try 
    {
      // If the file is present, overwrite the current values
      // with the values selected in game.
      out = new BufferedWriter(new OutputStreamWriter(_files.writeFileExternal(".spaceinvadersSettings")));
      out.write(Boolean.toString(this.touchControlOn)); 
    } // end try
    catch(IOException _e) 
    { 
      Log.d("EN0618", _e.getMessage());
      try
      {
        // Use defaults.
        out = new BufferedWriter(new OutputStreamWriter(_files.writeFileInternal(".spaceinvadersSettings")));
        out.write(Boolean.toString(this.touchControlOn)); ;
      } // end try
      catch(IOException _e2)
      {
        // Use defaults.
        Log.d("EN0618", _e2.getMessage());
      } // end catch
      
    } // end catch
    finally 
    {
      try 
      {
        if (out != null)
        {
          // Close the file.
          out.close();
        } // end if
      } // end try
      catch (IOException _e) 
      {
      
      } // end catch
    } // end finally
  } // public void save(FileIO _files) 
  
  
  public void setTouchControlOn(boolean _on)
  { // setTouchControlOn method for the Settings class. This method
    // sets the game to use touch control instead of the 
    // accelerometer.
    this.touchControlOn = _on;
  } // public void setTouchControlOn(boolean _on)
  
  
  public boolean getTouchControlOn()
  { // getToughControlOn method for the Settings class. This method
    // returns the value of touchControlOn (true for touch control,
    // false for accelerometer).
    return this.touchControlOn;
  } // public boolean getTouchControlOn(boolean _on)
  
} // public class Settings 