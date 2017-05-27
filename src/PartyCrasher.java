import jdk.nashorn.internal.objects.Global;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;

public class PartyCrasher extends Plant {

    int timespawned;
    
    public PImage img;

    public PartyCrasher(PApplet p, PVector pos){
        super(p, 400, 41, Effect.PARTYCRASHER );
        timespawned = pvz.timer.get_time_in_seconds();
        img = Globals.imgPP;
	    firstFrame = img;
	
    }
	
	public PartyCrasher(PApplet p) {
        super(p, 400, 41, Effect.PARTYCRASHER );
		firstFrame = img;
	}
	
	public PartyCrasher(PartyCrasher as) {
		this(as.p, as.pos);
		firstFrame = img;
	}
	
	
	public void boom() {
        this.hp = 0;
    }

    public void attack(Zombie other) {
        if(pvz.timer.get_time_in_seconds() - timespawned > pvz.timer.get_second() * 5) {
            other.receiveEffect(Effect.PARTYCRASHER);
            boom();
        }
    }

    @Override
    public void shoot() {
        return;
    }

    @Override
    public void onCollisionWith(ICollision other) {
        if(other.isZombie()) this.attack((Zombie) other);
    }

    @Override
    public void show() {
	    p.imageMode(PConstants.CENTER);
	    p.image(img, pos.x + size.x, pos.y + size.y, Globals.cellSize.x, Globals.cellSize.y);
	
    }
}
