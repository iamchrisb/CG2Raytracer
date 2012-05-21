package cg2.material;

import cg2.vecmath.Color;

public class Materials {
	
	private Materials() {
		
	}
	
	/** REFRACTOR INDEZES **/
	
	public static float air = 1.00029f;
	
	public static float vacuum = 1.0000f;
	
	public static float water = 1.333f;
	
	public static float glas = 1.5000f;
	
	public static float solid = 1000f;
	
	/** MATERIALS **/
	
	public static Material grey = new Material(new Color(0.005f,0.005f,0.005f),new Color(0.005f,0.005f,0.005f), new Color(0.1f,0.1f,0.1f), 0 , solid);
	
	public static Material white_grey = new Material(new Color(0.1f,0.1f,0.1f),new Color(0.1f,0.1f,0.1f), new Color(0.005f,0.005f,0.005f), 10000 , solid);
	
	public static Material darkgrey = new Material(new Color(0.2f,0.2f,0.2f) , new Color(0.2f,0.2f,0.2f) , new Color(0.1f,0.1f,0.1f), 40 , solid);
	
	public static Material darkgrey2 = new Material(new Color(0.2f,0.2f,0.2f) , new Color(0.2f,0.2f,0.2f) , new Color(0.2f,0.2f,0.2f), 1000 , solid);
	
	public static Material blue = new Material(new Color(0.f,0.0f,0.5f),new Color(1.f,1.f,.6f), new Color(0f,0f,0.3f), 100 , solid );
	
	public static Material green = new Material(new Color(0.0f,0.5f,0.0f),new Color(0.8f,0.8f,0.8f), new Color(0,0.2f,0.0f), 100 , solid );
	
	public static Material yellow = new Material(new Color(0.7f, 0.7f, 0f),new Color(0.7f, 0.7f, 0f), new Color(0.6f,0.6f,0.f), 100 , solid );
	
	public static Material test = new Material(new Color(0.2f,0.2f,0.2f),new Color(1.0f,1.f,1.0f), new Color(0.05f,0.05f,0.05f), 100 , solid );
	
	public static Material test2 = new Material(new Color(0.6f,0.f,0.f),new Color(0.8f,0.8f,0.8f), new Color(0.55f,0.0f,0.0f), 100 , solid );

	public static Material red = new Material(new Color(0.5f,0.f,0.f),new Color(0.6f,0.f,0.f), new Color(0.2f,0.0f,0.0f), 100 , solid );
	
	public static Material testRef = new Material(new Color(1.0f,0.0f,0.0f),new Color(0.0f,0.f,0.f), new Color(0.0f,0.0f,0.0f), 100 , water );
	
	public static Material testTransmit = new Material(new Color(0.00f,0.00f,0.00f),new Color(0.5f,0.5f,0.5f), new Color(0.0f,0.0f,0.0f), 100 , air );
	
	public static Material white = new Material(new Color(1.0f,1.0f,1.0f),new Color(.8f,0.8f,0.8f), new Color(0.6f,0.6f,0.6f), 100 , solid );
}
