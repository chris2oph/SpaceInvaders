// ----------------------------------------------------------------
// Space Invaders.
// InvaderManager.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.invaders.space;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import yes.team.invaders.space.objects.Invader;
import yes.team.invaders.space.objects.Projectile;
import yes.team.invaders.space.objects.GameObject;
import yes.team.framework.Game;
import yes.team.invaders.space.Assets;

import java.util.Random;

public class InvaderManager
{
  private static InvaderManager instance = null;
  
	private static final int NUMINVADERSACROSS = 6;
	private static final int NUMINVADERSDOWN = 6;
	private static final int NUMINVADERS = InvaderManager.NUMINVADERSACROSS * InvaderManager.NUMINVADERSDOWN;
	private static final int NUMPROJECTILES = 2;
	private static final float STARTMOVETIME = 2.0f;
	private static final float FASTESTMOVETIME = 0.5f;
	private static final float SPEEDINC = 0.03f;
	private static final float MOVEINC = 0.2f;
	private static final int DEATHROW = 11;
	private static final float PROJYVEL = 3.0f;
	private float moveTime = InvaderManager.STARTMOVETIME;
	private float waveInitialMoveTime = 2.0f;
	private float shootTime = 1.5f;
	private float timeToMove = 0.0f;
	private float timeToShoot = 0.0f;
	private List<Invader> invaders;
	private boolean right = true;
	private boolean down = false;
	
	private Game game;
  private int activeLeftColumn = 0;
	private int activeRightColumn = InvaderManager.NUMINVADERSACROSS - 1;
	private int spriteWidth = 0;
	private int spriteHeight = 0;

	private List<Projectile> projectiles;  
	private Random generator;
	private int numInvadersDead = 0;
	private int xStartPos;
	private int yStartPos;
	private boolean frame1 = true;
	
	
	private InvaderManager()
	{
	  
	} // public InvaderManager(Game _game)
	
	
	public static synchronized InvaderManager getInstance()
	{
    if(instance == null)
    {
      instance = new InvaderManager();
    } // end if
		
    return instance;
	} // public static InvaderManager getInstance()
	
	
	public void initialise(Game _game)
	{

		this.game = _game;
		this.invaders = new ArrayList<Invader>();
		this.projectiles = new ArrayList<Projectile>();
		this.generator = new Random(new Date().getTime());
		this.spriteWidth = Assets.getInstance().getInvader(1).getWidth() / 2; 
		this.spriteHeight = Assets.getInstance().getInvader(1).getHeight();
		this.xStartPos = (game.getGraphics().getWidth()/2) - (((int)(game.getGraphics().getWidth()/(this.spriteWidth)) * this.spriteWidth) / 2);
		this.yStartPos = this.spriteHeight*2;
		this.moveTime = InvaderManager.STARTMOVETIME;
		this.down = false;
		this.right = true;
		this.waveInitialMoveTime = 2.0f;
		
		this.moveTime = InvaderManager.STARTMOVETIME;
		this.waveInitialMoveTime = 2.0f;
	  this.shootTime = 1.5f;
	  this.timeToMove = 0.0f;
	  this.timeToShoot = 0.0f;
	  this.numInvadersDead = 0;
	  
		// Create projectiles for the invaders to use.
		for(int i=0; i<InvaderManager.NUMPROJECTILES;++i)
		{
			this.projectiles.add(new Projectile(_game));
			this.projectiles.get(i).setSprite(Assets.getInstance().getInvaderBullet());
			this.projectiles.get(i).setActive(false);
		} // end for
		
		for(int i=0; i<InvaderManager.NUMINVADERS; ++i)
		{
			invaders.add(new Invader(_game));
		} // end for
		
		this.setAttributes();
	} // public void initialise(Game _game)
	
	private void setAttributes()
	{
		// Resets the invaders in their initial positions.
		int invaderType = 0;		
		int xPos = xStartPos;
		int yPos = yStartPos;
		int invaderIndex = 0;
		for(int i=0; i<InvaderManager.NUMINVADERSDOWN; ++i)
		{
			for(int j=0; j<InvaderManager.NUMINVADERSACROSS; ++j)
			{
				invaderIndex = i * InvaderManager.NUMINVADERSDOWN + j;
				this.invaders.get(invaderIndex).setAttributes(invaderType, xPos, yPos, invaderIndex);
				xPos += this.spriteWidth;
			} // end for j
			
			yPos += this.spriteHeight;
			xPos = this.xStartPos;
			++invaderType;
			
			if(invaderType > 2)
			{
				invaderType = 0;
			} // end if
		} // end for i
	}
	
