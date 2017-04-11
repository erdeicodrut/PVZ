class Zombie extends Movable {
	private PApplet p;
	private float speed;
	private float damage;

	public Zombie(PApplet p, float x, float y, float width, float height, float hp, float speed, float damage) {
		this.p = p;
		super(x, y, width, height, hp);
		this.speed = speed;
		this.damage = damage;
	}

	public void move() {
		x -= speed;
	}

	public void attack(Plant other) {
		if (other.hp > 0) {
			other.hp -= damage;
		}
	}

	public recieve(float enemyDamage) {
		if (hp > 0) {
			hp -= enemyDamage;
		}
	}
}