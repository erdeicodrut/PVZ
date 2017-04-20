import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import java.io.File;
import java.util.ArrayList;

public class PoleVaultingZombie extends Zombie {
    private int animationFrame = 0;
    private int jumps = 1;
    ArrayList<PImage> pics = new ArrayList<>();

    public PoleVaultingZombie(PApplet p, PVector zombiePos) {
        super(p, zombiePos, Globals.zombieSize, 6, Globals.speed, 1);

        for (int i = 0; i <= 9; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Zombies/PoleVaultingZombie/PoleVaultingZombie_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }
    }

    public static PoleVaultingZombie spawn() {
        if (timerSpawn-- == 0)
        {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                            p.floor(p.random(6f)) * Globals.cellSize.y));

            timerSpawn = 150;

            return new PoleVaultingZombie(p, zombiePos);

        }
        return null;
    }

//    @Override
//    public void update() {
//        if (!CollisionManager.isCollidingWithClass(this, Plant.class))
//            move();
//        else if (jumps == 1) {
//            anim
//        }
//    }
    @Override
    public void show() {
        if (animationFrame >= pics.size()) animationFrame = 0;
        p.imageMode(PConstants.CENTER);
        p.image(pics.get(animationFrame++), pos.x, pos.y);
    }
}
