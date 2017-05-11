import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.util.ArrayList;

public class Globals {

    public static float scale = 1;

	public static PVector fieldPos = new PVector(250, 80).mult(scale);
	public static PVector fieldDim = new PVector(9, 5).mult(scale);
	public static PVector cellSize = new PVector(82f, 95f).mult(scale);

	public static PVector flowerSize = new PVector(100f/2, 100f/2).mult(scale);
	public static PVector zombieSize = new PVector(100f / 2, 100f / 2).mult(scale);

	public static PVector shopSize = new PVector(50, 450).mult(scale);
	public static PVector itemSize = new PVector(100f, 50f).mult(scale);

	public static float bulletDamage = 1f;

	public static PVector sunSize = new PVector(50f, 50f).mult(scale);

	public static float speed = 1;
	public static int spawnTime = 300;

    public static ArrayList<PImage> VaultPics  = new ArrayList<>();
    public static ArrayList<PImage> VaultPicsAttack  = new ArrayList<>();
    public static ArrayList<PImage> VaultPicsJump  = new ArrayList<>();
    public static ArrayList<PImage> VaultPicsWalk  = new ArrayList<>();

    public static ArrayList<PImage> picsBucketHead = new ArrayList<>();
    public static ArrayList<PImage> picsBucketHeadAttack = new ArrayList<>();

    public static ArrayList<PImage> picsSimpleZombie = new ArrayList<>();
    public static ArrayList<PImage> picsSimpleZombieAttack = new ArrayList<>();

    public static ArrayList<PImage> picsFlagZombie = new ArrayList<>();
    public static ArrayList<PImage> picsFlagZombieAttack = new ArrayList<>();

    public static ArrayList<PImage> picsConheadZombie = new ArrayList<>();
    public static ArrayList<PImage> picsConheadZombieAttack = new ArrayList<>();

    public static ArrayList<PImage> picPea = new ArrayList<>();

    public static ArrayList<PImage> picWall = new ArrayList<>();
    public static ArrayList<PImage> picWall1 = new ArrayList<>();
    public static ArrayList<PImage> picWall2 = new ArrayList<>();

    public static ArrayList<PImage> picSunflower = new ArrayList<>();

    public static ArrayList<PImage> picFirePea = new ArrayList<>();

    public static ArrayList<PImage> picsSnowPea = new ArrayList<>();

    public static ArrayList<PImage> picsMellonPea = new ArrayList<>();

    public static ArrayList<PImage> picSun = new ArrayList <>();


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