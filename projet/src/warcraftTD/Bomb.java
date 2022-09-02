package warcraftTD;

import java.util.ArrayList;

public class Bomb extends Projectile{
	
	private double aoe;

	/**
	 * Cr�er le projectile Arrow qui prend en parametres les degats, sa position et le monstre qu'il cible
	 * @param damage
	 * @param p
	 * @param monster
	 */
	public Bomb(int damage, Position p, Monster monster) {
		super(damage, p, monster.p);
		speed = 0.02;
		hitbox = 0.05;
		aoe = 0.1;
		
	}
	
	/**
	 * Affiche le projectile sur le plateau de jeu
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(p.x, p.y, 0.01);
	}
	
	/**
	 * Si un projectile est proche d'un monstre, il lui inflige de degats de zone et est supprime
	 */
	public void hit(ArrayList<Monster> monsters) {
		for(int i=0; i<monsters.size(); i++) {
			if(!(monsters.get(i).flying) && Math.sqrt(Math.pow(monsters.get(i).p.x - p.x, 2) + Math.pow(monsters.get(i).p.y-p.y, 2)) <= hitbox + monsters.get(i).size)
				moving = false;
			//a l'impact, la bombe effectue des degats de zone
			if(!moving && Math.sqrt(Math.pow(monsters.get(i).p.x - p.x, 2) + Math.pow(monsters.get(i).p.y-p.y, 2)) <= aoe + monsters.get(i).size && !(monsters.get(i).flying))
				monsters.get(i).pv -= damage;
		}
		
	}
}