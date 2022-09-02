package warcraftTD;

public class CannonTower extends Tower {
	
	private static int cost = 60;
	static double squareHeight;
	static double squareWidth;
	
	/**
	 * Creer une CannonTower a la position p
	 * @param p
	 */
	public CannonTower(Position p) {
		super(p);
		setStats();
	}
	
	/**
	 * @return le prix des CannonTower
	 */
	public static int getCost() {
		return cost;
	}
	
	/**
	 * Met a jour les caracteristiques de la tour
	 */
	public void setStats() {
		switch(level) {
		case 1:
			cooldown = 20;
			range = 0.2;
			damage = 8;	
			pvMax = 20;
			break;
		case 2:
			cooldown = 18;
			range = 0.3;
			damage = 10;
			pvMax = 20;
			break;
		}
		pv = pvMax;
		cd = cooldown;
	}
	
	/**
	 * Affiche la tour sur le plateau de jeu
	 */
	public void draw() {
		if(level == 1)
			StdDraw.picture(p.x, p.y, "images/cannonTower.png", squareHeight, squareWidth);
		if(level == 2)
			StdDraw.picture(p.x, p.y, "images/cannonTower2.png", squareHeight, squareWidth);
		super.draw();
	}
	
	/**
	 * Si un ennemi est a proximite, la tour lui tire dessus
	 */
	public void shoot(Monster m) {
		if(Math.sqrt(Math.pow(m.p.x - p.x, 2 ) + Math.pow(m.p.y-p.y, 2)) <= range+(m.size/2) && ! m.reached && cd == cooldown && !m.flying)  {
			cd=0;
			projectiles.add(new Bomb(damage, new Position(p), m));

		}
	}
}