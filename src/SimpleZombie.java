import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import java.io.File;
import java.util.ArrayList;

public class SimpleZombie extends Zombie {
    private int animationFrame = 0;
    private int animationFrameAtatck = 0;

    ArrayList<PImage> pics = new ArrayList<>();
    ArrayList<PImage> picsAttack = new ArrayList<>();

    public SimpleZombie(PApplet p, PVector pos) {
        super(p, pos, Globals.zombieSize, 4, Globals.speed, 1);
// add walking animation
        for (int i = 0; i <= 21; i++) {
            PImage temp = new PImage();
            temp = p.loadImage(new File("resources/Zombies/Zombie/Zombie_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }

 // add attacking animation
        for (int i = 0; i <= 20; i++) {
            PImage temp = new PImage();
            temp = p.loadImage(new File("resources/Zombies/ZombieAttack/ZombieAttack_" + (i++) + ".png").getAbsolutePath());
            picsAttack.add(temp);
        }
    }


    public static SimpleZombie spawn() {
        if (timerSpawn-- == 0)
        {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                            p.floor(p.random(6f)) * Globals.cellSize.y));

            timerSpawn = Globals.spawnTime;

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
        if (animationFrameAtatck >= picsAttack.size()) animationFrameAtatck = 0;
        p.imageMode(PConstants.CENTER);
        p.image(picsAttack.get(animationFrameAtatck++), pos.x, pos.y);
        animationFrame = 50;
    }

    @Override
    public void show() {
        if (animationFrame > 40) return;
        if (animationFrame >= pics.size()) animationFrame = 0;
        p.imageMode(PConstants.CENTER);
        p.image(pics.get(animationFrame++), pos.x, pos.y);
    }
}
