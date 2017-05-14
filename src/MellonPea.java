import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.ArrayList;

public class MellonPea extends Plant {
    int a = 0;
    private int animationFrame = 0;

    public MellonPea(PApplet p, PVector pos) {
        super(p, 3, 2, Effect.MELLON );
        firstFrame = Globals.picsMellonPea.get(0);
    }

    public MellonPea(PApplet p) {
        super(p, 3, 2, Effect.MELLON );
        firstFrame = Globals.picsMellonPea.get(0);

    }

    public MellonPea(MellonPea plant) {
        this(plant.p, plant.pos);
    }

    @Override
    public void show() {
        a++;

        if (animationFrame >= Globals.picsMellonPea.size()) animationFrame = 0;
        p.imageMode(PConstants.CENTER);
        p.image(Globals.picsMellonPea.get(animationFrame), pos.x + size.x, pos.y + size.y);
        if (a % 2 == 0) animationFrame++;
    }
}