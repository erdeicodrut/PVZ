import processing.core.PVector;
import processing.event.MouseEvent;

import java.util.ArrayList;

public abstract class InputManager
{
	public static ArrayList<IInput> queue = new ArrayList<>();
	public static IInput focusedObject;
	public static boolean isDragged = false;

	public static void addObject(IInput obj) { queue.add(obj); }

	public static IInput getObjectAt(PVector pos)
	{
		for (int i = queue.size()-1; i >= 0; i--) {
			if (queue.get(i) != focusedObject && queue.get(i).containsPoint(pos))
				return queue.get(i);
		}
		return null;
	}

	public static void mouseMoved(MouseEvent event)
	{
		PVector mousePos = Globals.getMousePos(event);

		IInput obj;
		if ((obj = getObjectAt(mousePos)) != null)
		{
			obj.mouseMoved(event);
		}
	}

	public static void mousePressed(MouseEvent event)
	{
		PVector mousePos = Globals.getMousePos(event);

		IInput obj;
		if ((obj = getObjectAt(mousePos)) != null)
		{
			focusedObject = obj;
			obj.mousePressed(event);
		}
	}

	public static void mouseClicked(MouseEvent event)
	{
		if (focusedObject == null)
			return;

		focusedObject.mouseClicked(event);
		focusedObject = null;
	}

	public static void mouseDragged(MouseEvent event)
	{
		if (focusedObject == null)
			return;

		isDragged = true;
		focusedObject.mouseDragged(event);
	}

	public static void mouseReleased(MouseEvent event)
	{
		if (focusedObject == null)
			return;

		focusedObject.mouseReleased(event);

		if (isDragged)
		{
			focusedObject = null;
			isDragged = false;
			// No mouseClicked() will occur
		}
	}

	public static void mouseWheel(MouseEvent event)
	{
		PVector mousePos = Globals.getMousePos(event);

		IInput obj;
		if ((obj = getObjectAt(mousePos)) != null)
		{
			obj.mouseWheel(event);
		}
	}

}