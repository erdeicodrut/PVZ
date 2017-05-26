import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class BucketheadZombie extends Zombie {
    int a = 0;

    private int frame_walk_full = 0;
    private int frame_attack_full = 0;
    private int frame_attack_walk = 0;
    
    
	public BucketheadZombie() { super(); }

    public BucketheadZombie(PApplet p, PVector zombiePos) {
        super(p, zombiePos, Globals.zombieSize, 55, Globals.speed, 1);
    }

    public static BucketheadZombie spawn() {
        if (timerSpawn-- == 0)
        {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                            p.floor(p.random(5f)) * Globals.cellSize.y));

            resetSpawn();

            return new BucketheadZombie(p, zombiePos);

        }
        return null;
    }

    public void attack(Plant other) {
        a++;
        if (timer-- < 0) {
            if (other.hp > 0) {
                other.hp -= damage;
            }
            timer = 30;
        }
        if (frame_attack_full >= Globals.bucket_head_attack_full.size()) frame_attack_full = 0;
        
//        p.imageMode(PConstants.CENTER);
//        p.image(Globals.bucket_head_attack_full.get(frame_attack_full), pos.x, pos.y);
        
        if (a % 5 == 0) frame_attack_full++;
    }

    @Override
    public void show() {
	
	    if (head && hp < 55 / 2) {
		    head = false;
		    pvz.dead.add(new Dies(p, Globals.head_zombie, pos));
	    }
	
	
	    a++;
        p.imageMode(PConstants.CENTER);
        if (frame_walk_full >= Globals.bucket_head_walk_full.size()) frame_walk_full = 0;
        if (frame_attack_full >= Globals.bucket_head_attack_full.size()) frame_attack_full = 0;
        if (frame_attack_walk >= Globals.simple_zombie_headless.size()) frame_attack_walk = 0;
	    if (frame_attack_headless >= Globals.simple_zombie_headless_attack.size()) frame_attack_headless = 0;

        if (CollisionManager.isCollidingWithClass(this, Plant.class)) {
	        if (!head) {
		        p.image(Globals.simple_zombie_headless_attack.get(frame_attack_headless), pos.x, pos.y);
		        if (a % 2 == 0) frame_attack_headless++;
	        } else {
	        	p.image(Globals.bucket_head_attack_full.get(frame_attack_full), pos.x, pos.y);
		        if (a % 5 == 0) frame_attack_full++;
	        }
        
            
        }  else if (!head) {
            p.image(Globals.simple_zombie_headless.get(frame_attack_walk), pos.x, pos.y);
            if (a % 2 == 0) frame_attack_walk++;
        } else {
            p.image(Globals.bucket_head_walk_full.get(frame_walk_full), pos.x, pos.y);
            if (a % 5 == 0) frame_walk_full++;
        }
    }
}
