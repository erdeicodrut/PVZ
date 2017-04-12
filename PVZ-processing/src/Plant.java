import processing.core.PApplet;
import processing.core.PVector;

class Plant extends Living {
	private Effect effect;
	private float damage;

	private int timer = 30;

	public Plant(PApplet p, float hp, Effect effect) {
		super(p, new PVector(0, 0), Globals.flowerSize, hp);

		this.effect = effect;
	}

	// Shoots a bullet based on a timer
	public void shoot() {
	    if (timer-- < 0) {
            pvz.bullets.add(new Bullet(p, pos, 15, 15, effect));
            timer = 30;
        }
	}

	@Override
	public void show() {
		p.fill(0, 255, 0);
		p.rect(pos.x, pos.y, size.x, size.y);
    }
}