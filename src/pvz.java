import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;
import java.util.ArrayList;

public class pvz extends PApplet {
    // The main grid of the game
    Field field = new Field(this, Globals.fieldPos, Globals.fieldDim);
    Shop shop;
    SimplePlant test;
    Plant test2;
    // Collections of entities
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Zombie> zombies = new ArrayList<>();
    public static ArrayList<Plant> plants = new ArrayList<>();

    public static ArrayList<Item> draggedItems = new ArrayList<>();
    public static ArrayList<Sun> suns = new ArrayList<>();


    public void settings() {
        size((int) (1600 / 2 * Globals.scale), (int) (1000 / 2 * Globals.scale));
    }

    public void setup() {
        frameRate(30);
        background(200);

        // Initializing the grid
        field.clear();

        new Sun(this);
        test = new SimplePlant(this, new PVector(300, 200));
        test.setPosition(new PVector(300, 300));
        test2 = test;
        shop = new Shop(this, new PVector(10, 15), Globals.shopSize);

        shop.addItem(new Item(this, 1, new Plant(this, 4, 1, Effect.NONE)));
        shop.addItem(new Item(this, 2, new SimplePlant(this)));
        shop.addItem(new Item(this, 40, new Plant(this, 4, 1, Effect.NONE)));
        shop.addItem(new Item(this, 50, new Plant(this, 4, 1, Effect.NONE)));
        shop.addItem(new Item(this, 25, new Plant(this, 4, 1, Effect.NONE)));
        shop.addItem(new Item(this, 10, new Plant(this, 4, 1, Effect.NONE)));
    }

    public void draw() {
        background(255);
        fill(0);

        for (Zombie z : zombies) z.update();
        for (Plant p : plants) p.update();
        for (Bullet b : bullets) b.update();

        test2.show();
        // Check collisions and clean-up
        CollisionManager.resolveCollisions();
        garbageCollector();

        Sun.spawn();




        Zombie p;
        int chance = 5;
        int r = floor(random(10));
        if (r < chance) {
            p = SimpleZombie.spawn();
        } else {
            p = FlagZombie.spawn();
        }

        if (p != null) {
            zombies.add(p);
            CollisionManager.addObject(p);
        }
        show();

    }


    private void show() {
        field.show(); // Cells

        for (Zombie z : zombies) z.show();
        for (Plant p : plants) p.show();
        for (Bullet b : bullets) b.show();

        shop.show(); // Shop and items

        for (Item draggedItem : draggedItems) draggedItem.show();
        for (Sun sun : suns) sun.show();

        for (Pair pair : CollisionManager.activeCollisions)
            System.out.println(pair.first + " " + pair.second);
        System.out.println("-------------------");
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