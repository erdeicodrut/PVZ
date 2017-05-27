import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class SimpleZombie extends Zombie {
    int a = 0;

    public int frame_walk_full = 0;
    public int frame_attack_full = 0;
	public int frame_attack_walk = 0;

    public SimpleZombie() { super(); };

    public SimpleZombie(PApplet p, PVector pos) {
        super(p, pos, Globals.zombieSize, 85, Globals.speed, 7);
    }


    public static SimpleZombie spawn() {
        if (timerSpawn-- == 0)
        {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                            p.floor(p.random(5f)) * Globals.cellSize.y));

            resetSpawn();

            return new SimpleZombie(p, zombiePos);

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

    @Override
    public void show() {
	
	    if (head && hp < 10 / 2) {
		    head = false;
		    pvz.dead.add(new Dies(p, Globals.head_zombie, pos));
	    }
    	
    	
    	a++;
        p.imageMode(PConstants.CENTER);
        if (frame_walk_full >= Globals.simple_zombie_walk_full.size()) frame_walk_full = 0;
        if (frame_attack_full >= Globals.simple_zombie_attack_full.size()) frame_attack_full = 0;
	    if (frame_attack_walk >= Globals.simple_zombie_headless.size()) frame_attack_walk = 0;
	    if (frame_attack_headless >= Globals.simple_zombie_headless_attack.size()) frame_attack_headless = 0;

        if (CollisionManager.isCollidingWithClass(this, Plant.class)) {
	        if (!head) {
		        p.image(Globals.simple_zombie_headless_attack.get(frame_attack_headless), pos.x, pos.y);
		        if (a % 2 == 0) frame_attack_headless++;
	        } else p.image(Globals.simple_zombie_attack_full.get(frame_attack_full++), pos.x, pos.y);
	        
            } else if (!head) {
            p.image(Globals.simple_zombie_headless.get(frame_attack_walk), pos.x, pos.y);
	        if (a % 2 == 0) frame_attack_walk++;
        } else {
            p.image(Globals.simple_zombie_walk_full.get(frame_walk_full++), pos.x, pos.y);
        }
    }
}
