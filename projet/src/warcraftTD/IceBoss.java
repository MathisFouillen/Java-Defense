package warcraftTD;

import java.util.ArrayList;

public class IceBoss extends Boss{
	
	private static ArrayList<SnowBall> snowballs = new ArrayList<SnowBall>();
	
	private class SnowBall {
		private int damage;
		private double speed;
		private Position p;
		private Position target;
		private double hitbox;
		private Tower targetedTower;
		
		/**
		 * Creer un boule de neige a la position p qui cible la tour t
		 * @param p
		 * @param t
		 */
		private SnowBall(Position p, Tower t) {
			damage = 1;
			this.p = new Position(p);
			this.target = new Position(t.p);
			this.targetedTower = t;
			speed = 0.01;
			hitbox = 0.005;
		}
		
		/**
		 * Affiche le projectile sur le plateau de jeu
		 */
		public void draw() {
			StdDraw.setPenColor(StdDraw.GRAY);
			StdDraw.filledCircle(p.x, p.y, 0.01);
		}
		
		/**
		 * Verifie que la boule de neige touche la tour t
		 * @param towers
		 * @return
		 */
		public boolean hit(ArrayList<Tower> towers) {
			boolean res = false;
			if(towers.indexOf(targetedTower) == -1)
				return false;
			if(Math.pow(target.x - p.x, 2) + Math.pow(target.y-p.y, 2) <= hitbox) {
				res = true;
				if((targetedTower.pv -= damage) <= 0) {
					towers.remove(targetedTower);
				}
			}
			return res;
		}
		
		/**
		 * Deplace la boule de neige en direction de sa tour cible
		 */
		public void move() {
			double dy = target.y - p.y;
			double dx = target.x - p.x;
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			
			if(Math.sqrt(Math.pow(target.x - p.x, 2) + Math.pow(target.y-p.y, 2)) >= hitbox) {
				p.x += ratioX * speed;
				p.y += ratioY * speed;
			}
		}
		
	}
	
	
	/**
	 * Creer un boss de glace, avec une vitesse, des points de vie et une route precise
	 * @param speed
	 * @param pv
	 * @param path
	 */
	public IceBoss(double speed, int pv, Path path) {
		super(speed, pv, path, false);
		image = "images/iceBoss.png";
		range = 0.2;
		size = 0.03;
		range = 0.3;
		cooldown = 30;
		
	}
	
	
	/**
	 * Creer un boss de glace qui possede les meme caracteristiques que le monstre en parametre
	 * @param m
	 */
	public IceBoss(Monster m) {
		super(m);
		image = "images/iceBoss.png";
		range = 0.2;
		size = 0.03;
		range = 0.2;
		cooldown = 30;
	}


	/**
	 * Met a jour le boss :
	 * 	le deplace, tire sur les tours a proximite si possible
	 * 	et le dessine sur le plateau de jeu
	 */
	public void update(ArrayList<Tower> towers) {
		cd++;
		boolean shooted = false;
		for(int i=0; i<towers.size(); i++) {
			//Tire sur toute les tours a proximite si possible
			if(cd > cooldown && Math.sqrt(Math.pow(towers.get(i).p.x - p.x, 2) + Math.pow(towers.get(i).p.y-p.y, 2)) <= range) {
				snowballs.add(new SnowBall(p, towers.get(i)));
				shooted = true;
			}
		}
		
		if(shooted)
			cd = 0;
		//Dessine et deplace les boule des neige, retire les celles arrivees a destination
		for(int i=0; i<snowballs.size(); i++) {
			snowballs.get(i).draw();
			snowballs.get(i).move();
			if(snowballs.get(i).hit(towers))
				snowballs.remove(i);
		}

		
		if(!reached && moving)
			move();
		draw();
	}
}
