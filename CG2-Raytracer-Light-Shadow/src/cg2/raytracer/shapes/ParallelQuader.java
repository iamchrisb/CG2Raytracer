package cg2.raytracer.shapes;

import java.util.ArrayList;

import cg2.interfaces.Materializable;
import cg2.interfaces.Raytracable;
import cg2.interfaces.Shape;
import cg2.material.Material;
import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class ParallelQuader implements Shape, Raytracable, Materializable {

	private Vector p;
	private Vector q;

	private Color c = new Color(1, 1, 0);

	private Plane right;
	private Plane left;
	private Plane front;
	private Plane back;
	private Plane ground;
	private Plane head;
	private Material m;

	private ArrayList<Plane> planes;

	public ParallelQuader(Vector p, Vector q, final Material m) {
		this.p = p;
		this.q = q;
		this.m = m;

		generatePlanes();
	}

	/**
	 * we generate the planes, the box consists of and add them to the
	 * plane-arraylist
	 */
	private void generatePlanes() {
		planes = new ArrayList<Plane>();

		front = new Plane(p, new Vector(0, 0, -1), this.m);
		back = new Plane(q, new Vector(0, 0, 1), this.m);
		left = new Plane(p, new Vector(-1, 0, 0), this.m);
		right = new Plane(q, new Vector(1, 0, 0), this.m);
		ground = new Plane(p, new Vector(0, -1, 0), this.m);
		head = new Plane(q, new Vector(0, 1, 0), this.m);

		planes.add(front);
		planes.add(back);
		planes.add(left);
		planes.add(right);
		planes.add(ground);
		planes.add(head);
	}

	@Override
	public Hit intersect(Ray r) {

		ArrayList<Hit> curHits = new ArrayList<Hit>();

		for (int i = 0; i < 6; i++) {
			float f = planes.get(i).getN()
					.dot(r.getOrigin().sub(planes.get(i).getPos()));
			if (f > 0) {
				if (planes.get(i).intersect(r) != null) {
					curHits.add(planes.get(i).intersect(r));
				}
			}
		}

		if (curHits.size() != 0) {
			Hit biggest = curHits.get(0);

			if (curHits.size() == 0)
				return null;

			if (curHits.size() == 1) {
				biggest = curHits.get(0);
			} else {
				for (int i = 0; i < curHits.size(); i++) {
					if (curHits.get(i) != null) {
						biggest = getMaxHit(biggest, curHits.get(i));
					}
				}
			}

			if (isHitBtwPAndQ(biggest, r)) {
				return biggest;
			}
		}
		return null;
	}

	/*
	 * checks wether the hitpoint is in the box or not
	 */
	private boolean isHitBtwPAndQ(Hit biggest, Ray r) {
		Vector in = r.getPoint(biggest.getDistance());
		float a = 0.000001f;

		if (in.x >= p.x - a && in.y >= p.y - a && in.z >= p.z - a) {
			if (in.x <= q.x + a && in.y <= q.y + a && in.z <= q.z + a) {
				return true;
			}
		}
		return false;
	}

	private Hit getMaxHit(Hit biggest, Hit h) {
		if (biggest.getDistance() < h.getDistance()) {
			return h;
		} else {
			return biggest;
		}
	}

	@Override
	public Color getColor() {
		return c;
	}

	@Override
	public String getType() {
		return "ParallelQuader";
	}

	@Override
	public Material getMaterial() {
		return m;
	}

	public ArrayList<Plane> getPlanes() {
		return planes;
	}

	@Override
	public void setMaterial(Material m) {
		this.m = m;
	}

}
