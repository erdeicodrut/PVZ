class Field {
    constructor(long, lat) {
        this.matrix = [long][lat];
    }

    this.clear() {
        for (line in this.matrix) {
            for (elem in line) {
                elem.plant = undefined;
            }
        }
    }
}
