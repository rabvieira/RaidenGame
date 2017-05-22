package game.graphics;

import game.Mecanics.GameLogic;

import javax.swing.JOptionPane;


/**
 * Classe responsavel pela atualizaçao em tempo real do que esta acontecendo na aplicaçao (ThreadPrincipal)
 */

public class MovimentThread extends Thread {
	AnimationBoard target;
	
	public MovimentThread(AnimationBoard target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		try{
			while(true){
				
				if(target.logics.drawInformation == 1)
					inGameAction();
				else
					inGamePause();
				
				target.repaint();
				Thread.sleep(5);
			}
		}catch(Exception e){
			System.out.println("error");
		}
	}

	//somente para verificar se a pausa do game foi bem sucedida
	private void inGamePause() {
		//System.out.println("Game Paused");
	}

	private void inGameAction() {
		if(target.logics.n.getNumberOfLifes() == 0){
			int n;
			n = JOptionPane.showConfirmDialog(null,"Fim de jogo !! Gostaria de registrar seu score ?","Reinicio",JOptionPane.YES_NO_OPTION);
			switch(n){
			case JOptionPane.YES_OPTION:
				target.logics.s.registerPlayerScore();
				newGame();
				break;
			case JOptionPane.NO_OPTION:
				newGame();
				break;
			}
		}
		
		//movimento da nave e verificaçao de morte desta
		if(!target.logics.n.getInvuneralble())
			target.logics.n.move();
		else{
			target.logics.n.invunerableTime();
		}
		
		//invunerabilidade no inicio do jogo
		if(target.logics.n.getNewLifeIinvulnerability()){
			target.logics.n.invunerabilityEnds();
		}
		
		//gamelogics
		target.logics.eManager.functions();
		target.logics.collisions.functions();
		
		//movimento dos tiros
		for(int i = 0; i < target.logics.n.s.length; i++)
			target.logics.n.s[i].move();
		
		//movimento dos inimigos
		for(int i = 0; i < target.logics.eManager.e.length; i++) {
			if(target.logics.eManager.e[i].getInvuneralble() == false)
				target.logics.eManager.e[i].move();
			else{
				target.logics.eManager.e[i].invunerableTime();
			}	
		}
		
		//movimento dos tiros dos inimigos
		for(int j = 0; j < target.logics.eManager.getActualEnemyNumber();j++){
			for(int i = 0; i < target.logics.eManager.e[j].s.length; i++)
				target.logics.eManager.e[j].s[i].move();
		}
		
		// movimento do background
		target.logics.b1.move();
		target.logics.b2.move();
	}

	private void newGame() {
		int k;
		k = JOptionPane.showConfirmDialog(null,"Gostaria de jogar novamente ?","Reinicio",JOptionPane.YES_NO_OPTION);
		switch(k){
		case JOptionPane.YES_OPTION:
			target.logics = new GameLogic();
			target.requerirFoco();
			target.logics.b1.setDy(2);
			target.logics.b2.setDy(2);
			target.logics.drawInformation = 1;
			target.logics.ingame = true;
			for(int i = 0; i < target.logics.eManager.getActualEnemyNumber(); i++)
				target.logics.eManager.e[i].outOfScreen = true;
			break;
		case JOptionPane.NO_OPTION:
			System.exit(0);
			break;
		}
	}
	
	
}
