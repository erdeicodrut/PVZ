import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class pvz extends PApplet {
	// The main grid of the game
	Field field = new Field(this, Globals.fieldPos, Globals.fieldDim);

	// Collections of entities
    public static ArrayList<Living> livings = new ArrayList<>();
	public static ArrayList<Bullet> bullets = new ArrayList<>();

	public void settings() {
		size(1600/2, 1000/2);
	}

	public void setup() {
		frameRate(30);
		background(200);

		// Initializing the grid
		field.clear();

		// Debugging
		Plant temp = field.at(3, 0).plantHere(new Plant(this,10, Effect.FIRE));
		if (temp != null)
			livings.add(temp);

		PVector zombiePos = PVector.add(Globals.fieldPos,
				new PVector((Globals.fieldDim.x-1) * Globals.cellSize.x,
						3 * Globals.cellSize.y));
		livings.add(new Zombie(this, zombiePos, Globals.zombieSize, 100, 3, 5));
	}

	public void draw() {
		background(255);
		fill(0);

		// Show the cells
		field.show();

		// Iterate 2 by 2 and check for collisions
        for (int i = 0; i < livings.size(); i++) {
            for (int j = 0; j < livings.size(); j++) {
                if (livings.get(i).collidesWith(livings.get(j))) {

					// Check particular classes
                    if ( livings.get(i).getClass() == Zombie.class && livings.get(j).getClass() == Plant.class ) {
                        Zombie z = (Zombie) livings.get(i);
                        Plant p = (Plant) livings.get(j);

                        z.attack(p);
                    }
                    else if (livings.get(i).getClass() == Zombie.class && livings.get(j).getClass() == Bullet.class) {
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

    }


}
