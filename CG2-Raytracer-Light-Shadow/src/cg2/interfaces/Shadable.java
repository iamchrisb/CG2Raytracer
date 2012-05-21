package cg2.interfaces;

import java.util.ArrayList;

import cg2.lightsources.LightSource;
import cg2.raytracer.Hit;
import cg2.vecmath.Color;

public interface Shadable {
	Color shade(final Hit h, final ArrayList<LightSource> lights);
}
