// ----------------------------------------------------------------
// Project: SpaceInvaders
// File: NumberOutput.java
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

// Specify the directory.
package yes.team.invaders.space;

// Import the files required by the class.
import java.util.*;
import java.lang.Integer;
import yes.team.framework.Pixmap;
import yes.team.framework.Game;
import yes.team.framework.Graphics;

// Definition and implementation of the NumberOutput class.
// Uses a pixmap image for number output, indexed 0-9.
// Integer and position can be set.
public class NumberOutput 
{
	// Variables for the position, value, size and sprite of the number output.
	private int xPosition;
	private int yPosition;
	private int numMod;
	private int numOutput;
	private int numCharSizeW;
	private int numCharSizeH;
	private int drawWidth;
	private Pixmap spriteNum;
	private List<Integer> numberList;
	Game game;
	
	
	NumberOutput(Game _game)
	{ // Constructor for the NumberOutput class.
		// Set the game variable to the passed in value and set the default 
		// values for the position, size, value and sprite.
		this.game      = _game;
		this.xPosition = 0;
		this.yPosition = 0;
		this.numCharSizeW = 0;
		this.numCharSizeH = 0;
		this.numOutput = 0;
		this.drawWidth = 0;
		this.spriteNum = null;
		this.numberList = new ArrayList<Integer>();
	} // NumberOutput(Game _game)
	
	
	NumberOutput(Game _game, int _xPos, int _yPos, int _numCharSizeW,int _numCharSizeH, Pixmap _sprite,int _drawWidth)
	{ // Constructor for the NumberOutput class.
		// Set the game variable to the passed in value and set the passed in 
		// values for the position, size, value and sprite.
		this.game      = _game;
		this.xPosition = _xPos;
		this.yPosition = _yPos;
		this.numCharSizeW = _numCharSizeW;
		this.numCharSizeH = _numCharSizeH;
		this.numOutput = 0;
		this.spriteNum = _sprite;
		this.drawWidth = _drawWidth;
		this.numberList = new ArrayList<Integer>();
	} // NumberOutput(Game _game, int _xPos, int _yPos, int _numCharSize, Pixmap _sprite)
	
	
    public void render()
    { // Render method for the NumberOutput class. This method renders the 
      // number output to screen.
      
    	// Create a graphics variable and set it to the game graphics.
	    Graphics g = this.game.getGraphics();
	    
	    // If the sprite number is not null.
	    if(this.spriteNum != null)
	    {
		    this.numberList.clear();
		    this.numMod = this.numOutput;
		  
		    // If the number is 0, add 0 to the stack.
		    if(this.numMod == 0)
		    {
		        this.numberList.add(0);
		    } // end if
		    
		    // While the number is greater than 0, add the number
		    // modularised with 10 to the stack and then divide the number
		    // by 10.
		    while (this.numMod > 0) 
		    {
		        this.numberList.add(this.numMod % 10);
		        this.numMod = this.numMod / 10;
		    }
		  
		    // Loop through the stack and draw the number.
		    for(int i=0;i<numberList.size();i++)
		    {
			    g.drawPixmap(this.spriteNum, (this.xPosition + ((this.numberList.size() - i)) * this.drawWidth) - this.numCharSizeW, 
					       this.yPosition,this.numberList.get(i) * this.numCharSizeW,0,this.numCharSizeW,this.numCharSizeH);
		    }	   
	    } // end if
    } // public void render(Game _game)
	
    
	public void setXPosition(int _xPos) 
	{ // setXPosition method for the NumberOutput class. This method sets the x position
    // to the passed in value.
		this.xPosition = _xPos; 
	} // public void setXPosition(int _xPos)
	
	
	public void setYPosition(int _yPos) 
	{ // setYPosition method for the NumberOutput class. This method sets the y position
    // to the passed in value.
		this.yPosition = _yPos; 
	} // public void setYPosition(int _yPos)
	
	
	public void setNumberOutput(int _numOut) 
	{ // setNumberOutput method for the NumberOutput class. This method sets the number
	  // to be the passed in value.
		this.numOutput = _numOut; 
	} // public void setNumberOutput(int _numOut) 
	
	
	public void setNumCharSizeW(int _numCharSizeW) 
	{ // setNumCharSizeW method for the NumberOutput class. This method sets the character
	  // width to be the passed in value.
		this.numCharSizeW = _numCharSizeW; 
	} // public void setNumCharSizeW(int _numCharSizeW)
	
	
	public void setNumCharSizeH(int _numCharSizeH) 
	{ // setNumCharSizeH method for the NumberOutput class. This method sets the character
	  // height to be the passed in value.
		this.numCharSizeH = _numCharSizeH; 
	} // public void setNumCharSizeH(int _numCharSizeH)
	
	
	public void setSprite(Pixmap _sprite) 
	{ // setSprite method for the NumberOutput class. This method sets the sprite
	  // that is used to be the passed in value.
		this.spriteNum = _sprite; 
	} // public void setSprite(Pixmap _sprite)
	
	
	public void setDrawWidth(int _drawWidth) 
	{ // setDrawWidth method for the NumberOutput class. This method sets the draw
	  // width to be the passed in value.
		this.drawWidth = _drawWidth; 
	} // public void setDrawWidth(int _drawWidth)
	
	
	public int getXPosition() 
	{ // getXPosition method for the NumberOutput class. This method gets the x
	  // position of the NumberOutput.
		return this.xPosition;
	} // public int getXPosition()
	
	
	public int getYPosition() 
	{ // getYPosition method for the NumberOutput class. This method gets the y
	  // position of the NumberOutput.
		return this.yPosition;
	} // public int getYPosition()
	
	
	public int getNumberOutput() 
	{ // getNumberOutput method for the NumberOutput class. This method gets the 
	  // numOutput.
		return this.numOutput; 
	} // public int getNumberOutput()
	
	
	public int getNumCharSizeW() 
	{ // getNumCharSizeW method for the NumberOutput class. This method gets the character
	  // width of the NumberOutput.
		return this.numCharSizeW;
	} // public int getNumCharSizeW()
	
	
	public int getNumCharSizeH() 
	{ // getNumCharSizeH method for the NumberOutput class. This method gets the character
	  // height of the NumberOutput.
		return this.numCharSizeH;
	} // public int getNumCharSizeH()
	
	
	public Pixmap getSprite() 
	{ // getSprite method for the NumberOutput class. This method gets the sprite
	  // of the NumberOutput.
		return this.spriteNum;
	} // public Pixmap getSprite()
	
	
	public int getDrawWidth() 
	{ // getDrawWidth method for the NumberOutput class. This method gets the draw
	  // width of the NumberOutput.
		return this.drawWidth;
	} // public int getDrawWidth()
	
} // public class NumberOutput 