	public void update(float _deltaTime, List<GameObject> _allObjects)
	{
		// Decide when to move invaders and shoot from invaders.
		this.timeToMove += _deltaTime;
		this.timeToShoot += _deltaTime;
		
		if(this.timeToMove > this.moveTime)
		{
			this.timeToMove = 0;
			this.move(_deltaTime);
			
			// Alternate animation frames when moving.
			this.frame1 = !this.frame1;
		} // end if
		
		if(this.timeToShoot > this.shootTime)
		{
			this.timeToShoot = 0;
			this.shoot();
		} // end if
		
		// Update active projectiles.
		for(int i=0; i<InvaderManager.NUMPROJECTILES; ++i)
		{
			Projectile proj = projectiles.get(i);
			
			if(proj.getActive())
			{
				proj.update(_deltaTime);
				proj.collisionDetection(_allObjects);
			} // end if
		} // end for
		
	
		
		// update animations...
		for(int i=0; i<InvaderManager.NUMINVADERS; ++i)
		{
			this.invaders.get(i).update(_deltaTime);
		} // end for
		

		if(this.right)
		{
			// at rhs?
			if(this.invaders.get(this.activeRightColumn).getXPosition() + this.spriteWidth * 2 >= this.game.getGraphics().getWidth())
			{
				this.right = false;
				this.down = true;
			} // end if
		} // end else if
		else
		{
			// at lhs?
			if(this.invaders.get(this.activeLeftColumn).getXPosition() <= this.spriteWidth)
			{
				this.right = true;
				this.down = true;
			} // end if
		} // end else
	} // public void update(double _deltaTime)
	  
	  
	public void move(float _deltaTime)
	{
		Assets.getInstance().getInvaderMove().play(4.0f);
		
		for(int i=0; i<InvaderManager.NUMINVADERS; ++i)
		{
      if(this.down)
			{					
				if(this.right)
				{
					this.right = false;
				} // end if
				else
				{
					this.right = true;
				} // end else
					
				this.invaders.get(i).setYPosition(this.invaders.get(i).getYPosition() + this.spriteHeight);
			} // end if
			else if(this.right)
			{					
				this.invaders.get(i).setXPosition(this.invaders.get(i).getXPosition() + this.spriteWidth);
			} // end else if
			else
			{
				this.invaders.get(i).setXPosition(this.invaders.get(i).getXPosition() - this.spriteWidth);
			} // end else

		} // end for
		
		if(this.down)
		{
			this.down = false;
			
			if(this.EndGame())
			{
				Assets.getInstance().getPlayerDeath().play(1);
				Lives.getInstance().setLives(0);
			} // end if
		} // end if
	} // public void move(float _deltaTime)
	
	private boolean EndGame()
	{
		if(this.GetLowestInvaderScreenPos() >= this.yStartPos + (InvaderManager.DEATHROW * this.spriteHeight))
		{
			return true;
		} // end if
		
		return false;
	} // private boolean EndGame()
	
	private int GetLowestInvaderScreenPos()
	{ // For checking end game condition.
		
		for(int i=InvaderManager.NUMINVADERS-1; i>=0; --i)
		{
			if(this.invaders.get(i).getActive())
			{
				return this.invaders.get(i).getYPosition();
			} // end if
		} // end for
		
		return 0;
	} // private int GetLowestInvaderScreenPos()
	
	public void render()
	{	// Draw invaders and invader projectiles.
	  int frameNum = frame1 ? 0 : 1;
	  
		for(int i=0; i<InvaderManager.NUMINVADERS; ++i)
		{
			this.invaders.get(i).render(frameNum);
		} // end for
		
		for(int i=0; i<InvaderManager.NUMPROJECTILES;++i)
		{
			this.projectiles.get(i).render();
		} // end for
	} // public void render()
	
	public List<Invader> getInvadersList()
	{
	  return this.invaders;
	} // public List<Invader> getInvadersList 
	
