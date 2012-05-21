package cg2.raytracer;

import cg2.interfaces.Shape;
import cg2.material.Materials;
import cg2.raytracer.shapes.Plane;
import cg2.raytracer.shapes.PlaneFog;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Hit {

	private Color c;
	private float distance;
	private Shape type;
	private Vector n;
	private Vector koords;
	private Ray ray;
	

	/**
	 * 
	 * @param c
	 *            the color of a shape
	 * @param distance
	 *            the specific distance of the shape
	 * @param type
	 *            the rype of the shape
	 * @param r2
	 */
	public Hit(final Color c, final float distance, final Shape type,
			Vector hitpoint, Vector normale, Ray r) {
		this.c = c;
		this.distance = distance;
		this.type = type;
		this.n = normale;
		this.koords = hitpoint;
		ray = r;
	}

	public Color shade() {
		
		Shader s = new Shader();
		int rekIndex = 2;
		
//		/** add the fog term **/
//		
//		Color fogColor = new Color(0,0,0);
////		Vector ur = new Vector(0, 2, 1);
//		Vector ur = new Vector(0.0f, -0.40f, 2);
//
//		Ray rf = new Ray(this.getHitpoint(), this.getHitpoint().sub(
//				ur).normalize());
//		PlaneFog pf = new PlaneFog(new Plane(new Vector(0f, -0.9f, 0f), new Vector(0f, -1f, 0f), Materials.yellow ), 3.0f,
//				new Color(0, 0, 0.7f));
//		Hit neoH = pf.intersect(rf);
//
//		if (neoH != null) {
////			System.out.println("not nientaaa");
//			if (neoH.getDistance() < this.getHitpoint().sub(ur).length()) {
//				fogColor = fogColor.add(pf.getFogColor());
//			}
//		}
//		wholeLight = wholeLight.add(fogColor);
		
		return s.shade(this , rekIndex);//.add(fogColor);

	}

	/**
	 * 
	 * @param h
	 *            the hit to check if greater or not
	 * @return the greatest hitpoint
	 */
	public Hit getMax(Hit h) {
		if (this.distance > h.distance)
			return this;
		return h;
	}

	/**
	 * 
	 * @param h
	 *            the hit to check if smaller or not
	 * @return the smaller hitpoint
	 */
	public Hit getMin(Hit h) {
		if (this.distance < h.distance)
			return this;
		return h;
	}
	
	public Ray getRay() {
		return ray;
	}

	public void setRay(Ray ray) {
		this.ray = ray;
	}
	
	@Override
	public String toString() {
		return "Hit [c=" + c + ", distance=" + distance + ", type=" + type
				+ ", n=" + n + ", koords=" + koords + ", ray=" + ray + "]";
	}

	public float getDistance() {
		return distance;
	}

	public Shape getType() {
		return this.type;
	}

	public Vector getN() {
		return n;
	}

	public Vector getHitpoint() {
		return koords;
	}

	public Color getColor() {
		return this.c;
	}

}
