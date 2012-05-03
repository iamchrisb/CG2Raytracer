package cg2.depricated;

import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.raytracer.shapes.Material;
import cg2.raytracer.shapes.Shape;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Plane implements Shape{
	
	private Vector a;
	private Vector b;
	private Vector c;
	private Color color;
	
	public Plane(final Vector a, final Vector b, final Vector c, final Color co) {
		this.a = a;
		this.b = b;
		this.c = c;
		color = co;
	}

	@Override
	public Hit intersect(Ray r) {
		float t = this.getDistance() - this.getNormN().dot(r.getOrigin()) / this.getNormN().dot(r.getNormalizedDirection());
		return new Hit(this.color, t, null, null, null, null);
	}

	@Override
	public Color getColor() {
		return color;
	}
	
	public Vector getNormN(){
		Vector n = (b.sub(a).cross(c.sub(a)));
		return n.normalize();
	}
	
	public float getDistance(){
		float d = a.dot(this.getNormN());
		return d;
	}

	@Override
	public String toString() {
		return "Plane []";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Material getMaterial() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
