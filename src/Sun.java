import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class Sun extends  GameObject implements IDrawable, IInput
{
	public Sun(PApplet p)
	{
		super(p,new PVector(p.random(1000), p.random(600)),Globals.sunSize);
		InputManager.addObject(this);
	}

	@Override
	public void mousePressed(MouseEvent event)
	{
		Shop.addBallance(25);
		System.out.println("SUN CLICKED");
	}

	@Override
	public void show()
	{
		p.fill(255,255,0);
		p.ellipse(pos.x,pos.y,size.x, size.y);
	}
}
