import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import java.io.File;
import java.util.ArrayList;

public class FlagZombie extends Zombie {

    int a = 0;

    public int animationFrame = 0;
    public int animationFrameAtatck = 0;


    public FlagZombie() { super(); }

    public FlagZombie(PApplet p, PVector zombiePos) {
        super(p, zombiePos, Globals.zombieSize, 11, Globals.speed, 1);
    }

    public static FlagZombie spawn() {
        if (timerSpawn-- == 0)
        {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                            p.floor(p.random(5f)) * Globals.cellSize.y));

            resetSpawn();

            return new FlagZombie(p, zombiePos);

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
        a++;

        p.imageMode(PConstants.CENTER);
        if (animationFrame >= Globals.picsFlagZombie.size()) animationFrame = 0;
        if (animationFrameAtatck >= Globals.picsFlagZombieAttack.size()) animationFrameAtatck = 0;

        if (CollisionManager.isCollidingWithClass(this, Plant.class)) {
            p.image(Globals.picsFlagZombieAttack.get(animationFrameAtatck), pos.x, pos.y);
            if (a % 3 == 0) animationFrameAtatck++;
        }  else if ( this.hp < 6 ) {
            p.image(Globals.deadZombieHalf.get(animationFrame), pos.x, pos.y);
            if (a % 2 == 0) animationFrame++;
        } else {
            p.image(Globals.picsFlagZombie.get(animationFrame), pos.x, pos.y);
            if (a % 3 == 0) animationFrame++;
        }
    }
}
