package warcraftTD;

import java.util.ArrayList;
import java.util.Random;

public abstract class Monster {
	protected Path path;
	
	// Position du monstre à l'instant t
	protected Position p;
	// Vitesse du monstre
	protected double speed;
	// Position du monstre à l'instant t+1
	protected Position nextP;
	// Boolean pour savoir si le monstre a atteint le chateau du joueur
	protected boolean reached;
	
	protected boolean flying;
	
	protected double size = 0.01;
	
	int pv;
	
	int pvMax;
	
	int killGold;
	
	String image;

	public Monster(Path path) {
		this.p = new Position(path.getSpawn());
		this.nextP = new Position(path.getSpawn());
		this.path = path;
		pvMax = pv;
	}
	
	public Monster(double speed, int pv, Path path) {
		this.p = new Position(path.getSpawn());
		this.nextP = new Position(path.getSpawn());
		this.speed = speed;
		this.pv = pv;
		killGold = 5;
		this.path = path;
		pvMax = pv;
	}
	
	public Monster(Monster m) {
		p = new Position(m.path.getSpawn());
		nextP = new Position(m.path.getSpawn());
		speed = m.speed;
		pv = m.pvMax;
		killGold = m.killGold;
		path = m.path;
		pvMax = m.pvMax;
	}
	


	/**
	 * Delace le monstre en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		
		double rangeMin = -0.5;
		double rangeMax = 0.5;
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		
		String direction = path.direction(p);
		
		if(direction == "Reached")
			reached = true;

		// Calcul la prochaine position du monstre
		if(direction == "S") {
			nextP.x = p.x;
			nextP.y -= speed;
			if(path.isInPath(new Position(p.x + randomValue, nextP.y), 0.02) && path.direction(new Position(p.x + randomValue, nextP.y)) == path.direction(p))
				nextP.x += randomValue;
		}

		if(direction == "E") {
			nextP.x += speed;
			nextP.y = p.y;
			if(path.isInPath(new Position(nextP.x, p.y - randomValue), 0.02) && path.direction(new Position(nextP.x, randomValue + p.y)) == path.direction(p))
				nextP.y += randomValue;
		}
		
		if(direction == "N") {
			nextP.x = p.x;
			nextP.y += speed;
			if(path.isInPath(new Position(p.x + randomValue, nextP.y), 0.02) && path.direction(new Position(p.x + randomValue, nextP.y)) == path.direction(p))
				nextP.x += randomValue;
		}
		
		if(direction == "O") {
			nextP.x -= speed;
			nextP.y = p.y;
			if(path.isInPath(new Position(nextP.x, randomValue + p.y), 0.02) && path.direction(new Position(nextP.x, randomValue + p.y)) == path.direction(p))
				nextP.y += randomValue;
		}
	
		
		// Mesure sur quel axe le monstre se dirige.
		double dy = nextP.y - p.y;
		double dx = nextP.x - p.x;
		

		if (dy + dx != 0 && !reached){
			// Mesure la distance à laquelle le monstre à pu se déplacer.
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			
			p.x += ratioX * speed;
			p.y += ratioY * speed;
		}

	}

	/**
	 * Deplace et affiche le monstre sur le plateau de jeu.
	 */
	public void update(ArrayList<Tower> towers) {
		if(!reached)
			move();
		draw();
	}

	/**
	 * Affiche le monstre sur le plateau de jeu.
	 */
	public void draw() {
		int rotation = 0;
		if(path.direction(p) == "N")
			rotation = 180;
		if(path.direction(p) == "E")
			rotation = 90;
		if(path.direction(p) == "O")
			rotation = -90;
		
		boolean slowed = false;
			
		for (int i = 0; i < LightningTower.slowedMonsters.size(); i++)
			slowed = LightningTower.slowedMonsters.get(i).m == this;
		 
		StdDraw.picture(p.x, p.y, image, size*4, size*4, rotation);
		if(slowed && pv>0) {
			StdDraw.setPenColor(StdDraw.BLACK);
	        StdDraw.filledRectangle(p.x, p.y-0.025, 0.0275, 0.0075);
	        StdDraw.setPenColor(StdDraw.GREEN);
	        StdDraw.filledRectangle(p.x+pv*0.025/pvMax - 0.025, p.y-0.025, pv*0.025/pvMax, 0.005);
		}
		else
			if(pv>0 && pv<pvMax) {
				StdDraw.setPenColor(StdDraw.BLACK);
		        StdDraw.filledRectangle(p.x, p.y-0.025, 0.0275, 0.0075);
		        StdDraw.setPenColor(StdDraw.RED);
		        StdDraw.filledRectangle(p.x+pv*0.025/pvMax - 0.025, p.y-0.025, pv*0.025/pvMax, 0.005);
			}
	}
}