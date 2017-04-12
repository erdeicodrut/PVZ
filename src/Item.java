import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Item extends Drawable {
    public Plant plant;
    public int value;

    public Item(PApplet p, int value, Plant plant) {
        super(p, new PVector(10, 25), new PVector(20, 20));
        this.value = value;
        this.plant = plant;
    }

    @Override
    public void show() {
        p.rectMode(PConstants.CORNER);
        p.fill(255);
        p.rect(pos.x, pos.y, size.x, size.y);
    }
}