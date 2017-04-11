import processing.core.PApplet;
<<<<<<< HEAD

class Plant extends Movable {
	private PApplet p;
	private Effect effect;
	private float speed;
	private float damage;

	public Plant(PApplet p, float x, float y, float width, float height, float hp, float speed, Effect effect) {
		super(p, x, y, width, height, hp);
=======
import processing.core.PVector;

class Plant extends Living
{
	private Effect effect;
	private float damage;

	private int timer = 30;

	public Plant(PApplet p, float hp, Effect effect) {
		super(p, new PVector(0, 0), Globals.flowerSize, hp);

>>>>>>> processing
		this.effect = effect;


	}

<<<<<<< HEAD
	public Bullet shoot() {
		return new Bullet(p, x, y, 15, 15, 1, speed, damage, effect);
	}

	public void show() {
	    
=======
	public void shoot() {
		if (timer-- < 0)
		{
			timer = 30;
			Globals.livings.add(new Bullet(p, pos, 15, 15, effect));
		}
	}

	public void show() {
//		p.rectMode(PConstants.CENTER);
		p.fill(0, 255, 0);
		p.rect(pos.x, pos.y, size.x, size.y);
>>>>>>> processing
    }
}