package yes.team.invaders.space.objects;

import java.util.Date;
import java.util.Random;

import android.graphics.Rect;
import yes.team.framework.Game;
import yes.team.framework.Graphics;
import yes.team.invaders.space.Assets;
import yes.team.invaders.space.Score;

public class Mothership extends GameObject 
{
	private static Mothership instance = null;
	private static final float XVEL = 1.8f;
	private static final int MINTIMEBETWEENSHIPS = 5;
	private static final int MAXRANDOMTIME = 15;
	private float deadCounter = 0;
	private int randomTime = 0;
	private float timeCounter = 0.0f;
	private boolean hasRandom = false;
	private Random generator;
	private Game game;
	Rect collisionBox;

	private Mothership() 
	{
		super(null);	
		collisionBox = new Rect();
	} // private Mothership() 
	
	public void initialise(Game _game)
	{
		this.game = _game;
		this.sprite = Assets.getInstance().getMothership();
		this.width = this.sprite.getWidth();
		this.height = this.sprite.getHeight();
		this.generator = new Random(new Date().getTime());
	} // public void initialise(Game _game)
	
	public static Mothership getInstance()
	{
		if(instance == null)
	  {
	    instance = new Mothership();
	  } // end if
		
	    return instance;
	} // public static Mothership getInstance()
	
	public void reset()
	{	// Called when mothership killed, exits screen or game restarts.
		this.active = false;
		this.timeCounter = 0.0f;
		this.hasRandom = false;
	} // public void reset()
	
	public void activate(int _xPos, float _xVel)
	{	// Called when mothership added from side of screen.
		this.xPosition = _xPos;
		this.yPosition = this.height;
		this.xVelocity = _xVel;
		this.active = true;
		this.deadCounter = 0;
	} // public void activate(int _xPos, float _xVel)
	
	
	public void render()
	{	// Draws a mothership or an explosion.
	  Graphics graphics = this.game.getGraphics();
	  
		if(this.active)
		{
			this.sprite = Assets.getInstance().getMothership();
			
			if(this.sprite != null)
			{
			  graphics.drawPixmap(this.sprite, this.xPosition, this.yPosition);
			} // end if
		} // end if
		else if(this.deadCounter > 0)
		{
			this.sprite = Assets.getInstance().getExplosion();
			 
			if(this.sprite != null)
			{
				graphics.drawPixmap(this.sprite, this.xPosition + (this.width/ 4), this.yPosition);
			} // end if
		}
	} // public void render()
	
	@Override
	public void update(float _deltaTime)
	{	// Decide when to add a mothership and move a mothership if one is active.
		this.timeCounter += _deltaTime;
		
		if(!this.active)
		{
			// Add a random amount of time to the interval between motherships.
			if(!this.hasRandom)
			{
				this.randomTime = this.generator.nextInt() % Mothership.MAXRANDOMTIME;
				
				if(this.randomTime < 0)
				{
					this.randomTime *= -1;
				} // end if
				
				this.hasRandom = true;
			} // end if
			
			// Time to add mothership?
			if(this.timeCounter > Mothership.MINTIMEBETWEENSHIPS + this.randomTime)
			{
				if(this.generator.nextInt() % 2 == 0)
				{
					this.activate(0 - this.width, Mothership.XVEL);
				} // end if
				else
				{
					this.activate(game.getGraphics().getWidth(), -Mothership.XVEL);
				} // end if
			} // end if
		} // end if(!this.getActive())
		
		if(this.active)
		{
			// Mothership active, update movement and collision.
			this.move(_deltaTime);
			this.updateCollisionBox();
			
			// Mothership off screen?
			if((this.xVelocity > 0 && this.xPosition > this.game.getGraphics().getWidth()) ||
					this.xVelocity < 0 && this.xPosition < 0 - this.width)
			{
				this.reset();
			}
		}
		
		// Playing explosion animation?
		if(this.deadCounter > 0)
		{
			this.deadCounter -= _deltaTime;
		}	
	} // public void update(float _deltaTime)
	
	@Override
	public void takeHit()
	{
		// Set explosion animation counter, increase player score, play sound and reset mothership attributes.
	  this.deadCounter = 0.5f;
	  Score.getInstance().AddScore(100);
	  Assets.getInstance().getInvaderDeath().play(1);
	  this.reset();
	} // public void takeHit()
	
	
  @Override
  public void updateCollisionBox()
  {
	  
	  this.collisionBox.left   = this.xPosition + (int)(this.width * 0.1);
	  this.collisionBox.right  = this.xPosition + this.width  - (int)(this.width * 0.1f);
	  this.collisionBox.top    = this.yPosition + (int)(this.height * 0.15f);
	  this.collisionBox.bottom = this.yPosition + this.height - (int)(this.height * 0.15f); 
	  this.setCollisionBox(collisionBox);
  } // public void updateCollisionBox()
}
