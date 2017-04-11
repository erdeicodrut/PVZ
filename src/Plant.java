import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class Plant extends Movable {
	private Effect effect;
	private float speed;
	private float damage;



	public Plant(PApplet p, float x, float y, float hp, Effect effect) {
		super(p);
		this.x = x;
		this.y = y;

		this.width = 142/2;
		this.height = 103/2;

		this.hp = hp;
		this.effect = effect;
	}

	public Bullet shoot() {
		return new Bullet(p, x, y, 15, 15, 1, speed, damage, effect);
	}

	public void show() {
		p.rectMode(PConstants.CENTER);
		p.fill(0, 255, 0);
		p.rect(x+width/2, y+height/2, width, height);
    }
}