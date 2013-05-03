package resources.datatypes;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;


// Data Structure: Node Data
/*
 * This Data Structure is built from an LinkedBlockingDeque. I
 * have limited its functions through this interface.
 * It is intended only to provide a circular data 
 * structure for the float data of the nodes. It is
 * initialized with out of range values so that it 
 * can maintain a history of data. Please change that
 * value if the node implementation changes.
 */

public class NodeData {
	private int size;
	private boolean updated;
	private LinkedBlockingDeque<Node> Data;
	
	public NodeData(int size){
		// Set size
		this.size = size;
		
		// Out of range data value set here
		Data = new LinkedBlockingDeque<Node>(size);
		
		// Loop to fill queue with out of range values
		for(int i = 0; i < size; i++){
			Data.offerLast(new Node("dummy", -70.0f));
		}
		
		// Don't let us get culled on our first tick
		updated = true;
	}
	public NodeData(int size, Node starterData){
		// Set size
		this.size = size;
		
		
		// Out of range data value set here
		Data = new LinkedBlockingDeque<Node>(size);
		
		// Loop to fill queue with out of range values
		for(int i = 0; i < size-1; i++){
			Data.offerLast(new Node("dummy", -70.0f));
		}
		
		// Enqueue our know value
		Data.offerLast(starterData);
		
		// Don't let us get culled on our first tick
		updated = true;
		
	}
	
	public void enqueue(Node value){
		// Pop off the oldest data
		Data.remove();
		
		// Add newer data
		Data.offerLast(value);
		
		// Toggle update
		updated = true;
		
		// Set maxima status if applicable
		if(size > 5){
			int tempLoc = size - 3;
			float mean, percentDifference;
			float activLevel;
			Node nodeArray[] = new Node[size];
			Data.toArray(nodeArray);
			activLevel = nodeArray[tempLoc].getAL();
			
			// TODO: This needs a facelift, but at least it works
			
			mean = (nodeArray[tempLoc-2].getAL() + nodeArray[tempLoc-1].getAL() + nodeArray[tempLoc].getAL() + 
					   nodeArray[tempLoc+1].getAL() + nodeArray[tempLoc+2].getAL())/5;
				percentDifference = nodeArray[tempLoc].getAL()/mean;
			
			if(activLevel >= nodeArray[tempLoc+1].getAL() && activLevel >= nodeArray[tempLoc-1].getAL() &&
					activLevel >= nodeArray[tempLoc+2].getAL() && activLevel >= nodeArray[tempLoc-2].getAL() &&
							nodeArray[tempLoc+1].getAL() >= nodeArray[tempLoc+2].getAL() &&
							nodeArray[tempLoc-1].getAL() >= nodeArray[tempLoc-2].getAL()){
				if(percentDifference > 1.1 || percentDifference < 0.97 ){
					nodeArray[tempLoc].setMaxima();	
				}

			}
		}
	}
	
	public boolean wasUpdated(){
		if(updated){
			updated = false;
			return true;
		}
		return false;
	}
	
	public Node peekLast(){
		// Grab the newest data
		return Data.peekLast();
	}
	
	public int getSize(){
		// Return size of the structure (for making oscilloscope)
		return size;
	}
	
	public Iterator<Node> iterator(){
		// Return a functional iterator
		return Data.iterator();
	}
	
	public Float getNodeDataAt(int index){
		return (Float) Data.toArray()[index];
	}
	
}
