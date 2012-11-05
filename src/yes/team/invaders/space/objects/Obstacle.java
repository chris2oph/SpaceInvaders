// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: Obstacle.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space.objects;

// Import the files required by the class.
import yes.team.framework.Game;
import yes.team.invaders.space.Assets;

// Definition and implementation of the Obstacle class.
public class Obstacle extends GameObject
{
	// Variables for the number of blocks wide and high in an obstacle.
	final int numBlocksW = 4;
	final int numBlocksH = 3;
	final int blockSize = 14;
	
	// 2 dimensional array of blocks for each obstacle.
	public Block[][] blocks;
	
	
  public Obstacle(Game _game)
  { // Constructor for the Obstacle class.
  	// Call the superclass constructor.
  	super(_game);
  } // public Obstacle(Game _game)
    
  
  @Override
  public void initialise()
  { // Initialise method for the Obstacle class. This method initialises the variables
    // for the obstacle.
  	blocks = new Block[numBlocksW][numBlocksH];
  	
  	// For all of the blocks in the array, create a new block, set the 
  	// sprite and set the block to active.
  	for(int i =0;i<numBlocksW;i++)
  	{
  		for(int u=0;u<numBlocksH;u++)
  		{
  			blocks[i][u] = new Block(this.game);
  			blocks[i][u].setSprite(Assets.getInstance().getObstacleSpr());
  			blocks[i][u].setActive(true);
  		} // end for u
  	} // end for i
    	
  	blocks[0][0].setSprite(Assets.getInstance().getObstacleCornerSpr());
  	blocks[3][0].setSprite(Assets.getInstance().getObstacleCornerRSpr());
  	blocks[1][2].setActive(false);
  	blocks[2][2].setActive(false);
    	
  	for(int i =0;i<numBlocksW;i++)
  	{
  		for(int u=0;u<numBlocksH;u++)
  		{
  		   blocks[i][u].setXPosition((i*blockSize)+this.getXPosition());
  		   blocks[i][u].setYPosition((u*blockSize)+this.getYPosition());
  		   blocks[i][u].updateCollisionBox();
  		} // end for u
  	} // end for i
  } // public void initialise()
    
  
  @Override
  public void render()
  {	// Render method for the Obstacle class. This method renders all blocks in an obstacle.
  	for(int i =0;i<numBlocksW;i++)
  	{
  		for(int u=0;u<numBlocksH;u++)
  		{
  		   if(blocks[i][u].getActive())
  		   {
  		     blocks[i][u].render();
  		   } // end if
  		} // end for u
  	}	// end for i
  } // public void render()
  
  
  public int getNumBlocksW()
  { // GetNumBlocksW method for the Obstacle class. This method returns the number of
    // blocks wide.
  	return numBlocksW;
  } // public int getNumBlocksW()
    
  
  public int getNumBlocksH()
  { // GetNumBlocksH method for the Obstacle class. This method returns the number of
    // blocks high.
  	return numBlocksH;
  } // public int getNumBlocksH()
  
} // public class Obstacle extends GameObject
