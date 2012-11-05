// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: Block.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space.objects;

// Import the files required by the class.
import yes.team.framework.Game;
import yes.team.framework.Graphics;

// Definition and implementation of the Block class.
public class Block extends GameObject
{
	// Variables for the health and size of the blocks and a declaration of a 
	// constant for the max block health.
	private int health;
	private static final int maxHealth = 3;
	private static final int blockSize = 15;	
	
	Block(Game _game)
	{ // Constructor for the Block class.
	  super(_game);
	  this.health = 0;
	  this.setActive(true);
	  this.setHeight(Block.blockSize-1);
	  this.setWidth(Block.blockSize-1);
	} // Block(Game _game)
	
	@Override
	public void render()
	{ // // Render method for the Block class. This method renders the blocks to screen.
		// If the block is active, render it to screen.
	    if(this.getActive())
	    {
	      Graphics g = this.game.getGraphics();
	      if(this.getSprite() != null)
	      {
	        g.drawPixmap(this.getSprite(), this.getXPosition(), this.getYPosition(),
	        				this.health*Block.blockSize,0,Block.blockSize,Block.blockSize);
	      } // end if
	    } // end if
	} // public void render()
	
	
	public void dmgHealth()
	{ // Dmghealth method for the Block class. This method reduces the health of a block 
	  // when hit by a projectile.
		this.health++;
		if(this.health >= Block.maxHealth)
		{
			this.setActive(false);
		} // end if
	} // public void dmgHealth()
	
	
	public void resetHealth()
	{ // Resethealth method for the Block class. This method resets the block health when the 
	  // game is reset.
		this.health = Block.maxHealth;
	} // public void resetHealth()
	
	
	@Override
	public void takeHit()
	{ // TakeHit method for the Block class. This method reduces the health of a block.
		this.dmgHealth();
	} // public void takeHit()
} // public class Block extends GameObject
