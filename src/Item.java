import processing.core.PApplet;
        import processing.core.PConstants;
        import processing.core.PVector;

public class Item extends Drawable {
    public Plant plant;
    public int value;

    public Item(PApplet p, int value, Plant plant) {
        super(p, new PVector(0, 0), Globals.itemSize);
        this.value = value;
        this.plant = plant;
    }

    @Override
    public void show() {
        p.rectMode(PConstants.CORNER);
        p.fill(255);
        p.rect(pos.x, pos.y, size.x, size.y);

        p.textAlign(PConstants.CENTER, PConstants.CENTER);
        p.fill(0);
        p.text(value, pos.x  + size.x / 2,  pos.y + size.y / 2 + size.y * 2/3);
    }
}