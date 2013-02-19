package resources.datatypes;

public class Node{
	private String name;
	private float activLevel;
	private float state;
	
	public Node(String name, Float activLevel){
		this.name = name;
		this.activLevel = activLevel;
	}
	
	public String getName(){
		return name;
	}
	
	public float getAL(){
		return activLevel;
	}
	
	// Overriding for the JList
	public String toString(){
		return (name + ", " + activLevel);
	}
}
