package warcraftTD;

import java.util.ArrayList;

public abstract class Projectile {
	protected int damage;
	protected double speed;
	protected Position p;
	protected boolean moving = true;
	protected Position target;
	
	protected double hitbox;

	/**
	 * Creer un projectile a la postion p, il cible un position et inflige des degats
	 * @param damage
	 * @param p
	 * @param target
	 */
	public Projectile(int damage, Position p, Position target) {
		this.damage = damage;
		this.p = p;
		this.target = new Position(target);
	}
	
	/**
	 * Deplace le projectile en direction de la position qu'avait le monstre au moment de tire
	 */
	public void move() {
		double dy = target.y - p.y;
		double dx = target.x - p.x;
		double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
		double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
		
		if(Math.sqrt(Math.pow(target.x - p.x, 2) + Math.pow(target.y-p.y, 2)) >= hitbox) {
			p.x += ratioX * speed;
			p.y += ratioY * speed;
		}
		else
			moving = false;
	}
	
	/**
	 * fonction abstaite traite dans les classes filles qui touche les ennemis
	 * @param monsters
	 */
	public abstract void hit(ArrayList<Monster> monsters) ;
	
	
	/**
	 * fonction abstrraite traite dans les classes fille qui affcihe le projectile sur le plateau de jeu.
	 */
	public abstract void draw() ;

	/**
	 * deplace et affiche le projectile sur le plateau de jeu. Detecte si le projectile touche un monstre.
	 * @param monsters
	 */
	public void update(ArrayList<Monster> monsters) {
		hit(monsters);
		move();
		draw();
	}
}