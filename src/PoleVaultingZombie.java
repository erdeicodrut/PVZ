import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import javax.xml.bind.annotation.XmlElementDecl;
import java.io.File;
import java.util.ArrayList;

public class PoleVaultingZombie extends Zombie {
    public int animationFrameJump = 0;
    public int animationFrame = 0;
    public int animationFrameWalk = 0;
    public int animationFrameAtatck = 0;
    private int jumps = 1;

    int a = 0;


    boolean hasJumped, doShow = true;
    private Plant toAvoid;

    public PoleVaultingZombie() { super(); }

    public PoleVaultingZombie(PApplet p, PVector zombiePos) {
        super(p, zombiePos, Globals.zombieSize, 17, Globals.speed, 1);

        hasJumped = false;
    }

    public static PoleVaultingZombie spawn() {
        if (timerSpawn-- == 0) {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                                p.floor(p.random(5f)) * Globals.cellSize.y));


            resetSpawn();

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
        if (animationFrameJump >= Globals.VaultPicsJump.size()) animationFrameJump = Globals.VaultPicsJump.size() - 1;
        p.imageMode(PConstants.CENTER);
        p.image(Globals.VaultPicsJump.get(animationFrameJump), pos.x, pos.y);
        if (a % 4 == 0) animationFrameJump++;

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
                if (hasJumped == false) toAvoid = plant;
            } else attack(plant);
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
            a++;

            p.imageMode(PConstants.CENTER);
            if (animationFrame >= Globals.VaultPics.size()) animationFrame = 0;
            if (animationFrameAtatck >= Globals.VaultPicsAttack.size()) animationFrameAtatck = 0;
            if (animationFrameWalk >= Globals.VaultPicsWalk.size()) animationFrameWalk = 0;

            if (CollisionManager.isCollidingWithClass(this, Plant.class)) {
                p.image(Globals.VaultPicsAttack.get(animationFrameAtatck++), pos.x, pos.y);
            } else {
                if (hasJumped == true) {
                    p.image(Globals.VaultPicsWalk.get(animationFrameWalk), pos.x, pos.y);
                    if (a % 3 == 0) animationFrameWalk++;
                }  else if ( this.hp < 8 ) {
                    p.image(Globals.deadPaulHalf.get(animationFrame), pos.x, pos.y);
                    if (a % 4 == 0) animationFrame++;
                } else {
                    p.image(Globals.VaultPics.get(animationFrame), pos.x, pos.y);
                    if (a % 3 == 0) animationFrame++;
                }
            }
        }
    }
}
