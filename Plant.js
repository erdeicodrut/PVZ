class Plant {

    constructor(type) {
        this.effect = Effect.NONE;
        this.bulletFactory = new BulletFactory(type);
    }

    shoot() {
        return new Bullet(this.x, this.y, 10, this.damage); //TODO get rid of magic numbers
    }

}
