package cg2.raytracer.shapes;

import cg2.interfaces.Materializable;
import cg2.interfaces.Raytracable;
import cg2.interfaces.Shape;
import cg2.material.Material;
import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Plane implements Shape, Raytracable, Materializable {

	private Vector n;
	private Vector pos;
	private float d;
	private Color c;
	private Material m;

	/**
	 * the plane is infinite so we just need a position and an normal
	 * 
	 * @param pos
	 *            position vector
	 * @param n
	 *            normale
	 * @param greyMaterial
	 */
	public Plane(final Vector pos, final Vector n, final Material greyMaterial) {
		this.n = n;
		this.pos = pos;
		this.m = greyMaterial;
	}

	@Override
	public Hit intersect(Ray r) {
		d = n.dot(pos);
		float dot = n.dot(r.getNormalizedDirection());

		// if 0 the plane is in a angle of 90 degree to
		// the cam
		if (dot == 0) {
			return null;
		}

		float t = (d - n.dot(r.getOrigin())) / dot;

		// if the plane is behind the cam return null, else..
		if (t > 0.00001) {
			return new Hit(c, t, this, r.getPoint(t), this.n.mult(1), r);
		} else {
			return null;
		}
	}

	@Override
	public Color getColor() {
		return c;
	}

	@Override
	public String getType() {
		return "P";
	}

	@Override
	public String toString() {
		return "Plane [n=" + n + "]";
	}

	@Override
	public Material getMaterial() {
		return m;
	}

	public Vector getN() {
		return n.normalize();
	}

	public Vector getPos() {
		return pos;
	}

	@Override
	public void setMaterial(Material m) {
		this.m = m;
	}

}
