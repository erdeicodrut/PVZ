class Cell {

    this.plant = undefined;

    constructor(xa, ya, xb, yb) {
        this.xA = xa;
        this.xB = xb;
        this.yA = ya;
        this.yB = yb;
    }

    this.isOccupied() {
        if (this.plant) {
            return true;
        } else {
            return false;
        }
    }

    this.place(plant) {
        this.plant = plant;
    }
}
