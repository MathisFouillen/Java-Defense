
package warcraftTD;

import java.util.List;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

public class World {
	// l'ensemble des monstres, pour gerer (notamment) l'affichage
	private List<Monster> monsters = new ArrayList<Monster>();
	
	// la route traversee par les monstres
	private Path path;
	
	// l'ensembe des tours
	private ArrayList<Tower> towers = new ArrayList<Tower>();
	
	// Position par laquelle les monstres vont venir
	private Position spawn;
	
	// Information sur la taille du plateau de jeu
	private int width;
	int height;
	int nbSquareX;
	private int nbSquareY;
	private double squareWidth;
	private double squareHeight;
	
	
	// Nombre de points de vie du joueur
	private int life = 20;
	
	// Pieces d'or du joueur
	private int gold = 100;
	
	
	// Commande sur laquelle le joueur appuie (sur le clavier)
	private char key;
	
	// Condition pour afficher le menu
	private boolean showMenu = false;
	
	// Condition pour terminer la partie
	private boolean end = false;
	
	// Information sur la vague
	private int waveNumber = 1;
	private Wave wave;
	private int level;

	
	/**
	 * Initialisation du monde en fonction de la largeur, la hauteur et le nombre de cases donnees
	 * @param width
	 * @param height
	 * @param nbSquareX
	 * @param nbSquareY
	 * @param startSquareX
	 * @param startSquareY
	 */
	public World(int width, int height, int nbSquareX, int nbSquareY, int startSquareX, int startSquareY, int level) {
		this.width = width;
		this.height = height;
		this.nbSquareX = nbSquareX;
		this.nbSquareY = nbSquareY;
		this.level = level;
		squareWidth = (double) 1 / (nbSquareX);
		squareHeight = (double) 1 / (nbSquareY);
		if(showMenu) {
			squareWidth = (double) 1 / (nbSquareX);
			squareHeight = (double) 1/ (nbSquareY);
		}
			
		spawn = new Position(startSquareX * squareWidth + squareWidth / 2, startSquareY * squareHeight + squareHeight / 2);
		
		//StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
		
		path = new Path(spawn, squareWidth, squareHeight, level);
		
		
		//Initialise la taille des tours
		ArrowTower.squareHeight = squareHeight;
		ArrowTower.squareWidth = squareWidth;
		CannonTower.squareHeight = squareHeight;
		CannonTower.squareWidth = squareWidth;
		LightningTower.squareHeight = 0.75*squareHeight;
		LightningTower.squareWidth = 0.75*squareWidth;
		FireTower.squareHeight = squareHeight;
		FireTower.squareWidth = squareWidth;
		
	}
	
	
	/**
	 * Donne au joueur beaucoup de pieces d'or et de vie
	 */
	public void cheatMode() {
		gold = 999999999;
		life = 999999999;
	}
	
	/**
	 * permet au joueur de commencer a partir d'une certaine vague
	 * @param waveNumber un numero de vague
	 */
	public void setWaveNumber(int waveNumber) {
		this.waveNumber = waveNumber;
	}
	
