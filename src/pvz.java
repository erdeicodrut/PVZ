import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;

public class pvz extends PApplet
{
	Field field = new Field(this, 40/2, 200/2,5, 8);

	ArrayList<Plant> plants = new ArrayList<>();

	public void settings()
	{
		size(1600/2, 1000/2);
	}

	public void setup()
	{
		background(200);
		field.clear();

		Plant temp = field.matrix[3][0].place(new Plant(this, field.matrix[3][0].x , field.matrix[3][0].y,10, Effect.FIRE));
		if (temp != null)
			plants.add(temp);

		System.out.println(field.matrix[0][0].plant);
	}

	public void draw()
	{
		background(255);

		fill(0);
		field.show();

		for (Plant plant : plants)
        	plant.show();
	}
}
