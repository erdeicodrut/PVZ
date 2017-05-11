import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class pvz extends PApplet {

    String simpleZombieString;
    String poleVaultZombiString;
    String cornheadZombieString;
    String bucketheadZombieString;
    String flagZombieString;

    // The main grid of the game
    Field field = new Field(this, Globals.fieldPos, Globals.fieldDim);
    Shop shop;
    Shovel shovel;
    Zombie p;
    PImage back;
    boolean gameOn;

    // Collections of entities
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Zombie> zombies;
    public static ArrayList<Plant> plants;

    public static ArrayList<Item> draggedItems;
    public static ArrayList<Sun> suns;

    public void settings() {
        back = loadImage(new File("resources/Background/PVZBackground_3.jpg").getAbsolutePath());
        size((int) (back.width), (int) (back.height));
    }

    public void setup() {
        load();
        IOHandler.loadInfo();
        textSize(17);
        bullets = new ArrayList<>();
        zombies = new ArrayList<>();
        plants = new ArrayList<>();
        draggedItems = new ArrayList<>();
        suns = new ArrayList<>();

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

        tempFire.card = loadImage(new File("resources/Cards/Card_Cactus.png").getAbsolutePath());
        tempSimpe.card = loadImage(new File("resources/Cards/Card_Peashooter.png").getAbsolutePath());
        tempSun.card = loadImage(new File("resources/Cards/Card_SunFlower.png").getAbsolutePath());
        tempWall.card = loadImage(new File("resources/Cards/Card_Wall-Nut.png").getAbsolutePath());
        tempSnow.card = loadImage(new File("resources/Cards/Card_SnowPea.png").getAbsolutePath());
        tempMello.card = loadImage(new File("resources/Cards/Card_Threepeater.png").getAbsolutePath());

        shop.addItem(new Item(this, 175, tempFire));
        shop.addItem(new Item(this, 100, tempSimpe));
        shop.addItem(new Item(this, 50, tempSun));
        shop.addItem(new Item(this, 50, tempWall));
        shop.addItem(new Item(this, 175, tempSnow));
        shop.addItem(new Item(this, 250, tempMello));


        simpleZombieString = SimpleZombie.class.toString().replace("class ", "");
        poleVaultZombiString = PoleVaultingZombie.class.toString().replace("class ", "");
        cornheadZombieString = ConeheadZombie.class.toString().replace("class ", "");
        bucketheadZombieString = BucketheadZombie.class.toString().replace("class ", "");
        flagZombieString= FlagZombie.class.toString().replace("class ", "");
    }

    public void draw() {
        if (zombies.size() != 0 || IOHandler.toSpawn.size() != 0) {
            fill(0);
            background(255);
            imageMode(CORNER);
            image(back, 0, 0);

            for (Zombie z : zombies) { z.update(); if (z.pos.x < 100) gameOn = false; }
            for (Plant p : plants) p.update();
            for (Bullet b : bullets) b.update();

            // Check collisions and clean-up
            CollisionManager.resolveCollisions();
            garbageCollector();

            Sun.spawn();
            shovel.show();

            if (IOHandler.toSpawn.size() > 0) spawner();
//            else youWon();

            show();
//            field.show();
        } else {
            if (gameOn == false) {
                imageMode(CORNER);
                image(loadImage(new File("resources/Background/lost.jpg").getAbsolutePath()), 0, 0);
                textSize(50);
                text("Game Over", width / 2, height / 2);
            } else if (IOHandler.toSpawn.size() == 0 && zombies.size() == 0) {
                imageMode(CORNER);
                image(back, 0, 0);
                textSize(50);
                text("You Won", width / 2, height / 2);
            }
        }
    }

    private void spawner() {
        int index = floor(random(IOHandler.toSpawn.size()));

        if (IOHandler.toSpawn.get(index).equals(simpleZombieString)) {
                p = SimpleZombie.spawn();
        } else

        if (Objects.equals(IOHandler.toSpawn.get(index), poleVaultZombiString)) {
                p = PoleVaultingZombie.spawn();
        } else

        if (Objects.equals(IOHandler.toSpawn.get(index), cornheadZombieString)) {
                p = ConeheadZombie.spawn();
        } else

        if (Objects.equals(IOHandler.toSpawn.get(index), bucketheadZombieString)) {
                p = BucketheadZombie.spawn();
        } else

        if (Objects.equals(IOHandler.toSpawn.get(index), flagZombieString)) {
                p = FlagZombie.spawn();
        }

        IOHandler.toSpawn.remove(index);

        if (p != null) {
            zombies.add(p);
            CollisionManager.addObject(p);
        }
    }

    private void show() {
//        field.show(); // Cells

        for (Plant p : plants) p.show();
        for (Bullet b : bullets) b.show();
        for (Zombie z : zombies) z.show();

        shop.show(); // Shop and items

        for (Item draggedItem : draggedItems) draggedItem.show();
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

    private void sort(ArrayList<Living> ye) {
        for (int i = 0; i < ye.size() - 1; i++) {
            for (int j = i + 1; j < ye.size(); j++) {
                if (ye.get(i).pos.y > ye.get(j).pos.y) {
                    Collections.swap(ye, i, j);
                }
            }
        }
    }

    private void load() {
        for (int i = 0; i <= 9; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/PoleVaultingZombie/PoleVaultingZombie_" + (i++) + ".png").getAbsolutePath());
            Globals.VaultPics.add(temp);
        }

        for (int i = 0; i <= 13; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/PoleVaultingZombieAttack/PoleVaultingZombieAttack_" + (i++) + ".png").getAbsolutePath());
            Globals.VaultPicsAttack.add(temp);
        }

        for (int i = 0; i <= 9; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/PoleVaultingZombieJump/PoleVaultingZombieJump_" + (i++) + ".png").getAbsolutePath());
            Globals.VaultPicsJump.add(temp);
        }

        for (int i = 0; i <= 24; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/PoleVaultingZombieWalk/PoleVaultingZombieWalk_" + (i++) + ".png").getAbsolutePath());
            Globals.VaultPicsWalk.add(temp);
        }



        for (int i = 0; i <= 14; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/BucketheadZombie/BucketheadZombie_" + (i++) + ".png").getAbsolutePath());
            Globals.picsBucketHead.add(temp);
        }

        for (int i = 0; i <= 10; i++) {
            PImage temp = new PImage();
            temp = loadImage(new File("resources/Zombies/BucketheadZombieAttack/BucketheadZombieAttack_" + (i++) + ".png").getAbsolutePath());
            Globals.picsBucketHeadAttack.add(temp);
        }


        for (int i = 0; i <= 21; i++) {
            PImage temp = new PImage();
            temp = loadImage(new File("resources/Zombies/Zombie/Zombie_" + (i++) + ".png").getAbsolutePath());
            Globals.picsSimpleZombie.add(temp);
        }

        for (int i = 0; i <= 20; i++) {
            PImage temp = new PImage();
            temp = loadImage(new File("resources/Zombies/ZombieAttack/ZombieAttack_" + (i++) + ".png").getAbsolutePath());
            Globals.picsSimpleZombieAttack.add(temp);
        }



        for (int i = 0; i <= 11; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/FlagZombie/FlagZombie_" + (i++) + ".png").getAbsolutePath());
            Globals.picsFlagZombie.add(temp);
        }

        for (int i = 0; i <= 10; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/FlagZombieAttack/FlagZombieAttack_" + (i++) + ".png").getAbsolutePath());
            Globals.picsFlagZombieAttack.add(temp);
        }


        for (int i = 0; i <= 20; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/ConeheadZombie/ConeheadZombie_" + (i++) + ".png").getAbsolutePath());
            Globals.picsConheadZombie.add(temp);
        }

        for (int i = 0; i <= 10; i++) {
            PImage temp;
            temp = loadImage(new File("resources/Zombies/ConeheadZombieAttack/ConeheadZombieAttack_" + (i++) + ".png").getAbsolutePath());
            Globals.picsConheadZombieAttack.add(temp);
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
            Globals.picSun.add(loadImage(new File("resources/Sun/Sun/Sun_" + i + ".png").getAbsolutePath()));
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
    }

    @Override
    public void mouseWheel(MouseEvent event) {
        InputManager.mouseWheel(event);
    }
}