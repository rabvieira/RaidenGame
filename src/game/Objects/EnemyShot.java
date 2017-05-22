package game.Objects;

import javax.swing.ImageIcon;

/**
 * Representa o objeto referente ao tiro de um inimigo. 
 */

public class EnemyShot extends GenericGameObject {

	private static final int SHOT_SPEED = 3;
	public boolean fire;
	
	public EnemyShot(int x, int y){
		super(x,y);
		Sprite = new ImageIcon("enemyshot.png");
		Bounds.setBounds(x, y, Sprite.getIconWidth(), Sprite.getIconHeight());
		fire = false;
	}
	
	public void move(){
		super.move();
		if(fire)
			dy = SHOT_SPEED;
		
		Bounds.setBounds(x, y, Sprite.getIconWidth(), Sprite.getIconHeight());
		
		if(y > Y_LOWER_BOUND + 100){
			y = Y_LOWER_BOUND  ;
			fire = false;
		}
	}

	public boolean getFire() {
		return fire;
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}
}