	/**
	 * retourne vrai si la position p n'est pas dans le menu
	 * @param p une position
	 * @return
	 */
	public boolean inGameScreen(Position p) {
		if(!showMenu)
			return true;	
		return (p.x < 1 - 3*squareWidth);
	}
	

	
	/**
	 * Definit le decors du plateau de jeu.
	 */
	 public void drawBackground() {	
		 StdDraw.setPenColor(StdDraw.LIGHT_GREEN);
		 String image;
		 if(level ==1)
			 image = "images/grass.jpg";
		 else
			 image = "images/snow.jpg";

		StdDraw.picture(0.5, 0.5, image, 1, 1);
	 }
	 
	 
	 /**
	  * Initialise le chemin sur la position du point de depart des monstres. Cette fonction permet d'afficher une route qui sera diffeente du decors.
	  */
	 public void drawPath() {
		// Position p = new Position(spawn);
		 StdDraw.setPenColor(StdDraw.YELLOW);
		 //StdDraw.filledRectangle(p.x, p.y, squareWidth / 2, squareHeight / 2);
		 String image;
		 if(level == 1) {
			 image = "images/path.jpg";
			 StdDraw.picture(path.getList().get(0).x , path.getList().get(0).y, "images/spawn.jpg", squareWidth, squareHeight);
		 }
		 else {
			 image = "images/path2.jpg";
			 StdDraw.picture(path.getList().get(0).x , path.getList().get(0).y, "images/path2.jpg", squareWidth, squareHeight);
		 }
		 
		 
		 
		 for(int i=1; i<path.getList().size(); i++) 
			 StdDraw.picture(path.getList().get(i).x , path.getList().get(i).y, image, squareWidth, squareHeight);
			 //StdDraw.filledRectangle(path.get(i).x, path.get(i).y, squareWidth / 2, squareHeight / 2);
		 
		 StdDraw.picture(path.getList().get(path.getList().size()-1).x , path.getList().get(path.getList().size()-1).y, "images/castle.png", squareWidth*1.5, squareHeight*1.5);
		 StdDraw.picture(path.getList().get(path.getList().size()-1).x , path.getList().get(path.getList().size()-1).y + 0.02, "images/heart.png", squareWidth/1.5, squareHeight/1.5);
	 }
	 
	 /**
	  * Affiche certaines informations sur l'ecran telles que les points de vie du joueur ou son or
	  */
	 public void drawInfos() {
		 
			// Affichage des vies
			StdDraw.setFont(new Font("Arial", Font.BOLD, 100 * width / 1200));
			StdDraw.setPenColor(StdDraw.RED);
			// StdDraw.text(path.list.get(path.list.size()-1).x-0.015,
			// path.list.get(path.list.size()-1).y,"♥");
			StdDraw.setFont(new Font("Arial", Font.BOLD, 16 * width / 1200));
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(path.getList().get(path.getList().size() - 1).x, path.getList().get(path.getList().size() - 1).y + 0.02,
					life + "");
			
		
			//Afficher le menu des actions, a droite
			if (showMenu) {

		    	 StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		    	 StdDraw.filledRectangle(1-squareWidth, 0.5, 3*squareWidth/2, nbSquareY*squareHeight/2);
		    	 
		    	 //Afficher les objets
		    	 StdDraw.picture(0.85, 0.8, "images/arrowTower.png", squareHeight, squareWidth); 
		         StdDraw.picture(0.95, 0.8, "images/cannonTower.png", squareHeight, squareWidth);
		         StdDraw.picture(0.85, 0.7, "images/lightningTower.png", squareHeight, squareWidth); 
		         if(level >= 2 || (level == 1 && waveNumber > 10)) 
		        	StdDraw.picture(0.95, 0.7, "images/fireTower.png", squareHeight, squareWidth);
		         if(level >= 2) {
		        	StdDraw.setPenColor(StdDraw.GREEN);
			        StdDraw.filledCircle(0.95, 0.6, squareWidth/2);
		         }
		         
		         StdDraw.setPenColor(StdDraw.RED);
		         StdDraw.filledCircle(0.85, 0.6, squareWidth/2);
		         StdDraw.setPenColor(StdDraw.BLACK);
		         StdDraw.setFont(new Font("Arial", Font.BOLD, 45*width/1200));
		         StdDraw.text(0.85, 0.5, "+");
		         StdDraw.text(0.95, 0.5, "$");
		     
		         
		         StdDraw.text(0.80, 0.5, ">");
		         
		         
		         //Afficher le prix
		         StdDraw.setFont(new Font("Arial", Font.BOLD, 20*width/1200));
		         StdDraw.setPenColor(StdDraw.ORANGE);
		         StdDraw.text(0.85, 0.75, ArrowTower.getCost()+"");
		         StdDraw.text(0.95, 0.75, CannonTower.getCost()+"");
		         StdDraw.text(0.85, 0.655, LightningTower.getCost()+"");
		         StdDraw.text(0.85, 0.55, Nuke.getCost()+"");
		         if(level >= 2)
		        	 StdDraw.text(0.95, 0.55, Heal.getCost()+"");
		         if(level >= 2 || (level == 1 && waveNumber > 10))
		        	 StdDraw.text(0.95, 0.65, FireTower.getCost()+"");
		         StdDraw.text(0.85, 0.475, "40");
		         
		         
		 }
			else {
				StdDraw.setPenColor(StdDraw.BLACK);
		        StdDraw.setFont(new Font("Arial", Font.BOLD, 45*width/1200));
		        StdDraw.text(0.990, 0.5, "<");
					
			}
				
			//Affichage des pieces d'or
	         StdDraw.setFont(new Font("Arial", Font.BOLD, 16*width/1200));
	         StdDraw.setPenColor(StdDraw.YELLOW);
	         StdDraw.filledCircle(0.95,0.95,0.03);
	         StdDraw.setPenColor(StdDraw.BLACK);
	         StdDraw.text(0.95, 0.95, "Gold:"+gold);
	         
	         //Affichage du numero de vague
	         StdDraw.setFont(new Font("Arial", Font.BOLD, 30*width/1200));
	         StdDraw.setPenColor(StdDraw.RED);
	         StdDraw.text(0.85, 0.95, "Wave:" + waveNumber);

   
    
	 }
	 
