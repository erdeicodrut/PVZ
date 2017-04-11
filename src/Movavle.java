class Movable {
	private PApplet p;
	private float x;
	private float y;
	private float width;
	private float height;
	private float hp;

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

	public void collidesWith(Movable other) {
		if (x + width  other.x &&
			y < other.y + other.height &&
			height + y > other.y) {
			return true;
		} else {
			return false;
		}
	}

	public void isAlive() {
		if (hp <= 0) {
			return true;
		} else {
			return false;
		}
	}
}