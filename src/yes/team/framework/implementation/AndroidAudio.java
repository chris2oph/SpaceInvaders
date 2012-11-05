// ----------------------------------------------------------------
// Space Invaders.
// AndroidAudio.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework.implementation;

import java.io.IOException;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import yes.team.framework.Audio;
import yes.team.framework.Sound;

public class AndroidAudio implements Audio 
{
  // Create a reference to the asset manager.
	AssetManager assets;
	// Create a reference to the sound pool.
	SoundPool soundPool;
	
	public AndroidAudio(Activity _activity) 
	{ // Constructor for the android audio.
	  
	  // Allow volume control.
		_activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// Assign the references of sound pool and asset. 
		this.assets = _activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	} // public AndroidAudio(Activity activity)
	
	@Override
	public Sound newSound(String filename) 
	{ // Creates a new sound based on the filename parameter passed in.
		try 
		{
		  // Create a new sound and load it into the sound pool
			AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			int soundId = soundPool.load(assetDescriptor, 0);
			return new AndroidSound(soundPool, soundId);
		} // try
		catch(IOException e) 
		{
			throw new RuntimeException("Couldn't load sound '" + filename + "'");
		} // catch(IOException e)
		
	} // public Sound newSound(String filename) 
	
} // public class AndroidAudio implements Audio