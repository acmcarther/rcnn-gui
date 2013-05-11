package model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Observable;

import resources.datatypes.Edge;
import resources.datatypes.Node;
import resources.datatypes.NodeData;

/**
 * RCNN_Model is the model component of the MVC part of the RCNN 
 * visualization. It contains:
 * <ul>
 * <li>Persistent Node History
 * <li>Forward Edge Information
 * <li>Backward Edge Information
 * </ul>
 * @author      Alexander McArther
 */
public class RCNN_Model extends Observable {
	// Class Variables
	private int nodeDataSize;
	private LinkedHashMap<String, NodeData> nodeMap;
	private LinkedHashMap<String, String[]> forwardEdgeMap; // TODO: Finalize edge maps
	//private LinkedHashMap<String, String[]> backwardEdgeMap;

	
	/**
	 * Creates the model of the RCNN Visualization. In the 
	 * process it generates a LinkedHashMap for the node list.
	 *
	 * @param  nodeDataSize - Integer specifying how many data entries
	 * per node to maintain at a time.
	 */
	public RCNN_Model(int nodeDataSize) {
		nodeMap = new LinkedHashMap<String, NodeData>(20);
		forwardEdgeMap = new LinkedHashMap<String, String[]>(20);
		this.nodeDataSize = nodeDataSize;
	}

	/**
	 * Breaks the internal LinkedHashMap into an array with 
	 * representative elements for each node.
	 *
	 * @return  Node array consisting of all of the nodes in the
	 * structure.
	 * @see Node
	 */
	public Node[] getNodeList() {
		// Declare variables
		int index = 0;
		
	    // Declare a node array of size equal to the node map
	    Node[] nodeList = new Node[nodeMap.size()];
	    
	    // Iterate through all of the entries in the node map
	    for (Entry<String, NodeData> mapEntry : nodeMap.entrySet()) {
	    	// Get the name of the node and it's activation levels
	    	nodeList[index] = new Node(mapEntry.getKey().substring(17), (mapEntry.getValue().peekLast().getAL()));
	    	
	    	// Move to the next element in the node array
	    	index++;
	    }
	    
	    // return the node array
		return nodeList;
	}
	
