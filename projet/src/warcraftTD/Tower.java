package warcraftTD;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
	
	protected Position p;
	protected int level;
	protected int cooldown;
	protected double range;
	protected int damage;
	protected int cd;
	int pv;
	int pvMax;
	
	double squareHeight;
	double squareWidth;
	
	
	protected List<Projectile> projectiles = new ArrayList<Projectile>();
	
	
	/**
	 * Creer une tour a la position p
	 * @param p
	 */
	public Tower(Position p) {
		this.p = p;
		level = 1;
	}
	
	
	/**
	 * Affiche les points de vie de la tour
	 */
	public void draw() {
		
		if(pv < pvMax) {
			StdDraw.setPenColor(StdDraw.BLACK);
	        StdDraw.filledRectangle(p.x, p.y-0.025, 0.0275, 0.0075);
	        StdDraw.setPenColor(StdDraw.RED);
	        StdDraw.filledRectangle(p.x+pv*0.025/pvMax - 0.025, p.y-0.025, pv*0.025/pvMax, 0.005);
	     
		}
			
	}
			
	/**
	 * Affiche la tour, ses points de vie et met a jour les projectiles de la tour.
	 * @param monsters
	 */
	public void update(ArrayList<Monster> monsters) {
		if(cd<cooldown)
			cd++;
		updateProjectiles(monsters);
		draw();
	}
	
	/**
	 * Met a jour tous les projectiles de la tour.
	 * @param monsters
	 */
	public void updateProjectiles(ArrayList<Monster> monsters) {
		 for(int i=0; i<projectiles.size(); i++) {
			projectiles.get(i).update(monsters);
			if(!projectiles.get(i).moving) {
				projectiles.remove(i);
			}
		 }
	 }
	
	/**
	 * Augmente le niveau de la tour
	 */
	public void upgrade() {
		if(level < 2)
			level++;
		setStats();
	}
	
	/**
	 * Fonction abstraite traite dans les classes filles
	 * La tour tire un missile si son le temps de recharge est pret et que le monstre est dans sa portee
	 * @param m un monstre
	 */
	public abstract void shoot(Monster m) ;
	
	/**
	 * Fonction abstraite traite dans les classes filles qui met a jour les caracteristiques de la tour.
	 */
	public abstract void setStats() ;
	
	
	/**
	 * Ajoute la tour à la liste des tours si elle est sur un emplacement libre
	 * @param towers la liste des tours
	 * @return vrai si la tour est a un emplacement deja prit
	 */
	public boolean isIn(ArrayList<Tower> towers) {
		for(int i=0; i<towers.size(); i++) 
			if(towers.get(i).p.x == p.x && towers.get(i).p.y == p.y)
				return true;
			return false;
	}
		
}