import processing.core.PApplet;

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
			}
		}
	}

	public void show() {
		for (Cell[] line : matrix) {
			for (Cell cell : line)
				cell.show();
		}
	}
}