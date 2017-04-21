import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class Globals {

    public static float scale = 1;

	public static PVector fieldPos = new PVector(250, 80).mult(scale);
	public static PVector fieldDim = new PVector(10, 6).mult(scale);
	public static PVector cellSize = new PVector(74f, 80f).mult(scale);

	public static PVector flowerSize = new PVector(100f/2, 100f/2).mult(scale);
	public static PVector zombieSize = new PVector(100f / 2, 100f / 2).mult(scale);

	public static PVector shopSize = new PVector(50, 450).mult(scale);
	public static PVector itemSize = new PVector(40f, 40f).mult(scale);

	public static float bulletDamage = 1f;

	public static PVector sunSize = new PVector(30f, 30f).mult(scale);

	public static float speed = 3;
	public static int spawnTime = 50;

//
// Helper functions
//

	public static PVector getMousePos(MouseEvent event)
	{ return new PVector(event.getX(), event.getY()); }

	public static PVector getMousePos(PApplet p)
	{ return new PVector(p.mouseX, p.mouseY); }

	public static PVector getRelativeMousePos(PApplet p)
	{ return new PVector(p.mouseX - p.pmouseX, p.mouseY - p.pmouseY); }
}