class Plant {
    constructor(type) {
        this.bulletFactory = new BulletFactory(type);
    }

    this.shoot() {
        return new Bullet(this.x, this.y, 10, this.damage); //TODO get rid of magic numbers
    }

    this.effect = Effect.NONE;
}
