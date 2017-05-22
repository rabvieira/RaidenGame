package game.graphics;



import game.Mecanics.GameLogic;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Classe responsavel por gerenciar o frame que realiza as animaçoes de interaçao com o usuario (animaçoes em jogo, exibiçao dos topscores
 * etc..).
 */
@SuppressWarnings("serial")
public class AnimationBoard extends  JPanel implements KeyListener{
	private int[] keys = {0, 0, 0, 0};
	GameLogic logics = new GameLogic();
	
	public AnimationBoard() {
		this.addKeyListener(this);
	}
	
	
	/**
	 * Metodo paintComponent responsavel por atualizar o que esta sendo desenhado em Animation Board
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		switch(logics.drawInformation){
		case 0:
			g.drawImage(logics.b1.getImage(),logics.b1.getX(),logics.b1.getY(),null);
			g.drawImage(logics.b2.getImage(),logics.b2.getX(),logics.b2.getY(),null);
			ImageIcon menuImage = new ImageIcon("menu.png");
			g.drawImage(menuImage.getImage(),20,0,null);
			break;
		case 1:
			drawInGameAnimation(g);
		break;
		case 2:
			drawInGameAnimation(g);
			g.drawString("Jogo Pausado" , 200, 20);
			break;
		
		case 3:
			drawScore(g);
			break;
		case 4:
			ImageIcon tutorialImage = new ImageIcon("tutorial.png");
			g.drawImage(tutorialImage.getImage(),0,0,null);
			break;
		default:
			break;
		}
	}

	
	/**
	 * Desenha a janela de TopScores.
	 */
	private void drawScore(Graphics g) {
		try {
			logics.s.actualizeScoreFile();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.setBackground(Color.BLACK);
		g.setFont(new Font("Serif", 100,30));
		g.setColor(Color.WHITE);
		int y = 100;
		g.drawString("TOP 10 SCORES",170, 50);
		
		for(int i = 0; i < 10; i++){
			try{
				g.drawString(logics.s.order.get(i).getName(),50, y);
				g.drawString("" + logics.s.order.get(i).getScore(),450,y);
				y += 30;
			}catch (IndexOutOfBoundsException  e){
				// entra aqui caso nao tenha 10 players registrados
			};
		}
	}

	
	/**
	 * Desenha todas informaçoes relativas ao jogo quando a açao do jogo esta em execuçao
	 */
	private void drawInGameAnimation(Graphics g) {
		ImageIcon LifeSprite = new ImageIcon("vida.png");
		
		//desenho dos backgrounds
		g.drawImage(logics.b1.getImage(),logics.b1.getX(),logics.b1.getY(),null);
		g.drawImage(logics.b2.getImage(),logics.b2.getX(),logics.b2.getY(),null);
		
		//desenho da nave
		g.drawImage(logics.n.getImage(), logics.n.getX(),logics.n.getY(),null);
		
		//desenho dos inimigos
		for(int i = 0; i < logics.eManager.getActualEnemyNumber(); i++){
			g.drawImage(logics.eManager.e[i].getImage(),logics.eManager.e[i].getX(),logics.eManager.e[i].getY(),null);
		}
		
		//desenho tiros dos inimigos
		for(int j = 0; j < logics.eManager.getActualEnemyNumber();j++){
			for(int i = 0; i < logics.n.s.length; i++){
				if(logics.eManager.e[j].s[i].getFire())
					g.drawImage(logics.eManager.e[j].s[i].getImage(),logics.eManager.e[j].s[i].getX(), logics.eManager.e[j].s[i].getY(),null);
			}
		}
		
		//desenho tiros nave
		g.setColor(Color.YELLOW);
		for(int i = 0; i < logics.n.s.length; i++){
			if(logics.n.s[i].getFire())
				g.drawImage(logics.n.s[i].getImage(),logics.n.s[i].getX(), logics.n.s[i].getY(),null);
		}
		
		//desenha o score
		g.setFont(new Font("Serif", 20,20));
		g.drawString("SCORE: " + logics.p.getScore(), 10, 30);
		
		//desenho de final de wave
		if(logics.eManager.endWave){
			g.drawString(logics.eManager.getMessage() , 200, 310);
		}
			
		//desenha os icones de vida
		int xLifeSprite = 460;
		for(int i = 0 ; i < logics.n.getNumberOfLifes(); i++){
			g.drawImage(LifeSprite.getImage(),xLifeSprite,6, null);
			xLifeSprite += 30;
		}
		
		//desenho da municao
		g.setFont(new Font("Serif", 20,20));
		g.drawString("AMMO: ", 8, 52);
		int xAmmoSprite = 80;
		for(int i = 0 ; i < logics.n.s.length; i++){
			if(!logics.n.s[i].fire){
				g.drawImage(logics.n.s[0].getImage(),xAmmoSprite,42, null);
				xAmmoSprite += 15;
			}
		}
	}
	
	public void requerirFoco(){
		this.requestFocusInWindow(true);
	}

	
	/**
	 * Metodo KeyPressed: pega as entradas digitadas pelo utilizador com o cuidado de registrar o buffer(parametro keys[]) do que foi digitado para melhor 
	 * movimentaçao da nave.
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			keys[0] = 1;
			logics.n.setDy(-1);
			break;
		case KeyEvent.VK_RIGHT:
			logics.n.setDx(1);
			keys[1] = 1;
			break;
		case KeyEvent.VK_DOWN:
			logics.n.setDy(1);
			keys[2] = 1;
			break;
		case KeyEvent.VK_LEFT:
			logics.n.setDx(-1);
			keys[3] = 1;
			break;
		case KeyEvent.VK_SPACE:
			int i = 0;
			while (i < logics.n.s.length - 1 && logics.n.s[i].getFire())
				i++;
			logics.n.s[i].setFire(true);
		break;
		default:
			break;
		}	
	}


	
	/**
	 * Metodo KeyReleased: Verifica o buffer do que foi digitado(keys) para quando a tecla for solta ocorra uma transiçao suave de movimento 
	 * da nave seja suave quando esta se move em direçoes opostas(direita,esquerda ou cima,baixo).
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()){
		case KeyEvent.VK_UP:
			if(keys[2] == 0)
				logics.n.setDy(0);
			keys[0] = 0;
			break;
		case KeyEvent.VK_RIGHT:
			if(keys[3] == 0)
				logics.n.setDx(0); 
			keys[1] = 0;
			break;
		case KeyEvent.VK_DOWN:
			if(keys[0] == 0)
				logics.n.setDy(0);
			keys[2] = 0;
			break;
		case KeyEvent.VK_LEFT:
			if(keys[1] == 0)
				logics.n.setDx(0);
			keys[3] = 0;
			break;
		case KeyEvent.VK_P:
			if(logics.ingame){
				if(logics.drawInformation == 1)
					logics.drawInformation = 2;
				else 
					logics.drawInformation = 1;
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	
	}


}
