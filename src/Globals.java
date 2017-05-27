import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.awt.peer.ChoicePeer;
import java.util.ArrayList;

public class Globals {

    public static float scale = 1;

	public static PVector fieldPos = new PVector(250, 80).mult(scale);
	public static PVector fieldDim = new PVector(15, 5).mult(scale);
	public static PVector cellSize = new PVector(83f, 96f).mult(scale);

	public static PVector flowerSize = new PVector(100f/2, 100f/2).mult(scale);
	public static PVector zombieSize = new PVector(100f / 2, 100f / 2).mult(scale);

	public static PVector shopSize = new PVector(50, 450).mult(scale);
	public static PVector itemSize = new PVector(100f, 50f).mult(scale);

	public static PVector sunSize = new PVector(50f, 50f).mult(scale);

	public static float speed = 1;
	public static int spawnTime = 100;

    public static ArrayList<PImage> pole_walk_full = new ArrayList<>();
    public static ArrayList<PImage> pole_attack_full = new ArrayList<>();
    public static ArrayList<PImage> VaultPicsJump  = new ArrayList<>();
    public static ArrayList<PImage> pole_walk_full2 = new ArrayList<>();

    public static ArrayList<PImage> bucket_head_walk_full = new ArrayList<>();
    public static ArrayList<PImage> bucket_head_attack_full = new ArrayList<>();

    public static ArrayList<PImage> simple_zombie_walk_full = new ArrayList<>();
    public static ArrayList<PImage> simple_zombie_attack_full = new ArrayList<>();

    public static ArrayList<PImage> flag_zombie_walk_full = new ArrayList<>();
    public static ArrayList<PImage> flag_zombie_attack_full = new ArrayList<>();

    public static ArrayList<PImage> conehead_walk_full = new ArrayList<>();
    public static ArrayList<PImage> cone_head_walk_full = new ArrayList<>();

    public static ArrayList<PImage> picPea = new ArrayList<>();

    public static ArrayList<PImage> picWall = new ArrayList<>();
    public static ArrayList<PImage> picWall1 = new ArrayList<>();
    public static ArrayList<PImage> picWall2 = new ArrayList<>();

    public static ArrayList<PImage> picSunflower = new ArrayList<>();

    public static ArrayList<PImage> picFirePea = new ArrayList<>();

    public static ArrayList<PImage> picsSnowPea = new ArrayList<>();

    public static ArrayList<PImage> picsMellonPea = new ArrayList<>();

    public static ArrayList<PImage> picSun = new ArrayList<>();
	
	
	public static ArrayList<PImage> deadZombie = new ArrayList<>();
    public static ArrayList<PImage> deadPaul = new ArrayList<>();
	

    public static ArrayList<PImage> simple_zombie_headless = new ArrayList<>();
    public static ArrayList<PImage> pole_walk_headless = new ArrayList<>();
	public static ArrayList<PImage> flag_walk_headless = new ArrayList<>();

	public static ArrayList<PImage> simple_zombie_headless_attack = new ArrayList<>();
	public static ArrayList<PImage> pole_attack_headless = new ArrayList<>();
	public static ArrayList<PImage> flag_attack_headless = new ArrayList<>();
	
	
	public static ArrayList<PImage> head_pole_vault = new ArrayList<>();
	public static ArrayList<PImage> head_zombie = new ArrayList<>();
	
	
	
	public static PVector getMousePos(MouseEvent event)
	{ return new PVector(event.getX(), event.getY()); }

	public static PVector getMousePos(PApplet p)
	{ return new PVector(p.mouseX, p.mouseY); }

	public static PVector getRelativeMousePos(PApplet p)
	{ return new PVector(p.mouseX - p.pmouseX, p.mouseY - p.pmouseY); }
}