package controller.forcegraph;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.Map.Entry;

import model.RCNN_Model;

import resources.datatypes.Node;
import resources.datatypes.PhysicsNode;

/*
	A credit to Brad Smith's Coding Blog. Concepts for this implementation 
	are taken from there. URL: http://www.brad-smith.info/blog/archives/129
*/

public class ForceGraphPHYS {
	// Class Variables
	LinkedHashMap<String,PhysicsNode> physNodeMap;
	Vector<PhysicsNode> physNodeList;
	Vector<Node> nodeList;
	RCNN_Model model;
	
	public ForceGraphPHYS(RCNN_Model model){
		physNodeMap = new LinkedHashMap<String,PhysicsNode>();
		physNodeList = new Vector<PhysicsNode>();
		this.model = model;
		updateGraph(model.getNodeList());
	}
	
	public void runPhysicsTick(){
		Iterator<Entry<String,PhysicsNode>> originIterator = physNodeMap.entrySet().iterator();
		Iterator<Entry<String,PhysicsNode>> affectedIterator;getClass();
		PhysicsNode originNode;
		PhysicsNode affectedNode;
		Entry<String,PhysicsNode> originEntry;
		String originName;
		String affectedName;
		Entry<String,PhysicsNode> affectedEntry;
		
		while(originIterator.hasNext()){
			
			affectedIterator = physNodeMap.entrySet().iterator();
			originEntry = originIterator.next();
			originNode = originEntry.getValue();
			originName = originEntry.getKey();
			
			// Clear origin node child list
			originNode.clearChildNodes();
			
			while(affectedIterator.hasNext()){
				affectedEntry = affectedIterator.next();
				affectedNode = affectedEntry.getValue();
				affectedName = affectedEntry.getKey();
				if( originName != affectedName ){
					if(model.hasEdgeBetween(originName,affectedName)){
						// Is Parent, add to child list
						originNode.addChildNode(affectedNode);
						
						// Apply attractive force for linked nodes
						originNode.addAttractiveForce(affectedNode);
					}
					else if( model.hasEdgeBetween(affectedName, originName) ){
						// Apply attractive force for linked nodes
						originNode.addAttractiveForce(affectedNode);
					}
					else{
						// Apply repulsive force for unrelated nodes
						originNode.addRepulsiveForce(affectedNode);
					}
				}	
			}	
		}
		
		// Apply all forces
		originIterator = physNodeMap.entrySet().iterator();
		while(originIterator.hasNext()){
			originEntry = originIterator.next();
			//System.out.println("before: "+originEntry.getKey() + ": " + originEntry.getValue().getX() + ", " + originEntry.getValue().getY());
			originEntry.getValue().applyForces();
			//System.out.println("after: "+originEntry.getKey() + ": " + originEntry.getValue().getX() + ", " + originEntry.getValue().getY());
		}
		
		
	}
	
	public void updateGraph(Node[] nodeList){
		
		// Function Variables
		PhysicsNode tempNode;
		Entry<String,PhysicsNode> nodeEntry;
		String tempKey;

		// Iterate through updated nodelist
		for(int index = 0; index < nodeList.length; index++){
			
			tempKey = nodeList[index].getName();
					
			// If we have the key, just update it
			if (physNodeMap.containsKey(tempKey)){
				// If we already have this key, simply update its values
				tempNode = physNodeMap.get(tempKey);
				tempNode.setModifiedFlag();
				tempNode.setAL(nodeList[index].getAL());
			}
			// Otherwise, we need to add it
			else{
				// otherwise, generate a new entry
				physNodeMap.put(tempKey, new PhysicsNode(tempKey, nodeList[index].getAL()));
				physNodeMap.get(tempKey).setModifiedFlag();
			}
		}
				
		// Build a node map iterator
		Iterator<Entry<String,PhysicsNode>> mapIterator = physNodeMap.entrySet().iterator();

		// Iterate through our new data set
		while(mapIterator.hasNext()){
					
			// Get next entry
			nodeEntry = mapIterator.next();
					
			// Get next data set
			tempNode = nodeEntry.getValue();
					
			// Check if this element was updated
			if (!tempNode.wasModified()){
				// Remove unmodified entry
				mapIterator.remove();
			}
			else{
				tempNode.clearModifiedFlag();
			}
		}
	}
	
	public PhysicsNode[] getPhysicsNodes(){
		int index = 0;
		
	    // Declare a node array of size equal to the node map
	    PhysicsNode[] nodeList = new PhysicsNode[physNodeMap.size()];
	    
	    // Iterate through all of the entries in the node map
	    for (Entry<String, PhysicsNode> mapEntry : physNodeMap.entrySet()) {
	    	// Get the name of the node and it's activation levels
	    	nodeList[index] = new PhysicsNode(mapEntry.getValue());
	    	
	    	// Move to the next element in the node array
	    	index++;
	    }
	    
	    // return the node array
		return nodeList;
	}
}
