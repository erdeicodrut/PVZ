import processing.core.PApplet;
import processing.core.PVector;

import java.lang.reflect.InvocationTargetException;

class Cell extends GameObject
{
	public Plant plant;

	public Cell(PApplet p, PVector pos, PVector size) {
	    super(p, pos, size);
	}

	public boolean isOccupied() {
		return plant != null;
	}

	// Retain and set the position of the plant passed in
	public Plant plantHere(Plant toPlant) {


		if (this.isOccupied() == false) {
			try {
				this.plant = toPlant.getClass().getConstructor(toPlant.getClass()).newInstance(toPlant);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.plant.setPosition(this.pos);


			pvz.plants.add(this.plant);
			CollisionManager.addObject(this.plant);

			return toPlant;
		}
		else return null;
	}

	public void clean() {
		plant.hp = 0;
	}

	public void clear() { plant = null; }

	public void show() {
	    p.noFill();
	    p.stroke(51);
	    p.strokeWeight(3);
		p.rect(pos.x, pos.y, size.x, size.y);


		if (plant != null && plant.isAlive() == false) { plant = null; }
	}
}