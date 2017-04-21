import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import java.io.File;
import java.util.ArrayList;

public class ConeheadZombie extends Zombie {
    private int animationFrame = 0;
    ArrayList<PImage> pics = new ArrayList<>();

    public ConeheadZombie(PApplet p, PVector zombiePos) {
        super(p, zombiePos, Globals.zombieSize, 6, Globals.speed, 1);

        for (int i = 0; i <= 20; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Zombies/ConeheadZombie/ConeheadZombie_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }
    }

    public static ConeheadZombie spawn() {
        if (timerSpawn-- == 0)
        {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                            p.floor(p.random(6f)) * Globals.cellSize.y));

            timerSpawn = Globals.spawnTime;

            return new ConeheadZombie(p, zombiePos);

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
