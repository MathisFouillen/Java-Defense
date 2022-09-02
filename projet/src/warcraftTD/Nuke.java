package warcraftTD;

import java.util.ArrayList;

public class Nuke extends Spell{
	
	private static double range = 0.1;
	private int damage;
	private static int cost = 100;
	
	/**
	 * Creer une bombe nucléaire a la position p
	 * @param p
	 */
	public Nuke(Position p) {
		super(p);
		damage = 30;
	}
	
	/**
	 * @return le prix d'une bombe nucléaire
	 */
	public static int getCost() {
		return cost;
	}
	
	/**
	 * @return la portee des bombes nucléaires
	 */
	public static double getRange() {
		return range;
	}
	

	/**
	 * Touche les mosntres dans le rayon d'action du sort
	 * @param monsters
	 */
	public void hit(ArrayList<Monster> monsters) {
		for(int i=0; i<monsters.size(); i++) {
			if(Math.sqrt(Math.pow(monsters.get(i).p.x - p.x, 2) + Math.pow(monsters.get(i).p.y-p.y, 2)) <= range)
				monsters.get(i).pv -= damage;
		}
		
	}

}
