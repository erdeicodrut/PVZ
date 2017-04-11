import processing.core.PApplet;
import processing.core.PVector;

abstract class Living extends Drawable {
	public float hp;

//	public Living(PApplet p) {
//		this.p = p;
//	}

	public Living(PApplet p, PVector pos, PVector size, float hp) {
		super(p, pos, size);
		this.hp = hp;
	}

	public boolean isAlive()
	{
		return hp <= 0;
	}
}



