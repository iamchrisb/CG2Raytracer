package cg2.raytracer.shapes;

import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.raytracer.Raytracable;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Sphere implements Shape, Raytracable, Materializable {

	private float radius;
	final private Vector c;
	private Vector x0;
	private Color color;
	private Material m;
	private Vector n;
	private Vector hitpoint;

	public Sphere(final Vector c, final float r, final Material blueMaterial) {
		this.m = blueMaterial;
		radius = r;
		this.c = c;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public Hit intersect(final Ray r) {
		// (x - center)� = r�
		// x0 = x - c

		float test = checkSqrt(r);
		return getHit(test, r.getNormalizedDirection(), r);
	}

	/**
	 * 
	 * @param test
	 * @param v
	 * @return the hitpoint or null
	 */
	private Hit getHit(final float test, Vector v, Ray r) {
		if (test < 0) {
			return null;
		} else if (test == 0) {
			float d = -(x0.dot(v));
			hitpoint = r.getPoint(d);
			n = c.sub(hitpoint);
			return new Hit(this.getColor(), d, this, hitpoint, n, r);
		} else if (test > 0) {
			hitpoint = r.getPoint(getMinHit(test, v));
			n = c.sub(hitpoint);
			if (getMinHit(test, v) < 0) {
//				System.out.println("return NULL NULL NULL");
				return null;
			} else {
				return new Hit(this.getColor(), getMinHit(test, v), this,
						hitpoint, n, r);
			}
		} else {
			return null;
		}
	}

	private float getMinHit(float test, Vector v) {
		float epsilon = 0.001f;
		float d1 = -(x0.dot(v)) - test;
		float d2 = -(x0.dot(v)) + test;
		float min = Math.min(d1, d2);
		//float max = Math.max(d1, d2);
		if (d1 > epsilon && d2 > epsilon) {
//			System.out.println("d1: " + d1 + " , d2: " + d2 + " = = " + min);
			return min;
		} else if(d1 < epsilon && d2 < epsilon ){
//			System.out.println(d1 + " , " + d2 + "BEIDE KLEINER EPSILON");
			return -23423423;
		} else if(d1 < epsilon && d2 > epsilon){
//			System.out.println(d1 + " , " + d2 + "D1 KLEINER EPSILON , D2 GR�SSER EPSILON");
			return d2;
		}else{
//			System.out.println(d1 + " , " + d2 + "D1 GR�SSER EPSILON , D2 KLEINER EPSILON");
			return d1;
		}

	}

	/**
	 * 
	 * @param r
	 *            ray what vectors we need to check
	 * @return the value to decide how much hitpoints the sphere got
	 */
	private float checkSqrt(final Ray r) {
		x0 = r.getOrigin().sub(c);
		float first = x0.dot(r.getNormalizedDirection());
		float sec = (x0.dot(x0)) - (radius * 2);
		float t = (float) Math.sqrt((first * first) - sec);
		return t;
	}

	public float getRadius() {
		return radius;
	}

	public Vector getCenter() {
		return c;
	}

	@Override
	public String toString() {
		return "Sphere [radius=" + radius + "]";
	}

	@Override
	public String getType() {
		return "K";
	}

	@Override
	public Material getMaterial() {
		return m;
	}

	@Override
	public void setMaterial(Material m) {
		this.m = m;
	}

	public Vector getN() {
		return n;
	}

	public Vector getHitpoint() {
		return hitpoint;
	}

}
