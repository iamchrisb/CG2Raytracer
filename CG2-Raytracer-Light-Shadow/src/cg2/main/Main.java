package cg2.main;

import cg2.lightsources.Light;
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
		
		Plane backPlane = new Plane(new Vector(0f, 0f, -3.5f), new Vector(0f, 0f, 1f), Materials.darkgrey);
//		Plane groundPlane = new Plane(new Vector(0f, -.3f, 0f), new Vector(0f, 1f, 0f), Materials.darkgrey );
		
		Plane groundPlane = new Plane(new Vector(0f, -1f, 0f), new Vector(0f, 1f, 0f), Materials.darkgrey );
//		Plane rightPlane = new Plane(new Vector(4f, 0f, 0f), new Vector(1f, 0f, 0f), new Color(0.2f,0.2f,0.2f));
		
		
//		s.addShape(backPlane);
		s.addShape(groundPlane);
//		s.addShape(rightPlane);
		
		Sphere k1 = new Sphere(new Vector(0.f, -.3f, -2.5f) , 0.005f ,Materials.test2 ); 
		Sphere k2 = new Sphere(new Vector(-0.2f, -0.1f, -2.5f) , 0.004f , Materials.yellow);
		Sphere k3 = new Sphere(new Vector(1.f , 1.f, -2.5f) , 0.5f , Materials.green );
//		Sphere k4 = new Sphere(new Vector(3f, -2.2f, -28f), 1.0f, new Color(0.5f, 1.0f,0.5f));
//		Sphere k5 = new Sphere(new Vector(-3, 1.3f, -8f), 0.6f, new Color(0.5f, 1.0f,0.5f));
		Sphere klittle = new Sphere(new Vector(0.07f, -0.1f, -2.5f) , 0.002f , Materials.green);
		
		
//		s.addShape(k1);
//		s.addShape(k2);
		s.addShape(k3);
//		s.addShape(k4);
//		s.addShape(k5);
//		s.addShape(klittle);
		
		ParallelQuader q1 = new ParallelQuader(new Vector(0.3f, -0.2f, -3.5f), new Vector(0.5f, 0.1f, -2), Materials.yellow);
//		s.addShape(q1);
		
		Color lc = new Color(0.5f,0.5f,0.5f);
		
		Light dlx = new Light();
		dlx.setColor(lc);
		dlx.setPosition(new Vector(-2f, 3f, -1.5f));
//		s.addLightSource(dlx);
		
		Light dlxa = new Light();
		dlxa.setColor(lc);
		dlxa.setPosition(new Vector(2f, 3f, -1.5f));
//		s.addLightSource(dlxa);
		
		Light dl = new Light();
		dl.setColor(lc);
		dl.setPosition(new Vector(-1.f , 1.f, -2.5f));
		s.addLightSource(dl);
		
		Light dl1 = new Light();
		dl1.setColor(lc);
		dl1.setPosition(new Vector(0, -2, 1));
//		s.addLightSource(dl1);
		
		Light dl2 = new Light();
		dl2.setColor(new Color(0.8f, 0.8f, 0.8f));
		dl2.setPosition(new Vector(0, 4, 0.f));
//		s.addLightSource(dl2);
		
		Light dl2b = new Light();
		dl2b.setColor(new Color(0.8f, 0.8f, 0.8f));
		dl2b.setPosition(new Vector(0, 0, 0.f));
//		s.addLightSource(dl2b);
		
		Light dl3 = new Light();
		dl3.setColor(new Color(0.8f, 0.8f, 0.8f));
		dl3.setPosition(new Vector(-14, 14, -20.f));
//		s.addLightSource(dl3);
		
		Light dl4 = new Light();
		dl4.setColor(new Color(0.6f, 0.6f, 0.6f));
		dl4.setPosition(new Vector(-14, 14, -10.f));
//		s.addLightSource(dl4);
		
		Light dlHigh = new Light();
		dlHigh.setColor(new Color(0.7f, 0.7f, 0.7f));
		dlHigh.setPosition(new Vector(0.f, 4.f, -2.f));
//		s.addLightSource(dlHigh);
		
		Light dlHigh2 = new Light();
		dlHigh2.setColor(new Color(0.7f, 0.7f, 0.7f));
		dlHigh2.setPosition(new Vector(0.f, 3.9f, -2.5f));
//		s.addLightSource(dlHigh2);
		
		String filename = path + "/" + "raytracer.png";
		new ImageGenerator(new Raytracer(c,s), resolutionX, resolutionY, filename, "png");
		ImageGenerator.showImage(filename);
	}

}
