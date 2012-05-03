package cg2.depricated;

import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class SpecularLight implements LightSource {

	private Vector position;
	private Color c;
	
	@Override
	public Vector getPosition() {
		return position;
	}

	@Override
	public void setPosition(Vector v) {
		position = v;
	}

	@Override
	public Color getColor() {
		return c;
	}

	@Override
	public void setColor(Color c) {
		this.c = c;
	}

}
