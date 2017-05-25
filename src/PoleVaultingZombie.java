import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class PoleVaultingZombie extends Zombie {
    public int frame_jump = 0;
    public int frame_walk = 0;
    public int frame_walk2 = 0;
    public int frame_attack = 0;
    private int jumps = 1;

        public int animationFrameHalf = 0;

    int a = 0;


    boolean hasJumped, doShow = true;
    private Plant toAvoid;

    public PoleVaultingZombie() { super(); }

    public PoleVaultingZombie(PApplet p, PVector zombiePos) {
        super(p, zombiePos, Globals.zombieSize, 17, Globals.speed, 1);

        hasJumped = false;
    }

    public static PoleVaultingZombie spawn() {
        if (timerSpawn-- == 0) {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                                p.floor(p.random(5f)) * Globals.cellSize.y));


            resetSpawn();

            return new PoleVaultingZombie(p, zombiePos);

        }
        return null;
    }

    public void update() {
        if (!CollisionManager.isCollidingWithClass(this, Plant.class)) {
            move();
        } else {
            if (!hasJumped)
            {
                move();
                jump();
            }
        }
    }

    private void jump() {
        doShow = false;

        a++;
        if (frame_jump >= Globals.VaultPicsJump.size()) frame_jump = Globals.VaultPicsJump.size() - 1;
        p.imageMode(PConstants.CENTER);
        p.image(Globals.VaultPicsJump.get(frame_jump), pos.x, pos.y);
        if (a % 4 == 0) frame_jump++;

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
    public void onCollisionWith(ICollision other) {
        if (Plant.class.isAssignableFrom(other.getClass())) {
            Plant plant = (Plant) other;

            if (toAvoid == null && plant != toAvoid) {
                if (hasJumped == false) toAvoid = plant;
            } else attack(plant);
        }
    }

    @Override
    public void onCollisionExitWith(ICollision other) {
        if (Plant.class.isAssignableFrom(other.getClass())) {
            Plant plantToAvoid = (Plant) other;

            if (plantToAvoid == toAvoid)
                hasJumped = true;

            doShow = true;
        }
    }

    @Override
    public void show() {
	
	
	    if (head && hp < 17 / 2) {
		    head = false;
		    pvz.dead.add(new Dies(p, Globals.head_pole_vault, pos));
	    }
	
	
	    if (doShow) {
        	
            a++;

            p.imageMode(PConstants.CENTER);
            if (frame_walk >= Globals.pole_walk_full.size()) frame_walk = 0;
            if (frame_attack >= Globals.pole_attack_full.size()) frame_attack = 0;
            if (frame_walk2 >= Globals.pole_walk_full2.size()) frame_walk2 = 0;
            if (animationFrameHalf >= Globals.pole_walk_headless.size()) animationFrameHalf = 0;
	        if (frame_attack_headless >= Globals.pole_attack_headless.size()) frame_attack_headless = 0;

            if (CollisionManager.isCollidingWithClass(this, Plant.class)) {
            	if (!head) {
		            p.image(Globals.pole_attack_headless.get(frame_attack_headless++), pos.x, pos.y);
	            } else p.image(Globals.pole_attack_full.get(frame_attack++), pos.x, pos.y);
            } else {
                if (hasJumped == true && head) {
                    p.image(Globals.pole_walk_full2.get(frame_walk2), pos.x, pos.y);
                    if (a % 3 == 0) frame_walk2++;
                }  else if (!head) {
                        p.image(Globals.pole_walk_headless.get(animationFrameHalf), pos.x, pos.y);
                    if (a % 2 == 0) animationFrameHalf++;
                } else {
                    p.image(Globals.pole_walk_full.get(frame_walk), pos.x, pos.y);
                    if (a % 3 == 0) frame_walk++;
                }
            }
        }
    }
}
