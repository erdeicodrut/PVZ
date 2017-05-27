import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import jdk.nashorn.internal.objects.Global;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class pvz extends PApplet {

    String simpleZombieString;
    String poleVaultZombiString;
    String cornheadZombieString;
    String bucketheadZombieString;
	String flagZombieString;
	String trogloditString;
	String scufundatorString;
	String domnumanagerString;
	String specialistaString;
	String doamnaProfesoaraString;
	String doamnaDirectoareString;

    // The main grid of the game
    Field field = new Field(this, Globals.fieldPos, Globals.fieldDim);
    Shop shop;
    Shovel shovel;
    Zombie p;
    PImage back;
    boolean gameOn;
    public static boolean iShow;
    public static Timer timer;

    // Collections of entities
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Zombie> zombies;
    public static ArrayList<Plant> plants;

    public static ArrayList<Item> itemHabarnam;
    public static ArrayList<Sun> suns;

    public static ArrayList<Dies> dead;



    public void settings() {
        back = loadImage(new File("resources/Background/Back.png").getAbsolutePath());
        size((int) (back.width), (int) (back.height));
    }

    public void setup() {
    	timer = new Timer(this);
        load();
        IOHandler.loadInfo();
        textSize(17);
        bullets = new ArrayList<>();
        zombies = new ArrayList<>();
        plants = new ArrayList<>();
        itemHabarnam = new ArrayList<>();
        suns = new ArrayList<>();
        dead = new ArrayList<>();

        gameOn = true;
        frameRate(30);

        // Initializing the grid
        field.clear();

        new Sun(this);
        shop = new Shop(this, new PVector(10, 15), Globals.shopSize);

        shovel = new Shovel(this);

        FirePea tempFire = new FirePea(this);
        SimplePlant tempSimpe = new SimplePlant(this);
        Sunflower tempSun = new Sunflower(this);
        WallNut tempWall = new WallNut(this);
        SnowPea tempSnow = new SnowPea(this);
        MellonPea tempMello = new MellonPea(this);
	    PartyCrasher tempParty = new PartyCrasher(this);

        tempFire.card = loadImage(new File("resources/Cards/Card_Cactus.png").getAbsolutePath());
        tempSimpe.card = loadImage(new File("resources/Cards/Card_Peashooter.png").getAbsolutePath());
        tempSun.card = loadImage(new File("resources/Cards/Card_SunFlower.png").getAbsolutePath());
        tempWall.card = loadImage(new File("resources/Cards/Card_Wall-Nut.png").getAbsolutePath());
        tempSnow.card = loadImage(new File("resources/Cards/Card_SnowPea.png").getAbsolutePath());
        tempParty.card = loadImage(new File("resources/Cards/Card_Threepeater.png").getAbsolutePath());

        shop.addItem(new Item(this, 80, tempSimpe));
        shop.addItem(new Item(this, 50, tempSun));
        shop.addItem(new Item(this, 500, tempParty));
        shop.addItem(new Item(this, 50, tempWall));
        shop.addItem(new Item(this, 75, tempSnow));
        shop.addItem(new Item(this, 125, tempFire));


        simpleZombieString = SimpleZombie.class.toString().replace("class ", "");
        poleVaultZombiString = PoleVaultingZombie.class.toString().replace("class ", "");
        cornheadZombieString = ConeheadZombie.class.toString().replace("class ", "");
        bucketheadZombieString = BucketheadZombie.class.toString().replace("class ", "");
	    flagZombieString= FlagZombie.class.toString().replace("class ", "");
	    trogloditString= Trogloditul.class.toString().replace("class ", "");
	    scufundatorString= Scufundatorul.class.toString().replace("class ", "");
	    domnumanagerString = DomnulManager.class.toString().replace("class ", "");
	    doamnaProfesoaraString = DoamnaProfesoara.class.toString().replace("class ", "");
	    specialistaString = Specialista.class.toString().replace("class ", "");
	    doamnaDirectoareString = DnaDirectoare.class.toString().replace("class ", "");
    }

    public void draw() {
	
	    if (gameOn == true) {
            fill(0);
            background(255);
            imageMode(CORNER);
            image(back, 0, 0);

            for (Zombie z : zombies) { z.update(); if (z.pos.x < 100) gameOn = false; }
            for (Plant p : plants) p.update();
            for (Bullet b : bullets) b.update();

            if (iShow == true) {
                imageMode(CENTER);
                image(DraggedItem.draggedItem.plant.firstFrame, mouseX, mouseY, Globals.cellSize.x, Globals.cellSize.y);
            }


            // Check collisions and clean-up
            CollisionManager.resolveCollisions();
            garbageCollector();

            Sun.spawn();
            shovel.show();
		
		    spawner();
		    
            show();
        } else {
            if (gameOn == false) {
                imageMode(CORNER);
                image(loadImage(new File("resources/Background/lost.jpg").getAbsolutePath()), 0, 0);
                textSize(50);
                text("Game Over", width / 2, height / 2);
                noLoop();
            } else if (IOHandler.toSpawn.size() == 0 && zombies.size() == 0 && timer.get_time_in_minutes() > 2) {
                imageMode(CORNER);
                image(back, 0, 0);
                textSize(50);
                text("You Won", width / 2, height / 2);
                noLoop();
            }
        }
    }
	
	private void spawner() {
		
		if (timer.get_time_in_seconds() == 60) {
			
			for (int i = 0; i < 15 * Globals.spawnTime; i++) {
				IOHandler.toSpawn.add(simpleZombieString);
			}
			for (int i = 0; i < 10 * Globals.spawnTime; i++) {
				IOHandler.toSpawn.add(trogloditString);
			}
			for (int i = 0; i < 10 * Globals.spawnTime; i++) {
				IOHandler.toSpawn.add(scufundatorString);
			}
		}
		
		if (timer.get_time_in_seconds() == 180) {
			
			for (int i = 0; i < 10 * Globals.spawnTime; i++) {
				IOHandler.toSpawn.add(simpleZombieString);
			}
			for (int i = 0; i < 7 * Globals.spawnTime; i++) {
				IOHandler.toSpawn.add(trogloditString);
			}
			for (int i = 0; i < 4 * Globals.spawnTime; i++) {
				IOHandler.toSpawn.add(domnumanagerString);
			}
			for (int i = 0; i < 3 * Globals.spawnTime; i++) {
				IOHandler.toSpawn.add(specialistaString);
			}
			for (int i = 0; i < 5 * Globals.spawnTime++; i++) {
				IOHandler.toSpawn.add(doamnaProfesoaraString);
			}
			
		}
		
		if (timer.get_time_in_seconds() == 330) {
			for (int i = 0; i < 5 * Globals.spawnTime; i++) {
				IOHandler.toSpawn.add(simpleZombieString);
			}
			for (int i = 0; i < 5 * Globals.spawnTime; i++) {
				IOHandler.toSpawn.add(trogloditString);
			}
			for (int i = 0; i < 7 * Globals.spawnTime; i++) {
				IOHandler.toSpawn.add(scufundatorString);
			}
			for (int i = 0; i < 6 * Globals.spawnTime; i++) {
				IOHandler.toSpawn.add(domnumanagerString);
			}
			for (int i = 0; i < 9 * Globals.spawnTime; i++) {
				IOHandler.toSpawn.add(specialistaString);
			}
			for (int i = 0; i < 8 * Globals.spawnTime ; i++) {
				IOHandler.toSpawn.add(doamnaProfesoaraString);
			}
			for (int i = 0; i < 1 * Globals.spawnTime; i++) {
				IOHandler.toSpawn.add(doamnaProfesoaraString);
			}
		
		}
        
        int index = floor(random(IOHandler.toSpawn.size()));

		if (IOHandler.toSpawn.size() > 0) {
			if (IOHandler.toSpawn.get(index).equals(simpleZombieString)) {
				p = SimpleZombie.spawn();
			} else if (Objects.equals(IOHandler.toSpawn.get(index), poleVaultZombiString)) {
				p = PoleVaultingZombie.spawn();
			} else if (Objects.equals(IOHandler.toSpawn.get(index), cornheadZombieString)) {
				p = ConeheadZombie.spawn();
			} else if (Objects.equals(IOHandler.toSpawn.get(index), bucketheadZombieString)) {
				p = BucketheadZombie.spawn();
			} else if (Objects.equals(IOHandler.toSpawn.get(index), flagZombieString)) {
				p = FlagZombie.spawn();
			} else if (Objects.equals(IOHandler.toSpawn.get(index), trogloditString)) {
				p = Trogloditul.spawn();
			} else if (Objects.equals(IOHandler.toSpawn.get(index), scufundatorString)) {
				p = Scufundatorul.spawn();
			} else if (Objects.equals(IOHandler.toSpawn.get(index), domnumanagerString)) {
				p = DomnulManager.spawn();
			} else if (Objects.equals(IOHandler.toSpawn.get(index), specialistaString)) {
				p = Specialista.spawn();
			} else if (Objects.equals(IOHandler.toSpawn.get(index), doamnaProfesoaraString)) {
				p = DoamnaProfesoara.spawn();
			} else if (Objects.equals(IOHandler.toSpawn.get(index), doamnaDirectoareString)) {
				p = DnaDirectoare.spawn();
			}
			
			IOHandler.toSpawn.remove(index);
			
			if (p != null) {
				zombies.add(p);
				CollisionManager.addObject(p);
			}
		}
    }

    private void show() {
        
        timer.show();

        for (Plant p : plants) p.show();
        for (Bullet b : bullets) b.show();
        for (Zombie z : zombies) z.show();
        for (Dies z : dead) z.show();

        shop.show(); // Shop and items

        for (Item draggedItem : itemHabarnam) draggedItem.show();
        for (Sun sun : suns) sun.show();
    }

    private void garbageCollector() {
        int i = 0;
        while (i < plants.size()) {
            if (!plants.get(i).isAlive()) {
                Living removed = plants.remove(i);
                CollisionManager.removeObject(removed);

                for (Cell[] line : field.matrix) {
                    for (Cell obj : line) {
                        if (obj.plant == removed) {
                            obj.clear();
                        }
                    }
                }

            } else
                i++;
        }
        i = 0;
        while (i < zombies.size()) {
            if (!zombies.get(i).isAlive()) {

                Living removed = zombies.remove(i);
                CollisionManager.removeObject(removed);
                String className = removed.getClass().toString();
                className = className.replace("class ", "");

                if (Objects.equals(className,poleVaultZombiString)) {
                    dead.add(new Dies(this, Globals.deadPaul, removed.pos));
                } else {
                    dead.add(new Dies(this, Globals.deadZombie, removed.pos));
                }

            } else
                i++;
        }
        i = 0;
        while (i < bullets.size()) {
            if (!bullets.get(i).isAlive()) {

                Living removed = bullets.remove(i);
                CollisionManager.removeObject(removed);
            } else
                i++;
        }
        i = 0;
        while (i < suns.size()) {
            if (suns.get(i).isGonnaDie) {
                Sun removed = suns.remove(i);
                InputManager.queue.remove(removed);
            } else
                i++;
        }
    }

    private void load() {
	    
	    Globals.imgDD = loadImage(new File("resources/8_doamnadirectoare.png").getAbsolutePath());
	    Globals.imgPP = loadImage(new File("resources/cabbage.png").getAbsolutePath());
	    Globals.imgTR = loadImage(new File("resources/Zombies/Troglo/trogloditul.png").getAbsolutePath());
	    Globals.imgDS = loadImage(new File("resources/Zombies/Specialista/Specialista.png").getAbsolutePath());
	    Globals.imgDP = loadImage(new File("resources/6_doamnaprofesoara.png").getAbsolutePath());
	    Globals.imgSS = loadImage(new File("resources/4_scufundatorul.png").getAbsolutePath());
	
	
	    for (int i = 0; i <= 9; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/PoleVaultingZombie/PoleVaultingZombie_" + (i++) + ".png").getAbsolutePath());
            Globals.pole_walk_full.add(temp);
        }

        for (int i = 0; i <= 13; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/PoleVaultingZombieAttack/PoleVaultingZombieAttack_" + (i++) + ".png").getAbsolutePath());
            Globals.pole_attack_full.add(temp);
        }

        for (int i = 0; i <= 9; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/PoleVaultingZombieJump/PoleVaultingZombieJump_" + (i++) + ".png").getAbsolutePath());
            Globals.VaultPicsJump.add(temp);
        }

        for (int i = 0; i <= 24; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/PoleVaultingZombieWalk/PoleVaultingZombieWalk_" + (i++) + ".png").getAbsolutePath());
            Globals.pole_walk_full2.add(temp);
        }



        for (int i = 0; i <= 14; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/BucketheadZombie/BucketheadZombie_" + (i++) + ".png").getAbsolutePath());
            Globals.bucket_head_walk_full.add(temp);
        }

        for (int i = 0; i <= 10; i++) {
            PImage temp = new PImage();
            temp = loadImage(new File("resources/Zombies/BucketheadZombieAttack/BucketheadZombieAttack_" + (i++) + ".png").getAbsolutePath());
            Globals.bucket_head_attack_full.add(temp);
        }
	       
	       
        for (int i = 0; i <= 21; i++) {
            PImage temp = new PImage();
            temp = loadImage(new File("resources/Zombies/Zombie/Zombie_" + (i++) + ".png").getAbsolutePath());
            Globals.simple_zombie_walk_full.add(temp);
        }
	       
        for (int i = 0; i <= 20; i++) {
            PImage temp = new PImage();
            temp = loadImage(new File("resources/Zombies/ZombieAttack/ZombieAttack_" + (i++) + ".png").getAbsolutePath());
            Globals.simple_zombie_attack_full.add(temp);
        }
	       
	       
	       
        for (int i = 0; i <= 11; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/FlagZombie/FlagZombie_" + (i++) + ".png").getAbsolutePath());
            Globals.flag_zombie_walk_full.add(temp);
        }
	       
        for (int i = 0; i <= 10; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/FlagZombieAttack/FlagZombieAttack_" + (i++) + ".png").getAbsolutePath());
            Globals.flag_zombie_attack_full.add(temp);
        }
	       
	       
        for (int i = 0; i <= 20; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/ConeheadZombie/ConeheadZombie_" + (i++) + ".png").getAbsolutePath());
            Globals.conehead_walk_full.add(temp);
        }
	       
        for (int i = 0; i <= 10; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/ConeheadZombieAttack/ConeheadZombieAttack_" + (i++) + ".png").getAbsolutePath());
            Globals.cone_head_walk_full.add(temp);
        }


        for (int i = 0; i <= 12; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Plants/Peashooter/Peashooter_" + (i++) + ".png").getAbsolutePath());
            Globals.picPea.add(temp);
        }


        for (int i = 0; i <= 15; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Plants/WallNut/WallNut/WallNut_" + (i++) + ".png").getAbsolutePath());
            Globals.picWall.add(temp);
        }

        for (int i = 0; i <= 10; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Plants/WallNut/Wallnut_cracked1/Wallnut_cracked1_" + (i++) + ".png").getAbsolutePath());
            Globals.picWall1.add(temp);
        }

        for (int i = 0; i <= 14; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Plants/WallNut/Wallnut_cracked2/Wallnut_cracked2_" + (i++) + ".png").getAbsolutePath());
            Globals.picWall2.add(temp);
        }


        for (int i = 0; i <= 17; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Plants/Sunflower/SunFlower_" + (i++) + ".png").getAbsolutePath());
            Globals.picSunflower.add(temp);
        }


        for (int i = 0; i <= 12; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Plants/FirePea/FirePea_" + (i++) + ".png").getAbsolutePath());
            Globals.picFirePea.add(temp);
        }


        for (int i = 0; i <= 14; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Plants/SnowPea/SnowPea_" + (i++) + ".png").getAbsolutePath());
            Globals.picsSnowPea.add(temp);
        }

        for (int i = 0; i <= 14; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Plants/Threepeater/Threepeater_" + (i++) + ".png").getAbsolutePath());
            Globals.picsMellonPea.add(temp);
        }

        for (int i = 0; i <= 21; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Sun/Sun/Sun_" + (i++) + ".png").getAbsolutePath());
            Globals.picSun.add(temp);
        }



        // dieded guy

        for (int i = 0; i <= 9; i++) {
            Globals.deadZombie.add(loadImage(new File("resources/Zombies/ZombieDie/ZombieDie_" + i + ".png").getAbsolutePath()));
        }

        for (int i = 0; i <= 8; i++) {
            Globals.deadPaul.add(loadImage(new File("resources/Zombies/PoleVaultingZombieDie/PoleVaultingZombieDie_" + i + ".png").getAbsolutePath()));
        }

        for (int i = 0; i <= 17; i++) {
            Globals.simple_zombie_headless.add(loadImage(new File("resources/Zombies/ZombieLostHead/ZombieLostHead_" + i + ".png").getAbsolutePath()));
        }

        for (int i = 0; i <= 28; i++) {
            Globals.pole_walk_headless.add(loadImage(new File("resources/Zombies/PoleVaultingZombieLostHeadWalk/PoleVaultingZombieLostHeadWalk_" + i + ".png").getAbsolutePath()));
        }
	
	    for (int i = 0; i <= 10; i++) {
		    Globals.simple_zombie_headless_attack.add(loadImage(new File("resources/Zombies/ZombieLostHeadAttack/ZombieLostHeadAttack_" + i + ".png").getAbsolutePath()));
	    }
	
	    for (int i = 0; i <= 11; i++) {
		    Globals.flag_walk_headless.add(loadImage(new File("resources/Zombies/FlagZombieLostHead/FlagZombieLostHead_" + i + ".png").getAbsolutePath()));
	    }
	    
	    for (int i = 0; i <= 10; i++) {
		    Globals.flag_attack_headless.add(loadImage(new File("resources/Zombies/FlagZombieLostHeadAttack/FlagZombieLostHeadAttack_" + i + ".png").getAbsolutePath()));
	    }
	
	    for (int i = 0; i <= 28; i++) {
		    Globals.pole_walk_headless.add(loadImage(new File("resources/Zombies/PoleVaultingZombieLostHeadWalk/PoleVaultingZombieLostHeadWalk_" + i + ".png").getAbsolutePath()));
	    }
	
	    for (int i = 0; i <= 13; i++) {
		    Globals.pole_attack_headless.add(loadImage(new File("resources/Zombies/PoleVaultingZombieLostHeadAttack/PoleVaultingZombieLostHeadAttack_" + i + ".png").getAbsolutePath()));
	    }
	
	    for (int i = 0; i <= 7; i++) {
		    Globals.head_pole_vault.add(loadImage(new File("resources/Zombies/PoleVaultingZombieHead/PoleVaultingZombieHead_" + i + ".png").getAbsolutePath()));
	    }
	    
	    for (int i = 0; i <= 11; i++) {
		    Globals.head_zombie.add(loadImage(new File("resources/Zombies/ZombieHead/ZombieHead_" + i + ".png").getAbsolutePath()));
	    }
    }
    
    

    @Override
    public void mouseMoved(MouseEvent event) {
        InputManager.mouseMoved(event);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        InputManager.mousePressed(event);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        InputManager.mouseClicked(event);
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        InputManager.mouseDragged(event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        InputManager.mouseReleased(event);
        shovel.setPosition(Shovel.origin);
    
    }

    @Override
    public void mouseWheel(MouseEvent event) {
        InputManager.mouseWheel(event);
    }
}