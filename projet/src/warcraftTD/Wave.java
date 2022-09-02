package warcraftTD;

import java.util.ArrayList;
import java.util.List;

public class Wave {
	
	private int frequence;
	private boolean waiting = true;
	private int endGold;
	
	private Path path;
	
	private List<Monster> monsters = new ArrayList<Monster>();
	
	
	
	/**
	 * Creer une liste de monstre, correspondant a une vague
	 * @param number le numero de la vague
	 * @param spawn la position a laquelle apparaisse les monstres
	 * @param path le chemins que doivent suivre les monstres
	 */
	public Wave(int number, Path path, int level) {
		this.path = path;
		if(level == 1)
			buildLevelOneWave(number);
		if(level == 2)
			buildLevelTwoWave(number);
	}
	
	/**
	 * @return la frequence d'apparition des monstres
	 */
	public int getFrequence() {
		return frequence;
	}
	
	/**
	 * @return si une vague est en preparation
	 */
	public boolean isWaiting() {
		return waiting;
	}
	
	/**
	 * @return l'or obtenue en cas de victoire contre la vague
	 */
	public int getEndGold() {
		return endGold;
	}
	
	/**
	 * @return la liste des monstres de la vague
	 */
	public List<Monster> getMonsters() {
		return monsters;
	}	
	
