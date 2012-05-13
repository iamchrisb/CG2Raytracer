package cg2.raytracer;

import cg2.lightsources.PointLight;
import cg2.material.Materials;
import cg2.raytracer.shapes.Plane;
import cg2.raytracer.shapes.Sphere;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;
import cg2.raytracer.shapes.*;

public class Shader {

	private Hit hit;
	private Hit neo;

	private float viewability;

	public Color shade(Hit h, int reflectionIndex) {

		// System.out.println(j);
		hit = h;
		/** define the outer color terms **/
		Color diffuse_out = new Color(0, 0, 0);
		Color specular_out = new Color(0, 0, 0);
		Color ambient = new Color(0, 0, 0);

		PointLight light;

		/** iterate over all lightsources and add the terms if necessary **/

		for (int i = 0; i < Scene.getInstance().getLights().size(); i++) {
			light = Scene.getInstance().getLights().get(i);

			/** computes the viewability for the shadow **/
			Ray ray = new Ray(hit.getHitpoint(), light.getPosition().sub(
					hit.getHitpoint()));
			float distanceA = hit.getHitpoint().sub(light.getPosition())
					.length();
			Hit newH = Scene.getInstance().intersect(ray);

			if (newH != null) {
				if (distanceA < newH.getDistance()
						|| newH.getDistance() < 0.0001) {
					viewability = 1;
				} else {
					viewability = 0;
				}
			} else {
				viewability = 1;
			}

			// viewability = 1;

			/** gets the shadow for the hitpoint **/
			if (viewability == 1) {

				/** get and add the diffuse term **/
				Vector s = hit.getHitpoint().sub(light.getPosition())
						.normalize();
				Vector norm = hit.getN().normalize();
				if (hit.getType() instanceof Plane) {
					norm = norm.mult(-1);
				}
				float sn = norm.dot(s);

				if (sn > 0) {
					diffuse_out = diffuse_out
							.add(light.getColor().modulate(sn));
				}

				/** get and add the specular term **/

				Vector r = norm.mult(2 * (norm.dot(s))).sub(s);
				r = r.normalize();

				if (norm.dot(s) > 0) {

					if (r.dot(hit.getRay().getNormalizedDirection()) > 0) {

						float vr = hit.getRay().getNormalizedDirection().dot(r);
						float vra = (float) Math.pow(vr, hit.getType()
								.getMaterial().getPhongExponent());
						if (vra > Math.toRadians(2)) {
							specular_out = specular_out.add(light.getColor()
									.modulate(vra));
						}
					}

				}
			}

		}

		/** bring all light-terms together **/
		ambient = ambient.add(hit.getType().getMaterial().getkAmbient());

		diffuse_out = hit.getType().getMaterial().getkDiffuse()
				.modulate(diffuse_out);
		specular_out = hit.getType().getMaterial().getkSpekular()
				.modulate(specular_out);

		Color add = new Color(0, 0, 0);
		add = diffuse_out;
		add = add.add(specular_out);
		add = add.add(ambient);

		/** add the reflection term **/
		// rv = 2( n * v )n - v
		//
		Vector v = hit.getRay().getNormalizedDirection().mult(-1f);
		Vector n = hit.getN().normalize();

		Vector rv = n.mult((n.dot(v)) * (2)).sub(v);

		neo = Scene.getInstance().intersect(new Ray(hit.getHitpoint(), rv));

		Color kr = new Color(0.7f, 0.7f, 0.7f);
		Color reflectionCol = new Color(0, 0, 0);

		if (reflectionIndex > 0 && neo != null && neo.getDistance() > 0.00001f) {
			reflectionIndex--;
			reflectionCol = (this.shade(neo, reflectionIndex)).modulate(kr);
		}

		/** add the transmitting term **/

		Color transmissionCol = new Color(0, 0, 0);

//		if (hit.getType().getMaterial().getkRef() != Materials.solid) {
			
			Hit lastHit = null;
			
			/** needed floats **/
			float cos = hit.getRay().getNormalizedDirection().dot(hit.getN());
			float n1;
			float n2;

			/** needed vectors **/
			Vector nTransmit = hit.getN();
			Vector i = hit.getRay().getNormalizedDirection();

			/** check the normal-vector **/
			if (cos > 0) {
				n1 = Scene.getInstance().getkRef();
				n2 = hit.getType().getMaterial().getkRef();
			} else {
				nTransmit = nTransmit.mult(-1);
				n2 = Scene.getInstance().getkRef();
				n1 = hit.getType().getMaterial().getkRef();
			}

			/** some computed values **/
			float nDiv = n1 / n2;
			float nDivPow2 = (float) Math.pow(nDiv, 2);
			float cosPow2 = (float) Math.pow(cos, 2);

			float checkTransmit = (nDivPow2 * (1 - cosPow2));

			if (checkTransmit <= 1) {

				float nDivCos = nDiv * cosPow2;
				
				Vector t1 = i.mult(nDiv);
				//Vector t2 = nTransmit.mult((float) Math.sqrt(1 - nDivCos));
				Vector t2 = nTransmit.mult((float) (nDiv*cos - Math.sqrt(1 - nDivCos)));
				Vector tDirection = t1.add(t2).normalize();

				Hit hTransmit = Scene.getInstance().intersect(
						new Ray(hit.getHitpoint(), tDirection.mult(-1)));

				if (reflectionIndex > 0 && hTransmit != null  ) {
					transmissionCol = (this.shade(hTransmit, reflectionIndex - 1));
					lastHit = hTransmit;
				}
			}
			
			if( lastHit != null ) {
			/** specific factors **/
			float r0 = (float) Math.pow(((n1 - n2) / (n1 + n2)), 2);
			float R = (float) (r0 + (1 - r0) * (Math.pow((1 - cosPow2), 5)));
			float T = 1 - R;
			
			/** prepare reflection and transmission color **/
				reflectionCol = reflectionCol.modulate(R);
				transmissionCol = transmissionCol.modulate(T);
			}
//		}

		/** return the final color **/
		//add = add.add(reflectionCol);
		add = add.add(transmissionCol);
		return add;

	}
}
