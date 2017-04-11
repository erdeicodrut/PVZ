import processing.core.PApplet;

public class pvz extends PApplet
{
	Field field = new Field(this, 40/2, 200/2,6, 9);

	public void settings()
	{
		size(1600/2, 1000/2);
	}

	public void setup()
	{
		background(200);
		field.clear();
	}

	public void draw()
	{
		fill(0);
        field.show();
	}
}
