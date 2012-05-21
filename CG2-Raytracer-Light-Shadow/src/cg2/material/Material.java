package cg2.material;

import cg2.vecmath.Color;

public class Material {
	
	private Color kAmbient;
	private Color kDiffuse;
	private Color kSpekular;
	
	private float phongExponent;
	private float kTransmit;


	public Material(Color kDif ,Color kSpec,  Color kAmbient, float phongExponent , float kTransmit) {
		this.setkDiffuse(kDif);
		this.setkSpekular(kSpec);
		this.setPhongExponent(phongExponent);
		this.setkAmbient(kAmbient);
		this.kTransmit = kTransmit;
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

	public float getkTransmit() {
		return kTransmit;
	}

	public void setkTransmit(float kRef) {
		this.kTransmit = kRef;
	}
	
	
	
}
