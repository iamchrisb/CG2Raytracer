package cg2.depricated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cg2.lightsources.LightSource;
import cg2.lightsources.PointLight;
import cg2.vecmath.Color;
import cg2.vecmath.Vector;

public class AreaLightdepri implements LightSource {
	
	private List<PointLight> list = new ArrayList<PointLight>();
	private Color c;
	
	public AreaLightdepri() {
	}

	public AreaLightdepri(PointLight ... p) {
		list = Arrays.asList(p);
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public ArrayList<PointLight> getList() {
		ArrayList<PointLight> plist = new ArrayList<PointLight>();
		for (PointLight pointLight : list) {
			plist.add(pointLight);
		}
		return plist ;
	}

	public void setList(ArrayList<PointLight> list) {
		this.list = list;
	}
	
	public void setSource(Vector v){
		PointLight p = new PointLight();
		p.setColor(c);
		p.setPosition(v);
		list.add(p);
	}
	
	public void addPointLight(PointLight p){
		list.add(p);
	}

}
