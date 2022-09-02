package warcraftTD;

import java.util.ArrayList;
import java.util.List;

public class LightningTower extends Tower{
	
	private static int cost = 30;
	private double slow;
	public static List<Lightning.SlowInformation> slowedMonsters = new ArrayList<Lightning.SlowInformation>();
	private int duration;
	
	static double squareHeight;
	static double squareWidth;
	

	/**
	 * Creer une LightingTower a la position p
	 * @param p
	 */
	public LightningTower(Position p) {
		super(p);
		setStats();
	}
	
	/**
	 * @return le prix d'une LightningTower
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
			cooldown = 30;
			range = 0.3;
			slow = 0.3;
			pvMax = 25;
			duration = 20;
			break;
		case 2:
			cooldown = 27;
			range = 0.4;
			slow = 0.4;
			pvMax = 30;
			duration = 23;
			break;
		}
		pv = pvMax;
	}
	
	/**
	 * Met a jour la tour, est rend leur vitesse d'origine au monstres ralentit depuis suffisament longtemps
	 */
	public void update(ArrayList<Monster> monsters) {
		super.update(monsters);
		for(int i=0; i<slowedMonsters.size(); i++) {
			if(slowedMonsters.get(i).timer++ >= duration) {
				slowedMonsters.get(i).m.speed = slowedMonsters.get(i).originalSpeed;
				slowedMonsters.remove(i);
			}
			
			
			
		}
	}
	
	/**
	 * Tire sur les monstres a proximite de la tour
	 */
	public void shoot(Monster m) {
		if(Math.sqrt(Math.pow(m.p.x - p.x, 2) + Math.pow(m.p.y-p.y, 2)) <= range+(m.size/2) && cd >= cooldown) {
			cd = 0;
			projectiles.add(new Lightning(damage, new Position(p), m, slow));
		}
	}
	
	/**
	 * Affiche la tour sur le plateau de jeu
	 */
	public void draw() {
		if(level == 1)
			StdDraw.picture(p.x, p.y, "images/lightningTower.png", squareHeight, squareWidth);
		if(level == 2)
			StdDraw.picture(p.x, p.y, "images/lightningTower2.png", squareHeight, squareWidth);
		super.draw();
	}

}