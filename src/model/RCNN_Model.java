package model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import resources.datatypes.Node;
import resources.datatypes.NodeData;
import view.RCNN_View;

public class RCNN_Model {
	private RCNN_View view;
	private LinkedHashMap<String, NodeData> nodeMap;
	private LinkedHashMap<String, String[]> forwardEdgeMap;
	private int refreshCount;
	private int nodeDataSize = 500;
	//private LinkedHashMap<String, String[]> backwardEdgeMap;
	

	public void registerView(RCNN_View view) {
		this.view = view;
	}

	public void initialize(int nodeDataSize) {
		nodeMap = new LinkedHashMap<String, NodeData>(20);
		refreshCount = 0;
		this.nodeDataSize = nodeDataSize;
	}

	public Node[] getNodeList() {
		// Declare variables
		int index = 0;
		
	    // convert hash map to node array
	    Node[] nodeList = new Node[nodeMap.size()];
	    
	    // Loop to output nodemap to a NodeList
	    for (Entry<String, NodeData> mapEntry : nodeMap.entrySet()) {
	    	nodeList[index] = new Node(mapEntry.getKey().substring(17), (mapEntry.getValue().peekLast()));
	    	index++;
	    }
	    
	    // return output
		return nodeList;
	}
	
	public boolean hasNodeNamed(String name) {
		return nodeMap.containsKey(name);
	}
	
	public boolean hasEdgeBetween(String parentName, String childName) {
		// Check if the parent exists in the edge map
		if(!forwardEdgeMap.containsKey(parentName)){
			return false;			
		}
		
		// Grab the list of children nodes from the parent edge
		String[] childrenNames = forwardEdgeMap.get(parentName);
		
		// Parse children list for the matching child
		for(String child : childrenNames){
			if (child == childName){
				return true;
			}
		}
		
		return false;
	}

	public void updateNodeMap(LinkedHashMap<String, Float> newNodeData) {
		
		// Define variables
		Entry<String,Float> floatEntry;
		Entry<String,NodeData> nodeEntry;
		String tempKey;
		NodeData tempData;
		
		// Build a new data iterator
		Iterator<Entry<String,Float>> newDataIterator = newNodeData.entrySet().iterator();

		// Iterate through Linked Hash Map
		while(newDataIterator.hasNext()){
			
			// Get next entry
			floatEntry = newDataIterator.next();
			
			// Get next key
			tempKey = floatEntry.getKey();
			
			if (nodeMap.containsKey(tempKey)){
				// If we already have this key, simply update its values
				nodeMap.get(tempKey).enqueue(floatEntry.getValue());
			}
			else{
				// otherwise, generate a new entry
				nodeMap.put(tempKey, new NodeData(nodeDataSize, floatEntry.getValue()));
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
			if (!tempData.wasUpdated()){
				// If we didnt update this data, it means its not in the sim,
				//	Thus we need to remove it
				nodeMap.remove(nodeEntry.getKey());
			}

		}
		
		
		// TODO: Migrate this functionality to another thread
		if(refreshCount < 1){
			view.updateNodeList(getNodeList());
			refreshCount = 50;
		}
		refreshCount--;
	}
	
	public LinkedHashMap<String, NodeData> getNodeMap(){
		return nodeMap;
	}
	
	public int getDataResolution(){
		return nodeDataSize;
	}

	public int getNodeCount() {
		return nodeMap.size();
	}

}
