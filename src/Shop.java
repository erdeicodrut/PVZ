import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import java.util.ArrayList;

class Shop extends Drawable {
	private int ballance;
	private ArrayList<Item> loadout = new ArrayList<>();

	public Shop(PApplet p, PVector pos, PVector size) {
		super(p, pos, size);
		ballance = 0;
		//loadout = new Item[6];
	}

	public void addBallance(int loan) {
		ballance += loan;
	}

	public boolean extract(int sum) {
		if (ballance > 0) {
			ballance -= sum;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void show()
	{
		p.rectMode(PConstants.CORNER);
		p.fill(0 , 0 , 255);
		p.rect(pos.x, pos.y, size.x , size.y);
	}
}