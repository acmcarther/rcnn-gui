package resources.datatypes;

public class Vector {
	// Class Variables
	public static double DAMPENING_CONST = 0.5;
	public double magnitude, angle;
	
	public Vector(){
		this.magnitude = 0;
		this.angle = 0;
	}
	
	public Vector(double magnitude, double angle){
		this.magnitude = magnitude;
		this.angle = angle;
		
		if( this.magnitude < 0 ){
			this.magnitude = -this.magnitude;
			this.angle = (Math.PI + this.angle) % (2*Math.PI); 
		}
		
	}
	
	public void addVectors(Vector secondVec){
		double resultX = this.getXComponent()+secondVec.getXComponent();
		double resultY = this.getYComponent()+secondVec.getYComponent();
		
		this.magnitude = (Math.hypot(resultY, resultX));
		
		if(resultX != 0.0){
			this.angle = Math.atan(resultY/resultX);
		}
		else{
			this.angle = 0;
		}
	}
	
	public void prodScalarVector(double scalar){
		this.magnitude = scalar * this.magnitude;
	}
	
	public void addVectorXY(double xCoord, double yCoord){
		double resultX = this.magnitude*Math.cos(this.angle) + 
				xCoord;
		double resultY = this.magnitude*Math.sin(this.angle) + 
				yCoord;
		this.magnitude = Math.hypot(resultY, resultX);

	}
	
	public void setForces(double magnitude, double angle){
		this.magnitude = magnitude;
		this.angle = angle;
	}
	
	public double getXComponent(){
		return this.magnitude*Math.cos(this.angle);
	}
	
	public double getYComponent(){
		return this.magnitude*Math.sin(this.angle);
	}

	public void setFromXY(double xPos, double yPos) {
		this.magnitude = Math.hypot((xPos), (yPos));
		
		if(xPos != 0.0){
			this.angle = Math.atan(yPos/xPos);
		}
		else{
			this.angle = 0;
		}

	}
}
