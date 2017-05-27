import processing.core.PApplet;
import processing.core.PVector;

class Shop extends Drawable {
	private int ballance;
	private Item[] loadout;

	public Shop(PApplet p, PVector pos, PVector size) {
		super(p, pos, size);
		ballance = 0;
		loadout = new Item[6];
	}

	public boolean extract(int sum) {
		if (ballance > 0) {
			ballance -= sum;
			return true;
		} else {
			return false;
		}
	}

}