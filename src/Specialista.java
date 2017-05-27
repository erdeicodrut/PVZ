import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;


public class Specialista extends Zombie{
	
	PImage img;
	
	public Specialista(PApplet p, PVector pos) {
		super(p, pos, Globals.zombieSize, 290, Globals.speed, 45);
		img = p.loadImage(new File("resources/Zombies/Specialista/Specialista.png").getAbsolutePath());
		
	}
	
	public static Specialista spawn() {
		if (timerSpawn-- == 0)
		{
			PVector zombiePos = PVector.add(Globals.fieldPos,
					new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
							p.floor(p.random(5f)) * Globals.cellSize.y));
			
			resetSpawn();
			
			return new Specialista(p, zombiePos);
			
		}
		return null;
	}
	
	
	public void receiveDamage(float damage) {
		if (hp > 0)
		{
			hp -= damage;
		}
		
		if (clonable) {
			hp = 0;
			PVector zombiePos;
			
			for (;;) {
				zombiePos = PVector.add(Globals.fieldPos,
						new PVector( p.floor(p.random(15f) * Globals.cellSize.x),
								p.floor(p.random(5f)) * Globals.cellSize.y));
				
				
				if (PApplet.dist(zombiePos.x, zombiePos.y, pos.x, pos.y) < Globals.cellSize.y * 3) {
					break;
				}
			}
			
			Specialista2 temp = new Specialista2(p, zombiePos);
			
			pvz.zombies.add(temp);
			CollisionManager.addObject(temp);
		}
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
