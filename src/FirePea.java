import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.ArrayList;

public class FirePea extends Plant {
    private int animationFrame = 0;
    ArrayList<PImage> pics = new ArrayList<>();

    public FirePea(PApplet p, PVector pos) {
        super(p, 3, 2, Effect.FIRE );
        for (int i = 0; i <= 12; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Plants/FirePea/FirePea_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }
    }

    public FirePea(PApplet p) {
        super(p, 3, 2, Effect.FIRE);
        for (int i = 0; i <= 12; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Plants/FirePea/FirePea_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }
    }

    public FirePea(FirePea plant) {
        this(plant.p, plant.pos);
    }

    @Override
    public void show() {
        if (animationFrame >= pics.size()) animationFrame = 0;
        p.imageMode(PConstants.CENTER);
        p.image(pics.get(animationFrame++), pos.x + size.x, pos.y + size.y);
    }
}