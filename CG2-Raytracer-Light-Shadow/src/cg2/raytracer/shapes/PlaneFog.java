package cg2.raytracer.shapes;

import cg2.interfaces.Shape;
import cg2.material.Material;
import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class PlaneFog implements Shape{
	
	private Plane fogPlane;
	private float fogDept;
	private Color fogColor;
	private float fogConcentration;

	public PlaneFog(Plane fogPlane, float fogDept, Color color) {
		this.fogPlane = fogPlane;
		this.fogDept = fogDept;
		this.fogColor = color;
	}

	@Override
	public Hit intersect(Ray r) {
		Hit fogH = fogPlane.intersect(r);
		return fogH;
	}

	@Override
	public Color getColor() {
		return fogPlane.getColor();
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Material getMaterial() {
		// TODO Auto-generated method stub
		return null;
	}

	public Plane getFogPlane() {
		return fogPlane;
	}

	public void setFogPlane(Plane fogPlane) {
		this.fogPlane = fogPlane;
	}

	public float getFogDept() {
		return fogDept;
	}

	public void setFogDept(float fogDept) {
		this.fogDept = fogDept;
	}

	public Color getFogColor() {
		return fogColor;
	}

	public void setFogColor(Color fogColor) {
		this.fogColor = fogColor;
	}

	public float getFogConcentration() {
		return fogConcentration;
	}

	public void setFogConcentration(float fogConcentration) {
		this.fogConcentration = fogConcentration;
	}
	
	
	
	

}
