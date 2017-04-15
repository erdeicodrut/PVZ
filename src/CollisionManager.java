import java.util.ArrayList;

class Pair
{
	public ICollision first;
	public ICollision second;

	public Pair(ICollision first, ICollision second)
	{
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean equals(Object other)
	{
		Pair p = (Pair) other;
		if (p == null)
			return false;

		return (first == p.first && second == p.second) ||
				(first == p.second && second == p.first);
	}
}

public abstract class CollisionManager
{
	public static ArrayList<ICollision> queue = new ArrayList<>();
	public static ArrayList<Pair> activeCollisions = new ArrayList<>();

	public static void addObject(ICollision obj) { queue.add(obj); }
	public static void removeObject(ICollision obj) {

		queue.remove(obj);

		int i = 0;
		while (i < activeCollisions.size()) {
			Pair pair = activeCollisions.get(i);
			if (pair.first == obj || pair.second == obj)
				activeCollisions.remove(pair);
			else
				i++;
		}

	}

	public static boolean isColliding(ICollision obj)
	{
		for (Pair pair : activeCollisions)
			if (obj == pair.first || obj == pair.second)
				return true;
		return false;
	}

	public static boolean isCollidingWith(ICollision obj1, ICollision obj2)
	{
		return activeCollisions.contains(new Pair(obj1, obj2));
	}

	public static boolean isCollidingWithClass(ICollision obj, Class c)
	{
		for (Pair pair : activeCollisions)
			if ((pair.first == obj && pair.second.getClass() == c) ||
					(pair.second == obj && pair.first.getClass() == c))
				return true;
		return false;
	}

	public static void resolveCollisions()
	{
		// Go until second to last
		for (int icurrent = 0; icurrent < queue.size()-1; icurrent++)
			for (int iother = icurrent+1; iother < queue.size(); iother++)
			{
				Pair pair = new Pair(queue.get(icurrent), queue.get(iother));

				if (pair.first.isCollidingWith(pair.second))
				{
					if (!activeCollisions.contains(pair))
					{
						activeCollisions.add(pair);
						pair.first.onCollisionEnterWith(pair.second);
						pair.second.onCollisionEnterWith(pair.first);
					}
					else
					{
						pair.first.onCollisionWith(pair.second);
						pair.second.onCollisionWith(pair.first);
					}

				}
				else
				if (activeCollisions.contains(pair))
				{
					activeCollisions.remove(pair);
					pair.first.onCollisionExitWith(pair.second);
					pair.second.onCollisionExitWith(pair.first);
				}
			}

	}
}