	/**
	 * Attempts to locate an edge in the forward edge map between
	 * the two input nodes.
	 * @param parentName - Name of the origin node.
	 * @param childName - Name of the terminating node.
	 * @return  <code>true</code> if the edge was found
	 * in the internal forward edge map, <code>false</code> otherwise.
	 */
	public boolean hasEdgeBetween(String parentName, String childName) {
		// Check if the parent exists in the edge map
		if(!forwardEdgeMap.containsKey(parentName)){
			return false;			
		}
		
		// Grab the list of children nodes from the parent edge
		String[] childrenNames = forwardEdgeMap.get(parentName);
		
		// Parse children list for the matching child
		for(String child : childrenNames){
			if (child.equals(childName)){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Uses new input data in the form of a LinkedHashMap to update
	 * the model's version of the data. It adds nodes if they aren't
	 * already in the structure, and removes them if they weren't in
	 * this latest batch of data
	 * 
	 * @param newNodeData - A LinkedHashMap containing string keys and
	 * float data elements corresponding to new data from some source
	 */
	public void updateNodeMap(LinkedHashMap<String, Float> newNodeData) {
		
		// Define function variables
		Entry<String,Float> floatEntry;
		Entry<String,NodeData> nodeEntry;
		String tempKey;
		NodeData tempData;
		
		// Build a new data iterator
		Iterator<Entry<String,Float>> newDataIterator = newNodeData.entrySet().iterator();

		// Iterate through updated Linked Hash Map
		while(newDataIterator.hasNext()){
			
			// Get next entry
			floatEntry = newDataIterator.next();
			
			// Get next key
			tempKey = floatEntry.getKey();
			
			// If we have the key, just update it
			if (nodeMap.containsKey(tempKey)){
				// If we already have this key, simply update its values
				nodeMap.get(tempKey).enqueue(new Node(tempKey, floatEntry.getValue()));
			}
			// Otherwise, we need to add it
			else{
				// otherwise, generate a new entry
				nodeMap.put(tempKey, new NodeData(nodeDataSize, new Node(tempKey, floatEntry.getValue())));
			}
		}
		
		// Build a node map iterator
		Iterator<Entry<String,NodeData>> mapIterator = nodeMap.entrySet().iterator();

		// Iterate through our new data set
		while(mapIterator.hasNext()){
			
			// Get next entry
			nodeEntry = mapIterator.next();
			
			// Get next data set
			tempData = nodeEntry.getValue();
			
			// Check if this element was updated
			if (!tempData.wasUpdated()){
				// Remove unmodified entry
				nodeMap.remove(nodeEntry.getKey());
			}
		}
		
		// Let everyone know we've updated our data
		setChanged();
		
		// Tell the observers about our new data
		notifyObservers(this);
	}
	
	public void updateEdgeMap(LinkedHashMap<String, String> newEdgeData) {
		
		// Define function variables
		Entry<String,String> edgeEntry;
		String key;
		String[] separatedEdges;
		
		// Build a new data iterator
		Iterator<Entry<String,String>> newDataIterator = newEdgeData.entrySet().iterator();

		// Iterate through updated Linked Hash Map
		while(newDataIterator.hasNext()){
			
			// Get next entry
			edgeEntry = newDataIterator.next();
			key = edgeEntry.getKey();

			// Split the input string
			separatedEdges = edgeEntry.getValue().split(" ");
			
			// Update entry in the map
			if( separatedEdges == null){
				System.out.println("what the fuck dude");
			}
			forwardEdgeMap.put(key, separatedEdges);
			
		}
		// TODO: purge values from edge list that did not get updated
	}
	
	/**
	 * Retrieves the internal LinkedHashMap containing the node data.
	 * 
	 * @return LinkedHashMap - Keyed by node name containing NodeData entries
	 * @see NodeData
	 */
	public LinkedHashMap<String, NodeData> getNodeMap(){
		return nodeMap;
	}
	
	/**
	 * Retrieves the internal LinkedHashMap containing the edge data.
	 * 
	 * @return LinkedHashMap - Keyed by node name containing Node Edges
	 */
	public LinkedHashMap<String, String[]> getForwardEdgeMap(){
		return forwardEdgeMap;
	}
	
	/**
	 * Retrieves the number of entries in each NodeData structure.
	 * 
	 * @return The size of the NodeData internal deque.
	 * @see NodeData
	 */
	public int getDataResolution(){
		return nodeDataSize;
	}

	/**
	 * Retrieves the number of nodes in the internal LinkedHashMap.
	 * 
	 * @return The number of keys in the LinkedHashMap
	 */
	public int getNodeCount() {
		return nodeMap.size();
	}
	
	/**
	 * Requests from the internal LinkedHashMap to see if a key
	 * like the input parameter exists as a node.
	 * 
	 * @param name - The name of the node to search for.
	 * @return <code>true</code> if the node exists, <code>
	 * false</code> otherwise.
	 */
	public boolean hasNodeNamed(String name) {
		return nodeMap.containsKey(name);
	}

	public Node[] getEdgeList(Node elementAt) {
		// Grab the node data symbolizing that element
		String[] edgeSet = forwardEdgeMap.get(elementAt.getName());
		
	    // Declare a node array of size equal to the node map
	    Node[] nodeList = new Node[edgeSet.length];
	    
	    // Iterate through all of the entries in the nodedata
	    for(int stringIndex = 0; stringIndex < edgeSet.length; stringIndex++) {
	    	
	    	// Get the name of the node and it's activation levels
	    	nodeList[stringIndex] = new Node(edgeSet[stringIndex], 0);
	    	
	    }
	    
	    // return the node array
		return nodeList;
	}

	public boolean hasEdgesFor(Node selectedValue) {
		// TODO Auto-generated method stub
		return forwardEdgeMap.containsKey(selectedValue.getName());
	}
	
}
