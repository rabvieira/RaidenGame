package game.Mecanics;

import game.Objects.BackGround;
import game.Objects.Nave;
import game.Objects.Player;
/**
 * Classe responsavel que contem todos os elementos de logica do jogo. Gerencia de maneira geral oque acontece no jogo com auxilio
 * de seus parametros. 
 */

public class GameLogic {

	private static final int INITIAL_Y_NAVE = 571;
	private static final int INITIAL_NAVE_X = 227;
	
	public Nave n;
	public BackGround b1;
	public BackGround b2;
	public Player p;
	public ScoreManager s;
	public EnemyManager eManager;
	public ColisionDetection collisions;
	public int drawInformation;;
	public boolean ingame;
	
	public GameLogic() {	
		n = new Nave(INITIAL_NAVE_X,INITIAL_Y_NAVE);
		b1 = new BackGround(0,0,-920,720);
		b2 = new BackGround(0,-920,-920,720);
		p = new Player();
		s = new ScoreManager(p);
		eManager = new EnemyManager();
		collisions = new ColisionDetection(n,eManager,p);
		drawInformation = 0;
	    ingame = false;
	}

}
