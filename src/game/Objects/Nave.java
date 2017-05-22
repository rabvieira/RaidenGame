package game.Objects;

import javax.swing.ImageIcon;

/**
 * Representa a nave do jogador. 
 */

public class Nave extends GenericGameObject{
	
	private static final int NEW_LIFE_INVUNERABILITY_TIME = 500;
	public Shot s[] = new Shot[NUMBER_OF_SHOTS];
	private int numberOfLifes = 3;
	private boolean invunerable = false;
	private boolean newLifeIinvulnerability = false;
	private int newLifeIinvulnerabilityTimer = NEW_LIFE_INVUNERABILITY_TIME;
	private int invunerableTime = 300;
	private int imageIndexControl = 1;
	
	public Nave(int x,int y){
		super(x,y);
		Sprite = new ImageIcon("nave.png");
		Bounds.setBounds(x, y, Sprite.getIconWidth(), Sprite.getIconHeight());
		
		for(int i = 0; i < NUMBER_OF_SHOTS; i++)
			s[i] = new Shot(x+25,y);
	}

	public void move(){
		super.move();
		
		//movimenta os tiros junto a nave
		for(int i = 0; i < NUMBER_OF_SHOTS; i++){
			if(!(s[i].getFire())){
				s[i].setX(x+25);
				s[i].setY(y);
			}
		}
		
		//movimenta o retangulo junto a nave
		Bounds.setBounds(x, y, Sprite.getIconWidth(), Sprite.getIconHeight());
		
		if(x > X_RIGHT_BOUND)
			x = X_RIGHT_BOUND;
		if(x < X_LEFT_BOUND)
			x = X_LEFT_BOUND;
		if(y > Y_LOWER_BOUND)
			y = Y_LOWER_BOUND;
		if(y < Y_UPPER_BOUND)
			y = Y_UPPER_BOUND;	
	}

	public int getNumberOfLifes() {
		return numberOfLifes;
	}

	public void setNumberOfLifes(int numberOfLifes) {
		this.numberOfLifes = numberOfLifes;
	}

	public boolean getInvuneralble() {
		return invunerable;
	}

	public void setInvuneralble(boolean invunerable) {
		this.invunerable = invunerable;
	}

	public void invunerableTime() {
		if(getInvunerableTime() > 0){
			String nameSpriteExplosion;
			
			if(getInvunerableTime() % 10 == 0){
				imageIndexControl++;
			}
			
			nameSpriteExplosion = "explosion" + imageIndexControl + ".png";
			setImage(nameSpriteExplosion);
			setInvunerableTime(getInvunerableTime() - 1);
		}
		else{
			newLifeIinvulnerability = true; 
			imageIndexControl = 1;
			invunerable = false;
			setInvunerableTime(300);
			setImage("nave.png");
			setNumberOfLifes(getNumberOfLifes() - 1); //TRAPAÇA VIDA INFINITA PARA TESTE
			setX(227);
			setY(571); 
			Bounds.setBounds(x, y, Sprite.getIconWidth(), Sprite.getIconHeight());
		}
	}

	public boolean getNewLifeIinvulnerability() {
		return newLifeIinvulnerability;
	}

	public void setNewLifeIinvulnerability(boolean newLifeIinvulnerability) {
		this.newLifeIinvulnerability = newLifeIinvulnerability;
	}

	public void invunerabilityEnds() {
		newLifeIinvulnerabilityTimer--;
		
		//efeito invisivel(invunerabilidade)
		if(newLifeIinvulnerabilityTimer%2 == 0)
			Sprite = new ImageIcon("");
		else
			Sprite = new ImageIcon("nave.png");
		
		
		if(newLifeIinvulnerabilityTimer == 0){
			newLifeIinvulnerability = false;
			newLifeIinvulnerabilityTimer = NEW_LIFE_INVUNERABILITY_TIME;
			Sprite = new ImageIcon("nave.png");
		}
		
		
	}

	public int getInvunerableTime() {
		return invunerableTime;
	}

	public void setInvunerableTime(int invunerableTime) {
		this.invunerableTime = invunerableTime;
	}
}
