package game.Mecanics;

import java.util.Random;

import game.Objects.Enemy;

/**
 * Classe responsavel por gerenciar os inimigos que aparecerao durante o jogo. Gerencia quantidade de inimigos local que o inimigo sera 
 * gerado, velocidade, movimentaçao(diagonal ou reta).  
 */
public class EnemyManager {

	private static final int MAX_ENEMY_NUMBER = 10;
	
	public Enemy e[] = new Enemy[MAX_ENEMY_NUMBER];
	public boolean endWave = false;
	private int waveNumber = 1;
	private String message;
	private int messageTimer = 0;
	private int actualEnemyNumber = 1;
	private int generalEnemySpeed = 1;

	public EnemyManager() {
		for(int i = 0; i < MAX_ENEMY_NUMBER; i++){ 
			e[i] = new Enemy(0,-100);
		}
	}

	public void generateRandomPositionOfEnemy(int i){
		Random randomGenerator = new Random();
		int randomX = randomGenerator.nextInt(500);
		boolean validPosition = false;
		e[i].setY(-100); // nao randomico: teto
		e[i].setX(randomX); // 30 =  rand number
		e[i].ActualizeBounds();
		
		while(!validPosition){
			validPosition = true;
			for(int j = 0; j < actualEnemyNumber; j++){
				if(e[i].Bounds.intersects(e[j].Bounds) && j != i){
					validPosition = false;
					randomX = randomGenerator.nextInt(500);
					e[i].setY(-100); // nao randomico: teto
					e[i].setX(randomX); // 30 =  rand number
					e[i].ActualizeBounds();
				}
			}
		}
	}

	public void chooseEnemyMovimentation(int i){
		e[i].setDy(generalEnemySpeed);
	}

	public void chooseKindOfEnemy(int i){
		e[i].setShootEnemy(true);
	}

	public void functions() {
		waveManager();
		for(int i = 0; i < actualEnemyNumber; i++){
			if(e[i].outOfScreen){
				e[i].setImage("enemy.png");
				this.generateRandomPositionOfEnemy(i);
				this.chooseEnemyMovimentation(i);
				e[i].outOfScreen = false;
			}
		}
	}
	
	public void waveManager(){
		if(endWave){
			launchNewWave();
		}	
		else{
			for(int i = 0; i < actualEnemyNumber; i++){
				if(e[i].getEstadoVida())
					return;	
			}
			message = "Fim da wave " + waveNumber;
			endWave = true;
			waveNumber++;
		}
	}

	private void launchNewWave() {
		if(messageTimer == 300)
			message = "Prepare-se para Wave " + waveNumber;
		if(messageTimer == 700){
			for(int i = 0; i < actualEnemyNumber; i++)
				e[i].setEstadoVida(true);
		}
		if(messageTimer == 1000){
			//AQUI ENTRA O QUE ACONTECERA NAS WAVES
			if(waveNumber <= 3)
				actualEnemyNumber++; // aumenta o numero de inimigos por vez
			
			if(waveNumber == 5){
				generalEnemySpeed ++; //aumenta a velocidade do inimigo
			}
			
			if(waveNumber >= 4){
				for(int i = 0; i < e.length; i++)
					e[i].setShootEnemy(true); // adiciona inimigos atiradores
			}
			
			if(waveNumber >= 6){
			for(int i = 0; i < e.length; i++)
				e[i].setDx(1); // adiciona ao inimigo j movimento diagonal
			}
			if(waveNumber >= 7 && waveNumber <= 9){
				actualEnemyNumber++;
			}
			
			if(waveNumber > 9){
				generalEnemySpeed ++;
			}
			
			
			for(int i = 0; i < actualEnemyNumber; i++)
				e[i].outOfScreen = true;
			messageTimer = 0;
			endWave = false;
		}
		messageTimer++;
	}
	
	public int getWaveNumber() {
		return waveNumber;
	}
	
	public void setWaveNumber(int waveNumber){
		this.waveNumber = waveNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getActualEnemyNumber() {
		return actualEnemyNumber;
	}

	public void setActualEnemyNumber(int actualEnemyNumber) {
		this.actualEnemyNumber = actualEnemyNumber;
	}
}
