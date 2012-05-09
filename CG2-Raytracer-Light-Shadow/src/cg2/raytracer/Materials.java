package cg2.raytracer;

import cg2.raytracer.shapes.Material;
import cg2.raytracer.shapes.MaterialRefrac;
import cg2.vecmath.Color;

public class Materials {
	
	private Materials() {
		
	}
	
	public static Material grey = new Material(new Color(0.005f,0.005f,0.005f),new Color(0.005f,0.005f,0.005f), new Color(0.1f,0.1f,0.1f), 0);
	
	public static Material white_grey = new Material(new Color(0.1f,0.1f,0.1f),new Color(0.1f,0.1f,0.1f), new Color(0.005f,0.005f,0.005f), 10000);
	
	public static Material darkgrey = new Material(new Color(0.2f,0.2f,0.2f) , new Color(0.2f,0.2f,0.2f) , new Color(0.1f,0.1f,0.1f), 1000);
	
	public static Material darkgrey2 = new Material(new Color(0.2f,0.2f,0.2f) , new Color(0.2f,0.2f,0.2f) , new Color(0.2f,0.2f,0.2f), 1000);
	
	public static Material blue = new Material(new Color(0.f,0.0f,0.5f),new Color(1.f,1.f,.6f), new Color(0f,0f,0.3f), 100 );
	
	public static Material green = new Material(new Color(0.0f,0.5f,0.0f),new Color(0.8f,0.8f,0.8f), new Color(0,0.2f,0.0f), 100);
	
	public static Material yellow = new Material(new Color(0.7f, 0.7f, 0f),new Color(0.7f, 0.7f, 0f), new Color(0.6f,0.6f,0.f), 100);
	
	public static Material test = new Material(new Color(0.2f,0.2f,0.2f),new Color(1.0f,1.f,1.0f), new Color(0.05f,0.05f,0.05f), 100);
	
	public static Material test2 = new Material(new Color(0.6f,0.f,0.f),new Color(0.8f,0.8f,0.8f), new Color(0.55f,0.0f,0.0f), 100);

	
	
	/** REFRACTORABLE MATERIALS **/
	
	public static MaterialRefrac testRef = new MaterialRefrac(new Color(0.6f,0.f,0.f),new Color(0.8f,0.8f,0.8f), new Color(0.55f,0.0f,0.0f), 100 , 1.333f);

}
