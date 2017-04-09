class Bullet extends Movable {

    constructor(x, y, speed, damage, effect) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
    }

    this.move() {
        this.x += this.speed;
    }

    this.hit(oponent) {
        oponent.receive(this.damage, this.effect);
        this.hp = 0;
    }
}
