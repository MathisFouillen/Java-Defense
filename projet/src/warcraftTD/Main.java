package warcraftTD;

public class Main {

	public static void main(String[] args) {
		int width = 1000;	//640, 1200
		int height = 700;	//480, 800
		int nbSquareX = 11;
		int nbSquareY = 11;
		int startX = 1;
		int startY = 10;		

		
		while(true) {
			
			// Lance le menu de selection de niveau
			World w = new Home(width, height, nbSquareX, nbSquareY, startX, startY).run();
			
			//w.cheatMode();
			//w.setWaveNumber(12);
			
			
			// Lancement de la boucle principale du jeu
			w.run();
		}
		
	}

}