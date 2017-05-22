package game.Mecanics;

import game.Objects.GenericGameObject;
import game.Objects.Nave;
import game.Objects.Player;

/**
 * Gerenciador de colisoes que acontecem dentro do jogo
 */
public class ColisionDetection {
	
	private Nave n;
	private Player p;
	private EnemyManager eManager;
	
	public ColisionDetection(Nave n, EnemyManager eManager,Player p){
		this.n = n;
		this.eManager = eManager;
		this.p = p;
	}
	
	public boolean DetectColision(GenericGameObject obj1,GenericGameObject obj2){
		if(obj1.Bounds.intersects(obj2.Bounds))
			return true;
		return false;
	}

	/**
	 * Verifica todas as colisoes que podem ocorrer quando o jogo esta em execuçao.
	 */
	public void functions() {
		for(int j = 0; j < eManager.getActualEnemyNumber(); j++){
			if(!n.getInvuneralble() && !eManager.e[j].getInvuneralble() && !n.getNewLifeIinvulnerability()){
				if(DetectColision(n, eManager.e[j])){
					//System.out.println("Colisao NAVE - INIMIGO!!");
					n.setInvuneralble(true);
				}
			}
			
			for(int i = 0; i < n.s.length; i++){
				if(DetectColision(n.s[i], eManager.e[j]) && n.s[i].fire){
					eManager.e[j].setInvuneralble(true);
					if(!n.getInvuneralble() && eManager.e[j].getEstadoVida())
						p.setScore(p.getScore() + 10); 
					

					eManager.e[j].setEstadoVida(false);
					//System.out.println("Colisao TIRO - INIMIGO!!");
				}
			}
			for(int k = 0; k < eManager.getActualEnemyNumber(); k++){
				for(int i = 0; i < n.s.length; i++){//fpc TIROS
					if(DetectColision(eManager.e[k].s[i], n) && eManager.e[k].s[i].fire && !n.getInvuneralble() && !n.getNewLifeIinvulnerability()){
						n.setInvuneralble(true);
						//System.out.println("Colisao TIRO - NAVE!!!");
					}
				}
			}	
		}
	}
}
