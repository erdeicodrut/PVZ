import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import processing.event.MouseEvent;

/**
 * Created by roscale on 4/13/17.
 */
public class DraggedItem extends GameObject
{
	public Plant plant;
	public float red = p.random(255);

	public DraggedItem(PApplet p, PVector pos, Plant plant) {
		super(p, pos, Globals.itemSize);
		this.plant = plant;
	}

	@Override
	public void show()
	{
		p.rectMode(PConstants.CORNER);
		p.fill(red, 0, 0, 128);
		p.rect(pos.x, pos.y, size.x, size.y);
	}

	@Override
	public void mouseDragged(MouseEvent event)
	{
		pos.add(Globals.getRelativeMousePos(p));
	}

	@Override
	public void mouseReleased(MouseEvent event)
	{
		IInput under = InputManager.getObjectAt(Globals.getMousePos(p));
		Cell cell;
		if ((cell = (Cell) under) != null)
		{
			cell.plantHere(new Plant(plant));
		}
	}
}
