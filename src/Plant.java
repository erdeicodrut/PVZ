class Plant extends Movable {
	private PApplet p;
	private Effect effect;
	private float speed;

	public Plant(PAplet p, float x, float y, float width, float height, float hp, float speed, Effect effect) {
		this.p = p;
		super(x, y, width, height, hp);
		this.effect = effect;
		this.speed = speed;
	}

	public void shoot() {
		return new Bullet(x, y, speed, damage, effect);
	}
}