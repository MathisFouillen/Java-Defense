package warcraftTD;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Path {
	 private List<Position> list = new ArrayList<Position>();
	 private double squareWidth;
	 private double squareHeight;
	 private Position spawn;
	 private int level;
	 private Position p;

	/**
	 * creer le chemin
	 * @param spawn
	 * @param squareWidth
	 * @param squareHeight
	 */
	public Path(Position spawn, double squareWidth, double squareHeight, int level) {

		this.squareHeight = squareHeight;
		this.squareWidth = squareWidth;
		this.spawn = spawn;
		this.level = level;
		this.p = new Position(spawn);
		this.p.x = (int)(p.x / squareWidth) * squareWidth + squareWidth / 2;
		this.p.y = (int)(p.y / squareHeight) * squareHeight + squareHeight / 2;
		buildPath();
	}
	
	/**
	 * @return la position du spawn
	 */
	public Position getSpawn() {
		return spawn;
	}
	
	/**
	 * @return la liste des positions de la route
	 */
	public List<Position> getList(){
		return list;
	}
	
	/**
	 * Ajoute une route la direction N, S, E ou O, de taille lenght
	 * @param direction
	 * @param lenght
	 */
	public void add(String direction, int lenght) {
		
		for(int i=0; i<lenght; i++) {
			if(direction.equals("N"))
				p.y += squareHeight;
			if(direction.equals("S"))
				p.y -= squareHeight;
			if(direction.equals("E"))
				p.x += squareWidth;
			if(direction.equals("O"))
				p.x -= squareWidth;
			this.p.x = (int)(p.x / squareWidth) * squareWidth + squareWidth / 2;
			this.p.y = (int)(p.y / squareHeight) * squareHeight + squareHeight / 2;
			list.add(new Position(p));
		}
	}
	
	/**
	 * Creer la liste des positions de la route
	 */
	public void buildPath() {
		list.add(spawn);
		switch(level) {
		case 1:
			add("S", 5);
			add("E", 3);
			add("N", 2);
			add("E", 3);
			add("S", 4);
			add("O", 5);
			add("S", 2);
			add("E", 7);
			add("N", 8);
			add("O", 3);
			break;
			
		case 2:
			add("E", 6);
			add("S", 4);
			add("O", 4);
			add("N", 2);
			add("O", 2);
			add("S", 5);
			add("E", 2);
			add("S", 3);
			add("E", 4);
			add("N", 3);
			add("E", 2);
			add("N", 5);
			break;
		}
		
		
		
	}
	
	/**
	 * creer un chemin genere aleatoirement
	 * @param spawn
	 * @param squareWidth
	 * @param squareHeight
	 * @param lenght
	 */
	public Path(Position spawn, double squareWidth, double squareHeight, int level, int lenght) {

		Position p = new Position(spawn);
		this.squareHeight = squareHeight;
		this.squareWidth = squareWidth;
		this.spawn = spawn;
		
		p.x = (int)(p.x / squareWidth) * squareWidth + squareWidth / 2;
		p.y = (int)(p.y / squareHeight) * squareHeight + squareHeight / 2;
		
		list.add(new Position(p));
		
		Position temp = new Position(p);

	
		while(list.size() < lenght) {
			int direction = new Random().nextInt(4);
			int l = (new Random().nextInt(5) + 2);
			boolean addable = true;
			for(int i=0; (i<l+2 && addable); i++) {
				if(direction == 0)
					temp.x -= squareWidth;
				if(direction == 1)
					temp.x += squareWidth;
				if(direction == 2)
					temp.y -= squareHeight;
				if(direction == 3)
					temp.y += squareHeight;
					
				temp.x = (int)(temp.x / squareWidth) * squareWidth + squareWidth / 2;
				temp.y = (int)(temp.y / squareHeight) * squareHeight + squareHeight / 2;
				
				//si le morceau de route qu'on veut ajouter traverse un chemin qui existe deja, on ne pourra pas l'ajouter
				for(int j=0; j<list.size(); j++) {
					double x = (int)(list.get(j).x / squareWidth) * squareWidth + squareWidth / 2;
					double y = (int)(list.get(j).y / squareWidth) * squareWidth + squareWidth / 2;
					if(temp.equals(new Position(x, y)))
						addable = false;
				}
				
				//si le morceau de route qu'on veut ajouter sort du plateau de jeu, on ne pourra pas l'ajouter
				if(temp.x<=0 || temp.x>=1-squareWidth || temp.y<=0 || temp.y>=1-squareHeight)
					addable = false;
			}
			
			//ajoute la route si possible, la route ajoutee est plus petite que celle testee pour eviter que les chemins se collent
			if(addable) {
				for(int i=0; i<l-2; i++) {
					if(direction == 0)
						p.x -= squareWidth;
					if(direction == 1)
						p.x += squareWidth;
					if(direction == 2)
						p.y -= squareHeight;
					if(direction == 3)
						p.y += squareHeight;
					
					p.x = (int)(p.x / squareWidth) * squareWidth + squareWidth / 2;
					p.y = (int)(p.y / squareHeight) * squareHeight + squareHeight / 2;
					
					list.add(new Position(p));
				}
				
			}
		}
	}

	
	/**
	 * retourne la precedente direction suivie avant la position p
	 * @param p uen position
	 * @return
	 */
	public String lastDirection(Position p) {
		double normalizedX = (int)(p.x / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int)(p.y / squareHeight) * squareHeight + squareHeight / 2;
		Position temp = new Position(normalizedX, normalizedY);
		Position res = null;
		for(int i=1; i<list.size(); i++)
			if(temp.equals(list.get(i)))
				res = list.get(i-1);
		
		if(res== null)
			return "";
		if(res.x > temp.x)
			return "E";
		if(res.x < temp.x)
			return "O";
		if(res.y > temp.y)
			return "N";
		if(res.y < temp.y)
			return "S";
		return "";
		
	}
	
	

	/**
	 * retourne la direction a suivre pour continuer sur le chemin apres la position p
	 * @param p une position
	 * @return
	 */
	public String direction(Position p) {

		
		if(new Position((int)(p.x / squareWidth) * squareWidth + squareWidth / 2, (int)(p.y / squareHeight) * squareHeight + squareHeight / 2).equals(list.get(list.size()-1)))
			return "Reached";
		
		double x = p.x;
		double y = p.y;
		String lastDirection = lastDirection(p);
		if(lastDirection == "S")
			y -= squareHeight/2;
		if(lastDirection == "N")
			y += squareHeight/2;
		if(lastDirection == "E")
			x += squareHeight/2;
		if(lastDirection == "O")
			x -= squareHeight/2;
		
		double normalizedX = (int)(x / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int)(y / squareHeight) * squareHeight + squareHeight / 2;
		Position temp = new Position(normalizedX, normalizedY);
		Position nextP = null;
		for(int i=0; i<list.size()-1; i++)
			if(temp.equals(list.get(i)))
				nextP = list.get(++i);
		
		if(nextP == null)
			return lastDirection(p);
		if(nextP.x > temp.x)
			return "E";
		if(nextP.x < temp.x)
			return "O";
		if(nextP.y > temp.y)
			return "N";
		if(nextP.y < temp.y)
			return "S";
		else
			return lastDirection(p);
	}

	/**
	 * return vrai si la Position p est dans le chemin avec une marge de largeur n
	 * @p une position
	 * @p un reel
	 * @return
	 */
	public boolean isInPath(Position p, double n) {
		return (isInPath(p) && isInPath(new Position(p.x-n, p.y)) && isInPath(new Position(p.x+n, p.y)) && 
				isInPath(new Position(p.x, p.y-n)) && isInPath(new Position(p.x, p.y+n)));
	}

	/**
	 * return vrai si la Position p est dans le chemin
	 * @p une position
	 * @return
	 */
	public boolean isInPath(Position p) {
		
		double normalizedX = (int)(p.x / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int)(p.y / squareHeight) * squareHeight + squareHeight / 2;
		Position temp = new Position(normalizedX, normalizedY);
		for(int i=0; i<list.size(); i++)
			if(list.get(i).equals(temp))
				return true;
		
		return false;
	}
}