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
	private int size = 0;
	private boolean updated;
	private ArrayDeque<Float> Data;
	
	public NodeData(){
		// Out of range data value set here
		Data = new ArrayDeque<Float>(500);
		
		// Loop to fill queue with out of range values
		for(int i = 0; i < 500; i++){
			Data.offerLast(-70.0f);
		}
	}
	public NodeData(float starterData){
		// Out of range data value set here
		Data = new ArrayDeque<Float>(500);
		
		// Loop to fill queue with out of range values
		for(int i = 0; i < 499; i++){
			Data.offerLast(-70.0f);
		}
		
		// Enqueue our know value
		Data.offerLast(starterData);
		
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
	
	public float pollLast(){
		// Grab the newest data
		return Data.pollLast();
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
