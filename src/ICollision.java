import processing.core.PVector;

public interface ICollision
{
	PVector getPosition();
	PVector getSize();

	default boolean isCollidingWith(ICollision other)
	{
		PVector thisPos = getPosition();
		PVector thisSize = getSize();
		PVector otherPos = other.getPosition();
		PVector otherSize = other.getSize();

		// Box collision
		return !(thisPos.x + thisSize.x <= otherPos.x ||
				thisPos.x >= otherPos.x + otherSize.x ||
				thisPos.y + thisSize.y <= otherPos.y ||
				thisPos.y >= otherPos.y + otherSize.y);
	}

	default void onCollisionEnterWith(ICollision other) {};
	default void onCollisionWith(ICollision other) {};
	default void onCollisionExitWith(ICollision other) {};

	default boolean isZombie() { return false; }

}
