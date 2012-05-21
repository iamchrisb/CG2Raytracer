package cg2.raytracer;

import java.util.ArrayList;

import cg2.interfaces.Shape;
import cg2.lightsources.LightSource;
import cg2.material.Materials;
import cg2.vecmath.Color;

public class Scene {

	private ArrayList<Shape> shapes;
	private ArrayList<LightSource> lights;
	private Color c;
	private float kRef;
	
	public float getkRef() {
		return kRef;
	}

	public void setkRef(float kRef) {
		this.kRef = kRef;
	}

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
		lights = new ArrayList<LightSource>();
		setkRef(Materials.air);
	}

	public Shape[] getShapes() {
		return shapes.toArray(new Shape[shapes.size()]);
	}

	public void addShape(final Shape s) {
		shapes.add(s);
	}
	
	public void addLightSource(final LightSource dl){
		lights.add(dl);
	}
	
	public ArrayList<LightSource> getLights(){
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
