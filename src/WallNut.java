import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.ArrayList;

public class WallNut extends Plant {
    private int animationFrame = 0;
    ArrayList<PImage> pics = new ArrayList<>();
    ArrayList<PImage> pics1 = new ArrayList<>();
    ArrayList<PImage> pics2 = new ArrayList<>();

    public WallNut(PApplet p, PVector pos) {
        super(p, 20, 0, Effect.NONE );
        for (int i = 0; i <= 15; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Plants/WallNut/WallNut/WallNut_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }

        for (int i = 0; i <= 10; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Plants/WallNut/Wallnut_cracked1/Wallnut_cracked1_" + (i++) + ".png").getAbsolutePath());
            pics1.add(temp);
        }

        for (int i = 0; i <= 14; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Plants/WallNut/Wallnut_cracked2/Wallnut_cracked2_" + (i++) + ".png").getAbsolutePath());
            pics2.add(temp);
        }
    }

    public WallNut(PApplet p) {
        super(p, 20, 0, Effect.NONE );
        for (int i = 0; i <= 15; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Plants/WallNut/WallNut/WallNut_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }

        for (int i = 0; i <= 10; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Plants/WallNut/Wallnut_cracked1/Wallnut_cracked1_" + (i++) + ".png").getAbsolutePath());
            pics1.add(temp);
        }

        for (int i = 0; i <= 14; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Plants/WallNut/Wallnut_cracked2/Wallnut_cracked2_" + (i++) + ".png").getAbsolutePath());
            pics2.add(temp);
        }
    }

    public WallNut(WallNut plant) {
        this(plant.p, plant.pos);
    }

    @Override
    public void update() {
        return;
    }

    @Override
    public void show() {
        if (hp < 5) {
            if (animationFrame >= pics2.size()) animationFrame = 0;
            p.imageMode(PConstants.CENTER);
            p.image(pics2.get(animationFrame++), pos.x + size.x / 2, pos.y);
        } else if (hp < 10) {
            if (animationFrame >= pics1.size()) animationFrame = 0;
            p.imageMode(PConstants.CENTER);
            p.image(pics1.get(animationFrame++), pos.x + size.x / 2, pos.y);
        } else {
            if (animationFrame >= pics.size()) animationFrame = 0;
            p.imageMode(PConstants.CENTER);
            p.image(pics.get(animationFrame++), pos.x + size.x / 2, pos.y);
        }
    }
}