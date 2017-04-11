import processing.core.PApplet;

class Cell {
	private PApplet p;
	private float x;
	private float y;
	private float width;
	private float height;
	private Plant plant;

	public Cell(PApplet p, float x, float y, float width, float height) {
	    this.p = p;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean isOccupied() {
		if (plant != null) {
			return false;
		} else {
			return true;
		}
	}

	public void place(Plant to) {

		//TODO shop stuff

		if (this.isOccupied() == false) {
			this.plant = to;
		}
	}

	public void show() {
	    p.noFill();
	    p.stroke(51);
	    p.strokeWeight(3);
		p.rect(x, y, width, height);
	}
}