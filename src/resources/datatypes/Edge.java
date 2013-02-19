package dataTypes;

public class Edge{
	private String source;
	private String destination;
	private float propDelay;
	
	public Edge(String source, String destination, float propDelay){
		this.source = source;
		this.destination = destination;
	}
	
	public String getSource(){
		return source;
	}
	
	public String getDestination(){
		return destination;
	}
	
	public float getPropDelay(){
		return propDelay;
	}
	
	// Overriding for the JList
	public String toString(){
		//return (source + " to " + destination + ", " + propDelay);
		return (destination + ", " + propDelay);
	}
}
