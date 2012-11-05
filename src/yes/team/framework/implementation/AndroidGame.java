// ----------------------------------------------------------------
// Space Invaders.
// AndroidGame.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework.implementation;

import java.util.Stack;

import yes.team.framework.Audio;
import yes.team.framework.FileIO;
import yes.team.framework.Game;
import yes.team.framework.Graphics;
import yes.team.framework.Input;
import yes.team.framework.Screen;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public abstract class AndroidGame extends Activity implements Game 
{
  // Declare the aspects associated to the android game.
	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;
	Stack<Screen> screens;
	private boolean locking;
	
	@Override
	public void onCreate(Bundle _savedInstanceState)
	{ 
	  // Set the attributes for the android game class.
		super.onCreate(_savedInstanceState);
		
		this.locking = false;
		
		// Request that the title bar be removed.
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// Set flags for the fullscreen.
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		// Determine the orientaton of the game and set the framebuffer width and heights accordingly.
		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeight = isLandscape ? 320 : 480;
		
		// Create a new framebuffer with the width and height variables associated.
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Config.RGB_565);
		
		// Get the scale values to allow for scaling on multiple resolution devices.
		float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();
		
		// Create new instances of the various managers.
		this.renderView = new AndroidFastRenderView(this, frameBuffer);
		this.graphics = new AndroidGraphics(getAssets(), frameBuffer);
		this.fileIO = new AndroidFileIO(getAssets(), this.getApplicationContext());
		this.audio = new AndroidAudio(this);
		this.input = new AndroidInput(this, this.renderView, scaleX, scaleY);
		
		// Create a new start screen.
		this.screen = getStartScreen();
		this.screens = new Stack<Screen>();
		
		// Set the content view to the fast render view.
		setContentView(this.renderView);
		PowerManager powerManager = (PowerManager)
		getSystemService(Context.POWER_SERVICE);
		this.wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
	} // public void onCreate(Bundle _savedInstanceState)
	
	@Override
	public void onResume()
	{
		super.onResume();
		this.wakeLock.acquire();
		this.renderView.resume();
		this.screen.resume();
		
	} // public void onResume()
	
	@Override
	public void onPause()
	{
		super.onPause();
		this.wakeLock.release();
		
		this.screen.pause();
		this.renderView.pause();
	
		if(isFinishing())
		{
		  this.screen.dispose();
		} // end if
	} // public void onPause()
	
	@Override
	public Input getInput() 
	{ // Returns the input manager implementation for the game.
		return this.input;
	} // public Input getInput() 
	
	
	@Override
	public FileIO getFileIO() 
	{ // Returns the file input and output manager for the game.
		return this.fileIO;
	} // public FileIO getFileIO()
	
	
	@Override
	public Graphics getGraphics() 
	{ // Returns the graphics manager for the game.
		return this.graphics;
	} // public Graphics getGraphics()
	
	
	@Override
	public Audio getAudio() 
	{ // Returns the audio manager for the game.
		return this.audio;
	} // public Audio getAudio()
	
	
	@Override
	public void setScreen(Screen _screen) 
	{ // Sets the current screen or scene of the game.
		if(_screen == null)
		{
			throw new IllegalArgumentException("Screen must not be null");
		} // end if
		
		// Pause the game screen and dispose of it.
		if(!this.locking)
		{
		  this.screen.pause();
		} // end if
		this.screen.dispose();
		
		
		// Resume the current screen and update it.
		_screen.resume();
		_screen.update(0);
		
		// Set the current game screen.
		this.screen = _screen;
	} // public void setScreen(Screen _screen) 
	
	
	public Screen getCurrentScreen()
	{ // Returns the current active screen of the game.
		return screen;
	} // public Screen getCurrentScreen()
	
	
	@Override
	public void renderBottomScreenStack()
	{ // Renders the game screen at position 0 on the stack. Used for providing a seamless transition back to previous screens.
	  this.screens.get(0).present(1.0f);
	} // public void renderBottomScreenStack() 
	
	
	@Override
  public void pushScreen(Screen _screen) 
  { // Pushes the screen parameter on to the screen stack for transition between screens.
    if(_screen == null)
    {
      throw new IllegalArgumentException("Screen must not be null");
    } // end if
    
    this.screens.push(_screen);
  } // public void pushScreen(Screen _screen) 
  
	
  @Override
  public void popScreen()
  { // Pops a screen off the screen stack and places it as the current screen.
    this.screen = this.screens.pop();
  } // public void popScreen()
  
  
  @Override
  public void renderCurrentScreen()
  { // Renders the current game screen. Used just before the game over screen is called.
    this.screen.present(1.0f);
  } // public void renderCurrentScreen()
  
  
  @Override
  public void clearScreenStack()
  {
    while(!this.screens.empty())
    {
      this.screens.pop();
    } // end while
  } // public void clearScreenStack()
  
  
  @Override
  public void setLocking(boolean _locking)
  {
    this.locking = _locking;
  } // public void setLocking(boolean _locking) 
  
} // public abstract class AndroidGame extends Activity implements Game