	 /**
	  * Fonction qui recupere le positionnement de la souris et permet d'afficher une image de tour en temps reel
	  *	lorsque le joueur appuie sur une des touches permettant la construction d'une tour.
	  */
	 public void drawMouse() {
		double normalizedX = (int)(StdDraw.mouseX() / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int)(StdDraw.mouseY() / squareHeight) * squareHeight + squareHeight / 2;
		String image = null;
		switch (key) {
		case 'a' : 
			//StdDraw.setPenColor(StdDraw.DARK_GRAY);
            //StdDraw.filledRectangle(normalizedX, normalizedY, squareWidth / 2, squareHeight / 2);
            image = "images/arrowTower.png";
            StdDraw.picture(normalizedX, normalizedY, image, ArrowTower.squareHeight, ArrowTower.squareWidth);
			break;
		case 'b' :
			//StdDraw.setPenColor(StdDraw.GRAY);
			//StdDraw.filledRectangle(normalizedX, normalizedY, squareWidth / 2, squareHeight / 2);
			image = "images/cannonTower.png";
			StdDraw.picture(normalizedX, normalizedY, image, CannonTower.squareHeight, CannonTower.squareWidth);
			break;
		case 'm' :
			image = "images/lightningTower.png";
			StdDraw.picture(normalizedX, normalizedY, image, LightningTower.squareHeight, LightningTower.squareWidth);
			break;
			
		case 'c':
			if(level >= 2 || (level == 1 && waveNumber > 10)) {
				image = "images/fireTower.png";
				StdDraw.picture(normalizedX, normalizedY, image, FireTower.squareHeight, FireTower.squareWidth);
			}
			break;
			
		case 'd':
			StdDraw.setPenColor(new Color(255, 0, 0, 100));
            StdDraw.filledCircle(normalizedX, normalizedY, Nuke.getRange());
			break;
		case 'g':
			if(level >= 2) {
				StdDraw.setPenColor(new Color(120, 220, 30, 100));
				StdDraw.filledCircle(normalizedX, normalizedY, Heal.getRange());
			}
			break;
		}
		
//		 if (image != null)
//			 StdDraw.picture(normalizedX, normalizedY, image, squareWidth, squareHeight);
	 }
		 
	 /**
	  * Pour chaque monstre de la liste de monstres de la vague, utilise la fonction update() qui appelle les fonctions run() et draw() de Monster.
	  * Modifie la position du monstre au cours du tempsaÂ  l'aide du parametre nextP.
	  */
	 public void updateMonsters() {
	 
		Iterator<Monster> i = monsters.iterator();
		Monster m;
		
		while (i.hasNext()) {
			m = i.next();
			m.update(towers);
		}

		//Identifie les monstres morts et ceux qui ont parcouru toute la route
		for(int j=0; j<monsters.size(); j++) {
			if(monsters.get(j).pv <= 0) {
				gold += monsters.get(j).killGold;
				monsters.remove(j);
			}
			else
				if(monsters.get(j).reached) {
					monsters.remove(j);
					life--;
				}
		}

	 }
	
