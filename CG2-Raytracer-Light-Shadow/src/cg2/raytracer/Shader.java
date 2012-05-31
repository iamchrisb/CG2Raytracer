package cg2.raytracer;

import cg2.lightsources.AreaLight;
import cg2.lightsources.PointLight;
import cg2.material.Constants;
import cg2.material.Materials;
import cg2.raytracer.shapes.Plane;
import cg2.raytracer.shapes.PlaneFog;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Shader {

	private Hit neo;

	private float viewability;

	public Color shade(Hit h, int reflectionIndex) {

		/** define the outer color terms **/

		Color diffuse_point_out = new Color(0, 0, 0);
		Color specular_out = new Color(0, 0, 0);
		Color ambient = new Color(0, 0, 0);
		Color diffuse_area_out = new Color(0, 0, 0);

		PointLight plight;
		AreaLight alight;

		/** iterate over all lightsources and add the terms if necessary **/

		for (int i = 0; i < Scene.getInstance().getLights().size(); i++) {

			Color diffuse_area_in = new Color(0, 0, 0);

			/** AreaLight Part **/

			if (Scene.getInstance().getLights().get(i) instanceof AreaLight) {

				alight = (AreaLight) Scene.getInstance().getLights().get(i);

				for (int j = 0; j < alight.getList().size(); j++) {

					/** computes the viewability for the shadow **/
					Ray ray = new Ray(h.getHitpoint(), alight.getList().get(j)
							.getPosition().sub(h.getHitpoint()));
					float distanceA = h.getHitpoint()
							.sub(alight.getList().get(j).getPosition())
							.length();
					Hit newH = Scene.getInstance().intersect(ray);

					if (newH != null) {
						if (distanceA < newH.getDistance()
								|| newH.getDistance() < Constants.EPSILON) {
							viewability = 1;
						} else {
							viewability = 0;
						}
					} else {
						viewability = 1;
					}

					/** gets the shadow for the hitpoint **/
					if (viewability == 1) {

						/** get and add the diffuse term **/
						Vector s = h.getHitpoint()
								.sub(alight.getList().get(j).getPosition())
								.normalize();
						Vector norm = h.getN().normalize();
						float sn = norm.dot(s);

						if (sn > 0) {
							diffuse_area_in = diffuse_area_in.add(alight
									.getList().get(j).getColor().modulate(sn));
						}
					}
					float breaker = 1f / alight.getList().size();
					diffuse_area_out = diffuse_area_in.modulate(breaker)
							.modulate(getDistance(distanceA));
				}
			}

			/** PointLight Part **/

			if (Scene.getInstance().getLights().get(i) instanceof PointLight) {

				plight = (PointLight) Scene.getInstance().getLights().get(i);

				/** computes the viewability for the shadow **/
				Ray ray = new Ray(h.getHitpoint(), plight.getPosition().sub(
						h.getHitpoint()));
				float distanceA = h.getHitpoint().sub(plight.getPosition())
						.length();
				Hit newH = Scene.getInstance().intersect(ray);

				if (newH != null) {
					if (distanceA < newH.getDistance()
							|| newH.getDistance() < Constants.EPSILON) {
						viewability = 1;
					} else {
						viewability = 0;
					}
				} else {
					viewability = 1;
				}

//				 viewability = 1;

				/** gets the shadow for the hitpoint **/
				if (viewability == 1) {

					/** get and add the diffuse term **/
					Vector s = h.getHitpoint().sub(plight.getPosition())
							.normalize();
					Vector norm = h.getN().normalize();
					float sn = norm.dot(s);

					if (sn > 0) {
						diffuse_point_out = diffuse_point_out.add(plight
								.getColor().modulate(sn)).modulate(getDistance(distanceA));
					}

					/** get and add the specular term **/

					Vector r = norm.mult(2 * (norm.dot(s))).sub(s);
					r = r.normalize();

					if (norm.dot(s) > 0) {

						if (r.dot(h.getRay().getNormalizedDirection()) > 0) {

							float vr = h.getRay().getNormalizedDirection()
									.dot(r);
							float vra = (float) Math.pow(vr, h.getType()
									.getMaterial().getPhongExponent());
							if (vra > Math.toRadians(2)) {
								specular_out = specular_out.add(plight
										.getColor().modulate(vra));
							}
						}

					}
				}
			}
		}

		/** bring all light-terms together **/
		ambient = ambient.add(h.getType().getMaterial().getkAmbient());

		diffuse_point_out = diffuse_point_out.modulate(h.getType()
				.getMaterial().getkDiffuse());
		specular_out = h.getType().getMaterial().getkSpekular()
				.modulate(specular_out);

		Color wholeLight = new Color(0, 0, 0);
		wholeLight = wholeLight.add(diffuse_point_out);
		wholeLight = wholeLight.add(diffuse_area_out);
		wholeLight = wholeLight.add(specular_out);
		wholeLight = wholeLight.add(ambient);

		/** the reflection and transmit term **/
		Color kr = new Color(0.7f, 0.7f, 0.7f);
		Color reflectionCol = new Color(0, 0, 0);
		Color transmissionCol = new Color(0, 0, 0);

		/** needed floats **/
		float cos = h.getRay().getNormalizedDirection().dot(h.getN().normalize().mult(-1));
		float n1;
		float n2;

		/** needed vectors **/
		Vector nTransmit = h.getN();
		Vector i = h.getRay().getNormalizedDirection();

		/** check the normal-vector **/
		if (cos > 0) {
			n1 = Scene.getInstance().getkRef();
			n2 = h.getType().getMaterial().getkTransmit();
		} else {
			nTransmit = nTransmit.mult(-1);
			n2 = Scene.getInstance().getkRef();
			n1 = h.getType().getMaterial().getkTransmit();
		}

		/** some computed values **/
		float nDiv = n1 / n2;
		float nDivPow2 = (float) Math.pow(nDiv, 2);
		float cosPow2 = (float) Math.pow(cos, 2);
		/** specific factors **/
		float r0 = (float) Math.pow(((n1 - n2) / (n1 + n2)), 2);
		float R = (float) (r0 + (1 - r0) * (Math.pow(1 - cosPow2, 5)));
		float T = 1 - R;

		float checkTransmit = (nDivPow2 * (1 - cosPow2));

		if (checkTransmit <= 1) {
			R = 1;
			T = 0;
		}
		
		T = 0;
		R = 0;

		/** compute the transmitting term **/
		if (T > Constants.EPSILON) {
			float nDivCos = nDiv * cosPow2;

			Vector t1 = i.mult(nDiv);
			Vector t2 = nTransmit.mult((float) (nDiv * cos - Math
					.sqrt(1 - nDivCos)));
			Vector tDirection = t1.add(t2).normalize();

			Hit hTransmit = Scene.getInstance().intersect(
					new Ray(h.getHitpoint(), tDirection));

			if (reflectionIndex > 0 && hTransmit != null) {
				transmissionCol = (this.shade(hTransmit, reflectionIndex - 1))
						.modulate(kr);
			}
		}

		/** compute the reflection term **/
		if (R > Constants.EPSILON) {
			Vector v = h.getRay().getNormalizedDirection().mult(-1f);
			Vector n = h.getN().normalize();

			Vector rv = n.mult((n.dot(v)) * (2)).sub(v);

			neo = Scene.getInstance().intersect(new Ray(h.getHitpoint(), rv));

			if (reflectionIndex > 0 && neo != null
					&& neo.getDistance() > Constants.EPSILON) {
				reflectionIndex--;
				reflectionCol = (this.shade(neo, reflectionIndex)).modulate(kr);
			}
		}
		
//		T = 1;
//		R = 0;

		/** prepare reflection and transmission color **/
		reflectionCol = reflectionCol.modulate(R);
		transmissionCol = transmissionCol.modulate(T);

		/** return the final color **/
		wholeLight = wholeLight.add(reflectionCol);
		wholeLight = wholeLight.add(transmissionCol);

		return wholeLight;
	}

	public float getDistance(float distance) {
		float c1 = 0f;
		float c2 = 1.0f;
		float c3 = .0f;
		float lower = (1 / (c1 + c2 * distance + c3
				* (float) Math.pow(distance, 2)));
		return Math.min(lower, 1);
	}
}











































/**
/** add the fog term 

Color fogColor = new Color(0, 0, 0);
// Vector ur = new Vector(0, 2, 1);
Vector ur = new Vector(0.0f, -0.40f, 2);

Ray rf = new Ray(h.getHitpoint(), h.getHitpoint().sub(ur).normalize());
PlaneFog pf = new PlaneFog(new Plane(new Vector(0f, -0.9f, 0f),
		new Vector(0f, -1f, 0f), Materials.yellow), 3.0f, new Color(0,
		0, 0.7f));
Hit neoH = pf.intersect(rf);

if (neoH != null) {
	// if(hit.getType() instanceof Sphere){
	// System.out.println("not nientaaa");
	if (h.getType() instanceof Plane) {
//		System.out.println(neoH.getType());
		fogColor = fogColor.add(pf.getFogColor());
	}
	// }
}
wholeLight = wholeLight.add(fogColor);
**/