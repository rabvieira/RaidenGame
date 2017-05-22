package game.Objects;

import javax.swing.ImageIcon;

/**
 * Representa um objeto inimigo referente ao jogo. 
 */

public class Enemy extends GenericGameObject{
	
	public EnemyShot[] s = new EnemyShot[NUMBER_OF_SHOTS];
	public boolean outOfScreen = false;
	public int invunerableTime = 300;
	private int imageIndexControl = 1;
	private boolean invunerable = false;
	private boolean shootEnemy = false;
	private int shotTime = 0;
	
	public Enemy(int x, int y) {
		super(x, y);
		Sprite = new ImageIcon("enemy.png");
		Bounds.setBounds(x, y, Sprite.getIconWidth(), Sprite.getIconHeight());
		for(int i = 0; i < NUMBER_OF_SHOTS; i++)
			s[i] = new EnemyShot(x+25,y+25);
	}

	public void move(){
		super.move();
		shotTime++;
		for(int i = 0; i < NUMBER_OF_SHOTS; i++){
			if(!(s[i].getFire())){
				s[i].setX(x + 25);
				s[i].setY(y + 25);
			}
		}
		
		Bounds.setBounds(x, y, Sprite.getIconWidth(), Sprite.getIconHeight());
		
		if(y > Y_LOWER_BOUND + 200)
			outOfScreen = true;
		if(x == X_RIGHT_BOUND)
			dx = -1;
		if(x == X_LEFT_BOUND)
			dx = 1;
		
		if(shotTime == 101)
			shotTime = 0;
		if(shotTime == 100 && shootEnemy){
			s[0].setFire(true);
			shotTime = 0;
		}
	}
	
	public void invunerableTime() {
		if(invunerableTime > 0){
			String nameSpriteExplosion;
			
			if(invunerableTime % 10 == 0){
				imageIndexControl++;
			}
			
			nameSpriteExplosion = "explosion" + imageIndexControl + ".png";
			setImage(nameSpriteExplosion);
			invunerableTime--;
		}
		else{
			imageIndexControl = 1;
			invunerable = false;
			invunerableTime = 300;
			setImage("enemy.png");
			//posiciona os inimigos fora da tela até o final da wave
			setX(-200);
			setY(0); 
			setDy(0);
			setDx(0);

			Bounds.setBounds(x, y, Sprite.getIconWidth(), Sprite.getIconHeight());
		}
	}
	
	public boolean getInvuneralble() {
		return invunerable;
	}

	public void setInvuneralble(boolean invunerable) {
		this.invunerable = invunerable;
	}

	public boolean getShootEnemy() {
		return shootEnemy;
	}

	public void setShootEnemy(boolean shootEnemy) {
		this.shootEnemy = shootEnemy;
	}

	public void ActualizeBounds() {
		Bounds.setBounds(x, y, Sprite.getIconWidth(), Sprite.getIconHeight());
	}
}
