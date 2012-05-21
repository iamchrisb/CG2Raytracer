package cg2.depricated;

import cg2.raytracer.Hit;
import cg2.raytracer.Ray;

public interface Raytracable {
	
	/**
	 * 
	 * @param r the ray for the intersection
	 * @return a hitpoint with the shape or null
	 */
	Hit intersect(Ray r);

}
