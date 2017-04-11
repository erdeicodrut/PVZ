import processing.core.PApplet;
<<<<<<< HEAD

class Field {
	private PApplet p;
	private float x;
	private float y;
	private int width, height;

	private float cellWidth = 71f;
	private float cellHeight = 51.5f;

	public  Cell[][] matrix;

	public Field(PApplet p, float x, float y, int width, int height) {
		this.p = p;

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		matrix = new Cell[width][height];
	}

	public void clear() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				matrix[i][j] = new Cell(p, x + j*cellWidth, y + i*cellHeight, cellWidth, cellHeight);
=======
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
>>>>>>> processing
			}
		}
	}

<<<<<<< HEAD
	public void show() {
=======
	public Cell at(int x, int y) {
		return this.matrix[x][y];
	}

	public void show() {
		p.rectMode(CORNER);
>>>>>>> processing
		for (Cell[] line : matrix) {
			for (Cell cell : line)
				cell.show();
		}
	}
}