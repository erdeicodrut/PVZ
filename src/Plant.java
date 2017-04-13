import processing.core.PApplet;
import processing.core.PVector;

class Plant extends Living {
	private Effect effect;
	private float damage;

	private int timer = 30;

	public Plant(PApplet p, float hp, float damage, Effect effect) {
		super(p, new PVector(0, 0), Globals.flowerSize, hp);
		this.damage = damage;
		this.effect = effect;
    }

	public Plant(Plant plant) {
		super(plant.p, plant.pos, Globals.flowerSize, plant.hp);
		this.effect = plant.effect;
	}

	// Shoots a bullet based on a timer
	public void shoot() {
        System.out.println(timer);
        if (timer-- <= 0) {
            System.out.println(timer + " TRUE");
			pvz.bullets.add(new Bullet(p, pos, 15, damage, effect));
            timer = 30;
        }
	}

	@Override
	public void show() {
		p.fill(0, 255, 0);
		p.rect(pos.x, pos.y, size.x, size.y);
    }
}