import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.io.File;
import java.util.ArrayList;

public class Sun extends GameObject implements IDrawable, IInput
{
	private static int timer = 180;

    public boolean isGonnaDie = false;

    int animationFrame = 0;

    ArrayList<PImage> pic = new ArrayList <>();


    public Sun(PApplet p)
	{
		super(p,new PVector(p.random(p.width * 2/10, p.width * 8/10), p.random(p.height * 2/10, p.height * 8/10)),Globals.sunSize);
		InputManager.addObject(this);
		pvz.suns.add(this);
		for (int i = 0; i <= 21; i++) {
			pic.add(p.loadImage(new File("resources/Sun/Sun/Sun_" + i + ".png").getAbsolutePath()));
		}
	}


	public Sun(PApplet p, PVector pos)
	{
		super(p, pos, Globals.sunSize);
		setZ(1);

		InputManager.addObject(this);
		pvz.suns.add(this);
		for (int i = 0; i <= 21; i++) {
			pic.add(p.loadImage(new File("resources/Sun/Sun/Sun_" + i + ".png").getAbsolutePath()));
		}
	}

	@Override
	public void mousePressed(MouseEvent event)
	{
        Shop.addBallance(25);
        isGonnaDie = true;
	}

	@Override
	public void mouseReleased(MouseEvent event)
	{
		return;
	}

	@Override
	public void show()
	{
		if (animationFrame >= pic.size()) animationFrame = 0;
		p.imageMode(PConstants.CENTER);
		p.image(pic.get(animationFrame++), pos.x, pos.y);
	}

	public static void spawn() {
		if (timer-- < 0) {
			new Sun(p);
			timer = 450;
		}
	}

	@Override
	public boolean containsPoint(PVector point)
	{
		PVector bottomRightCorner = PVector.add(this.pos, this.size);
		return (point.x >= this.pos.x - size.x) && (point.x < bottomRightCorner.x) &&
				(point.y >= this.pos.y - size.y) && (point.y < bottomRightCorner.y);
	}

}
