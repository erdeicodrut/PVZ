class Movable
{
	constructor(x, y, width, height, hp)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.hp = hp;
	}

	this.setPosition(x, y)
	{
		this.x = x;
		this.y = y;
	}

	this.collidesWith(other)
	{
		if (x < other.x + other.width &&
   			x + width > other.x &&
   			y < other.y + other.height &&
   			height + y > other.y)
			return true;
		return false;
	}

	this.isAlive() {
        if (this.hp <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
