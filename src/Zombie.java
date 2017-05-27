import processing.core.PApplet;
import processing.core.PVector;

class Zombie extends Living {
	protected float speed;
	protected float damage;
	protected float totalDamage = 0;

	public boolean head = true;
	
	protected int timer = 30;
	protected static int timerSpawn = Globals.spawnTime;
	
	public int frame_attack_headless = 0;
	
	
	public Zombie() { super(); }

	public Zombie(PApplet p, PVector pos, PVector size, float hp, float speed, float damage) {
		super(p, pos, size, hp);

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

    public boolean isZombie() { return true; }

	public static Zombie spawn() {
		if (timerSpawn-- == 0)
		{
			PVector zombiePos = PVector.add(Globals.fieldPos,
					new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
							p.floor(p.random(5f)) * Globals.cellSize.y));

			timerSpawn = Globals.spawnTime;

			return new Zombie(p, zombiePos, Globals.zombieSize, 10, 1, 5);

		}
		return null;
	}

    @Override
	public void show() {
		p.fill(255, 0, 0);
		p.rect(pos.x, pos.y, size.x, size.y);
	}

	@Override
	public void onCollisionWith(ICollision other) {
		if (Plant.class.isAssignableFrom(other.getClass())) {
            Plant plant = (Plant) other;
            attack(plant);
        }
	}

	public static void resetSpawn() {
		timerSpawn = Globals.spawnTime / 2;
	}
}