import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.MouseEvent;

import javax.print.attribute.standard.PrinterMoreInfoManufacturer;
import java.io.File;

public class Shovel extends DraggedItem {
    PVector origin;
    PImage pic;

    public Shovel (PApplet p) {
        super(p, new PVector(p.width - 100, p.height - 100), 0, null, null);
        pic = p.loadImage(new File("resources/Items/Menu/Shovel.png").getAbsolutePath());
        origin = new PVector(p.width - 100, p.height - 100);
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

        setPosition(origin);
    }

    public void show() {
        p.image(pic, pos.x, pos.y);
    }
}