	/**
	 * Precise si le jeu est en attente de la prochaine vague ou non
	 * @param waiting
	 */
	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}
	

	
	/**
	 * Ajoute des monstres a la vague
	 * @param m un monstre
	 * @param lenght nombre de monstre a ajouter
	 * @param level le niveau
	 */
	public void add(Monster m, int lenght, int level) {
		for(int i=0; i<lenght; i++) {
			if(m instanceof BaseMonster)
				monsters.add(new BaseMonster(m, level));
			if(m instanceof FlyingMonster)
				monsters.add(new FlyingMonster(m, level));
			if (m instanceof IceBoss)
				monsters.add(new IceBoss(m));
			else if(m instanceof Boss)
				monsters.add(new Boss(m));
		}

	}

	
	/**
	 * genere la vague numero number du niveau 1 
	 * @param number le numero de la vague
	 */
	public void buildLevelOneWave(int number){
		Monster m;
			switch(number) {
			case 1:
				frequence = 1500;
				add(new BaseMonster(0.005, 5, path, 1), 10, 1);
				endGold = 10;
				break;
			
			case 2:
				frequence = 750;
				add(new BaseMonster(0.005, 5, path, 1), 10, 1);
				endGold = 20;
				break;
			
			case 3:
				frequence = 1000;
				add(new FlyingMonster(0.01, 2, path, 1), 7, 1);
				endGold = 30;
				break;
			
			
			case 4 :
				frequence = 1000;
				add(new BaseMonster(0.005, 5, path, 1), 3, 1);
				add(new FlyingMonster(0.01, 2, path, 1), 3, 1);
				add(new BaseMonster(0.005, 9, path, 1), 3, 1);
				add(new FlyingMonster(0.01, 5, path, 1), 3,  1);
				endGold = 50;
				break;
				
			
			case 5:
				m = (new Boss(0.001, 500, path, false));
				m.killGold = 20;
				monsters.add(m);
				endGold = 10;
				break;
				
			case 6:
				frequence = 500;
				add(new FlyingMonster(0.01, 5, path, 1), 15, 1);
				endGold = 30;
				break;
			
			case 7:
				frequence = 500;
				add(new BaseMonster(0.01, 10, path, 1), 15, 1);
				m = (new Boss(0.003, 200, path, false));
				m.killGold = 20;
				monsters.add(m);
				endGold = 35;
				break;
				
			case 8:
				frequence = 500;
				add(new FlyingMonster(0.01, 15, path, 1),15, 1);
				add(new BaseMonster(0.01, 10, path, 1), 15, 1);
				endGold = 50;
				break;
				
			case 9:
				frequence = 400;
				add(new FlyingMonster(0.015, 12, path, 1), 15, 1);
				add(new BaseMonster(0.01, 15, path, 1), 15, 1);
				add(new FlyingMonster(0.015, 12, path, 1), 5, 1);
				add(new BaseMonster(0.01, 20, path, 1), 3, 1);
				m = (new Boss(0.003, 350, path, false));
				m.killGold = 25;
				monsters.add(m);
				endGold = 30;
				break;
				
			case 10:
				m = (new Boss(0.002, 1000, path, true));
				m.killGold = 20;
				monsters.add(m);
				endGold = 50;
				break;
				
				
			case 11:
				frequence = 300;
				add(new FlyingMonster(0.015, 15, path, 1), 15, 1);
				add(new BaseMonster(0.01, 18, path, 1), 15, 1);
				add(new FlyingMonster(0.015, 15, path, 1), 15, 1);
				add(new BaseMonster(0.015, 23, path, 1), 15, 1);
				m = (new Boss(0.001, 250, path, true));
				m.killGold = 10;
				monsters.add(m);
				endGold = 30;
				break;
				
			case 12:
				frequence = 400;
				add(new FlyingMonster(0.015, 15, path, 1), 12, 1);
				add(new BaseMonster(0.015, 25, path, 1), 15, 1);
				m = (new Boss(0.0015, 300, path, true));
				m.killGold = 60;
				monsters.add(m);
				endGold = 30;
				break;
				
			case 13:
				frequence = 400;
				add(new FlyingMonster(0.015, 20, path, 1), 20 ,1);
				add(new BaseMonster(0.015, 25, path, 1), 20, 1);
				endGold = 35;
				break;
				
			case 14:
				frequence = 350;
				add(new FlyingMonster(0.015, 25, path, 1), 25, 1);
				m = (new Boss(0.006, 250, path, true));
				m.killGold = 10;
				monsters.add(m);
				add(new BaseMonster(0.015, 30, path, 1), 30, 1);
				
				endGold = 40;
				break;
				
			case 15:
				frequence = 300;
				add(new FlyingMonster(0.015, 25, path, 1), 20, 1);
				m = (new Boss(0.006, 250, path, true));
				monsters.add(m);
				add(new BaseMonster(0.015, 25, path, 1), 20, 1);
				m = (new Boss(0.001, 500, path, false));
				monsters.add(m);
				add(new BaseMonster(0.015, 25, path, 1), 50, 1);
				add(new FlyingMonster(0.015, 25, path, 1), 50, 1);
				m = (new Boss(0.002, 2000, path, false));
				
				
			}
	}

	/**
	 * genere la vague numero number du niveau 2
	 * @param number le numero de la vague
	 */
	public void buildLevelTwoWave(int number) {
		Monster m;
		switch(number) {
		case 1:
			frequence = 1000;
			add(new BaseMonster(0.007, 5, path, 2), 8, 2);
			endGold = 50;
			break;

		case 2:
			frequence = 900;
			add(new FlyingMonster(0.01, 2, path, 2), 15, 2);
			endGold = 50;
			break;
		case 3:
			frequence = 850;
			add(new BaseMonster(0.01, 7, path, 2), 20, 2);
			add(new FlyingMonster(0.01, 5, path, 2), 10, 2);
			endGold = 50;
			break;
		case 4:
			frequence = 850;
			add(new BaseMonster(0.012, 7, path, 2), 15, 2);
			add(new FlyingMonster(0.013, 6, path, 2), 13, 2);
			endGold = 50;
			break;
		case 5:
			m = (new IceBoss(0.006, 150, path));
			m.killGold = 20;
			monsters.add(m);
			endGold = 60;
			break;
		case 6:
			frequence = 700;
			add(new BaseMonster(0.01, 10, path, 2), 25, 2);
			m = (new Boss(0.005, 100, path, false));
			m.killGold = 20;
			monsters.add(m);
			add(new FlyingMonster(0.012, 9, path, 2), 10, 2);
			endGold = 60;
			break;
		case 7:
			frequence = 600;
			add(new BaseMonster(0.012, 10, path, 2), 20, 2);
			add(new FlyingMonster(0.012, 12, path, 2), 15, 2);
			m = (new IceBoss(0.008, 100, path));
			m.killGold = 20;
			monsters.add(m);
			endGold = 60;
			break;
		case 8:
			frequence = 500;
			add(new BaseMonster(0.013, 15, path, 2), 20, 2);
			m = (new Boss(0.007, 300, path, false));
			m.killGold = 20;
			monsters.add(m);
			add(new FlyingMonster(0.013, 14, path, 2), 15, 2);
			endGold = 60;
			break;
		case 9:
			frequence = 450;
			m = (new IceBoss(0.008, 60, path));
			m.killGold = 30;
			monsters.add(m);
			add(new BaseMonster(0.016, 15, path, 2), 15, 2);
			m = (new IceBoss(0.008, 60, path));
			m.killGold = 30;
			monsters.add(m);
			add(new FlyingMonster(0.016, 16, path, 2), 17, 2);
			endGold = 60;
			break;
		case 10:
			m = (new Boss(0.005, 800, path, true));
			m.killGold = 20;
			monsters.add(m);
			endGold = 50;
			break;
		case 11:
			frequence = 425;
			m = (new Boss(0.01, 200, path, true));
			m.killGold = 20;
			monsters.add(m);
			add(new FlyingMonster(0.018, 25, path, 2), 35, 2);
			endGold = 60;
			break;
		case 12:
			frequence = 400;
			m = (new IceBoss(0.01, 100, path));
			m.killGold = 40;
			monsters.add(m);
			add(new BaseMonster(0.018, 20, path, 2), 20, 2);
			add(new FlyingMonster(0.018, 23, path, 2), 15, 2);
			endGold = 50;
			break;
		case 13:
			frequence = 350;
			m = (new Boss(0.01, 350, path, true));
			m.killGold = 20;
			monsters.add(m);
			add(new BaseMonster(0.02, 30, path, 2), 30, 2);
			m = (new Boss(0.05, 200, path, false));
			m.killGold = 20;
			monsters.add(m);
			add(new FlyingMonster(0.02, 25, path, 2), 20, 2);
			endGold = 50;
			break;
		case 14:
			frequence = 350;
			m = (new Boss(0.01, 400, path, false));
			m.killGold = 20;
			monsters.add(m);
			m = (new Boss(0.01, 350, path, true));
			m.killGold = 20;
			monsters.add(m);
			add(new BaseMonster(0.02, 35, path, 2), 20, 2);
			add(new FlyingMonster(0.02, 30, path, 2), 30, 2);
			endGold = 70;
			break;
		}

	}
}