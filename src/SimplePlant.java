import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.ArrayList;

public class SimplePlant extends Plant{
    int a = 0;
    private int animationFrame = 0;


    public SimplePlant(PApplet p, PVector pos) {
        super(p, 3, 1, Effect.NONE );
        firstFrame = Globals.picPea.get(0);
    }

    public SimplePlant(SimplePlant plant) {
        this(plant.p, plant.pos);
        firstFrame = Globals.picPea.get(0);
    }

    public SimplePlant(PApplet p) {
        super(p, 3, 1, Effect.NONE );
        firstFrame = Globals.picPea.get(0);
    }

    @Override
    public void show() {
        a++;

        if (animationFrame >= Globals.picPea.size()) animationFrame = 0;
        p.imageMode(PConstants.CENTER);
        p.image(Globals.picPea.get(animationFrame), pos.x + size.x, pos.y + size.y);
        if (a % 2 == 0) animationFrame++;

    }
}
