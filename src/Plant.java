import processing.core.PApplet;
import processing.core.PVector;

import java.util.Iterator;

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
		this(plant.p, plant.hp, plant.damage, plant.effect);
	}

	public void update() {
        for (int i = 0; i < pvz.livings.size(); i++)
        {
	        Living living = pvz.livings.get(i);
	        if (living.getClass() == Zombie.class && this.pos.y == living.pos.y){
	            this.shoot();
	            break;
            }
        }
	}

	// Shoots a bullet based on a timer
	public void shoot() {
        if (timer-- <= 0) {
            Bullet bullet = new Bullet(p, PVector.add(pos, PVector.div(size, 2)), 15, damage, effect);
            timer = 30;
        }
	}

	@Override
	public void show() {
		p.fill(0, 255, 0);
		p.rect(pos.x, pos.y, size.x, size.y);
    }
}