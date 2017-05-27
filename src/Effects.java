
class Effects
{
	public static void applyEffect(Effect e, Zombie zombie) {
		switch (e) {
			case ICE:
				zombie.speed /= 1.3;
				break;

			case FIRE:
				zombie.hp -= 1;
				break;

			case MELLON:
				for (Zombie zo : pvz.zombies ) {
					if ( pvz.dist(zo.pos.x, zo.pos.y, zombie.pos.x, zombie.pos.y) < 100 )
						zo.receiveDamage(2);
				}
				break;
			case PARTYCRASHER:
				zombie.clonable = true;
				break;
			
			default:
				break;
		}
	}
	
		
}