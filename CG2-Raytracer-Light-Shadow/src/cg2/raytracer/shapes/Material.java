package cg2.raytracer.shapes;

import cg2.vecmath.Color;

public class Material {
	
	private Color kAmbient;
	private Color kDiffuse;
	private Color kSpekular;
	
	private float phongExponent;


	public Material(Color kdif ,Color kspec,  Color ambient, float phongExponent) {
		this.setkDiffuse(kdif);
		this.setkSpekular(kspec);
		this.setPhongExponent(phongExponent);
		this.setkAmbient(ambient);
	}

	public Color getkAmbient() {
		return kAmbient;
	}

	public void setkAmbient(Color kAmbient) {
		this.kAmbient = kAmbient;
	}

	public Color getkDiffuse() {
		return kDiffuse;
	}

	public void setkDiffuse(Color kDiffuse) {
		this.kDiffuse = kDiffuse;
	}

	public Color getkSpekular() {
		return kSpekular;
	}

	public void setkSpekular(Color kSpekular) {
		this.kSpekular = kSpekular;
	}

	public float getPhongExponent() {
		return phongExponent;
	}

	public void setPhongExponent(float phongExponent) {
		this.phongExponent = phongExponent;
	}
	
}
