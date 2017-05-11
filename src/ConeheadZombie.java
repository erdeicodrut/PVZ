import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import sun.font.CoreMetrics;

import java.io.File;
import java.util.ArrayList;

public class ConeheadZombie extends Zombie {

    int a = 0;

    public int animationFrame = 0;
    public int animationFrameAtatck = 0;

    public ConeheadZombie() { super(); }

    public ConeheadZombie(PApplet p, PVector zombiePos) {
        super(p, zombiePos, Globals.zombieSize, 27, Globals.speed, 1);
    }

    public static ConeheadZombie spawn() {
        if (timerSpawn-- == 0)
        {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                            p.floor(p.random(5f)) * Globals.cellSize.y));

            timerSpawn = Globals.spawnTime;

            return new ConeheadZombie(p, zombiePos);

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
        a ++;
        p.imageMode(PConstants.CENTER);
        if (animationFrame >= Globals.picsConheadZombie.size()) animationFrame = 0;
        if (animationFrameAtatck >= Globals.picsConheadZombieAttack.size()) animationFrameAtatck = 0;

        if (CollisionManager.isCollidingWithClass(this, Plant.class)) {
            p.image(Globals.picsConheadZombieAttack.get(animationFrameAtatck), pos.x, pos.y);
            if (a % 2 == 0) animationFrameAtatck++;
        } else {
            p.image(Globals.picsConheadZombie.get(animationFrame), pos.x, pos.y);
            if (a % 2 == 0) animationFrame++;
        }
    }
}
