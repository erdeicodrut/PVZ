import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
public class DomnulManager extends Zombie {
	
	PImage img;
	
	public DomnulManager(PApplet p, PVector pos) {
		super(p, pos, Globals.zombieSize, 400, Globals.speed, 37.6f);
		img = p.loadImage(new File("resources/Zombies/DomnuManager/Domnulmanager.png").getAbsolutePath());
		
	}
	
	public static DomnulManager spawn() {
		if (timerSpawn-- == 0)
		{
			PVector zombiePos = PVector.add(Globals.fieldPos,
					new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
							p.floor(p.random(5f)) * Globals.cellSize.y));
			
			resetSpawn();
			
			return new DomnulManager(p, zombiePos);
			
		}
		return null;
	}
	
	int counter = 0;
	float exHp = hp;
	
	
	
	
	public void attack(Plant other) {
		
		if (timer-- < 0) {
			if (other.hp > 0) {
				other.hp -= damage;
			}
			timer = 30;
		}
	}
	
	public void show() {
		
		if (exHp != hp) {
			counter++;
		}
		exHp = hp;
		
		if (counter == 5) {
			for (float i = pos.x; i > 0; i++) {
				for (Plant p : pvz.plants) {
					if (p.pos.x == i) {
						if (p.getClass() == SimplePlant.class || p.getClass() == FirePea.class) {
							p.hp -= p.damage * 3;
						} else {
							p.hp -= p.damage;
						}
						//TODO implement a Dies thing to show the damage given
						counter = 0;
						break;
					}
				}
				if (counter == 0) {
					break;
				}
			}
			counter = 0;
		}
		
		
		p.imageMode(PConstants.CORNER);
		p.image(img, pos.x, pos.y, img.width / 6, img.height / 6);
	}
	
}
