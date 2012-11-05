package yes.team.invaders.space.objects;

import yes.team.framework.Game;
import yes.team.framework.Graphics;
import yes.team.invaders.space.Assets;
import yes.team.invaders.space.InvaderManager;
import yes.team.invaders.space.Score;
import android.graphics.Rect;

public class Invader extends GameObject 
{
	private int invaderType;
	private int index;
	private float deadCounter = 0;
	private Rect collisionBox;
	
	public Invader(Game _game)
	{
		super(_game);
		collisionBox = new Rect();
	} // public Invader(Game _game)
	
	public void setAttributes(int _invaderType, int _xPos, int _yPos, int _index)
	{
		this.xPosition = _xPos;
		this.yPosition = _yPos;
		this.active = true;
		this.invaderType = _invaderType;
		this.index = _index;
		this.deadCounter = 0.0f;
		
		if(this.invaderType == 0)
		{
			this.sprite = Assets.getInstance().getInvader(0);
		} // end if
		else if(this.invaderType == 1)
		{
			this.sprite = Assets.getInstance().getInvader(1);
		} // end else if
		else if(this.invaderType == 2)
		{
			this.sprite = Assets.getInstance().getInvader(2);
		} // end else if
		
		this.width = this.sprite.getWidth() / 2;
		this.height = this.sprite.getHeight();
	} // public Invader(Game _game, int _invaderType, int _xPos, int _yPos)
	
	public void render(int _frameNum)
	{
	  Graphics graphics = this.game.getGraphics();
		if(this.getActive())
		{
			// Draw space invader.
			if(this.sprite != null)
			{
				graphics.drawPixmap(this.sprite, this.xPosition, this.yPosition, _frameNum * this.width, 0, this.width, this.height);
			} // end if
		} // end if
		else if(this.deadCounter > 0)
		{
			// Draw explosion animation
			this.sprite = Assets.getInstance().getExplosion();
			 
			if(this.sprite != null)
			{
				graphics.drawPixmap(this.sprite, this.xPosition, this.yPosition);
			} // end if
		} // end if
	} // public void render()
	
	@Override
	public void update(float _deltaTime)
	{
		// Still exploding?
		if(this.deadCounter > 0)
		{
			this.deadCounter -= _deltaTime;
		} // end if		

		this.updateCollisionBox();
	} // public void update(float _deltaTime)
	
	
	@Override
	public void takeHit()
	{
		// Invader shot by player. Set explosion animation counter, kill the invader, increase player score and play sound.
		this.active = false;
		this.deadCounter = 0.5f;
		InvaderManager.getInstance().invaderDied(this);
		Score.getInstance().AddScore(10);
		Assets.getInstance().getInvaderDeath().play(1);
	} // public void takeHit()
	
	
  @Override
  public void updateCollisionBox()
  {
	  this.collisionBox.left   = this.xPosition + (int)(this.width * 0.1);
	  this.collisionBox.right  = this.xPosition + this.width  - (int)(this.width * 0.1);
	  this.collisionBox.top    = this.yPosition + (int)(this.height * 0.2f);
	  this.collisionBox.bottom = this.yPosition + this.height - (int)(this.height * 0.2f); 
	  this.setCollisionBox(this.collisionBox);
  } // public void updateCollisionBox()
	
  public int getIndex()
  {
	  // Gets the index of an invader in the invader collection.
	  return this.index;
  } // public int getIndex()
	
	public void setDeadCounter(float _deadCounter)
	{
		// For explosion animation.
		this.deadCounter = _deadCounter;
	} // public void setDeadCounter(float _deadCounter)
} // public class Invader extends Object 
