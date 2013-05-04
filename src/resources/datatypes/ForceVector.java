package resources.datatypes;

public class ForceVector {
	// Class Variables
	public double magnitude, angle;
	
	public ForceVector(double magnitude, double angle){
		this.magnitude = magnitude;
		this.angle = angle;
	}
	
	public void addVectorMagAng(double magnitude, double angle){
		//System.out.println("Attempting magang with: "+ magnitude + ", " + angle);
		double resultX = this.magnitude*Math.cos(this.angle) + 
				magnitude*Math.cos(angle);
		double resultY = this.magnitude*Math.sin(this.angle) + 
				magnitude*Math.sin(angle);
		//System.out.println("result xy: "+ resultX + ", " + resultY);
		this.magnitude = (0.25f)*(Math.hypot(resultY, resultX));
		if(resultX != 0.0){
			this.angle = Math.atan(resultY/resultX);
		}
		else{
			this.angle = 0;
		}
		//System.out.println("result mag&ang: "+ this.magnitude + ", " + this.angle);
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
}
