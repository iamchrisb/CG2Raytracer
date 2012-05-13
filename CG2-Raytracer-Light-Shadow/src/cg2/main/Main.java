package cg2.main;

import cg2.lightsources.PointLight;
import cg2.material.Materials;
import cg2.raytracer.Camera;
import cg2.raytracer.Raytracer;
import cg2.raytracer.Scene;
import cg2.raytracer.shapes.ParallelQuader;
import cg2.raytracer.shapes.Plane;
import cg2.raytracer.shapes.Sphere;
import cg2.system.ImageGenerator;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// get the user's home directory - should work on all operating systems
		String path = System.getProperty("user.home");
		
		int resolutionX = 1366;
//		resolutionX = 1920;
//		resolutionX = 480;
		int resolutionY = 768;
//		resolutionY = 1080;
//		resolutionY = 380;
		
		int angle = 120;
		
		Camera c = new Camera();
		c.setAngle(angle);
		
		Scene s = Scene.getInstance();
		
//		Plane backPlane = new Plane(new Vector(0f, 0f, -3.5f), new Vector(0f, 0f, 1f), Materials.white_grey);
//		Plane groundPlane = new Plane(new Vector(0f, -.3f, 0f), new Vector(0f, 1f, 0f), Materials.darkgrey );
		
		Plane groundPlane = new Plane(new Vector(0f, -1f, 0f), new Vector(0f, 1f, 0f), Materials.white_grey );
//		Plane rightPlane = new Plane(new Vector(1f, 0f, 0f), new Vector(-1f, 0f, 0.25f), Materials.yellow);
		
		
//		s.addShape(backPlane);
		s.addShape(groundPlane);
//		s.addShape(rightPlane);
		
		Sphere k2 = new Sphere(new Vector(-0.3f, -0.88f, -2.0f) , 0.006f , Materials.darkgrey);
		Sphere k3 = new Sphere(new Vector(.1f , -.89f, -2.5f) , 0.005f , Materials.green );
		Sphere k3b = new Sphere(new Vector(-0.1f , -.89f, -2.3f) , 0.005f , Materials.blue );
		
		Sphere kRef = new Sphere(new Vector(-0.35f , -.89f, -1.5f) , 0.005f , Materials.testRef );
		
		
		s.addShape(k2);
		s.addShape(k3);
		s.addShape(k3b);
		s.addShape(kRef);
		
		ParallelQuader q1 = new ParallelQuader(new Vector(0.3f, -0.96f, -3.5f), new Vector(0.5f, -0.7f, -1.4f), Materials.darkgrey);
		s.addShape(q1);
		
		Color lc = new Color(0.2f,0.2f,0.35f);
		
		PointLight dlx = new PointLight();
		dlx.setColor(lc);
		dlx.setPosition(new Vector(-2f, 3f, -1.5f));
//		s.addLightSource(dlx);
		
		PointLight dlxa = new PointLight();
		dlxa.setColor(lc);
		dlxa.setPosition(new Vector(2f, 3f, -1.5f));
//		s.addLightSource(dlxa);
		
		PointLight dl = new PointLight();
		dl.setColor(lc);
		dl.setPosition(new Vector(0.0f , 1.f, -2.5f));
		s.addLightSource(dl);
		
		PointLight dll = new PointLight();
		dll.setColor(lc);
		dll.setPosition(new Vector(0.5f , 1.f, -2.5f));
		s.addLightSource(dll);
		
		PointLight dl1 = new PointLight();
		dl1.setColor(lc);
		dl1.setPosition(new Vector(-0.5f , 1.f, -2.5f));
		s.addLightSource(dl1);
		
		PointLight dl2 = new PointLight();
		dl2.setColor(lc);
		dl2.setPosition(new Vector(-1.f , 1.f, -2.5f));
		s.addLightSource(dl2);
		
		PointLight dl2b = new PointLight();
		dl2b.setColor(lc);
		dl2b.setPosition(new Vector(1.f , 1.f, -2.5f));
		s.addLightSource(dl2b);
		
		PointLight dl3 = new PointLight();
		dl3.setColor(lc);
		dl3.setPosition(new Vector(-.75f , 1.f, -2.5f));
		s.addLightSource(dl3);
		
		PointLight dl4 = new PointLight();
		dl4.setColor(lc);
		dl4.setPosition(new Vector(0.75f , 1.f, -2.5f));
		s.addLightSource(dl4);
		
		PointLight dlHigh = new PointLight();
		dlHigh.setColor(new Color(0.7f, 0.7f, 0.7f));
		dlHigh.setPosition(new Vector(0.f, 4.f, -2.f));
//		s.addLightSource(dlHigh);
		
		PointLight dlHigh2 = new PointLight();
		dlHigh2.setColor(new Color(0.7f, 0.7f, 0.7f));
		dlHigh2.setPosition(new Vector(0.f, 3.9f, -2.5f));
//		s.addLightSource(dlHigh2);
		
		String filename = path + "/" + "raytracer.png";
		new ImageGenerator(new Raytracer(c,s), resolutionX, resolutionY, filename, "png");
		ImageGenerator.showImage(filename);
	}

}
