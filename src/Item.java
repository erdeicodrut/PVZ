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

//	public void showDrag() {
//		// pos.add(Globals.getRelativeMousePos(p));
//
//		p.rectMode(PConstants.CORNER);
//		p.fill(255, 100);
//		p.rect(pos.x, pos.y, size.x, size.y);
//	}

	@Override
	public void mouseReleased(MouseEvent event) {
//    	pvz.draggedItems.clear();
	}

	@Override
    public void mousePressed(MouseEvent event)
    {
//	    System.out.println("fASZ");
	    draggedItem = new DraggedItem(p, pos, plant);
//	    InputManager.addObject(draggedItem);
	    InputManager.focusedObject = draggedItem;
	    InputManager.isDragged = true;
//    	pvz.draggedItems.add(new Item(p, value, plant));
    }

	@Override
	public void mouseDragged(MouseEvent event)
	{
		// InputManager.queue.remove(this);
		if (draggedItem != null)
			draggedItem.show();

	}
}