	 /**
	  * Met a jour les tours
	  */
	 public void updateTowers() {
		 for(int i=0; i<towers.size(); i++) {
			 for(int j=0; j<monsters.size(); j++)
				towers.get(i).shoot(monsters.get(j));
			 towers.get(i).update((ArrayList<Monster>) monsters);
		 }
	 }
	 
	 
	 
	 /**
	  * Met a  jour toutes les informations du plateau de jeu ainsi que les deplacements des monstres et les attaques des tours.
	  * @return les points de vie restants du joueur
	  */
	 public int update() {
		drawBackground();
		drawPath();
		updateTowers();
		updateMonsters();
		drawInfos();
		drawMouse();
		return life;
	 }
	 
	/**
	 * Recupere la touche appuyee par l'utilisateur et affiche les informations pour la touche selectionnee
	 * @param key la touche utilisee par le joueur
	 */
	public void keyPress(char key) {
		if(Character.compare('t', key) == 0) {
			showMenu = !showMenu;
			return;
		}
		else
			 key = Character.toLowerCase(key);
		this.key = key;
		switch (key) {
		case 'a':
			System.out.println("Arrow Tower selected (" + ArrowTower.getCost() + "g).");
			break;
		case 'b':
			System.out.println("Cannon Tower selected (" + CannonTower.getCost() + "g).");
			break;
		case 'm':
			System.out.println("Magic Tower selected (" + LightningTower.getCost() + "g).");
			break;
		case 'd':
			System.out.println("Nuke selected (" + Nuke.getCost() + "g).");
			break;
		case 'g':
			if(level >= 2)
				System.out.println("Heal selected (" + Heal.getCost() + "g).");
			break;
		case 'c':
			if(level >= 2 || (level == 1 && waveNumber > 10))
				System.out.println("Fire Tower selected (" + FireTower.getCost() +"g).");
			break;
		case 'e':
			System.out.println("Evolution selected (40g).");
			break; 
		case 'x':
			System.out.println("Sell selected.");
			break;
		case 's':
			System.out.println("Starting game!");
		case 'q':
			System.out.println("Exiting.");
			end=true;
			
			
		}
	}
	
