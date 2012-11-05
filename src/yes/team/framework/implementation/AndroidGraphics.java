// ----------------------------------------------------------------
// Space Invaders.
// AndroidGraphics.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework.implementation;

import java.io.IOException;
import java.io.InputStream;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import yes.team.framework.Graphics;
import yes.team.framework.Pixmap;

public class AndroidGraphics implements Graphics 
{
  // Create references to the various objects. 
	AssetManager assets;           // Create a reference to the assetManager.
	Bitmap frameBuffer;            // Create a reference to the framebuffer.
	Canvas canvas;                 // Create a reference to the canvas.
	Paint paint;                   // Declare a paint object.
	Rect srcRect = new Rect();     // Create a new source rect.
	Rect dstRect = new Rect();     // Create a new destinationRect.

	public AndroidGraphics(AssetManager _assets, Bitmap _frameBuffer) 
	{
		this.assets = _assets;
		this.frameBuffer = _frameBuffer;
		this.canvas = new Canvas(_frameBuffer);
		this.paint = new Paint();
	} // public AndroidGraphics(AssetManager _assets, Bitmap _frameBuffer)
	
	@Override
	public Pixmap newPixmap(String _fileName, PixmapFormat _format) 
	{ // Creates a new pixmap from the filename passed in and the format specified.
		Config config = null;
		
		// Determine the pixmap formats.
		if(_format == PixmapFormat.RGB565)
		{
			config = Config.RGB_565;
		} // end if
		else if(_format == PixmapFormat.ARGB4444)
		{
			config = Config.ARGB_4444;
		} // end else if
		else
		{
			config = Config.ARGB_8888;
		} // end else
		
		// Create a new options and pass in the bitmap format.
		Options options = new Options();
		options.inPreferredConfig = config;
		
		InputStream in = null;
		Bitmap bitmap = null;
		
		try 
		{
		  // Open up the filename and decode into a bitmap.
			in = assets.open(_fileName);
			bitmap = BitmapFactory.decodeStream(in);
			
			if(bitmap == null)
			{
				throw new RuntimeException("Couldn't load bitmap from asset '" + _fileName + "'");
			} // end if
		} // end try
		catch (IOException e) 
		{
			throw new RuntimeException("Couldn't load bitmap from asset '" + _fileName + "'");
		} // end catch
		finally 
		{
			if (in != null) 
			{
				try
				{
					in.close();
				} // end try
				catch (IOException e) 
				{
				} // end catch
			} // end if
		} // end finally.
		
		// Set the format of the bitmap.
		if(bitmap.getConfig() == Config.RGB_565)
		{
		  _format = PixmapFormat.RGB565;
		} // end if
		else if(bitmap.getConfig() == Config.ARGB_4444)
		{
		  _format = PixmapFormat.ARGB4444;
		} // end else if
		else
		{
		  _format = PixmapFormat.ARG8888;
		} // end else
		
		return new AndroidPixmap(bitmap, _format);
	} // public Pixmap newPixmap(String _fileName, PixmapFormat _format)
	
	@Override
	public void clear(int _colour)
	{ // Draws a bitmap on the screen at position x and y. Specifies the rectangle to use inside the source file.
		this.canvas.drawRGB((_colour & 0xff0000) >> 16, (_colour & 0xff00) >> 8, (_colour & 0xff));
	} // public void clear(int _colour)
	
	@Override
	public void drawPixmap(Pixmap _pixmap, int _x, int _y, int _srcX, int _srcY, int _srcWidth, int _srcHeight) 
	{ // Draws a bitmap on the screen at position x and y. Specifies the rectangle to use inside the source file.
	  // Define the source rect from the input parameters.
	  this.srcRect.left = _srcX;
	  this.srcRect.top = _srcY;
	  this.srcRect.right = _srcX + _srcWidth - 1;
	  this.srcRect.bottom = _srcY + _srcHeight - 1;
	  
	  // Define the destination rect from the input parameters.
	  this.dstRect.left = _x;
	  this.dstRect.top = _y;
	  this.dstRect.right = _x + _srcWidth - 1;
	  this.dstRect.bottom = _y + _srcHeight - 1;
	  
	  // Draw the bitmap from the source rect and destination rect on to the cavas.
	  this.canvas.drawBitmap(((AndroidPixmap) _pixmap).bitmap, this.srcRect, this.dstRect, null);
	} // public void drawPixmap(Pixmap _pixmap, int _x, int _y, int _srcX, int _srcY, int _srcWidth, int _srcHeight) 
	
	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y) 
	{ // Draws the full image bitmap at the screen at position x and y.
	  // Draw the entire bitmap on to the canvas.
		canvas.drawBitmap(((AndroidPixmap)pixmap).bitmap, x, y, null);
	} // public void drawPixmap(Pixmap pixmap, int x, int y)
	
	@Override
	public int getWidth() 
	{ // Gets the width of the framebuffer.
		return frameBuffer.getWidth();
	} // public int getWidth() 
	
	@Override
	public int getHeight() 
	{ // Gets the height of the framebuffer.
		return frameBuffer.getHeight();
	} // public int getHeight()
} // public class AndroidGraphics implements Graphics