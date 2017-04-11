import processing.core.PApplet;
import processing.core.PVector;

class Zombie extends Living
{
	private float speed;
	private float damage;

	private int timer = 30;

	public Zombie(PApplet p, PVector pos, PVector size, float hp, float speed, float damage) {
		super(p, pos, size, hp);

		this.speed = speed;
		this.damage = damage;
	}

	public void move() {
		pos.x -= speed;
	}

	public void attack(Plant other) {
		if (timer-- < 0) {
			if (other.hp > 0) {
				other.hp -= damage;
			}
			timer = 30;
		}
	}

	public void receive(float damage, Effect effect) {
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