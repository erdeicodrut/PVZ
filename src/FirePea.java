import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.ArrayList;

public class FirePea extends Plant {
    int a = 0;

    private int animationFrame = 0;

    public FirePea(PApplet p, PVector pos) {
        super(p, 3, 2, Effect.FIRE );
    }

    public FirePea(PApplet p) {
        super(p, 3, 2, Effect.FIRE);
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
}