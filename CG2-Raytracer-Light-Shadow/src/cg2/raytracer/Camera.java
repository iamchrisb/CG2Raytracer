package cg2.raytracer;

import cg2.vecmath.Vector;

public class Camera {

	/**
	 * how is
	 */
	private Vector upVector;
	/**
	 * position of the cam
	 */
	private Vector eye;
	private float w;
	private float h;
	private int angle;
	private float distance;

	public Camera() {
		this.upVector = new Vector(0, 1, 0);
		this.eye = new Vector(0, 0, 0);
		this.w = 1000f;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param resolutionX
	 * @param resolutionY
	 * @return
	 */
	Ray generateRay(final int x, final int y, final int resolutionX,
			final int resolutionY) {
		h = (resolutionY * w) / resolutionX;

		float za = getZa(x, y, resolutionX, resolutionY);
		float xi = -1 * (w / 2) + (x + 0.5f) * (w / resolutionX);
		float yj = -1 * (h / 2) + (y + 0.5f) * (h / resolutionY);

		return new Ray(eye, new Vector(xi, yj, -za));
	}

	private float getZa(final int x, final int y, final int resolutionX,
			final int resolutionY) {

		distance = (float) (w / (2 * Math.tan(angle / 2)));

		return distance;
	}

	public Vector getUpVector() {
		return upVector;
	}

	public void setAngle(int a) {
		this.angle = a;
	}

	public Vector getEye() {
		return eye;
	}

}
