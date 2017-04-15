import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

public class Sun extends GameObject implements IDrawable, IInput
{
	private static int timer = 450;

	public Sun(PApplet p)
	{
		super(p,new PVector(p.random(p.width * 2/10, p.width * 8/10), p.random(p.height * 2/10, p.height * 8/10)),Globals.sunSize);
		InputManager.addObject(this);
		pvz.suns.add(this);
	}

	@Override
	public void mousePressed(MouseEvent event)
	{
		Shop.addBallance(25);
		System.out.println("SUN CLICKED");
		pvz.suns.remove(this);
	}

	@Override
	public void show()
	{
		p.fill(255,255,0);
		p.rect(pos.x,pos.y,size.x, size.y);
	}

	public static void spawn() {
		if (timer-- < 0) {
			new Sun(p);
			timer = 450;
		}
	}
}
