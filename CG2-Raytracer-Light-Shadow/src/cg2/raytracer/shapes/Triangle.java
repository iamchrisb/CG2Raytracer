package cg2.raytracer.shapes;

import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.raytracer.Raytracable;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Triangle implements Shape, Raytracable{
	
//	private Vector a;
	private Vector b;
	private Vector c;
	
	
	public Triangle(final Vector p0 , final Vector p1 , final Vector p2, final Color color) {
//		Vector o = new Vector(0, 0, 0);
//		a = o.sub(p0);
		b = p1.sub(p0);
		this.c = p2.sub(p0);
	}

	@Override
	public Hit intersect(Ray r) {
		
		float value = 0.00001f;
		
		Vector n = b.cross(c);
		if(n.dot(r.getNormalizedDirection())<value && n.dot(r.getNormalizedDirection())>-value ){
			return null;
		}
		
		return null;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Triangle";
	}

	@Override
	public Material getMaterial() {
		// TODO Auto-generated method stub
		return null;
	}

}
