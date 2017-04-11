import processing.core.PApplet;
<<<<<<< HEAD

class Zombie extends Movable {
	private PApplet p;
	private float speed;
	private float damage;

	public Zombie(PApplet p, float x, float y, float width, float height, float hp, float speed, float damage) {
		super(p, x, y, width, height, hp);
		this.p = p;
=======
import processing.core.PVector;

class Zombie extends Living
{
	private float speed;
	private float damage;

	public Zombie(PApplet p, PVector pos, PVector size, float hp, float speed, float damage) {
		super(p, pos, size, hp);

>>>>>>> processing
		this.speed = speed;
		this.damage = damage;
	}

	public void move() {
		pos.x -= speed;
	}

	public void attack(Plant other) {
		if (other.hp > 0) {
			other.hp -= damage;
		}
	}

<<<<<<< HEAD
	public void recieve(float enemyDamage, Effect effect) {
=======
	public void receive(float damage, Effect effect) {
>>>>>>> processing
		if (hp > 0) {
			hp -= damage;
		}
	}

	@Override
	public void show()
	{
		p.fill(255, 0, 0);
		p.rect(pos.x, pos.y, size.x, size.y);
	}
}