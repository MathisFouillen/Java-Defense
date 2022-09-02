package warcraftTD;

import java.util.ArrayList;

public class Boss extends Monster{
	protected boolean moving = true;
	protected double range = 0.125;
	protected int cooldown = 15;
	protected int cd;
	
	
	/**
	 * Creer un boss, prend en parametre sa vitesse, ses points de vie, la route qu'il suit et s'il vole ou non
	 * @param speed
	 * @param pv
	 * @param path
	 * @param flying
	 */
	public Boss(double speed, int pv, Path path, boolean flying) {
		super(speed, pv, path);
		this.flying = flying;
		size = 0.03;
		if(flying)
			image = "images/flyingBoss.png";
		else
			image = "images/baseBoss.png";
	}
	
	/**
	 * Creer un boss qui possede les meme caracteritiques que le monstre en parametre
	 * @param m
	 */
	public Boss(Monster m) {
		super(m);
		flying = m.flying;
		size = 0.03;
		if(flying)
			image = "images/flyingBoss.png";
		else
			image = "images/baseBoss.png";
	}
	
	/**
	 * Affiche et deplace le Boss, si il est a proximite d'une tour, il s'arrete pour lui infliger des degats
	 */
	public void update(ArrayList<Tower> towers) {
		moving = true;
		cd ++;
		for(int i=0; i<towers.size(); i++) {
			if(Math.sqrt(Math.pow(towers.get(i).p.x - p.x, 2) + Math.pow(towers.get(i).p.y-p.y, 2)) <= range) {
				moving = false;
				if(cd > cooldown) {
					cd = 0;
					if(--towers.get(i).pv == 0)
						towers.remove(i);
				}
			}
		}
		if(!reached && moving)
			move();
		draw();
	}

}