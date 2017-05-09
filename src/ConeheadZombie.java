import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import sun.font.CoreMetrics;

import java.io.File;
import java.util.ArrayList;

public class ConeheadZombie extends Zombie {

    public int animationFrame = 0;
    public int animationFrameAtatck = 0;
    ArrayList<PImage> pics = new ArrayList<>();
    ArrayList<PImage> picsAttack = new ArrayList<>();

    public ConeheadZombie() { super(); }

    public ConeheadZombie(PApplet p, PVector zombiePos) {
        super(p, zombiePos, Globals.zombieSize, 27, Globals.speed, 1);

        for (int i = 0; i <= 20; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Zombies/ConeheadZombie/ConeheadZombie_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }

        for (int i = 0; i <= 10; i++) {
            PImage temp = new PImage();
            temp = p.loadImage(new File("resources/Zombies/ConeheadZombieAttack/ConeheadZombieAttack_" + (i++) + ".png").getAbsolutePath());
            picsAttack.add(temp);
        }
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
        p.imageMode(PConstants.CENTER);
        if (animationFrame >= pics.size()) animationFrame = 0;
        if (animationFrameAtatck >= picsAttack.size()) animationFrameAtatck = 0;

        if (CollisionManager.isCollidingWithClass(this, Plant.class)) {
            p.image(picsAttack.get(animationFrameAtatck++), pos.x, pos.y);
        } else {
            p.image(pics.get(animationFrame++), pos.x, pos.y);
        }
    }
}
