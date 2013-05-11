package resources.datatypes;

public class Vector {
	// Class Variables
	public double x,y;
	
	public Vector(){
		this.x = this.y = 0;
	}
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Vector(Vector otherVec){
		this.x = otherVec.getX();
		this.y = otherVec.getY();
	}
	
	public void addVector(Vector secondVec){
		this.x = this.x + secondVec.x;
		this.y = this.y + secondVec.y;
	}
	
	public void multScalar(double scalar){
		this.x *= scalar;
		this.y *= scalar;
	}
	
	public void normalize(){
		double magnitude = Math.hypot(x, y);
		this.x /= magnitude;
		this.y /= magnitude;
	}
	
	public String toString(){
		return new String("(" + getX() + ", " + getY() + ")");
	}
	
	public void setXY(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}

}
