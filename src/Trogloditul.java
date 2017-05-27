import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;

public class Trogloditul extends Zombie{
	PImage img;
	
	public Trogloditul(PApplet p, PVector pos) {
		super(p, pos, Globals.zombieSize, 95, Globals.speed, 9.6f);
		img = p.loadImage(new File("resources/Zombies/Troglo/trogloditul.png").getAbsolutePath());
		
	}
	
	public static Trogloditul spawn() {
		if (timerSpawn-- == 0)
		{
			PVector zombiePos = PVector.add(Globals.fieldPos,
					new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
							p.floor(p.random(5f)) * Globals.cellSize.y));
			
			resetSpawn();
			
			return new Trogloditul(p, zombiePos);
			
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
		p.image(img, pos.x, pos.y, img.width / 6, img.height / 6);
	}
	
}
