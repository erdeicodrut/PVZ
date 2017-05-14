import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.ArrayList;

class Shop extends GameObject
{
	private static int ballance;
	PImage sunPic;

	private ArrayList<Item> loadout = new ArrayList<>();

	public Shop(PApplet p, PVector pos, PVector size) {
		super(p, pos, size);
		sunPic = p.loadImage(new File("resources/Sun/Sun/Sun_1.png").getAbsolutePath());
		ballance = 150;
	}

	public static void addBallance(int loan) {
		ballance += loan;
	}

	public static boolean extract(int sum) {
		if (ballance - sum >= 0) {
			ballance -= sum;
			return true;
		} else {
			return false;
		}
	}

	public void addItem(Item item) {
		pvz.itemHabarnam.add(item);
		InputManager.addObject(item);

	    if (loadout.size() >= 6)
	        return;

	    loadout.add(item);
	    for (int i = 0; i < loadout.size(); i++) {
            item.setPosition(new PVector(pos.x, pos.y + i*Globals.itemSize.y + i * Globals.itemSize.y / 3 + i * Globals.itemSize.y / 2));
        }

	}

	@Override
	public void show()
	{
		p.rectMode(PConstants.CORNER);
		p.fill(242 , 50 , 100);
//		p.rect(pos.x, pos.y, size.x , size.y);

		for (Item item : loadout)
		    item.show();

		p.image(sunPic,130,20);
		p.fill(255);
		p.rect(120, 80, 100, 27);
		p.fill(0);
		p.text(ballance, 170, 93);
	}
}