package cg2.raytracer;

import cg2.system.Painter;
import cg2.vecmath.Color;

public class Raytracer implements Painter{
	
	private Camera camera;
	private Scene scene;
	private Ray ray;

	public Raytracer(final Camera c, final Scene s){
		this.camera = c;
		this.scene = s;
		
	}

	@Override
	public Color pixelColorAt(int x, int y, int resolutionX, int resolutionY) {
		
		ray = camera.generateRay(x,y,resolutionX,resolutionY);
		
		Hit h = scene.intersect(ray);
		
		if(h != null){
			return h.shade(scene.getLights());
		}

		return new Color(0,0,0);
	}
	
	public Camera getCamera() {
		return camera;
	}

	public Scene getScene() {
		return scene;
	}
	
}
