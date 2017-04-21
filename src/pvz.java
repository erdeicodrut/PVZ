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
    Zombie p;
    PImage back;

    // Collections of entities
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Zombie> zombies = new ArrayList<>();
    public static ArrayList<Plant> plants = new ArrayList<>();

    public static ArrayList<Item> draggedItems = new ArrayList<>();
    public static ArrayList<Sun> suns = new ArrayList<>();

    public void settings() {
        back = loadImage(new File("resources/Background/PVZBackground_3.jpg").getAbsolutePath());
        size((int) (back.width), (int) (back.height));
    }

    public void setup() {
        frameRate(30);

        // Initializing the grid
        field.clear();

        new Sun(this);
        shop = new Shop(this, new PVector(10, 15), Globals.shopSize);

        shop.addItem(new Item(this, 2, new FirePea(this)));
        shop.addItem(new Item(this, 2, new SimplePlant(this)));
        shop.addItem(new Item(this, 1, new Sunflower(this)));
        shop.addItem(new Item(this, 1, new WallNut(this)));
        shop.addItem(new Item(this, 2, new SnowPea(this)));
        shop.addItem(new Item(this, 3, new MellonPea(this)));
    }

    public void draw() {
        fill(0);
        background(255);
        imageMode(CORNER);
        image(back, 0, 0);


        for (Zombie z : zombies) z.update();
        for (Plant p : plants) p.update();
        for (Bullet b : bullets) b.update();

        // Check collisions and clean-up
        CollisionManager.resolveCollisions();
        garbageCollector();

        Sun.spawn();

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