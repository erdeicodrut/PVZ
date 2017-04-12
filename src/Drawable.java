import processing.core.PApplet;
import processing.core.PVector;

public abstract class Drawable {
	public PApplet p;
	public PVector pos;
	public PVector size;

	public Drawable(PApplet p) {
		this.p = p;
	}

	public Drawable(PApplet p, PVector pos, PVector size) {
		this(p);
		this.pos = new PVector(pos.x, pos.y);
		this.size = new PVector(size.x, size.y);
	}

	// Box collision check between 2 Drawable objects
	public boolean collidesWith(Drawable other) {
		return !(pos.x + size.x < other.pos.x || pos.x > other.pos.x + other.size.x || pos.y + size.y < other.pos.y || pos.y > other.pos.y + other.size.y);
	}

	public abstract void show();
}
