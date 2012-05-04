package cg2.raytracer;

import cg2.lightsources.Light;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Shader {

	private Hit hit;
	private Hit neo;

	private float viewability;

	public Color shade(Hit h, int j) {

		j--;
		System.out.println(j);
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
						|| newH.getDistance() < 0.000001) {
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
				Vector s = hit.getHitpoint().sub(light.getPosition())
						.normalize();
				Vector norm = hit.getN().normalize();

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

		Vector rv = hit
				.getN()
				.sub(hit.getRay().getNormalizedDirection())
				.mult((2 * (hit.getN().dot(hit.getRay()
						.getNormalizedDirection()))));

		Shader rek = new Shader();
		neo = Scene.getInstance().intersect(
				new Ray(hit.getHitpoint(), hit.getHitpoint().sub(rv)));

		Color kr = new Color(0.15f, 0.15f, 0.15f);
		if (j > 0 && neo != null) {
			add = add.add(rek.shade(neo, j).modulate(kr));
		}
		return add;

	}

}
