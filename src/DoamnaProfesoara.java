import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;


public class DoamnaProfesoara extends Zombie{
	
	PImage img;
	int spawnTime;
	
	public DoamnaProfesoara(PApplet p, PVector pos) {
		super(p, pos, Globals.zombieSize, 290, Globals.speed, 45);
		spawnTime = pvz.timer.get_time_in_seconds();
		img = Globals.imgDP;
	}
	
	public static DoamnaProfesoara spawn() {
		if (timerSpawn-- == 0)
		{
			PVector zombiePos = PVector.add(Globals.fieldPos,
					new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
							p.floor(p.random(5f)) * Globals.cellSize.y));
			
			resetSpawn();
			
			return new DoamnaProfesoara(p, zombiePos);
			
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
		
		for (Plant a : pvz.plants) {
			if (a.pos.y == pos.y) {
				a.timer += pvz.timer.get_second() * 2;
			}
		}
		
		p.imageMode(PConstants.CORNER);
		p.image(img, pos.x, pos.y, img.width / 6, img.height / 6);
	}
}