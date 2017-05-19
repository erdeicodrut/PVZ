import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public class Dies {
    ArrayList<PImage> ay;
    PApplet p;
    PVector pos;
    int a = 0;
    int animationFrame = 0;
    public Dies(PApplet p, ArrayList<PImage> ay, PVector pos) {
        this.p = p;
        this.pos = pos;
        this.ay = ay;
    }

    public boolean show() {
        if (++a % 4 == 0) animationFrame++;
        if (animationFrame < ay.size()) {
            p.image(ay.get(animationFrame), pos.x, pos.y);
            return true;
        } else {
            return false;
        }
    }
}
