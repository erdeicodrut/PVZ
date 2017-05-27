import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;

public class Scufundatorul extends Zombie{
	PImage img;
	
	public Scufundatorul(PApplet p, PVector pos) {
		super(p, pos, Globals.zombieSize, 130, Globals.speed * 2, 24f);
		img = Globals.imgSS;
	}
	
	public static Scufundatorul spawn() {
		if (timerSpawn-- == 0)
		{
			PVector zombiePos = PVector.add(Globals.fieldPos,
					new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
							p.floor(p.random(5f)) * Globals.cellSize.y));
			
			resetSpawn();
			
			return new Scufundatorul(p, zombiePos);
			
		}
		return null;
	}
	
	public void attack(Plant other) {
		if (timer-- < 0) {
			if (other.hp > 0) {
				other.hp -= damage;
			}
			timer = 30;
		}
	}
	
	public void show() {
		p.imageMode(PConstants.CORNER);
		p.image(img, pos.x, pos.y, img.width / 23, img.height / 23);
	}
	
}
