class Plant extends Movable {
    constructor(type, x, y, width, height, hp) {
    	this.speed = 10;
    	super(x, y, width, height, hp);
        this.effect = type; //Effect.NONE;
    }

    shoot() {
        return new Bullet(this.x, this.y, this.speed, this.damage, this.effect);
    }
}
