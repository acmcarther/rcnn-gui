package resources.datatypes;

public class ControlData {
	private String node1, node2;
	private float activationLevel;
	
	public ControlData(String node1){
		this.node1 = node1;
	}
	
	public ControlData(String name1, float activationLevel){
		node1 = name1;
		this.activationLevel = activationLevel;
	}
	
	public ControlData(String node1, String node2){
		this.node1 = node1;
		this.node2 = node2;
	}
	
	public String getNode1(){
		return node1;
	}
	
	public String getNode2(){
		return node2;
	}
	
	public Float getAL(){
		return activationLevel;
	}

	
}
