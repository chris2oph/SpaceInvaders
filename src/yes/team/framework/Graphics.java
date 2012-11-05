// ----------------------------------------------------------------
// Space Invaders.
// Graphics.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework;

public interface Graphics 
{
  
  public static enum PixmapFormat
  { // Stores an enum for the different types of bitmap formatting to be used.
    ARG8888,    // Highest quality. Allows use of alpha and uses 8 bits for each channel. 
    ARGB4444,   // Medium quality. Allows use of alpha and uses 4 bits for each channel.
    RGB565      // Lowest quality. Only handles red green and blue, uses 5 bits for red and blue, 6 for green. 
  } // public static enum PixmapFormat

  // Creates a new pixmap from the filename passed in and the format specified.
  public Pixmap newPixmap(String _fileName, PixmapFormat _format);
  
  // Clears the entire framebuffer with the colour integer specified.
  public void clear(int _colour);
  
  // Draws a bitmap on the screen at position x and y. Specifies the rectangle to use inside the source file.
  public void drawPixmap(Pixmap _pixmap, int _x, int _y, int _srcX, int _srcY, int _srcWidth, int _srcHeight);
  
  // Draws the full image bitmap at the screen at position x and y. 
  public void drawPixmap(Pixmap _pixmap, int _x, int _y);
  
  // Gets the width of the framebuffer.
  public int  getWidth();
  
  // Gets the height of the framebuffer.
  public int  getHeight();
  
} // public class Graphics
