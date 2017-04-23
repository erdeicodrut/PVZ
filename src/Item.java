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
        p.imageMode(PConstants.CORNER);
        p.image(plant.card, pos.x, pos.y);


        p.textAlign(PConstants.CENTER, PConstants.CENTER);
        p.fill( 0);
        p.text(value, pos.x  + size.x * 2,  pos.y + size.y + 5);
    }

	@Override
    public void mousePressed(MouseEvent event)
    {
	    draggedItem = new DraggedItem(p, pos, value, plant);
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