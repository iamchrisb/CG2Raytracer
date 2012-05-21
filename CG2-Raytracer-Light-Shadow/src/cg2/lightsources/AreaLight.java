package cg2.lightsources;

import java.util.ArrayList;

import org.omg.CORBA.FREE_MEM;

import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class AreaLight implements LightSource {
	
	private ArrayList<PointLight> list = new ArrayList<PointLight>();
	private Color c;
	private Vector pos;
	private float frequency;
	public AreaLight(final Color c, final Vector pos , int first , int second , float frequency ) {
		this.c = c;
		this.pos = pos;
		this.frequency = frequency;
		instanceLights(first , second);
	}
	private void instanceLights(int first , int second) {
		float count = 0.0f;
		for (int i = 0; i < first + second; i++) {
			list.add(new PointLight(new Vector(pos.x + count , pos.y , pos.z) , c));
			list.add(new PointLight(new Vector(pos.x , pos.y , pos.z + count) , c));
			count += frequency;
		}
	}
	public ArrayList<PointLight> getList() {
		return list;
	}
	public void setList(ArrayList<PointLight> list) {
		this.list = list;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
	public Vector getPos() {
		return pos;
	}
	public void setPos(Vector pos) {
		this.pos = pos;
	}

}
