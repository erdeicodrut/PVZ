import processing.core.PVector;
import processing.event.MouseEvent;

public interface IInput extends Comparable<IInput> {
	boolean containsPoint(PVector point);
	float getZ();

	default void mouseMoved(MouseEvent event) {}
	default void mousePressed(MouseEvent event) {}
	default void mouseClicked(MouseEvent event) {}
	default void mouseDragged(MouseEvent event) {}
	default void mouseReleased(MouseEvent event) {}
	default void mouseWheel(MouseEvent event) {}

	@Override
	default int compareTo(IInput other) {
		return (int) (this.getZ() - other.getZ());
	}
}
