package resources.datatypes;

public class PhysicsNode {
	
	// TODO: make this a common interface of Node
	// Class Variables
	double xPos, yPos;
	String name;
	float activLevel;
	boolean wasModified;
	
	Vector forces;
	Vector velocity;

	public PhysicsNode(String name, float activLevel) {
		this.name = name;
		this.activLevel = activLevel;
		
		// Generate random initial positions from 0 to 5
		xPos = (Math.random() * 20);
		yPos = (Math.random() * 20);
		
		// Set force Vector
		forces = new Vector(0,0);
		
		velocity = new Vector(0,0);
	}
	
	public PhysicsNode(Node basisNode) {
		this.name = basisNode.getName();
		this.activLevel = basisNode.getAL();
		
		// Generate random initial positions from 0 to 5

		xPos = (Math.random() * 20);
		yPos = (Math.random() * 20);

		
		// Set force Vector
		forces = new Vector(0,0);
	}
	
	public PhysicsNode(PhysicsNode basisNode) {
		this.name = basisNode.getName();
		this.activLevel = basisNode.getAL();
		
		// Generate random initial positions from 0 to 5
		xPos = basisNode.getX();
		yPos = basisNode.getY();
		
		// Set force Vector
		forces = basisNode.getForces();
		
		// Set modified
		wasModified = basisNode.wasModified();
	}
	
	public void setEqualTo(PhysicsNode otherNode){
		xPos = otherNode.getX();
		yPos = otherNode.getY();
		name = otherNode.getName();
		activLevel = otherNode.getAL();
	}
	
	public void addRepulsiveForce(PhysicsNode otherNode){
		double distance =  Math.max(getDistanceFrom(otherNode.getX(),otherNode.getY()), 1);
		double force = -(10000.0f / (Math.pow(distance, 2)));
		double angle = getAngleTo(otherNode.getX(), otherNode.getY());
		
		// add forces
		forces.addVectors(new Vector(force,angle));
	}
	
	public void addAttractiveForce(PhysicsNode otherNode){
		double distance =  Math.max(getDistanceFrom(otherNode.getX(),otherNode.getY()), 1);
		double force = 0.1f  * Math.max(distance - 5, 0);
		double angle = getAngleTo(otherNode.getX(), otherNode.getY());
		
		// add forces
		forces.addVectors(new Vector(force,angle));
	}
	
	public void applyForces(){
		forces.addVectorXY(xPos, yPos);
		if( getDistanceFrom(forces.getXComponent(),forces.getYComponent()) > 5){
			xPos = forces.getXComponent();
			yPos = forces.getYComponent();
		}
		
		velocity.addVectors(forces);
		// Dampening
		velocity.prodScalarVector(0.5);
		forces.setForces(0,0);
	}
	
	public void applyVelocity(){
		Vector currentPos = new Vector();
		currentPos.setFromXY(xPos, yPos);
		currentPos.addVectors(velocity);
		xPos = currentPos.getXComponent();
		yPos = currentPos.getYComponent();
	}
	
	public double getDistanceFrom(double inXPos, double inYPos){
		return Math.hypot((xPos - inXPos), (yPos - inYPos));
	}
	
	public double getAngleTo(double inXPos, double inYPos){
		return Math.atan((inYPos-yPos)/(inXPos-xPos));
	}

	public double getX(){
		return xPos;
	}
	
	public double getY(){
		return yPos;
	}
	
	public float getAL(){
		return activLevel;
	}
	
	public String getName(){
		return name;
	}
	
	public Vector getForces(){
		return forces;
	}
	
	public void setModifiedFlag(){
		wasModified = true;
	}
	
	public void clearModifiedFlag(){
		wasModified = false;
	}
	
	public boolean wasModified(){
		return wasModified;
	}

	public void setAL(float al) {
		activLevel = al;
		
	}
	
}
