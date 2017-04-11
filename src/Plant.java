import processing.core.PApplet;

class Plant extends Movable {
	private PApplet p;
	private Effect effect;
	private float speed;
	private float damage;

	public Plant(PApplet p, float x, float y, float width, float height, float hp, float speed, Effect effect) {
		super(p, x, y, width, height, hp);
		this.effect = effect;
		this.speed = speed;
	}

	public Bullet shoot() {
		return new Bullet(p, x, y, 15, 15, 1, speed, damage, effect);
	}

	public void show() {
	    
    }
}