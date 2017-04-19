import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import sun.java2d.pipe.SpanShapeRenderer;

import java.io.File;
import java.util.ArrayList;

public class SimplePlant extends Plant{
    private int animationFrame = 0;
    ArrayList<PImage> pics = new ArrayList<>();

    public SimplePlant(PApplet p, PVector pos) {
        super(p, 5, 1, Effect.NONE );

        for (int i = 0; i <= 12; i++) {
            PImage temp;
            temp = p.loadImage(new File("resources/Plants/Peashooter/Peashooter_" + (i++) + ".png").getAbsolutePath());
            pics.add(temp);
        }
    }

    public SimplePlant(SimplePlant plant) {
        this(plant.p);
    }


    public SimplePlant(PApplet p) {
        super(p, 5, 1, Effect.NONE );

    }

    @Override
    public void show() {
        if (animationFrame >= pics.size()) animationFrame = 0;
        p.imageMode(PConstants.CENTER);
        p.image(pics.get(animationFrame++), pos.x, pos.y);
    }
}
