import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.util.ArrayList;

public class DraggedItem extends GameObject {
    public static DraggedItem draggedItem;

    public Item item;
    public int value;
    public Plant plant;
    public float red = p.random(255);

    public DraggedItem(PApplet p, PVector pos, int value, Plant plant, Item item) {
        super(p, pos, Globals.itemSize);
        this.item = item;
        this.value = value;
        this.plant = plant;
        DraggedItem.draggedItem = this;
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
        pvz.iShow = true;

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        IInput under = InputManager.getObjectAt(Globals.getMousePos(p));
        if (p.mouseX < Globals.fieldPos.x || p.mouseY < Globals.fieldPos.y ||
                p.mouseX > Globals.fieldPos.x + Globals.cellSize.x * Globals.fieldDim.x ||
                p.mouseY > Globals.fieldPos.y + Globals.cellSize.y * Globals.fieldDim.y ) {
            pvz.iShow = false;
            return;

        }

        if (under == null)
            return;

        if (under.getClass() == Cell.class) {
            Cell cell = (Cell) under;
            if (!cell.isOccupied()) {
                Plant fasz =  plant;
                if (Shop.extract(value)) {
                    cell.plantHere(fasz);
                }
            }
        }
        pvz.iShow = false;
    }
}
