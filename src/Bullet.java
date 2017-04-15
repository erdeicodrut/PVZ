import processing.core.PApplet;
import processing.core.PVector;

class Bullet extends Living implements Effects
{
	private float speed;
	private float damage;
	private Effect effect;

	public Bullet(PApplet p, PVector pos, float speed, float damage, Effect effect) {
		super(p, pos, new PVector(20, 20), 1);
		pvz.livings.add(this);
		CollisionManager.addObject(this);

		this.size = new PVector(20, 20);
		this.effect = effect;
		this.speed = speed;
		this.damage = damage;
	}

	public void update() {
		move();
	}

	public void move() {
		pos.x += speed;
	}

	// This method is called when it collides with a zombie
	public void hit(Zombie other) {
		hp = 0;
		other.receiveDamage(damage);
	}

	@Override
	public void show()
	{
		// Temp
		p.fill(255, 255, 0);
		p.ellipse(pos.x, pos.y, size.x, size.y);
	}

	@Override
	public void onCollisionEnterWith(ICollision other) {
		if (other.getClass() == Zombie.class) {
			System.out.println("COLLIDED");
			Zombie zombie = (Zombie) other;
			this.hit(zombie);
			hp = 0;
		}
	}
}