	/**
	 * Verifie lorsque l'utilisateur clique sur sa souris qu'il peut: 
	 * 		- Ajouter une tour a la position indiquee par la souris.
	 * 		- Ameliorer une tour existante.	
	 * Puis l'ajouter a la liste des tours
	 * @param x
	 * @param y
	 */
	public void mouseClick(double x, double y) {
		double normalizedX = (int)(x / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int)(y / squareHeight) * squareHeight + squareHeight / 2;
		Position p = new Position(normalizedX, normalizedY);
		
		if(showMenu) {
			if(x >= 0.8-squareWidth/4 && x <= 0.8 + squareWidth/4 && y >= 0.5 - squareHeight/4 && y <= 0.5 + squareHeight/4) {
				showMenu = false;
				return;
			}
		}
			
		else if (x >= 0.99-squareWidth/4 && x <= 0.99 + squareWidth/4 && y >= 0.5 - squareHeight/4 && y <= 0.5 + squareHeight/4) {
			showMenu = true;
			return;
	}
	
		switch (key) {
		case 'a':
			if(gold >= ArrowTower.getCost()) { 
				Tower t = new ArrowTower(p);
				if(!(t.isIn(towers) || path.isInPath(p)) && inGameScreen(p)) {
					towers.add(t);
					gold -= ArrowTower.getCost();
				}
			}
			key = ' ';
			break;
		case 'b':
			if(gold >= CannonTower.getCost()) { 
				Tower t = new CannonTower(p);
				if(!(t.isIn(towers) ||  path.isInPath(p)) && inGameScreen(p)) {
					towers.add(t);
					gold -=  CannonTower.getCost();
				}
			}
			key = ' ';
			break;
		
		case 'm':
			if(gold >= LightningTower.getCost()) { 
				Tower t = new LightningTower(p);
				if(!(t.isIn(towers) ||  path.isInPath(p)) && inGameScreen(p)) {
					towers.add(t);
					gold -= LightningTower.getCost();
				}
			}
			key = ' ';
			break;
		
		case 'e':
			//System.out.println("Ici il est possible de faire evoluer une des tours");
			if(gold >= 40)
			for(int i=0; i<towers.size(); i++) {
				if(p.equals(towers.get(i).p)) {
					towers.get(i).upgrade();
					gold-=40;
					break;
				}
					
			}
			key = ' ';
			break;
			
		case 'x':
			if(inGameScreen(p)) {
				for(int i=0; i<towers.size(); i++) {
					if(p.equals(towers.get(i).p)) {
						if(towers.get(i).level == 2)
							gold += 20;
						if(towers.get(i) instanceof ArrowTower)
							gold += ArrowTower.getCost() * 0.5;
						if(towers.get(i) instanceof CannonTower)
							gold += CannonTower.getCost() * 0.5;
						if(towers.get(i) instanceof LightningTower)
							gold += LightningTower.getCost() * 0.5;
						if(towers.get(i) instanceof FireTower)
							gold += FireTower.getCost() * 0.5;
						towers.remove(i);
						break;
					}
				}
				
			}
			key = ' ';
			break;
			
		case 'c':
			if((level >= 2  || (level == 1 && waveNumber > 10)) && gold >= FireTower.getCost()) {
				Tower t = new FireTower(p);
				if(!(t.isIn(towers) ||  path.isInPath(p)) && inGameScreen(p)) {
					towers.add(t);
					gold -= FireTower.getCost();
				}
			}
			key = ' ';
			break;
			
		case 'd':
			if(gold>=Nuke.getCost() && inGameScreen(p)) {
				new Nuke(p).hit((ArrayList<Monster>) monsters);
				gold -= Nuke.getCost();
			}
			key = ' ';
			break;
			
		case 'g':
			if(level >= 2 && gold>=Heal.getCost() && inGameScreen(p)) {
				new Heal(p).heal((ArrayList<Tower>) towers);
				gold -= Heal.getCost();
			}
			key = ' ';
			break;
		}
		
		//Affiche le menu sur le cote
		if(showMenu) {
			if(x >= 0.85-squareWidth/2 && x <= 0.85 + squareWidth/2 && y >= 0.8 - squareHeight/2 && y <= 0.8 + squareHeight/2) {
				key = ' ';
				keyPress(key='a');
			}
			if(x >= 0.95-squareWidth/2 && x <= 0.95 + squareWidth/2 && y >= 0.8 - squareHeight/2 && y <= 0.8 + squareHeight/2) {
				key = ' ';
				keyPress(key='b');
			}
			if(x >= 0.85-squareWidth/2 && x <= 0.85 + squareWidth/2 && y >= 0.7 - squareHeight/2 && y <= 0.7 + squareHeight/2) {
				key = ' ';
				keyPress(key='m');
			}
			if((level >= 2 || (level == 1 && waveNumber > 10)) && x >= 0.95-squareWidth/2 && x <= 0.95 + squareWidth/2 && y >= 0.7 - squareHeight/2 && y <= 0.7 + squareHeight/2) {
				key = ' ';
				keyPress(key='c');
			}
			if(x >= 0.85-squareWidth/2 && x <= 0.85 + squareWidth/2 && y >= 0.6 - squareHeight/2 && y <= 0.6 + squareHeight/2) {
				key = ' ';
				keyPress(key='d');
			}
			if(level >= 2 && x >= 0.95-squareWidth/2 && x <= 0.95 + squareWidth/2 && y >= 0.6 - squareHeight/2 && y <= 0.6 + squareHeight/2) {
				key = ' ';
				keyPress(key='g');
			}
			if(x >= 0.85-squareWidth/2 && x <= 0.85 + squareWidth/2 && y >= 0.5 - squareHeight/2 && y <= 0.5 + squareHeight/2) {
				key = ' ';
				keyPress(key='e');
			}
			if(x >= 0.95-squareWidth/2 && x <= 0.95 + squareWidth/2 && y >= 0.5 - squareHeight/2 && y <= 0.5 + squareHeight/2) {
				key = ' ';
				keyPress(key='x');
			}
		}
			

		
	}
	
