// ----------------------------------------------------------------
// Space Invaders.
// AndroidSound.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework.implementation;

import android.media.SoundPool;
import yes.team.framework.Sound;

public class AndroidSound implements Sound 
{
	int soundId;
	SoundPool soundPool;
	
	
	public AndroidSound(SoundPool _soundPool, int _soundId) 
	{
		this.soundId = _soundId;
		this.soundPool = _soundPool;
	} // public AndroidSound(SoundPool _soundPool, int _soundId)
	
	
	@Override
	public void play(float _volume) 
	{ // Plays the sound out of the volume.
		this.soundPool.play(soundId, _volume, _volume, 0, 0, 1);
	} // public void play(float _volume)

	
	@Override
	public void dispose() 
	{ // Disposes of the sound from memory.
		this.soundPool.unload(soundId);
	} // public void dispose() 
	
} // public class AndroidSound implements Sound 
