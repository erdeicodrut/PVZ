import processing.core.PApplet;
import processing.core.PVector;

class Cell extends GameObject
{
	private Plant plant;

	public Cell(PApplet p, PVector pos, PVector size) {
	    super(p, pos, size);
	}

	public boolean isOccupied() {
		return plant != null;
	}

	// Retain and set the position of the plant passed in
	public Plant plantHere(Plant toPlant) {
		//TODO shop stuff

		if (this.isOccupied() == false) {
			this.plant = toPlant;
			this.plant.pos = this.pos;

			pvz.livings.add(plant);
			CollisionManager.addObject(plant);

			return toPlant;
		}
		else return null;
	}

	public void show() {
	    p.noFill();
	    p.stroke(51);
	    p.strokeWeight(3);
		p.rect(pos.x, pos.y, size.x, size.y);

		if (plant != null) plant.show();

		if (plant != null && plant.isAlive() == false) { plant = null; }
	}
}