package cg2.raytracer;

import cg2.vecmath.Vector;

public class Ray {
	
	private Vector origin;
	private Vector direction;
	
	public Ray(final Vector origin , final Vector v){
		this.direction = v.normalize();
		this.origin = origin;
	}
	
	public Vector getOrigin(){
		return origin;
	}
	
	public Vector getNormalizedDirection(){		
		return direction;
	}
	
	public Vector getPoint(float f){
		Vector point = origin.add(getNormalizedDirection().mult(f));
		return point;
	}
	
	public void setOrigin(final Vector v){
		this.origin = v;
	}

	@Override
	public String toString() {
		return "Ray [origin=" + origin + ", direction=" + direction + "]";
	}
	
	public Vector getDirection(){
		return this.direction;
	}

}
