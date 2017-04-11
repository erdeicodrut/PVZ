import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PConstants.CORNER;

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

	public void show() {
		p.rectMode(CORNER);
		for (Cell[] line : matrix) {
			for (Cell cell : line)
				cell.show();
		}
	}
}