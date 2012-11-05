// ----------------------------------------------------------------
// Space Invaders.
// AndroidFileIO.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import yes.team.framework.FileIO;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

public class AndroidFileIO implements FileIO 
{
  // Create references to the asset managers and the storage paths.
  AssetManager assets;
  String externalStoragePath;
  String internalStoragePath;
  Context context;
  
  public AndroidFileIO(AssetManager _assets, Context _context)
  {
    // Assign the assets and storage paths.
    this.assets = _assets;
    this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    this.internalStoragePath = File.separator;
    this.context = _context;
  } // public AndroidFileIO(AssetManager assets)

  
  @Override
  public InputStream readAsset(String _fileName) throws IOException 
  { // Reads an asset passed in from the filename parameter. Throws an IOException if failed.
    return assets.open(_fileName);
  } // public InputStream readAsset(String fileName) throws IOException

  
  @Override
  public InputStream readFileExternal(String _fileName) throws IOException 
  { // Reads a file saved to external storage. Takes the filename as a parameter and throws an IOException if failed.
    return new FileInputStream(externalStoragePath + _fileName);
  } // public InputStream readFile(String fileName) throws IOException

  
  @Override
  public OutputStream writeFileExternal(String _fileName) throws IOException 
  { // Writes a file to the external storage. Takes a filename as a parameter and throws an IOException if failed.
    return new FileOutputStream(externalStoragePath + _fileName);
  } // public OutputStream writeFile(String fileName) throws IOException
  
  
  @Override
  public InputStream readFileInternal(String _fileName) throws IOException
  { // Reads a file from the devices internal storage. Takes a filename as a parameter and throws an IOException if failed.
    return context.openFileInput(_fileName);
  } // public InputStream readFileInternal(String fileName) throws IOException
  
  
  @Override
  public OutputStream writeFileInternal(String _fileName) throws IOException
  { // Writes a file to the internal device storage. Takes a filename as a parameter and throws an IOException if failed.
    return context.openFileOutput(_fileName, Context.MODE_PRIVATE);
  } // public OutputStream writeFileInternal(String fileName) throws IOException

  
} // public class AndroidFileIO implements FileIO
