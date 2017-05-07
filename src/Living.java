import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

abstract class Living extends GameObject
{
	public float hp;
	public int animationFrame;

	public Living() {super();}

	public Living(PApplet p, PVector pos, PVector size, float hp) {
		super(p, pos, size);
		this.hp = hp;
	}


	public boolean isAlive() {
		return !(hp <= 0);
	}
}



