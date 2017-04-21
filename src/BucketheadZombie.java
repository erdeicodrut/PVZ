import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import java.io.File;
import java.util.ArrayList;

public class BucketheadZombie extends Zombie {
    private int animationFrame = 0;
    ArrayList<PImage> pics = new ArrayList<>();

    public BucketheadZombie(PApplet p, PVector zombiePos) {
        super(p, zombiePos, Globals.zombieSize, 8, Globals.speed, 1);

        for (int i = 0; i <= 14; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Zombies/BucketheadZombie/BucketheadZombie_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }
    }

    public static BucketheadZombie spawn() {
        if (timerSpawn-- == 0)
        {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                            p.floor(p.random(6f)) * Globals.cellSize.y));

            timerSpawn = Globals.spawnTime;

            return new BucketheadZombie(p, zombiePos);

        }
        return null;
    }

    @Override
    public void show() {
        if (animationFrame >= pics.size()) animationFrame = 0;
        p.imageMode(PConstants.CENTER);
        p.image(pics.get(animationFrame++), pos.x, pos.y);
    }
}
