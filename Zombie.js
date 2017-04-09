class Zombie extends Movable {
    constructor(speed, damage) {
        this.speed = speed;
        this.damage = damage;
    }

    this.attack(plant) {
        if (plant.hp > 0) {
            plant.hp -= this.damage;
        }
    }

    this.move() {
        this.x -= this.speed;
    }

    
}
