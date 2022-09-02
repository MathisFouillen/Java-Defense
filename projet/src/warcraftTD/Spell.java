package warcraftTD;

public abstract class Spell {
	protected Position p;
	
	/**
	 * Creer un sort a la position p
	 * @param p
	 */
	public Spell(Position p) {
		this.p = p;
	}
}