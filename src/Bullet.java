import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;

class Bullet extends Living
{
	private float speed;
	private float damage;
	private Effect effect;
	PImage pic;

	public Bullet(PApplet p, PVector pos, float speed, float damage, Effect effect) {
		super(p, pos, new PVector(20, 20), 1);
		pvz.bullets.add(this);
		CollisionManager.addObject(this);

		this.size = new PVector(20, 20);
		this.effect = effect;
		this.speed = speed;
		this.damage = damage;

		if (Effect.ICE == effect) {
            pic = p.loadImage(new File("resources/Bullets/PB_ICE/PB1_0.png").getAbsolutePath());
        } else if (Effect.MELLON == effect){
			pic = p.loadImage(new File("resources/Bullets/PB_Normal/Mellon.png").getAbsolutePath());
		} else {
			pic = p.loadImage(new File("resources/Bullets/PB_Normal/PB0_R.png").getAbsolutePath());
		}
	}

	public void update() {
		move();
	}

	public void move() {
		pos.x += speed;
	}
	
	



	// This method is called when it collides with a zombie
	public void hit(Zombie other) {
		hp = 0;
		other.receiveDamage(damage);
		Effects.applyEffect(effect, other);
	}

	@Override
	public void show()
	{
        p.image(pic, pos.x, pos.y);
	}

	@Override
	public void onCollisionEnterWith(ICollision other) {
        if (other.isZombie()) {
            Zombie zombie = (Zombie) other;
            this.hit(zombie);
            hp = 0;
        }
    }
}