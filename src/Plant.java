import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

class Plant extends Living {
	protected Effect effect;
	protected float damage;
	public PImage firstFrame;

    public PImage card;


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
        for (int i = 0; i < pvz.zombies.size(); i++)
        {
	        Zombie living = pvz.zombies.get(i);
	        if (this.pos.y == living.pos.y){
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