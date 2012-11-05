// ----------------------------------------------------------------
// Space Invaders.
// Pixmap.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework;

import yes.team.framework.Graphics.PixmapFormat;

public interface Pixmap 
{
  // Returns the width of the pixmap.
  public int getWidth();
  
  // Returns the height of the pixmap.
  public int getHeight();
  
  // Returns the format type of the pixmap (ARGB888, ARGB4444, RGB565).
  public PixmapFormat getFormat();
  
  // Disposes of the pixmap object and clears up the memory.
  public void dispose();
  
} // public interface Pixmap
