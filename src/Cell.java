class Cell {
	public float xA, yA, xB, yB;
	public Plant plant;

	public Cell(float xA, float yA, float xB, float yB) {
		this.xA = xA;
		this.yA = yA;
		this.xB = xB;
		this.yB = yB;
	}

	public boolean isOccupied() {
		if (plant != null) {
			return false;
		} else {
			return true;
		}
	}

	public place(Plant to) {

		//TODO shop stuff

		if (this.isOccupied == false) {
			this.plant = to;
		}
	}
}