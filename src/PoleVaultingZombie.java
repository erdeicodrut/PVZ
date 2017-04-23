import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import java.io.File;
import java.util.ArrayList;

public class PoleVaultingZombie extends Zombie {
    public int animationFrameJump = 0;
    public int animationFrame = 0;
    public int animationFrameAtatck = 0;
    private int jumps = 1;

    int a = 0;

    ArrayList <PImage> pics = new ArrayList <>();
    ArrayList <PImage> picsAttack = new ArrayList <>();
    ArrayList <PImage> picsJump = new ArrayList <>();
    ArrayList <PImage> picsWalk = new ArrayList <>();

    boolean hasJumped, doShow = true;
    private Plant toAvoid;

    public PoleVaultingZombie(PApplet p, PVector zombiePos) {
        super(p, zombiePos, Globals.zombieSize, 6, Globals.speed, 1);

        hasJumped = false;

        for (int i = 0; i <= 9; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Zombies/PoleVaultingZombie/PoleVaultingZombie_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }

        for (int i = 0; i <= 13; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Zombies/PoleVaultingZombieAttack/PoleVaultingZombieAttack_" + (i++) + ".png").getAbsolutePath());
            picsAttack.add(temp);
        }

        for (int i = 0; i <= 9; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Zombies/PoleVaultingZombieJump/PoleVaultingZombieJump_" + (i++) + ".png").getAbsolutePath());
            picsJump.add(temp);
        }

        for (int i = 0; i <= 24; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Zombies/PoleVaultingZombieWalk/PoleVaultingZombieWalk_" + (i++) + ".png").getAbsolutePath());
            picsWalk.add(temp);
        }




    }

    public static PoleVaultingZombie spawn() {
        if (timerSpawn-- == 0) {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                                p.floor(p.random(6f)) * Globals.cellSize.y));

            timerSpawn = Globals.spawnTime;

            return new PoleVaultingZombie(p, zombiePos);

        }
        return null;
    }

    public void update() {
        if (!CollisionManager.isCollidingWithClass(this, Plant.class)) {
            move();
        } else {
            if (!hasJumped)
            {
                move();
                jump();
            } //TODO well the thing is that shit happens... it worked before and we need to make it work again.
                // we had the zombie jumping over the first shit but now he's retarded. we might throw him into an orphanage
        }
    }

    private void jump() {
        doShow = false;

        a++;
        if (animationFrameJump >= picsJump.size()) animationFrameJump = picsJump.size() - 1;
        p.imageMode(PConstants.CENTER);
        p.image(picsJump.get(animationFrameJump), pos.x, pos.y);
        if (a % 2 == 0) animationFrameJump++;

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
    public void onCollisionWith(ICollision other) {
        if (Plant.class.isAssignableFrom(other.getClass())) {
            Plant plant = (Plant) other;

            if (toAvoid == null && plant != toAvoid) {
                System.out.println("Jump");
                if (hasJumped == false) toAvoid = plant;
            }
            else
                attack(plant);

        }
    }

    @Override
    public void onCollisionExitWith(ICollision other) {
        if (Plant.class.isAssignableFrom(other.getClass())) {
            Plant plantToAvoid = (Plant) other;

            if (plantToAvoid == toAvoid)
                hasJumped = true;

            doShow = true;
        }
    }

    @Override
    public void show() {
        if (doShow) {
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
}
