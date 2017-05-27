import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;

public class DnaDirectoare extends Zombie {

    int attackCounter = 0;
    PImage img;

    public DnaDirectoare (PApplet p, PVector pos) {

        super(p, pos, Globals.zombieSize, 550, Globals.speed, 50);
        img = p.loadImage(new File("resources/Zombies/Directoare/8_doamnadirectoare.png").getAbsolutePath());
    }

    public static DnaDirectoare spawn() {
        if (timerSpawn-- == 0)
        {
            PVector zombiePos = PVector.add(Globals.fieldPos,
                    new PVector((Globals.fieldDim.x - 1) * Globals.cellSize.x,
                            p.floor(p.random(5f)) * Globals.cellSize.y));

            resetSpawn();

            return new DnaDirectoare(p, zombiePos);

        }
        return null;
    }

    public void attack(Plant other) {

        if (attackCounter == 5) {

            float stok = 0;

            for (float i = pos.y; i < 0; i--) {
                for (Plant pl : pvz.plants) {
                    if (i == pl.pos.y) {
                        stok = i;
                        break;
                    }
                }
                if (stok != 0) break;
            }

            for (Plant pl : pvz.plants) {
                if (stok == pl.pos.y) {
                    pl.hp -= 50;
                }

            }

            attackCounter = -1;
        }

        if (timer-- < 0) {
            if (other.hp > 0) {
                other.hp -= damage;
            }
            timer = 30;

            attackCounter ++;
        }
    }
	
    
    
    public void show() {
        p.imageMode(PConstants.CORNER);
        p.image(img, pos.x, pos.y, img.width/7, img.height/7);
    }

}
