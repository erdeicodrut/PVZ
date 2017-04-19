import processing.core.PImage;
import java.util.ArrayList;

public class Animations extends pvz{

    static PImage temp;
    ArrayList<PImage> Zombie = new ArrayList<>();

    public void loadImages() {
        temp = loadImage("resources/Zombies/Zombie");
    }

}
