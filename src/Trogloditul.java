import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

/**
 * Created by coddy on 5/27/17.
 */
public class Trogloditul extends Zombie{
	public Trogloditul() { super(); };
	
	public Trogloditul(PApplet p, PVector pos) {
		super(p, pos, Globals.zombieSize, 95, Globals.speed, 9.6f);
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
		p.imageMode(PConstants.CENTER);
		p.image(Globals.bucket_head_attack_full.get(0), pos.x, pos.y);
	}
	
}
