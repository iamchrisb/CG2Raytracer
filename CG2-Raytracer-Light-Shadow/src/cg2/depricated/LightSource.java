package cg2.depricated;

import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public interface LightSource {
	
	Vector getPosition();
	void setPosition(final Vector v);
	
	Color getColor();
	void setColor(Color c);

}
