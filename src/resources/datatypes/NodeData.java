package resources.datatypes;

import java.util.ArrayDeque;
import java.util.Iterator;

// Data Structure: Node Data
/*
 * This Data Structure is built from an ArrayDeque. I
 * have limited its functions through this interface.
 * It is intended only to provide a circular data 
 * structure for the float data of the nodes. It is
 * initialized with out of range values so that it 
 * can maintain a history of data. Please change that
 * value if the node implementation changes.
 */

public class NodeData {
	private int size = 500;
	private boolean updated;
	private ArrayDeque<Float> Data;
	
	public NodeData(){
		// Out of range data value set here
		Data = new ArrayDeque<Float>(size);
		
		// Loop to fill queue with out of range values
		for(int i = 0; i < size; i++){
			Data.offerLast(-70.0f);
		}
		
		// Don't let us get culled on our first tick
		updated = true;
	}
	public NodeData(float starterData){
		// Out of range data value set here
		Data = new ArrayDeque<Float>(size);
		
		// Loop to fill queue with out of range values
		for(int i = 0; i < size-1; i++){
			Data.offerLast(-70.0f);
		}
		
		// Enqueue our know value
		Data.offerLast(starterData);
		
		// Don't let us get culled on our first tick
		updated = true;
		
	}
	
	public void enqueue(float value){
		// Pop off the oldest data
		Data.remove();
		
		// Add newer data
		Data.offerLast(value);
		
		// Toggle update
		updated = true;
	}
	
	public boolean wasUpdated(){
		if(updated){
			updated = false;
			return true;
		}
		return false;
	}
	
	public float peekLast(){
		// Grab the newest data
		return Data.peekLast();
	}
	
	public int getSize(){
		// Return size of the structure (for making oscilloscope)
		return size;
	}
	
	public Iterator<Float> iterator(){
		// Return a functional iterator
		return Data.iterator();
	}
	
}
