class Movable
{
	constructor(x, y, width, height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	setPosition(x, y)
	{
		this.x = x;
		this.y = y;
	}

	collidesWith(other)
	{
		if (x < other.x + other.width &&
   			x + width > other.x &&
   			y < other.y + other.height &&
   			height + y > other.y)
			return true;
		return false;
	}
}