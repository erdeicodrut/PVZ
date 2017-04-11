class Plant extends Movable {
	private Effect effect;
	private float speed;

	public Plant(float x, float y, float width, float height, float hp, float speed, Effect effect) {
		super(x, y, width, height, hp);
		this.effect = effect;
		this.speed = speed;
	}

	public void shoot() {
		return new Bullet(x, y, speed, damage, effect);
	}
}