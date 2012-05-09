package cg2.raytracer;

import cg2.lightsources.Light;
import cg2.raytracer.shapes.Plane;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

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

		Light light;

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
			
			viewability = 1;

			/** gets the shadow for the hitpoint **/
			if (viewability == 1) {

				/** get and add the diffuse term **/
				Vector s = hit.getHitpoint().sub(light.getPosition())
						.normalize();
				Vector norm = hit.getN().normalize();
				if(hit.getType() instanceof Plane){
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
						specular_out = specular_out.add(light.getColor()
								.modulate(vra));
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

		/** get the reflection term **/
		// rv = 2( n * v )n - v

		Vector v = hit.getRay().getNormalizedDirection().mult(-1);
		Vector n = hit.getN().normalize();
		
		Vector rv = n.mult((n.dot(v)) * (2)).sub(v);
		
//		Vector rvDirection = hit.getHitpoint().sub(rv);
		neo = Scene.getInstance().intersect(
				new Ray(hit.getHitpoint(), rv));

		Color kr = new Color(0.7f, 0.7f, 0.7f);
		if (reflectionIndex > 0 && neo != null ) {
			reflectionIndex--;
			add = add.add((this.shade(neo, reflectionIndex))).modulate(kr);
//			add = (this.shade(neo, reflectionIndex).modulate(kr));
		}

		return add;

	}

}
