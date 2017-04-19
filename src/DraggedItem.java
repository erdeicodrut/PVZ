import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import processing.event.MouseEvent;

public class DraggedItem extends GameObject {
    public int value;
    public Plant plant;
    public float red = p.random(255);

    public DraggedItem(PApplet p, PVector pos, int value, Plant plant) {
        super(p, pos, Globals.itemSize);
        this.value = value;
        this.plant = plant;
    }

    @Override
    public void show() {
        p.rectMode(PConstants.CORNER);
        p.fill(red, 0, 0, 128);
        p.rect(pos.x, pos.y, size.x, size.y);
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        pos.add(Globals.getRelativeMousePos(p));
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        IInput under = InputManager.getObjectAt(Globals.getMousePos(p));
        if (under == null)
            return;

        if (under.getClass() == Cell.class) {
            Cell cell = (Cell) under;
            if (!cell.isOccupied()) {
                Plant fasz = new SimplePlant((SimplePlant) plant);
                if (Shop.extract(value)) {
                    cell.plantHere(fasz);
                    pvz.plants.add(fasz);
                }
            }
        }
    }
}
