import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.ArrayList;

public class Sunflower extends Plant {
    private int animationFrame = 0;
    private static int timer = 180;
    ArrayList<PImage> pics = new ArrayList<>();

    public Sunflower(PApplet p, PVector pos) {
        super(p, 3, 0, Effect.NONE );
        for (int i = 0; i <= 17; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Plants/Sunflower/SunFlower_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }
    }

    public Sunflower(PApplet p) {
        super(p, 3, 0, Effect.NONE );
        for (int i = 0; i <= 17; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Plants/Sunflower/SunFlower_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }
    }


    public Sunflower(Sunflower plant) {
        this(plant.p, plant.pos);
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
        if (animationFrame >= pics.size()) animationFrame = 0;
        p.imageMode(PConstants.CENTER);
        p.image(pics.get(animationFrame++), pos.x + size.x, pos.y + size.y);
    }
}