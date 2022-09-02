package warcraftTD;


public class ArrowTower extends Tower {
	
	private static int cost = 50;
	static double squareHeight;
	static double squareWidth;

	
	/**
	 * Creer un objet ArrowTower, sous-classe de Tower, prend en parametre sa position
	 * @param p
	 */
	public ArrowTower(Position p) {
		super(p);
		setStats();
	}
	
	/**
	 * @return le prix d'une ArrowTower (elle ont toute le meme)
	 */
	public static int getCost() {
		return cost;
	}
	
	/**
	 * Met a jour les caracteritiques de la tour
	 */
	public void setStats() {
		switch(level) {
		case 1:
			cooldown = 15;
			range = 0.3;
			damage = 2;
			pvMax = 20;
			break;
		case 2:
			cooldown = 13;
			range = 0.4;
			damage = 3;
			pvMax = 25;
			break;
		}
		pv = pvMax;
		
	}

	/**
	 * Si un ennemi est a proximite, la tour lui tire dessus
	 */
	public void shoot(Monster m) {
		if(Math.sqrt(Math.pow(m.p.x - p.x, 2) + Math.pow(m.p.y-p.y, 2)) <= range+(m.size/2) && cd >= cooldown) {
			cd = 0;
			projectiles.add(new Arrow(damage, new Position(p), m));
		}
	}
	
	/**
	 * Affiche la tour sur le plateau de jeu
	 */
	public void draw() {
		if(level == 1)
			StdDraw.picture(p.x, p.y, "images/arrowTower.png", squareHeight, squareWidth);
		if(level == 2)
			StdDraw.picture(p.x, p.y, "images/arrowTower2.png", squareHeight, squareWidth);
		super.draw();
	}
	
}