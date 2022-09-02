package warcraftTD;

import java.util.ArrayList;

public class Heal extends Spell{

	private static double range = 0.2;
	private int heal;
	private static int cost = 100;

	/**
	 * Creer un sort de soin a la position p
	 * @param p
	 */
	public Heal(Position p) {
		super(p);
		heal = 5;
	}
	
	/**
	 * @return le prix d'un sort de soin
	 */
	public static int getCost() {
		return cost;
	}
	
	/**
	 * @return la portee du sort de soin
	 */
	public static double getRange() {
		return range;
	}
	
	/**
	 * Soigne toute les tours qui sont dans le rayon d'action du sort
	 * @param towers
	 */
	public void heal(ArrayList<Tower> towers) {
		for(int i=0; i<towers.size(); i++) {
			if(Math.sqrt(Math.pow(towers.get(i).p.x - p.x, 2) + Math.pow(towers.get(i).p.y-p.y, 2)) <= range + towers.get(i).squareWidth/2)
				towers.get(i).pv = Math.min(towers.get(i).pvMax, towers.get(i).pv + heal);
		}
	}
	
}