	/**
	 * Comme son nom l'indique, cette fonction permet d'afficher dans le terminal les differentes possibilitees 
	 * offertes au joueur pour interagir avec le clavier
	 */
	public void printCommands() {
		System.out.println("Press A to select Arrow Tower (cost " + ArrowTower.getCost() + "g).");
		System.out.println("Press B to select Cannon Tower (cost " + CannonTower.getCost() + "g).");
		System.out.println("Press M to select Magic Tower (cost " + LightningTower.getCost() + "g).");
		if(level >= 2)
			System.out.println("Press C to select Fire Tower (cost " + FireTower.getCost() + "g).");
		System.out.println("Press D to select Nuke (cost " + Nuke.getCost() + "g).");
		System.out.println("Press G to select Heal (cost " + Heal.getCost() + "g).");
		System.out.println("Press E to update a tower (cost 40g).");
		System.out.println("Click on the grass to build it.");
		System.out.println("Press S to start.");
	}
	
	
	
	/**
	 * Recupere la touche entree au clavier ainsi que la position de la souris et met a jour le plateau en fonction de ces interractions
	 */
	public void run() {
		printCommands();
		
		
		update();
		StdDraw.setFont(new Font("Arial", Font.BOLD, 140*width/1200));
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(0.50, 0.5, "Press S to Start.");
		StdDraw.show();
		
		//Il faut attendre que l'utilisateur appui sur S
		while(! (StdDraw.hasNextKeyTyped() &&  Character.compare('s', Character.toLowerCase(StdDraw.nextKeyTyped())) == 0)) ;
		
		System.out.println("Starting game!");
		int nbrMonsters = 0;
		long start = System.currentTimeMillis() + 20000;
		wave = new Wave(waveNumber, path, level);
		System.out.println("Hold F to skip waiting");

		
		
		
		while(!end) {
			
			//Apparition des monstres
			if(System.currentTimeMillis() - start > wave.getFrequence() && nbrMonsters < wave.getMonsters().size()) {
				wave.setWaiting(false);
				start = System.currentTimeMillis();
				monsters.add(wave.getMonsters().get(nbrMonsters++));
			}
			
			//Fin d'une vague
			if(monsters.size() == 0 && !wave.isWaiting() && nbrMonsters == wave.getMonsters().size()) {
				if(Character.compare(key, 'f') == 0)
					key = ' ';
				gold += wave.getEndGold();
				System.out.println("Wave as ended, you earned " + wave.getEndGold() + " gold !");
				wave = new Wave(++waveNumber, path, level);
				nbrMonsters = 0;
				
				if(wave.getMonsters().size() == 0) {
					System.out.println("You have passed the level " + level + " !");
					end = true;
				}
				
				//Dans le niveau 2, le tours prennent des degats a la fin de chaque vague
				if(level == 2) {
					for(int i=0; i<towers.size(); i++)
						if((towers.get(i).pv-=3) <= 0)
							towers.remove(i);
				}
				
				//Debloque la tour de feu, dans la vague 11 su niveau 1
				start = System.currentTimeMillis() + 20000;
				 if(level == 1 && waveNumber == 11)
					 System.out.println("New tower unlocked ! Press C to select Fire Tower (cost" + FireTower.getCost() + "g).");
				System.out.println("Hold F to skip waiting");
				}
			
			
			StdDraw.clear();
			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}
			
			if (StdDraw.isMousePressed()) {
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
				StdDraw.pause(50);
			}
			
			if(update()==0) {
				System.out.println("Game over");
				end = true;
			}	
			
			//Si une vague est en attente, le compte a rebours diminue
			if(wave.isWaiting()) {
				int timer = (int)(-(System.currentTimeMillis() - start)/1000) + 1;
	
		        //Affichage du compte a rebours avant le debut de la vague
	        	StdDraw.setFont(new Font("Arial", Font.BOLD, 140*width/1200));
	            StdDraw.setPenColor(StdDraw.RED);
	        	StdDraw.text(0.50, 0.5, timer+"");
		         
	        	 //Accelerer l'attente
	        	 if(Character.compare('f', key) == 0) {
	        		 start -= 3000;
	        		 key = ' ';
	        	 }
		         
			}
			
			
			StdDraw.show();
			StdDraw.pause(20);			
		}
		
		System.out.println("Game as ended");

	}
	
}