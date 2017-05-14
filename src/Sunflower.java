import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.ArrayList;

public class Sunflower extends Plant {
    int a = 0;

    private int animationFrame = 0;
    private static int timer = 180;

    public Sunflower(PApplet p, PVector pos) {
        super(p, 3, 0, Effect.NONE );
        firstFrame = Globals.picSunflower.get(0);
    }

    public Sunflower(PApplet p) {
        super(p, 3, 0, Effect.NONE );
        firstFrame = Globals.picSunflower.get(0);
    }


    public Sunflower(Sunflower plant) {
        this(plant.p, plant.pos);
        firstFrame = Globals.picSunflower.get(0);
    }


    public void spawnSun() {
        if (timer-- < 0) {
            new Sun(p, new PVector(pos.x + p.random(-40,40), pos.y + 50 + + p.random(-40,40)));
            timer = 180;
        }
    }

    @Override
    public void update() {
        spawnSun();
    }

    @Override
    public void show() {
        a++;
        if (animationFrame >= Globals.picSunflower.size()) animationFrame = 0;
        p.imageMode(PConstants.CENTER);
        p.image(Globals.picSunflower.get(animationFrame), pos.x + size.x, pos.y + size.y);
        if (a % 2 == 0) animationFrame++;
    }
}