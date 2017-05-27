import processing.core.PApplet;
import processing.core.PVector;

public class PartyCrasher extends Plant {

    int timespawned;

    public PartyCrasher(PApplet p, PVector pos){
        super(p, 400, 41, Effect.PARTYCRASHER );
        timespawned = pvz.timer.get_time_in_seconds();
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
    public void onCollisionWith(ICollision other) {
        if(other.isZombie()) this.attack((Zombie) other);
    }

    @Override
    public void show() {
        super.show();
    }
}
