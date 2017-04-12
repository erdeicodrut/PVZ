import processing.core.PApplet;
import processing.core.PVector;

class Field {
	private PApplet p;
	private PVector pos;

	private Cell[][] matrix;
	private PVector dim;

	public Field(PApplet p, PVector pos, PVector dim) {
		this.p = p;
		this.pos = pos;

		this.dim = dim;

		// Init the matrix
		matrix = new Cell[(int) dim.y][(int) dim.x];
	}

	// Reset every cell of the matrix
	public void clear() {
		for (int i = 0; i < dim.y; i++) {
			for (int j = 0; j < dim.x; j++) {
				PVector newPos = new PVector(pos.x + j*Globals.cellSize.x, pos.y + i*Globals.cellSize.y);
				matrix[i][j] = new Cell(p, newPos, Globals.cellSize);
			}
		}
	}

	public Cell at(int x, int y) {
		return this.matrix[x][y];
	}

	// Iterate through each cell and show it
	public void show() {
		for (Cell[] line : matrix) {
			for (Cell cell : line)
				cell.show();
		}
	}
}