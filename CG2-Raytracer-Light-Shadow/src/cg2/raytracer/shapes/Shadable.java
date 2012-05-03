package cg2.raytracer.shapes;

import java.util.ArrayList;

import cg2.depricated.LightSource;
import cg2.raytracer.Hit;
import cg2.vecmath.Color;

public interface Shadable {
	Color shade(final Hit h, final ArrayList<LightSource> lights);
}
