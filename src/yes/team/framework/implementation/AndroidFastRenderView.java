// ----------------------------------------------------------------
// Space Invaders.
// AndroidFastRenderView.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework.implementation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidFastRenderView extends SurfaceView implements Runnable 
{
  // Create references to the game, framebuffer, render thread and surface holder.
  AndroidGame game;
  Bitmap framebuffer;
  Thread renderThread = null;
  SurfaceHolder holder;
  
  // Set the running state to be false.
  volatile boolean running = false;
  
  public AndroidFastRenderView(AndroidGame _game, Bitmap _framebuffer) 
  { 
    // Call the superclass constructor and assign the references to the parameters passed.
    super(_game);
    this.game = _game; 
    this.framebuffer = _framebuffer; 
    this.holder = this.getHolder();
  } // public AndroidFastRenderView(AndroidGame game, Bitmap framebuffer)
  
  public void resume() 
  { // Resumes the render view.
    this.running = true;
    this.renderThread = new Thread(this);
    this.renderThread.start(); 
  } // public void resume()
  
  @Override
  public void run() 
  {
    Rect destRect = new Rect();
    long startTime = System.nanoTime();
    
    while(running)
    {
      if(!holder.getSurface().isValid())
      {
        continue;
      } // end if
      
      // Update the time since last frame.
      float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
      
      // Set the current frame time and call the updates and presents of each of the screens.
      startTime = System.nanoTime();
      game.getCurrentScreen().update(deltaTime); 
      game.getCurrentScreen().present(deltaTime);
      Canvas canvas = holder.lockCanvas(); 
      canvas.getClipBounds(destRect);
      
      // Draw the frambuffer on the canvas.
      canvas.drawBitmap(framebuffer, null, destRect, null); 
      holder.unlockCanvasAndPost(canvas);
    } // while(running)
  } // public void run()
  
  public void pause()
  {
    this.running = false;
    while(true)
    {
      try
      {
        renderThread.join();
        break;
      } // end try
      catch(InterruptedException e)
      {
        // retry
      } // end catch
    } // end while
  } // public void pause()

} // public class AndroidFastRenderView implements Runnable
