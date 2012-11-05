// ----------------------------------------------------------------
// Space Invaders.
// Pool.java
// Code modified from Beginning Android Games [2011] by Mario Zechner Apress.
// Team: Elliott Minns, Martin Stanger, Phillip Scott, Chris Lemin, 
//       Thomas Gillett
// ----------------------------------------------------------------

package yes.team.framework.implementation;

import java.util.ArrayList;
import java.util.List;

public class Pool<T>
{
	public interface PoolObjectFactory<T> 
	{
		public T createObject();
	} // public interface PoolObjectFactory<T>
	
	private final List<T> freeObjects;
	private final PoolObjectFactory<T> factory;
	private final int maxSize;
	
	
	public Pool(PoolObjectFactory<T> _factory, int _maxSize) 
	{
		this.factory = _factory;
		this.maxSize = _maxSize;
		this.freeObjects = new ArrayList<T>(maxSize);
	} // public Pool(PoolObjectFactory<T> _factory, int _maxSize)
	
	
	public T newObject() 
	{
		T object = null;
		if(freeObjects.size() == 0)
		{
			object = factory.createObject();
		} // end if
		else
		{
			object = freeObjects.remove(freeObjects.size() - 1);
		} // end else
		
		return object;
	} // public T newObject()
	
	
	public void free(T _object) 
	{
		if (freeObjects.size() < maxSize)
		{
			freeObjects.add(_object);
		} // end if
	} // public void free(T _object)
	
} // public class Pool<T>
