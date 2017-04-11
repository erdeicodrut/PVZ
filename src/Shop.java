class Shop {	
	private int ballance;
	private Item[] loadout;

	public Shop() {
		ballance = 0;
		loadout = new Item[6];
	}

	public boolean extract(int sum) {
		if (ballance > 0) {
			ballance -= sum;
			return true;
		} else {
			return false;
		}
	}
}