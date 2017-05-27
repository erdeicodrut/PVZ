import processing.core.PApplet;

public class Timer {
	
	PApplet p;
	
	public Timer(PApplet p) {
		this.p = p;
	}
	
	public int get_time_in_seconds() {
		return p.frameCount / 30;
	}
	
	public int get_time_in_minutes() {
		return p.frameCount / 1800;
	}
	
	public void show() {
		p.textSize(24);
		p.text( (get_time_in_minutes() < 10 ? "0" + get_time_in_minutes() : get_time_in_minutes()) + ":" + (get_time_in_seconds() < 60 ? get_time_in_seconds() : get_time_in_seconds() - (get_time_in_minutes() * 60)), p.width - 100, 100);
	}
	

}
