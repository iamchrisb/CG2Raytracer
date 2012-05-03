package cg2.raytracer;

import java.util.ArrayList;

import cg2.lightsources.Light;
import cg2.raytracer.shapes.Shape;
import cg2.raytracer.shapes.Sphere;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Hit {

	private Color c;
	private float distance;
	private Shape type;
	private Vector n;
	private Vector koords;
	private Ray ray;
	private float viewability;

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

	public Color shade(ArrayList<Light> arrayList) {

		Color diffuse_out = new Color(0, 0, 0);
		Color specular_out = new Color(0, 0, 0);
		Color ambient = new Color(0, 0, 0);

		for (int i = 0; i < arrayList.size(); i++) {
			Light light = arrayList.get(i);
			getViewability(light);

			if (viewability == 1) {
				Color diffuse = new Color(0, 0, 0);
				Color specular = new Color(0, 0, 0);

				Vector s = this.getHitpoint().sub(light.getPosition());
				s = s.normalize();
				Vector n = this.getN().normalize();

				float sn = n.dot(s);

				if (sn > 0) {
					diffuse = getDiffusePart(light, sn);
					diffuse_out = diffuse_out.add(diffuse);
				}

				Vector r = n.mult(2 * (n.dot(s))).sub(s);
				r = r.normalize();

				if (n.dot(s) > 0) {

					if (r.dot(ray.getNormalizedDirection()) > 0) {
						specular = light.getColor();

						float vr = ray.getNormalizedDirection().dot(r);
						float vra = (float) Math.pow(vr, this.getType()
								.getMaterial().getPhongExponent());
						specular = specular.modulate(vra).modulate(viewability);
						specular_out = specular_out.add(specular);
					}

				}
			}

		}

		ambient = ambient.add(this.getType().getMaterial().getkAmbient());

		diffuse_out = getType().getMaterial().getkDiffuse()
				.modulate(diffuse_out);
		specular_out = getType().getMaterial().getkSpekular()
				.modulate(specular_out);

		Color add = new Color(0, 0, 0);
		add = diffuse_out;
		add = add.add(specular_out);
		add = add.add(ambient);

		return add;

	}

	private void getViewability(Light light) {
		Ray r = new Ray(koords, light.getPosition().sub(koords));
		float distanceA = koords.sub(light.getPosition()).length();
		Hit newH = Scene.getInstance().intersect(r);

		if (newH != null) {
			if (distanceA < newH.getDistance() || newH.getDistance() < 0.000001) {
				viewability = 1;
			} else {
				viewability = 0;
			}
		} else {
			viewability = 1;
		}

	}

	private Color getDiffusePart(Light light, float sn) {
		return light.getColor().modulate(sn);
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

	@Override
	public String toString() {
		return "Hit " + type.getType() + ": distance=" + distance + "]";
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
