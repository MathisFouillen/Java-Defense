package warcraftTD;

import java.awt.Font;

public class Home {
	private int level;
	private int width;
	private int height;
	private int nbSquareX;
	private int nbSquareY;
	private int startX;
	private int startY;
	private double squareWidth;
	private double squareHeight;
	
	/**
	 * Initialisation du menu de selection de niveau en fonction de la largeur, la hauteur et le nombre de cases donnees
	 * @param width
	 * @param height
	 * @param nbSquareX
	 * @param nbSquareY
	 * @param startX
	 * @param startY
	 */
	public Home(int width, int height, int nbSquareX, int nbSquareY, int startX, int startY) {
		this.width = width;
		this.height = height;
		this.nbSquareX = nbSquareX;
		this.nbSquareY = nbSquareY;
		this.startX = startX;
		this.startY = startY;
		squareWidth = (double) 1 / nbSquareX;
		squareHeight = (double) 1 / nbSquareY;
		
		StdDraw.setCanvasSize(width, height);
		StdDraw.disableDoubleBuffering();
	}
	
	/**
	 * Selectionne un niveau si l'utilisateur appui sur la touche correspondante
	 * @param key
	 */
	public void keyPress(char key) {
		key = Character.toLowerCase(key);
		switch (key) {
		case '&' :
			level = 1;
			break;
		case 'é':
			level = 2;
			break;
		}
	}
	
	/**
	 * Selectionne un niveau si l'utilisateur clique dessus
	 * @param x
	 * @param y
	 */
	public void mouseClick(double x, double y) {
		
		if(x >= 0.2 - squareWidth*2 && x <= 0.2 + squareWidth*2 && y >= 0.5-squareHeight*2 && y<= 0.5+squareHeight*2)
			level = 1;
		if(x >= 0.7 - squareWidth*2 && x <= 0.7 + squareWidth*2 && y >= 0.5-squareHeight*2 && y<= 0.5+squareHeight*2)
			level = 2;
	}

	/**
	 * Dessine le menu de selection de niveau
	 */
	public void draw() {
		
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledRectangle(0.5, 0.5, 0.5, 0.5);
		
		StdDraw.setFont(new Font("Arial", Font.BOLD, 20*width/1200));
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(0.5, 0.8, "Choose your level");
        
        StdDraw.setFont(new Font("Arial", Font.BOLD, 14*width/1200));
        StdDraw.text(0.2, 0.3, "Level 1");
		StdDraw.picture(0.2, 0.5, "images/level1.jpg", squareWidth*4, squareHeight*4);
		StdDraw.text(0.7, 0.3, "Level 2");
		StdDraw.picture(0.7, 0.5, "images/level2.jpg", squareWidth*4, squareHeight*4);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.5, 0.1, "Made by Yaël Mousel and Mathis Fouillen");
		
	}
	
	/**
	 * Retourne un plateau de jeu correspondant au niveau selectionne par le joueur
	 * Recupere la touche entree au clavier ainsi que la position de la souris
	 * @return 
	 */
	public World run() {
		draw();
		while(level == 0) {
			if (StdDraw.hasNextKeyTyped())
				keyPress(StdDraw.nextKeyTyped());
			
			if (StdDraw.isMousePressed()) {
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
				StdDraw.pause(50);
			}

		}
		
		return new World(width, height, nbSquareX, nbSquareY, startX, startY, level);
	}
	
	
	
	
}
