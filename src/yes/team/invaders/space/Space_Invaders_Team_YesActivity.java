// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: Space_Invaders_Team_YesActivity.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

// Specify the file directory.
package yes.team.invaders.space;

// Import the required files.
import yes.team.framework.Screen;
import yes.team.framework.implementation.AndroidGame;

// Definition and implementation of the 
// Space_Invaders_Team_YesActivity class. This activity is the 
// main activity for the game.
public class Space_Invaders_Team_YesActivity extends AndroidGame 
{
    /** Called when the activity is first created. */
    @Override
    public Screen getStartScreen()
    {
      return new LoadingScreen(this);
    } // public Screen getStartScreen() 
} // public class Space_Invaders_Team_YesActivity extends AndroidGame