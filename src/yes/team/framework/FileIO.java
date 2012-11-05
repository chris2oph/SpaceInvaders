// ----------------------------------------------------------------
// Space Invaders.
// FileIO.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO 
{
  // Reads an asset passed in from the filename parameter. Throws an IOException if failed.
  public InputStream readAsset(String _fileName) throws IOException;
  
  // Reads a file saved to external storage. Takes the filename as a parameter and throws an IOException if failed.
  public InputStream readFileExternal(String _fileName) throws IOException;
  
  // Writes a file to the external storage. Takes a filename as a parameter and throws an IOException if failed.
  public OutputStream writeFileExternal(String _fileName) throws IOException;
  
  // Reads a file from the devices internal storage. Takes a filename as a parameter and throws an IOException if failed.
  public InputStream readFileInternal(String _fileName) throws IOException;
  
  // Writes a file to the internal device storage. Takes a filename as a parameter and throws an IOException if failed.
  public OutputStream writeFileInternal(String _fileName) throws IOException;
  
} // public interface FileIO 
