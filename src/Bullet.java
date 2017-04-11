import processing.core.PApplet;

class Bullet extends Movable {
	private PApplet p;
	private float speed;
	private float damage;
	private Effect effect;

	public Bullet(PApplet p, float x, float y, float width, float height, float hp, float speed, float damage, Effect effect) {
		super(p, x, y, width, height, hp);
		this.effect = effect;
		this.speed = speed;
		this.damage = damage;
	}

	public void move() {
		x -= speed;
	}

	public void hit(Zombie other) {
		hp = 0;
		other.recieve(damage, effect);
	}
}