import processing.core.PApplet;

class Cell {
	private PApplet p;
	public float x;
	public float y;
	private float width;
	private float height;
	public Plant plant;

	public Cell(PApplet p, float x, float y, float width, float height) {
	    this.p = p;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean isOccupied() {
		if (plant == null) {
			return false;
		} else {
			return true;
		}
	}

	public Plant place(Plant toPlant) {

		//TODO shop stuff

		if (this.isOccupied() == false) {
			this.plant = toPlant;
			return toPlant;
		}
		else return null;
	}

	public void show() {
	    p.noFill();
	    p.stroke(51);
	    p.strokeWeight(3);
		p.rect(x, y, width, height);
	}
}