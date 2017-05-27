import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.ArrayList;

public class FirePea extends Plant {
    int a = 0;
    int timer = 60;

    private int animationFrame = 0;

    public FirePea(PApplet p, PVector pos) {
        super(p, 350, 33, Effect.FIRE );
        firstFrame = Globals.picFirePea.get(0);

    }

    public FirePea(PApplet p) {
        super(p, 350, 33, Effect.FIRE);
        firstFrame = Globals.picFirePea.get(0);
    }

    public FirePea(FirePea plant) {
        this(plant.p, plant.pos);
    }

    @Override
    public void show() {
        a++;
        if (animationFrame >= Globals.picFirePea.size()) animationFrame = 0;
        p.imageMode(PConstants.CENTER);
        p.image(Globals.picFirePea.get(animationFrame), pos.x + size.x, pos.y + size.y);
        if (a % 2 == 0) animationFrame++;
    }

    @Override
    public void shoot() {
        if (timer-- <= 0) {
            Bullet bullet = new Bullet(p, PVector.add(pos, PVector.div(size, 2)), 15, damage, effect);
            timer = 60;
        }
    }
}