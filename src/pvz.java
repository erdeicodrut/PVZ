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
    public static ArrayList<Bullet> bullets = new ArrayList<>();

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
        Plant temp = field.at(3, 0).plantHere(new Plant(this, 10, Effect.FIRE));
        if (temp != null)
            livings.add(temp);

        PVector zombiePos = PVector.add(Globals.fieldPos,
                new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                        3 * Globals.cellSize.y));
        livings.add(new Zombie(this, zombiePos, Globals.zombieSize, 100, 3, 5));

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

        // Show the cells
        field.show();

        // Shop stuff
        shop.show();

        // Iterate 2 by 2 and check for collisions
        for (int i = 0; i < livings.size(); i++) {
            for (int j = 0; j < livings.size(); j++) {
                if (livings.get(i).collidesWith(livings.get(j))) {

                    // Check particular classes
                    if (livings.get(i).getClass() == Zombie.class && livings.get(j).getClass() == Plant.class) {
                        Zombie z = (Zombie) livings.get(i);
                        Plant p = (Plant) livings.get(j);

                        z.attack(p);
                    } else if (livings.get(i).getClass() == Zombie.class && livings.get(j).getClass() == Bullet.class) {
                        Zombie z = (Zombie) livings.get(i);
                        Bullet b = (Bullet) livings.get(j);

                        b.hit(z);
                    }

                } else {
                    // Doesn't collide, so just move the zombies
                    if (livings.get(i).getClass() == Zombie.class)
                        ((Zombie) livings.get(i)).move();
                }
            }
        }

        // Move and show the bullets
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
            bullets.get(i).show();
        }

        // Show other creatures(plants & zombies)
        for (Living l : livings) {
            l.show();
        }

        // Showing the dragged items
//        for (Item cur : draggedItems) {
//            cur.showDrag();
//        }

    }

    @Override public void mouseMoved(MouseEvent event) { InputManager.mouseMoved(event); }
    @Override public void mousePressed(MouseEvent event) { InputManager.mousePressed(event); }
    @Override public void mouseClicked(MouseEvent event) { InputManager.mouseClicked(event); }
    @Override public void mouseDragged(MouseEvent event) { InputManager.mouseDragged(event); }
    @Override public void mouseReleased(MouseEvent event) { InputManager.mouseReleased(event); }
    @Override public void mouseWheel(MouseEvent event) { InputManager.mouseWheel(event); }

}