	public void invaderDied(Invader _invader)
	{ // See if column is now empty now that invader has been killed, increase speed slightly.
		this.checkColumn(_invader.getIndex());
		this.moveTime -= InvaderManager.SPEEDINC;
		++this.numInvadersDead;
		
		// All invaders dead?
		if(this.numInvadersDead >= InvaderManager.NUMINVADERS)
		{
			// Yes, add new wave.
			this.numInvadersDead = 0;
			this.timeToMove = 0.0f;
			
		  this.activeLeftColumn  = 0;
		  this.activeRightColumn = InvaderManager.NUMINVADERSACROSS-1;
		  _invader.setDeadCounter(0);
		  //_invader.setActive(true);
		  this.right = true;
		  this.down  = false;
		  this.timeToShoot = this.shootTime;
		  
		  for(int i=0;i<InvaderManager.NUMPROJECTILES;i++)
		  {
		    this.projectiles.get(i).setActive(false);
		  } // end for
			
			if(this.moveTime > InvaderManager.FASTESTMOVETIME)
			{
				// Increase the initial invaders speed.
				this.waveInitialMoveTime -= InvaderManager.MOVEINC;
				this.moveTime = this.waveInitialMoveTime;
			} // end if
			else
			{
				this.moveTime = InvaderManager.FASTESTMOVETIME;
			} // end else
			
			setAttributes();
		} // end if
	} // public void invaderDied(int _deadInvaderIndex)
	
	public void reset()
	{
		// New wave.
		this.numInvadersDead = 0;
		this.timeToMove = 0.0f;
		this.frame1 = true;
		this.down = false;
		this.right = true;
		this.activeLeftColumn = 0;
		this.activeRightColumn = InvaderManager.NUMINVADERSACROSS - 1;
		
		this.setAttributes();
	} // public void reset()
	
	private boolean columnActive(int _colNum)
	{
		for(int i = 0; i < InvaderManager.NUMINVADERSDOWN; ++i)
		{
			if(this.invaders.get(i * InvaderManager.NUMINVADERSACROSS + _colNum).getActive())
			{
				return true;
			} // end if
		} // end for
		
		return false;
	} // private boolean ColumnActive(int colNum)
	
	public void checkColumn(int _deadInvaderIndex)
	{
		// Sets the active left or right column so that invaders continue moving to the end of the screen.
	  // Get the invader column by modulusing its index by the number of columns.
		int colNum = _deadInvaderIndex % InvaderManager.NUMINVADERSACROSS;
		
		if(this.activeLeftColumn != this.activeRightColumn)
		{		
			if(colNum == this.activeLeftColumn)
			{
				if(!this.columnActive(colNum) && colNum < InvaderManager.NUMINVADERSACROSS)
				{
					this.checkColumn(++this.activeLeftColumn);
				} // end if
			} // end if
			else if(colNum == this.activeRightColumn)
			{
				if(!this.columnActive(colNum) && colNum > -1)
				{
					this.checkColumn(--this.activeRightColumn);
				} // end if
			} // end else if 
		} // end if
	} // public void checkColumn(int _deadInvaderIndex)
	
	private void shoot()
	{
	  int rand;
		// Shoot from a random active column.
		do
		{
			rand = this.generator.nextInt() % InvaderManager.NUMINVADERSACROSS;
			
			if(rand < 0)
			{
				rand *= -1;
			} // end if
		} // end do
		while(!this.columnActive(rand));
		
		Invader tempInv = this.invaders.get(getShootingInvaderIndex(rand));
		
		// Fire projectile from lowest active invader in a column.
		for(int i=0; i<InvaderManager.NUMPROJECTILES; ++i)
		{
			Projectile proj = projectiles.get(i);
			
			if(!proj.getActive())
			{
				proj.fire(tempInv.getXPosition() + (this.spriteWidth / 2) - (proj.getWidth() / 2),
						tempInv.getYPosition() + this.spriteHeight + 1, 0, InvaderManager.PROJYVEL, proj.getSprite());
				break;
			} // end if
		} // end for
		
		// Now set the shoot time to something random.
		this.shootTime = this.generator.nextFloat()*3.5f + 0.75f ;
		
	} // private void shoot()
	
	private int getShootingInvaderIndex(int _colNum)
	{	// Gets the index of the lowest active invader in a column. 
		int invaderIndex = 0;
		
		for(int i=0; i<InvaderManager.NUMINVADERSDOWN; ++i)
		{
			int indexToTest = i * InvaderManager.NUMINVADERSDOWN + _colNum;
			
			if(this.invaders.get(indexToTest).getActive())
			{
				invaderIndex = indexToTest;
			} // end if
		} // end for
		
		return invaderIndex;
	} // private int getShootingInvaderIndex(int _colNum)
	
} // public class InvaderManager

