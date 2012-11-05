// ----------------------------------------------------------------
// Space Invaders.
// Sound.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework;

public interface Sound
{
  // Plays the sound out of the volume.
  public void play(float _volume);
  
  // Disposes of the sound from memory.
  public void dispose();
  
} // public interface Sound 
