import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import java.io.File;
import java.util.ArrayList;

public class SimpleZombie extends Zombie {
    int a = 0;

    public int animationFrame = 0;
    public int animationFrameAtatck = 0;

    public SimpleZombie() { super(); };

    public SimpleZombie(PApplet p, PVector pos) {
        super(p, pos, Globals.zombieSize, 10, Globals.speed, 1);
    }


    public static SimpleZombie spawn() {
        if (timerSpawn-- == 0)
        {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                            p.floor(p.random(5f)) * Globals.cellSize.y));

            resetSpawn();

            return new SimpleZombie(p, zombiePos);

        }
        return null;
    }

    public void attack(Plant other) {
        if (timer-- < 0) {
            if (other.hp > 0) {
                other.hp -= damage;
            }
            timer = 30;
        }
    }

    @Override
    public void show() {
        p.imageMode(PConstants.CENTER);
        if (animationFrame >= Globals.picsSimpleZombie.size()) animationFrame = 0;
        if (animationFrameAtatck >= Globals.picsSimpleZombieAttack.size()) animationFrameAtatck = 0;

        if (CollisionManager.isCollidingWithClass(this, Plant.class)) {
            p.image(Globals.picsSimpleZombieAttack.get(animationFrameAtatck++), pos.x, pos.y);
        }  else if ( this.hp < 5 ) {
            p.image(Globals.deadZombieHalf.get(animationFrame), pos.x, pos.y);
            animationFrame++;
        } else {
            p.image(Globals.picsSimpleZombie.get(animationFrame++), pos.x, pos.y);
        }
    }
}
