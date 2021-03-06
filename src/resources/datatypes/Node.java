package resources.datatypes;

public class Node{
	protected String name;
	protected float activLevel;
	protected boolean isMaxima;

	
	public Node(String name, float activLevel){
		isMaxima = false;
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

	public void setMaxima() {
		isMaxima = true;
	}
	
	public boolean getMaxima(){
		return isMaxima;
	}
}

