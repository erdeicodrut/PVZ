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

    public static ArrayList<Zombie> zombies = new ArrayList<>();
    public static ArrayList<Plant> plants = new ArrayList<>();

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
        Plant temp = field.at(3, 0).plantHere(new Plant(this, 10, Globals.bulletDamage, Effect.FIRE));
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

        zombies.clear();
        plants.clear();
        for (int i = 0; i < livings.size(); i++) {
            if (livings.get(i).getClass() == Zombie.class) {
                zombies.add((Zombie) livings.get(i));
            }
            if (livings.get(i).getClass() == Plant.class) {
                plants.add((Plant) livings.get(i));
            }
        }

            // Iterate 2 by 2 and check for collisions
        for (int i = 0; i < livings.size(); i++) {
            for (int j = 0; j < livings.size(); j++) {

                if (livings.get(i).collidesWith(livings.get(j))) {

                    // Check particular classes
                    if (livings.get(i).getClass() == Zombie.class && livings.get(j).getClass() == Plant.class) {
                        Zombie z = (Zombie) livings.get(i);
                        Plant p = (Plant) livings.get(j);

                        z.attack(p);
                        // z.moveB();

                    } else if (livings.get(i).getClass() == Zombie.class && livings.get(j).getClass() == Bullet.class) {
                        Zombie z = (Zombie) livings.get(i);
                        Bullet b = (Bullet) livings.get(j);

                        b.hit(z);

                    }
                } else {
                    if (livings.get(i).getClass() == Zombie.class) {
                        ((Zombie) livings.get(i)).move();
                    }
                }
            }
            /*
            So this is one of the most "treaba de carpaci" you'll ever find here
             backed up moveB that you find in the nested loops above

            You can't move the zombies otherwise,
            if you do it in the nested loop you get
            a zombie that moves with a speed equal
            to it's speed times the number of livings existent
             */
        }

        for (Plant plant : plants)
            for (Zombie zombie : zombies)
                if (plant.pos.y == zombie.pos.y) {
                    plant.shoot();
                    break;
                }

        for (Zombie zombie : zombies) {
            for(Bullet bullet : bullets) {
                if (bullet.collidesWith(zombie)) {
                    bullet.hit(zombie);
                }

            }
        }

        // GARBAGE COLLECTORS
        ArrayList<GameObject> dead = new ArrayList<>();
        for (int i = 0; i < plants.size(); i++)
            if (!plants.get(i).isAlive()) {
                dead.add(plants.get(i));
                plants.remove(i);
            }
//
        for (int i = 0; i < zombies.size(); i++)
            if (!zombies.get(i).isAlive()) {
                dead.add(zombies.get(i));
                zombies.remove(i);
            }

        livings.removeAll(dead);
//
       for (int i = 0; i < bullets.size(); i++) {
           System.out.println(bullets.get(i) + " " + bullets.get(i).hp);
           if (!bullets.get(i).isAlive() || bullets.get(i).pos.x >= width) {
               System.out.println(bullets.get(i) + " REMOVED");
               bullets.remove(i);
           }
       }


        // Move and show the bullets
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
            bullets.get(i).show();

//            if () {
//                bullets.remove(i);
////                System.out.println("removed");
//
//            }
//
        }

        // Show other creatures(plants & zombies)
        for (Living l : livings) {
            l.show();
        }

    }

    @Override public void mouseMoved(MouseEvent event) { InputManager.mouseMoved(event); }
    @Override public void mousePressed(MouseEvent event) { InputManager.mousePressed(event); }
    @Override public void mouseClicked(MouseEvent event) { InputManager.mouseClicked(event); }
    @Override public void mouseDragged(MouseEvent event) { InputManager.mouseDragged(event); }
    @Override public void mouseReleased(MouseEvent event) { InputManager.mouseReleased(event); }
    @Override public void mouseWheel(MouseEvent event) { InputManager.mouseWheel(event); }

}