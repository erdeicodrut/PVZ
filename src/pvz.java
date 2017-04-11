import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class pvz extends PApplet
{
	// The main grid of the game
	Field field = new Field(this, Globals.fieldPos, Globals.fieldDim);

    public ArrayList<Living> livings = new ArrayList<>();

//	ArrayList<Plant> plants = new ArrayList<>();
//	ArrayList<Zombie> zombies = new ArrayList<>();
	static ArrayList<Bullet> bullets = new ArrayList<>();

	public void settings()
	{
		size(1600/2, 1000/2);
	}

	public void setup()
	{
		frameRate(30);
		background(200);

		// Initializing the grid
		field.clear();

		//
		Plant temp = field.at(3, 0).plantHere(new Plant(this,10, Effect.FIRE));
		if (temp != null)
			livings.add(temp);

		PVector zombiePos = PVector.add(Globals.fieldPos,
				new PVector((Globals.fieldDim.x-1) * Globals.cellSize.x,
						3 * Globals.cellSize.y));
		livings.add(new Zombie(this, zombiePos, Globals.zombieSize, 100, 3, 5));

//		System.out.println(field.at(3, 0).plant);
	}

	public void draw()
	{

		background(255);

		fill(0);
		field.show();

//		for (Plant plant : plants)
//        	plant.show();
//
//		for (int i = 0; i < zombies.size(); i++)
//		{
//			zombies.get(i).move();
//
//			boolean removed = false;
//			for (int j = 0; j < plants.size(); j++)
//				if (zombies.get(i).collidesWith(plants.get(j)))
//				{
//					// zombie.hp = 0;
//					//plants.get(j).hp = 0;
//					bullets.add(plants.get(j).shoot());
//					// zombies.remove(zombies.get(i));
//					plants.remove(plants.get(j));
//
//					removed = true;
//				}
//
////			if (removed)
////				continue;
//
//			zombies.get(i).show();
//		}
//
//		for (Bullet bullet : bullets)
//		{
//			bullet.move();
//			bullet.show();
//		}



        for (Living l1 : livings) {
        }

        for (int i = 0; i < livings.size(); i++) {
            for (int j = 0; j < livings.size(); j++) {

                if (livings.get(i).collidesWith(livings.get(j))) {


                    if ( livings.get(i).getClass() == Zombie.class && livings.get(j).getClass() == Plant.class ) {

                        Zombie z = (Zombie) livings.get(i);
                        Plant p = (Plant) livings.get(j);

                        System.out.println(p.hp);

                        z.attack(p);


                    } else if (livings.get(i).getClass() == Zombie.class && livings.get(j).getClass() == Bullet.class) {



                        ((Bullet) livings.get(j)).hit((Zombie) livings.get(i));
                    }
                } else { // if it doesnt collide then move else dont, look up
                    if (livings.get(i).getClass() == Zombie.class)
                        ((Zombie) livings.get(i)).move();
                }
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            System.out.println("da fasz" + i);
            bullets.get(i).move();
            bullets.get(i).show();
        }

        for (Living l : livings)
		{
            System.out.println(l.getClass());
            l.show();

        }

    }


}
