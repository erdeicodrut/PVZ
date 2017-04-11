import processing.core.PApplet;
<<<<<<< HEAD

class Bullet extends Movable {
	private PApplet p;
	private float speed;
	private float damage;
	private Effect effect;
	private float hp; // this has hp because any time it hits something the hp goes to 0 and it will be automatically collected by the manual garbage collecter we will implement
					  // TODO implement hp to everything REFACTORING JOB

	public Bullet(PApplet p, float x, float y, float width, float height, float hp, float speed, float damage, Effect effect) {
		super(p, x, y, width, height, hp);
=======
import processing.core.PVector;

class Bullet extends Living
{
	private float speed;
	private float damage;
	private Effect effect;

	public Bullet(PApplet p, PVector pos, float speed, float damage, Effect effect) {
		super(p, pos, new PVector(20, 20), 1);

		this.size = new PVector(20, 20);
>>>>>>> processing
		this.effect = effect;
		this.speed = speed;
		this.damage = damage;
	}

	public void move() {
		pos.x += speed;
	}

	public void hit(Zombie other) {
		hp = 0;
		other.receive(damage, effect);
	}

<<<<<<< HEAD
	public void hit(Zombie other) {
		other.recieve(damage, effect);
		this.hp = 0;
=======
	@Override
	public void show()
	{
		// Temporary
		p.fill(255, 255, 0);
		p.ellipse(pos.x, pos.y, size.x, size.y);
>>>>>>> processing
	}
}