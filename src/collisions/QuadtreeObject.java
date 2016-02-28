package collisions;

public abstract class QuadtreeObject {

	private double quadX, quadY, quadRadius;
	private boolean immobile; 
	
	public QuadtreeObject(double quadX, double quadY, double quadRadius, boolean immobile){
		//NOTE: Immobile objects are sequestered into one tree branch and are calculated only once.
		//NOTE: Radius is actually block radius, or square radius. 
	}

	public double getQuadX() {
		return quadX;
	}

	public void setQuadX(double quadX) {
		this.quadX = quadX;
	}

	public double getQuadY() {
		return quadY;
	}

	public void setQuadY(double quadY) {
		this.quadY = quadY;
	}

	public double getQuadRadius() {
		return quadRadius;
	}

	public void setQuadRadius(double quadRadius) {
		this.quadRadius = quadRadius;
	}

	public boolean isImmobile() {
		return immobile;
	}

}
