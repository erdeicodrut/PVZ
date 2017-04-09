class Cell {


    constructor(xa, ya, xb, yb) {
        this.plant = undefined;
        this.xA = xa;
        this.xB = xb;
        this.yA = ya;
        this.yB = yb;
    }

    isOccupied() {
        if (this.plant) {
            return true;
        } else {
            return false;
        }
    }

    place(plant) {
        this.plant = plant;
    }
}
