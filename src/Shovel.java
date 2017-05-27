import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.io.File;

public class Shovel extends DraggedItem {
    PImage pic;
    public static PVector origin = new PVector(p.width - 100, p.height - 100);

    public Shovel (PApplet p) {
        super(p, new PVector(p.width - 100, p.height - 100), 0, null, null);
        pic = p.loadImage(new File("resources/Items/Menu/Shovel.png").getAbsolutePath());
        InputManager.addObject(this);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        IInput under = InputManager.getObjectAt(Globals.getMousePos(p));
        if (under == null)
            return;

        if (under.getClass() == Cell.class) {
            Cell cell = (Cell) under;
            if (cell.isOccupied()) {
                cell.clean();
            }
        }


    }

    @Override
    public void mouseDragged(MouseEvent event) {
        super.mouseDragged(event);
        pvz.iShow = false;
    }

    public void show() {
        p.imageMode(PConstants.CORNER);
        p.image(pic, pos.x, pos.y);
    }
}
