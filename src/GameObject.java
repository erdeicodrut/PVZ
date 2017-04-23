import processing.core.PApplet;
import processing.core.PVector;

public abstract class GameObject implements IDrawable, IInput, ICollision
{
	public static PApplet p;
	public PVector pos;
	public PVector size;

	public GameObject(PApplet p) {
		this.p = p;
	}

	public GameObject(PApplet p, PVector pos, PVector size) {
		this(p);
		this.pos = new PVector(pos.x, pos.y);
		this.size = new PVector(size.x, size.y);
	}

	// Box collision check between 2 GameObject objects
	public boolean collidesWith(GameObject other) {
		return !((pos.x > other.pos.x + other.size.x || pos.x < other.pos.x - other.size.x) && (pos.y == other.pos.y) ) ;
	}

	public void setPosition(PVector pos) {
		this.pos.set(pos.x, pos.y);
	}

	public void setZ(float z) {
		this.pos.z = z;
		InputManager.sortQueue();
	}

	// IInput
	@Override
	public boolean containsPoint(PVector point)
	{
		PVector bottomRightCorner = PVector.add(this.pos, this.size);
		return (point.x >= this.pos.x) && (point.x < bottomRightCorner.x) &&
				(point.y >= this.pos.y) && (point.y < bottomRightCorner.y);
	}

	@Override
	public float getZ() {
		return this.pos.z;
	}

	// ICollision
	@Override
	public PVector getPosition() { return pos; }
	public PVector getSize() { return size; }
}
