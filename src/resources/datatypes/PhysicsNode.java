package resources.datatypes;

public class PhysicsNode {
    
    // TODO: make this a common interface of Node
    // Class Variables
    String name;
    float activLevel;
    boolean wasModified;
    
    Vector position;
    Vector forces;
    Vector velocity;

    public PhysicsNode(String name, float activLevel) {
        this.name = name;
        this.activLevel = activLevel;

        // Initialize physics traits
        position = new Vector(Math.random()*50,Math.random()*50);
        forces = new Vector(0,0);
        velocity = new Vector(0,0);
    }
    
    public PhysicsNode(Node basisNode) {
        this.name = basisNode.getName();
        this.activLevel = basisNode.getAL();

        // Set force Vector
        position = new Vector(Math.random()*50,Math.random()*50);
        forces = new Vector(0,0);
        velocity = new Vector(0,0);
    }
    
    public PhysicsNode(PhysicsNode basisNode) {
        this.name = basisNode.getName();
        this.activLevel = basisNode.getAL();
        
        // Generate random initial positions from 0 to 5
        position = basisNode.getPos();
        
        // Set force Vector
        forces = basisNode.getForces();
        
        // Set modified
        wasModified = basisNode.wasModified();
    }
    
    public void setEqualTo(PhysicsNode otherNode){       
        name = otherNode.getName();
        activLevel = otherNode.getAL();
        
        position = otherNode.getPos();
        forces = otherNode.getForces();
        velocity = otherNode.getVelocity();
        wasModified = otherNode.wasModified();
    }
    
    public void addRepulsiveForce(PhysicsNode otherNode){
        double absDist = getDistanceFrom(otherNode.getPos().getX(),otherNode.getPos().getY());
        double workingDist =  Math.max(absDist, 0.001);
        double force = -(20000.0f / (Math.pow(workingDist, 2)));
        Vector forceVec = new Vector();
        
        // Set the preliminary force vector to the displacement
        forceVec.setX(otherNode.getPos().getX() - this.getPos().getX());
        forceVec.setY(otherNode.getPos().getY() - this.getPos().getY());
        
        // Weight the vector by the repulsive force
        forceVec.normalize();
        forceVec.multScalar(force);
        
        // Add force
        forces.addVector(forceVec);
    }
    
    public void addAttractiveForce(PhysicsNode otherNode){
        double absDist = getDistanceFrom(otherNode.getPos().getX(),otherNode.getPos().getY());
        double workingDist =  Math.max(absDist, 0.01);
        double force = 0.05f  * Math.max(workingDist - 5, 0);
        Vector forceVec = new Vector();
        
        // Set the preliminary force vector to the displacement
        forceVec.setX(otherNode.getPos().getX() - this.getPos().getX());
        forceVec.setY(otherNode.getPos().getY() - this.getPos().getY());
        
        // Weight the vector by the attractive force
        //System.out.println("AF:         " + forceVec);
        forceVec.normalize();
        forceVec.multScalar(force);
        //System.out.println("weightedAF: " + forceVec);
        
        // Add force
        forces.addVector(forceVec);
    }
    
    public void applyForces(){
        
    	addGravity();
        // Add the force into the velocity
        velocity.addVector(forces);
        velocity.multScalar(0.75);
    	position.addVector(velocity);
       
        // Clear force vector
        clearForces();
    }
    
    public void addGravity(){
    	double absDist = getDistanceFrom(0,0);
        double workingDist =  Math.max(absDist, 0.01);
        double force = 0.005f  * Math.max(workingDist - 20, 0);
        Vector forceVec = new Vector();
        
        // Set the preliminary force vector to the displacement
        forceVec.setX(- this.getPos().getX());
        forceVec.setY(- this.getPos().getY());

        // Weight the vector by the attractive force
        forceVec.normalize();
        forceVec.multScalar(force);
        
        // Add force
        forces.addVector(forceVec);
    }
    
    public double getDistanceFrom(double inXPos, double inYPos){
        return Math.hypot((position.x - inXPos), (position.y - inYPos));
    }
    
    public Vector getPos(){
        return position;
    }
    
    public float getAL(){
        return activLevel;
    }
    
    public Vector getVelocity(){
    	return velocity;
    }
    
    public String getName(){
        return name;
    }
    
    public Vector getForces(){
        return forces;
    }
    
    public void clearForces(){
    	forces.setXY(0,0);
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