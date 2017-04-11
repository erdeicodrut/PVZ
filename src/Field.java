class Field {
	public  Cell[][] matrix;
	public int width, height;
	public Field(int width, int height) {
		matrix = new Cell[width][height];
		this.width = width;
		this.height = height;
	}

	public void clear() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				matrix[i][j] = new Cell();
			}
		}
	}
}