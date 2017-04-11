import processing.core.PApplet;

class Movable {
	private PApplet p;
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected float hp;

	public Movable(PApplet p, float x, float y, float width, float height, float hp) {
		this.p = p;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.hp = hp;
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public boolean collidesWith(Movable other) {
		if (x + width  < other.x &&
			y < other.y + other.height &&
			height + y > other.y) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isAlive() {
		if (hp <= 0) {
			return true;
		} else {
			return false;
		}
	}
}