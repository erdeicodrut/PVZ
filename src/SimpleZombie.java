import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import java.io.File;
import java.util.ArrayList;

public class SimpleZombie extends Zombie {
    private int animationFrame = 0;
    ArrayList<PImage> pics = new ArrayList<>();

    public SimpleZombie(PApplet p, PVector pos) {
        super(p, pos, Globals.zombieSize, 4, Globals.speed, 1);

        for (int i = 0; i <= 21; i++) {
            PImage temp = new PImage();
            temp = p.loadImage(new File("resources/Zombies/Zombie/Zombie_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }
    }


    public static SimpleZombie spawn() {
        if (timerSpawn-- == 0)
        {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                            p.floor(p.random(6f)) * Globals.cellSize.y));

            timerSpawn = 150;

            return new SimpleZombie(p, zombiePos);

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
