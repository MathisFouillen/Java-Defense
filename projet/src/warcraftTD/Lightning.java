package warcraftTD;

import java.util.ArrayList;

public class Lightning extends Projectile {
	
	public class SlowInformation {
		public Monster m;
		public double originalSpeed;
		public int timer;

		
		/**
		 * Sauvegarde la vitesse du monstre m
		 * @param m
		 * @param slow
		 */
		private SlowInformation(Monster m) {
			this.m = m;
			originalSpeed = m.speed;
			timer = 0;
		}

	}
	

	private double slow;

	
	/**
	 * Creer le projectile eclair a la position p, il est precise ses degats, sa position,
	 * 	le monstre qu'il prend pour cible et sa puissance de ralentissement
	 * @param damage
	 * @param p
	 * @param m
	 * @param slow
	 */
	public Lightning(int damage, Position p, Monster m, double slow) {
		super(damage, p, m.p);
		speed = 0.04;
		hitbox = 0.02;
		this.slow = slow;
	}

	/**
	 * Affiche le projectile sur le plateau de jeu
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledCircle(p.x, p.y, 0.01);
	}

	/**
	 * Si une ennemi est touche par le projectile, celui-ci est ralentit pendant un certain temps
	 */
	public void hit(ArrayList<Monster> monsters) {
		boolean hitable = true;
		for (int i = 0; i < monsters.size(); i++) {
			if (Math.sqrt(Math.pow(monsters.get(i).p.x - p.x, 2) + Math.pow(monsters.get(i).p.y - p.y, 2)) <= hitbox + monsters.get(i).size) {
				moving = false;
				for (int j = 0; j < LightningTower.slowedMonsters.size(); j++)
					if(LightningTower.slowedMonsters.get(j).m == monsters.get(i)) {
						hitable = false;
						//si le mosntre est deja ralentit, reinitialise la duree du malus
						LightningTower.slowedMonsters.get(j).timer = 0;
					}
				
				//ralentit le monstre touche (l'ajoute a la liste des monstres ralentis)
				if(hitable) {
					LightningTower.slowedMonsters.add(new SlowInformation(monsters.get(i)));
					monsters.get(i).speed *= (1-slow);
					break;
				}
			}
		}
	}

}