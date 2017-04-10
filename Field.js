class Field {
    constructor(long, lat) {
        this.matrix = [[]];
    }

    clear() {
        var inceputX = 32;
        var inceputY = 176;

        // var i = 0;
        for (var i = 0; i < this.matrix.length; i++) {
            // var j = 0;
            for (var j = 0; j < this.matrix[i].length; j++) {
                var xA = inceputX + i*142;
                var yA = inceputY + j*103;
                this.matrix[i][j].push(new Cell(xA, yA, xA+142, yA+103));
                this.matrix[i][j].elem.plant = undefined;
                // j++;
            }
            // i++;
        }
    }
}
