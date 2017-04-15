import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.util.ArrayList;

public class pvz extends PApplet {
    // The main grid of the game
    Field field = new Field(this, Globals.fieldPos, Globals.fieldDim);
    Shop shop;

    // Collections of entities
    public static ArrayList<Living> livings = new ArrayList<>();
    public static ArrayList<Item> draggedItems = new ArrayList<>();

    public void settings() {
        size((int) ( 1600 / 2 * Globals.scale), (int) (1000 / 2 * Globals.scale));
    }

    public void setup() {
        frameRate(30);
        background(200);

        // Initializing the grid
        field.clear();

        // Debugging
        Plant temp = field.at(3, 0).plantHere(new Plant(this, 3, Globals.bulletDamage, Effect.FIRE));
        Plant temp1 = field.at(3, 1).plantHere(new Plant(this, 3, Globals.bulletDamage, Effect.FIRE));
//        if (temp != null)
//            livings.add(temp);
//            livings.add(temp1);

        PVector zombiePos = PVector.add(Globals.fieldPos,
                                        new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                                        3 * Globals.cellSize.y));

        new Zombie(this, zombiePos, Globals.zombieSize, 10, 3, 5);

        shop = new Shop(this, new PVector(10, 15), Globals.shopSize);
        shop.addItem(new Item(this, 12, temp));
        shop.addItem(new Item(this, 35, temp));
        shop.addItem(new Item(this, 40, temp));
        shop.addItem(new Item(this, 50, temp));
        shop.addItem(new Item(this, 25, temp));
        shop.addItem(new Item(this, 10, temp));
    }

    public void draw() {
        background(255);
        fill(0);

        // Update
        for (int i = 0; i < livings.size(); i++) {
            livings.get(i).update();
        }

        // Check collisions and clean-up
        CollisionManager.resolveCollisions();
        garbageCollector();

        // Show
        field.show(); // Cells

        for (Living living : livings) {
//            System.out.println(living);
            living.show();
        }

        shop.show(); // Shop and items

        for (Item draggedItem : draggedItems) {
            draggedItem.show();
        }
    }

    private void garbageCollector() {
        int i = 0;
        while (i < livings.size()) {
            if (!livings.get(i).isAlive()) {
                // Debugging
                if (livings.get(i).getClass() == Zombie.class) {
                    PVector zombiePos = PVector.add(Globals.fieldPos,
                            new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                                    3 * Globals.cellSize.y));
                    Zombie zombie = new Zombie(this, zombiePos, Globals.zombieSize, 10, 3, 5);
                }

                Living removed = livings.remove(i);
                CollisionManager.removeObject(removed);
            }
            else
                i++;
        }
    }

    @Override public void mouseMoved(MouseEvent event) { InputManager.mouseMoved(event); }
    @Override public void mousePressed(MouseEvent event) { InputManager.mousePressed(event); }
    @Override public void mouseClicked(MouseEvent event) { InputManager.mouseClicked(event); }
    @Override public void mouseDragged(MouseEvent event) { InputManager.mouseDragged(event); }
    @Override public void mouseReleased(MouseEvent event) { InputManager.mouseReleased(event); }
    @Override public void mouseWheel(MouseEvent event) { InputManager.mouseWheel(event); }
}