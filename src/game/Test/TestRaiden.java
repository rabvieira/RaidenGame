package game.Test;

import static org.junit.Assert.*;
import game.Mecanics.GameLogic;

import org.junit.Test;

/**
 * Classe de testes unitários que almejam simular diversos eventos no jogo.
*/

public class TestRaiden {

	/* Colision:
	 * Os testes 'Colision' tem por objeto testar situações de colisão no jogo.
	 */
	
	/* ColisionGood_Shot:
	 * Verifica a colisão entre um tiro enviado pela Nave (jogador) à naves inimigas.
	 * Quando isto ocorre, a nave acertada sofre dano e caso seja destruída o score do jogador é aumentado.
	 */
	
	@Test
	public void testColisionGood_Shot() {
		GameLogic g = new GameLogic();
		g.eManager.setActualEnemyNumber(1);
		
		g.eManager.e[0].setX(500);
		g.eManager.e[0].setY(500);
		
		g.n.s[0].setX(500);
		g.n.s[0].setY(450);
		
		g.n.s[0].setDy(1);
		g.eManager.e[0].move();
		
		for(int i = 0; i < 50 ; i++)
			g.n.s[0].move();
		
		//verificacao da colisao
		assertEquals(true,g.collisions.DetectColision(g.n.s[0],g.eManager.e[0] ));
		
		//verificaçao do score
		g.n.s[0].setFire(true);
		g.collisions.functions();
		assertEquals(10,g.p.getScore());

	}

	/* ColisionNave_to_Enemy:
	 * Verifica a colisão entre a Nave (jogador) à naves inimigas.
	 * Quando isto ocorre, a nave do jogador é destruida e a vida do jogador é decrementada,
	 */
	
	@Test
	public void testColisionNave_to_Enemy() {
		GameLogic g = new GameLogic();
		g.eManager.setActualEnemyNumber(1);
		g.eManager.e[0].setX(227);
		g.eManager.e[0].setY(571);
		g.eManager.e[0].setDy(1);
		g.eManager.e[0].move();
		g.n.move();
		//teste de colisao
		assertEquals(true,g.collisions.DetectColision(g.eManager.e[0],g.n ));
		
		g.collisions.functions();
		g.n.setInvunerableTime(-1);
		g.n.invunerableTime();
		//teste de perca de vida
		assertEquals(2,g.n.getNumberOfLifes());
	}
	
	/* ColisionBad_Shot:
	 * Vericica a colisão entre um tiro enviado pelas naves inimigas à Nave (jogador).
	 * Quando isto ocorre, a nave acertada sofre dano, perdendo a vida atual.
	 */
	
	@Test
	public void testColisionBad_Shot() {
		GameLogic g = new GameLogic();
		g.eManager.setActualEnemyNumber(1);
		g.eManager.e[0].s[0].setX(227);
		g.eManager.e[0].s[0].setY(571);
		g.eManager.e[0].setDy(1);
		
		g.eManager.e[0].s[0].move();
		g.n.move();

		assertEquals(true,g.collisions.DetectColision(g.eManager.e[0].s[0],g.n ));
		
		g.collisions.functions();
		g.n.setInvunerableTime(-1);
		g.n.invunerableTime();
		//teste de perca de vida
		assertEquals(2,g.n.getNumberOfLifes());
		
	}
	
	/*-----------------------------------------------------------------------------------------------*/
	
	/* Movement:
	 * Os testes 'Movement' tem por objeto testar a movimentação da nave (jogador).
	 */
		
	/* Movement_Right:
	 * Vericica se a nave se desloca para a direita quando ordenada pelo jogador
	 */
	
	@Test
	public void testMovement_Right() {
		//nave inicia na posicao x = 227 y = 571
		GameLogic g = new GameLogic();
		g.n.setDx(1);
		//move um pixel para a direita
		g.n.move();
		
		assertEquals(228,g.n.getX());
	}

	/* Movement_Left:
	 * Vericica se a nave se desloca para a esquerda quando ordenada pelo jogador
	 */
	
	@Test
	public void testMovement_Left() {
		//nave inicia na posicao x = 227 y = 571
		GameLogic g = new GameLogic();
		g.n.setDx(-1);
		//move um pixel para a esquerda
		g.n.move();
		
		assertEquals(226,g.n.getX());
	}

	/* Movement_Up:
	 * Vericica se a nave se desloca para cima quando ordenada pelo jogador
	 */
	
	@Test
	public void testMovement_Up() {
		//nave inicia na posicao x = 227 y = 571
		GameLogic g = new GameLogic();
		g.n.setDy(-1);
		//move um pixel para a cima
		g.n.move();
		
		assertEquals(570,g.n.getY());
	}
	
	/* Movement_Down:
	 * Vericica se a nave se desloca para baixo quando ordenada pelo jogador
	 */
	
	@Test
	public void testMovement_Down() {
		//nave inicia na posicao x = 227 y = 571
		GameLogic g = new GameLogic();
		g.n.setDy(1);
		//move um pixel para a cima
		g.n.move();
		
		assertEquals(572,g.n.getY());
	}
	
	/*-----------------------------------------------------------------------------------------------*/
	
	/* Other:
	 * Os testes 'Other' tem por objeto testar aspectos diversos do jogo
	 */
		
	/* Score
	 * Vericica se o score é incrementado quando a nave (jogador) abate uma nave inimiga
	 */
	
	@Test
	public void testScore() {
		GameLogic g = new GameLogic();
		g.eManager.setActualEnemyNumber(1);
		
		g.eManager.e[0].setX(500);
		g.eManager.e[0].setY(500);
		
		g.n.s[0].setX(500);
		g.n.s[0].setY(450);
		
		g.n.s[0].setDy(1);
		g.eManager.e[0].move();
		
		for(int i = 0; i < 50 ; i++)
			g.n.s[0].move();
		
		//verificaçao do score
		g.n.s[0].setFire(true);
		g.collisions.functions();
		assertEquals(10,g.p.getScore());
	}

}