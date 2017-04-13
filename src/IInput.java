import processing.core.PVector;
import processing.event.MouseEvent;

public interface IInput {
	boolean containsPoint(PVector point);

	default void mouseMoved(MouseEvent event) {}
	default void mousePressed(MouseEvent event) {}
	default void mouseClicked(MouseEvent event) {}
	default void mouseDragged(MouseEvent event) {}
	default void mouseReleased(MouseEvent event) {}
	default void mouseWheel(MouseEvent event) {}
}
