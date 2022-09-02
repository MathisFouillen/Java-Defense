package warcraftTD;

import java.util.ArrayList;

public class Arrow extends Projectile{
	
	
	/**
	 * Créer le projectile Arrow qui prend en parametres les degats, sa position et le monstre qu'il cible
	 * @param damage
	 * @param p
	 * @param m
	 */
	public Arrow(int damage, Position p, Monster m) {
		super(damage, p, m.p);
		speed = 0.04;
		hitbox = 0.02;
	}
	
	/**
	 * Affiche le projectile sur le plateau de jeu
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(p.x, p.y, 0.01);
	}
	
	/**
	 * Si un projectile est proche d'un monstre, il lui inflige de degâts et est supprime
	 */
	public void hit(ArrayList<Monster> monsters) {
		for(int i=0; i<monsters.size(); i++) {
			if(Math.sqrt(Math.pow(monsters.get(i).p.x - p.x, 2) + Math.pow(monsters.get(i).p.y-p.y, 2)) <= hitbox + monsters.get(i).size) {
				moving = false;
				monsters.get(i).pv -= damage;
				break;
			}
		}
	}
	
}