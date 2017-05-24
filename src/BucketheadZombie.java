import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import java.io.File;
import java.util.ArrayList;

public class BucketheadZombie extends Zombie {
    int a = 0;

    public int animationFrame = 0;
    public int animationFrameAtatck = 0;

    public BucketheadZombie() { super(); }

    public BucketheadZombie(PApplet p, PVector zombiePos) {
        super(p, zombiePos, Globals.zombieSize, 55, Globals.speed, 1);
    }

    public static BucketheadZombie spawn() {
        if (timerSpawn-- == 0)
        {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                            p.floor(p.random(5f)) * Globals.cellSize.y));

            resetSpawn();

            return new BucketheadZombie(p, zombiePos);

        }
        return null;
    }

    public void attack(Plant other) {
        a++;
        if (timer-- < 0) {
            if (other.hp > 0) {
                other.hp -= damage;
            }
            timer = 30;
        }
        if (animationFrameAtatck >= Globals.picsBucketHeadAttack.size()) animationFrameAtatck = 0;
        p.imageMode(PConstants.CENTER);
        p.image(Globals.picsBucketHeadAttack.get(animationFrameAtatck), pos.x, pos.y);
        if (a % 5 == 0) animationFrameAtatck++;
    }

    @Override
    public void show() {
        a++;
        p.imageMode(PConstants.CENTER);
        if (animationFrame >= Globals.picsBucketHead.size()) animationFrame = 0;
        if (animationFrameAtatck >= Globals.picsBucketHeadAttack.size()) animationFrameAtatck = 0;

        if (CollisionManager.isCollidingWithClass(this, Plant.class)) {
            p.image(Globals.picsBucketHeadAttack.get(animationFrameAtatck), pos.x, pos.y);
            if (a % 5 == 0) animationFrameAtatck++;
        }  else if ( this.hp < 27 ) {
            p.image(Globals.deadZombieHalf.get(animationFrame), pos.x, pos.y);
            if (a % 2 == 0) animationFrame++;
        } else {
            p.image(Globals.picsBucketHead.get(animationFrame), pos.x, pos.y);
            if (a % 5 == 0) animationFrame++;
        }
    }
}
