import processing.core.PApplet;
import processing.core.PVector;

class Zombie extends Living {
	private float speed;
	private float damage;
	private float totalDamage = 0;

	private int timer = 30;
	private static int timerSpawn = 150;

	public Zombie(PApplet p, PVector pos, PVector size, float hp, float speed, float damage) {
		super(p, pos, size, hp);
		pvz.livings.add(this);
		CollisionManager.addObject(this);

		this.speed = speed;
		this.damage = damage;
	}

	public void move() {
		pos.x -= speed;
	}

	public void update() {
		if (!CollisionManager.isCollidingWithClass(this, Plant.class))
			move();
	}

	// This method is called when it collides with a plant
	//
	public void attack(Plant other) {
		if (timer-- < 0) {
			if (other.hp > 0) {
				other.hp -= damage;
			}
			timer = 30;
		}
	}

	// Receives some damage/effect from a bullet
	public void receiveDamage(float damage) {
		if (hp > 0)
		{
			totalDamage += damage;
		}
		hp -= totalDamage;
		totalDamage = 0;
	}

	public void receiveEffect(Effect effect) {
		Effects.applyEffect(effect, this);
	}

	public static void spawn() {
		if (timerSpawn-- == 0)
		{
			PVector zombiePos = PVector.add(Globals.fieldPos,
					new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
							p.floor(p.random(6f)) * Globals.cellSize.y));

			new Zombie(p, zombiePos, Globals.zombieSize, 10, 1, 5);

			timerSpawn = 150;

		}
	}

	@Override
	public void show() {
		p.fill(255, 0, 0);
		p.rect(pos.x, pos.y, size.x, size.y);
	}

	@Override
	public void onCollisionWith(ICollision other)
	{
		if (other.getClass() == Plant.class)
		{
			Plant plant = (Plant) other;
			attack(plant);
		}
	}
}