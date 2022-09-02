package warcraftTD;

import java.util.ArrayList;

public class FireBall extends Projectile{
	
	private double aoe;
	

	/**
	 * Creer une boule de feu, en specifiant ses degats, sa position
	 * 	et le monstre qu'il prend pour cible
	 * @param damage
	 * @param p
	 * @param monster
	 */
	public FireBall(int damage, Position p, Monster monster) {
		super(damage, p, monster.p);
		speed = 0.04;
		hitbox = 0.05;
		aoe = 0.075;
		
	}
	
	/**
	 * Affiche le projectile sur le plateau de jeu
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledCircle(p.x, p.y, 0.01);
	}
	
	/**
	 * Si un projectile est proche d'un monstre, il lui inflige de degats de zone et est supprime
	 */
	public void hit(ArrayList<Monster> monsters) {
		for(int i=0; i<monsters.size(); i++) {
			if(Math.sqrt(Math.pow(monsters.get(i).p.x - p.x, 2) + Math.pow(monsters.get(i).p.y-p.y, 2)) <= hitbox + monsters.get(i).size)
				moving = false;
			//a l'impact, effectue des degats de zone
			if(!moving && Math.sqrt(Math.pow(monsters.get(i).p.x - p.x, 2) + Math.pow(monsters.get(i).p.y-p.y, 2)) <= aoe + monsters.get(i).size)
				monsters.get(i).pv -= damage;
		}
		
	}
}