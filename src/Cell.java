import processing.core.PApplet;
<<<<<<< HEAD

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
=======
import processing.core.PVector;

class Cell extends Drawable {
	private Plant plant;

	public Cell(PApplet p, PVector pos, PVector size) {
	    super(p, pos, size);
>>>>>>> processing
	}

	public boolean isOccupied() {
		return plant != null;
	}

<<<<<<< HEAD
	public void place(Plant to) {

		//TODO shop stuff

		if (this.isOccupied() == false) {
			this.plant = to;
=======
	public Plant plantHere(Plant toPlant) {
		//TODO shop stuff

		if (this.isOccupied() == false) {
			this.plant = toPlant;
			this.plant.pos = this.pos;
			return toPlant;
>>>>>>> processing
		}
		else return null;
	}

	public void show() {
	    p.noFill();
	    p.stroke(51);
	    p.strokeWeight(3);
		p.rect(pos.x, pos.y, size.x, size.y);
	}

	public void show() {
	    p.noFill();
	    p.stroke(51);
	    p.strokeWeight(3);
		p.rect(x, y, width, height);
	}
}