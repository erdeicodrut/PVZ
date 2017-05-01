import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class pvz extends PApplet {
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

        shop.addItem(new Item(this, 2, tempFire));
        shop.addItem(new Item(this, 2, tempSimpe));
        shop.addItem(new Item(this, 1, tempSun));
        shop.addItem(new Item(this, 1, tempWall));
        shop.addItem(new Item(this, 2, tempSnow));
        shop.addItem(new Item(this, 3, tempMello));
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

            // Check collisions and clean-up
            CollisionManager.resolveCollisions();
            garbageCollector();

            Sun.spawn();
            shovel.show();

            int chance = 5;
            int r = floor(random(10));
            if (r < chance - 2) {
                p = SimpleZombie.spawn();
            } else if (r == chance) {
                p = FlagZombie.spawn();
            } else if (r > chance + 2) {
                p = BucketheadZombie.spawn();
            } else if (r > chance) {
                p = ConeheadZombie.spawn();
            } else
                p = PoleVaultingZombie.spawn();

            if (p != null) {
                zombies.add(p);
                CollisionManager.addObject(p);
            }
            show();
        } else {
            imageMode(CORNER);
            image(loadImage(new File("resources/Background/lost.jpg").getAbsolutePath()), 0, 0);
            textSize(50);
            text("Game Over", width/2, height/2);
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
                removed = null;
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