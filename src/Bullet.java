import processing.core.PApplet;

class Bullet extends Movable {
	private PApplet p;
	private float speed;
	private float damage;
	private Effect effect;
	private float hp; // this has hp because any time it hits something the hp goes to 0 and it will be automatically collected by the manual garbage collecter we will implement
					  // TODO implement hp to everything REFACTORING JOB

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
		other.recieve(damage, effect);
		this.hp = 0;
	}
}