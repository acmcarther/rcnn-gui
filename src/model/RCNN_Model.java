package model;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import resources.datatypes.Node;
import view.RCNN_View;

public class RCNN_Model {
	private RCNN_View view;
	private LinkedHashMap<String, Float> nodeMap;
	private LinkedHashMap<String, String[]> forwardEdgeMap;
	//private LinkedHashMap<String, String[]> backwardEdgeMap;

	public void registerView(RCNN_View view) {
		this.view = view;
	}

	public void initialize() {
		
	}

	public Node[] getNodeList() {
		// Declare variables
		int index = 0;
		
	    // convert hash map to node array
	    Node[] nodeList = new Node[nodeMap.size()];
	    
	    // Loop to output nodemap to a NodeList
	    for (Entry<String, Float> mapEntry : nodeMap.entrySet()) {
	    	nodeList[index] = new Node(mapEntry.getKey().substring(17), (mapEntry.getValue()));
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

	public void setNodeMap(LinkedHashMap<String, Float> nodeMap) {

		// Update the model's NodeList
		this.nodeMap = nodeMap;
		
		// TODO: Migrate this functionality to another thread
		view.updateNodeList(getNodeList());
	}



}
