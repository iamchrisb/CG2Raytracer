package cg2.raytracer;

import java.util.ArrayList;

import cg2.lightsources.Light;
import cg2.raytracer.shapes.Shape;
import cg2.vecmath.Color;

public class Scene implements Raytracable{

	private ArrayList<Shape> shapes;
	private ArrayList<Light> lights;
	private Color c;
	
	public static Scene instance = null;
	
	public static Scene getInstance(){
		if(instance == null){ 
			instance = new Scene();
		}
		return instance;
	}

	private Scene() {
		c = new Color(1, 1, 1);
		shapes = new ArrayList<Shape>();
		lights = new ArrayList<Light>();
	}

	public Shape[] getShapes() {
		return shapes.toArray(new Shape[shapes.size()]);
	}

	public void addShape(final Shape s) {
		shapes.add(s);
	}
	
	public void addLightSource(final Light dl){
		lights.add(dl);
	}
	
	public ArrayList<Light> getLights(){
		return lights;
	}

	public Color getColor() {
		return c;
	}

	public Hit intersect(Ray r) {

		ArrayList<Hit> hits = new ArrayList<Hit>();
		Hit currentH;
		
		for (int i = 0; i < shapes.size(); i++) {
			currentH = shapes.get(i).intersect(r);
			if (currentH != null) {
				 hits.add(currentH);
			}
		}
		return getMinHit(hits);
	}

	private Hit getMinHitDis(final Hit h, final Hit h2) {
		if (h.getDistance() > h2.getDistance()){
			return h2;
		}else if(h.getDistance() < h2.getDistance()){
			return h;
		}
		else return h;
	}
	
	private Hit getMinHit(final ArrayList<Hit> hits){
		Hit smallest = null;
		if(hits.size() == 1) {
			smallest = hits.get(0);
		} else if (hits.size() > 1) {
			smallest = hits.get(0);
			for (int i = 1; i < hits.size(); i++) {
				smallest = this.getMinHitDis(smallest, hits.get(i));
			}
		} else {
			smallest = null;
		}
		return smallest;
	}

}
