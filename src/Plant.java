import processing.core.PApplet;
import processing.core.PVector;

class Plant extends Living {
	private Effect effect;
	private float damage;

	private int timer = 30;

	public static int DebugingNum = 0;

	public Plant(PApplet p, float hp, float damage, Effect effect) {
		super(p, new PVector(0, 0), Globals.flowerSize, hp);
		this.damage = damage;
		this.effect = effect;
		DebugingNum++;
    }

	public Plant(Plant plant) {
		super(plant.p, plant.pos, Globals.flowerSize, plant.hp);
		this.effect = plant.effect;
		DebugingNum++;
	}

	public void update() {
        for (Zombie zombie : pvz.zombies)
            if (this.pos.y == zombie.pos.y) {
				this.shoot();
                break;
            }
	}

	// Shoots a bullet based on a timer
	public void shoot() {
        if (timer-- <= 0) {
            Bullet bullet = new Bullet(p, pos, 15, damage, effect);
            CollisionManager.addObject(bullet);
			pvz.bullets.add(bullet);
            timer = 30;
        }
	}

	@Override
	public void show() {
		p.fill(0, 255, 0);
		p.rect(pos.x, pos.y, size.x, size.y);
    }
}