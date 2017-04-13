import processing.core.PApplet;
        import processing.core.PConstants;
        import processing.core.PVector;
import processing.event.MouseEvent;

import java.util.ArrayList;

public class Item extends GameObject
{
    public Plant plant;
    public int value;

	private DraggedItem draggedItem;

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

	@Override
	public void mouseReleased(MouseEvent event) {
	}

	@Override
    public void mousePressed(MouseEvent event)
    {
	    draggedItem = new DraggedItem(p, pos, plant);
	    InputManager.focusedObject = draggedItem;
	    InputManager.isDragged = true;
    }

	@Override
	public void mouseDragged(MouseEvent event)
	{
		if (draggedItem != null)
			draggedItem.show();

	}
}