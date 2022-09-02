package warcraftTD;

public class FlyingMonster extends Monster {
	
	
	/**
	 * Creer un monstre volant, son apparence depend du niveau de jeu, 
	 * il suit la route passee en parametre
	 * @param path
	 * @param level
	 */
	public FlyingMonster(Path path, int level){
		super(path);
		flying = true;
		if(level == 1)
			image = "images/flyingMonster.png";
		else
			image = "images/flyingMonster2.png";
	}
	
	/**
	 * Creer un monstre volant, son apparence depend du niveau de jeu, 
	 * il suit la route passee en parametre
	 * Sa vitesse et ses points de vue sont specifie en parametre
	 * @param speed
	 * @param pv
	 * @param path
	 * @param level
	 */
	public FlyingMonster(double speed, int pv, Path path, int level) {
		super(speed, pv, path);
		flying = true;
		if(level == 1)
			image = "images/flyingMonster.png";
		else
			image = "images/flyingMonster2.png";
	}
	
	/**
	 * Creer un monstre volant qui possede les meme caracteritiques
	 * 	que le monstre en parametre
	 * @param m
	 * @param level
	 */
	public FlyingMonster(Monster m, int level) {
		super(m);
		flying = true;
		if(level == 1)
			image = "images/flyingMonster.png";
		else
			image = "images/flyingMonster2.png";
	}
	
	
}