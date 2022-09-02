package warcraftTD;

public class BaseMonster extends Monster {
	
	
	/**
	 * Creer un monstre de base, son apparence depend du niveau de jeu, 
	 * il suit la route passee en parametre
	 * @param path
	 * @param level
	 */
	public BaseMonster(Path path, int level) {
		super(path);
		pv = 5;
		flying = false;
		size = 0.012;
		if(level == 1)
			image = "images/baseMonster.png";
		else
			image = "images/baseMonster2.png";
	}

	/**
	 * Creer un monstre de base, son apparence depend du niveau de jeu, 
	 * il suit la route passee en parametre
	 * Il est indiquer en parametre la vitesses et les pv
	 * @param speed
	 * @param pv
	 * @param path
	 * @param level
	 */
	public BaseMonster(double speed, int pv, Path path, int level) {
		super(speed, pv, path);
		flying = false;
		size = 0.012;
		if(level == 1)
			image = "images/baseMonster.png";
		else
			image = "images/baseMonster2.png";
	}
	
	/**
	 * Creer un monstre de base, qui possede les meme caracteritiques que le monstre passe en parametre
	 * @param m
	 * @param level
	 */
	public BaseMonster(Monster m, int level) {
		super(m);
		flying = false;
		size = 0.012;
		if(level == 1)
			image = "images/baseMonster.png";
		else
			image = "images/baseMonster2.png";
		
	}
	

}