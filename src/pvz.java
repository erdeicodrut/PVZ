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
    public static ArrayList<Sun> suns = new ArrayList<>();


    public void settings() {
        size((int) ( 1600 / 2 * Globals.scale), (int) (1000 / 2 * Globals.scale));
    }

    public void setup() {
        frameRate(30);
        background(200);

        // Initializing the grid
        field.clear();

        new Sun(this);

        shop = new Shop(this, new PVector(10, 15), Globals.shopSize);

        shop.addItem(new Item(this, 1, new Plant(this, 4, 1, Effect.NONE)));
        shop.addItem(new Item(this, 35, new Plant(this, 4, 1, Effect.NONE)));
        shop.addItem(new Item(this, 40, new Plant(this, 4, 1, Effect.NONE)));
        shop.addItem(new Item(this, 50, new Plant(this, 4, 1, Effect.NONE)));
        shop.addItem(new Item(this, 25, new Plant(this, 4, 1, Effect.NONE)));
        shop.addItem(new Item(this, 10, new Plant(this, 4, 1, Effect.NONE)));
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

	    Sun.spawn();
	    Zombie.spawn();

        show();
    }

    private void show() {
	    field.show(); // Cells


	    for (Living living : livings) {
		    living.show();
	    }

	    shop.show(); // Shop and items

	    for (Item draggedItem : draggedItems) {
		    draggedItem.show();
	    }

	    for (Sun sun : suns) {
		    sun.show();
	    }
    }

    private void garbageCollector() {
        int i = 0;
        while (i < livings.size()) {
            if (!livings.get(i).isAlive()) {

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