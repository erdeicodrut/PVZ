import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.ArrayList;

public class WallNut extends Plant {
    int a = 0;

    private int animationFrame = 0;

    public WallNut(PApplet p, PVector pos) {
        super(p, 40, 0, Effect.NONE );
    }

    public WallNut(PApplet p) {
        super(p, 500, 0, Effect.NONE );
        firstFrame = Globals.picWall.get(0);
    }

    public WallNut(WallNut plant) {
        this(plant.p, plant.pos);
        firstFrame = Globals.picWall.get(0);
    }

    @Override
    public void update() {
        return;
    }

    @Override
    public void show() {
        a++;

        if (hp < 5) {
            if (animationFrame >= Globals.picWall2.size()) animationFrame = 0;
            p.imageMode(PConstants.CENTER);
            p.image(Globals.picWall2.get(animationFrame), pos.x + size.x, pos.y + size.y);
        } else if (hp < 10) {
            if (animationFrame >= Globals.picWall1.size()) animationFrame = 0;
            p.imageMode(PConstants.CENTER);
            p.image(Globals.picWall1.get(animationFrame), pos.x + size.x, pos.y + size.y);
        } else {
            if (animationFrame >= Globals.picWall.size()) animationFrame = 0;
            p.imageMode(PConstants.CENTER);
            p.image(Globals.picWall.get(animationFrame), pos.x + size.x, pos.y + size.y);
        }

        if (a % 2 == 0) animationFrame++;
    }
}