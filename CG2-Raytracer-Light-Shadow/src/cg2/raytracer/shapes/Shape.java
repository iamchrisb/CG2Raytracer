package cg2.raytracer.shapes;

import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.vecmath.Color;

public interface Shape {
	
	/**
	 * 
	 * @param r the ray for the intersection
	 * @return a hitpoint with the shape or null
	 */
	Hit intersect(Ray r);
	
	/**
	 * 
	 * @return the color of the shape
	 */
	Color getColor();
	
	/**
	 * 
	 * @return the kind of the shape
	 */
	String getType();
	
	/**
	 * 
	 * @return the material - not implemented yet
	 */
	Material getMaterial();

}
