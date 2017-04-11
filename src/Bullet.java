import processing.core.PApplet;
import processing.core.PVector;

class Bullet extends Living
{
	private float speed;
	private float damage;
	private Effect effect;

	public Bullet(PApplet p, PVector pos, float speed, float damage, Effect effect) {
		super(p, pos, new PVector(20, 20), 1);

		this.size = new PVector(20, 20);
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

	@Override
	public void show()
	{
		// Temporary
		p.fill(255, 255, 0);
		p.ellipse(pos.x, pos.y, size.x, size.y);
	}
}