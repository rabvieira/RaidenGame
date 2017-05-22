package game.Objects;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Classe gerenerica de um objeto presente no jogo.
 */
public class GenericGameObject {
	protected static final int Y_LOWER_BOUND = 580;
	protected static final int Y_UPPER_BOUND = 5;
	protected static final int X_LEFT_BOUND = 5;
	protected static final int X_RIGHT_BOUND = 495;
	protected static final int NUMBER_OF_SHOTS = 3;
	
	protected int x;
	protected int y;
	protected int dx = 0;
	protected int dy = 0;
	private boolean estadoVida = true;
	protected ImageIcon Sprite;
	public Rectangle Bounds = new Rectangle(); 
	
	public GenericGameObject(int x,int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setDx(int dx){
		this.dx = dx;
	}
	
	public void setDy(int dy){
		this.dy = dy;	
	}
	
	public Image getImage() {
		return Sprite.getImage();
	}
	
	public void setImage(String s){
		Sprite = new ImageIcon(s);
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void move(){
			x += dx;
			y += dy;
	}

	public boolean getEstadoVida() {
		return estadoVida;
	}
	public void setEstadoVida(boolean estadoVida) {
		this.estadoVida = estadoVida;
	}
}
