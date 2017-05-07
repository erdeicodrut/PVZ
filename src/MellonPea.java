import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.ArrayList;

public class MellonPea extends Plant {
    private int animationFrame = 0;
    ArrayList<PImage> pics = new ArrayList<>();

    public MellonPea(PApplet p, PVector pos) {
        super(p, 5, 1, Effect.MELLON );
        for (int i = 0; i <= 14; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Plants/Threepeater/Threepeater_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }
    }

    public MellonPea(PApplet p) {
        super(p, 5, 1, Effect.MELLON );
        for (int i = 0; i <= 14; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Plants/Threepeater/Threepeater_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }
    }

    public MellonPea(MellonPea plant) {
        this(plant.p, plant.pos);
    }

    @Override
    public void show() {
        if (animationFrame >= pics.size()) animationFrame = 0;
        p.imageMode(PConstants.CENTER);
        p.image(pics.get(animationFrame++), pos.x + size.x, pos.y + size.y);
    }
}