package cg2.lightsources;

import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Light {
	
	private Vector position;
	private Color c;

	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector v) {
		this.position = v;
	}

	public Color getColor() {
		return c;
	}

	public void setColor(Color c) {
		this.c =c;
	}

}
