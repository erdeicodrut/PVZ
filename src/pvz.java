import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class pvz extends PApplet
{
<<<<<<< HEAD
	Field field = new Field(this, 40/2, 200/2,6, 9);
=======
	// The main grid of the game
	Field field = new Field(this, Globals.fieldPos, Globals.fieldDim);

	ArrayList<Living> livings = new ArrayList<>();

	ArrayList<Plant> plants = new ArrayList<>();
	ArrayList<Zombie> zombies = new ArrayList<>();
	ArrayList<Bullet> bullets = new ArrayList<>();
>>>>>>> processing

	public void settings()
	{
		size(1600/2, 1000/2);
	}

	public void setup()
	{
		frameRate(30);
		background(200);
<<<<<<< HEAD
		field.clear();
=======

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
>>>>>>> processing
	}

	public void draw()
	{
		background(255);

		fill(0);
<<<<<<< HEAD
        field.show();
=======
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

		for (Living l1 : Globals.livings)
			for (Living l2 : Globals.livings)
			{
				if (l1.collidesWith(l2))
				{
					if (l1.getClass() == Zombie.class && l2.getClass() == Plant.class)
					{
						Zombie z = (Zombie) l1;
						Plant p = (Plant) l2;

						z.attack(p);
						p.shoot();
					}

					else if (l1.getClass() == Zombie.class && l2.getClass() == Bullet.class)
					{

					}
				}
			}

		for (Living l : livings)
		{
			if (l.getClass() == Zombie)

			l.show();
		}

>>>>>>> processing
	}
}
