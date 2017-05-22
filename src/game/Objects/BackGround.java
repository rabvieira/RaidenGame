package game.Objects;

import javax.swing.ImageIcon;
/**
 * Representa o objeto background. 
 */

public class BackGround extends GenericGameObject{

	private int restartCiclePlace;
	private int cicleDelimiter;
	ImageIcon BackGroundImage;	
	
	public BackGround(int x,int y,int restartCiclePlace,int cicleDelimiter) {
		super(x,y);
		this.restartCiclePlace = restartCiclePlace;
		this.cicleDelimiter = cicleDelimiter;
		Sprite = new ImageIcon("background.png");
	}
	
	public void move(){
		super.move();
		if(y > cicleDelimiter){
			y = restartCiclePlace;
		}
	}
}
