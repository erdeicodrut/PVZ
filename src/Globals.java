import processing.core.PVector;

public class Globals {

    public static float scale = 1;

	public static PVector fieldPos = new PVector(40/2 + 70, 200/2).mult(scale);
	public static PVector fieldDim = new PVector(10, 6).mult(scale);
	public static PVector cellSize = new PVector(50f, 50f).mult(scale);

	public static PVector flowerSize = new PVector(100f/2, 100f/2).mult(scale);
	public static PVector zombieSize = new PVector(100f / 2, 100f / 2).mult(scale);

	public static PVector shopSize = new PVector(50, 450).mult(scale);
	public static PVector itemSize = new PVector(40f, 40f).mult(scale);
}
