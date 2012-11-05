// ----------------------------------------------------------------
// Space Invaders.
// AndroidPixmap.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework.implementation;

import android.graphics.Bitmap;
import yes.team.framework.Graphics.PixmapFormat;
import yes.team.framework.Pixmap;

public class AndroidPixmap implements Pixmap 
{
  // Hold a bitmap image and a format for each pixmap.
	Bitmap bitmap;
	PixmapFormat format;
	
	
	public AndroidPixmap(Bitmap _bitmap, PixmapFormat _format) 
	{
		this.bitmap = _bitmap;
		this.format = _format;
	} // public AndroidPixmap(Bitmap _bitmap, PixmapFormat _format)
	
	
	@Override
	public int getWidth() 
	{ // Returns the width of the pixmap.
		return bitmap.getWidth();
	} // public int getWidth()
	
	
	@Override
	public int getHeight() 
	{ // Returns the height of the pixmap.
		return bitmap.getHeight();
	} // public int getHeight()
	
	
	@Override
	public PixmapFormat getFormat() 
	{ // Returns the format type of the pixmap (ARGB888, ARGB4444, RGB565).
		return format;
	} // public PixmapFormat getFormat()
	
	
	@Override
	public void dispose() 
	{ // Disposes of the pixmap object and clears up the memory.
		bitmap.recycle();
	} // public void dispose() 
	
} // public class AndroidPixmap implements Pixmap 