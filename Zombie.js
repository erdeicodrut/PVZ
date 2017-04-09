class Zombie extends Movable {
    constructor(speed, damage) {
        this.speed = speed;
        this.damage = damage;
    }

    attack(plant) {
        if (plant.hp > 0) {
            plant.hp -= this.damage;
        }
    }

    move() {
        this.x -= this.speed;
    }


}
