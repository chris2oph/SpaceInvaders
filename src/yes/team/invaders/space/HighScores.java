//----------------------------------------------------------------
//Project: SpaceInvaders
//File: HighScores.java
//Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//    Thomas Gillett
//----------------------------------------------------------------

package yes.team.invaders.space;

//Import the required files for the class.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yes.team.framework.FileIO;
import android.util.Log;

public class HighScores 
{
  // Singleton instance of the HighScore class.
  private static HighScores instance = null; 
  
  // Boolean to determine if the player is using
  private List<Integer> scores;
   
  private HighScores()
  { // Constructor for the HighScore class.
    this.scores = new ArrayList<Integer>();
  } // Private AssetManager().
  
  
  public static synchronized HighScores getInstance()
  { // GetInstance method which returns a new instance 
    // of the HighScore class if one does not already 
    // exist.
    if(instance == null)
    {
      instance = new HighScores();
    }
    return instance;
  } // public static synchronized AssetManager getInstance()
  
  
  public void load(FileIO _files) 
  { // Load method for the HighScore class which loads
    // a file from memory for the stored game settings.
    BufferedReader in = null;
    
    // Check for the highscore file
    try 
    {
      // If the file is found, use the value defined in the file 
      // for the control type.
      in = new BufferedReader(new InputStreamReader(_files.readFileExternal(".spaceinvadersHighScores")));
      String inString = in.readLine();
      while(inString != null)
      {
        this.scores.add(Integer.parseInt(inString));
        inString = in.readLine();
      } // end while
    } // end try
    catch(IOException _e) 
    {
      try
      {
        // Use defaults.
        in = new BufferedReader(new InputStreamReader(_files.readFileInternal(".spaceinvadersHighScores")));
        String inString = in.readLine();
        while(inString != null)
        {
          this.scores.add(Integer.parseInt(inString));
          inString = in.readLine();
        } // end while
      } // end try
      catch(IOException _e2)
      {
        // Use defaults.
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
          // Close the scores file.
          in.close();
        } // end if
      } // end try
      catch(IOException e) 
      {
        
      } // end catch
    } // end finally
  } // public void load(FileIO _files)
  
  
  public void save(FileIO _files) 
  { // Save method for the HighScores class. This method saves
    // the settings to memory for future use by the game.
    
    BufferedWriter out = null;
   
    // Check for the file for the settings.
    try 
    {
      // If the file is present, overwrite the current values
      // with the values selected in game.
      out = new BufferedWriter(new OutputStreamWriter(_files.writeFileExternal(".spaceinvadersHighScores")));
      
      int length = scores.size();
      
      for(int i=0;i<length;i++)
      {
        out.write(Integer.toString(this.scores.get(i)));
        out.write('\n');
      } // end for
      
    } // end try
    catch(IOException _e) 
    { 
      try
      {
        // Use defaults.
        out = new BufferedWriter(new OutputStreamWriter(_files.writeFileInternal(".spaceinvadersHighScores")));
        int length = scores.size();
        
        for(int i=0;i<length;i++)
        {
          out.write(Integer.toString(this.scores.get(i)));
          out.write('\n');
        } // end for
        
      } // end try
      catch(IOException _e2)
      {
        // Use defaults.
      } // end catch
      Log.d("EN0618", _e.getMessage());
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
  
  
  public void addHighScore(int _score)
  { 
    int length = this.scores.size();
    
    if(length < 10)
    {
      this.scores.add(_score);
    } // end if
    else
    {
      // replace the score at the bottom.
      if(this.scores.get(length-1) < _score)
      {
        this.scores.remove(length-1);
        this.scores.add(_score);
      } // end if
    } // end else
    
    // Sort the list to fit nicely.
    Collections.sort(this.scores);
    Collections.reverse(this.scores);
    
  } // public void setTouchControlOn(boolean _on)
  
  
  public List<Integer> getHighScores()
  { 
    return this.scores;
  } // public boolean getTouchControlOn(boolean _on)
  
} // public class HighScores
